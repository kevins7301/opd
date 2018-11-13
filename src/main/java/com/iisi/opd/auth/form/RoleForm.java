/*    */ package com.iisi.opd.auth.form;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RoleForm implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String oid;
/*    */   private String roleName;
/*    */   private String[] functionIDs;
/*    */   private String action;
/*    */   
/*    */   public String getOid() {
/* 13 */     return this.oid;
/*    */   }
/*    */   
/* 16 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getRoleName() {
/* 19 */     return this.roleName;
/*    */   }
/*    */   
/* 22 */   public void setRoleName(String roleName) { this.roleName = roleName; }
/*    */   
/*    */   public String getAction() {
/* 25 */     return this.action;
/*    */   }
/*    */   
/* 28 */   public void setAction(String action) { this.action = action; }
/*    */   
/*    */   public String[] getFunctionIDs() {
/* 31 */     return this.functionIDs;
/*    */   }
/*    */   
/* 34 */   public void setFunctionIDs(String[] functionIDs) { this.functionIDs = functionIDs; }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\form\RoleForm.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */