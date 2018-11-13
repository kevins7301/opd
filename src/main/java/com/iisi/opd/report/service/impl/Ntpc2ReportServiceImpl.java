/*    */ package com.iisi.opd.report.service.impl;
/*    */ 
/*    */ import com.iisi.common.bean.IntervalBean;
/*    */ import com.iisi.common.util.Pager;
/*    */ import com.iisi.opd.report.dao.Ntpc2Dao;
/*    */ import com.iisi.opd.report.service.Ntpc2ReportService;
/*    */ import com.iisi.opd.report.vo.LogAnalysisOptionsVo;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class Ntpc2ReportServiceImpl
/*    */   implements Ntpc2ReportService
/*    */ {
/*    */   @Autowired
/*    */   private Ntpc2Dao ntpc2Dao;
/*    */   
/*    */   @Deprecated
/*    */   public List<Object[]> getDataLogAnalysis(boolean isAnalysisByUnit, Date startTime, Date endTime, LogAnalysisOptionsVo analysisOptionsVo)
/*    */   {
/* 31 */     IntervalBean interval = new IntervalBean("log_time", startTime, endTime);
/* 32 */     if (analysisOptionsVo == null)
/* 33 */       analysisOptionsVo = new LogAnalysisOptionsVo();
/* 34 */     return this.ntpc2Dao.getDataLogAnalysis(isAnalysisByUnit, interval, analysisOptionsVo);
/*    */   }
/*    */   
/*    */   public List<Object[]> getKindOfDataLogAnalysis(boolean isAnalysisByUnit, Date startTime, Date endTime, LogAnalysisOptionsVo analysisOptionsVo)
/*    */   {
/* 39 */     IntervalBean interval = new IntervalBean("log_time", startTime, endTime);
/* 40 */     if (analysisOptionsVo == null)
/* 41 */       analysisOptionsVo = new LogAnalysisOptionsVo();
/* 42 */     return this.ntpc2Dao.getKindOfDataLogAnalysis(isAnalysisByUnit, interval, analysisOptionsVo);
/*    */   }
/*    */   
/*    */   public List<Object[]> getFreqOfDataLogAnalysis(Date startTime, Date endTime, LogAnalysisOptionsVo analysisOptionsVo, List<String> unitOidList, List<String> dataSetOidList, int frequency)
/*    */   {
/* 47 */     IntervalBean interval = new IntervalBean("log_time", startTime, endTime);
/* 48 */     if (analysisOptionsVo == null)
/* 49 */       analysisOptionsVo = new LogAnalysisOptionsVo();
/* 50 */     return this.ntpc2Dao.getFreqOfDataLogAnalysis(interval, analysisOptionsVo, unitOidList, dataSetOidList, frequency);
/*    */   }
/*    */   
/*    */   public List<Object[]> getDataUsedStatistic(Date startTime, Date endTime, String unitOid, String dataSetName)
/*    */   {
/* 55 */     IntervalBean interval = new IntervalBean("log_time", startTime, endTime);
/* 56 */     return this.ntpc2Dao.getDataUsedStatistic(interval, unitOid, dataSetName);
/*    */   }
/*    */   
/*    */   public Pager getDataUsedSearch(Pager pager, Date startTime, Date endTime, String dataSetName, String ip, Boolean isPublic)
/*    */   {
/* 61 */     IntervalBean interval = new IntervalBean("log_time", startTime, endTime);
/* 62 */     return this.ntpc2Dao.getDataUsedSearch(pager, interval, dataSetName, ip, isPublic);
/*    */   }
/*    */   
/*    */   public List<Object[]> getDataInStatus(Date startTime, Date endTime, String unitOid, String dataSetName, String dataInType)
/*    */   {
/* 67 */     IntervalBean interval = new IntervalBean("ODS.create_time", startTime, endTime);
/* 68 */     return this.ntpc2Dao.getDataInStatus(interval, unitOid, dataSetName, dataInType);
/*    */   }
/*    */   
/*    */   public List<Object[]> getDataInDiff(Date startTime, Date endTime, String unitOid, String dataSetName, String dataInType)
/*    */   {
/* 73 */     IntervalBean interval = new IntervalBean("log_time", startTime, endTime);
/* 74 */     return this.ntpc2Dao.getDataInDiff(interval, unitOid, dataSetName, dataInType);
/*    */   }
/*    */   
/*    */   public Pager getDataInSearch(Pager pager, Date startTime, Date endTime, String unitOid, String dataSetName, String dataInType, Boolean execResult, Boolean isPublic)
/*    */   {
/* 79 */     IntervalBean interval = new IntervalBean("OLDI.log_time", startTime, endTime);
/* 80 */     return this.ntpc2Dao.getDataInSearch(pager, interval, unitOid, dataSetName, dataInType, execResult, isPublic);
/*    */   }
/*    */   
/*    */   public List<Object[]> getEachUnitDataStstisticStatus()
/*    */   {
/* 85 */     return this.ntpc2Dao.getEachUnitDataStstisticStatus();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static enum StatisticType
/*    */   {
/* 94 */     Null, 
/* 95 */     DayOfYear, 
/* 96 */     WeekOfYear, 
/* 97 */     MonthOfYear, 
/* 98 */     QuarterOfYear, 
/* 99 */     Year;
/*    */     
/*    */     private StatisticType() {}
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\service\impl\Ntpc2ReportServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */