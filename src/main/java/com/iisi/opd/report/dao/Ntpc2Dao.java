package com.iisi.opd.report.dao;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.GenericDao;
import com.iisi.common.util.Pager;
import com.iisi.opd.report.vo.LogAnalysisOptionsVo;
import java.util.List;

public abstract interface Ntpc2Dao
  extends GenericDao<Object>
{
  @Deprecated
  public abstract List<Object[]> getDataLogAnalysis(boolean paramBoolean, IntervalBean paramIntervalBean, LogAnalysisOptionsVo paramLogAnalysisOptionsVo);
  
  public abstract List<Object[]> getKindOfDataLogAnalysis(boolean paramBoolean, IntervalBean paramIntervalBean, LogAnalysisOptionsVo paramLogAnalysisOptionsVo);
  
  public abstract List<Object[]> getFreqOfDataLogAnalysis(IntervalBean paramIntervalBean, LogAnalysisOptionsVo paramLogAnalysisOptionsVo, List<String> paramList1, List<String> paramList2, int paramInt);
  
  public abstract List<Object[]> getDataUsedStatistic(IntervalBean paramIntervalBean, String paramString1, String paramString2);
  
  public abstract Pager getDataUsedSearch(Pager paramPager, IntervalBean paramIntervalBean, String paramString1, String paramString2, Boolean paramBoolean);
  
  public abstract List<Object[]> getDataInStatus(IntervalBean paramIntervalBean, String paramString1, String paramString2, String paramString3);
  
  public abstract List<Object[]> getDataInDiff(IntervalBean paramIntervalBean, String paramString1, String paramString2, String paramString3);
  
  public abstract Pager getDataInSearch(Pager paramPager, IntervalBean paramIntervalBean, String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, Boolean paramBoolean2);
  
  public abstract List<Object[]> getEachUnitDataStstisticStatus();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\dao\Ntpc2Dao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */