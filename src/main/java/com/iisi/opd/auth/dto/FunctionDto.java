/*    */ package com.iisi.opd.auth.dto;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FunctionDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String oid;
/*    */   private String functionName;
/*    */   private String methodPath;
/*    */   private String accessPath;
/*    */   private boolean isGroup;
/* 19 */   private List<FunctionDto> childFunctions = new ArrayList();
/* 20 */   private List<FunctionDto> parentFunctions = new ArrayList();
/*    */   
/*    */   public String getOid() {
/* 23 */     return this.oid;
/*    */   }
/*    */   
/* 26 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getFunctionName() {
/* 29 */     return this.functionName;
/*    */   }
/*    */   
/* 32 */   public void setFunctionName(String functionName) { this.functionName = functionName; }
/*    */   
/*    */   public String getMethodPath() {
/* 35 */     return this.methodPath;
/*    */   }
/*    */   
/* 38 */   public void setMethodPath(String methodPath) { this.methodPath = methodPath; }
/*    */   
/*    */   public String getAccessPath() {
/* 41 */     return this.accessPath;
/*    */   }
/*    */   
/* 44 */   public void setAccessPath(String accessPath) { this.accessPath = accessPath; }
/*    */   
/*    */   public boolean isGroup() {
/* 47 */     return this.isGroup;
/*    */   }
/*    */   
/* 50 */   public void setGroup(boolean isGroup) { this.isGroup = isGroup; }
/*    */   
/*    */   public List<FunctionDto> getChildFunctions() {
/* 53 */     return this.childFunctions;
/*    */   }
/*    */   
/* 56 */   public void setChildFunctions(List<FunctionDto> childFunctions) { this.childFunctions = childFunctions; }
/*    */   
/*    */   public List<FunctionDto> getParentFunctions() {
/* 59 */     return this.parentFunctions;
/*    */   }
/*    */   
/* 62 */   public void setParentFunctions(List<FunctionDto> parentFunctions) { this.parentFunctions = parentFunctions; }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dto\FunctionDto.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */