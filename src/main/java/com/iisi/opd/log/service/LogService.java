package com.iisi.opd.log.service;

import com.iisi.common.util.Pager;
import com.iisi.opd.log.po.CfgTrackingLogPo;
import com.iisi.opd.log.po.DataAccessLogPo;
import com.iisi.opd.log.po.DataAccessRejectLogPo;
import com.iisi.opd.log.po.DataCheckResultPo;
import com.iisi.opd.log.po.DataFileLogPo;
import com.iisi.opd.log.po.DataInLogPo;
import com.iisi.opd.log.po.DataOutLogPo;
import com.iisi.opd.log.po.DataServiceLogPo;
import com.iisi.opd.log.po.SiteAccessLogPo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;

public abstract interface LogService
{
  public abstract CfgTrackingLogPo add(CfgTrackingLogPo paramCfgTrackingLogPo);
  
  public abstract CfgTrackingLogPo findCfgTrackingLogPoByOid(String paramString);
  
  public abstract List<CfgTrackingLogPo> findAllCfgTrackingLogPo();
  
  public abstract List<CfgTrackingLogPo> findCfgTrackingLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract DataAccessLogPo add(DataAccessLogPo paramDataAccessLogPo);
  
  public abstract DataAccessLogPo findDataAccessLogPoByOid(String paramString);
  
  public abstract List<DataAccessLogPo> findAllDataAccessLogPo();
  
  public abstract List<DataAccessLogPo> findDataAccessLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract Map<String, Long> getDataSetAccessViewStatistic(Date paramDate1, Date paramDate2);
  
  public abstract DataAccessRejectLogPo add(DataAccessRejectLogPo paramDataAccessRejectLogPo);
  
  public abstract DataAccessRejectLogPo findDataAccessRejectLogPoByOid(String paramString);
  
  public abstract List<DataAccessRejectLogPo> findAllDataAccessRejectLogPo();
  
  public abstract List<DataAccessRejectLogPo> findDataAccessRejectLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract Map<String, Long> getDataSetAccessRejectViewStatistic(Date paramDate1, Date paramDate2);
  
  public abstract DataCheckResultPo add(DataCheckResultPo paramDataCheckResultPo);
  
  public abstract DataCheckResultPo findDataCheckResultPoByOid(String paramString);
  
  public abstract List<DataCheckResultPo> findAllDataCheckResultPo();
  
  public abstract List<DataCheckResultPo> findDataCheckResultPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract DataFileLogPo add(DataFileLogPo paramDataFileLogPo);
  
  public abstract DataFileLogPo findDataFileLogPoByOid(String paramString);
  
  public abstract List<DataFileLogPo> findAllDataFileLogPo();
  
  public abstract List<DataFileLogPo> findDataFileLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract Map<String, Long[]> getDataFileDownloadCountStatistic(Date paramDate1, Date paramDate2);
  
  public abstract DataInLogPo add(DataInLogPo paramDataInLogPo);
  
  public abstract DataInLogPo addWithCheckResult(DataInLogPo paramDataInLogPo, DataCheckResultPo paramDataCheckResultPo);
  
  public abstract DataInLogPo findDataInLogPoByOid(String paramString);
  
  public abstract List<DataInLogPo> findAllDataInLogPo();
  
  public abstract List<DataInLogPo> findDataInLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract DataOutLogPo add(DataOutLogPo paramDataOutLogPo);
  
  public abstract DataOutLogPo findDataOutLogPoByOid(String paramString);
  
  public abstract List<DataOutLogPo> findAllDataOutLogPo();
  
  public abstract List<DataOutLogPo> findDataOutLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract Map<String, Map<String, Long>> getDataOutCountStatistic(Date paramDate1, Date paramDate2);
  
  public abstract Map<String, Long> getDataOutCountWithNoTypeStatistic(Date paramDate1, Date paramDate2);
  
  public abstract DataServiceLogPo add(DataServiceLogPo paramDataServiceLogPo);
  
  public abstract DataServiceLogPo findDataServiceLogPoByOid(String paramString);
  
  public abstract List<DataServiceLogPo> findAllDataServiceLogPo();
  
  public abstract List<DataServiceLogPo> findDataServiceLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract SiteAccessLogPo add(SiteAccessLogPo paramSiteAccessLogPo);
  
  public abstract SiteAccessLogPo findSiteAccessLogPoByOid(String paramString);
  
  public abstract List<SiteAccessLogPo> findAllSiteAccessLogPo();
  
  public abstract Pager findAllSiteAccessLogPo(Pager paramPager);
  
  public abstract Pager findAllSiteAccessLogPo(Pager paramPager, Date paramDate1, Date paramDate2);
  
  public abstract Pager findAdmSiteAccessLog(Pager paramPager, Date paramDate1, Date paramDate2);
  
  public abstract List<SiteAccessLogPo> findSiteAccessLogPoByCriteria(DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findSiteAccessLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract List<Object[]> findSiteAccessLogCountData(Date paramDate1, Date paramDate2);
  
  public abstract void removeLogByDataSetOid(String paramString);
  
  public abstract void removeLogByDataCfgOid(String paramString);
  
  public abstract Pager findAllCfgTrackingLogPo(Pager paramPager);
  
  public abstract Pager findCfgTrackingLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findAllDataAccessLogPo(Pager paramPager);
  
  public abstract Pager findDataAccessLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findAllDataAccessRejectLogPo(Pager paramPager);
  
  public abstract Pager findDataAccessRejectLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findAllDataCheckResultPo(Pager paramPager);
  
  public abstract Pager findDataCheckResultPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findAllDataFileLogPo(Pager paramPager);
  
  public abstract Pager findDataFileLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findAllDataInLogPo(Pager paramPager);
  
  public abstract Pager findDataInLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findAllDataOutLogPo(Pager paramPager);
  
  public abstract Pager findDataOutLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract Pager findAllDataServiceLogPo(Pager paramPager);
  
  public abstract Pager findDataServiceLogPoByCriteria(Pager paramPager, DetachedCriteria paramDetachedCriteria);
  
  public abstract int getRowCount(DetachedCriteria paramDetachedCriteria);
  
  public abstract List<?> getProjectionsQueryList(DetachedCriteria paramDetachedCriteria);
  
  public abstract List<?> getNativeSqlQueryList(String paramString);
  
  public abstract List<?> getNativeSqlQueryList(String paramString, Object[] paramArrayOfObject);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\service\LogService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */