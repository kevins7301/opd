package com.iisi.opd.search.service;

import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.po.DataSetPo.OrderByOption;
import java.util.Date;
import java.util.List;

public abstract interface Ntpc2DataSearchService
{
  public abstract List<DataSetPo> dataSetMultiCollectionSearch(List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract List<DataSetPo> dataSetMultiCollectionSearch(List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, String paramString, List<String> paramList3, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager dataSetMultiCollectionSearch(Pager paramPager, List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager dataSetMultiCollectionSearch(Pager paramPager, List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, String paramString, List<String> paramList3, DataSetPo.OrderByOption paramOrderByOption);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\search\service\Ntpc2DataSearchService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */