package com.iisi.opd.cfg.dao;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.GenericDao;
import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.po.DataSetPo.OrderByOption;
import java.util.List;

public abstract interface DataSetDao
  extends GenericDao<DataSetPo>
{
  public abstract List<DataSetPo> categorySearch(String paramString);
  
  public abstract List<DataSetPo> tagSearch(String paramString);
  
  public abstract List<DataSetPo> lastSearch();
  
  public abstract Pager lastSearch(Pager paramPager);
  
  public abstract List<DataSetPo> advancedSearch(String paramString1, String paramString2, String paramString3, IntervalBean paramIntervalBean, String paramString4);
  
  public abstract List<DataSetPo> advancedSearch(String paramString1, String paramString2, String paramString3, IntervalBean paramIntervalBean, String paramString4, List<String> paramList);
  
  public abstract Pager advancedSearch(Pager paramPager, String paramString1, String paramString2, String paramString3, IntervalBean paramIntervalBean, String paramString4, List<String> paramList);
  
  public abstract List<DataSetPo> findHide();
  
  public abstract List<DataSetPo> findPublicDataSet();
  
  public abstract List<DataSetPo> findPublicDataSetOrderByCfgResourceModifiedDate();
  
  public abstract Pager findPublicDataSet(Pager paramPager);
  
  public abstract List<DataSetPo> findAll();
  
  public abstract List<DataSetPo> keywordSearch(String paramString);
  
  public abstract List<DataSetPo> keywordSearch(String paramString, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager keywordSearch(Pager paramPager, String paramString);
  
  public abstract Pager keywordSearch(Pager paramPager, String paramString, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager findAllAgreed(Pager paramPager, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract List<DataSetPo> findAllAgreed();
  
  public abstract List<DataSetPo> dataSetMultiCollectionSearch(List<String> paramList1, List<String> paramList2, IntervalBean paramIntervalBean, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract List<DataSetPo> dataSetMultiCollectionSearch(List<String> paramList1, List<String> paramList2, IntervalBean paramIntervalBean, String paramString, List<String> paramList3, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager dataSetMultiCollectionSearch(Pager paramPager, List<String> paramList1, List<String> paramList2, IntervalBean paramIntervalBean, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager dataSetMultiCollectionSearch(Pager paramPager, List<String> paramList1, List<String> paramList2, IntervalBean paramIntervalBean, String paramString, List<String> paramList3, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract List<Object[]> getDataSetOidAndCfgResourceModifiedDate();
  
  public abstract Pager dataSetHotList(Pager paramPager);
  
  public abstract List<DataSetPo> dataSetHotList();
  
  public abstract List<DataSetPo> findAllEnable();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataSetDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */