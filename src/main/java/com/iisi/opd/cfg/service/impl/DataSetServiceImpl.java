package com.iisi.opd.cfg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.common.util.Pager;
import com.iisi.opd.category.po.DataCategoryMetadataPo;
import com.iisi.opd.category.po.DataCategoryPo;
import com.iisi.opd.cfg.dao.DataCfgDao;
import com.iisi.opd.cfg.dao.DataSetApplyDao;
import com.iisi.opd.cfg.dao.DataSetDao;
import com.iisi.opd.cfg.dao.DataSetVerDao;
import com.iisi.opd.cfg.po.DataCfgApplyPo;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataSetApplyPo;
import com.iisi.opd.cfg.po.DataSetCategoryVerMetadataPo;
import com.iisi.opd.cfg.po.DataSetCategoryVerPo;
import com.iisi.opd.cfg.po.DataSetMetadataApplyPo;
import com.iisi.opd.cfg.po.DataSetMetadataPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.po.DataSetVerMetadataPo;
import com.iisi.opd.cfg.po.DataSetVerPo;
import com.iisi.opd.cfg.po.DataSetVerTagPo;
import com.iisi.opd.cfg.service.DataCfgService;
import com.iisi.opd.cfg.service.DataSetService;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;
import com.iisi.opd.log.service.LogService;
import com.iisi.opd.tag.po.DataTagPo;

@Service
@Transactional
public class DataSetServiceImpl implements DataSetService {
    @Autowired
    private Boolean isVerify;
    @Autowired
    private DataSetDao dataSetDao;
    @Autowired
    private DataSetApplyDao dataSetApplyDao;
    @Autowired
    private DataSetVerDao dataSetVerDao;
    @Autowired
    private DataCfgService dataCfgService;
    @Autowired
    private DataCfgDao dataCfgDao;
    @Autowired
    private LogService logService;

    @Override
    public DataSetPo add(DataSetPo po) {
        return this.dataSetDao.save(po);
    }

    @Override
    public void delete(DataSetPo po) {
        this.dataSetDao.delete(po);
    }

    @Override
    public void update(DataSetPo po) {
        this.dataSetDao.update(po);
    }

    @Override
    public List<DataSetPo> findAll() {
        return this.dataSetDao.findAll();
    }

    @Override
    public DataSetPo findByOid(String oid) {
        return this.dataSetDao.findById(oid);
    }

    @Override
    public Boolean isVerify() {
        return this.isVerify;
    }

    @Override
    public void setVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    @Override
    public Pager findAllApplied(Pager pager, DataSetApplyPo.ApplyOrderByOption orderByOption) {
        return this.dataSetApplyDao.findAllApplied(pager, orderByOption);
    }

    @Override
    public List<DataSetApplyPo> findAllApplied() {
        return this.dataSetApplyDao.findAllApplied();
    }

    @Override
    public Pager findAllRefused(Pager pager, DataSetApplyPo.ApplyOrderByOption orderByOption) {
        return this.dataSetApplyDao.findAllRefused(pager, orderByOption);
    }

    @Override
    public List<DataSetApplyPo> findAllRefused() {
        return this.dataSetApplyDao.findAllRefused();
    }

    @Override
    public Pager findAllAgreed(Pager pager, DataSetPo.OrderByOption orderByOption) {
        return this.dataSetDao.findAllAgreed(pager, orderByOption);
    }

    @Override
    public List<DataSetPo> findAllAgreed() {
        return this.dataSetDao.findAllAgreed();
    }

    @Override
    public List<DataSetPo> findAllEnable() {
        return this.dataSetDao.findAllEnable();
    }

    @Override
    public List<DataSetPo> findHide() {
        return this.dataSetDao.findHide();
    }

    @Override
    public DataSetApplyPo enableApply(DataSetApplyPo dataSetApplyPo) {
        DataSetPo dataSetPo = dataSetApplyPo.getDataSetPo();
        if (dataSetPo != null) {
            if (dataSetPo.isEnable().booleanValue()) {
                throw new OpdException(ErrorCodeEnum.ERR_2020002_EXCEPTION);
            }
            if ((dataSetPo.isApplied().booleanValue()) && ((dataSetApplyPo.getOid() == null)
                    || (dataSetApplyPo.getActionType() != DataSetApplyPo.ActionType.ENABLE))) {

                throw new OpdException(ErrorCodeEnum.ERR_2020001_EXCEPTION);
            }
        }

        dataSetApplyPo.setActionType(DataSetApplyPo.ActionType.ENABLE);
        dataSetApplyPo.setDataStatus(DataSetApplyPo.DataStatus.APPLY);
        if (this.isVerify.booleanValue()) {
            dataSetApplyPo.setApplyTime(new Date());
            dataSetApplyPo = this.dataSetApplyDao.save(dataSetApplyPo);
            if (dataSetPo != null) {
                dataSetPo.setApplied(Boolean.valueOf(true));
                this.dataSetDao.update(dataSetPo);
            }
        } else {
            if (dataSetPo != null) {
                dataSetPo.setEnable(Boolean.valueOf(true));
                dataSetPo.setPublicTime(new Date(System.currentTimeMillis()));
                dataSetPo.setPublishedDate(new Date(System.currentTimeMillis()));
                dataSetPo.setCreateTime(new Date(System.currentTimeMillis()));
                this.dataSetDao.update(dataSetPo);
            } else {
                dataSetPo = new DataSetPo();
                copyProperties(dataSetPo, dataSetApplyPo);
                dataSetPo.setPublicTime(new Date(System.currentTimeMillis()));
                dataSetPo.setEnable(Boolean.valueOf(true));
                dataSetApplyPo.setDataSetPo(dataSetPo);
                dataSetPo = this.dataSetDao.save(dataSetPo);
            }
            this.dataSetDao.flush();

            dataSetPo = this.dataSetDao.findById(dataSetPo.getOid());
            saveDataSetVersion(dataSetPo);
            dataSetApplyPo.setDataSetPo(dataSetPo);
        }
        return dataSetApplyPo;
    }

    @Override
    public DataSetApplyPo editApply(DataSetApplyPo dataSetApplyPo) {
        DataSetApplyPo back = null;
        DataSetPo dataSetPo = dataSetApplyPo.getDataSetPo();
        if (dataSetPo == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2020004_EXCEPTION);
        }
        if ((dataSetPo.isApplied().booleanValue())
                && ((dataSetApplyPo.getOid() == null) || (dataSetApplyPo.getActionType() != DataSetApplyPo.ActionType.EDIT))) {

            throw new OpdException(ErrorCodeEnum.ERR_2020001_EXCEPTION);
        }
        if (!dataSetPo.isEnable().booleanValue()) {
            throw new OpdException(ErrorCodeEnum.ERR_2020003_EXCEPTION);
        }

        if (this.isVerify.booleanValue()) {
            dataSetApplyPo.setActionType(DataSetApplyPo.ActionType.EDIT);
            dataSetApplyPo.setDataStatus(DataSetApplyPo.DataStatus.APPLY);
            dataSetApplyPo.setApplyTime(new Date());
            dataSetPo.setApplied(Boolean.valueOf(true));
            back = this.dataSetApplyDao.save(dataSetApplyPo);
            this.dataSetDao.update(dataSetPo);
        } else {
            dataSetPo = dataSetApplyPo.getDataSetPo();
            copyProperties(dataSetPo, dataSetApplyPo);
            this.dataSetDao.update(dataSetPo);

            saveDataSetVersion(dataSetPo);
        }
        return back;
    }

    @Override
    public DataSetApplyPo disableApply(DataSetApplyPo dataSetApplyPo) {
        DataSetApplyPo back = null;
        DataSetPo dataSetPo = dataSetApplyPo.getDataSetPo();
        if (dataSetPo == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2020004_EXCEPTION);
        }
        if ((dataSetPo.isApplied().booleanValue()) && (dataSetApplyPo.getOid() == null)) {
            throw new OpdException(ErrorCodeEnum.ERR_2020001_EXCEPTION);
        }
        if (!dataSetPo.isEnable().booleanValue()) {
            throw new OpdException(ErrorCodeEnum.ERR_2020003_EXCEPTION);
        }

        dataSetApplyPo.setActionType(DataSetApplyPo.ActionType.DISABLE);
        dataSetApplyPo.setDataStatus(DataSetApplyPo.DataStatus.APPLY);
        if (this.isVerify.booleanValue()) {
            dataSetApplyPo.setApplyTime(new Date());
            back = this.dataSetApplyDao.save(dataSetApplyPo);
            if (dataSetPo != null) {
                dataSetPo.setApplied(Boolean.valueOf(true));
                this.dataSetDao.update(dataSetPo);
            }
        } else {
            dataSetPo.setLastEditUserName(dataSetApplyPo.getApplyUserName());
            dataSetPo.setModifiedDate(new Date(System.currentTimeMillis()));
            dataSetPo.setEnable(Boolean.valueOf(false));
            this.dataSetDao.update(dataSetPo);

            saveDataSetVersion(dataSetPo);
        }
        return back;
    }

    @Override
    public void cancelApply(DataSetApplyPo dataSetApplyPo) {
        DataSetPo dataSetPo = dataSetApplyPo.getDataSetPo();
        if (dataSetPo != null) {
            dataSetPo.setDataSetApplyPo(null);
            dataSetPo.setApplied(Boolean.FALSE);
            this.dataSetDao.update(dataSetPo);
        }
        this.dataSetApplyDao.delete(dataSetApplyPo);
    }

    @Override
    public DataSetPo setAgree(String oid) {
        DataSetApplyPo dataSetApplyPo = this.dataSetApplyDao.findById(oid);
        DataSetPo dataSetPo = dataSetApplyPo.getDataSetPo();
        DataSetApplyPo.ActionType type = dataSetApplyPo.getActionType();
        if (DataSetApplyPo.ActionType.ENABLE.equals(type)) {
            if (dataSetPo == null) {
                dataSetPo = new DataSetPo();
                copyProperties(dataSetPo, dataSetApplyPo);
                dataSetPo.setEnable(Boolean.valueOf(true));
                dataSetPo.setPublicTime(new Date(System.currentTimeMillis()));
                dataSetPo.setPublishedDate(new Date(System.currentTimeMillis()));
                dataSetPo.setCreateTime(new Date(System.currentTimeMillis()));
            } else {
                dataSetPo.setEnable(Boolean.valueOf(true));
                dataSetPo.setPublicTime(new Date(System.currentTimeMillis()));
            }
        } else if (DataSetApplyPo.ActionType.EDIT.equals(type)) {
            copyProperties(dataSetPo, dataSetApplyPo);
        } else if (DataSetApplyPo.ActionType.DISABLE.equals(type)) {
            dataSetPo.setLastEditUserName(dataSetApplyPo.getApplyUserName());
            dataSetPo.setModifiedDate(new Date(System.currentTimeMillis()));
            dataSetPo.setEnable(Boolean.valueOf(false));
        }

        List<DataCfgApplyPo> dataCfgApplyPoList = dataSetApplyPo.getDataCfgApplyPoList();

        if (dataCfgApplyPoList != null) {
            for (DataCfgApplyPo dataCfgApplyPo : dataCfgApplyPoList) {
                dataCfgApplyPo.setDataSetApplyPo(null);
            }
            dataSetApplyPo.setDataCfgApplyPoList(null);
            this.dataSetApplyDao.update(dataSetApplyPo);
        }
        dataSetPo.setDataSetApplyPo(null);
        dataSetPo.setApplied(Boolean.valueOf(false));
        if ((DataSetApplyPo.ActionType.ENABLE.equals(type)) && (dataSetPo.getOid() == null)) {
            // ! dataSet 新增
            dataSetPo = this.dataSetDao.save(dataSetPo);
        } else {
            this.dataSetDao.update(dataSetPo);
        }

        this.dataSetApplyDao.delete(dataSetApplyPo);

        saveDataSetVersion(dataSetPo);
        return dataSetPo;
    }

    @Override
    public void setRefuse(String oid) {
        DataSetApplyPo dataSetApplyPo = this.dataSetApplyDao.findById(oid);
        dataSetApplyPo.setDataStatus(DataSetApplyPo.DataStatus.REFUSE);
        dataSetApplyPo.setRefuseTime(new Date());
        this.dataSetApplyDao.update(dataSetApplyPo);
    }

    @Override
    public List<DataSetVerPo> findVerByDataSetOid(String dataSetOid) {
        return this.dataSetVerDao.findVerByDataSetOid(dataSetOid);
    }

    @Override
    public DataSetApplyPo findDataSetApplyPoById(String oid) {
        return this.dataSetApplyDao.findById(oid);
    }

    private void copyProperties(DataSetPo dataSetPo, DataSetApplyPo dataSetApplyPo) {
        dataSetPo.setName(dataSetApplyPo.getName());
        dataSetPo.setEnable(dataSetApplyPo.isEnable());
        dataSetPo.setPublic(dataSetApplyPo.isPublic());
        dataSetPo.setNoPublicReason(dataSetApplyPo.getNoPublicReason());
        dataSetPo.setDescription(dataSetApplyPo.getDescription());
        dataSetPo.setLastEditUserName(dataSetApplyPo.getApplyUserName());
        dataSetPo.setDataCategoryPo(dataSetApplyPo.getDataCategoryPo());
        dataSetPo.setUnitPo(dataSetApplyPo.getUnitPo());
        dataSetPo.setModifiedDate(new Date(System.currentTimeMillis()));

        List<DataTagPo> applyTagList = dataSetApplyPo.getDataTagPoList();
        List<DataTagPo> targetTagList = dataSetPo.getDataTagPoList();
        targetTagList.clear();
        for (DataTagPo dataTagPo : applyTagList) {
            targetTagList.add(dataTagPo);
        }

        if (dataSetPo.getDataSetMetadataPoList() == null) {
            dataSetPo.setDataSetMetadataPoList(new ArrayList());
        }
        List<DataSetMetadataPo> dataSetMetadataList = dataSetPo.getDataSetMetadataPoList();
        List<DataSetMetadataApplyPo> dataSetMetadataApplyList = dataSetApplyPo.getDataSetMetadataPoList();

        List<DataSetMetadataPo> appendMetadataList = new ArrayList();
        List<DataSetMetadataPo> unremovableMetadataList = new ArrayList();
        List<DataSetMetadataPo> removableMetadataList = new ArrayList();

        for (DataSetMetadataApplyPo dataSetMetadataApply : dataSetMetadataApplyList) {
            boolean found = false;

            for (DataSetMetadataPo dataSetMetadata : dataSetMetadataList) {
                if (found) {
                    break;
                }

                if (dataSetMetadataApply.getMetadataKey().equals(dataSetMetadata.getMetadataKey())) {
                    dataSetMetadata.setCommon(dataSetMetadataApply.isCommon());
                    dataSetMetadata.setMetadataValue(dataSetMetadataApply.getMetadataValue());
                    found = true;

                    unremovableMetadataList.add(dataSetMetadata);
                }
            }

            if (!found) {
                DataSetMetadataPo dataSetMetadata = new DataSetMetadataPo();
                dataSetMetadata.setCommon(dataSetMetadataApply.isCommon());
                dataSetMetadata.setDataSetPo(dataSetPo);
                dataSetMetadata.setMetadataKey(dataSetMetadataApply.getMetadataKey());
                dataSetMetadata.setMetadataValue(dataSetMetadataApply.getMetadataValue());

                appendMetadataList.add(dataSetMetadata);
            }
        }

        for (DataSetMetadataPo dataSetMetadata : dataSetMetadataList) {
            if (!unremovableMetadataList.contains(dataSetMetadata)) {
                removableMetadataList.add(dataSetMetadata);
            }
        }

        for (DataSetMetadataPo dataSetMetadata : removableMetadataList) {
            dataSetMetadataList.remove(dataSetMetadata);
        }

        dataSetMetadataList.addAll(appendMetadataList);

        appendMetadataList.clear();
        unremovableMetadataList.clear();
        removableMetadataList.clear();

        if (dataSetApplyPo.getDataCfgApplyPoList() != null)
            dataSetPo.setDataCfgApplyPoList(dataSetApplyPo.getDataCfgApplyPoList());
    }

    private void copyProperties(DataSetVerPo dataSetVerPo, DataSetPo dataSetPo) {
        dataSetVerPo.setName(dataSetPo.getName());
        dataSetVerPo.setActive(dataSetPo.isActive());
        dataSetVerPo.setEnable(dataSetPo.isEnable());
        dataSetVerPo.setPublic(dataSetPo.isPublic());
        dataSetVerPo.setNoPublicReason(dataSetPo.getNoPublicReason());
        dataSetVerPo.setPublicTime(dataSetPo.getPublicTime());
        dataSetVerPo.setDescription(dataSetPo.getDescription());
        if (dataSetPo.getDataCategoryPo() != null) {
            DataCategoryPo dataCategoryPo = dataSetPo.getDataCategoryPo();
            DataSetCategoryVerPo dataSetCategoryVerPo = new DataSetCategoryVerPo();
            dataSetCategoryVerPo.setName(dataCategoryPo.getName());
            dataSetCategoryVerPo.setIcon(dataCategoryPo.getIcon());

            List<DataCategoryMetadataPo> categorMetadataList = dataCategoryPo.getDataCategoryMetadataPoList();
            List<DataSetCategoryVerMetadataPo> list = new ArrayList();
            for (DataCategoryMetadataPo dataCategoryMetadataPo : categorMetadataList) {
                DataSetCategoryVerMetadataPo dataSetCategoryVerMetadataPo = new DataSetCategoryVerMetadataPo();
                dataSetCategoryVerMetadataPo.setMetadataKey(dataCategoryMetadataPo.getMetadataKey());
                dataSetCategoryVerMetadataPo.setMetadataValue(dataCategoryMetadataPo.getMetadataValue());
                dataSetCategoryVerMetadataPo.setDataSetCategoryVerPo(dataSetCategoryVerPo);
                list.add(dataSetCategoryVerMetadataPo);
            }
            dataSetCategoryVerPo.setDataCategoryVerMetadataPoList(list);

            dataSetVerPo.setDataSetCategoryVerPo(dataSetCategoryVerPo);
        }
        dataSetVerPo.setEditUserName(dataSetPo.getLastEditUserName());
        dataSetVerPo.setUnitPo(dataSetPo.getUnitPo());

        List<DataTagPo> dataTagPoList = dataSetPo.getDataTagPoList();
        List<DataSetVerTagPo> dataSetVerTagPoList = new ArrayList();
        for (DataTagPo dataTagPo : dataTagPoList) {
            DataSetVerTagPo dataTagVerPo = new DataSetVerTagPo();
            dataTagVerPo.setName(dataTagPo.getName());
            dataTagVerPo.setDataSetVerPo(dataSetVerPo);
            dataSetVerTagPoList.add(dataTagVerPo);
        }
        dataSetVerPo.setDataSetVerTagPoList(dataSetVerTagPoList);

        List<DataSetVerMetadataPo> dataSetVerMetadataPoList = new ArrayList();
        List<DataSetMetadataPo> sourceList = dataSetPo.getDataSetMetadataPoList();
        for (DataSetMetadataPo dataSetMetadataPo : sourceList) {
            DataSetVerMetadataPo dataSetVerMetadataPo = new DataSetVerMetadataPo();
            dataSetVerMetadataPo.setCommon(dataSetMetadataPo.isCommon());
            dataSetVerMetadataPo.setDataSetVerPo(dataSetVerPo);
            dataSetVerMetadataPo.setMetadataKey(dataSetMetadataPo.getMetadataKey());
            dataSetVerMetadataPo.setMetadataValue(dataSetMetadataPo.getMetadataValue());
            dataSetVerMetadataPoList.add(dataSetVerMetadataPo);
        }
        dataSetVerPo.setDataSetVerMetadataPoList(dataSetVerMetadataPoList);
    }

    private void saveDataSetVersion(DataSetPo dataSetPo) {
        DataSetVerPo dataSetVerPo = new DataSetVerPo();
        copyProperties(dataSetVerPo, dataSetPo);

        //dataSetVerPo.getDataSetCategoryVerPo().setDataSetVerPo(dataSetVerPo);
        dataSetVerPo.setDataSetPo(dataSetPo);
        dataSetVerPo.setLogTime(new Date());
        dataSetVerPo = this.dataSetVerDao.save(dataSetVerPo);
    }

    @Override
    public void update(DataSetApplyPo po) {
        this.dataSetApplyDao.update(po);
    }

    @Override
    public void removeDataSet(String dataSetOid) {
        List<DataSetVerPo> removeDataSetVerList = this.dataSetVerDao.findVerByDataSetOid(dataSetOid);
        if (removeDataSetVerList.size() != 0) {
            this.dataSetVerDao.deleteAll(removeDataSetVerList);
        }

        List<DataCfgPo> removeDataCfgPoList = this.dataSetDao.findById(dataSetOid).getDataCfgPoList();
        for (DataCfgPo dataCfgPo : removeDataCfgPoList) {
            this.dataCfgService.removeDataCfg(dataCfgPo.getOid());
        }

        this.logService.removeLogByDataSetOid(dataSetOid);

        this.dataSetDao.deleteById(dataSetOid);
        this.dataSetDao.flush();
    }

    @Override
    public List<DataSetPo> findPublicDataSet() {
        return this.dataSetDao.findPublicDataSet();
    }

    @Override
    public List<DataSetPo> findPublicDataSetOrderByCfgResourceModifiedDate() {
        return this.dataSetDao.findPublicDataSetOrderByCfgResourceModifiedDate();
    }

    @Override
    public Pager findPublicDataSet(Pager pager) {
        return this.dataSetDao.findPublicDataSet(pager);
    }

    @Override
    public List<DataSetPo> getAllOriginalDataSetList() {
        return this.dataSetDao.findAll(DataSetPo.class);
    }

    @Override
    public List<Object[]> getDataSetOidAndCfgResourceModifiedDate() {
        return this.dataSetDao.getDataSetOidAndCfgResourceModifiedDate();
    }
}