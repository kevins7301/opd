package com.iisi.opd.category.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.opd.category.dao.DataCategoryDao;
import com.iisi.opd.category.po.DataCategoryPo;
import com.iisi.opd.category.service.DataCategoryService;
import com.iisi.opd.cfg.dao.DataSetApplyDao;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;

@Service
@Transactional
public class DataCategoryServiceImpl implements DataCategoryService {
    public static final Logger log = LoggerFactory.getLogger(DataCategoryServiceImpl.class);

    @Autowired
    private DataCategoryDao dataCategoryDao;

    @Autowired
    private DataSetApplyDao dataSetApplyDao;

    @Override
    public DataCategoryPo add(DataCategoryPo po) {
        return (DataCategoryPo) this.dataCategoryDao.save(po);
    }

    @Override
    @Transactional(noRollbackFor = { OpdException.class })
    public void delete(DataCategoryPo po) {
        if (po.getDataSetPoList().size() == 0) {
            if (this.dataSetApplyDao.countByCategoryOid(po.getOid()) == 0) {
                this.dataCategoryDao.delete(po);
            } else {
                throw new OpdException(ErrorCodeEnum.ERR_2040002_EXCEPTION);
            }
        } else {
            throw new OpdException(ErrorCodeEnum.ERR_2040001_EXCEPTION);
        }
    }

    @Override
    public void update(DataCategoryPo po) {
        this.dataCategoryDao.update(po);
    }

    @Override
    public List<DataCategoryPo> findAll() {
        return this.dataCategoryDao.findAll(DataCategoryPo.class);
    }

    @Override
    public DataCategoryPo findByOid(String oid) {
        DataCategoryPo po = (DataCategoryPo) this.dataCategoryDao.findById(oid);
        log.debug("DataCategoryPo：", po);
        return po;
    }
}