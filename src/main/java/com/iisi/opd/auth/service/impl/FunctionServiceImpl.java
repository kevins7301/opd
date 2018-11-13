/*    */ package com.iisi.opd.auth.service.impl;
/*    */ 
/*    */ import com.iisi.opd.auth.dao.FunctionDAO;
/*    */ import com.iisi.opd.auth.po.FunctionPo;
/*    */ import com.iisi.opd.auth.service.FunctionService;
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
/*    */ public class FunctionServiceImpl
/*    */   implements FunctionService
/*    */ {
/* 24 */   public static final Logger log = LoggerFactory.getLogger(FunctionServiceImpl.class);
/*    */   
/*    */   @Autowired
/*    */   FunctionDAO functionDAO;
/*    */   
/*    */   public FunctionPo add(FunctionPo po)
/*    */   {
/* 31 */     return (FunctionPo)this.functionDAO.save(po);
/*    */   }
/*    */   
/*    */   public void delete(FunctionPo po)
/*    */   {
/* 36 */     this.functionDAO.delete(po);
/*    */   }
/*    */   
/*    */   public void update(FunctionPo po)
/*    */   {
/* 41 */     this.functionDAO.update(po);
/*    */   }
/*    */   
/*    */   public List<FunctionPo> findAll()
/*    */   {
/* 46 */     return this.functionDAO.findAll(FunctionPo.class);
/*    */   }
/*    */   
/*    */   public FunctionPo findByOid(String oid)
/*    */   {
/* 51 */     FunctionPo po = (FunctionPo)this.functionDAO.findById(oid);
/* 52 */     log.debug("FunctionPo：", po);
/* 53 */     if ((null == po) || (po.equals(new FunctionPo()))) {
/* 54 */       throw new OpdException(ErrorCodeEnum.ERR_1001000_EXCEPTION);
/*    */     }
/* 56 */     return po;
/*    */   }
/*    */   
/*    */ 
/*    */   public FunctionPo findByFunctionName(String name)
/*    */   {
/* 62 */     FunctionPo po = this.functionDAO.findByFunctionName(name);
/* 63 */     log.debug("FunctionPo：", po);
/* 64 */     if ((null == po) || (po.equals(new FunctionPo()))) {
/* 65 */       throw new OpdException(ErrorCodeEnum.ERR_1001000_EXCEPTION);
/*    */     }
/* 67 */     return po;
/*    */   }
/*    */   
/*    */ 
/*    */   public List<FunctionPo> findAllFunctionGroups()
/*    */   {
/* 73 */     return this.functionDAO.findAllFunctionGroups();
/*    */   }
/*    */   
/*    */   public List<FunctionPo> findAllNonFunctionGroups()
/*    */   {
/* 78 */     return this.functionDAO.findAllNonFunctionGroups();
/*    */   }
/*    */   
/*    */   public List<FunctionPo> findByLikeFunctionName(String groupName, String functionName) throws OpdException
/*    */   {
/* 83 */     return this.functionDAO.findByLikeFunctionName(groupName, functionName);
/*    */   }
/*    */   
/*    */   public FunctionPo findByAccessPath(String accessPath)
/*    */   {
/* 88 */     return this.functionDAO.findByAccessPath(accessPath);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\service\impl\FunctionServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */