package com.iisi.opd.tag.service;

import com.iisi.opd.tag.po.DataTagPo;
import java.util.List;

public abstract interface DataTagService
{
  public abstract DataTagPo add(DataTagPo paramDataTagPo);
  
  public abstract void delete(DataTagPo paramDataTagPo);
  
  public abstract void update(DataTagPo paramDataTagPo);
  
  public abstract List<DataTagPo> findAll();
  
  public abstract List<DataTagPo> findAllwithDataSetCondition();
  
  public abstract DataTagPo findByOid(String paramString);
  
  public abstract DataTagPo findByName(String paramString);
  
  public abstract DataTagPo findByBlurredName(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\tag\service\DataTagService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */