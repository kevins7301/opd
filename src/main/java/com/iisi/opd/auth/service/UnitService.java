package com.iisi.opd.auth.service;

import com.iisi.opd.auth.po.UnitPo;
import java.util.List;

public abstract interface UnitService
{
  public abstract UnitPo add(UnitPo paramUnitPo);
  
  public abstract void delete(UnitPo paramUnitPo);
  
  public abstract void update(UnitPo paramUnitPo);
  
  public abstract List<UnitPo> findAll();
  
  public abstract UnitPo findByOid(String paramString);
  
  public abstract UnitPo findByUnitName(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\service\UnitService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */