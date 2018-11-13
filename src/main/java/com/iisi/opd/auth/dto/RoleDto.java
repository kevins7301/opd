/*    */ package com.iisi.opd.auth.dto;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoleDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String oid;
/*    */   private String roleName;
/*    */   private String roleLevel;
/*    */   private String description;
/*    */   private String lastEditUserName;
/*    */   private Date lastEditTime;
/*    */   private boolean isActive;
/* 22 */   private List<FunctionDto> functions = new ArrayList();
/*    */   
/*    */   public String getOid() {
/* 25 */     return this.oid;
/*    */   }
/*    */   
/* 28 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getRoleName() {
/* 31 */     return this.roleName;
/*    */   }
/*    */   
/* 34 */   public void setRoleName(String roleName) { this.roleName = roleName; }
/*    */   
/*    */   public List<FunctionDto> getFunctions() {
/* 37 */     return this.functions;
/*    */   }
/*    */   
/* 40 */   public void setFunctions(List<FunctionDto> functions) { this.functions = functions; }
/*    */   
/*    */   public String getRoleLevel() {
/* 43 */     return this.roleLevel;
/*    */   }
/*    */   
/* 46 */   public void setRoleLevel(String roleLevel) { this.roleLevel = roleLevel; }
/*    */   
/*    */   public String getDescription() {
/* 49 */     return this.description;
/*    */   }
/*    */   
/* 52 */   public void setDescription(String description) { this.description = description; }
/*    */   
/*    */   public String getLastEditUserName() {
/* 55 */     return this.lastEditUserName;
/*    */   }
/*    */   
/* 58 */   public void setLastEditUserName(String lastEditUserName) { this.lastEditUserName = lastEditUserName; }
/*    */   
/*    */   public Date getLastEditTime() {
/* 61 */     return this.lastEditTime;
/*    */   }
/*    */   
/* 64 */   public void setLastEditTime(Date lastEditTime) { this.lastEditTime = lastEditTime; }
/*    */   
/*    */   public boolean isActive() {
/* 67 */     return this.isActive;
/*    */   }
/*    */   
/* 70 */   public void setActive(boolean isActive) { this.isActive = isActive; }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dto\RoleDto.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */