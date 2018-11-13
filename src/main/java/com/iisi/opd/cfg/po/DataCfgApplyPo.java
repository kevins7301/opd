/*     */ package com.iisi.opd.cfg.po;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.EnumType;
/*     */ import javax.persistence.Enumerated;
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
/*     */ @Entity
/*     */ @Table(name="od_data_cfg_apply")
/*     */ public class DataCfgApplyPo
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
/*     */   @Column(name="is_enable", nullable=false)
/*     */   private Boolean isEnable;
/*     */   @Column(name="is_public", nullable=false)
/*     */   private Boolean isPublic;
/*     */   @Column(name="no_public_reason")
/*     */   @Lob
/*     */   private String noPublicReason;
/*     */   @Column(name="comment", columnDefinition="varchar(max)")
/*     */   private String comment;
/*     */   @Column(name="apply_user_name", length=100, nullable=false)
/*     */   private String applyUserName;
/*     */   @Column(name="apply_time", nullable=false)
/*     */   private Date applyTime;
/*     */   @Column(name="refuse_time")
/*     */   private Date refuseTime;
/*     */   @Column(name="start_time")
/*     */   private Date startTime;
/*     */   @Column(name="end_time")
/*     */   private Date endTime;
/*     */   @Enumerated(EnumType.ORDINAL)
/*     */   @Column(name="action_type", nullable=false)
/*     */   private ActionType actionType;
/*     */   @Enumerated(EnumType.ORDINAL)
/*     */   @Column(name="data_status", nullable=false)
/*     */   private DataStatus dataStatus;
/*     */   @OneToMany(mappedBy="dataCfgApplyPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   @OrderBy("fieldOrder")
/*     */   private List<DataFieldCfgApplyPo> dataFieldCfgApplyPoList;
/*     */   @OneToMany(mappedBy="dataCfgApplyPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private List<DataCfgMetadataApplyPo> dataCfgMetadataApplyPoList;
/*     */   @OneToOne(mappedBy="dataCfgApplyPo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
/*     */   private DataCfgFileApplyPo dataCfgFileApplyPo;
/*     */   @OneToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_cfg_oid", referencedColumnName="oid")
/*     */   private DataCfgPo dataCfgPo;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_set_apply_oid", referencedColumnName="oid")
/*     */   private DataSetApplyPo dataSetApplyPo;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_set_oid", referencedColumnName="oid")
/*     */   private DataSetPo dataSetPo;
/*     */   
/*     */   public DataCfgApplyPo()
/*     */   {
/* 106 */     this.isStructured = Boolean.TRUE;
/* 107 */     this.isEnable = Boolean.FALSE;
/* 108 */     this.isPublic = Boolean.FALSE;
/* 109 */     this.dataFieldCfgApplyPoList = new ArrayList();
/* 110 */     this.dataCfgMetadataApplyPoList = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getOid()
/*     */   {
/* 118 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/* 126 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 134 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 142 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 150 */     return this.description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 158 */     this.description = description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isStructured()
/*     */   {
/* 166 */     return this.isStructured;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStructured(Boolean isStructured)
/*     */   {
/* 174 */     this.isStructured = isStructured;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isEnable()
/*     */   {
/* 182 */     return this.isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnable(Boolean isEnable)
/*     */   {
/* 190 */     this.isEnable = isEnable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean isPublic()
/*     */   {
/* 198 */     return this.isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublic(Boolean isPublic)
/*     */   {
/* 206 */     this.isPublic = isPublic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNoPublicReason()
/*     */   {
/* 214 */     return this.noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNoPublicReason(String noPublicReason)
/*     */   {
/* 222 */     this.noPublicReason = noPublicReason;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getComment()
/*     */   {
/* 230 */     return this.comment;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/* 238 */     this.comment = comment;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getApplyUserName()
/*     */   {
/* 246 */     return this.applyUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setApplyUserName(String applyUserName)
/*     */   {
/* 254 */     this.applyUserName = applyUserName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getApplyTime()
/*     */   {
/* 262 */     return this.applyTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setApplyTime(Date applyTime)
/*     */   {
/* 270 */     this.applyTime = applyTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getRefuseTime()
/*     */   {
/* 278 */     return this.refuseTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRefuseTime(Date refuseTime)
/*     */   {
/* 286 */     this.refuseTime = refuseTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 294 */     return this.startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 302 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 310 */     return this.endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 318 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ActionType getActionType()
/*     */   {
/* 326 */     return this.actionType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setActionType(ActionType type)
/*     */   {
/* 334 */     this.actionType = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataStatus getDataStatus()
/*     */   {
/* 342 */     return this.dataStatus;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataStatus(DataStatus status)
/*     */   {
/* 350 */     this.dataStatus = status;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataFieldCfgApplyPo> getDataFieldCfgApplyPoList()
/*     */   {
/* 358 */     return this.dataFieldCfgApplyPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataFieldCfgApplyPoList(List<DataFieldCfgApplyPo> dataFieldCfgApplyPoList)
/*     */   {
/* 366 */     this.dataFieldCfgApplyPoList = dataFieldCfgApplyPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<DataCfgMetadataApplyPo> getDataCfgMetadataApplyPoList()
/*     */   {
/* 374 */     return this.dataCfgMetadataApplyPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgMetadataApplyPoList(List<DataCfgMetadataApplyPo> dataCfgMetadataApplyPoList)
/*     */   {
/* 382 */     this.dataCfgMetadataApplyPoList = dataCfgMetadataApplyPoList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgFileApplyPo getDataCfgFileApplyPo()
/*     */   {
/* 390 */     return this.dataCfgFileApplyPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgFileApplyPo(DataCfgFileApplyPo dataCfgFileApplyPo)
/*     */   {
/* 398 */     this.dataCfgFileApplyPo = dataCfgFileApplyPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgPo getDataCfgPo()
/*     */   {
/* 406 */     return this.dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgPo(DataCfgPo dataCfgPo)
/*     */   {
/* 414 */     this.dataCfgPo = dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataSetApplyPo getDataSetApplyPo()
/*     */   {
/* 422 */     return this.dataSetApplyPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetApplyPo(DataSetApplyPo dataSetApplyPo)
/*     */   {
/* 430 */     this.dataSetApplyPo = dataSetApplyPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataSetPo getDataSetPo()
/*     */   {
/* 438 */     return this.dataSetPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataSetPo(DataSetPo dataSetPo)
/*     */   {
/* 446 */     this.dataSetPo = dataSetPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static enum ActionType
/*     */   {
/* 454 */     ENABLE(1),  EDIT(2),  DISABLE(3);
/*     */     
/*     */     private int actionType;
/*     */     
/* 458 */     private ActionType(int actionType) { this.actionType = actionType; }
/*     */     
/*     */     public int getActionType()
/*     */     {
/* 462 */       return this.actionType;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum DataStatus
/*     */   {
/* 470 */     APPLY(1),  AGREE(2),  REFUSE(3);
/*     */     
/*     */     private int dataStatus;
/*     */     
/* 474 */     private DataStatus(int dataStatus) { this.dataStatus = dataStatus; }
/*     */     
/*     */     public int getDataStatus()
/*     */     {
/* 478 */       return this.dataStatus;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataCfgApplyPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */