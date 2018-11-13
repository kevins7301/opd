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
/*     */ @Table(name="od_log_cfg_tracking")
/*     */ public class CfgTrackingLogPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid")
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="table_name", length=100, nullable=false)
/*     */   private String tableName;
/*     */   @Column(name="user_id", length=100, nullable=false)
/*     */   private String userId;
/*     */   @Column(name="mod_op", length=10, nullable=false)
/*     */   private ModificationOpration modificationOperation;
/*     */   @Column(name="mod_content", nullable=false)
/*     */   private String modificationContent;
/*     */   @Column(name="log_time", nullable=false)
/*     */   private Date logTime;
/*     */   
/*     */   public String getOid()
/*     */   {
/*  47 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  55 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTableName()
/*     */   {
/*  63 */     return this.tableName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTableName(String tableName)
/*     */   {
/*  71 */     this.tableName = tableName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUserId()
/*     */   {
/*  79 */     return this.userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUserId(String userId)
/*     */   {
/*  87 */     this.userId = userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ModificationOpration getModificationOperation()
/*     */   {
/*  95 */     return this.modificationOperation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModificationOperation(ModificationOpration modificationOperation)
/*     */   {
/* 103 */     this.modificationOperation = modificationOperation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getModificationContent()
/*     */   {
/* 111 */     return this.modificationContent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModificationContent(String modificationContent)
/*     */   {
/* 119 */     this.modificationContent = modificationContent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 127 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 135 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum ModificationOpration
/*     */   {
/* 142 */     INSERT("I"),  UPDATE("U"),  DELETE("D");
/*     */     
/*     */     private String modificationOpration;
/*     */     
/* 146 */     private ModificationOpration(String modificationOpration) { this.modificationOpration = modificationOpration; }
/*     */     
/*     */     public String getModificationOpration()
/*     */     {
/* 150 */       return this.modificationOpration;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\CfgTrackingLogPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */