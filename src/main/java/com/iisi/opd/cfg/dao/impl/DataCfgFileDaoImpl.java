/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataCfgFileDao;
/*    */ import com.iisi.opd.cfg.po.DataCfgFilePo;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataCfgFileDaoImpl
/*    */   extends GenericDaoImpl<DataCfgFilePo>
/*    */   implements DataCfgFileDao
/*    */ {
/*    */   public DataCfgFilePo findByDataCfgOid(String oid)
/*    */   {
/* 20 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgFilePo.class);
/* 21 */     criteria.add(Restrictions.eq("dataCfgPo.oid", oid));
/* 22 */     List<?> list = getHibernateTemplate().findByCriteria(criteria);
/* 23 */     DataCfgFilePo po = null;
/*    */     
/*    */ 
/* 26 */     if (list.size() == 1) {
/* 27 */       po = (DataCfgFilePo)list.get(0);
/*    */     }
/* 29 */     return po;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataCfgFileDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */