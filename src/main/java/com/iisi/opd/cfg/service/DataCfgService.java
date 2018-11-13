package com.iisi.opd.cfg.service;

import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.dto.DataCfgFileDto;
import com.iisi.opd.cfg.po.DataCfgApplyPo;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataCfgVerPo;
import com.iisi.opd.cfg.po.DataCfgZipFilePo;
import com.iisi.opd.cfg.po.DataFieldCfgPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.data.in.vo.DataInOptionsVo;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract interface DataCfgService
{
  public abstract DataCfgPo add(DataCfgPo paramDataCfgPo);
  
  public abstract void delete(DataCfgPo paramDataCfgPo);
  
  public abstract void update(DataCfgApplyPo paramDataCfgApplyPo);
  
  public abstract void update(DataCfgPo paramDataCfgPo);
  
  public abstract Boolean isVerify();
  
  public abstract void setVerify(Boolean paramBoolean);
  
  public abstract List<DataCfgPo> findAll();
  
  public abstract DataCfgPo findByOid(String paramString);
  
  public abstract List<DataCfgApplyPo> findAllApplied();
  
  public abstract List<DataCfgApplyPo> findAllRefused();
  
  public abstract DataCfgApplyPo enableApply(DataCfgApplyPo paramDataCfgApplyPo);
  
  public abstract DataCfgApplyPo editApply(DataCfgApplyPo paramDataCfgApplyPo);
  
  public abstract DataCfgApplyPo disableApply(DataCfgApplyPo paramDataCfgApplyPo);
  
  public abstract void cancelApply(DataCfgApplyPo paramDataCfgApplyPo);
  
  public abstract DataCfgPo setAgree(String paramString)
    throws Exception;
  
  public abstract DataCfgPo setAgree(String paramString, DataInOptionsVo paramDataInOptionsVo)
    throws Exception;
  
  public abstract void setRefuse(String paramString);
  
  public abstract List<DataCfgPo> findHide();
  
  public abstract DataSetPo addDataSet(DataSetPo paramDataSetPo);
  
  public abstract void deleteDataSet(DataSetPo paramDataSetPo);
  
  public abstract void updateDataSet(DataSetPo paramDataSetPo);
  
  public abstract List<DataCfgVerPo> findVerByDataCfgOid(String paramString);
  
  public abstract DataCfgApplyPo findDataCfgApplyPoById(String paramString);
  
  public abstract void removeDataCfg(String paramString);
  
  public abstract List<DataCfgPo> findPublicDataCfg();
  
  public abstract Pager findPublicDataCfg(Pager paramPager);
  
  public abstract List<DataCfgPo> findAllAgreed();
  
  public abstract void updateDataCountByOid(String paramString, int paramInt);
  
  public abstract void updateUpdateTimeByOid(String paramString, Date paramDate);
  
  public abstract List<DataCfgPo> findPublicByDataSetOid(String paramString);
  
  public abstract Map<String, List<DataCfgZipFilePo>> getZipFileMapKeyBySourceType(DataCfgPo paramDataCfgPo);
  
  public abstract void deleteAllCfgZipFile(DataCfgPo paramDataCfgPo);
  
  public abstract void deleteAllCfgZipFileByType(DataCfgPo paramDataCfgPo, String paramString);
  
  public abstract Map<String, List<DataFieldCfgPo>> getColumnFiledCfgMapByDataCfgPo(DataCfgPo paramDataCfgPo);
  
  public abstract DataCfgFileDto getDataCfgFileDto(String paramString);
  
  public abstract int getSumOfAllDataCfgDataCount();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\service\DataCfgService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */