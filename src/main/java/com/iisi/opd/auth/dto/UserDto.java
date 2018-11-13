/*     */ package com.iisi.opd.auth.dto;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserDto
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String oid;
/*     */   private UnitDto unit;
/*     */   private String userName;
/*     */   private String password;
/*     */   private String email;
/*     */   private String phone;
/*     */   private String loginId;
/*  22 */   private boolean isActive = true;
/*     */   private List<RoleDto> roles;
/*     */   private RoleDto activeRole;
/*     */   private Date createTime;
/*     */   private Date lastUpdateTime;
/*     */   
/*     */   public UserDto() {
/*  29 */     this.roles = new ArrayList();
/*     */   }
/*     */   
/*     */   public UnitDto getUnit() {
/*  33 */     return this.unit;
/*     */   }
/*     */   
/*  36 */   public void setUnit(UnitDto unit) { this.unit = unit; }
/*     */   
/*     */   public String getUserName() {
/*  39 */     return this.userName;
/*     */   }
/*     */   
/*  42 */   public void setUserName(String userName) { this.userName = userName; }
/*     */   
/*     */   public String getOid() {
/*  45 */     return this.oid;
/*     */   }
/*     */   
/*  48 */   public void setOid(String oid) { this.oid = oid; }
/*     */   
/*     */   public String getEmail() {
/*  51 */     return this.email;
/*     */   }
/*     */   
/*  54 */   public void setEmail(String email) { this.email = email; }
/*     */   
/*     */   public List<RoleDto> getRoles() {
/*  57 */     return this.roles;
/*     */   }
/*     */   
/*  60 */   public void setRoles(List<RoleDto> roles) { this.roles = roles; }
/*     */   
/*     */   public RoleDto getActiveRole() {
/*  63 */     return this.activeRole;
/*     */   }
/*     */   
/*  66 */   public void setActiveRole(RoleDto activeRole) { this.activeRole = activeRole; }
/*     */   
/*     */   public String getPassword() {
/*  69 */     return this.password;
/*     */   }
/*     */   
/*  72 */   public void setPassword(String password) { this.password = password; }
/*     */   
/*     */   public String getLoginId()
/*     */   {
/*  76 */     return this.loginId;
/*     */   }
/*     */   
/*     */   public void setLoginId(String loginId) {
/*  80 */     this.loginId = loginId;
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/*  84 */     return this.isActive;
/*     */   }
/*     */   
/*     */   public void setActive(boolean isActive) {
/*  88 */     this.isActive = isActive;
/*     */   }
/*     */   
/*     */   public String getPhone() {
/*  92 */     return this.phone;
/*     */   }
/*     */   
/*     */   public void setPhone(String phone) {
/*  96 */     this.phone = phone;
/*     */   }
/*     */   
/*     */   public Date getCreateTime() {
/* 100 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 104 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */   public Date getLastUpdateTime() {
/* 108 */     return this.lastUpdateTime;
/*     */   }
/*     */   
/*     */   public void setLastUpdateTime(Date lastUpdateTime) {
/* 112 */     this.lastUpdateTime = lastUpdateTime;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dto\UserDto.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */