/*    */ package com.iisi.opd.log.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.log.dao.DataServiceLogDao;
/*    */ import com.iisi.opd.log.po.DataServiceLogPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataServiceLogDaoImpl
/*    */   extends GenericDaoImpl<DataServiceLogPo>
/*    */   implements DataServiceLogDao
/*    */ {
/*    */   public void removeByDataSetOid(String dataSetOid)
/*    */   {
/* 24 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataServiceLogPo.class);
/* 25 */     criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
/*    */     
/*    */ 
/* 28 */     List<DataServiceLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
/* 29 */     deleteAll(removeList);
/*    */   }
/*    */   
/*    */ 
/*    */   public void removeByDataCfgOid(String dataCfgOid)
/*    */   {
/* 35 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataServiceLogPo.class);
/* 36 */     criteria.add(Restrictions.eq("dataCfgOid", dataCfgOid));
/*    */     
/*    */ 
/* 39 */     List<DataServiceLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
/* 40 */     deleteAll(removeList);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\impl\DataServiceLogDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */