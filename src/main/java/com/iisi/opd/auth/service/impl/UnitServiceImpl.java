/*    */ package com.iisi.opd.auth.service.impl;
/*    */ 
/*    */ import com.iisi.opd.auth.dao.UnitDAO;
/*    */ import com.iisi.opd.auth.po.UnitPo;
/*    */ import com.iisi.opd.auth.service.UnitService;
/*    */ import com.iisi.opd.exception.OpdException;
/*    */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class UnitServiceImpl
/*    */   implements UnitService
/*    */ {
/* 24 */   public static final Logger log = LoggerFactory.getLogger(UnitServiceImpl.class);
/*    */   
/*    */   @Autowired
/*    */   UnitDAO unitDAO;
/*    */   
/*    */   public UnitPo add(UnitPo po)
/*    */   {
/* 31 */     return (UnitPo)this.unitDAO.save(po);
/*    */   }
/*    */   
/*    */   public void delete(UnitPo po)
/*    */   {
/* 36 */     this.unitDAO.delete(po);
/*    */   }
/*    */   
/*    */ 
/*    */   public void update(UnitPo po)
/*    */   {
/* 42 */     this.unitDAO.update(po);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<UnitPo> findAll()
/*    */   {
/* 48 */     return this.unitDAO.findAll(UnitPo.class);
/*    */   }
/*    */   
/*    */   public UnitPo findByOid(String oid)
/*    */   {
/* 53 */     UnitPo po = (UnitPo)this.unitDAO.findById(oid);
/* 54 */     log.debug("UnitPo：", po);
/* 55 */     if ((null == po) || (po.equals(new UnitPo()))) {
/* 56 */       throw new OpdException(ErrorCodeEnum.ERR_1001000_EXCEPTION);
/*    */     }
/* 58 */     return po;
/*    */   }
/*    */   
/*    */ 
/*    */   public UnitPo findByUnitName(String name)
/*    */   {
/* 64 */     UnitPo po = this.unitDAO.findByUnitName(name);
/* 65 */     log.debug("UnitPo：", po);
/* 66 */     if ((null == po) || (po.equals(new UnitPo()))) {
/* 67 */       throw new OpdException(ErrorCodeEnum.ERR_1001000_EXCEPTION);
/*    */     }
/* 69 */     return po;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\service\impl\UnitServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */