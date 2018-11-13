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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_data_field_cfg_ver")
/*     */ public class DataFieldCfgVerPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid")
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="field_name", length=50, nullable=false)
/*     */   private String fieldName;
/*     */   @Column(name="disp_name", length=100)
/*     */   private String dispName;
/*     */   @Column(name="field_order", nullable=false)
/*     */   private long fieldOrder;
/*     */   @Column(name="field_type", length=50)
/*     */   private String fieldType;
/*     */   @Column(name="check_method", length=100)
/*     */   private String checkMethod;
/*     */   @Column(name="check_rule", length=100)
/*     */   private String checkRule;
/*     */   @Column(name="is_public", nullable=false, columnDefinition="tinyint default '1'")
/*     */   private Boolean isPublic;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_cfg_ver_oid", referencedColumnName="oid", nullable=false)
/*     */   private DataCfgVerPo dataCfgVerPo;
/*     */   
/*     */   public DataFieldCfgVerPo()
/*     */   {
/*  53 */     this.isPublic = Boolean.TRUE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  61 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  69 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFieldName()
/*     */   {
/*  77 */     return this.fieldName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFieldName(String fieldName)
/*     */   {
/*  85 */     this.fieldName = fieldName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDispName()
/*     */   {
/*  93 */     return this.dispName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDispName(String dispName)
/*     */   {
/* 101 */     this.dispName = dispName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getFieldOrder()
/*     */   {
/* 109 */     return this.fieldOrder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFieldOrder(long fieldOrder)
/*     */   {
/* 117 */     this.fieldOrder = fieldOrder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFieldType()
/*     */   {
/* 127 */     return this.fieldType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFieldType(String fieldType)
/*     */   {
/* 137 */     this.fieldType = fieldType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCheckRule()
/*     */   {
/* 145 */     return this.checkRule;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCheckRule(String checkRule)
/*     */   {
/* 153 */     this.checkRule = checkRule;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgVerPo getDataCfgVerPo()
/*     */   {
/* 161 */     return this.dataCfgVerPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgVerPo(DataCfgVerPo dataCfgVerPo)
/*     */   {
/* 169 */     this.dataCfgVerPo = dataCfgVerPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCheckMethod()
/*     */   {
/* 177 */     return this.checkMethod;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCheckMethod(String checkMethod)
/*     */   {
/* 186 */     this.checkMethod = checkMethod;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isPublic()
/*     */   {
/* 194 */     return this.isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublic(Boolean isPublic)
/*     */   {
/* 202 */     this.isPublic = isPublic;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataFieldCfgVerPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */