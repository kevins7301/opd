package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.po.DataCfgVerPo;
import java.util.List;

public abstract interface DataCfgVerDao
  extends GenericDao<DataCfgVerPo>
{
  public abstract List<DataCfgVerPo> findVerByDataCfgOid(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataCfgVerDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */