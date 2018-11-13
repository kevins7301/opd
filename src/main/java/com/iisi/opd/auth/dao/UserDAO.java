package com.iisi.opd.auth.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.auth.po.UserPo;
import com.iisi.opd.exception.OpdException;
import java.util.List;

public abstract interface UserDAO
  extends GenericDao<UserPo>
{
  public abstract UserPo findByUserName(String paramString)
    throws OpdException;
  
  public abstract List<UserPo> findByLikeUserName(String paramString)
    throws OpdException;
  
  public abstract UserPo findByLoginId(String paramString)
    throws OpdException;
  
  public abstract List<UserPo> findByLikeLoginId(String paramString)
    throws OpdException;
  
  public abstract UserPo findByEmail(String paramString)
    throws OpdException;
  
  public abstract UserPo findByPhone(String paramString)
    throws OpdException;
  
  public abstract List<UserPo> findByIsActive(boolean paramBoolean)
    throws OpdException;
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\UserDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */