/*     */ package com.iisi.opd.cfg.po;
/*     */ 
/*     */ import com.iisi.opd.auth.po.UnitPo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.ManyToOne;
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
/*     */ @Table(name="od_data_set_ver")
/*     */ public class DataSetVerPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="name", length=100, nullable=false)
/*     */   private String name;
/*     */   @Column(name="description")
/*     */   @Lob
/*     */   private String description;
/*     */   @Column(name="is_active", nullable=false)
/*     */   private Boolean isActive;
/*     */   @Column(name="is_enable", nullable=false)
/*     */   private Boolean isEnable;
/*     */   @Column(name="is_public", nullable=false)
/*     */   private Boolean isPublic;
/*     */   @Column(name="no_public_reason")
/*     */   @Lob
/*     */   private String noPublicReason;
/*     */   @Column(name="edit_user_name", length=100)
/*     */   private String editUserName;
/*     */   @Column(name="public_time")
/*     */   private Date publicTime;
/*     */   @Column(name="log_time", nullable=false)
/*     */   private Date logTime;
/*     */   @OneToOne(mappedBy="dataSetVerPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private DataSetCategoryVerPo dataSetCategoryVerPo;
/*     */   @OneToMany(mappedBy="dataSetVerPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private List<DataSetVerTagPo> dataSetVerTagPoList;
/*     */   @OneToMany(mappedBy="dataSetVerPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private List<DataSetVerMetadataPo> dataSetVerMetadataPoList;
/*     */   @OneToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_set_oid", referencedColumnName="oid")
/*     */   private DataSetPo dataSetPo;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="unit_oid", referencedColumnName="oid", nullable=false)
/*     */   private UnitPo unitPo;
/*     */   
/*     */   public DataSetVerPo()
/*     */   {
/*  71 */     this.isActive = Boolean.TRUE;
/*  72 */     this.isEnable = Boolean.FALSE;
/*  73 */     this.isPublic = Boolean.FALSE;
/*  74 */     this.dataSetVerTagPoList = new ArrayList();
/*  75 */     this.dataSetVerMetadataPoList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/*  83 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  91 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  99 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 107 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 115 */     return this.description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 123 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isActive()
/*     */   {
/* 131 */     return this.isActive;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setActive(Boolean isActive)
/*     */   {
/* 139 */     this.isActive = isActive;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isEnable()
/*     */   {
/* 147 */     return this.isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnable(Boolean isEnable)
/*     */   {
/* 155 */     this.isEnable = isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isPublic()
/*     */   {
/* 163 */     return this.isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublic(Boolean isPublic)
/*     */   {
/* 171 */     this.isPublic = isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNoPublicReason()
/*     */   {
/* 179 */     return this.noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNoPublicReason(String noPublicReason)
/*     */   {
/* 187 */     this.noPublicReason = noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getEditUserName()
/*     */   {
/* 196 */     return this.editUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEditUserName(String editUserName)
/*     */   {
/* 204 */     this.editUserName = editUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getPublicTime()
/*     */   {
/* 212 */     return this.publicTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublicTime(Date publicTime)
/*     */   {
/* 220 */     this.publicTime = publicTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 228 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 236 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataSetCategoryVerPo getDataSetCategoryVerPo()
/*     */   {
/* 244 */     return this.dataSetCategoryVerPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetCategoryVerPo(DataSetCategoryVerPo dataSetCategoryVerPo)
/*     */   {
/* 252 */     this.dataSetCategoryVerPo = dataSetCategoryVerPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataSetVerTagPo> getDataSetVerTagPoList()
/*     */   {
/* 260 */     return this.dataSetVerTagPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetVerTagPoList(List<DataSetVerTagPo> dataTagVerPoList)
/*     */   {
/* 268 */     this.dataSetVerTagPoList = dataTagVerPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataSetVerMetadataPo> getDataSetVerMetadataPoList()
/*     */   {
/* 276 */     return this.dataSetVerMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetVerMetadataPoList(List<DataSetVerMetadataPo> dataSetMetadataPoList)
/*     */   {
/* 284 */     this.dataSetVerMetadataPoList = dataSetMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataSetPo getDataSetPo()
/*     */   {
/* 292 */     return this.dataSetPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetPo(DataSetPo dataSetPo)
/*     */   {
/* 300 */     this.dataSetPo = dataSetPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnitPo getUnitPo()
/*     */   {
/* 308 */     return this.unitPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUnitPo(UnitPo unitPo)
/*     */   {
/* 316 */     this.unitPo = unitPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataSetVerPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */