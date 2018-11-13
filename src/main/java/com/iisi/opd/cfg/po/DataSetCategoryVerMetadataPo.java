/*    */ package com.iisi.opd.cfg.po;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
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
/*    */ @Entity
/*    */ @Table(name="od_data_set_category_ver_metadata")
/*    */ public class DataSetCategoryVerMetadataPo
/*    */ {
/*    */   @Id
/*    */   @Column(name="oid", length=36)
/*    */   @GenericGenerator(name="generator", strategy="guid")
/*    */   @GeneratedValue(generator="generator")
/*    */   private String oid;
/*    */   @Column(name="metadata_key", nullable=false, columnDefinition="varchar(max)")
/*    */   private String metadataKey;
/*    */   @Column(name="metadata_value", nullable=false, columnDefinition="varchar(max)")
/*    */   private String metadataValue;
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="od_data_set_category_ver_oid", referencedColumnName="oid", nullable=false)
/*    */   private DataSetCategoryVerPo dataSetCategoryVerPo;
/*    */   
/*    */   public String getOid()
/*    */   {
/* 42 */     return this.oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setOid(String oid)
/*    */   {
/* 50 */     this.oid = oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getMetadataKey()
/*    */   {
/* 58 */     return this.metadataKey;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setMetadataKey(String metadataKey)
/*    */   {
/* 66 */     this.metadataKey = metadataKey;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getMetadataValue()
/*    */   {
/* 74 */     return this.metadataValue;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setMetadataValue(String metadataValue)
/*    */   {
/* 82 */     this.metadataValue = metadataValue;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public DataSetCategoryVerPo getDataSetCategoryVerPo()
/*    */   {
/* 90 */     return this.dataSetCategoryVerPo;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setDataSetCategoryVerPo(DataSetCategoryVerPo dataSetCategoryVerPo)
/*    */   {
/* 98 */     this.dataSetCategoryVerPo = dataSetCategoryVerPo;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataSetCategoryVerMetadataPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */