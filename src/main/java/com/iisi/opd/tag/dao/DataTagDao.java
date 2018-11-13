package com.iisi.opd.tag.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.tag.po.DataTagPo;
import java.util.List;

public abstract interface DataTagDao
  extends GenericDao<DataTagPo>
{
  public abstract List<DataTagPo> findAllwithDataSetCondition();
  
  public abstract DataTagPo findByName(String paramString);
  
  public abstract List<DataTagPo> dataTagSearch(String paramString);
  
  public abstract DataTagPo findByBlurredName(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\tag\dao\DataTagDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */