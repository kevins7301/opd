/*    */ package com.iisi.opd.cfg.service.impl;
/*    */ 
/*    */ import com.iisi.opd.cfg.dao.DataRuleBaseDao;
/*    */ import com.iisi.opd.cfg.po.DataRuleBasePo;
/*    */ import com.iisi.opd.cfg.service.DataRuleBaseService;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DataRuleBaseServiceImpl
/*    */   implements DataRuleBaseService
/*    */ {
/*    */   @Autowired
/*    */   private DataRuleBaseDao dataRuleBaseDao;
/*    */   
/*    */   public List<DataRuleBasePo> findAllOrderByRuleId()
/*    */   {
/* 24 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataRuleBasePo.class);
/* 25 */     criteria.addOrder(Order.asc("ruleId"));
/* 26 */     return this.dataRuleBaseDao.findAllOrderByRuleId();
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\impl\DataRuleBaseServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */