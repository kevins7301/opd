package com.iisi.opd.cfg.service;

import java.io.File;

public abstract interface DataCfgFile2Service
{
  public abstract void saveArgsByDataCfgOid(String paramString1, String paramString2, long paramLong, byte[] paramArrayOfByte);
  
  public abstract void saveArgsByDataCfgOid(String paramString1, String paramString2, long paramLong, File paramFile);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\DataCfgFile2Service.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */