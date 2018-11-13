package com.iisi.opd.data.in.exec.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.common.util.TempFileUtils;
import com.iisi.opd.data.in.exec.UploadExecutor;
import com.iisi.opd.data.in.service.DataInService;
import com.iisi.opd.data.in.vo.DataInOptionsVo;
import com.iisi.opd.log.po.DataInLogPo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class ExcelUploadExecutorImpl implements UploadExecutor {
    @Autowired
    private DataInService dataInServiceImpl;
    @Autowired
    private TempFileUtils tempFileUtils;

    @Override
    public JSONObject execute(InputStream inputStream) throws Exception {
        return execute(inputStream, null);
    }

    @Override
    public JSONObject execute(InputStream inputStream, DataInOptionsVo optionsVo) throws Exception {
        JSONObject jsonObjectMap = new JSONObject();
        Workbook workbook = WorkbookFactory.create(inputStream);
        if (workbook == null) {
            return jsonObjectMap;
        }

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        try {
            List<String> headerList = new ArrayList();

            JSONObject createTable = generateCreateTableStatement(rowIterator, headerList);

            skipTitle(rowIterator, optionsVo);

            JSONObject insertData = generateInsertDataStatement(rowIterator, headerList);

            jsonObjectMap.put("CREATE_TABLE", createTable);
            jsonObjectMap.put("INSERT_TABLE", insertData);
        } catch (Exception e) {
            throw e;
        } finally {
            inputStream.close();
        }

        return jsonObjectMap;
    }

    @Override
    public Map<String, File> batchExecute(String dataCfgOid, InputStream inputStream,
            DataInOptionsVo optionsVo) throws Exception {
        Workbook workbook = WorkbookFactory.create(inputStream);
        if (workbook == null) {
            return new HashMap();
        }

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Map<String, File> tmpFileMap = null;
        try {
            tmpFileMap = generateTmpFileMap(dataCfgOid);
            List<String> headerList = new ArrayList();

            generateCreateTableStatementFile((File) tmpFileMap.get("CREATE_TABLE"), rowIterator, headerList, dataCfgOid);

            if ((optionsVo != null) && (!optionsVo.isCoverOldData())) {

                tmpFileMap.remove("CREATE_TABLE");
            }

            skipTitle(rowIterator, optionsVo);

            generateInsertDataStatementFile((File) tmpFileMap.get("INSERT_TABLE"), rowIterator, headerList, optionsVo,
                    dataCfgOid);
        } catch (Exception e) {
            FileUtils.deleteQuietly((File) tmpFileMap.get("CurrTmpDir"));
            throw e;
        } finally {
            inputStream.close();
        }
        return tmpFileMap;
    }

    private JSONObject generateCreateTableStatement(Iterator<Row> rows, List<String> headerList) {
        JSONObject createTable = new JSONObject();
        JSONArray fields = new JSONArray();
        if (rows.hasNext()) {
            Iterator<Cell> cells = ((Row) rows.next()).cellIterator();
            while (cells.hasNext()) {
                Cell cell = (Cell) cells.next();
                JSONObject field = new JSONObject();
                field.put("id", cell.getStringCellValue());
                field.put("type", "string");
                fields.add(field);
                headerList.add(cell.getStringCellValue());
            }
        }
        createTable.put("fields", fields);

        return createTable;
    }

    private void generateCreateTableStatementFile(File createFile, Iterator<Row> rows, List<String> headerList,
            String dataCfgOid) throws IOException {
        JSONObject createTable = generateCreateTableStatement(rows, headerList);
        createTable.put("data_oid", dataCfgOid);
        FileUtils.writeStringToFile(createFile, createTable.toString(), "UTF-8");
    }

    private JSONObject generateInsertDataStatement(Iterator<Row> rows, List<String> headerList) {
        JSONObject insertData = new JSONObject();
        JSONArray records = new JSONArray();
        while (rows.hasNext()) {
            Row row = (Row) rows.next();
            records.add(generateRowData(row, headerList));
        }
        insertData.put("records", records);

        return insertData;
    }

    private void generateInsertDataStatementFile(File insertFile, Iterator<Row> rows, List<String> headerList,
            DataInOptionsVo optionsVo, String dataCfgOid) throws IOException {
        JSONObject insertData = new JSONObject();
        if (optionsVo == null) {
            optionsVo = new DataInOptionsVo();
        }

        insertData.put("data_oid", dataCfgOid);
        JSONArray records = new JSONArray();
        int execBatchIdx = 0;
        optionsVo.setExecCommentType(DataInLogPo.ExecCommentType.APPENDED);
        PrintWriter pw = new PrintWriter(insertFile, "UTF-8");

        while (rows.hasNext()) {
            Row row = (Row) rows.next();
            records.add(generateRowData(row, headerList));

            if (!rows.hasNext()) {
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

    private JSONObject generateRowData(Row row, List<String> headerList) {
        JSONObject record = new JSONObject();

        for (int index = 0; index < headerList.size(); index++) {
            Cell cell = row.getCell(index);

            if (cell == null) {
                record.put(headerList.get(index), "");
            } else {
                cell.setCellType(1);
                record.put(headerList.get(cell.getColumnIndex()), cell.getRichStringCellValue().toString());
            }
        }

        return record;
    }

    private void skipTitle(Iterator<Row> rows, DataInOptionsVo optionsVo) {
        if ((optionsVo != null) && (optionsVo.isMutilLangTitle())) {
            rows.next();
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