/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataSetVerDao;
/*    */ import com.iisi.opd.cfg.po.DataSetVerPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class DataSetVerDaoImpl extends GenericDaoImpl<DataSetVerPo> implements DataSetVerDao
/*    */ {
/*    */   public List<DataSetVerPo> findVerByDataSetOid(String dataSetOid)
/*    */   {
/* 18 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataSetVerPo.class);
/* 19 */     criteria.createCriteria("dataSetPo");
/* 20 */     criteria.add(Restrictions.eq("dataSetPo.oid", dataSetOid));
/* 21 */     criteria.addOrder(Order.desc("logTime"));
/* 22 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataSetVerDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */