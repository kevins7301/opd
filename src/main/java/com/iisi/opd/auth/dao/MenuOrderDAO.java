package com.iisi.opd.auth.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.auth.po.MenuOrderPo;
import java.util.List;

public abstract interface MenuOrderDAO
  extends GenericDao<MenuOrderPo>
{
  public abstract List<MenuOrderPo> findChildOrderInfo(String paramString);
  
  public abstract void deleteChildsByParentID(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\MenuOrderDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */