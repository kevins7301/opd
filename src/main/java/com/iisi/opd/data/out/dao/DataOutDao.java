package com.iisi.opd.data.out.dao;

import com.iisi.opd.data.out.dto.SqlParamDto;
import java.util.List;
import java.util.Map;

public abstract interface DataOutDao
{
  public abstract List<String> getColumnName(String paramString);
  
  public abstract List<Map<String, Object>> getData(String paramString, Map<String, String> paramMap, List<SqlParamDto> paramList);
  
  public abstract List<Map<String, Object>> getData(String paramString, List<SqlParamDto> paramList);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\out\dao\DataOutDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */