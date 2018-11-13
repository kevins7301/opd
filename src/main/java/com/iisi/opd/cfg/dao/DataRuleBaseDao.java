package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.po.DataRuleBasePo;
import java.util.List;
import java.util.Map;

public abstract interface DataRuleBaseDao
  extends GenericDao<DataRuleBasePo>
{
  public abstract List<DataRuleBasePo> findAllOrderByRuleId();
  
  public abstract Map<String, DataRuleBasePo> getDataRuleBasePoMap();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataRuleBaseDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */