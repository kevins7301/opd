/*    */ package com.iisi.opd.cfg.service.impl;
/*    */ 
/*    */ import com.iisi.opd.cfg.dao.DataCfgFile2Dao;
/*    */ import com.iisi.opd.cfg.po.DataCfgFile2Po;
/*    */ import com.iisi.opd.cfg.po.DataCfgPo;
/*    */ import com.iisi.opd.cfg.service.DataCfgFile2Service;
/*    */ import com.iisi.opd.cfg.service.DataCfgService;
/*    */ import java.io.File;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DataCfgFile2ServiceImpl
/*    */   implements DataCfgFile2Service
/*    */ {
/* 20 */   protected final Logger logger = LoggerFactory.getLogger(getClass());
/*    */   
/*    */   @Autowired
/*    */   private DataCfgFile2Dao dataCfgFile2Dao;
/*    */   
/*    */   @Autowired
/*    */   private DataCfgService dataCfgServiceImpl;
/*    */   
/*    */   public void saveArgsByDataCfgOid(String dataCfgOid, String fileName, long fileSize, byte[] fileContent)
/*    */   {
/* 30 */     DataCfgFile2Po dataCfgFile2Po = this.dataCfgFile2Dao.findByDataCfgOid(dataCfgOid);
/*    */     
/*    */ 
/* 33 */     if (dataCfgFile2Po == null) {
/* 34 */       DataCfgPo dataCfgPo = this.dataCfgServiceImpl.findByOid(dataCfgOid);
/* 35 */       dataCfgFile2Po = new DataCfgFile2Po();
/* 36 */       dataCfgFile2Po.setDataCfgPo(dataCfgPo);
/* 37 */       dataCfgFile2Po = (DataCfgFile2Po)this.dataCfgFile2Dao.save(dataCfgFile2Po);
/*    */     }
/*    */     
/* 40 */     dataCfgFile2Po.setName(fileName);
/* 41 */     dataCfgFile2Po.setSize(fileSize);
/*    */     
/* 43 */     this.dataCfgFile2Dao.savePoWithBlob(dataCfgFile2Po, fileContent);
/*    */   }
/*    */   
/*    */ 
/*    */   public void saveArgsByDataCfgOid(String dataCfgOid, String fileName, long fileSize, File file)
/*    */   {
/* 49 */     DataCfgFile2Po dataCfgFile2Po = this.dataCfgFile2Dao.findByDataCfgOid(dataCfgOid);
/*    */     
/*    */ 
/* 52 */     if (dataCfgFile2Po == null) {
/* 53 */       DataCfgPo dataCfgPo = this.dataCfgServiceImpl.findByOid(dataCfgOid);
/* 54 */       dataCfgFile2Po = new DataCfgFile2Po();
/* 55 */       dataCfgFile2Po.setDataCfgPo(dataCfgPo);
/* 56 */       dataCfgFile2Po = (DataCfgFile2Po)this.dataCfgFile2Dao.save(dataCfgFile2Po);
/*    */     }
/*    */     
/* 59 */     dataCfgFile2Po.setName(fileName);
/* 60 */     dataCfgFile2Po.setSize(fileSize);
/*    */     
/* 62 */     this.dataCfgFile2Dao.savePoWithBlob(dataCfgFile2Po, file);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\impl\DataCfgFile2ServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */