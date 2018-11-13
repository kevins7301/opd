package com.iisi.opd.auth.service;

import com.iisi.opd.auth.po.RolePo;
import java.util.List;

public abstract interface RoleService
{
  public abstract RolePo add(RolePo paramRolePo);
  
  public abstract void delete(RolePo paramRolePo);
  
  public abstract void update(RolePo paramRolePo);
  
  public abstract List<RolePo> findAll();
  
  public abstract RolePo findByOid(String paramString);
  
  public abstract RolePo findByRoleName(String paramString);
  
  public abstract List<RolePo> findByNameAndStatus(String paramString, Boolean paramBoolean);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\service\RoleService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */