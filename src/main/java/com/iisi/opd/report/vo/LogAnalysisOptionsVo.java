/*     */ package com.iisi.opd.report.vo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogAnalysisOptionsVo
/*     */ {
/*     */   private boolean accessCount;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean dataFileCount;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean dataOutCount;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean dataSrvCount;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LogAnalysisOptionsVo()
/*     */   {
/*  33 */     this.accessCount = false;
/*  34 */     this.dataFileCount = false;
/*  35 */     this.dataOutCount = false;
/*  36 */     this.dataSrvCount = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LogAnalysisOptionsVo(boolean accessCount, boolean dataFileCount, boolean dataOutCount, boolean dataSrvCount)
/*     */   {
/*  47 */     this.accessCount = accessCount;
/*  48 */     this.dataFileCount = dataFileCount;
/*  49 */     this.dataOutCount = dataOutCount;
/*  50 */     this.dataSrvCount = dataSrvCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAccessCount()
/*     */   {
/*  58 */     return this.accessCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAccessCount(boolean accessCount)
/*     */   {
/*  66 */     this.accessCount = accessCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDataFileCount()
/*     */   {
/*  74 */     return this.dataFileCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataFileCount(boolean dataFileCount)
/*     */   {
/*  82 */     this.dataFileCount = dataFileCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDataOutCount()
/*     */   {
/*  90 */     return this.dataOutCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataOutCount(boolean dataOutCount)
/*     */   {
/*  98 */     this.dataOutCount = dataOutCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDataSrvCount()
/*     */   {
/* 106 */     return this.dataSrvCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSrvCount(boolean dataSrvCount)
/*     */   {
/* 114 */     this.dataSrvCount = dataSrvCount;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\vo\LogAnalysisOptionsVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */