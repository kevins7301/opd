/*    */ package com.iisi.opd.log.po;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
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
/*    */ @Entity
/*    */ @Table(name="od_log_data_check_result")
/*    */ public class DataCheckResultPo
/*    */ {
/*    */   @Id
/*    */   @Column(name="oid", length=36)
/*    */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*    */   @GeneratedValue(generator="generator")
/*    */   private String oid;
/*    */   @Column(name="content", nullable=false, columnDefinition="text")
/*    */   private String content;
/*    */   
/*    */   public String getOid()
/*    */   {
/* 31 */     return this.oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setOid(String oid)
/*    */   {
/* 39 */     this.oid = oid;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getContent()
/*    */   {
/* 47 */     return this.content;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setContent(String content)
/*    */   {
/* 55 */     this.content = content;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\DataCheckResultPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */