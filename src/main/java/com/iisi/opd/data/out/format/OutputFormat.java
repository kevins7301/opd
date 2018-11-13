package com.iisi.opd.data.out.format;

import java.util.List;
import java.util.Map;

public abstract interface OutputFormat
{
  public abstract String getOutputData(List<Map<String, Object>> paramList)
    throws Exception;
  
  public abstract String getOutputData(List<Map<String, Object>> paramList, boolean paramBoolean)
    throws Exception;
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\out\format\OutputFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */