/*    */ package com.iisi.opd.auth.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.auth.dao.UnitDAO;
/*    */ import com.iisi.opd.auth.po.UnitPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class UnitDAOImpl
/*    */   extends GenericDaoImpl<UnitPo>
/*    */   implements UnitDAO
/*    */ {
/*    */   public UnitPo findByUnitName(String name)
/*    */   {
/* 23 */     Criteria c = getSession().createCriteria(UnitPo.class).add(Restrictions.eq("unitName", name));
/*    */     
/* 25 */     List pos = c.list();
/* 26 */     if (pos.size() == 1) {
/* 27 */       return (UnitPo)pos.get(0);
/*    */     }
/* 29 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public void delete(Object o)
/*    */   {
/* 35 */     UnitPo unitPo = (UnitPo)o;
/* 36 */     List<UnitPo> units = unitPo.getMonitoredUnits();
/* 37 */     for (int i = 0; i < units.size(); i++) {
/* 38 */       ((UnitPo)units.get(i)).removeOtherMonitorUnit(unitPo);
/*    */     }
/* 40 */     units = unitPo.getOtherMonitorUnits();
/* 41 */     for (int i = 0; i < units.size(); i++) {
/* 42 */       ((UnitPo)units.get(i)).removeMonitoredUnit(unitPo);
/*    */     }
/*    */     
/* 45 */     unitPo.setMonitoredUnits(null);
/* 46 */     unitPo.setOtherMonitorUnits(null);
/*    */     
/* 48 */     super.delete(o);
/*    */   }
/*    */   
/*    */   public UnitPo findAuthUnit(String unitId)
/*    */   {
/* 53 */     DetachedCriteria criteria = DetachedCriteria.forClass(UnitPo.class);
/* 54 */     criteria.add(Restrictions.eq("unitID", unitId));
/*    */     
/* 56 */     List<?> list = getHibernateTemplate().findByCriteria(criteria);
/* 57 */     UnitPo po = null;
/*    */     
/* 59 */     if (list.size() == 1) {
/* 60 */       po = (UnitPo)list.get(0);
/* 61 */       return po;
/*    */     }
/*    */     
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\impl\UnitDAOImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */