/*     */ package com.iisi.opd.auth.po;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinTable;
/*     */ import javax.persistence.ManyToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="auth_role")
/*     */ public class RolePo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="role_name", length=100, nullable=false)
/*     */   private String roleName;
/*     */   @Column(name="role_level", length=100)
/*     */   private String roleLevel;
/*     */   @Column(name="description", length=100)
/*     */   private String description;
/*     */   @Column(name="last_edit_user_name", length=100)
/*     */   private String lastEditUserName;
/*     */   @Column(name="last_edit_time")
/*     */   private Date lastEditTime;
/*     */   @Column(name="is_active", nullable=false)
/*     */   private boolean isActive;
/*     */   @ManyToMany(cascade={javax.persistence.CascadeType.ALL})
/*     */   @JoinTable(name="auth_role_function_mapping", joinColumns={@javax.persistence.JoinColumn(name="role_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="function_id")})
/*     */   private List<FunctionPo> functions;
/*     */   @ManyToMany(mappedBy="roles")
/*     */   private List<UserPo> users;
/*     */   
/*     */   public RolePo()
/*     */   {
/*  55 */     this.isActive = true;
/*  56 */     this.functions = new ArrayList();
/*  57 */     this.users = new ArrayList();
/*     */   }
/*     */   
/*     */   public String getOid() {
/*  61 */     return this.oid;
/*     */   }
/*     */   
/*  64 */   public void setOid(String oid) { this.oid = oid; }
/*     */   
/*     */   public String getRoleName() {
/*  67 */     return this.roleName;
/*     */   }
/*     */   
/*  70 */   public void setRoleName(String roleName) { this.roleName = roleName; }
/*     */   
/*     */   public String getRoleLevel() {
/*  73 */     return this.roleLevel;
/*     */   }
/*     */   
/*  76 */   public void setRoleLevel(String roleLevel) { this.roleLevel = roleLevel; }
/*     */   
/*     */   public String getDescription() {
/*  79 */     return this.description;
/*     */   }
/*     */   
/*  82 */   public void setDescription(String description) { this.description = description; }
/*     */   
/*     */   public String getLastEditUserName() {
/*  85 */     return this.lastEditUserName;
/*     */   }
/*     */   
/*  88 */   public void setLastEditUserName(String lastEditUserName) { this.lastEditUserName = lastEditUserName; }
/*     */   
/*     */   public Date getLastEditTime() {
/*  91 */     return this.lastEditTime;
/*     */   }
/*     */   
/*  94 */   public void setLastEditTime(Date lastEditTime) { this.lastEditTime = lastEditTime; }
/*     */   
/*     */   public List<FunctionPo> getFunctions() {
/*  97 */     return this.functions;
/*     */   }
/*     */   
/*     */   public void setFunctions(List<FunctionPo> functions) {
/* 101 */     if (functions == null) {
/* 102 */       functions = new ArrayList();
/*     */     }
/* 104 */     for (FunctionPo f : this.functions) {
/* 105 */       f.getRoles().remove(this);
/*     */     }
/* 107 */     this.functions = functions;
/* 108 */     for (FunctionPo f : this.functions)
/* 109 */       f.getRoles().add(this);
/*     */   }
/*     */   
/*     */   public void removeFunction(FunctionPo function) {
/* 113 */     this.functions.remove(function);
/* 114 */     function.getRoles().remove(this);
/*     */   }
/*     */   
/* 117 */   public void addFunction(FunctionPo function) { if (!this.functions.contains(function)) {
/* 118 */       this.functions.add(function);
/* 119 */       function.getRoles().add(this);
/*     */     }
/*     */   }
/*     */   
/* 123 */   public List<UserPo> getUsers() { return this.users; }
/*     */   
/*     */   public void setUsers(List<UserPo> users)
/*     */   {
/* 127 */     if (users == null) {
/* 128 */       users = new ArrayList();
/*     */     }
/* 130 */     for (UserPo u : this.users) {
/* 131 */       u.getRoles().remove(this);
/*     */     }
/* 133 */     this.users = users;
/* 134 */     for (UserPo u : this.users)
/* 135 */       u.getRoles().add(this);
/*     */   }
/*     */   
/*     */   public void addUser(UserPo userPo) {
/* 139 */     if (!this.users.contains(userPo)) {
/* 140 */       this.users.add(userPo);
/* 141 */       userPo.getRoles().add(this);
/*     */     }
/*     */   }
/*     */   
/* 145 */   public void removeUser(UserPo userPo) { this.users.remove(userPo);
/* 146 */     userPo.getRoles().remove(this);
/*     */   }
/*     */   
/* 149 */   public boolean isActive() { return this.isActive; }
/*     */   
/*     */   public void setActive(boolean isActive) {
/* 152 */     this.isActive = isActive;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 156 */     StringBuilder sb = new StringBuilder();
/* 157 */     sb.append("RolePo::oid:").append(this.oid);
/* 158 */     sb.append(",roleName:").append(this.roleName);
/* 159 */     sb.append(",roleLevel:").append(this.roleLevel);
/* 160 */     sb.append(",description:").append(this.description);
/* 161 */     sb.append(",lastEditUserName:").append(this.lastEditUserName);
/* 162 */     sb.append(",lastEditTime:").append(this.lastEditTime);
/* 163 */     sb.append(",isActive:").append(this.isActive);
/* 164 */     sb.append(",functions:{");
/* 165 */     for (FunctionPo f : this.functions) {
/* 166 */       sb.append(f.toString(false)).append(",");
/*     */     }
/* 168 */     sb.append("}");
/* 169 */     sb.append(",users:{");
/* 170 */     for (UserPo u : this.users) {
/* 171 */       sb.append(u.toString(false)).append(",");
/*     */     }
/* 173 */     sb.append("}");
/* 174 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(boolean isFull)
/*     */   {
/* 182 */     if (isFull) {
/* 183 */       return toString();
/*     */     }
/* 185 */     StringBuilder sb = new StringBuilder();
/* 186 */     sb.append("[RolePo::oid:").append(this.oid);
/* 187 */     sb.append(",roleName:").append(this.roleName).append("]");
/* 188 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\po\RolePo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */