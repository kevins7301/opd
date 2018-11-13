/*     */ package com.iisi.opd.report.vo;
/*     */ 
/*     */ import java.sql.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataFileDownloadCountVo
/*     */ {
/*     */   private String dataSetOid;
/*     */   private Long downloadCounts;
/*     */   private Long downloadSize;
/*     */   private Date startDateTime;
/*     */   private Date endDateTime;
/*     */   
/*     */   public DataFileDownloadCountVo()
/*     */   {
/*  23 */     this.downloadCounts = new Long(0L);
/*  24 */     this.downloadSize = new Long(0L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetOid()
/*     */   {
/*  32 */     return this.dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetOid(String dataSetOid)
/*     */   {
/*  40 */     this.dataSetOid = dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Long getDownloadCounts()
/*     */   {
/*  48 */     return this.downloadCounts;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDownloadCounts(Long downloadCounts)
/*     */   {
/*  56 */     this.downloadCounts = downloadCounts;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartDateTime()
/*     */   {
/*  64 */     return this.startDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartDateTime(Date startDateTime)
/*     */   {
/*  72 */     this.startDateTime = startDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndDateTime()
/*     */   {
/*  80 */     return this.endDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndDateTime(Date endDateTime)
/*     */   {
/*  88 */     this.endDateTime = endDateTime;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  93 */     StringBuffer sb = new StringBuffer("DataFileDownloadCountVo::");
/*  94 */     sb.append("dataSetOid:").append(this.dataSetOid);
/*  95 */     sb.append(",downloadCounts:<");
/*  96 */     sb.append("key:FILE,value:").append(this.downloadCounts);
/*  97 */     sb.append(",size:").append(this.downloadSize);
/*  98 */     sb.append(">,startDateTime:").append(this.startDateTime);
/*  99 */     sb.append(",endDateTime:").append(this.endDateTime);
/* 100 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Long getDownloadSize()
/*     */   {
/* 108 */     return this.downloadSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDownloadSize(Long downloadSize)
/*     */   {
/* 116 */     this.downloadSize = downloadSize;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\vo\DataFileDownloadCountVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */