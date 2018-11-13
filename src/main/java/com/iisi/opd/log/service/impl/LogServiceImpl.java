/*     */ package com.iisi.opd.log.service.impl;
/*     */ 
/*     */ import com.iisi.common.bean.IntervalBean;
/*     */ import com.iisi.common.util.Pager;
/*     */ import com.iisi.opd.log.dao.CfgTrackingLogDao;
/*     */ import com.iisi.opd.log.dao.DataAccessLogDao;
/*     */ import com.iisi.opd.log.dao.DataAccessRejectLogDao;
/*     */ import com.iisi.opd.log.dao.DataCheckResultDao;
/*     */ import com.iisi.opd.log.dao.DataFileLogDao;
/*     */ import com.iisi.opd.log.dao.DataInLogDao;
/*     */ import com.iisi.opd.log.dao.DataOutLogDao;
/*     */ import com.iisi.opd.log.dao.DataServiceLogDao;
/*     */ import com.iisi.opd.log.dao.SiteAccessLogDao;
/*     */ import com.iisi.opd.log.po.CfgTrackingLogPo;
/*     */ import com.iisi.opd.log.po.DataAccessLogPo;
/*     */ import com.iisi.opd.log.po.DataAccessRejectLogPo;
/*     */ import com.iisi.opd.log.po.DataCheckResultPo;
/*     */ import com.iisi.opd.log.po.DataFileLogPo;
/*     */ import com.iisi.opd.log.po.DataInLogPo;
/*     */ import com.iisi.opd.log.po.DataOutLogPo;
/*     */ import com.iisi.opd.log.po.DataServiceLogPo;
/*     */ import com.iisi.opd.log.po.SiteAccessLogPo;
/*     */ import com.iisi.opd.log.service.LogService;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.hibernate.criterion.DetachedCriteria;
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
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class LogServiceImpl
/*     */   implements LogService
/*     */ {
/*  44 */   public static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);
/*     */   
/*     */   @Autowired
/*     */   private CfgTrackingLogDao cfgTrackingLogDao;
/*     */   
/*     */   @Autowired
/*     */   private DataAccessLogDao dataAccessLogDao;
/*     */   
/*     */   @Autowired
/*     */   private DataAccessRejectLogDao dataAccessRejectLogDao;
/*     */   
/*     */   @Autowired
/*     */   private DataCheckResultDao dataCheckResultDao;
/*     */   
/*     */   @Autowired
/*     */   private DataFileLogDao dataFileLogDao;
/*     */   
/*     */   @Autowired
/*     */   private DataInLogDao dataInLogDao;
/*     */   
/*     */   @Autowired
/*     */   private DataOutLogDao dataOutLogDao;
/*     */   
/*     */   @Autowired
/*     */   private DataServiceLogDao dataServiceLogDao;
/*     */   
/*     */   @Autowired
/*     */   private SiteAccessLogDao siteAccessLogDao;
/*     */   
/*     */   public CfgTrackingLogPo add(CfgTrackingLogPo cfgTrackingLogPo)
/*     */   {
/*  75 */     return (CfgTrackingLogPo)this.cfgTrackingLogDao.save(cfgTrackingLogPo);
/*     */   }
/*     */   
/*     */   public CfgTrackingLogPo findCfgTrackingLogPoByOid(String oid)
/*     */   {
/*  80 */     return (CfgTrackingLogPo)this.cfgTrackingLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<CfgTrackingLogPo> findAllCfgTrackingLogPo()
/*     */   {
/*  85 */     return this.cfgTrackingLogDao.findAll(CfgTrackingLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllCfgTrackingLogPo(Pager pager)
/*     */   {
/*  90 */     return this.cfgTrackingLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<CfgTrackingLogPo> findCfgTrackingLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/*  95 */     return this.cfgTrackingLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findCfgTrackingLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 100 */     return this.cfgTrackingLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public DataAccessLogPo add(DataAccessLogPo dataAccessLogPo)
/*     */   {
/* 105 */     return (DataAccessLogPo)this.dataAccessLogDao.save(dataAccessLogPo);
/*     */   }
/*     */   
/*     */   public DataAccessLogPo findDataAccessLogPoByOid(String oid)
/*     */   {
/* 110 */     return (DataAccessLogPo)this.dataAccessLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<DataAccessLogPo> findAllDataAccessLogPo()
/*     */   {
/* 115 */     return this.dataAccessLogDao.findAll(DataAccessLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllDataAccessLogPo(Pager pager)
/*     */   {
/* 120 */     return this.dataAccessLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<DataAccessLogPo> findDataAccessLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 125 */     return this.dataAccessLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findDataAccessLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 130 */     return this.dataAccessLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public Map<String, Long> getDataSetAccessViewStatistic(Date startDateTime, Date endDateTime)
/*     */   {
/* 135 */     IntervalBean interval = new IntervalBean("logTime", startDateTime, endDateTime);
/* 136 */     return this.dataAccessLogDao.getDataSetAccessViewStatistic(interval);
/*     */   }
/*     */   
/*     */   public DataAccessRejectLogPo add(DataAccessRejectLogPo dataAccessRejectLog)
/*     */   {
/* 141 */     return (DataAccessRejectLogPo)this.dataAccessRejectLogDao.save(dataAccessRejectLog);
/*     */   }
/*     */   
/*     */   public DataAccessRejectLogPo findDataAccessRejectLogPoByOid(String oid)
/*     */   {
/* 146 */     return (DataAccessRejectLogPo)this.dataAccessRejectLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<DataAccessRejectLogPo> findAllDataAccessRejectLogPo()
/*     */   {
/* 151 */     return this.dataAccessRejectLogDao.findAll(DataAccessRejectLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllDataAccessRejectLogPo(Pager pager)
/*     */   {
/* 156 */     return this.dataAccessRejectLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<DataAccessRejectLogPo> findDataAccessRejectLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 161 */     return this.dataAccessRejectLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findDataAccessRejectLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 166 */     return this.dataAccessRejectLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public Map<String, Long> getDataSetAccessRejectViewStatistic(Date startDateTime, Date endDateTime)
/*     */   {
/* 171 */     IntervalBean interval = new IntervalBean("logTime", startDateTime, endDateTime);
/* 172 */     return this.dataAccessRejectLogDao.getDataSetAccessRejectViewStatistic(interval);
/*     */   }
/*     */   
/*     */   public DataCheckResultPo add(DataCheckResultPo dataCheckResultPo)
/*     */   {
/* 177 */     return (DataCheckResultPo)this.dataCheckResultDao.save(dataCheckResultPo);
/*     */   }
/*     */   
/*     */   public DataCheckResultPo findDataCheckResultPoByOid(String oid)
/*     */   {
/* 182 */     return (DataCheckResultPo)this.dataCheckResultDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<DataCheckResultPo> findAllDataCheckResultPo()
/*     */   {
/* 187 */     return this.dataCheckResultDao.findAll(DataCheckResultPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllDataCheckResultPo(Pager pager)
/*     */   {
/* 192 */     return this.dataCheckResultDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<DataCheckResultPo> findDataCheckResultPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 197 */     return this.dataCheckResultDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findDataCheckResultPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 202 */     return this.dataCheckResultDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public DataFileLogPo add(DataFileLogPo dataFileLogPo)
/*     */   {
/* 207 */     return (DataFileLogPo)this.dataFileLogDao.save(dataFileLogPo);
/*     */   }
/*     */   
/*     */   public DataFileLogPo findDataFileLogPoByOid(String oid)
/*     */   {
/* 212 */     return (DataFileLogPo)this.dataFileLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<DataFileLogPo> findAllDataFileLogPo()
/*     */   {
/* 217 */     return this.dataFileLogDao.findAll(DataFileLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllDataFileLogPo(Pager pager)
/*     */   {
/* 222 */     return this.dataFileLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<DataFileLogPo> findDataFileLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 227 */     return this.dataFileLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findDataFileLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 232 */     return this.dataFileLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public Map<String, Long[]> getDataFileDownloadCountStatistic(Date startDateTime, Date endDateTime)
/*     */   {
/* 237 */     IntervalBean interval = new IntervalBean("logTime", startDateTime, endDateTime);
/* 238 */     return this.dataFileLogDao.getDataFileDownloadCountStatistic(interval);
/*     */   }
/*     */   
/*     */   public DataInLogPo add(DataInLogPo dataInLogPo)
/*     */   {
/* 243 */     return (DataInLogPo)this.dataInLogDao.save(dataInLogPo);
/*     */   }
/*     */   
/*     */   public DataInLogPo addWithCheckResult(DataInLogPo dataInLogPo, DataCheckResultPo dataCheckResultPo)
/*     */   {
/* 248 */     dataCheckResultPo = add(dataCheckResultPo);
/* 249 */     dataInLogPo.setCheckResultOid(dataCheckResultPo.getOid());
/* 250 */     return (DataInLogPo)this.dataInLogDao.save(dataInLogPo);
/*     */   }
/*     */   
/*     */   public DataInLogPo findDataInLogPoByOid(String oid)
/*     */   {
/* 255 */     return (DataInLogPo)this.dataInLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<DataInLogPo> findAllDataInLogPo()
/*     */   {
/* 260 */     return this.dataInLogDao.findAll(DataInLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllDataInLogPo(Pager pager)
/*     */   {
/* 265 */     return this.dataInLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<DataInLogPo> findDataInLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 270 */     return this.dataInLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findDataInLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 275 */     return this.dataInLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public DataOutLogPo add(DataOutLogPo DataOutLogPo)
/*     */   {
/* 280 */     return (DataOutLogPo)this.dataOutLogDao.save(DataOutLogPo);
/*     */   }
/*     */   
/*     */   public DataOutLogPo findDataOutLogPoByOid(String oid)
/*     */   {
/* 285 */     return (DataOutLogPo)this.dataOutLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<DataOutLogPo> findAllDataOutLogPo()
/*     */   {
/* 290 */     return this.dataOutLogDao.findAll(DataOutLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllDataOutLogPo(Pager pager)
/*     */   {
/* 295 */     return this.dataOutLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<DataOutLogPo> findDataOutLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 300 */     return this.dataOutLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findDataOutLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 305 */     return this.dataOutLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public Map<String, Map<String, Long>> getDataOutCountStatistic(Date startDateTime, Date endDateTime)
/*     */   {
/* 310 */     IntervalBean interval = new IntervalBean("logTime", startDateTime, endDateTime);
/* 311 */     return this.dataOutLogDao.getDataOutCountStatistic(interval);
/*     */   }
/*     */   
/*     */   public Map<String, Long> getDataOutCountWithNoTypeStatistic(Date startDateTime, Date endDateTime)
/*     */   {
/* 316 */     IntervalBean interval = new IntervalBean("logTime", startDateTime, endDateTime);
/* 317 */     return this.dataOutLogDao.getDataOutCountWithNoTypeStatistic(interval);
/*     */   }
/*     */   
/*     */   public DataServiceLogPo add(DataServiceLogPo dataServiveLogPo)
/*     */   {
/* 322 */     return (DataServiceLogPo)this.dataServiceLogDao.save(dataServiveLogPo);
/*     */   }
/*     */   
/*     */   public DataServiceLogPo findDataServiceLogPoByOid(String oid)
/*     */   {
/* 327 */     return (DataServiceLogPo)this.dataServiceLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<DataServiceLogPo> findAllDataServiceLogPo()
/*     */   {
/* 332 */     return this.dataServiceLogDao.findAll(DataServiceLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllDataServiceLogPo(Pager pager)
/*     */   {
/* 337 */     return this.dataServiceLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public List<DataServiceLogPo> findDataServiceLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 342 */     return this.dataServiceLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findDataServiceLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 347 */     return this.dataServiceLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public SiteAccessLogPo add(SiteAccessLogPo siteAccessLogPo)
/*     */   {
/* 352 */     return (SiteAccessLogPo)this.siteAccessLogDao.save(siteAccessLogPo);
/*     */   }
/*     */   
/*     */   public SiteAccessLogPo findSiteAccessLogPoByOid(String oid)
/*     */   {
/* 357 */     return (SiteAccessLogPo)this.siteAccessLogDao.findById(oid);
/*     */   }
/*     */   
/*     */   public List<Object[]> findSiteAccessLogCountData(Date start, Date end)
/*     */   {
/* 362 */     IntervalBean interval = new IntervalBean("logTime", start, end);
/* 363 */     return this.siteAccessLogDao.findSiteAccessLogCountData(interval);
/*     */   }
/*     */   
/*     */   public List<SiteAccessLogPo> findAllSiteAccessLogPo()
/*     */   {
/* 368 */     return this.siteAccessLogDao.findAll(SiteAccessLogPo.class);
/*     */   }
/*     */   
/*     */   public Pager findAllSiteAccessLogPo(Pager pager)
/*     */   {
/* 373 */     return this.siteAccessLogDao.getPager(pager, "logTime");
/*     */   }
/*     */   
/*     */   public Pager findAllSiteAccessLogPo(Pager pager, Date startTime, Date endTime)
/*     */   {
/* 378 */     IntervalBean interval = new IntervalBean("logTime", startTime, endTime);
/* 379 */     return this.siteAccessLogDao.getPagerByRange(pager, interval);
/*     */   }
/*     */   
/*     */   public Pager findAdmSiteAccessLog(Pager pager, Date startTime, Date endTime)
/*     */   {
/* 384 */     IntervalBean Interval = new IntervalBean("logTime", startTime, endTime);
/* 385 */     return this.siteAccessLogDao.getPagerByAdmRange(pager, Interval);
/*     */   }
/*     */   
/*     */   public List<SiteAccessLogPo> findSiteAccessLogPoByCriteria(DetachedCriteria criteria)
/*     */   {
/* 390 */     return this.siteAccessLogDao.findByCriteria(criteria);
/*     */   }
/*     */   
/*     */   public Pager findSiteAccessLogPoByCriteria(Pager pager, DetachedCriteria criteria)
/*     */   {
/* 395 */     return this.siteAccessLogDao.getPager(pager, criteria, "logTime");
/*     */   }
/*     */   
/*     */   public void removeLogByDataSetOid(String dataSetOid)
/*     */   {
/* 400 */     this.dataAccessLogDao.removeByDataSetOid(dataSetOid);
/* 401 */     this.dataAccessRejectLogDao.removeByDataSetOid(dataSetOid);
/* 402 */     this.dataFileLogDao.removeByDataSetOid(dataSetOid);
/* 403 */     this.dataInLogDao.removeByDataSetOid(dataSetOid);
/* 404 */     this.dataOutLogDao.removeByDataSetOid(dataSetOid);
/* 405 */     this.dataServiceLogDao.removeByDataSetOid(dataSetOid);
/*     */   }
/*     */   
/*     */   public void removeLogByDataCfgOid(String dataCfgOid) {
/* 409 */     this.dataAccessLogDao.removeByDataCfgOid(dataCfgOid);
/* 410 */     this.dataAccessRejectLogDao.removeByDataCfgOid(dataCfgOid);
/* 411 */     this.dataFileLogDao.removeByDataCfgOid(dataCfgOid);
/* 412 */     this.dataInLogDao.removeByDataCfgOid(dataCfgOid);
/* 413 */     this.dataOutLogDao.removeByDataCfgOid(dataCfgOid);
/* 414 */     this.dataServiceLogDao.removeByDataCfgOid(dataCfgOid);
/*     */   }
/*     */   
/*     */   public int getRowCount(DetachedCriteria criteria)
/*     */   {
/* 419 */     return this.dataServiceLogDao.getRowCount(criteria);
/*     */   }
/*     */   
/*     */   public List<?> getProjectionsQueryList(DetachedCriteria criteria)
/*     */   {
/* 424 */     return this.dataServiceLogDao.getProjectionsQueryList(criteria);
/*     */   }
/*     */   
/*     */   public List<?> getNativeSqlQueryList(String sql)
/*     */   {
/* 429 */     return this.dataServiceLogDao.getNativeSqlQueryList(sql);
/*     */   }
/*     */   
/*     */   public List<?> getNativeSqlQueryList(String sqlQuery, Object[] params)
/*     */   {
/* 434 */     return this.dataServiceLogDao.getNativeSqlQueryList(sqlQuery, params);
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\service\impl\LogServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */