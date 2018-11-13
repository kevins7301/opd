/*     */ package com.iisi.opd.cfg.service.impl;
/*     */ 
/*     */ import com.iisi.common.util.ZipUtils;
/*     */ import com.iisi.opd.cfg.dao.DataCfgDao;
/*     */ import com.iisi.opd.cfg.dao.DataCfgZipFileDao;
/*     */ import com.iisi.opd.cfg.dto.DataCfgZipFileDto;
/*     */ import com.iisi.opd.cfg.po.DataCfgPo;
/*     */ import com.iisi.opd.cfg.po.DataCfgZipFilePo;
/*     */ import com.iisi.opd.cfg.service.DataCfgService;
/*     */ import com.iisi.opd.cfg.service.DataCfgZipFileService;
/*     */ import com.iisi.opd.data.out.service.DataOutService;
/*     */ import com.iisi.opd.exception.OpdException;
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class DataCfgZipFileServiceImpl
/*     */   implements DataCfgZipFileService
/*     */ {
/*  43 */   protected final Logger logger = LoggerFactory.getLogger(getClass());
/*     */   
/*     */   @Autowired
/*     */   private DataCfgZipFileDao dataCfgZipFileDao;
/*     */   
/*     */   @Autowired
/*     */   private DataCfgDao dataCfgDao;
/*     */   
/*     */   @Autowired
/*     */   private DataOutService dataOutService;
/*     */   
/*     */   @Autowired
/*     */   private DataCfgService dataCfgService;
/*     */   
/*     */   @Autowired
/*     */   private ZipUtils ZipUtils;
/*     */   
/*     */   @Resource(name="sourceTypeList")
/*     */   private List<String> sourceTypeList;
/*     */   
/*     */   private static final int BUFFER_SIZE = 1000;
/*     */   public static final String UTF8_BOM = "﻿";
/*     */   
/*     */   public List<DataCfgZipFilePo> findByDataCfgOid(String dataCfgOid)
/*     */   {
/*  68 */     return this.dataCfgZipFileDao.findByDataCfgOid(dataCfgOid);
/*     */   }
/*     */   
/*     */ 
/*     */   public List<DataCfgZipFilePo> findByDataCfgOid(String dataCfgOid, String sourceType)
/*     */   {
/*  74 */     return this.dataCfgZipFileDao.findByDataCfgOid(dataCfgOid, sourceType);
/*     */   }
/*     */   
/*     */   public DataCfgZipFilePo findById(String zipFileOid)
/*     */   {
/*  79 */     return (DataCfgZipFilePo)this.dataCfgZipFileDao.findById(zipFileOid);
/*     */   }
/*     */   
/*     */   public DataCfgZipFilePo save(DataCfgZipFilePo dataCfgZipFilePo)
/*     */   {
/*  84 */     return (DataCfgZipFilePo)this.dataCfgZipFileDao.save(dataCfgZipFilePo);
/*     */   }
/*     */   
/*     */ 
/*     */   public Map<String, List<DataCfgZipFilePo>> getZipFileMapByDataCfgOid(String dataCfgOid)
/*     */   {
/*  90 */     Map<String, List<DataCfgZipFilePo>> sourceTypeToZipFilePoMap = new HashMap();
/*     */     
/*     */ 
/*     */ 
/*  94 */     for (String key : this.sourceTypeList) {
/*  95 */       List<DataCfgZipFilePo> dataCfgZipFilePoList = this.dataCfgZipFileDao.findByDataCfgOid(dataCfgOid, key);
/*     */       
/*  97 */       sourceTypeToZipFilePoMap.put(key, dataCfgZipFilePoList);
/*     */     }
/*  99 */     return sourceTypeToZipFilePoMap;
/*     */   }
/*     */   
/*     */   public void prepareZipFile(DataCfgPo dataCfgPo, int dataSize)
/*     */   {
/* 104 */     for (String type : this.sourceTypeList) {
/* 105 */       prepareZipFile(dataCfgPo, dataSize, type);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void prepareZipFile(DataCfgPo dataCfgPo, int dataSize, String sourceType)
/*     */   {
/* 112 */     dataCfgPo = this.dataCfgService.findByOid(dataCfgPo.getOid());
/*     */     
/* 114 */     if (dataCfgPo == null)
/*     */     {
/* 116 */       throw new OpdException(ErrorCodeEnum.ERR_2080001_EXCEPTION);
/*     */     }
/*     */     
/*     */ 
/* 120 */     String baseFileName = dataCfgPo.getName();
/*     */     
/* 122 */     standardPrepareZiFile(dataCfgPo, dataSize, baseFileName, sourceType.toLowerCase());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void prepareZipFile(DataCfgPo dataCfgPo, int dataSize, String fileName, String sourceType)
/*     */   {
/* 130 */     dataCfgPo = this.dataCfgService.findByOid(dataCfgPo.getOid());
/*     */     
/* 132 */     if (dataCfgPo == null)
/*     */     {
/* 134 */       throw new OpdException(ErrorCodeEnum.ERR_2080001_EXCEPTION);
/*     */     }
/* 136 */     standardPrepareZiFile(dataCfgPo, dataSize, fileName, sourceType.toLowerCase());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void standardPrepareZiFile(DataCfgPo dataCfgPo, int dataSize, String fileName, String sourceType)
/*     */   {
/* 155 */     BufferedInputStream bufferInputStream = null;
/* 156 */     FileInputStream fileInputStream = null;
/*     */     
/* 158 */     String dataCfgOid = dataCfgPo.getOid();
/*     */     
/* 160 */     int totalDataCount = dataCfgPo.getDataCount();
/*     */     
/* 162 */     List<DataCfgZipFilePo> dataCfgZipFilePoList = dataCfgPo.getDataCfgZipFilePoList();
/*     */     
/*     */     try
/*     */     {
/* 166 */       int i = 0; for (int packageId = 0; i < totalDataCount; packageId++)
/*     */       {
/* 168 */         String zipFileName = String.format("%1$s_%2$03d", new Object[] { fileName, Integer.valueOf(packageId) });
/*     */         
/* 170 */         String srcFileName = String.format("%1$s.%2$s", new Object[] { zipFileName, sourceType });
/*     */         
/* 172 */         this.logger.info("建立暫存壓縮檔案:{}", zipFileName);
/* 173 */         File srcFile = new File(srcFileName);
/* 174 */         this.logger.info("建立暫存原始檔案:{}", srcFileName);
/* 175 */         File zipFile = new File(zipFileName);
/*     */         
/* 177 */         if ("csv".equals(sourceType))
/*     */         {
/* 179 */           FileOutputStream fos = new FileOutputStream(srcFile);
/* 180 */           OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
/*     */           
/* 182 */           osw.write("﻿");
/* 183 */           osw.close();
/* 184 */           fos.close();
/*     */         }
/*     */         
/* 187 */         this.logger.info("進行分批 資料批次擷取作業");
/* 188 */         for (int j = 0; (j < dataSize) && (i + j < totalDataCount); j += 1000) {
/* 189 */           int roundMaxSize = 1000;
/* 190 */           if ((i + j + 1000 > dataSize) && (dataSize > 1000))
/*     */           {
/* 192 */             roundMaxSize = dataSize - 1000;
/* 193 */           } else if (dataSize < 1000)
/* 194 */             roundMaxSize = dataSize; else {
/* 195 */             if (i + j >= dataSize)
/*     */               break;
/*     */           }
/* 198 */           this.logger.info("批次擷取:當前 idx:{}, maxSize:{}", String.valueOf(i + j), String.valueOf(roundMaxSize));
/*     */           
/*     */ 
/* 201 */           List<Map<String, Object>> dataList = this.dataOutService.getData(dataCfgOid, null, null, String.valueOf(i + j), String.valueOf(roundMaxSize), null, null);
/*     */           
/*     */ 
/*     */ 
/* 205 */           boolean isAppend = (j != 0) && (!dataList.isEmpty());
/* 206 */           String fileContent = this.dataOutService.getOutputData(sourceType, dataList, isAppend);
/*     */           
/* 208 */           fileContent = new String(fileContent.getBytes("UTF-8"), "UTF-8");
/*     */           
/*     */ 
/* 211 */           FileOutputStream fos = new FileOutputStream(srcFile, true);
/* 212 */           BufferedOutputStream bos = new BufferedOutputStream(fos);
/* 213 */           OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");
/*     */           
/* 215 */           osw.write(fileContent);
/* 216 */           osw.close();
/* 217 */           bos.close();
/* 218 */           fos.close();
/* 219 */           this.logger.info("批次擷取:將本次內容 附加到 {} 內", srcFile.getName());
/*     */         }
/* 221 */         this.logger.info("結束分批 資料批次擷取作業");
/*     */         
/*     */ 
/* 224 */         Map<String, Object> contentMap = this.ZipUtils.zipProcess(srcFile, zipFile);
/*     */         
/*     */ 
/* 227 */         fileInputStream = new FileInputStream(zipFile);
/* 228 */         bufferInputStream = new BufferedInputStream(fileInputStream);
/*     */         
/*     */ 
/* 231 */         DataCfgZipFilePo dataCfgZipFilePo = new DataCfgZipFilePo();
/* 232 */         dataCfgZipFilePo.setContentFile(this.dataCfgZipFileDao.createBlob(bufferInputStream, zipFile.length()));
/*     */         
/* 234 */         dataCfgZipFilePo.setMd5(contentMap.get("md5").toString());
/* 235 */         dataCfgZipFilePo.setDataCfgPo(dataCfgPo);
/* 236 */         dataCfgZipFilePo.setSourceType(sourceType);
/* 237 */         dataCfgZipFilePo.setZipFileName(zipFileName + ".zip");
/* 238 */         this.logger.info("儲存作業:本次作業檔案大小 {}KB", Long.valueOf(zipFile.length() / 1024L));
/* 239 */         this.logger.info("儲存作業:進行寫入DB作業", srcFile.getName());
/* 240 */         dataCfgZipFilePo = save(dataCfgZipFilePo);
/* 241 */         this.logger.info("儲存作業:本次作業完成", srcFile.getName());
/*     */         
/* 243 */         dataCfgZipFilePoList.add(dataCfgZipFilePo);
/*     */         
/* 245 */         bufferInputStream.close();
/* 246 */         fileInputStream.close();
/*     */         
/*     */ 
/* 249 */         this.logger.info("刪除暫存壓縮檔案:{}", zipFile.getName());
/* 250 */         FileUtils.deleteQuietly(zipFile);
/* 251 */         this.logger.info("刪除暫存原始檔案:{}", srcFile.getName());
/* 252 */         FileUtils.deleteQuietly(srcFile);i += dataSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 256 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     } catch (FileNotFoundException e) {
/* 258 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     } catch (IOException e) {
/* 260 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     }
/*     */     
/* 263 */     this.dataCfgService.update(dataCfgPo);
/*     */   }
/*     */   
/*     */ 
/*     */   public void processSingleFile(DataCfgPo dataCfgPo, byte[] fileContent, String fileName, String sourceType, boolean isCoverOldFile)
/*     */   {
/* 269 */     dataCfgPo = this.dataCfgService.findByOid(dataCfgPo.getOid());
/*     */     
/* 271 */     sourceType = sourceType.toLowerCase();
/* 272 */     String fullFileName = fileName + "." + sourceType;
/*     */     
/* 274 */     File file = new File(fullFileName);
/*     */     try
/*     */     {
/* 277 */       FileUtils.writeByteArrayToFile(file, fileContent);
/*     */     } catch (IOException e) {
/* 279 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     }
/*     */     
/* 282 */     processSingleFile(dataCfgPo, file, sourceType, isCoverOldFile);
/*     */     
/*     */ 
/* 285 */     FileUtils.deleteQuietly(file);
/*     */   }
/*     */   
/*     */ 
/*     */   public void processSingleFile(DataCfgPo dataCfgPo, File file, String sourceType, boolean isCoverOldFile)
/*     */   {
/* 291 */     dataCfgPo = this.dataCfgService.findByOid(dataCfgPo.getOid());
/* 292 */     List<DataCfgZipFilePo> dataCfgZipFilePoList = dataCfgPo.getDataCfgZipFilePoList();
/*     */     
/*     */ 
/* 295 */     sourceType = sourceType.toLowerCase();
/* 296 */     BufferedInputStream bufferInputStream = null;
/* 297 */     FileInputStream FileInputStream = null;
/*     */     try
/*     */     {
/* 300 */       FileInputStream = new FileInputStream(file);
/* 301 */       bufferInputStream = new BufferedInputStream(FileInputStream);
/*     */       
/* 303 */       if (isCoverOldFile) {
/* 304 */         boolean isMatch = false;
/*     */         
/* 306 */         for (DataCfgZipFilePo targetPo : dataCfgZipFilePoList)
/*     */         {
/*     */ 
/* 309 */           if ((targetPo.getSourceType().equals(sourceType)) && (targetPo.getZipFileName().equals(file.getName())))
/*     */           {
/*     */ 
/* 312 */             targetPo.setContentFile(this.dataCfgZipFileDao.createBlob(bufferInputStream, file.length()));
/*     */             
/*     */ 
/* 315 */             targetPo.setMd5("");
/* 316 */             this.dataCfgZipFileDao.save(targetPo);
/* 317 */             isMatch = true;
/* 318 */             break;
/*     */           }
/*     */         }
/* 321 */         if (!isMatch) {
/* 322 */           DataCfgZipFilePo dataCfgZipFilePo = standardCfgZipFileProcess(dataCfgPo, file, bufferInputStream, sourceType);
/*     */           
/*     */ 
/* 325 */           dataCfgZipFilePo.setDataCfgPo(dataCfgPo);
/*     */           
/* 327 */           dataCfgZipFilePo = (DataCfgZipFilePo)this.dataCfgZipFileDao.save(dataCfgZipFilePo);
/* 328 */           dataCfgZipFilePoList.add(dataCfgZipFilePo);
/*     */         }
/*     */       }
/*     */       else {
/* 332 */         DataCfgZipFilePo dataCfgZipFilePo = standardCfgZipFileProcess(dataCfgPo, file, bufferInputStream, sourceType);
/*     */         
/*     */ 
/* 335 */         dataCfgZipFilePo.setDataCfgPo(dataCfgPo);
/*     */         
/* 337 */         dataCfgZipFilePo = (DataCfgZipFilePo)this.dataCfgZipFileDao.save(dataCfgZipFilePo);
/* 338 */         dataCfgZipFilePoList.add(dataCfgZipFilePo);
/*     */       }
/* 340 */       bufferInputStream.close();
/* 341 */       FileInputStream.close();
/*     */     } catch (SQLException e) {
/* 343 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     } catch (FileNotFoundException e) {
/* 345 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     } catch (IOException e) {
/* 347 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     }
/*     */   }
/*     */   
/*     */   private DataCfgZipFilePo standardCfgZipFileProcess(DataCfgPo dataCfgPo, File file, BufferedInputStream bufferInputStream, String sourceType)
/*     */   {
/* 353 */     DataCfgZipFilePo dataCfgZipFilePo = new DataCfgZipFilePo();
/*     */     try
/*     */     {
/* 356 */       dataCfgZipFilePo.setContentFile(this.dataCfgZipFileDao.createBlob(bufferInputStream, file.length()));
/*     */     }
/*     */     catch (SQLException e) {
/* 359 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     } catch (IOException e) {
/* 361 */       throw new OpdException(ErrorCodeEnum.DEFAULT_EXCEPTION, e);
/*     */     }
/*     */     
/* 364 */     dataCfgZipFilePo.setMd5("");
/*     */     
/* 366 */     dataCfgZipFilePo.setSourceType(sourceType);
/*     */     
/* 368 */     dataCfgZipFilePo.setZipFileName(file.getName());
/* 369 */     return dataCfgZipFilePo;
/*     */   }
/*     */   
/*     */   public List<DataCfgZipFileDto> getDataCfgZipFileDtoList(String dataCfgOid)
/*     */   {
/* 374 */     return this.dataCfgZipFileDao.getDataCfgZipFileDtoList(dataCfgOid);
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\impl\DataCfgZipFileServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */