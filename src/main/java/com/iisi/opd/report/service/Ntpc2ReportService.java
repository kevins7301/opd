package com.iisi.opd.report.service;

import com.iisi.common.util.Pager;
import com.iisi.opd.report.vo.LogAnalysisOptionsVo;
import java.util.Date;
import java.util.List;

public abstract interface Ntpc2ReportService
{
  @Deprecated
  public abstract List<Object[]> getDataLogAnalysis(boolean paramBoolean, Date paramDate1, Date paramDate2, LogAnalysisOptionsVo paramLogAnalysisOptionsVo);
  
  public abstract List<Object[]> getKindOfDataLogAnalysis(boolean paramBoolean, Date paramDate1, Date paramDate2, LogAnalysisOptionsVo paramLogAnalysisOptionsVo);
  
  public abstract List<Object[]> getFreqOfDataLogAnalysis(Date paramDate1, Date paramDate2, LogAnalysisOptionsVo paramLogAnalysisOptionsVo, List<String> paramList1, List<String> paramList2, int paramInt);
  
  public abstract List<Object[]> getDataUsedStatistic(Date paramDate1, Date paramDate2, String paramString1, String paramString2);
  
  public abstract Pager getDataUsedSearch(Pager paramPager, Date paramDate1, Date paramDate2, String paramString1, String paramString2, Boolean paramBoolean);
  
  public abstract List<Object[]> getDataInStatus(Date paramDate1, Date paramDate2, String paramString1, String paramString2, String paramString3);
  
  public abstract List<Object[]> getDataInDiff(Date paramDate1, Date paramDate2, String paramString1, String paramString2, String paramString3);
  
  public abstract Pager getDataInSearch(Pager paramPager, Date paramDate1, Date paramDate2, String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, Boolean paramBoolean2);
  
  public abstract List<Object[]> getEachUnitDataStstisticStatus();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\service\Ntpc2ReportService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */