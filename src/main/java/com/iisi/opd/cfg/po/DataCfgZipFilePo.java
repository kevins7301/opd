/*     */ package com.iisi.opd.cfg.po;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.sql.Blob;
/*     */ import java.sql.SQLException;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.apache.commons.io.IOUtils;
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
/*     */ @Entity
/*     */ @Table(name="od_data_cfg_zip_file")
/*     */ public class DataCfgZipFilePo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="file_name", nullable=false)
/*     */   @Lob
/*     */   private String zipFileName;
/*     */   @Column(name="source_type", length=36, nullable=false)
/*     */   private String sourceType;
/*     */   @Column(name="size", nullable=false)
/*     */   private long size;
/*     */   @Column(name="content_file")
/*     */   @Lob
/*     */   private byte[] contentFile;
/*     */   @Column(name="md5", length=36, nullable=false)
/*     */   @Lob
/*     */   private String md5;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_cfg_oid", referencedColumnName="oid", nullable=false)
/*     */   private DataCfgPo dataCfgPo;
/*     */   
/*     */   public DataCfgZipFilePo()
/*     */   {
/*  65 */     this.size = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  73 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  81 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getZipFileName()
/*     */   {
/*  89 */     return this.zipFileName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setZipFileName(String zipFileName)
/*     */   {
/*  97 */     this.zipFileName = zipFileName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSourceType()
/*     */   {
/* 105 */     return this.sourceType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSourceType(String sourceType)
/*     */   {
/* 113 */     this.sourceType = sourceType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSize()
/*     */   {
/* 121 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/* 129 */     this.size = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getContentFile()
/*     */   {
/* 137 */     return this.contentFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setContentFile(Blob contentFile)
/*     */     throws IOException, SQLException
/*     */   {
/* 147 */     this.contentFile = IOUtils.toByteArray(contentFile.getBinaryStream(), contentFile.length());
/* 148 */     this.size = this.contentFile.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setContentFile(byte[] contentFile)
/*     */   {
/* 156 */     this.contentFile = contentFile;
/* 157 */     this.size = this.contentFile.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMd5()
/*     */   {
/* 165 */     return this.md5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMd5(String md5)
/*     */   {
/* 173 */     this.md5 = md5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgPo getDataCfgPo()
/*     */   {
/* 181 */     return this.dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgPo(DataCfgPo dataCfgPo)
/*     */   {
/* 189 */     this.dataCfgPo = dataCfgPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataCfgZipFilePo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */