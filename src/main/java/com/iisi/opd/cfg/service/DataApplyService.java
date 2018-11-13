package com.iisi.opd.cfg.service;

import com.iisi.opd.cfg.po.DataCfgApplyPo;
import com.iisi.opd.cfg.po.DataSetApplyPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.data.in.vo.DataInOptionsVo;
import java.util.List;

public abstract interface DataApplyService
{
  public abstract DataSetApplyPo enableApply(DataSetApplyPo paramDataSetApplyPo, List<DataCfgApplyPo> paramList);
  
  public abstract DataSetPo agreeDataSetApply(String paramString, boolean paramBoolean)
    throws Exception;
  
  public abstract DataSetPo agreeDataSetApply(String paramString, boolean paramBoolean, DataInOptionsVo paramDataInOptionsVo)
    throws Exception;
  
  public abstract void refuseDataSetApply(String paramString, boolean paramBoolean);
  
  public abstract Boolean isVerify();
  
  public abstract void setVerify(Boolean paramBoolean);
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\DataApplyService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */