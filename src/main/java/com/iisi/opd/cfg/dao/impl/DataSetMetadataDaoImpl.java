/*    */ package com.iisi.opd.cfg.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.cfg.dao.DataSetMetadataDao;
/*    */ import com.iisi.opd.cfg.po.DataSetMetadataPo;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class DataSetMetadataDaoImpl extends GenericDaoImpl<DataSetMetadataPo> implements DataSetMetadataDao
/*    */ {
/*    */   public void removeByDataSetOid(String oid)
/*    */   {
/* 15 */     Query query = getSession().createQuery("delete from DataSetMetadataPo where dataSetPo.oid = :oid");
/* 16 */     query.setString("oid", oid);
/* 17 */     query.executeUpdate();
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\impl\DataSetMetadataDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */