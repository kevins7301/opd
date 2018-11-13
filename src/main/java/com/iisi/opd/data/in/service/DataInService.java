package com.iisi.opd.data.in.service;

import com.iisi.opd.data.in.vo.DataInOptionsVo;
import com.iisi.opd.data.in.vo.DataInProcessVo;
import java.io.File;

public abstract interface DataInService
{
  public abstract void createTable(String paramString);
  
  public abstract void createTable(String paramString, DataInOptionsVo paramDataInOptionsVo);
  
  public abstract void updateData(String paramString);
  
  public abstract void updateData(String paramString, DataInOptionsVo paramDataInOptionsVo);
  
  public abstract void insertData(String paramString);
  
  public abstract void insertData(String paramString, DataInOptionsVo paramDataInOptionsVo);
  
  public abstract void deleteData(String paramString);
  
  public abstract void uploadFile(String paramString1, String paramString2, long paramLong, byte[] paramArrayOfByte)
    throws Exception;
  
  public abstract void uploadFile(String paramString1, String paramString2, long paramLong, byte[] paramArrayOfByte, DataInOptionsVo paramDataInOptionsVo)
    throws Exception;
  
  public abstract void uploadFile(String paramString1, String paramString2, long paramLong, File paramFile, DataInOptionsVo paramDataInOptionsVo)
    throws Exception;
  
  public abstract void uploadFileBatch(String paramString1, String paramString2, long paramLong, File paramFile, DataInOptionsVo paramDataInOptionsVo)
    throws Exception;
  
  public abstract void dataInStandardProcess(DataInProcessVo paramDataInProcessVo);
  
  public abstract int checkFileStandardProcess(DataInProcessVo paramDataInProcessVo);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\service\DataInService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */