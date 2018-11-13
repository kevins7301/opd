/*     */ package com.iisi.opd.auth.po;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ @Entity
/*     */ @Table(name="auth_function")
/*     */ public class FunctionPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="function_name", length=100, nullable=false)
/*     */   private String functionName;
/*     */   @Column(name="method_path", length=100, nullable=false)
/*     */   private String methodPath;
/*     */   @Column(name="access_path", length=100, nullable=false)
/*     */   private String accessPath;
/*     */   @Column(name="is_group", nullable=false)
/*     */   private boolean isGroup;
/*     */   @ManyToMany(cascade={javax.persistence.CascadeType.ALL})
/*     */   @JoinTable(name="auth_function_group", joinColumns={@javax.persistence.JoinColumn(name="function_group_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="function_id")})
/*  38 */   private List<FunctionPo> childFunctions = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @ManyToMany(mappedBy="functions")
/*  44 */   private List<RolePo> roles = new ArrayList();
/*     */   
/*     */   @ManyToMany(mappedBy="childFunctions")
/*  47 */   private List<FunctionPo> parentFunctions = new ArrayList();
/*     */   
/*     */   public String getOid()
/*     */   {
/*  51 */     return this.oid;
/*     */   }
/*     */   
/*  54 */   public void setOid(String oid) { this.oid = oid; }
/*     */   
/*     */   public String getFunctionName() {
/*  57 */     return this.functionName;
/*     */   }
/*     */   
/*  60 */   public void setFunctionName(String functionName) { this.functionName = functionName; }
/*     */   
/*     */   public String getMethodPath() {
/*  63 */     return this.methodPath;
/*     */   }
/*     */   
/*  66 */   public void setMethodPath(String methodPath) { this.methodPath = methodPath; }
/*     */   
/*     */   public String getAccessPath() {
/*  69 */     return this.accessPath;
/*     */   }
/*     */   
/*  72 */   public void setAccessPath(String accessPath) { this.accessPath = accessPath; }
/*     */   
/*     */   public boolean isGroup() {
/*  75 */     return this.isGroup;
/*     */   }
/*     */   
/*  78 */   public void setGroup(boolean isGroup) { this.isGroup = isGroup; }
/*     */   
/*     */   public List<RolePo> getRoles() {
/*  81 */     return this.roles;
/*     */   }
/*     */   
/*     */   public void setRoles(List<RolePo> roles) {
/*  85 */     if (roles == null) {
/*  86 */       roles = new ArrayList();
/*     */     }
/*  88 */     for (RolePo role : this.roles) {
/*  89 */       role.getFunctions().remove(this);
/*     */     }
/*  91 */     this.roles = roles;
/*  92 */     for (RolePo role : this.roles)
/*  93 */       role.getFunctions().add(this);
/*     */   }
/*     */   
/*     */   public void removeRole(RolePo role) {
/*  97 */     this.roles.remove(role);
/*  98 */     role.getFunctions().remove(this);
/*     */   }
/*     */   
/* 101 */   public void addRole(RolePo role) { if (!this.roles.contains(role)) {
/* 102 */       this.roles.add(role);
/* 103 */       role.getFunctions().add(this);
/*     */     }
/*     */   }
/*     */   
/* 107 */   public List<FunctionPo> getChildFunctions() { return this.childFunctions; }
/*     */   
/*     */   public void setChildFunctions(List<FunctionPo> childFunctions)
/*     */   {
/* 111 */     if (childFunctions == null) {
/* 112 */       childFunctions = new ArrayList();
/*     */     }
/* 114 */     for (FunctionPo child : this.childFunctions) {
/* 115 */       child.getParentFunctions().remove(this);
/*     */     }
/* 117 */     this.childFunctions = childFunctions;
/* 118 */     for (FunctionPo child : this.childFunctions)
/* 119 */       child.getParentFunctions().add(this);
/*     */   }
/*     */   
/*     */   public void removeChildFunction(FunctionPo function) {
/* 123 */     this.childFunctions.remove(function);
/* 124 */     function.getParentFunctions().remove(this);
/*     */   }
/*     */   
/* 127 */   public void addChildFunction(FunctionPo function) { if (!this.childFunctions.contains(function)) {
/* 128 */       this.childFunctions.add(function);
/* 129 */       function.getParentFunctions().add(this);
/*     */     }
/*     */   }
/*     */   
/* 133 */   public List<FunctionPo> getParentFunctions() { return this.parentFunctions; }
/*     */   
/*     */   public void setParentFunctions(List<FunctionPo> parentFunctions)
/*     */   {
/* 137 */     if (parentFunctions == null) {
/* 138 */       parentFunctions = new ArrayList();
/*     */     }
/* 140 */     for (FunctionPo parent : this.parentFunctions) {
/* 141 */       parent.getChildFunctions().remove(this);
/*     */     }
/* 143 */     this.parentFunctions = parentFunctions;
/* 144 */     for (FunctionPo parent : this.parentFunctions)
/* 145 */       parent.getChildFunctions().add(this);
/*     */   }
/*     */   
/*     */   public void removeParentFunction(FunctionPo function) {
/* 149 */     this.parentFunctions.remove(function);
/* 150 */     function.getChildFunctions().remove(this);
/*     */   }
/*     */   
/* 153 */   public void addParentFunction(FunctionPo function) { if (!this.parentFunctions.contains(function)) {
/* 154 */       this.parentFunctions.add(function);
/* 155 */       function.getChildFunctions().add(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 160 */     StringBuilder sb = new StringBuilder();
/* 161 */     sb.append("FunctionPo::oid:").append(this.oid);
/* 162 */     sb.append(",functionName:").append(this.functionName);
/* 163 */     sb.append(",methodPath:").append(this.methodPath);
/* 164 */     sb.append(",accessPath:").append(this.accessPath);
/* 165 */     sb.append(",isGroup:").append(this.isGroup);
/* 166 */     sb.append(",childFunctions:{");
/* 167 */     for (FunctionPo f : this.childFunctions) {
/* 168 */       sb.append(f.toString(false)).append(",");
/*     */     }
/* 170 */     sb.append("}");
/* 171 */     sb.append(",parentFunctions:{");
/* 172 */     for (FunctionPo f : this.parentFunctions) {
/* 173 */       sb.append(f.toString(false)).append(",");
/*     */     }
/* 175 */     sb.append("}");
/* 176 */     sb.append(",roles:{");
/* 177 */     for (RolePo r : this.roles) {
/* 178 */       sb.append(r.toString(false)).append(",");
/*     */     }
/* 180 */     sb.append("}");
/* 181 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(boolean isFull)
/*     */   {
/* 189 */     if (isFull) {
/* 190 */       return toString();
/*     */     }
/* 192 */     StringBuilder sb = new StringBuilder();
/* 193 */     sb.append("[FunctionPo::oid:").append(this.oid);
/* 194 */     sb.append(",functionName:").append(this.functionName).append("]");
/* 195 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\po\FunctionPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */