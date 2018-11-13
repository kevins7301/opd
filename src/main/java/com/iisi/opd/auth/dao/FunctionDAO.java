package com.iisi.opd.auth.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.auth.po.FunctionPo;
import com.iisi.opd.exception.OpdException;
import java.util.List;

public abstract interface FunctionDAO
  extends GenericDao<FunctionPo>
{
  public abstract FunctionPo findByFunctionName(String paramString);
  
  public abstract List<FunctionPo> findByLikeFunctionName(String paramString1, String paramString2)
    throws OpdException;
  
  public abstract List<FunctionPo> findAllFunctionGroups();
  
  public abstract List<FunctionPo> findAllNonFunctionGroups();
  
  public abstract FunctionPo findByAccessPath(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\FunctionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */