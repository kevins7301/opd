package com.iisi.opd.auth.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.auth.po.RolePo;
import java.util.List;

public abstract interface RoleDAO
  extends GenericDao<RolePo>
{
  public abstract RolePo findByRoleName(String paramString);
  
  public abstract List<RolePo> findByNameAndStatus(String paramString, Boolean paramBoolean);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\RoleDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */