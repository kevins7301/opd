package com.iisi.opd.data.in.exec.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.common.util.TempFileUtils;
import com.iisi.opd.data.in.exec.UploadExecutor;
import com.iisi.opd.data.in.service.DataInService;
import com.iisi.opd.data.in.vo.DataInOptionsVo;
import com.iisi.opd.log.po.DataInLogPo;

import au.com.bytecode.opencsv.CSVReader;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class CsvUploadExecutorImpl implements UploadExecutor {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataInService dataInServiceImpl;

    @Autowired
    private TempFileUtils tempFileUtils;

    public static final String UTF8_BOM = "﻿";
    private Charset utf8Charset = Charset.forName("UTF-8");

    private boolean isSystemFileEncodingEqualsUTF8 = System.getProperty("file.encoding").equals("UTF-8");

    @Override
    public JSONObject execute(InputStream inputStream) throws Exception {
        return execute(inputStream, null);
    }

    @Override
    public JSONObject execute(InputStream inputStream, DataInOptionsVo optionsVo) throws Exception {
        JSONObject jsonObjectMap = new JSONObject();
        InputStreamReader inputStreamReader = getGuessEndoingToPrepareInputStream(inputStream, optionsVo);
        if (inputStreamReader == null) {
            return jsonObjectMap;
        }

        CSVReader csvReader = new CSVReader(inputStreamReader);
        try {
            String[] header = csvReader.readNext();

            if ((optionsVo != null) && (this.utf8Charset.equals(optionsVo.getCharset()))) {
                header[0] = removeUTF8BOM(header[0]);
            }

            JSONObject createTable = generateCreateTableStatement(csvReader, header);

            skipTitle(csvReader, optionsVo);

            JSONObject insertData = generateInsertDataStatement(csvReader, header);

            jsonObjectMap.put("CREATE_TABLE", createTable);
            jsonObjectMap.put("INSERT_TABLE", insertData);
        } catch (Exception e) {
            throw e;
        } finally {
            csvReader.close();
            inputStreamReader.close();
            inputStream.close();
        }

        return jsonObjectMap;
    }

    @Override
    public Map<String, File> batchExecute(String dataCfgOid, InputStream inputStream,
            DataInOptionsVo optionsVo) throws Exception {
        InputStreamReader inputStreamReader = getGuessEndoingToPrepareInputStream(inputStream, optionsVo);
        if (inputStreamReader == null) {
            return new HashMap();
        }

        CSVReader csvReader = new CSVReader(inputStreamReader);
        Map<String, File> tmpFileMap = null;
        try {
            tmpFileMap = generateTmpFileMap(dataCfgOid);
            String[] header = csvReader.readNext();

            if ((optionsVo != null) && (this.utf8Charset.equals(optionsVo.getCharset()))) {
                header[0] = removeUTF8BOM(header[0]);
            }

            generateCreateTableStatementFile((File) tmpFileMap.get("CREATE_TABLE"), csvReader, header, dataCfgOid);

            if ((optionsVo != null) && (!optionsVo.isCoverOldData())) {

                tmpFileMap.remove("CREATE_TABLE");
            }

            skipTitle(csvReader, optionsVo);

            if ((optionsVo == null) || (optionsVo.isCreateInsertJsonFile())) {
                generateInsertDataStatementFile((File) tmpFileMap.get("INSERT_TABLE"), csvReader, header, optionsVo, dataCfgOid);
            } else {
                tmpFileMap.remove("INSERT_TABLE");
            }
        } catch (Exception e) {
            FileUtils.deleteQuietly((File) tmpFileMap.get("CurrTmpDir"));
            throw e;
        } finally {
            csvReader.close();
            inputStreamReader.close();
            inputStream.close();
        }
        return tmpFileMap;
    }

    private JSONObject generateCreateTableStatement(CSVReader csvReader, String[] header) {
        JSONObject createTable = new JSONObject();
        JSONArray fields = new JSONArray();
        for (String head : header) {
            JSONObject field = new JSONObject();
            field.put("id", head);
            field.put("type", "string");
            fields.add(field);
        }
        createTable.put("fields", fields);

        return createTable;
    }

    private void generateCreateTableStatementFile(File createFile, CSVReader csvReader, String[] header,
            String dataCfgOid) throws IOException {
        JSONObject createTable = generateCreateTableStatement(csvReader, header);
        createTable.put("data_oid", dataCfgOid);
        FileUtils.writeStringToFile(createFile, createTable.toString(), "UTF-8");
    }

    private JSONObject generateInsertDataStatement(CSVReader csvReader, String[] header) throws IOException {
        JSONObject insertData = new JSONObject();
        JSONArray records = new JSONArray();
        String[] data = null;
        while ((data = csvReader.readNext()) != null) {
            records.add(generateRowData(data, header));
        }
        insertData.put("records", records);

        return insertData;
    }

    private void generateInsertDataStatementFile(File insertFile, CSVReader csvReader, String[] header, DataInOptionsVo optionsVo,
            String dataCfgOid) throws IOException {
        JSONObject insertData = new JSONObject();
        if (optionsVo == null) {
            optionsVo = new DataInOptionsVo();
        }

        insertData.put("data_oid", dataCfgOid);
        JSONArray records = new JSONArray();
        String[] data = csvReader.readNext();
        int execBatchIdx = 0;
        optionsVo.setExecCommentType(DataInLogPo.ExecCommentType.APPENDED);
        PrintWriter pw = new PrintWriter(insertFile, "UTF-8");

        while (data != null) {
            records.add(generateRowData(data, header));

            if ((data = csvReader.readNext()) == null) {
                optionsVo.setExecCommentType(DataInLogPo.ExecCommentType.FINISHED);
            }

            execBatchIdx++;
            if (execBatchIdx == optionsVo.getRecordBatchSize()) {
                insertData.put("records", records);
                pw.println(insertData.toString());

                records.clear();
                execBatchIdx = 0;
                pw.flush();
            }
        }

        if (execBatchIdx != 0) {
            insertData.put("records", records);
            pw.println(insertData.toString());
        }

        pw.flush();
        pw.close();
    }

    private JSONObject generateRowData(String[] data, String[] header) {
        JSONObject record = new JSONObject();
        for (int j = 0; j < header.length; j++) {
            record.put(header[j], data[j]);
        }

        return record;
    }

    private InputStreamReader getGuessEndoingToPrepareInputStream(InputStream inputStream, DataInOptionsVo optionsVo) {
        try {
            if ((optionsVo != null) && (optionsVo.getCharset() != null)) {
                Charset charset = optionsVo.getCharset();

                if ("UTF-8".equals(charset.name())) {
                    return new InputStreamReader(inputStream, "UTF-8");
                }
                return new InputStreamReader(inputStream, "Big5");
            }

            if (!this.isSystemFileEncodingEqualsUTF8) {
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuffer sb = new StringBuffer();
                String str;
                while ((str = bufferReader.readLine()) != null) {
                    sb.append(str).append("\n");
                }

                inputStream = new ByteArrayInputStream(sb.toString().getBytes(System.getProperty("file.encoding")));
            }
            return new InputStreamReader(inputStream);
        } catch (Exception e) {
        }

        return null;
    }

    private static String removeUTF8BOM(String s) {
        if (s.startsWith("﻿")) {
            return s.substring(1);
        }
        return s;
    }

    private void skipTitle(CSVReader csvReader, DataInOptionsVo optionsVo) throws IOException {
        if ((optionsVo != null) && (optionsVo.isMutilLangTitle())) {
            csvReader.readNext();
        }
    }

    private Map<String, File> generateTmpFileMap(String dataCfgOid) throws IOException {
        Map<String, File> tmpMap = new HashMap();
        File currentTmpFile = this.tempFileUtils.getTempDirectory(dataCfgOid);
        tmpMap.put("CurrTmpDir", currentTmpFile);

        File createStatment = new File(currentTmpFile, "CREATE_TABLE");
        tmpMap.put("CREATE_TABLE", createStatment);
        FileUtils.touch(createStatment);

        File insertStatment = new File(currentTmpFile, "INSERT_TABLE");
        tmpMap.put("INSERT_TABLE", insertStatment);
        FileUtils.touch(insertStatment);

        return tmpMap;
    }
}