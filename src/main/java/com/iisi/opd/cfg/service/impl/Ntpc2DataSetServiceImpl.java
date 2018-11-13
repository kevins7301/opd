/*    */ package com.iisi.opd.cfg.service.impl;
/*    */ 
/*    */ import com.iisi.common.util.Pager;
/*    */ import com.iisi.opd.cfg.dao.Ntpc2DataSetDao;
/*    */ import com.iisi.opd.cfg.service.Ntpc2DataSetService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class Ntpc2DataSetServiceImpl
/*    */   implements Ntpc2DataSetService
/*    */ {
/*    */   @Autowired
/*    */   private Ntpc2DataSetDao ntpc2DataSetDao;
/*    */   
/*    */   public Pager getAllDataSetApplyStatus(Pager pager)
/*    */   {
/* 22 */     return this.ntpc2DataSetDao.getAllDataSetApplyStatus(pager);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\impl\Ntpc2DataSetServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */