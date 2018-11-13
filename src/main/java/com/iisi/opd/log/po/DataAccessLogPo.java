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
/*     */ @Entity
/*     */ @Table(name="od_log_access")
/*     */ public class DataAccessLogPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid")
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="data_cfg_oid", length=36, nullable=true)
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
/*  49 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  57 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataCfgOid()
/*     */   {
/*  65 */     return this.dataCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgOid(String dadaCfgOid)
/*     */   {
/*  73 */     this.dataCfgOid = dadaCfgOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetOid()
/*     */   {
/*  81 */     return this.dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetOid(String dataSetOid)
/*     */   {
/*  89 */     this.dataSetOid = dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPageName()
/*     */   {
/*  97 */     return this.pageName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPageName(String pageName)
/*     */   {
/* 105 */     this.pageName = pageName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getClientIp()
/*     */   {
/* 113 */     return this.clientIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setClientIp(String clientIp)
/*     */   {
/* 121 */     this.clientIp = clientIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 129 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 137 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetUnitOid()
/*     */   {
/* 145 */     return this.dataSetUnitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetUnitOid(String dataSetUnitOid)
/*     */   {
/* 153 */     this.dataSetUnitOid = dataSetUnitOid;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\DataAccessLogPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */