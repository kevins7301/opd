package com.iisi.opd.data.in.exec;

import com.iisi.opd.data.in.vo.DataInOptionsVo;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import net.sf.json.JSONObject;

public abstract interface UploadExecutor
{
  public static final String CREATE_TABLE = "CREATE_TABLE";
  public static final String INSERT_TABLE = "INSERT_TABLE";
  public static final String FILE_ENCODING = "UTF-8";
  
  public abstract JSONObject execute(InputStream paramInputStream)
    throws Exception;
  
  public abstract JSONObject execute(InputStream paramInputStream, DataInOptionsVo paramDataInOptionsVo)
    throws Exception;
  
  public abstract Map<String, File> batchExecute(String paramString, InputStream paramInputStream, DataInOptionsVo paramDataInOptionsVo)
    throws Exception;
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\in\exec\UploadExecutor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */