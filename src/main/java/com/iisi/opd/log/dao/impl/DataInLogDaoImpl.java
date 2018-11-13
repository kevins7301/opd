/*    */ package com.iisi.opd.log.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.log.dao.DataInLogDao;
/*    */ import com.iisi.opd.log.po.DataInLogPo;
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
/*    */ public class DataInLogDaoImpl
/*    */   extends GenericDaoImpl<DataInLogPo>
/*    */   implements DataInLogDao
/*    */ {
/*    */   public void removeByDataSetOid(String dataSetOid)
/*    */   {
/* 24 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataInLogPo.class);
/* 25 */     criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
/*    */     
/*    */ 
/* 28 */     List<DataInLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
/* 29 */     deleteAll(removeList);
/*    */   }
/*    */   
/*    */ 
/*    */   public void removeByDataCfgOid(String dataCfgOid)
/*    */   {
/* 35 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataInLogPo.class);
/* 36 */     criteria.add(Restrictions.eq("dataCfgOid", dataCfgOid));
/*    */     
/*    */ 
/* 39 */     List<DataInLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
/* 40 */     deleteAll(removeList);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\impl\DataInLogDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */