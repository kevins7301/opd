/*    */ package com.iisi.opd.auth.form;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserForm
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String unitID;
/*    */   private String userName;
/*    */   private String password;
/*    */   private String oid;
/*    */   private String email;
/*    */   private String phone;
/*    */   private String[] roleIDs;
/*    */   private String action;
/*    */   private Date createTime;
/*    */   private Date lastUpdateTime;
/*    */   
/*    */   public String getUserName()
/*    */   {
/* 25 */     return this.userName;
/*    */   }
/*    */   
/* 28 */   public void setUserName(String userName) { this.userName = userName; }
/*    */   
/*    */   public String getOid() {
/* 31 */     return this.oid;
/*    */   }
/*    */   
/* 34 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getEmail() {
/* 37 */     return this.email;
/*    */   }
/*    */   
/* 40 */   public void setEmail(String email) { this.email = email; }
/*    */   
/*    */   public String getPassword() {
/* 43 */     return this.password;
/*    */   }
/*    */   
/* 46 */   public void setPassword(String password) { this.password = password; }
/*    */   
/*    */   public String getUnitID() {
/* 49 */     return this.unitID;
/*    */   }
/*    */   
/* 52 */   public void setUnitID(String unitID) { this.unitID = unitID; }
/*    */   
/*    */   public String[] getRoleIDs() {
/* 55 */     return this.roleIDs;
/*    */   }
/*    */   
/* 58 */   public void setRoleIDs(String[] roleIDs) { this.roleIDs = roleIDs; }
/*    */   
/*    */   public String getAction() {
/* 61 */     return this.action;
/*    */   }
/*    */   
/* 64 */   public void setAction(String action) { this.action = action; }
/*    */   
/*    */   public String getPhone() {
/* 67 */     return this.phone;
/*    */   }
/*    */   
/* 70 */   public void setPhone(String phone) { this.phone = phone; }
/*    */   
/*    */   public Date getCreateTime() {
/* 73 */     return this.createTime;
/*    */   }
/*    */   
/* 76 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*    */   
/*    */   public Date getLastUpdateTime() {
/* 79 */     return this.lastUpdateTime;
/*    */   }
/*    */   
/* 82 */   public void setLastUpdateTime(Date lastUpdateTime) { this.lastUpdateTime = lastUpdateTime; }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\form\UserForm.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */