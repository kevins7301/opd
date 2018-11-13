/*     */ package com.iisi.opd.auth.po;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.JoinTable;
/*     */ import javax.persistence.ManyToMany;
/*     */ import javax.persistence.ManyToOne;
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
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="auth_user")
/*     */ public class UserPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="unit_id", nullable=false)
/*     */   private UnitPo unit;
/*     */   @Column(name="user_name", nullable=false, columnDefinition="nvarchar(100)")
/*     */   private String userName;
/*     */   @Column(name="password", length=100, nullable=false)
/*     */   private String password;
/*     */   @Column(name="email", length=100, nullable=false)
/*     */   private String email;
/*     */   @Column(name="phone", nullable=false, columnDefinition="nvarchar(36) default ''")
/*     */   private String phone;
/*     */   @Column(name="login_id", length=100, nullable=false)
/*     */   private String loginId;
/*     */   @Column(name="is_active", nullable=false)
/*  54 */   private boolean isActive = true;
/*     */   
/*     */ 
/*     */   @Column(name="create_time", nullable=true)
/*     */   private Date createTime;
/*     */   
/*     */   @Column(name="last_update_time", nullable=true)
/*     */   private Date lastUpdateTime;
/*     */   
/*     */   @ManyToMany(cascade={javax.persistence.CascadeType.ALL})
/*     */   @JoinTable(name="auth_user_role_mapping", joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="role_id")})
/*  65 */   private List<RolePo> roles = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnitPo getUnit()
/*     */   {
/*  72 */     return this.unit;
/*     */   }
/*     */   
/*  75 */   public void setUnit(UnitPo unit) { this.unit = unit; }
/*     */   
/*     */   public String getUserName() {
/*  78 */     return this.userName;
/*     */   }
/*     */   
/*  81 */   public void setUserName(String userName) { this.userName = userName; }
/*     */   
/*     */   public String getOid() {
/*  84 */     return this.oid;
/*     */   }
/*     */   
/*  87 */   public void setOid(String oid) { this.oid = oid; }
/*     */   
/*     */   public String getEmail() {
/*  90 */     return this.email;
/*     */   }
/*     */   
/*  93 */   public void setEmail(String email) { this.email = email; }
/*     */   
/*     */   public String getPhone() {
/*  96 */     return this.phone;
/*     */   }
/*     */   
/*  99 */   public void setPhone(String phone) { this.phone = phone; }
/*     */   
/*     */   public List<RolePo> getRoles() {
/* 102 */     return this.roles;
/*     */   }
/*     */   
/*     */   public void setRoles(List<RolePo> roles) {
/* 106 */     if (roles == null) {
/* 107 */       roles = new ArrayList();
/*     */     }
/* 109 */     for (RolePo r : this.roles) {
/* 110 */       r.getUsers().remove(this);
/*     */     }
/* 112 */     this.roles = roles;
/* 113 */     for (RolePo r : this.roles)
/* 114 */       r.getUsers().add(this);
/*     */   }
/*     */   
/*     */   public void removeRole(RolePo rolePo) {
/* 118 */     this.roles.remove(rolePo);
/* 119 */     rolePo.getUsers().remove(this);
/*     */   }
/*     */   
/* 122 */   public void addRole(RolePo rolePo) { if (!this.roles.contains(rolePo)) {
/* 123 */       this.roles.add(rolePo);
/* 124 */       rolePo.getUsers().add(this);
/*     */     }
/*     */   }
/*     */   
/* 128 */   public String getPassword() { return this.password; }
/*     */   
/*     */   public void setPassword(String password) {
/* 131 */     this.password = password;
/*     */   }
/*     */   
/* 134 */   public String getLoginId() { return this.loginId; }
/*     */   
/*     */   public void setLoginId(String loginId) {
/* 137 */     this.loginId = loginId;
/*     */   }
/*     */   
/* 140 */   public boolean isActive() { return this.isActive; }
/*     */   
/*     */   public void setActive(boolean isActive) {
/* 143 */     this.isActive = isActive;
/*     */   }
/*     */   
/* 146 */   public Date getCreateTime() { return this.createTime; }
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/* 149 */     this.createTime = createTime;
/*     */   }
/*     */   
/* 152 */   public Date getLastUpdateTime() { return this.lastUpdateTime; }
/*     */   
/*     */   public void setLastUpdateTime(Date lastUpdateTime) {
/* 155 */     this.lastUpdateTime = lastUpdateTime;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 159 */     StringBuilder sb = new StringBuilder();
/* 160 */     sb.append("UserPo::oid:").append(this.oid);
/* 161 */     sb.append(",loginId:").append(this.loginId);
/* 162 */     sb.append(",userName:").append(this.userName);
/* 163 */     sb.append(",password:").append(this.password);
/* 164 */     sb.append(",isActive:").append(this.isActive);
/* 165 */     sb.append(",createTime:").append(this.createTime);
/* 166 */     sb.append(",lastUpdateTime:").append(this.lastUpdateTime);
/* 167 */     sb.append(",phone:").append(this.phone);
/* 168 */     sb.append(",email:").append(this.email);
/* 169 */     sb.append(",unit:").append(this.unit.toString(false));
/* 170 */     sb.append(",roles:{");
/* 171 */     for (RolePo r : this.roles) {
/* 172 */       sb.append(r.toString(false)).append(",");
/*     */     }
/* 174 */     sb.append("}");
/* 175 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(boolean isFull)
/*     */   {
/* 183 */     if (isFull) {
/* 184 */       return toString();
/*     */     }
/* 186 */     StringBuilder sb = new StringBuilder();
/* 187 */     sb.append("[UserPo::oid:").append(this.oid);
/* 188 */     sb.append(",loginId:").append(this.loginId).append("]");
/* 189 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\po\UserPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */