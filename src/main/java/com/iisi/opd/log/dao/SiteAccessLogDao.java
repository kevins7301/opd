package com.iisi.opd.log.dao;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.GenericDao;
import com.iisi.common.util.Pager;
import com.iisi.opd.log.po.SiteAccessLogPo;
import java.util.List;

public abstract interface SiteAccessLogDao
  extends GenericDao<SiteAccessLogPo>
{
  public abstract List<Object[]> findSiteAccessLogCountData(IntervalBean paramIntervalBean);
  
  public abstract Pager getPagerByRange(Pager paramPager, IntervalBean paramIntervalBean);
  
  public abstract Pager getPagerByAdmRange(Pager paramPager, IntervalBean paramIntervalBean);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\SiteAccessLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */