/*    */ package com.iisi.opd.auth.form;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class OrderForm implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String parentID;
/*    */   private String[] childOrders;
/*    */   
/* 10 */   public String getParentID() { return this.parentID; }
/*    */   
/*    */   public void setParentID(String parentID) {
/* 13 */     this.parentID = parentID;
/*    */   }
/*    */   
/* 16 */   public String[] getChildOrders() { return this.childOrders; }
/*    */   
/*    */   public void setChildOrders(String[] childOrders) {
/* 19 */     this.childOrders = childOrders;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\form\OrderForm.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */