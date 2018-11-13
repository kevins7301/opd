package com.iisi.opd.log.dao;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.GenericDao;
import com.iisi.opd.log.po.DataOutLogPo;
import java.util.Map;

public abstract interface DataOutLogDao
  extends GenericDao<DataOutLogPo>
{
  public abstract void removeByDataSetOid(String paramString);
  
  public abstract void removeByDataCfgOid(String paramString);
  
  public abstract Map<String, Map<String, Long>> getDataOutCountStatistic(IntervalBean paramIntervalBean);
  
  public abstract Map<String, Long> getDataOutCountWithNoTypeStatistic(IntervalBean paramIntervalBean);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\DataOutLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */