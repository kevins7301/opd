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
/*     */ @Entity
/*     */ @Table(name="od_log_access_reject")
/*     */ public class DataAccessRejectLogPo
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
/*     */   @Column(name="page_name", length=100, nullable=false)
/*     */   private String pageName;
/*     */   @Column(name="client_ip", length=23, nullable=false)
/*     */   private String clientIp;
/*     */   @Column(name="log_time", nullable=false)
/*     */   private Date logTime;
/*     */   
/*     */   public String getOid()
/*     */   {
/*  48 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  56 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataCfgOid()
/*     */   {
/*  64 */     return this.dataCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgOid(String dadaCfgOid)
/*     */   {
/*  72 */     this.dataCfgOid = dadaCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetOid()
/*     */   {
/*  80 */     return this.dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetOid(String dataSetOid)
/*     */   {
/*  88 */     this.dataSetOid = dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPageName()
/*     */   {
/*  96 */     return this.pageName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPageName(String pageName)
/*     */   {
/* 104 */     this.pageName = pageName;
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
/*     */   public Date getLogTime()
/*     */   {
/* 128 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 136 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetUnitOid()
/*     */   {
/* 144 */     return this.dataSetUnitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetUnitOid(String dataSetUnitOid)
/*     */   {
/* 152 */     this.dataSetUnitOid = dataSetUnitOid;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\DataAccessRejectLogPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */