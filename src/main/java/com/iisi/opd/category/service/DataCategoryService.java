package com.iisi.opd.category.service;

import com.iisi.opd.category.po.DataCategoryPo;
import java.util.List;

public abstract interface DataCategoryService
{
  public abstract DataCategoryPo add(DataCategoryPo paramDataCategoryPo);
  
  public abstract void delete(DataCategoryPo paramDataCategoryPo);
  
  public abstract void update(DataCategoryPo paramDataCategoryPo);
  
  public abstract List<DataCategoryPo> findAll();
  
  public abstract DataCategoryPo findByOid(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\category\service\DataCategoryService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */