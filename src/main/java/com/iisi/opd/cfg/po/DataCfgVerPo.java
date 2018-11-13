/*     */ package com.iisi.opd.cfg.po;
/*     */ 
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
/*     */ import javax.persistence.OrderBy;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_data_cfg_ver")
/*     */ public class DataCfgVerPo
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
/*     */   @Column(name="data_set_name", length=100)
/*     */   private String dataSetName;
/*     */   @Column(name="is_structured", nullable=false)
/*     */   private Boolean isStructured;
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
/*     */   @Column(name="start_time")
/*     */   private Date startTime;
/*     */   @Column(name="end_time")
/*     */   private Date endTime;
/*     */   @Column(name="log_time", nullable=false)
/*     */   private Date logTime;
/*     */   @OneToMany(mappedBy="dataCfgVerPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   @OrderBy("fieldOrder")
/*     */   private List<DataFieldCfgVerPo> dataFieldCfgVerPoList;
/*     */   @OneToMany(mappedBy="dataCfgVerPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private List<DataCfgVerMetadataPo> dataCfgVerMetadataPoList;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_cfg_oid", referencedColumnName="oid", nullable=false)
/*     */   private DataCfgPo dataCfgPo;
/*     */   
/*     */   public DataCfgVerPo()
/*     */   {
/*  87 */     this.isStructured = Boolean.TRUE;
/*  88 */     this.isActive = Boolean.TRUE;
/*  89 */     this.isEnable = Boolean.FALSE;
/*  90 */     this.isPublic = Boolean.FALSE;
/*  91 */     this.dataFieldCfgVerPoList = new ArrayList();
/*  92 */     this.dataCfgVerMetadataPoList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/* 100 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/* 108 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 116 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 124 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 132 */     return this.description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 140 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDataSetName()
/*     */   {
/* 148 */     return this.dataSetName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetName(String dataSetName)
/*     */   {
/* 156 */     this.dataSetName = dataSetName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isStructured()
/*     */   {
/* 164 */     return this.isStructured;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStructured(Boolean isStructured)
/*     */   {
/* 172 */     this.isStructured = isStructured;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isActive()
/*     */   {
/* 180 */     return this.isActive;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setActive(Boolean isActive)
/*     */   {
/* 188 */     this.isActive = isActive;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isEnable()
/*     */   {
/* 196 */     return this.isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnable(Boolean isEnable)
/*     */   {
/* 204 */     this.isEnable = isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isPublic()
/*     */   {
/* 212 */     return this.isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublic(Boolean isPublic)
/*     */   {
/* 220 */     this.isPublic = isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNoPublicReason()
/*     */   {
/* 228 */     return this.noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNoPublicReason(String noPublicReason)
/*     */   {
/* 236 */     this.noPublicReason = noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getEditUserName()
/*     */   {
/* 244 */     return this.editUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEditUserName(String editUserName)
/*     */   {
/* 252 */     this.editUserName = editUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getPublicTime()
/*     */   {
/* 260 */     return this.publicTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublicTime(Date publicTime)
/*     */   {
/* 268 */     this.publicTime = publicTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 276 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 284 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 292 */     return this.endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 300 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 308 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 316 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataFieldCfgVerPo> getDataFieldCfgVerPoList()
/*     */   {
/* 324 */     return this.dataFieldCfgVerPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataFieldCfgVerPoList(List<DataFieldCfgVerPo> dataFieldCfgVerPoList)
/*     */   {
/* 332 */     this.dataFieldCfgVerPoList = dataFieldCfgVerPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataCfgVerMetadataPo> getDataCfgVerMetadataList()
/*     */   {
/* 340 */     return this.dataCfgVerMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgVerMetadataList(List<DataCfgVerMetadataPo> dataCfgVerMetadataPoList)
/*     */   {
/* 348 */     this.dataCfgVerMetadataPoList = dataCfgVerMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgPo getDataCfgPo()
/*     */   {
/* 356 */     return this.dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgPo(DataCfgPo dataCfgPo)
/*     */   {
/* 364 */     this.dataCfgPo = dataCfgPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataCfgVerPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */