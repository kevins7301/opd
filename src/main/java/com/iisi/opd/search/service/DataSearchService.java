package com.iisi.opd.search.service;

import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.po.DataSetPo.OrderByOption;
import com.iisi.opd.tag.po.DataTagPo;
import java.util.Date;
import java.util.List;

public abstract interface DataSearchService
{
  public abstract List<DataCfgPo> dataCfgCategorySearch(String paramString);
  
  public abstract List<DataSetPo> dataSetCategorySearch(String paramString);
  
  public abstract List<DataCfgPo> dataCfgTagSearch(String paramString);
  
  public abstract List<DataSetPo> dataSetTagSearch(String paramString);
  
  public abstract List<DataCfgPo> dataCfgLastSearch();
  
  public abstract List<DataSetPo> dataSetLastSearch();
  
  public abstract Pager dataSetLastSearch(Pager paramPager);
  
  public abstract List<DataCfgPo> dataCfgAdvancedSearch(String paramString1, String paramString2, String paramString3, Date paramDate1, Date paramDate2, String paramString4);
  
  public abstract List<DataSetPo> dataSetAdvancedSearch(String paramString1, String paramString2, String paramString3, Date paramDate1, Date paramDate2, String paramString4);
  
  public abstract List<DataSetPo> dataSetAdvancedSearch(String paramString1, String paramString2, String paramString3, Date paramDate1, Date paramDate2, String paramString4, List<String> paramList);
  
  public abstract Pager dataSetAdvancedSearch(Pager paramPager, String paramString1, String paramString2, String paramString3, Date paramDate1, Date paramDate2, String paramString4, List<String> paramList);
  
  public abstract List<DataCfgPo> dataCfgKeywordSearch(String paramString);
  
  public abstract Pager dataCfgKeywordSearch(Pager paramPager, String paramString);
  
  public abstract List<DataSetPo> dataSetKeywordSearch(String paramString);
  
  public abstract List<DataSetPo> dataSetKeywordSearch(String paramString, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager dataSetKeywordSearch(Pager paramPager, String paramString);
  
  public abstract Pager dataSetKeywordSearch(Pager paramPager, String paramString, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract List<DataSetPo> dataSetHotList();
  
  public abstract Pager dataSetHotList(Pager paramPager);
  
  public abstract List<DataCfgPo> dataCfgHotList();
  
  public abstract Pager dataCfgHotList(Pager paramPager);
  
  public abstract List<DataTagPo> dataTagSearch(String paramString);
  
  public abstract List<DataSetPo> dataSetMultiCollectionSearch(List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract List<DataSetPo> dataSetMultiCollectionSearch(List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, String paramString, List<String> paramList3, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager dataSetMultiCollectionSearch(Pager paramPager, List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract Pager dataSetMultiCollectionSearch(Pager paramPager, List<String> paramList1, List<String> paramList2, Date paramDate1, Date paramDate2, boolean paramBoolean, String paramString, List<String> paramList3, DataSetPo.OrderByOption paramOrderByOption);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\search\service\DataSearchService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */