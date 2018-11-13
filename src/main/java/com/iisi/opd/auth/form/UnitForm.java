/*    */ package com.iisi.opd.auth.form;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class UnitForm
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String unitName;
/*    */   private String oid;
/*    */   private String parentID;
/*    */   private String isRoot;
/*    */   private String unitAlias;
/*    */   private String action;
/*    */   
/*    */   public String getAction()
/*    */   {
/* 19 */     return this.action;
/*    */   }
/*    */   
/* 22 */   public void setAction(String action) { this.action = action; }
/*    */   
/*    */   public String getUnitName() {
/* 25 */     return this.unitName;
/*    */   }
/*    */   
/* 28 */   public void setUnitName(String unitName) { this.unitName = unitName; }
/*    */   
/*    */   public String getOid() {
/* 31 */     return this.oid;
/*    */   }
/*    */   
/* 34 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getParentID() {
/* 37 */     return this.parentID;
/*    */   }
/*    */   
/* 40 */   public void setParentID(String parentID) { this.parentID = parentID; }
/*    */   
/*    */   public String getIsRoot() {
/* 43 */     return this.isRoot;
/*    */   }
/*    */   
/* 46 */   public void setIsRoot(String isRoot) { this.isRoot = isRoot; }
/*    */   
/*    */   public String getUnitAlias() {
/* 49 */     return this.unitAlias;
/*    */   }
/*    */   
/* 52 */   public void setUnitAlias(String unitAlias) { this.unitAlias = unitAlias; }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\form\UnitForm.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */