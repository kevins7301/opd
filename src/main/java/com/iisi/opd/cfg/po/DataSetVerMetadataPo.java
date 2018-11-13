/*     */ package com.iisi.opd.cfg.po;
/*     */ 
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
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
/*     */ @Entity
/*     */ @Table(name="od_data_set_ver_metadata")
/*     */ public class DataSetVerMetadataPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="metadata_key", nullable=false, length=100)
/*     */   private String metadataKey;
/*     */   @Column(name="metadata_value", nullable=false, columnDefinition="varchar(max)")
/*     */   private String metadataValue;
/*     */   @Column(name="is_common", nullable=false)
/*     */   private Boolean isCommon;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_set_ver_oid", referencedColumnName="oid", nullable=false)
/*     */   private DataSetVerPo dataSetVerPo;
/*     */   
/*     */   public DataSetVerMetadataPo()
/*     */   {
/*  41 */     this.isCommon = Boolean.FALSE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
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
/*     */   public String getMetadataKey()
/*     */   {
/*  65 */     return this.metadataKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMetadataKey(String metadataKey)
/*     */   {
/*  73 */     this.metadataKey = metadataKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMetadataValue()
/*     */   {
/*  81 */     return this.metadataValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMetadataValue(String metadataValue)
/*     */   {
/*  89 */     this.metadataValue = metadataValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isCommon()
/*     */   {
/*  97 */     return this.isCommon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCommon(Boolean isCommon)
/*     */   {
/* 105 */     this.isCommon = isCommon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataSetVerPo getDataSetVerPo()
/*     */   {
/* 113 */     return this.dataSetVerPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetVerPo(DataSetVerPo dataSetVerPo)
/*     */   {
/* 121 */     this.dataSetVerPo = dataSetVerPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataSetVerMetadataPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */