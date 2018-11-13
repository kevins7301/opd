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
/*    */ public class DataServiceCountVo
/*    */ {
/*    */   private String dataSetOid;
/*    */   private Long serviceCount;
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
/*    */   public Long getServiceCount()
/*    */   {
/* 40 */     return this.serviceCount;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setServiceCount(Long serviceCount)
/*    */   {
/* 48 */     this.serviceCount = serviceCount;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Date getStartDateTime()
/*    */   {
/* 56 */     return this.startDateTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setStartDateTime(Date startDateTime)
/*    */   {
/* 64 */     this.startDateTime = startDateTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Date getEndDateTime()
/*    */   {
/* 72 */     return this.endDateTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setEndDateTime(Date endDateTime)
/*    */   {
/* 80 */     this.endDateTime = endDateTime;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 85 */     StringBuffer sb = new StringBuffer("DataServiceCountVo::");
/* 86 */     sb.append("dataSetOid:").append(this.dataSetOid);
/* 87 */     sb.append(",serviceCount:").append(this.serviceCount);
/* 88 */     sb.append(",startDateTime:").append(this.startDateTime);
/* 89 */     sb.append(",endDateTime:").append(this.endDateTime);
/* 90 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\vo\DataServiceCountVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */