package com.iisi.opd.auth.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.auth.po.UnitPo;

public abstract interface UnitDAO
  extends GenericDao<UnitPo>
{
  public abstract UnitPo findByUnitName(String paramString);
  
  public abstract UnitPo findAuthUnit(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\UnitDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */