/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataCfgApplyDao;
/*    */ import com.iisi.opd.cfg.po.DataCfgApplyPo;
/*    */ import com.iisi.opd.cfg.po.DataCfgApplyPo.DataStatus;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataCfgApplyDaoImpl
/*    */   extends GenericDaoImpl<DataCfgApplyPo>
/*    */   implements DataCfgApplyDao
/*    */ {
/*    */   public List<DataCfgApplyPo> findAllApplied()
/*    */   {
/* 22 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgApplyPo.class);
/* 23 */     criteria.add(Restrictions.eq("dataStatus", DataCfgApplyPo.DataStatus.APPLY));
/* 24 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<DataCfgApplyPo> findAllRefused()
/*    */   {
/* 30 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgApplyPo.class);
/* 31 */     criteria.add(Restrictions.eq("dataStatus", DataCfgApplyPo.DataStatus.REFUSE));
/* 32 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataCfgApplyDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */