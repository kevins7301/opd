package com.iisi.opd.data.in.dao;

import com.iisi.opd.data.in.vo.DataInOptionsVo;
import com.iisi.opd.exception.OpdException;
import java.io.File;
import java.nio.charset.Charset;

public abstract interface DataInDao
{
  public abstract void createTable(String paramString)
    throws OpdException;
  
  public abstract void createTable(String paramString, DataInOptionsVo paramDataInOptionsVo)
    throws OpdException;
  
  public abstract int insertData(String paramString)
    throws OpdException;
  
  public abstract int insertData(String paramString, boolean paramBoolean)
    throws OpdException;
  
  public abstract int updateData(String paramString)
    throws OpdException;
  
  public abstract int deleteData(String paramString)
    throws OpdException;
  
  public abstract int count(String paramString)
    throws OpdException;
  
  public abstract int batchInsertCsvData(String paramString, File paramFile, Charset paramCharset)
    throws OpdException;
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\dao\DataInDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */