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
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.OrderBy;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.Fetch;
/*     */ import org.hibernate.annotations.FetchMode;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_data_cfg")
/*     */ public class DataCfgPo
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
/*     */   @Column(name="is_structured", nullable=false)
/*     */   private Boolean isStructured;
/*     */   @Column(name="is_active", nullable=false)
/*     */   private Boolean isActive;
/*     */   @Column(name="is_enable", nullable=false)
/*     */   private Boolean isEnable;
/*     */   @Column(name="is_applied", nullable=false)
/*     */   private Boolean isApplied;
/*     */   @Column(name="is_public", nullable=false)
/*     */   private Boolean isPublic;
/*     */   @Column(name="no_public_reason")
/*     */   @Lob
/*     */   private String noPublicReason;
/*     */   @Column(name="public_time")
/*     */   private Date publicTime;
/*     */   @Column(name="start_time")
/*     */   private Date startTime;
/*     */   @Column(name="end_time")
/*     */   private Date endTime;
/*     */   @Column(name="last_edit_user_name", length=100)
/*     */   private String lastEditUserName;
/*     */   @Column(name="last_edit_time")
/*     */   private Date lastEditTime;
/*     */   @Column(name="create_time")
/*     */   private Date createTime;
/*     */   @Column(name="update_time")
/*     */   private Date updateTime;
/*     */   @Column(name="data_count")
/*     */   private int dataCount;
/*     */   @OneToMany(mappedBy="dataCfgPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   @OrderBy("fieldOrder")
/*     */   private List<DataFieldCfgPo> dataFieldCfgPoList;
/*     */   @OneToMany(mappedBy="dataCfgPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private List<DataCfgMetadataPo> dataCfgMetadataPoList;
/*     */   @OneToOne(mappedBy="dataCfgPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private DataCfgFilePo dataCfgFilePo;
/*     */   @OneToOne(mappedBy="dataCfgPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private DataCfgTableInfoPo dataCfgTableInfoPo;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_set_oid", referencedColumnName="oid")
/*     */   private DataSetPo dataSetPo;
/*     */   @OneToOne(mappedBy="dataCfgPo", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   @Fetch(FetchMode.JOIN)
/*     */   private DataCfgApplyPo dataCfgApplyPo;
/*     */   @OneToMany(mappedBy="dataCfgPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private List<DataCfgZipFilePo> dataCfgZipFilePoList;
/*     */   
/*     */   public DataCfgPo()
/*     */   {
/* 113 */     this.isStructured = Boolean.TRUE;
/* 114 */     this.isActive = Boolean.TRUE;
/* 115 */     this.isEnable = Boolean.FALSE;
/* 116 */     this.isPublic = Boolean.FALSE;
/* 117 */     this.isApplied = Boolean.FALSE;
/* 118 */     this.dataCount = 0;
/* 119 */     this.dataFieldCfgPoList = new ArrayList();
/* 120 */     this.dataCfgMetadataPoList = new ArrayList();
/* 121 */     this.dataCfgZipFilePoList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/* 129 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/* 137 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 145 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 153 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 161 */     return this.description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 169 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isStructured()
/*     */   {
/* 177 */     return this.isStructured;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStructured(Boolean isStructured)
/*     */   {
/* 185 */     this.isStructured = isStructured;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isActive()
/*     */   {
/* 193 */     return this.isActive;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setActive(Boolean isActive)
/*     */   {
/* 201 */     this.isActive = isActive;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isEnable()
/*     */   {
/* 209 */     return this.isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnable(Boolean isEnable)
/*     */   {
/* 217 */     this.isEnable = isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isApplied()
/*     */   {
/* 225 */     return this.isApplied;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setApplied(Boolean isApplied)
/*     */   {
/* 233 */     this.isApplied = isApplied;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isPublic()
/*     */   {
/* 241 */     return this.isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublic(Boolean isPublic)
/*     */   {
/* 249 */     this.isPublic = isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNoPublicReason()
/*     */   {
/* 257 */     return this.noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNoPublicReason(String noPublicReason)
/*     */   {
/* 265 */     this.noPublicReason = noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getPublicTime()
/*     */   {
/* 273 */     return this.publicTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublicTime(Date publicTime)
/*     */   {
/* 281 */     this.publicTime = publicTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLastEditUserName()
/*     */   {
/* 289 */     return this.lastEditUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 297 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 305 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 313 */     return this.endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 321 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLastEditUserName(String lastEditUserName)
/*     */   {
/* 329 */     this.lastEditUserName = lastEditUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLastEditTime()
/*     */   {
/* 337 */     return this.lastEditTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLastEditTime(Date lastEditTime)
/*     */   {
/* 345 */     this.lastEditTime = lastEditTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataFieldCfgPo> getDataFieldCfgPoList()
/*     */   {
/* 353 */     return this.dataFieldCfgPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataFieldCfgPoList(List<DataFieldCfgPo> dataFieldCfgPoList)
/*     */   {
/* 361 */     this.dataFieldCfgPoList = dataFieldCfgPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataCfgMetadataPo> getDataCfgMetadataPoList()
/*     */   {
/* 369 */     return this.dataCfgMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgMetadataPoList(List<DataCfgMetadataPo> dataCfgMetadataPoList)
/*     */   {
/* 377 */     this.dataCfgMetadataPoList = dataCfgMetadataPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgFilePo getDataCfgFilePo()
/*     */   {
/* 385 */     return this.dataCfgFilePo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgFilePo(DataCfgFilePo dataCfgFilePo)
/*     */   {
/* 393 */     this.dataCfgFilePo = dataCfgFilePo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgTableInfoPo getDataCfgTableInfoPo()
/*     */   {
/* 401 */     return this.dataCfgTableInfoPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgTableInfoPo(DataCfgTableInfoPo dataCfgTableInfoPo)
/*     */   {
/* 409 */     this.dataCfgTableInfoPo = dataCfgTableInfoPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataSetPo getDataSetPo()
/*     */   {
/* 417 */     return this.dataSetPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetPo(DataSetPo dataSetPo)
/*     */   {
/* 425 */     this.dataSetPo = dataSetPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgApplyPo getDataCfgApplyPo()
/*     */   {
/* 433 */     return this.dataCfgApplyPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgApplyPo(DataCfgApplyPo dataCfgApplyPo)
/*     */   {
/* 441 */     this.dataCfgApplyPo = dataCfgApplyPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 449 */     return this.createTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 457 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 465 */     return this.updateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUpdateTime(Date updateTime)
/*     */   {
/* 473 */     this.updateTime = updateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDataCount()
/*     */   {
/* 481 */     return this.dataCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCount(int dataCount)
/*     */   {
/* 489 */     this.dataCount = dataCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataCfgZipFilePo> getDataCfgZipFilePoList()
/*     */   {
/* 497 */     return this.dataCfgZipFilePoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addDataCfgZipFilePo(DataCfgZipFilePo dataCfgZipFilePo)
/*     */   {
/* 505 */     this.dataCfgZipFilePoList.add(dataCfgZipFilePo);
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataCfgPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */