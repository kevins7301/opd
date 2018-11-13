/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataCfgVerDao;
/*    */ import com.iisi.opd.cfg.po.DataCfgVerPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataCfgVerDaoImpl
/*    */   extends GenericDaoImpl<DataCfgVerPo>
/*    */   implements DataCfgVerDao
/*    */ {
/*    */   public List<DataCfgVerPo> findVerByDataCfgOid(String dataCfgOid)
/*    */   {
/* 23 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgVerPo.class);
/* 24 */     criteria.createCriteria("dataCfgPo");
/* 25 */     criteria.add(Restrictions.eq("dataCfgPo.oid", dataCfgOid));
/* 26 */     criteria.addOrder(Order.desc("logTime"));
/* 27 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataCfgVerDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */