package com.iisi.opd.report.service;

import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.log.po.DataInLogPo;
import com.iisi.opd.report.vo.DataFileDownloadCountVo;
import com.iisi.opd.report.vo.DataOutCountVo;
import com.iisi.opd.report.vo.DataServiceCountVo;
import com.iisi.opd.report.vo.DataSetViewVo;
import com.iisi.opd.report.vo.UnitCountVo;
import java.util.List;
import java.util.Map;

public abstract interface ReportService
{
  public abstract List<DataSetViewVo> getDataSetViewStatistic(java.sql.Date paramDate1, java.sql.Date paramDate2);
  
  public abstract List<DataFileDownloadCountVo> getDataFileDownloadCountStatistic(java.sql.Date paramDate1, java.sql.Date paramDate2);
  
  /**
   * @deprecated
   */
  public abstract List<DataServiceCountVo> getDataServiceCountStatistic(java.sql.Date paramDate1, java.sql.Date paramDate2);
  
  public abstract List<DataOutCountVo> getDataOutCountStatistic(java.sql.Date paramDate1, java.sql.Date paramDate2);
  
  public abstract List<UnitCountVo> getUnitCountStatistic(java.sql.Date paramDate1, java.sql.Date paramDate2);
  
  public abstract List<UnitCountVo> getUnitCountStatisticWithEnable(java.sql.Date paramDate1, java.sql.Date paramDate2);
  
  public abstract Map<String, Integer> getSiteAccessCountStatistic(java.util.Date paramDate1, java.util.Date paramDate2);
  
  public abstract Map<DataCfgPo, List<DataInLogPo>> getDataInLog(java.util.Date paramDate1, java.util.Date paramDate2, String paramString, Boolean paramBoolean)
    throws OpdException;
  
  public abstract UnitCountVo getDataSetSpecificStatistic(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\report\service\ReportService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */