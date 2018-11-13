package com.iisi.opd.log.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.log.po.DataServiceLogPo;

public abstract interface DataServiceLogDao
  extends GenericDao<DataServiceLogPo>
{
  public abstract void removeByDataSetOid(String paramString);
  
  public abstract void removeByDataCfgOid(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\DataServiceLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */