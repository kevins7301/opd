/*    */ package com.iisi.opd.cfg.service.impl;
/*    */ 
/*    */ import com.iisi.opd.cfg.dao.DataCfgTableInfoDao;
/*    */ import com.iisi.opd.cfg.po.DataCfgTableInfoPo;
/*    */ import com.iisi.opd.cfg.service.DataCfgTableInfoService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Isolation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional(isolation=Isolation.READ_UNCOMMITTED)
/*    */ public class DataCfgTableInfoServiceImpl
/*    */   implements DataCfgTableInfoService
/*    */ {
/*    */   @Autowired
/*    */   private DataCfgTableInfoDao dataCfgTableInfoDao;
/*    */   
/*    */   public DataCfgTableInfoPo findByDataCfgOid(String oid)
/*    */   {
/* 21 */     return this.dataCfgTableInfoDao.findByDataCfgOid(oid);
/*    */   }
/*    */   
/*    */   public DataCfgTableInfoPo save(DataCfgTableInfoPo dataCfgTableInfoPo)
/*    */   {
/* 26 */     return (DataCfgTableInfoPo)this.dataCfgTableInfoDao.save(dataCfgTableInfoPo);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\impl\DataCfgTableInfoServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */