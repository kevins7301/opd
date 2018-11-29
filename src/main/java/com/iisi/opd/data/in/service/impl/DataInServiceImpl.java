package com.iisi.opd.data.in.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.common.util.GuessEncodeUtils;
import com.iisi.common.util.TempFileUtils;
import com.iisi.opd.cfg.dao.DataCfgDao;
import com.iisi.opd.cfg.dto.DataFieldCfgDto;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataFieldCfgPo;
import com.iisi.opd.cfg.service.DataCfgFileService;
import com.iisi.opd.cfg.service.DataCfgService;
import com.iisi.opd.cfg.service.DataRuleBaseService;
import com.iisi.opd.data.in.check.FieldExecutor;
import com.iisi.opd.data.in.dao.DataInDao;
import com.iisi.opd.data.in.exec.UploadExecutor;
import com.iisi.opd.data.in.service.DataFieldCfgDtoPoTransformer;
import com.iisi.opd.data.in.service.DataInService;
import com.iisi.opd.data.in.vo.DataInCheckVo;
import com.iisi.opd.data.in.vo.DataInOptionsVo;
import com.iisi.opd.data.in.vo.DataInProcessVo;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;
import com.iisi.opd.log.dao.DataCheckResultDao;
import com.iisi.opd.log.dao.DataInLogDao;
import com.iisi.opd.log.po.DataCheckResultPo;
import com.iisi.opd.log.po.DataInLogPo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional(noRollbackFor = OpdException.class, propagation = Propagation.REQUIRED)
public class DataInServiceImpl implements DataInService {
    public final Logger logger;
    @Autowired
    private DataInDao dataInDao;
    @Autowired
    private DataCfgDao dataCfgDao;
    @Autowired
    private DataCfgService dataCfgService;
    @Autowired
    private DataCfgFileService dataCfgFileService;
    @Autowired
    private DataRuleBaseService dataRuleBaseService;
    @Autowired
    private DataInLogDao dataInLogDao;
    @Autowired
    private DataCheckResultDao dataCheckResultDao;
    @Autowired
    private GuessEncodeUtils guessEncodeUtils;
    @Resource(name = "executorMap")
    private Map<String, UploadExecutor> executorMap;
    @Resource(name = "checkExecutorkMap")
    private Map<String, FieldExecutor> checkExecutorkMap;
    @Resource(name = "fieldTypeMap")
    private Map<String, Boolean> fieldTypeMap;
    @Autowired
    private DataFieldCfgDtoPoTransformer dataFieldCfgDtoPoTransformer;
    @Autowired
    private TempFileUtils tempFileUtils;
    @Autowired
    private ThreadPoolTaskExecutor opdThreadPoolTaskExecutor;

    public DataInServiceImpl() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public void createTable(String json) {
        createTable(json, new DataInOptionsVo());
    }

    @Override
    public void createTable(String json, DataInOptionsVo optionsVo) {
        if (optionsVo == null) {
            optionsVo = new DataInOptionsVo();
        }
        this.dataInDao.createTable(json, optionsVo);

        updateInfoToDataCfgPo(JSONObject.fromObject(json).getString("data_oid"), 0);
    }

    @Override
    public void updateData(String json) {
        updateData(json, new DataInOptionsVo());
    }

    @Override
    public void updateData(String json, DataInOptionsVo optionsVo) {
        if (optionsVo == null) {
            optionsVo = new DataInOptionsVo();
        }
        DataInCheckVo vo = checkJsonForDataIn(json, optionsVo);

        int updateCount = 0;
        try {
            updateCount = this.dataInDao.updateData(json);
        } catch (OpdException e) {
            Date endTime = new Date();
            Date logTime = new Date();
            createDataInLogPo(vo.getDataCfgPo(), null, DataInLogPo.TransferOptions.BATCH, vo.getCount(), 0, 0, 0,
                    DataInLogPo.CheckResult.SUCCESS, DataInLogPo.ExecResult.FAIL_OTHERS, vo.getStartTime(), endTime,
                    getExceptionessage(e), logTime);
            throw e;
        }
        DataCheckResultPo dataCheckResultPo = generateDataCheckResultPo(vo.isHasCheckRule());

        Date endTime = new Date();
        Date logTime = new Date();
        createDataInLogPo(vo.getDataCfgPo(), dataCheckResultPo.getOid(), DataInLogPo.TransferOptions.BATCH, vo.getCount(), 0,
                updateCount, 0, DataInLogPo.CheckResult.SUCCESS, DataInLogPo.ExecResult.SUCCESS, vo.getStartTime(), endTime, null,
                logTime);

        updateInfoToDataCfgPo(vo.getDataOid());
    }

    @Override
    public void insertData(String json) {
        insertData(json, new DataInOptionsVo());
    }

    @Override
    public void insertData(String json, DataInOptionsVo optionsVo) {
        if (optionsVo == null) {
            optionsVo = new DataInOptionsVo();
        }
        DataInCheckVo vo = checkJsonForDataIn(json, optionsVo);

        int insertCount = 0;
        try {
            insertCount = this.dataInDao.insertData(json, optionsVo.isRegularize());
        } catch (OpdException e) {
            Date endTime = new Date();
            Date logTime = new Date();
            createDataInLogPo(vo.getDataCfgPo(), null, DataInLogPo.TransferOptions.BATCH, vo.getCount(), 0, 0, 0,
                    DataInLogPo.CheckResult.SUCCESS, DataInLogPo.ExecResult.FAIL_OTHERS, vo.getStartTime(), endTime,
                    getExceptionessage(e), logTime, optionsVo.getExecCommentType());
            throw e;
        }
        DataCheckResultPo dataCheckResultPo = generateDataCheckResultPo(vo.isHasCheckRule());

        Date endTime = new Date();
        Date logTime = new Date();
        createDataInLogPo(vo.getDataCfgPo(), dataCheckResultPo.getOid(), DataInLogPo.TransferOptions.BATCH, vo.getCount(),
                insertCount, 0, 0, DataInLogPo.CheckResult.SUCCESS, DataInLogPo.ExecResult.SUCCESS, vo.getStartTime(), endTime,
                null, logTime, optionsVo.getExecCommentType());

        updateInfoToDataCfgPo(vo.getDataOid());
    }

    private void insertData(DataInProcessVo vo) {
        DataCfgPo po = this.dataCfgService.findByOid(vo.getDataCfgOid());
        int insertCount = 0;
        try {
            insertCount = this.dataInDao.insertData(vo.getInsertJson().toString(), vo.getDataInOptionsVo().isRegularize());
        } catch (OpdException e) {
            Date endTime = new Date();
            Date logTime = new Date();
            createDataInLogPo(po, null, DataInLogPo.TransferOptions.BATCH, vo.getInsertJson().getJSONArray("records").size(), 0,
                    0, 0, DataInLogPo.CheckResult.SUCCESS, DataInLogPo.ExecResult.FAIL_OTHERS, vo.getStartTime(), endTime,
                    getExceptionessage(e), logTime, vo.getDataInOptionsVo().getExecCommentType());
            throw e;
        }
        DataCheckResultPo dataCheckResultPo = generateDataCheckResultPo(vo.getDataFieldCfgDtoList().size() > 0);

        Date endTime = new Date();
        Date logTime = new Date();
        createDataInLogPo(po, dataCheckResultPo.getOid(), DataInLogPo.TransferOptions.BATCH,
                vo.getInsertJson().getJSONArray("records").size(), insertCount, 0, 0, DataInLogPo.CheckResult.SUCCESS,
                DataInLogPo.ExecResult.SUCCESS, vo.getStartTime(), endTime, null, logTime,
                vo.getDataInOptionsVo().getExecCommentType());

        updateInfoToDataCfgPo(vo.getDataCfgOid());
    }

    @Override
    public void deleteData(String json) {
        Date startTime = new Date();
        String data_oid = JSONObject.fromObject(json).getString("data_oid");
        DataCfgPo dataCfgPo = this.dataCfgService.findByOid(data_oid);
        int count = this.dataInDao.count(data_oid);

        int deleteCount = 0;
        try {
            deleteCount = this.dataInDao.deleteData(json);
        } catch (OpdException e) {
            Date endTime = new Date();
            Date logTime = new Date();
            createDataInLogPo(dataCfgPo, null, DataInLogPo.TransferOptions.BATCH, count, 0, 0, 0, DataInLogPo.CheckResult.SUCCESS,
                    DataInLogPo.ExecResult.FAIL_OTHERS, startTime, endTime, getExceptionessage(e), logTime);
            throw e;
        }
        DataCheckResultPo dataCheckResultPo = generateDataCheckResultPo(true);

        Date endTime = new Date();
        Date logTime = new Date();
        createDataInLogPo(dataCfgPo, dataCheckResultPo.getOid(), DataInLogPo.TransferOptions.BATCH, count, 0, 0, deleteCount,
                DataInLogPo.CheckResult.SUCCESS, DataInLogPo.ExecResult.SUCCESS, startTime, endTime, null, logTime);

        updateInfoToDataCfgPo(data_oid);
    }

    private void transFileIntoDB(String oid, String fileName, File file, DataInOptionsVo optionsVo) throws Exception {
        DataCfgPo dataCfgPo = this.dataCfgService.findByOid(oid);
        if ((dataCfgPo.isStructured().booleanValue()) && (optionsVo.isExecParsingFile())) {
            int index = fileName.lastIndexOf(".");
            String subFileName = "";
            if (index > -1) {
                subFileName = fileName.substring(index).toLowerCase();
            }
            int totalRows = -1;
            if (this.executorMap.containsKey(subFileName)) {
                UploadExecutor uploadExecutor = (UploadExecutor) this.executorMap.get(subFileName);
                if (subFileName.equals(".csv")) {
                    InputStream inputStream = FileUtils.openInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    Charset charset = this.guessEncodeUtils.guessEncodeForStream(bis, (int) file.length());
                    if (!"UTF-8".equals(charset.toString())) {
                        charset = Charset.forName("BIG5");
                    }
                    optionsVo.setCharset(charset);
                    bis.close();
                    inputStream.close();

                    totalRows = totalRowsOfCsvFile(file, optionsVo.getCharset());
                }
                Map<String, File> tmpFileMap = null;
                try {
                    if (totalRows > optionsVo.getMaxRowsOfCsvFile()) {
                        optionsVo.setCreateInsertJsonFile(false);
                    }
                    tmpFileMap = uploadExecutor.batchExecute(oid, FileUtils.openInputStream(file), optionsVo);
                    if (optionsVo.isCoverOldData()) {
                        File createStatmentFile = (File) tmpFileMap.get("CREATE_TABLE");

                        createTable(FileUtils.readFileToString(createStatmentFile, "UTF-8"), optionsVo);
                    }
                    if (!optionsVo.isCreateInsertJsonFile()) {
                        int count = this.dataInDao.batchInsertCsvData(oid, file, optionsVo.getCharset());
                        updateInfoToDataCfgPo(oid, count);
                    } else {
                        File insertStatmentFile = (File) tmpFileMap.get("INSERT_TABLE");
                        List<Future<Boolean>> futureList = multiThreadInsertData(insertStatmentFile, optionsVo);
                        for (;;) {
                            for (Future<Boolean> future : futureList) {
                                if ((((Boolean) future.get()).booleanValue()) && ((future.isDone()) || (future.isCancelled()))) {
                                    futureList.remove(future);
                                }
                            }
                            if (futureList.isEmpty()) {
                                break;
                            }
                        }
                    }
                } catch (OpdException e) {
                    throw e;
                } catch (Exception e) {
                    throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
                } finally {
                    this.opdThreadPoolTaskExecutor.submit(new DeleteFileTask((File) tmpFileMap.get("CurrTmpDir")));
                }
            } else {
                Date startTime = new Date();
                Date endTime = new Date();
                Date logTime = new Date();
                OpdException e = new OpdException(ErrorCodeEnum.ERR_2080015_EXCEPTION);
                createDataInLogPo(dataCfgPo, null, DataInLogPo.TransferOptions.BATCH, 0, 0, 0, 0,
                        DataInLogPo.CheckResult.CHECK_FAIL, DataInLogPo.ExecResult.FAIL_STRUCTURE_ERROR, startTime, endTime,
                        getExceptionessage(e), logTime);
                resourceModifiedDateToDataCfgPo(dataCfgPo.getOid());

                throw e;
            }
        } else {
            Date startTime = new Date();
            Date endTime = new Date();
            Date logTime = new Date();
            createDataInLogPo(dataCfgPo, null, DataInLogPo.TransferOptions.SINGLE, 1, 1, 0, 0, DataInLogPo.CheckResult.SKIP_CHECK,
                    DataInLogPo.ExecResult.SUCCESS, startTime, endTime, null, logTime);
            resourceModifiedDateToDataCfgPo(dataCfgPo.getOid());
        }
    }

    @Override
    public void uploadFile(String oid, String fileName, long fileSize, byte[] fileContent) throws Exception {
        uploadFile(oid, fileName, fileSize, fileContent, null);
    }

    @Override
    public void uploadFile(String oid, String fileName, long fileSize, byte[] fileContent,
            DataInOptionsVo optionsVo) throws Exception {
        File tmpDir = this.tempFileUtils.getTempDirectory(oid);
        File tmpDataInFile = new File(tmpDir, fileName);

        FileUtils.writeByteArrayToFile(tmpDataInFile, fileContent);
        fileContent = null;
        if (optionsVo == null) {
            optionsVo = new DataInOptionsVo();
        }
        try {
            uploadFile(oid, fileName, fileSize, tmpDataInFile, optionsVo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.opdThreadPoolTaskExecutor.submit(new DeleteFileTask(tmpDir));
        }
    }

    @Override
    public void uploadFile(String oid, String fileName, long fileSize, File file, DataInOptionsVo optionsVo) throws Exception {
        transFileIntoDB(oid, fileName, file, optionsVo);

        this.dataCfgFileService.saveArgsByDataCfgOid(oid, fileName, fileSize, file);
    }

    @Override
    public void uploadFileBatch(String oid, String fileName, long fileSize, File file,
            DataInOptionsVo optionsVo) throws Exception {
        transFileIntoDB(oid, fileName, file, optionsVo);

        this.dataCfgFileService.saveArgsByDataCfgOid(oid, fileName, fileSize, file);
    }

    private void resourceModifiedDateToDataCfgPo(String oid) {
        this.dataCfgDao.updateCfginfo(oid);
    }

    private void updateInfoToDataCfgPo(String oid) {
        this.dataCfgDao.updateCfginfo(oid, this.dataInDao.count(oid));
    }

    private void updateInfoToDataCfgPo(String oid, int dataCount) {
        this.dataCfgDao.updateCfginfo(oid, dataCount);
    }

    private void createDataInLogPo(DataCfgPo dataCfgPo, String checkResultOid, DataInLogPo.TransferOptions transferOptions,
            int sourceDataCount, int insertCount, int updateCount, int deleteCount, DataInLogPo.CheckResult checkResult,
            DataInLogPo.ExecResult execResult, Date startTime, Date endTime, String ErrMsg, Date logTime) {
        createDataInLogPo(dataCfgPo, checkResultOid, transferOptions, sourceDataCount, insertCount, updateCount, deleteCount,
                checkResult, execResult, startTime, endTime, ErrMsg, logTime, DataInLogPo.ExecCommentType.STANDARD);
    }

    private void createDataInLogPo(DataCfgPo dataCfgPo, String checkResultOid, DataInLogPo.TransferOptions transferOptions,
            int sourceDataCount, int insertCount, int updateCount, int deleteCount, DataInLogPo.CheckResult checkResult,
            DataInLogPo.ExecResult execResult, Date startTime, Date endTime, String ErrMsg, Date logTime,
            DataInLogPo.ExecCommentType execCommentType) {
        DataInLogPo dataInlogPo = new DataInLogPo();
        dataInlogPo.setDataCfgOid(dataCfgPo.getOid());
        if (dataCfgPo.getDataSetPo() != null) {
            dataInlogPo.setDataSetOid(dataCfgPo.getDataSetPo().getOid());
        }
        if ((dataCfgPo.getDataSetPo() != null) && (dataCfgPo.getDataSetPo().getUnitPo() != null)) {
            dataInlogPo.setDataSetUnitOid(dataCfgPo.getDataSetPo().getUnitPo().getOid());
        }
        dataInlogPo.setCheckResultOid(checkResultOid);
        dataInlogPo.setTransferOptions(transferOptions);
        dataInlogPo.setSourceDataCount(sourceDataCount);
        dataInlogPo.setInsertCount(insertCount);
        dataInlogPo.setUpdateCount(updateCount);
        dataInlogPo.setDeleteCount(deleteCount);
        dataInlogPo.setCheckResult(checkResult);
        dataInlogPo.setExecResult(execResult);
        dataInlogPo.setStartTime(startTime);
        dataInlogPo.setEndTime(endTime);
        dataInlogPo.setErrMsg(ErrMsg);
        dataInlogPo.setLogTime(logTime);
        dataInlogPo.setExecCommentMode(execCommentType);
        this.dataInLogDao.save(dataInlogPo);
    }

    private DataInCheckVo checkJsonForDataIn(String json, DataInOptionsVo optionsVo) {
        DataInCheckVo vo = new DataInCheckVo(json);
        int permitErrorCount = standardCheckProcess(vo, optionsVo);
        if (permitErrorCount > 0) {
            DataCheckResultPo dataCheckResultPo = saveDataCheckResultPo(vo.getErrorMeg().toString());
            OpdException e = new OpdException(ErrorCodeEnum.ERR_2070028_EXCEPTION, String.valueOf(permitErrorCount));
            Date endTime = new Date();
            Date logTime = new Date();
            createDataInLogPo(vo.getDataCfgPo(), dataCheckResultPo.getOid(), DataInLogPo.TransferOptions.BATCH, vo.getCount(), 0,
                    0, 0, DataInLogPo.CheckResult.CHECK_FAIL, DataInLogPo.ExecResult.FAIL_CHECKFAIL, vo.getStartTime(), endTime,
                    getExceptionessage(e), logTime);
            throw e;
        }
        return vo;
    }

    private int standardCheckProcess(DataInCheckVo vo, DataInOptionsVo optionsVo) {
        JSONArray records = vo.getRecords();

        vo.setDataCfgPo(this.dataCfgService.findByOid(vo.getDataOid()));

        Map<String, List<DataFieldCfgPo>> columnFiledCfgMap = this.dataCfgService
                .getColumnFiledCfgMapByDataCfgPo(vo.getDataCfgPo());

        vo.setHasCheckRule(columnFiledCfgMap.size() > 0);
        Set<String> fieldNameKeySet = columnFiledCfgMap.keySet();
        StringBuffer errorStringBuffer = vo.getErrorMeg();
        List<String> columnErrorInfo = new ArrayList();
        int permitErrorCount = 0;

        int idx = 0;
        for (int length = records.size(); idx < length; idx++) {
            Iterator<String> iterator = fieldNameKeySet.iterator();
            JSONObject recordObj = (JSONObject) records.get(idx);
            boolean thisRoundErrorHappen = false;
            String fieldName;
            FieldExecutor checkExecutor;
            String dataValue;
            while (iterator.hasNext()) {
                fieldName = (String) iterator.next();
                List<DataFieldCfgPo> temp_List = (List) columnFiledCfgMap.get(fieldName);
                checkExecutor = (FieldExecutor) this.checkExecutorkMap.get(((DataFieldCfgPo) temp_List.get(0)).getFieldType());

                Object obj = recordObj.get(fieldName);
                if (obj != null) {
                    dataValue = obj.toString();
                    for (DataFieldCfgPo fieldCfgPo : temp_List) {
                        if (!checkFieldExecute(checkExecutor, dataValue, fieldCfgPo)) {
                            thisRoundErrorHappen = true;
                            columnErrorInfo
                                    .add(String.format("�W�� %1$s�A���e %2$s�A���q�L %3$s ���W�h %4$s ���d�C<br/>", new Object[] {
                                            fieldName, dataValue, fieldCfgPo.getCheckMethod(), fieldCfgPo.getCheckRule() }));
                        }
                    }
                }
            }
            if (thisRoundErrorHappen) {
                permitErrorCount++;
                errorStringBuffer.append(String.format("�� %1$d ��<br/>", new Object[] { Integer.valueOf(idx) }));
                for (String errStr : columnErrorInfo) {
                    errorStringBuffer.append(errStr);
                }
                columnErrorInfo.clear();
                errorStringBuffer.append("\n");
                if (permitErrorCount >= optionsVo.getPermitCheckFaultSize()) {
                    break;
                }
            }
        }
        return permitErrorCount;
    }

    private boolean checkFieldExecute(FieldExecutor checkExecutor, String dataValue, DataFieldCfgPo po) {
        try {
            Method method = checkExecutor.getClass().getDeclaredMethod(po.getCheckMethod(),
                    new Class[] { String.class, String.class });
            method.setAccessible(true);
            return ((Boolean) method.invoke(checkExecutor, new Object[] { dataValue, po.getCheckRule() })).booleanValue();
        } catch (OpdException e) {
            this.logger.error(String.format("[OpdException Error]:%1$s, dataValue=[%2$s], checkMethod=[%3$s], checkRule=[%4$s]",
                    new Object[] { checkExecutor.getClass().getName(), dataValue, po.getCheckMethod(), po.getCheckRule() }),
                    e.getCause());

            return false;
        } catch (Exception e) {
            this.logger.error(String.format("[Exception Error]:%1$s, dataValue=[%2$s], checkMethod=[%3$s], checkRule=[%4$s]",
                    new Object[] { checkExecutor.getClass().getName(), dataValue, po.getCheckMethod(), po.getCheckRule() }),
                    e.getCause());
        }
        return false;
    }

    private DataCheckResultPo generateDataCheckResultPo(boolean hasCheckRule) {
        return hasCheckRule ? saveDataCheckResultPo("�������`") : saveDataCheckResultPo("�������`�A�L��������");
    }

    private DataCheckResultPo saveDataCheckResultPo(String content) {
        DataCheckResultPo dataCheckResultPo = new DataCheckResultPo();
        dataCheckResultPo.setContent(content);
        return (DataCheckResultPo) this.dataCheckResultDao.save(dataCheckResultPo);
    }

    private String getExceptionessage(Exception e) {
        StringWriter str = new StringWriter();
        e.printStackTrace(new PrintWriter(str));
        this.logger.error("", e);
        return str.getBuffer().toString();
    }

    @Override
    public void dataInStandardProcess(DataInProcessVo dataInProcessVo) {
        if (dataInProcessVo.getDataCfgOid() == null) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }
        if (dataInProcessVo.getFileContext() == null) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }
        int errorCnt = checkFileStandardProcess(dataInProcessVo);
        if (errorCnt > 0) {
            return;
        }
        JSONObject jsObj = dataInProcessVo.getCreateJson();
        jsObj.put("data_oid", dataInProcessVo.getDataCfgOid());
        jsObj = dataInProcessVo.getInsertJson();
        jsObj.put("data_oid", dataInProcessVo.getDataCfgOid());

        createTable(dataInProcessVo.getCreateJson().toString(), dataInProcessVo.getDataInOptionsVo());

        insertData(dataInProcessVo);
    }

    @Override
    public int checkFileStandardProcess(DataInProcessVo dataInProcessVo) {
        if (dataInProcessVo == null) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }
        if (dataInProcessVo.getDataFieldCfgPoList() == null) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }
        if (dataInProcessVo.getFileType() == null) {
            throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION);
        }
        execFileAnalysisProcess(dataInProcessVo);

        dataInProcessVo.setDataFieldCfgDtoList(
                this.dataFieldCfgDtoPoTransformer.convertListToDto(dataInProcessVo.getDataFieldCfgPoList()));

        return execCheckFieldProcess(dataInProcessVo);
    }

    private int execCheckFieldProcess(DataInProcessVo dataInProcessVo) {
        JSONArray records = dataInProcessVo.getInsertJson().getJSONArray("records");

        Map<String, List<DataFieldCfgDto>> columnFiledCfgMap = dataFieldCfgDtoGroupProcess(
                dataInProcessVo.getDataFieldCfgDtoList());

        Object[] fieldNameKeySet = columnFiledCfgMap.keySet().toArray();

        List<String> columnErrorInfo = new ArrayList();
        int permitErrorCount = 0;

        int idx = 0;
        for (int length = records.size(); idx < length; idx++) {
            JSONObject recordObj = (JSONObject) records.get(idx);
            boolean thisRoundErrorHappen = false;
            FieldExecutor checkExecutor;
            String dataValue;
            for (Object fieldName : fieldNameKeySet) {
                List<DataFieldCfgDto> temp_List = (List) columnFiledCfgMap.get(fieldName.toString());
                checkExecutor = (FieldExecutor) this.checkExecutorkMap.get(((DataFieldCfgDto) temp_List.get(0)).getFieldType());

                Object obj = recordObj.get(fieldName);
                if (obj != null) {
                    dataValue = obj.toString();
                    for (DataFieldCfgDto fieldCfgDto : temp_List) {
                        if (!checkFieldExecuteProcess(checkExecutor, dataValue, fieldCfgDto)) {
                            thisRoundErrorHappen = true;
                            columnErrorInfo
                                    .add(String.format("�W�� %1$s�A���e %2$s�A���q�L %3$s ���W�h %4$s ���d�C<br/>", new Object[] {
                                            fieldName, dataValue, fieldCfgDto.getCheckMethod(), fieldCfgDto.getCheckRule() }));
                        }
                    }
                }
            }
            if (thisRoundErrorHappen) {
                permitErrorCount++;

                StringBuffer errorStringBuffer = new StringBuffer();

                dataInProcessVo.getErrorMessageMap().put(new Integer(idx), errorStringBuffer);

                errorStringBuffer.append(String.format("�� %1$d ��<br/>", new Object[] { Integer.valueOf(idx) }));
                for (String errStr : columnErrorInfo) {
                    errorStringBuffer.append(errStr);
                }
                columnErrorInfo.clear();
                errorStringBuffer.append("\n");
                if (permitErrorCount >= dataInProcessVo.getDataInOptionsVo().getPermitCheckFaultSize()) {
                    break;
                }
            }
        }
        return permitErrorCount;
    }

    private void execFileAnalysisProcess(DataInProcessVo dataInProcessVo) {
        String subFileName = "." + dataInProcessVo.getFileType();
        if (this.executorMap.containsKey(subFileName)) {
            byte[] fileContent = dataInProcessVo.getFileContext();
            DataInOptionsVo optionsVo = dataInProcessVo.getDataInOptionsVo();

            UploadExecutor uploadExecutor = (UploadExecutor) this.executorMap.get(subFileName);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent);
            guessFileEncodeFromStream(dataInProcessVo, inputStream);
            try {
                JSONObject jsonObjectMap = uploadExecutor.execute(inputStream, optionsVo);
                dataInProcessVo.setCreateJson((JSONObject) jsonObjectMap.get("CREATE_TABLE"));
                dataInProcessVo.setInsertJson((JSONObject) jsonObjectMap.get("INSERT_TABLE"));
            } catch (Exception e) {
                throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
            }
        }
    }

    private void guessFileEncodeFromStream(DataInProcessVo dataInProcessVo, ByteArrayInputStream inputStream) {
        if (dataInProcessVo.getFileType().equals("csv")) {
            dataInProcessVo.getDataInOptionsVo()
                    .setCharset(this.guessEncodeUtils.guessEncodeForStream(inputStream, dataInProcessVo.getFileContext().length));
            inputStream = new ByteArrayInputStream(dataInProcessVo.getFileContext());
        }
    }

    private boolean checkFieldExecuteProcess(FieldExecutor checkExecutor, String dataValue, DataFieldCfgDto dto) {
        try {
            Method method = checkExecutor.getClass().getDeclaredMethod(dto.getCheckMethod(),
                    new Class[] { String.class, String.class });
            method.setAccessible(true);
            return ((Boolean) method.invoke(checkExecutor, new Object[] { dataValue, dto.getCheckRule() })).booleanValue();
        } catch (OpdException e) {
            this.logger.error(String.format("[OpdException Error]:%1$s, dataValue=[%2$s], checkMethod=[%3$s], checkRule=[%4$s]",
                    new Object[] { checkExecutor.getClass().getName(), dataValue, dto.getCheckMethod(), dto.getCheckRule() }),
                    e.getCause());

            return false;
        } catch (Exception e) {
            this.logger.error(String.format("[Exception Error]:%1$s, dataValue=[%2$s], checkMethod=[%3$s], checkRule=[%4$s]",
                    new Object[] { checkExecutor.getClass().getName(), dataValue, dto.getCheckMethod(), dto.getCheckRule() }),
                    e.getCause());
        }
        return false;
    }

    private Map<String, List<DataFieldCfgDto>> dataFieldCfgDtoGroupProcess(List<DataFieldCfgDto> dataFieldCfgDtoList) {
        Map<String, List<DataFieldCfgDto>> columnFiledCfgMap = new HashMap();
        for (DataFieldCfgDto dataFieldCfgPo : dataFieldCfgDtoList) {
            if (this.fieldTypeMap.get(dataFieldCfgPo.getFieldType()) != null) {
                List<DataFieldCfgDto> temp_List;
                if ((temp_List = (List) columnFiledCfgMap.get(dataFieldCfgPo.getFieldName())) == null) {
                    temp_List = new ArrayList();
                    columnFiledCfgMap.put(dataFieldCfgPo.getFieldName(), temp_List);
                }
                temp_List.add(dataFieldCfgPo);
            }
        }
        return columnFiledCfgMap;
    }

    private List<Future<Boolean>> multiThreadInsertData(File jsonFile, DataInOptionsVo optionsVo) {
        List<Future<Boolean>> futureList = new ArrayList();

        InputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(jsonFile);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            optionsVo.setExecCommentType(DataInLogPo.ExecCommentType.APPENDED);
            for (String line = null; (line = br.readLine()) != null;) {
                insertData(line, optionsVo);
            }
            return futureList;
        } catch (FileNotFoundException e) {
            this.logger.error(e.getMessage(), e);
        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    this.logger.error(e.getMessage(), e);
                }
            }
        }
        return futureList;
    }

    public class DeleteFileTask implements Callable<Boolean> {
        private File tmpDir;

        public DeleteFileTask(File tmpDir) {
            this.tmpDir = tmpDir;
        }

        @Override
        public Boolean call() {
            while (!FileUtils.deleteQuietly(this.tmpDir)) {
            }
            return Boolean.TRUE;
        }
    }

    private int totalRowsOfCsvFile(File csvFile, Charset charset) throws Exception {
        int totalRows = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), charset));
        while (reader.readLine() != null) {
            totalRows++;
        }
        reader.close();

        return totalRows;
    }
}
