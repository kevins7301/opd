/*     */ package com.iisi.opd.cfg.po;
/*     */ 
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_data_cfg_file")
/*     */ public class DataCfgFilePo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="name")
/*     */   @Lob
/*     */   private String name;
/*     */   @Column(name="size")
/*     */   private long size;
/*     */   @Column(name="content")
/*     */   @Lob
/*     */   private byte[] content;
/*     */   @OneToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_cfg_oid", referencedColumnName="oid")
/*     */   private DataCfgPo dataCfgPo;
/*     */   
/*     */   public String getOid()
/*     */   {
/*  47 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  55 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  63 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  71 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSize()
/*     */   {
/*  79 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/*  87 */     this.size = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getContent()
/*     */   {
/*  95 */     return this.content;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setContent(byte[] content)
/*     */   {
/* 103 */     this.content = content;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgPo getDataCfgPo()
/*     */   {
/* 111 */     return this.dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgPo(DataCfgPo dataCfgPo)
/*     */   {
/* 119 */     this.dataCfgPo = dataCfgPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataCfgFilePo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */