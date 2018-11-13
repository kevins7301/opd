/*    */ package com.iisi.opd.cfg.po;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name="od_rule_base")
/*    */ public class DataRuleBasePo
/*    */ {
/*    */   @Id
/*    */   @Column(name="rule_id", length=60)
/*    */   private String ruleId;
/*    */   @Column(name="rule_desc", length=60)
/*    */   private String ruleDesc;
/*    */   @Column(name="with_condition")
/*    */   private boolean withCondition;
/*    */   
/*    */   public DataRuleBasePo()
/*    */   {
/* 26 */     this.withCondition = false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getRuleId()
/*    */   {
/* 34 */     return this.ruleId;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setRuleId(String ruleId)
/*    */   {
/* 42 */     this.ruleId = ruleId;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getRuleDesc()
/*    */   {
/* 50 */     return this.ruleDesc;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setRuleDesc(String ruleDesc)
/*    */   {
/* 58 */     this.ruleDesc = ruleDesc;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean isWithCondition()
/*    */   {
/* 66 */     return this.withCondition;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setWithCondition(boolean withCondition)
/*    */   {
/* 74 */     this.withCondition = withCondition;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\po\DataRuleBasePo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */