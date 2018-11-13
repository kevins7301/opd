package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.po.DataCfgApplyPo;
import java.util.List;

public abstract interface DataCfgApplyDao
  extends GenericDao<DataCfgApplyPo>
{
  public abstract List<DataCfgApplyPo> findAllApplied();
  
  public abstract List<DataCfgApplyPo> findAllRefused();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataCfgApplyDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */