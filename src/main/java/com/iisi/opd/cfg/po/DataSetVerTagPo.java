/*    */ package com.iisi.opd.cfg.po;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name="od_data_set_ver_tag")
/*    */ public class DataSetVerTagPo
/*    */ {
/*    */   @Id
/*    */   @Column(name="oid", length=36)
/*    */   @GenericGenerator(name="generator", strategy="guid")
/*    */   @GeneratedValue(generator="generator")
/*    */   private String oid;
/*    */   @Column(name="name", length=100)
/*    */   private String name;
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="od_data_set_ver_oid", referencedColumnName="oid", nullable=false)
/*    */   private DataSetVerPo dataSetVerPo;
/*    */   
/*    */   public String getOid()
/*    */   {
/* 39 */     return this.oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setOid(String oid)
/*    */   {
/* 47 */     this.oid = oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getName()
/*    */   {
/* 55 */     return this.name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 63 */     this.name = name;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public DataSetVerPo getDataSetVerPo()
/*    */   {
/* 71 */     return this.dataSetVerPo;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setDataSetVerPo(DataSetVerPo dataSetVerPo)
/*    */   {
/* 79 */     this.dataSetVerPo = dataSetVerPo;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataSetVerTagPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */