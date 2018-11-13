/*     */ package com.iisi.opd.log.po;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_log_data_file")
/*     */ public class DataFileLogPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid")
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="data_cfg_oid", length=36, nullable=false)
/*     */   private String dataCfgOid;
/*     */   @Column(name="data_set_oid", length=36, nullable=false)
/*     */   private String dataSetOid;
/*     */   @Column(name="data_set_unit_oid", length=36, nullable=false)
/*     */   private String dataSetUnitOid;
/*     */   @Column(name="client_ip", length=23, nullable=false)
/*     */   private String clientIp;
/*     */   @Column(name="file_oid", length=36, nullable=false)
/*     */   private String fileOid;
/*     */   @Column(name="file_size", nullable=false)
/*     */   private long fileSize;
/*     */   @Column(name="start_time", nullable=false)
/*     */   private Date startTime;
/*     */   @Column(name="end_time", nullable=false)
/*     */   private Date endTime;
/*     */   @Column(name="exec_result", nullable=false)
/*     */   private ExecResult execResult;
/*     */   @Column(name="err_msg", nullable=false)
/*     */   private String errMsg;
/*     */   @Column(name="log_time", nullable=false)
/*     */   private Date logTime;
/*     */   
/*     */   public String getOid()
/*     */   {
/*  64 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  72 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataCfgOid()
/*     */   {
/*  80 */     return this.dataCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgOid(String dadaCfgOid)
/*     */   {
/*  88 */     this.dataCfgOid = dadaCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetOid()
/*     */   {
/*  96 */     return this.dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetOid(String dataSetOid)
/*     */   {
/* 104 */     this.dataSetOid = dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getClientIp()
/*     */   {
/* 112 */     return this.clientIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setClientIp(String clientIp)
/*     */   {
/* 120 */     this.clientIp = clientIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 128 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 136 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 144 */     return this.endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 152 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getErrMsg()
/*     */   {
/* 160 */     return this.errMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setErrMsg(String errMsg)
/*     */   {
/* 168 */     this.errMsg = errMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ExecResult getExecResult()
/*     */   {
/* 176 */     return this.execResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExecResult(ExecResult execResult)
/*     */   {
/* 184 */     this.execResult = execResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 192 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 200 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFileOid()
/*     */   {
/* 208 */     return this.fileOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFileOid(String fileOid)
/*     */   {
/* 216 */     this.fileOid = fileOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getFileSize()
/*     */   {
/* 224 */     return this.fileSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFileSize(long fileSize)
/*     */   {
/* 232 */     this.fileSize = fileSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetUnitOid()
/*     */   {
/* 240 */     return this.dataSetUnitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetUnitOid(String dataSetUnitOid)
/*     */   {
/* 248 */     this.dataSetUnitOid = dataSetUnitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum ExecResult
/*     */   {
/* 255 */     SUCCESS(0),  FAIL(1);
/*     */     
/*     */     private int execResult;
/*     */     
/* 259 */     private ExecResult(int execResult) { this.execResult = execResult; }
/*     */     
/*     */     public int getExecResult()
/*     */     {
/* 263 */       return this.execResult;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\DataFileLogPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */