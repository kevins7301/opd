package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.po.DataSetApplyPo;
import com.iisi.opd.cfg.po.DataSetApplyPo.ApplyOrderByOption;
import java.util.List;

public abstract interface DataSetApplyDao
  extends GenericDao<DataSetApplyPo>
{
  public abstract Pager findAllApplied(Pager paramPager, DataSetApplyPo.ApplyOrderByOption paramApplyOrderByOption);
  
  public abstract List<DataSetApplyPo> findAllApplied();
  
  public abstract Pager findAllRefused(Pager paramPager, DataSetApplyPo.ApplyOrderByOption paramApplyOrderByOption);
  
  public abstract List<DataSetApplyPo> findAllRefused();
  
  public abstract int countByCategoryOid(String paramString);
  
  public abstract int countByTagOid(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataSetApplyDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */