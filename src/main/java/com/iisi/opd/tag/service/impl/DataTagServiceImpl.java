/*    */ package com.iisi.opd.tag.service.impl;
/*    */ 
/*    */ import com.iisi.opd.cfg.dao.DataSetApplyDao;
/*    */ import com.iisi.opd.exception.OpdException;
/*    */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*    */ import com.iisi.opd.tag.dao.DataTagDao;
/*    */ import com.iisi.opd.tag.po.DataTagPo;
/*    */ import com.iisi.opd.tag.service.DataTagService;
/*    */ import java.util.List;
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
/*    */ public class DataTagServiceImpl
/*    */   implements DataTagService
/*    */ {
/*    */   @Autowired
/*    */   private DataTagDao dataTagDao;
/*    */   @Autowired
/*    */   private DataSetApplyDao dataSetApplyDao;
/*    */   
/*    */   public DataTagPo add(DataTagPo po)
/*    */   {
/* 30 */     return (DataTagPo)this.dataTagDao.save(po);
/*    */   }
/*    */   
/*    */   public void delete(DataTagPo po)
/*    */   {
/* 35 */     if (po.getDataSetPoList().size() == 0) {
/* 36 */       if (this.dataSetApplyDao.countByTagOid(po.getOid()) == 0) {
/* 37 */         this.dataTagDao.delete(po);
/*    */       } else {
/* 39 */         throw new OpdException(ErrorCodeEnum.ERR_2030002_EXCEPTION);
/*    */       }
/*    */     } else {
/* 42 */       throw new OpdException(ErrorCodeEnum.ERR_2030001_EXCEPTION);
/*    */     }
/*    */   }
/*    */   
/*    */   public void update(DataTagPo po)
/*    */   {
/* 48 */     this.dataTagDao.update(po);
/*    */   }
/*    */   
/*    */   public List<DataTagPo> findAll()
/*    */   {
/* 53 */     return this.dataTagDao.findAll(DataTagPo.class);
/*    */   }
/*    */   
/*    */   public List<DataTagPo> findAllwithDataSetCondition()
/*    */   {
/* 58 */     return this.dataTagDao.findAllwithDataSetCondition();
/*    */   }
/*    */   
/*    */   public DataTagPo findByOid(String oid)
/*    */   {
/* 63 */     return (DataTagPo)this.dataTagDao.findById(oid);
/*    */   }
/*    */   
/*    */   public DataTagPo findByName(String name)
/*    */   {
/* 68 */     return this.dataTagDao.findByName(name);
/*    */   }
/*    */   
/*    */   public DataTagPo findByBlurredName(String name)
/*    */   {
/* 73 */     return this.dataTagDao.findByBlurredName(name);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\tag\service\impl\DataTagServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */