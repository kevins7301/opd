/*     */ package com.iisi.opd.cfg.dto;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataCfgZipFileDto
/*     */ {
/*     */   private String oid;
/*     */   
/*     */ 
/*     */   private String zipFileName;
/*     */   
/*     */ 
/*     */   private String sourceType;
/*     */   
/*     */ 
/*     */   private long size;
/*     */   
/*     */   private String md5;
/*     */   
/*     */ 
/*     */   public DataCfgZipFileDto()
/*     */   {
/*  23 */     this.size = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  31 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  39 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getZipFileName()
/*     */   {
/*  47 */     return this.zipFileName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setZipFileName(String zipFileName)
/*     */   {
/*  55 */     this.zipFileName = zipFileName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSourceType()
/*     */   {
/*  63 */     return this.sourceType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSourceType(String sourceType)
/*     */   {
/*  71 */     this.sourceType = sourceType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSize()
/*     */   {
/*  79 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/*  87 */     this.size = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMd5()
/*     */   {
/*  95 */     return this.md5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMd5(String md5)
/*     */   {
/* 103 */     this.md5 = md5;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dto\DataCfgZipFileDto.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */