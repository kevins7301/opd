package com.iisi.opd.report.vo;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.iisi.opd.log.po.DataOutLogPo;

public class DataOutCountVo {
    private String dataSetOid;
    private Map<String, Long> downloadCounts;
    private Date startDateTime;
    private Date endDateTime;

    public DataOutCountVo() {
        this.downloadCounts = new HashMap();
        this.downloadCounts.put(DataOutLogPo.FileType.CSV.getFileType(), Long.valueOf(0L));
        this.downloadCounts.put(DataOutLogPo.FileType.JSON.getFileType(), Long.valueOf(0L));
        this.downloadCounts.put(DataOutLogPo.FileType.XML.getFileType(), Long.valueOf(0L));
        this.downloadCounts.put(DataOutLogPo.FileType.XLS.getFileType(), Long.valueOf(0L));
        this.downloadCounts.put(DataOutLogPo.FileType.XLSX.getFileType(), Long.valueOf(0L));
    }

    public String getDataSetOid() {
        return this.dataSetOid;
    }

    public void setDataSetOid(String dataSetOid) {
        this.dataSetOid = dataSetOid;
    }

    public Map<String, Long> getDownloadCounts() {
        return this.downloadCounts;
    }

    public void setDownloadCounts(Map<String, Long> downloadCounts) {
        this.downloadCounts = downloadCounts;
    }

    public Date getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("DataFileDownloadCountVo::");
        sb.append("dataSetOid:").append(this.dataSetOid);
        sb.append(",downloadCounts:<");
        Set<String> keys = this.downloadCounts.keySet();
        for (String key : keys) {
            sb.append("key:").append(key);
            sb.append(",value:").append(this.downloadCounts.get(key)).append(",");
        }
        sb.append(">,startDateTime:").append(this.startDateTime);
        sb.append(",endDateTime:").append(this.endDateTime);
        return sb.toString();
    }
}