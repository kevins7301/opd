package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.dto.DataCfgZipFileDto;
import com.iisi.opd.cfg.po.DataCfgZipFilePo;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

public abstract interface DataCfgZipFileDao
  extends GenericDao<DataCfgZipFilePo>
{
  public abstract List<DataCfgZipFilePo> findByDataCfgOid(String paramString);
  
  public abstract List<DataCfgZipFilePo> findByDataCfgOid(String paramString1, String paramString2);
  
  public abstract List<DataCfgZipFileDto> getDataCfgZipFileDtoList(String paramString);
  
  public abstract Blob createBlob(InputStream paramInputStream, long paramLong);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataCfgZipFileDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */