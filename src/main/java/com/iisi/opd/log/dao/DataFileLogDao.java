package com.iisi.opd.log.dao;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.GenericDao;
import com.iisi.opd.log.po.DataFileLogPo;
import java.util.Map;

public abstract interface DataFileLogDao
  extends GenericDao<DataFileLogPo>
{
  public abstract void removeByDataSetOid(String paramString);
  
  public abstract void removeByDataCfgOid(String paramString);
  
  public abstract Map<String, Long[]> getDataFileDownloadCountStatistic(IntervalBean paramIntervalBean);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\DataFileLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */