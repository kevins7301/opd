/*    */ package com.iisi.opd.auth.form;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FunctionForm
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String oid;
/*    */   private String functionName;
/*    */   private String methodPath;
/*    */   private String accessPath;
/*    */   private String[] functionIDs;
/*    */   private String isGroup;
/*    */   private String action;
/*    */   
/*    */   public String getOid()
/*    */   {
/* 21 */     return this.oid;
/*    */   }
/*    */   
/* 24 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getAction() {
/* 27 */     return this.action;
/*    */   }
/*    */   
/* 30 */   public void setAction(String action) { this.action = action; }
/*    */   
/*    */   public String getFunctionName() {
/* 33 */     return this.functionName;
/*    */   }
/*    */   
/* 36 */   public void setFunctionName(String functionName) { this.functionName = functionName; }
/*    */   
/*    */   public String getMethodPath() {
/* 39 */     return this.methodPath;
/*    */   }
/*    */   
/* 42 */   public void setMethodPath(String methodPath) { this.methodPath = methodPath; }
/*    */   
/*    */   public String getAccessPath() {
/* 45 */     return this.accessPath;
/*    */   }
/*    */   
/* 48 */   public void setAccessPath(String accessPath) { this.accessPath = accessPath; }
/*    */   
/*    */   public String[] getFunctionIDs() {
/* 51 */     return this.functionIDs;
/*    */   }
/*    */   
/* 54 */   public void setFunctionIDs(String[] functionIDs) { this.functionIDs = functionIDs; }
/*    */   
/*    */   public String getIsGroup() {
/* 57 */     return this.isGroup;
/*    */   }
/*    */   
/* 60 */   public void setIsGroup(String isGroup) { this.isGroup = isGroup; }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\form\FunctionForm.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */