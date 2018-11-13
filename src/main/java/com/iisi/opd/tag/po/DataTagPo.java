/*     */ package com.iisi.opd.tag.po;
/*     */ 
/*     */ import com.iisi.opd.cfg.po.DataSetPo;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinTable;
/*     */ import javax.persistence.ManyToMany;
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
/*     */ @Table(name="od_data_tag")
/*     */ public class DataTagPo
/*     */   implements Serializable
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="name", length=100, nullable=false)
/*     */   private String name;
/*     */   @Column(name="remark", nullable=true, columnDefinition="nvarchar(max)")
/*     */   private String remark;
/*     */   @Column(name="access_count")
/*     */   private int accessCount;
/*     */   @ManyToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="od_data_set_tag", joinColumns={@javax.persistence.JoinColumn(name="od_data_tag_oid")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="od_data_set_oid")})
/*     */   private List<DataSetPo> dataSetPoList;
/*     */   
/*     */   public DataTagPo()
/*     */   {
/*  48 */     this.dataSetPoList = new ArrayList();
/*  49 */     this.accessCount = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  57 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  65 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  73 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  81 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataSetPo> getDataSetPoList()
/*     */   {
/*  89 */     return this.dataSetPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetPoList(List<DataSetPo> dataSetPoList)
/*     */   {
/*  97 */     this.dataSetPoList = dataSetPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 105 */     return this.remark;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 113 */     this.remark = remark;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAccessCount()
/*     */   {
/* 121 */     return this.accessCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAccessCount()
/*     */   {
/* 129 */     this.accessCount += 1;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\tag\po\DataTagPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */