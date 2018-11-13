/*    */ package com.iisi.opd.report.vo;
/*    */ 
/*    */ import java.sql.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataSetViewVo
/*    */ {
/*    */   private String dataSetOid;
/*    */   private long viewCount;
/*    */   private Date startDateTime;
/*    */   private Date endDateTime;
/*    */   
/*    */   public String getDataSetOid()
/*    */   {
/* 24 */     return this.dataSetOid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setDataSetOid(String dataSetOid)
/*    */   {
/* 32 */     this.dataSetOid = dataSetOid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public long getViewCount()
/*    */   {
/* 40 */     return this.viewCount;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setViewCount(long viewCount)
/*    */   {
/* 48 */     this.viewCount = viewCount;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addViewCount(long viewCount)
/*    */   {
/* 56 */     this.viewCount += viewCount;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Date getStartDateTime()
/*    */   {
/* 64 */     return this.startDateTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setStartDateTime(Date startDateTime)
/*    */   {
/* 72 */     this.startDateTime = startDateTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Date getEndDateTime()
/*    */   {
/* 80 */     return this.endDateTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setEndDateTime(Date endDateTime)
/*    */   {
/* 88 */     this.endDateTime = endDateTime;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 93 */     StringBuffer sb = new StringBuffer("DataSetViewVo::");
/* 94 */     sb.append("dataSetOid:").append(this.dataSetOid);
/* 95 */     sb.append(",viewCount:").append(this.viewCount);
/* 96 */     sb.append(",startDateTime:").append(this.startDateTime);
/* 97 */     sb.append(",endDateTime:").append(this.endDateTime);
/* 98 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\vo\DataSetViewVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */