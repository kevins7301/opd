package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.dto.DataCfgFileDto;
import com.iisi.opd.cfg.po.DataCfgPo;
import java.util.Date;
import java.util.List;

public abstract interface DataCfgDao
  extends GenericDao<DataCfgPo>
{
  public abstract List<DataCfgPo> categorySearch(String paramString);
  
  public abstract List<DataCfgPo> tagSearch(String paramString);
  
  public abstract List<DataCfgPo> lastSearch();
  
  public abstract List<DataCfgPo> advancedSearch(String paramString1, String paramString2, String paramString3, Date paramDate1, Date paramDate2, String paramString4);
  
  public abstract List<DataCfgPo> findHide();
  
  public abstract List<DataCfgPo> findPublicDataCfg();
  
  public abstract Pager findPublicDataCfg(Pager paramPager);
  
  public abstract List<DataCfgPo> findAll();
  
  public abstract List<DataCfgPo> keywordSearch(String paramString);
  
  public abstract Pager keywordSearch(Pager paramPager, String paramString);
  
  public abstract List<DataCfgPo> findAllAgreed();
  
  public abstract List<DataCfgPo> findPublicByDataSetOid(String paramString);
  
  public abstract void updateCfginfo(String paramString);
  
  public abstract void updateCfginfo(String paramString, int paramInt);
  
  public abstract DataCfgFileDto getDataCfgFileDto(String paramString);
  
  public abstract int getSumOfAllDataCfgDataCount();
}


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\cfg\dao\DataCfgDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */