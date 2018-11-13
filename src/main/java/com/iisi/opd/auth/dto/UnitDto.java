/*    */ package com.iisi.opd.auth.dto;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class UnitDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String oid;
/*    */   private UnitDto parent;
/*    */   private String unitPathName;
/*    */   private String unitName;
/*    */   private String unitAlias;
/*    */   private List<UnitDto> otherMonitorUnits;
/*    */   private boolean isRoot;
/*    */   
/*    */   public UnitDto()
/*    */   {
/* 22 */     this.otherMonitorUnits = new ArrayList();
/*    */   }
/*    */   
/*    */   public UnitDto getParent() {
/* 26 */     return this.parent;
/*    */   }
/*    */   
/* 29 */   public void setParent(UnitDto parent) { this.parent = parent; }
/*    */   
/*    */   public String getOid() {
/* 32 */     return this.oid;
/*    */   }
/*    */   
/* 35 */   public void setOid(String oid) { this.oid = oid; }
/*    */   
/*    */   public String getUnitPathName() {
/* 38 */     return this.unitPathName;
/*    */   }
/*    */   
/* 41 */   public void setUnitPathName(String unitPathName) { this.unitPathName = unitPathName; }
/*    */   
/*    */   public String getUnitName() {
/* 44 */     return this.unitName;
/*    */   }
/*    */   
/* 47 */   public void setUnitName(String unitName) { this.unitName = unitName; }
/*    */   
/*    */   public String getUnitAlias() {
/* 50 */     return this.unitAlias;
/*    */   }
/*    */   
/* 53 */   public void setUnitAlias(String unitAlias) { this.unitAlias = unitAlias; }
/*    */   
/*    */   public List<UnitDto> getOtherMonitorUnits() {
/* 56 */     return this.otherMonitorUnits;
/*    */   }
/*    */   
/* 59 */   public void setOtherMonitorUnits(List<UnitDto> otherMonitorUnits) { this.otherMonitorUnits = otherMonitorUnits; }
/*    */   
/*    */   public boolean isRoot()
/*    */   {
/* 63 */     return this.isRoot;
/*    */   }
/*    */   
/*    */   public void setRoot(boolean isRoot) {
/* 67 */     this.isRoot = isRoot;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dto\UnitDto.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */