/*     */ package com.iisi.opd.cfg.po;
/*     */ 
/*     */ import java.sql.Blob;
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
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_data_cfg_file")
/*     */ public class DataCfgFile2Po
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
/*     */   @OneToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="od_data_cfg_oid", referencedColumnName="oid")
/*     */   private DataCfgPo dataCfgPo;
/*     */   @Column(name="content")
/*     */   @Lob
/*     */   private Blob content;
/*     */   
/*     */   public String getOid()
/*     */   {
/*  49 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  57 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  65 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  73 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSize()
/*     */   {
/*  81 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/*  89 */     this.size = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Blob getContent()
/*     */   {
/*  97 */     return this.content;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setContent(Blob content)
/*     */   {
/* 105 */     this.content = content;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DataCfgPo getDataCfgPo()
/*     */   {
/* 113 */     return this.dataCfgPo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataCfgPo(DataCfgPo dataCfgPo)
/*     */   {
/* 121 */     this.dataCfgPo = dataCfgPo;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataCfgFile2Po.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */