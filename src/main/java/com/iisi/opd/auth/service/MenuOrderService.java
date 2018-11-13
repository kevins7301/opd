package com.iisi.opd.auth.service;

import com.iisi.opd.auth.dto.FunctionDto;
import com.iisi.opd.auth.dto.RoleDto;
import com.iisi.opd.auth.dto.UserDto;
import com.iisi.opd.auth.po.MenuOrderPo;
import java.util.List;

public abstract interface MenuOrderService
{
  public abstract void doOrder(UserDto paramUserDto);
  
  public abstract void doOrder(RoleDto paramRoleDto);
  
  public abstract void doOrder(FunctionDto paramFunctionDto);
  
  public abstract void deleteChildsByParentID(String paramString);
  
  public abstract List<MenuOrderPo> findChildOrderInfo(String paramString);
  
  public abstract MenuOrderPo add(MenuOrderPo paramMenuOrderPo);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\service\MenuOrderService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */