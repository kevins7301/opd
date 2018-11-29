package com.iisi.opd.cfg.service;

import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.po.DataSetApplyPo;
import com.iisi.opd.cfg.po.DataSetApplyPo.ApplyOrderByOption;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.po.DataSetPo.OrderByOption;
import com.iisi.opd.cfg.po.DataSetVerPo;
import java.util.List;

public abstract interface DataSetService
{
  public abstract Boolean isVerify();
  
  public abstract void setVerify(Boolean paramBoolean);
  
  public abstract DataSetPo add(DataSetPo paramDataSetPo);
  
  public abstract void delete(DataSetPo paramDataSetPo);
  
  public abstract void update(DataSetApplyPo paramDataSetApplyPo);
  
  public abstract void update(DataSetPo paramDataSetPo);
  
  public abstract List<DataSetPo> findAll();
  
  public abstract DataSetPo findByOid(String paramString);
  
  public abstract Pager findAllApplied(Pager paramPager, DataSetApplyPo.ApplyOrderByOption paramApplyOrderByOption);
  
  public abstract List<DataSetApplyPo> findAllApplied();
  
  public abstract Pager findAllRefused(Pager paramPager, DataSetApplyPo.ApplyOrderByOption paramApplyOrderByOption);
  
  public abstract List<DataSetApplyPo> findAllRefused();
  
  public abstract List<DataSetPo> findHide();
  
  public abstract DataSetApplyPo enableApply(DataSetApplyPo paramDataSetApplyPo);
  
  public abstract DataSetApplyPo editApply(DataSetApplyPo paramDataSetApplyPo);
  
  public abstract DataSetApplyPo disableApply(DataSetApplyPo paramDataSetApplyPo);
  
  public abstract void cancelApply(DataSetApplyPo paramDataSetApplyPo);
  
  public abstract DataSetPo setAgree(String paramString);
  
  public abstract void setRefuse(String paramString);
  
  public abstract List<DataSetVerPo> findVerByDataSetOid(String paramString);
  
  public abstract DataSetApplyPo findDataSetApplyPoById(String paramString);
  
  public abstract void removeDataSet(String paramString);
  
  public abstract List<DataSetPo> findPublicDataSet();
  
  public abstract List<DataSetPo> findPublicDataSetOrderByCfgResourceModifiedDate();
  
  public abstract Pager findPublicDataSet(Pager paramPager);
  
  public abstract Pager findAllAgreed(Pager paramPager, DataSetPo.OrderByOption paramOrderByOption);
  
  public abstract List<DataSetPo> findAllAgreed();
  
  public abstract List<DataSetPo> getAllOriginalDataSetList();
  
  public abstract List<Object[]> getDataSetOidAndCfgResourceModifiedDate();
  
  public abstract List<DataSetPo> findAllEnable();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\DataSetService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */