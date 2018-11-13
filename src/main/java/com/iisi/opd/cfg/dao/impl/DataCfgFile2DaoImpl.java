/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataCfgFile2Dao;
/*    */ import com.iisi.opd.cfg.po.DataCfgFile2Po;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.sql.Blob;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.hibernate.Hibernate;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class DataCfgFile2DaoImpl
/*    */   extends GenericDaoImpl<DataCfgFile2Po>
/*    */   implements DataCfgFile2Dao
/*    */ {
/*    */   public DataCfgFile2Po findByDataCfgOid(String oid)
/*    */   {
/* 27 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgFile2Po.class);
/* 28 */     criteria.add(Restrictions.eq("dataCfgPo.oid", oid));
/* 29 */     List<?> list = getHibernateTemplate().findByCriteria(criteria);
/*    */     
/* 31 */     return (DataCfgFile2Po)getSigleObject(list);
/*    */   }
/*    */   
/*    */   public void savePoWithBlob(DataCfgFile2Po dataCfgFile2Po, byte[] fileContent)
/*    */   {
/* 36 */     if ((fileContent == null) || (fileContent.length == 0)) {
/* 37 */       return;
/*    */     }
/*    */     
/* 40 */     Session session = getCurrentSession();
/* 41 */     Blob blob = Hibernate.createBlob(fileContent, session);
/* 42 */     dataCfgFile2Po.setContent(blob);
/*    */     
/* 44 */     create(dataCfgFile2Po);
/*    */     
/* 46 */     flush();
/*    */   }
/*    */   
/*    */   public void savePoWithBlob(DataCfgFile2Po dataCfgFile2Po, File file)
/*    */   {
/* 51 */     if (file == null) {
/* 52 */       return;
/*    */     }
/* 54 */     Blob blob = null;
/* 55 */     BufferedInputStream bif = null;
/*    */     try
/*    */     {
/* 58 */       Session session = getCurrentSession();
/* 59 */       bif = new BufferedInputStream(new FileInputStream(file));
/* 60 */       blob = Hibernate.createBlob(bif, file.length(), session);
/* 61 */       dataCfgFile2Po.setContent(blob);
/*    */       
/* 63 */       create(dataCfgFile2Po);
/*    */       
/* 65 */       flush();
/*    */     } catch (IOException e) {
/* 67 */       this.logger.error(e.getMessage(), e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataCfgFile2DaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */