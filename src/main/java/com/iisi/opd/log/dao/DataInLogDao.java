package com.iisi.opd.log.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.log.po.DataInLogPo;

public abstract interface DataInLogDao
  extends GenericDao<DataInLogPo>
{
  public abstract void removeByDataSetOid(String paramString);
  
  public abstract void removeByDataCfgOid(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\DataInLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */