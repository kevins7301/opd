/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataRuleBaseDao;
/*    */ import com.iisi.opd.cfg.po.DataRuleBasePo;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataRuleBaseDaoImpl
/*    */   extends GenericDaoImpl<DataRuleBasePo>
/*    */   implements DataRuleBaseDao
/*    */ {
/*    */   public List<DataRuleBasePo> findAllOrderByRuleId()
/*    */   {
/* 24 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataRuleBasePo.class);
/* 25 */     criteria.addOrder(Order.asc("ruleId"));
/* 26 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */   
/*    */   public Map<String, DataRuleBasePo> getDataRuleBasePoMap()
/*    */   {
/* 31 */     List<DataRuleBasePo> list = findAll(DataRuleBasePo.class);
/* 32 */     Map<String, DataRuleBasePo> map = new HashMap();
/* 33 */     for (DataRuleBasePo po : list) {
/* 34 */       map.put(po.getRuleId(), po);
/*    */     }
/* 36 */     return map;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataRuleBaseDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */