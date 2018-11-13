package com.iisi.opd.cfg.dao;

import com.iisi.common.dao.GenericDao;
import com.iisi.opd.cfg.po.DataCfgFilePo;

public abstract interface DataCfgFileDao extends GenericDao<DataCfgFilePo> {
    public abstract DataCfgFilePo findByDataCfgOid(String paramString);
}
