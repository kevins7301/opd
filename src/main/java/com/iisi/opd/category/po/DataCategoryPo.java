/*     */ package com.iisi.opd.category.po;
/*     */ 
/*     */ import com.iisi.opd.cfg.po.DataSetPo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.OneToMany;
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
/*     */ @Entity
/*     */ @Table(name="od_data_category")
/*     */ public class DataCategoryPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="name", length=100, nullable=false)
/*     */   private String name;
/*     */   @Column(name="icon")
/*     */   @Lob
/*     */   private byte[] icon;
/*     */   @OneToMany(mappedBy="dataCategoryPo")
/*     */   private List<DataSetPo> dataSetPoList;
/*     */   @OneToMany(mappedBy="dataCategoryPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private List<DataCategoryMetadataPo> dataCategoryMetadataPoList;
/*     */   
/*     */   public DataCategoryPo()
/*     */   {
/*  46 */     this.dataSetPoList = new ArrayList();
/*  47 */     this.dataCategoryMetadataPoList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  55 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  63 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  71 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  79 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getIcon()
/*     */   {
/*  87 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIcon(byte[] icon)
/*     */   {
/*  95 */     this.icon = icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataSetPo> getDataSetPoList()
/*     */   {
/* 103 */     return this.dataSetPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetPoList(List<DataSetPo> dataSetPoList)
/*     */   {
/* 111 */     this.dataSetPoList = dataSetPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataCategoryMetadataPo> getDataCategoryMetadataPoList()
/*     */   {
/* 119 */     return this.dataCategoryMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCategoryMetadataPoList(List<DataCategoryMetadataPo> dataCategoryMetadataPoList)
/*     */   {
/* 128 */     this.dataCategoryMetadataPoList = dataCategoryMetadataPoList;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\category\po\DataCategoryPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */