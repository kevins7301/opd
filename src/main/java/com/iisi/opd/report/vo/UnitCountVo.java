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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnitCountVo
/*     */ {
/*     */   private String unitOid;
/*     */   private String dataSetOid;
/*     */   private long dataOutCount;
/*     */   private long serviceCount;
/*     */   private long viewCount;
/*     */   private long downloadCount;
/*     */   private Date startDateTime;
/*     */   private Date endDateTime;
/*     */   
/*     */   public String getUnitOid()
/*     */   {
/*  32 */     return this.unitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUnitOid(String unitOid)
/*     */   {
/*  40 */     this.unitOid = unitOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getServiceCount()
/*     */   {
/*  48 */     return this.serviceCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setServiceCount(long serviceCount)
/*     */   {
/*  56 */     this.serviceCount = serviceCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getViewCount()
/*     */   {
/*  64 */     return this.viewCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setViewCount(long viewCount)
/*     */   {
/*  72 */     this.viewCount = viewCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addViewCount(long viewCount)
/*     */   {
/*  80 */     this.viewCount += viewCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDownloadCount()
/*     */   {
/*  88 */     return this.downloadCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDownloadCount(long downloadCount)
/*     */   {
/*  96 */     this.downloadCount = downloadCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartDateTime()
/*     */   {
/* 104 */     return this.startDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartDateTime(Date startDateTime)
/*     */   {
/* 112 */     this.startDateTime = startDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndDateTime()
/*     */   {
/* 120 */     return this.endDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndDateTime(Date endDateTime)
/*     */   {
/* 128 */     this.endDateTime = endDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetOid()
/*     */   {
/* 136 */     return this.dataSetOid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetOid(String dataSetOid)
/*     */   {
/* 144 */     this.dataSetOid = dataSetOid;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 149 */     StringBuffer sb = new StringBuffer("UnitCountVo::");
/* 150 */     sb.append("unitOid:").append(this.unitOid);
/* 151 */     sb.append(",dataSetOid:").append(this.dataSetOid);
/* 152 */     sb.append(",dataOutCount:").append(this.dataOutCount);
/* 153 */     sb.append(",serviceCount:").append(this.serviceCount);
/* 154 */     sb.append(",viewCount:").append(this.viewCount);
/* 155 */     sb.append(",downloadCount:").append(this.downloadCount);
/* 156 */     sb.append(",startDateTime:").append(this.startDateTime);
/* 157 */     sb.append(",endDateTime:").append(this.endDateTime);
/* 158 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDataOutCount()
/*     */   {
/* 166 */     return this.dataOutCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataOutCount(long dataOutCount)
/*     */   {
/* 174 */     this.dataOutCount = dataOutCount;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\vo\UnitCountVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */