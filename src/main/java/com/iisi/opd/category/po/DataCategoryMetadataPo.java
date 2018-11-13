/*     */ package com.iisi.opd.category.po;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_data_category_metadata")
/*     */ public class DataCategoryMetadataPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="metadata_key", nullable=false, columnDefinition="varchar(max)")
/*     */   private String metadataKey;
/*     */   @Column(name="metadata_value", nullable=false, columnDefinition="varchar(max)")
/*     */   private String metadataValue;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_category_oid", referencedColumnName="oid", nullable=false)
/*     */   private DataCategoryPo dataCategoryPo;
/*     */   
/*     */   public String getOid()
/*     */   {
/*  45 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  53 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMetadataKey()
/*     */   {
/*  61 */     return this.metadataKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMetadataKey(String metadataKey)
/*     */   {
/*  69 */     this.metadataKey = metadataKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMetadataValue()
/*     */   {
/*  77 */     return this.metadataValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMetadataValue(String metadataValue)
/*     */   {
/*  85 */     this.metadataValue = metadataValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCategoryPo getDataCategoryPo()
/*     */   {
/*  93 */     return this.dataCategoryPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCategoryPo(DataCategoryPo dataCategoryPo)
/*     */   {
/* 101 */     this.dataCategoryPo = dataCategoryPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\category\po\DataCategoryMetadataPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */