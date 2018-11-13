package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.po.DataCfgFile2Po;
import java.io.File;

public abstract interface DataCfgFile2Dao
  extends GenericDao<DataCfgFile2Po>
{
  public abstract DataCfgFile2Po findByDataCfgOid(String paramString);
  
  public abstract void savePoWithBlob(DataCfgFile2Po paramDataCfgFile2Po, byte[] paramArrayOfByte);
  
  public abstract void savePoWithBlob(DataCfgFile2Po paramDataCfgFile2Po, File paramFile);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataCfgFile2Dao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */