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
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="auth_unit")
/*     */ public class UnitPo
/*     */ {
/*     */   public static final String ROOT_PARENT_ID = "ROOT";
/*     */   @Id
/*     */   @Column(name="oid", length=36)
/*     */   @GenericGenerator(name="generator", strategy="guid")
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="parent_unit_id", length=100, nullable=false)
/*     */   private String parentUnitID;
/*     */   @Column(name="unit_path_name", length=100, nullable=false)
/*     */   private String unitPathName;
/*     */   @Column(name="unit_name", length=100, nullable=false)
/*     */   private String unitName;
/*     */   @Column(name="unit_alias", length=100, nullable=true)
/*     */   private String unitAlias;
/*     */   @ManyToMany(cascade={javax.persistence.CascadeType.ALL})
/*     */   @JoinTable(name="auth_unit_monitor_unit", joinColumns={@javax.persistence.JoinColumn(name="unit_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="monitor_unit_id")})
/*  41 */   private List<UnitPo> otherMonitorUnits = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @ManyToMany(mappedBy="otherMonitorUnits")
/*  47 */   private List<UnitPo> monitoredUnits = new ArrayList();
/*     */   
/*     */   public String getParentUnitID()
/*     */   {
/*  51 */     return this.parentUnitID;
/*     */   }
/*     */   
/*  54 */   public void setParentUnitID(String parentUnitID) { this.parentUnitID = parentUnitID; }
/*     */   
/*     */   public String getOid() {
/*  57 */     return this.oid;
/*     */   }
/*     */   
/*  60 */   public void setOid(String oid) { this.oid = oid; }
/*     */   
/*     */   public String getUnitPathName() {
/*  63 */     return this.unitPathName;
/*     */   }
/*     */   
/*  66 */   public void setUnitPathName(String unitPathName) { this.unitPathName = unitPathName; }
/*     */   
/*     */   public String getUnitName() {
/*  69 */     return this.unitName;
/*     */   }
/*     */   
/*  72 */   public void setUnitName(String unitName) { this.unitName = unitName; }
/*     */   
/*     */   public String getUnitAlias() {
/*  75 */     return this.unitAlias;
/*     */   }
/*     */   
/*  78 */   public void setUnitAlias(String unitAlias) { this.unitAlias = unitAlias; }
/*     */   
/*     */   public List<UnitPo> getOtherMonitorUnits() {
/*  81 */     return this.otherMonitorUnits;
/*     */   }
/*     */   
/*     */   public void setOtherMonitorUnits(List<UnitPo> otherMonitorUnits) {
/*  85 */     if (otherMonitorUnits == null) {
/*  86 */       otherMonitorUnits = new ArrayList();
/*     */     }
/*  88 */     for (UnitPo u : this.otherMonitorUnits) {
/*  89 */       u.getMonitoredUnits().remove(this);
/*     */     }
/*  91 */     this.otherMonitorUnits = otherMonitorUnits;
/*  92 */     for (UnitPo u : this.otherMonitorUnits)
/*  93 */       u.getMonitoredUnits().add(this);
/*     */   }
/*     */   
/*     */   public void removeOtherMonitorUnit(UnitPo unitPo) {
/*  97 */     this.otherMonitorUnits.remove(unitPo);
/*  98 */     unitPo.getMonitoredUnits().remove(this);
/*     */   }
/*     */   
/* 101 */   public void addOtherMonitorUnit(UnitPo unitPo) { if (!this.otherMonitorUnits.contains(unitPo)) {
/* 102 */       this.otherMonitorUnits.add(unitPo);
/* 103 */       unitPo.getMonitoredUnits().add(this);
/*     */     }
/*     */   }
/*     */   
/* 107 */   public List<UnitPo> getMonitoredUnits() { return this.monitoredUnits; }
/*     */   
/*     */   public void setMonitoredUnits(List<UnitPo> monitoredUnits)
/*     */   {
/* 111 */     if (monitoredUnits == null) {
/* 112 */       monitoredUnits = new ArrayList();
/*     */     }
/* 114 */     for (UnitPo u : this.monitoredUnits) {
/* 115 */       u.getOtherMonitorUnits().remove(this);
/*     */     }
/* 117 */     this.monitoredUnits = monitoredUnits;
/* 118 */     for (UnitPo u : this.monitoredUnits)
/* 119 */       u.getOtherMonitorUnits().add(this);
/*     */   }
/*     */   
/*     */   public void removeMonitoredUnit(UnitPo unitPo) {
/* 123 */     this.monitoredUnits.remove(unitPo);
/* 124 */     unitPo.getOtherMonitorUnits().remove(this);
/*     */   }
/*     */   
/* 127 */   public void addMonitoredUnit(UnitPo unitPo) { if (!this.monitoredUnits.contains(unitPo)) {
/* 128 */       this.monitoredUnits.add(unitPo);
/* 129 */       unitPo.getOtherMonitorUnits().add(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 134 */     StringBuilder sb = new StringBuilder();
/* 135 */     sb.append("UnitPo::oid:").append(this.oid);
/* 136 */     sb.append(",unitName:").append(this.unitName);
/* 137 */     sb.append(",unitAlias:").append(this.unitAlias);
/* 138 */     sb.append(",parentUnitID:").append(this.parentUnitID);
/* 139 */     sb.append(",unitPathName:").append(this.unitPathName);
/* 140 */     sb.append(",otherMonitorUnits:{");
/* 141 */     for (UnitPo u : this.otherMonitorUnits) {
/* 142 */       sb.append(u.toString(false)).append(",");
/*     */     }
/* 144 */     sb.append("}");
/* 145 */     sb.append(",monitoredUnits:{");
/* 146 */     for (UnitPo u : this.monitoredUnits) {
/* 147 */       sb.append(u.toString(false)).append(",");
/*     */     }
/* 149 */     sb.append("}");
/* 150 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(boolean isFull)
/*     */   {
/* 158 */     if (isFull) {
/* 159 */       return toString();
/*     */     }
/* 161 */     StringBuilder sb = new StringBuilder();
/* 162 */     sb.append("[UnitPo::oid:").append(this.oid);
/* 163 */     sb.append(",unitName:").append(this.unitName).append("]");
/* 164 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\po\UnitPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */