package com.iisi.opd.data.in.vo;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import com.iisi.opd.log.po.DataInLogPo;

public class DataInOptionsVo {
    private boolean coverOldData;
    private boolean coverOldTitle;
    private boolean mutilLangTitle;
    private boolean matchFieldColumn;
    private int permitCheckFaultSize;
    private boolean isRegularize;
    private Charset charset;
    private DataInLogPo.ExecCommentType execCommentType;
    private int recordBatchSize;
    private boolean execParsingFile;
    private int maxRowsOfCsvFile;
    private boolean createInsertJsonFile;

    public DataInOptionsVo() {
        this.coverOldData = true;
        this.coverOldTitle = true;
        this.matchFieldColumn = false;
        this.mutilLangTitle = false;
        this.permitCheckFaultSize = 1000;
        this.isRegularize = false;
        this.execCommentType = DataInLogPo.ExecCommentType.STANDARD;
        setRecordBatchSize(1000);
        this.execParsingFile = true;

        this.maxRowsOfCsvFile = 500000;
        try {
            Properties properties = new Properties();
            String propertiesPath = getClass().getResource("/").toURI().resolve("../resources/properties/opd.properties")
                    .getPath();
            FileInputStream inStream = new FileInputStream(propertiesPath);
            properties.load(inStream);
            String temp = properties.getProperty("maxRowsOfCsvFile");
            if (temp != null) {
                this.maxRowsOfCsvFile = Integer.parseInt(temp);
            }
        } catch (Exception e) {
        }
        this.createInsertJsonFile = true;
    }

    public boolean isCoverOldData() {
        return this.coverOldData;
    }

    public void setCoverOldData(Boolean coverOldData) {
        this.coverOldData = coverOldData.booleanValue();
    }

    public boolean isCoverOldTitle() {
        return this.coverOldTitle;
    }

    public void setCoverOldTitle(boolean coverOldTitle) {
        this.coverOldTitle = coverOldTitle;
    }

    public int getPermitCheckFaultSize() {
        return this.permitCheckFaultSize;
    }

    public void setPermitCheckFaultSize(int permitCheckFaultSize) {
        this.permitCheckFaultSize = permitCheckFaultSize;
    }

    public boolean isMutilLangTitle() {
        return this.mutilLangTitle;
    }

    public void setMutilLangTitle(boolean mutilLangTitle) {
        this.mutilLangTitle = mutilLangTitle;
    }

    @Override
    public String toString() {
        return String.format(
                "\"coverOldData\":\"%1$s\", \"coverOldTitle\":\"%2$s\", \"permitCheckFaultSize\":\"%3$s\", \"isMutilLangTitle\":\"%4$b\"",
                new Object[] { Boolean.valueOf(this.coverOldData), Boolean.valueOf(this.coverOldTitle),
                        Integer.valueOf(this.permitCheckFaultSize), Boolean.valueOf(this.mutilLangTitle) });
    }

    public boolean isMatchFieldColumn() {
        return this.matchFieldColumn;
    }

    public void setMatchFieldColumn(boolean matchFieldColumn) {
        this.matchFieldColumn = matchFieldColumn;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public boolean isRegularize() {
        return this.isRegularize;
    }

    public void setRegularize(boolean isRegularize) {
        this.isRegularize = isRegularize;
    }

    public DataInLogPo.ExecCommentType getExecCommentType() {
        return this.execCommentType;
    }

    public void setExecCommentType(DataInLogPo.ExecCommentType execCommentType) {
        this.execCommentType = execCommentType;
    }

    public int getRecordBatchSize() {
        return this.recordBatchSize;
    }

    public void setRecordBatchSize(int recordBatchSize) {
        this.recordBatchSize = recordBatchSize;
    }

    public boolean isExecParsingFile() {
        return this.execParsingFile;
    }

    public void setExecParsingFile(boolean execParsingFile) {
        this.execParsingFile = execParsingFile;
    }

    public int getMaxRowsOfCsvFile() {
        return this.maxRowsOfCsvFile;
    }

    public void setMaxRowsOfCsvFile(int maxRowsOfCsvFile) {
        this.maxRowsOfCsvFile = maxRowsOfCsvFile;
    }

    public boolean isCreateInsertJsonFile() {
        return this.createInsertJsonFile;
    }

    public void setCreateInsertJsonFile(boolean createInsertJsonFile) {
        this.createInsertJsonFile = createInsertJsonFile;
    }
}