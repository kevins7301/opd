/*     */ package com.iisi.opd.cfg.po;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.OneToOne;
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
/*     */ @Table(name="od_data_set_category_ver")
/*     */ public class DataSetCategoryVerPo
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
/*     */   @OneToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_set_ver_oid", referencedColumnName="oid")
/*     */   private DataSetVerPo dataSetVerPo;
/*     */   @OneToMany(mappedBy="dataSetCategoryVerPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private List<DataSetCategoryVerMetadataPo> dataSetCategoryVerMetadataPoList;
/*     */   
/*     */   public DataSetCategoryVerPo()
/*     */   {
/*  46 */     this.dataSetCategoryVerMetadataPoList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  54 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  62 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  70 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  78 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getIcon()
/*     */   {
/*  86 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIcon(byte[] icon)
/*     */   {
/*  94 */     this.icon = icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataSetVerPo getDataSetVerPoList()
/*     */   {
/* 102 */     return this.dataSetVerPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetVerPo(DataSetVerPo dataSetVerPo)
/*     */   {
/* 110 */     this.dataSetVerPo = dataSetVerPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataSetCategoryVerMetadataPo> getDataCategoryVerMetadataPoList()
/*     */   {
/* 118 */     return this.dataSetCategoryVerMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCategoryVerMetadataPoList(List<DataSetCategoryVerMetadataPo> dataCategoryMetadataPoList)
/*     */   {
/* 127 */     this.dataSetCategoryVerMetadataPoList = dataCategoryMetadataPoList;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataSetCategoryVerPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */