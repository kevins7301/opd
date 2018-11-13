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
/*     */ @Table(name="od_log_data_out")
/*     */ public class DataOutLogPo
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
/*     */   @Column(name="data_cnt", nullable=false)
/*     */   private long dataCount;
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
/*     */   @Column(name="file_type", length=10, nullable=false)
/*     */   private FileType fileType;
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
/*     */   public long getDataCount()
/*     */   {
/* 128 */     return this.dataCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCount(long dataCount)
/*     */   {
/* 136 */     this.dataCount = dataCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 144 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 152 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 160 */     return this.endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 168 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getErrMsg()
/*     */   {
/* 176 */     return this.errMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setErrMsg(String errMsg)
/*     */   {
/* 184 */     this.errMsg = errMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ExecResult getExecResult()
/*     */   {
/* 192 */     return this.execResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExecResult(ExecResult execResult)
/*     */   {
/* 200 */     this.execResult = execResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 208 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 216 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FileType getFileType()
/*     */   {
/* 224 */     return this.fileType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFileType(FileType fileType)
/*     */   {
/* 232 */     this.fileType = fileType;
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
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum FileType
/*     */   {
/* 271 */     XML("XML"),  CSV("CSV"),  XLS("XLS"),  XLSX("XLSX"),  JSON("JSON");
/*     */     
/*     */     private String fileType;
/*     */     
/* 275 */     private FileType(String fileType) { this.fileType = fileType; }
/*     */     
/*     */     public String getFileType()
/*     */     {
/* 279 */       return this.fileType;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\DataOutLogPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */