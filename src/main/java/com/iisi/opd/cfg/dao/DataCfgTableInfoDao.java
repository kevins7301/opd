package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.po.DataCfgTableInfoPo;

public abstract interface DataCfgTableInfoDao
  extends GenericDao<DataCfgTableInfoPo>
{
  public abstract DataCfgTableInfoPo findByDataCfgOid(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataCfgTableInfoDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */