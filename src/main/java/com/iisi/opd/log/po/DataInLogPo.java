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
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_log_data_in")
/*     */ public class DataInLogPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="data_cfg_oid", length=36, nullable=false)
/*     */   private String dataCfgOid;
/*     */   @Column(name="data_set_oid", length=36)
/*     */   private String dataSetOid;
/*     */   @Column(name="data_set_unit_oid", length=36)
/*     */   private String dataSetUnitOid;
/*     */   @Column(name="transfer_opts", length=10, nullable=false)
/*     */   private TransferOptions transferOptions;
/*     */   @Column(name="src_data_cnt", nullable=false)
/*     */   private long sourceDataCount;
/*     */   @Column(name="insert_cnt", nullable=false)
/*     */   private long insertCount;
/*     */   @Column(name="update_cnt", nullable=false)
/*     */   private long updateCount;
/*     */   @Column(name="delete_cnt", nullable=false)
/*     */   private long deleteCount;
/*     */   @Column(name="check_result", nullable=false)
/*     */   private CheckResult checkResult;
/*     */   @Column(name="exec_result", nullable=false)
/*     */   private ExecResult execResult;
/*     */   @Column(name="exec_mode", nullable=false, columnDefinition="int default 1")
/*     */   private ExecCommentType execCommentType;
/*     */   @Column(name="start_time", nullable=false)
/*     */   private Date startTime;
/*     */   @Column(name="end_time", nullable=false)
/*     */   private Date endTime;
/*     */   @Column(name="err_msg", columnDefinition="varchar(MAX)")
/*     */   private String errMsg;
/*     */   @Column(name="log_time", nullable=false)
/*     */   private Date logTime;
/*     */   @Column(name="check_result_oid", length=36)
/*     */   private String checkResultOid;
/*     */   
/*     */   public DataInLogPo()
/*     */   {
/*  75 */     this.execCommentType = ExecCommentType.STANDARD;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  83 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  91 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataCfgOid()
/*     */   {
/*  99 */     return this.dataCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgOid(String dadaCfgOid)
/*     */   {
/* 107 */     this.dataCfgOid = dadaCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetOid()
/*     */   {
/* 115 */     return this.dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetOid(String dataSetOid)
/*     */   {
/* 123 */     this.dataSetOid = dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TransferOptions getTransferOptions()
/*     */   {
/* 132 */     return this.transferOptions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTransferOptions(TransferOptions transferOptions)
/*     */   {
/* 140 */     this.transferOptions = transferOptions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getInsertCount()
/*     */   {
/* 148 */     return this.insertCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInsertCount(long insertCount)
/*     */   {
/* 156 */     this.insertCount = insertCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getUpdateCount()
/*     */   {
/* 164 */     return this.updateCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUpdateCount(long updateCount)
/*     */   {
/* 172 */     this.updateCount = updateCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDeleteCount()
/*     */   {
/* 180 */     return this.deleteCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDeleteCount(long deleteCount)
/*     */   {
/* 188 */     this.deleteCount = deleteCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CheckResult getCheckResult()
/*     */   {
/* 196 */     return this.checkResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCheckResult(CheckResult checkResult)
/*     */   {
/* 204 */     this.checkResult = checkResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ExecResult getExecResult()
/*     */   {
/* 212 */     return this.execResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExecResult(ExecResult execResult)
/*     */   {
/* 220 */     this.execResult = execResult;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 228 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 236 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 244 */     return this.endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 252 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getErrMsg()
/*     */   {
/* 260 */     return this.errMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setErrMsg(String errMsg)
/*     */   {
/* 268 */     this.errMsg = errMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 276 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 284 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCheckResultOid()
/*     */   {
/* 292 */     return this.checkResultOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCheckResultOid(String checkResultOid)
/*     */   {
/* 300 */     this.checkResultOid = checkResultOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSourceDataCount()
/*     */   {
/* 308 */     return this.sourceDataCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSourceDataCount(long sourceDataCount)
/*     */   {
/* 316 */     this.sourceDataCount = sourceDataCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetUnitOid()
/*     */   {
/* 324 */     return this.dataSetUnitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetUnitOid(String dataSetUnitOid)
/*     */   {
/* 332 */     this.dataSetUnitOid = dataSetUnitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ExecCommentType getExecCommentMode()
/*     */   {
/* 340 */     return this.execCommentType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExecCommentMode(ExecCommentType execCommentType)
/*     */   {
/* 348 */     this.execCommentType = execCommentType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum ExecResult
/*     */   {
/* 355 */     SUCCESS(0),  FAIL_CHECKFAIL(-1),  FAIL_OTHERS(-2),  FAIL_STRUCTURE_ERROR(-3);
/*     */     
/*     */     private int execResult;
/*     */     
/* 359 */     private ExecResult(int execResult) { this.execResult = execResult; }
/*     */     
/*     */     public int getExecResult()
/*     */     {
/* 363 */       return this.execResult;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static enum CheckResult
/*     */   {
/* 370 */     SUCCESS(0),  SKIP_CHECK(1),  CHECK_FAIL(-1);
/*     */     
/*     */     private int checkResult;
/*     */     
/* 374 */     private CheckResult(int checkResult) { this.checkResult = checkResult; }
/*     */     
/*     */     public int getCheckResult()
/*     */     {
/* 378 */       return this.checkResult;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum TransferOptions
/*     */   {
/* 386 */     SINGLE("S"),  BATCH("B");
/*     */     
/*     */     private String transferOptions;
/*     */     
/* 390 */     private TransferOptions(String transferOptions) { this.transferOptions = transferOptions; }
/*     */     
/*     */     public String getTransferOptions()
/*     */     {
/* 394 */       return this.transferOptions;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum ExecCommentType
/*     */   {
/* 402 */     STANDARD,  APPENDED,  FINISHED;
/*     */     
/*     */     private ExecCommentType() {}
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\DataInLogPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */