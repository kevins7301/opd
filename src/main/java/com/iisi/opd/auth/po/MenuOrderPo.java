/*    */ package com.iisi.opd.auth.po;
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
/*    */ @Entity
/*    */ @Table(name="auth_menu_order")
/*    */ public class MenuOrderPo
/*    */ {
/*    */   @Id
/*    */   @Column(name="oid", length=36)
/*    */   @GenericGenerator(name="generator", strategy="guid")
/*    */   @GeneratedValue(generator="generator")
/*    */   private String oid;
/*    */   @Column(name="parent_id", length=100, nullable=false)
/*    */   private String parentID;
/*    */   @Column(name="child_id", length=100, nullable=false)
/*    */   private String childID;
/*    */   @Column(name="child_order", nullable=false)
/*    */   private int childOrder;
/*    */   
/*    */   public String getOid()
/*    */   {
/* 33 */     return this.oid;
/*    */   }
/*    */   
/* 36 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getParentID() {
/* 39 */     return this.parentID;
/*    */   }
/*    */   
/* 42 */   public void setParentID(String parentID) { this.parentID = parentID; }
/*    */   
/*    */   public String getChildID() {
/* 45 */     return this.childID;
/*    */   }
/*    */   
/* 48 */   public void setChildID(String childID) { this.childID = childID; }
/*    */   
/*    */   public int getChildOrder() {
/* 51 */     return this.childOrder;
/*    */   }
/*    */   
/* 54 */   public void setChildOrder(int childOrder) { this.childOrder = childOrder; }
/*    */   
/*    */   public String toString() {
/* 57 */     StringBuilder sb = new StringBuilder();
/* 58 */     sb.append("MenuOrderPo::oid:").append(this.oid);
/* 59 */     sb.append(",parentID:").append(this.parentID);
/* 60 */     sb.append(",childID:").append(this.childID);
/* 61 */     sb.append(",childOrder:").append(this.childOrder);
/* 62 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\po\MenuOrderPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */