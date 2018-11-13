/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataCfgTableInfoDao;
/*    */ import com.iisi.opd.cfg.po.DataCfgTableInfoPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataCfgTableInfoDaoImpl
/*    */   extends GenericDaoImpl<DataCfgTableInfoPo>
/*    */   implements DataCfgTableInfoDao
/*    */ {
/*    */   public DataCfgTableInfoPo findByDataCfgOid(String oid)
/*    */   {
/* 21 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgTableInfoPo.class);
/* 22 */     criteria.add(Restrictions.eq("dataCfgPo.oid", oid));
/* 23 */     List<?> list = getHibernateTemplate().findByCriteria(criteria);
/* 24 */     DataCfgTableInfoPo po = null;
/*    */     
/*    */ 
/* 27 */     if (list.size() == 1) {
/* 28 */       po = (DataCfgTableInfoPo)list.get(0);
/*    */     }
/* 30 */     return po;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataCfgTableInfoDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */