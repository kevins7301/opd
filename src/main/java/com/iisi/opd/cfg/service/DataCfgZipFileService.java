package com.iisi.opd.cfg.service;

import com.iisi.opd.cfg.dto.DataCfgZipFileDto;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataCfgZipFilePo;
import java.io.File;
import java.util.List;
import java.util.Map;

public abstract interface DataCfgZipFileService
{
  public abstract DataCfgZipFilePo findById(String paramString);
  
  public abstract DataCfgZipFilePo save(DataCfgZipFilePo paramDataCfgZipFilePo);
  
  public abstract void prepareZipFile(DataCfgPo paramDataCfgPo, int paramInt);
  
  public abstract void prepareZipFile(DataCfgPo paramDataCfgPo, int paramInt, String paramString);
  
  public abstract void prepareZipFile(DataCfgPo paramDataCfgPo, int paramInt, String paramString1, String paramString2);
  
  public abstract List<DataCfgZipFilePo> findByDataCfgOid(String paramString);
  
  public abstract List<DataCfgZipFilePo> findByDataCfgOid(String paramString1, String paramString2);
  
  public abstract Map<String, List<DataCfgZipFilePo>> getZipFileMapByDataCfgOid(String paramString);
  
  public abstract void processSingleFile(DataCfgPo paramDataCfgPo, byte[] paramArrayOfByte, String paramString1, String paramString2, boolean paramBoolean);
  
  public abstract void processSingleFile(DataCfgPo paramDataCfgPo, File paramFile, String paramString, boolean paramBoolean);
  
  public abstract List<DataCfgZipFileDto> getDataCfgZipFileDtoList(String paramString);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\DataCfgZipFileService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */