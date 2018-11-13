package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.po.DataSetVerPo;
import java.util.List;

public abstract interface DataSetVerDao
  extends GenericDao<DataSetVerPo>
{
  public abstract List<DataSetVerPo> findVerByDataSetOid(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataSetVerDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */