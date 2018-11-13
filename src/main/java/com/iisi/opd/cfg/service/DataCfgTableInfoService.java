package com.iisi.opd.cfg.service;

import com.iisi.opd.cfg.po.DataCfgTableInfoPo;

public abstract interface DataCfgTableInfoService
{
  public abstract DataCfgTableInfoPo findByDataCfgOid(String paramString);
  
  public abstract DataCfgTableInfoPo save(DataCfgTableInfoPo paramDataCfgTableInfoPo);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\DataCfgTableInfoService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */