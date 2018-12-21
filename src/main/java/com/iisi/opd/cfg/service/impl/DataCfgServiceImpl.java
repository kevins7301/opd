package com.iisi.opd.cfg.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.dao.DataCfgApplyDao;
import com.iisi.opd.cfg.dao.DataCfgDao;
import com.iisi.opd.cfg.dao.DataCfgVerDao;
import com.iisi.opd.cfg.dao.DataCfgZipFileDao;
import com.iisi.opd.cfg.dao.DataSetDao;
import com.iisi.opd.cfg.dto.DataCfgFileDto;
import com.iisi.opd.cfg.po.DataCfgApplyPo;
import com.iisi.opd.cfg.po.DataCfgFileApplyPo;
import com.iisi.opd.cfg.po.DataCfgMetadataApplyPo;
import com.iisi.opd.cfg.po.DataCfgMetadataPo;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataCfgVerMetadataPo;
import com.iisi.opd.cfg.po.DataCfgVerPo;
import com.iisi.opd.cfg.po.DataCfgZipFilePo;
import com.iisi.opd.cfg.po.DataFieldCfgApplyPo;
import com.iisi.opd.cfg.po.DataFieldCfgPo;
import com.iisi.opd.cfg.po.DataFieldCfgVerPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.service.DataCfgService;
import com.iisi.opd.data.in.service.DataInService;
import com.iisi.opd.data.in.vo.DataInOptionsVo;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;
import com.iisi.opd.log.service.LogService;

@Service
@Transactional
public class DataCfgServiceImpl implements DataCfgService {
    @Autowired
    private Boolean isVerify;
    @Autowired
    private DataCfgDao dataCfgDao;
    @Autowired
    private DataCfgApplyDao dataCfgApplyDao;
    @Autowired
    private DataSetDao dataSetDao;
    @Autowired
    private DataCfgVerDao dataCfgVerDao;
    @Autowired
    private DataCfgZipFileDao dataCfgZipFileDao;
    @Autowired
    private LogService logService;
    @Autowired
    private DataInService dataInService;
    @Resource(name = "sourceTypeList")
    private List<String> sourceTypeList;
    @Resource(name = "fieldTypeMap")
    private Map<String, Boolean> fieldTypeMap;

    @Override
    public DataCfgPo add(DataCfgPo po) {
        return this.dataCfgDao.save(po);
    }

    @Override
    public void delete(DataCfgPo po) {
        this.dataCfgDao.delete(po);
    }

    @Override
    public void update(DataCfgApplyPo po) {
        this.dataCfgApplyDao.save(po);
    }

    @Override
    public void update(DataCfgPo po) {
        this.dataCfgDao.save(po);
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
    public List<DataCfgPo> findAll() {
        return this.dataCfgDao.findAll();
    }

    @Override
    public DataCfgPo findByOid(String oid) {
        return (DataCfgPo) this.dataCfgDao.findById(oid);
    }

    @Override
    public List<DataCfgApplyPo> findAllApplied() {
        return this.dataCfgApplyDao.findAllApplied();
    }

    @Override
    public List<DataCfgPo> findAllAgreed() {
        return this.dataCfgDao.findAllAgreed();
    }

    @Override
    public List<DataCfgApplyPo> findAllRefused() {
        return this.dataCfgApplyDao.findAllRefused();
    }

    @Override
    public DataCfgApplyPo enableApply(DataCfgApplyPo dataCfgApplyPo) {
        Date currentTime = new Date(System.currentTimeMillis());

        DataCfgPo dataCfgPo = dataCfgApplyPo.getDataCfgPo();
        if (dataCfgPo != null) {
            if (dataCfgPo.isEnable().booleanValue()) {
                throw new OpdException(ErrorCodeEnum.ERR_2020002_EXCEPTION);
            }
            if ((dataCfgPo.isApplied().booleanValue()) && ((dataCfgApplyPo.getOid() == null)
                    || (dataCfgApplyPo.getActionType() != DataCfgApplyPo.ActionType.ENABLE))) {

                throw new OpdException(ErrorCodeEnum.ERR_2020001_EXCEPTION);
            }
        }

        dataCfgApplyPo.setActionType(DataCfgApplyPo.ActionType.ENABLE);
        dataCfgApplyPo.setDataStatus(DataCfgApplyPo.DataStatus.APPLY);
        if (this.isVerify.booleanValue()) {
            dataCfgApplyPo.setApplyTime(new Date());
            dataCfgApplyPo = (DataCfgApplyPo) this.dataCfgApplyDao.save(dataCfgApplyPo);
            if (dataCfgPo != null) {
                dataCfgPo.setApplied(Boolean.valueOf(true));
                this.dataCfgDao.update(dataCfgPo);
            }
        } else {
            if (dataCfgPo != null) {
                dataCfgPo.setEnable(Boolean.valueOf(true));
                dataCfgPo.setPublicTime(currentTime);
                dataCfgPo.setCreateTime(currentTime);
                dataCfgPo.setLastEditUserName(dataCfgApplyPo.getApplyUserName());
                dataCfgPo.setLastEditTime(currentTime);
                dataCfgPo.setStartTime(dataCfgApplyPo.getStartTime());
                dataCfgPo.setEndTime(dataCfgApplyPo.getEndTime());
                this.dataCfgDao.update(dataCfgPo);
            } else {
                dataCfgPo = new DataCfgPo();
                copyProperties(dataCfgPo, dataCfgApplyPo);
                dataCfgPo.setEnable(Boolean.valueOf(true));
                dataCfgPo.setPublicTime(currentTime);
                dataCfgPo = (DataCfgPo) this.dataCfgDao.save(dataCfgPo);
                DataCfgFileApplyPo dataCfgFileApplyPo = dataCfgApplyPo.getDataCfgFileApplyPo();

                if (dataCfgFileApplyPo != null) {
                    String fileName = dataCfgFileApplyPo.getName();
                    long fileSize = dataCfgFileApplyPo.getSize();
                    byte[] fileContent = dataCfgFileApplyPo.getContent();
                    try {
                        this.dataInService.uploadFile(dataCfgPo.getOid(), fileName, fileSize, fileContent);
                    } catch (OpdException e) {
                        throw e;
                    } catch (Exception e) {
                    }
                }
            }

            this.dataCfgDao.flush();

            dataCfgPo = (DataCfgPo) this.dataCfgDao.findById(dataCfgPo.getOid());
            saveDataCfgVersion(dataCfgPo);
            dataCfgApplyPo.setDataCfgPo(dataCfgPo);
        }
        return dataCfgApplyPo;
    }

    @Override
    public DataCfgApplyPo editApply(DataCfgApplyPo dataCfgApplyPo) {
        DataCfgApplyPo back = null;
        DataCfgPo dataCfgPo = dataCfgApplyPo.getDataCfgPo();
        if (dataCfgPo == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2020004_EXCEPTION);
        }
        if ((dataCfgPo.isApplied().booleanValue())
                && ((dataCfgApplyPo.getOid() == null) || (dataCfgApplyPo.getActionType() != DataCfgApplyPo.ActionType.EDIT))) {

            throw new OpdException(ErrorCodeEnum.ERR_2020001_EXCEPTION);
        }
        if (!dataCfgPo.isEnable().booleanValue()) {
            throw new OpdException(ErrorCodeEnum.ERR_2020003_EXCEPTION);
        }

        if (this.isVerify.booleanValue()) {
            dataCfgApplyPo.setActionType(DataCfgApplyPo.ActionType.EDIT);
            dataCfgApplyPo.setDataStatus(DataCfgApplyPo.DataStatus.APPLY);
            dataCfgApplyPo.setApplyTime(new Date());
            dataCfgPo.setApplied(Boolean.valueOf(true));
            back = (DataCfgApplyPo) this.dataCfgApplyDao.save(dataCfgApplyPo);
            this.dataCfgDao.update(dataCfgPo);
        } else {
            dataCfgPo = dataCfgApplyPo.getDataCfgPo();
            copyProperties(dataCfgPo, dataCfgApplyPo);
            this.dataCfgDao.update(dataCfgPo);

            saveDataCfgVersion(dataCfgPo);
        }
        return back;
    }

    @Override
    public DataCfgApplyPo disableApply(DataCfgApplyPo dataCfgApplyPo) {
        DataCfgApplyPo back = null;
        DataCfgPo dataCfgPo = dataCfgApplyPo.getDataCfgPo();
        if (dataCfgPo == null) {
            throw new OpdException(ErrorCodeEnum.ERR_2020004_EXCEPTION);
        }
        if (dataCfgPo.isApplied().booleanValue()) {
            throw new OpdException(ErrorCodeEnum.ERR_2020001_EXCEPTION);
        }
        if (!dataCfgPo.isEnable().booleanValue()) {
            throw new OpdException(ErrorCodeEnum.ERR_2020003_EXCEPTION);
        }

        dataCfgApplyPo.setActionType(DataCfgApplyPo.ActionType.DISABLE);
        dataCfgApplyPo.setDataStatus(DataCfgApplyPo.DataStatus.APPLY);
        if (this.isVerify.booleanValue()) {
            dataCfgApplyPo.setApplyTime(new Date());
            back = (DataCfgApplyPo) this.dataCfgApplyDao.save(dataCfgApplyPo);
            if (dataCfgPo != null) {
                dataCfgPo.setApplied(Boolean.valueOf(true));
                this.dataCfgDao.update(dataCfgPo);
            }
        } else {
            dataCfgPo.setEnable(Boolean.valueOf(false));
            dataCfgPo.setLastEditUserName(dataCfgApplyPo.getApplyUserName());
            dataCfgPo.setLastEditTime(new Date(System.currentTimeMillis()));
            dataCfgPo.setStartTime(dataCfgApplyPo.getStartTime());
            dataCfgPo.setEndTime(dataCfgApplyPo.getEndTime());
            this.dataCfgDao.update(dataCfgPo);

            saveDataCfgVersion(dataCfgPo);
        }
        return back;
    }

    @Override
    public void cancelApply(DataCfgApplyPo dataCfgApplyPo) {
        DataCfgPo dataCfgPo = dataCfgApplyPo.getDataCfgPo();
        if (dataCfgPo != null) {
            dataCfgPo.setDataCfgApplyPo(null);
            dataCfgPo.setApplied(Boolean.FALSE);
            this.dataCfgDao.update(dataCfgPo);
        }
        this.dataCfgApplyDao.delete(dataCfgApplyPo);
    }

    @Override
    public DataCfgPo setAgree(String oid) throws Exception {
        return setAgree(oid, null);
    }

    @Override
    public DataCfgPo setAgree(String oid, DataInOptionsVo optionsVo) throws Exception {
        Date currentTime = new Date(System.currentTimeMillis());
        DataCfgApplyPo dataCfgApplyPo = this.dataCfgApplyDao.findById(oid);
        DataCfgPo dataCfgPo = dataCfgApplyPo.getDataCfgPo();
        DataCfgApplyPo.ActionType type = dataCfgApplyPo.getActionType();
        if (DataCfgApplyPo.ActionType.ENABLE.equals(type)) {
            if (dataCfgPo == null) {
                dataCfgPo = new DataCfgPo();
                copyProperties(dataCfgPo, dataCfgApplyPo);
                dataCfgPo.setEnable(Boolean.valueOf(true));
                dataCfgPo.setPublicTime(currentTime);
                dataCfgPo.setCreateTime(currentTime);
            } else {
                dataCfgPo.setEnable(Boolean.valueOf(true));
                dataCfgPo.setPublicTime(currentTime);
                dataCfgPo.setLastEditUserName(dataCfgApplyPo.getApplyUserName());
                dataCfgPo.setLastEditTime(currentTime);
                dataCfgPo.setStartTime(dataCfgApplyPo.getStartTime());
                dataCfgPo.setEndTime(dataCfgApplyPo.getEndTime());
            }
        } else if (DataCfgApplyPo.ActionType.EDIT.equals(type)) {
            copyProperties(dataCfgPo, dataCfgApplyPo);
        } else if (DataCfgApplyPo.ActionType.DISABLE.equals(type)) {
            dataCfgPo.setEnable(Boolean.valueOf(false));
            dataCfgPo.setLastEditUserName(dataCfgApplyPo.getApplyUserName());
            dataCfgPo.setLastEditTime(currentTime);
        }

        dataCfgPo.setDataCfgApplyPo(null);
        dataCfgPo.setApplied(Boolean.valueOf(false));

        if (((DataCfgApplyPo.ActionType.ENABLE.equals(type)) && (dataCfgPo.getOid() == null))
                || (DataCfgApplyPo.ActionType.EDIT.equals(type))) {

            if ((DataCfgApplyPo.ActionType.ENABLE.equals(type)) && (dataCfgPo.getOid() == null)) {
                dataCfgPo = this.dataCfgDao.save(dataCfgPo);
                this.dataCfgDao.flush();
            }
            DataCfgFileApplyPo dataCfgFileApplyPo = dataCfgApplyPo.getDataCfgFileApplyPo();

            if (dataCfgFileApplyPo != null) {
                String fileName = dataCfgFileApplyPo.getName();
                long fileSize = dataCfgFileApplyPo.getSize();
                byte[] fileContent = dataCfgFileApplyPo.getContent();
                this.dataInService.uploadFile(dataCfgPo.getOid(), fileName, fileSize, fileContent, optionsVo);
            }
        } else {
            this.dataCfgDao.update(dataCfgPo);
        }
        this.dataCfgApplyDao.delete(dataCfgApplyPo);

        saveDataCfgVersion(dataCfgPo);
        return dataCfgPo;
    }

    @Override
    public void setRefuse(String oid) {
        DataCfgApplyPo dataCfgApplyPo = (DataCfgApplyPo) this.dataCfgApplyDao.findById(oid);
        dataCfgApplyPo.setDataStatus(DataCfgApplyPo.DataStatus.REFUSE);
        dataCfgApplyPo.setRefuseTime(new Date());
        this.dataCfgApplyDao.update(dataCfgApplyPo);
    }

    @Override
    public List<DataCfgPo> findHide() {
        return this.dataCfgDao.findHide();
    }

    @Override
    public DataSetPo addDataSet(DataSetPo dataSetPo) {
        return (DataSetPo) this.dataSetDao.save(dataSetPo);
    }

    @Override
    public void deleteDataSet(DataSetPo dataSetPo) {
        this.dataSetDao.delete(dataSetPo);
    }

    @Override
    public void updateDataSet(DataSetPo dataSetPo) {
        this.dataSetDao.update(dataSetPo);
    }

    @Override
    public List<DataCfgVerPo> findVerByDataCfgOid(String dataCfgOid) {
        return this.dataCfgVerDao.findVerByDataCfgOid(dataCfgOid);
    }

    @Override
    public DataCfgApplyPo findDataCfgApplyPoById(String oid) {
        return (DataCfgApplyPo) this.dataCfgApplyDao.findById(oid);
    }

    @Override
    public void removeDataCfg(String dataCfgOid) {
        List<DataCfgVerPo> removeDataCfgVerList = this.dataCfgVerDao.findVerByDataCfgOid(dataCfgOid);
        if (removeDataCfgVerList.size() != 0) {
            this.dataCfgVerDao.deleteAll(removeDataCfgVerList);
        }

        this.logService.removeLogByDataCfgOid(dataCfgOid);

        this.dataCfgDao.deleteById(dataCfgOid);
        this.dataCfgDao.flush();
    }

    @Override
    public List<DataCfgPo> findPublicDataCfg() {
        return this.dataCfgDao.findPublicDataCfg();
    }

    @Override
    public Pager findPublicDataCfg(Pager pager) {
        return this.dataCfgDao.findPublicDataCfg(pager);
    }

    @Override
    public void updateDataCountByOid(String oid, int dataCount) {
        DataCfgPo dataCfgPo = (DataCfgPo) this.dataCfgDao.findById(oid);
        if (dataCfgPo != null) {
            dataCfgPo.setDataCount(dataCount);
            this.dataCfgDao.update(dataCfgPo);
        }
    }

    @Override
    public void updateResourceModifiedDateByOid(String oid, Date resourceModifiedDate) {
        DataCfgPo dataCfgPo = (DataCfgPo) this.dataCfgDao.findById(oid);
        if (dataCfgPo != null) {
            dataCfgPo.setResourceModifiedDate(resourceModifiedDate);
            dataCfgPo.setUpdateTime(resourceModifiedDate);
            this.dataCfgDao.update(dataCfgPo);
        }
    }

    private void copyProperties(DataCfgPo dataCfgPo, DataCfgApplyPo dataCfgApplyPo) {
        dataCfgPo.setName(dataCfgApplyPo.getName());
        dataCfgPo.setDescription(dataCfgApplyPo.getDescription());
        dataCfgPo.setLastEditUserName(dataCfgApplyPo.getApplyUserName());
        dataCfgPo.setStructured(dataCfgApplyPo.isStructured());
        dataCfgPo.setEnable(dataCfgApplyPo.isEnable());
        dataCfgPo.setPublic(dataCfgApplyPo.isPublic());
        dataCfgPo.setNoPublicReason(dataCfgApplyPo.getNoPublicReason());
        dataCfgPo.setDataSetPo(dataCfgApplyPo.getDataSetPo());
        dataCfgPo.setLastEditTime(new Date(System.currentTimeMillis()));
        dataCfgPo.setStartTime(dataCfgApplyPo.getStartTime());
        dataCfgPo.setEndTime(dataCfgApplyPo.getEndTime());

        dataCfgPo.getDataFieldCfgPoList().clear();

        List<DataFieldCfgPo> dataFieldCfglist = dataCfgPo.getDataFieldCfgPoList();
        List<DataFieldCfgApplyPo> dataFieldCfgApplyList = dataCfgApplyPo.getDataFieldCfgApplyPoList();
        for (DataFieldCfgApplyPo dataFieldCfgApplyPo : dataFieldCfgApplyList) {
            DataFieldCfgPo dataFieldCfgPo = new DataFieldCfgPo();
            dataFieldCfgPo.setFieldName(dataFieldCfgApplyPo.getFieldName());
            dataFieldCfgPo.setDispName(dataFieldCfgApplyPo.getDispName());
            dataFieldCfgPo.setFieldOrder(dataFieldCfgApplyPo.getFieldOrder());
            dataFieldCfgPo.setFieldType(dataFieldCfgApplyPo.getFieldType());
            dataFieldCfgPo.setCheckMethod(dataFieldCfgApplyPo.getCheckMethod());
            dataFieldCfgPo.setCheckRule(dataFieldCfgApplyPo.getCheckRule());
            dataFieldCfgPo.setPublic(dataFieldCfgApplyPo.isPublic());
            dataFieldCfgPo.setDataCfgPo(dataCfgPo);
            dataFieldCfglist.add(dataFieldCfgPo);
        }
        dataCfgPo.setDataFieldCfgPoList(dataFieldCfglist);

        dataCfgPo.getDataCfgMetadataPoList().clear();

        List<DataCfgMetadataPo> dataCfgInfoPoList = dataCfgPo.getDataCfgMetadataPoList();
        List<DataCfgMetadataApplyPo> dataCfgMetadataApplyPoList = dataCfgApplyPo.getDataCfgMetadataApplyPoList();
        for (DataCfgMetadataApplyPo dataCfgMetadataApplyPo : dataCfgMetadataApplyPoList) {
            DataCfgMetadataPo dataCfgMetadataPo = new DataCfgMetadataPo();
            dataCfgMetadataPo.setMetadataKey(dataCfgMetadataApplyPo.getMetadataKey());
            dataCfgMetadataPo.setMetadataValue(dataCfgMetadataApplyPo.getMetadataValue());
            dataCfgMetadataPo.setCommon(dataCfgMetadataApplyPo.isCommon());
            dataCfgMetadataPo.setDataCfgPo(dataCfgPo);
            dataCfgInfoPoList.add(dataCfgMetadataPo);
        }
        dataCfgPo.setDataCfgMetadataPoList(dataCfgInfoPoList);
    }

    private void copyProperties(DataCfgVerPo dataCfgVerPo, DataCfgPo dataCfgPo) {
        dataCfgVerPo.setName(dataCfgPo.getName());
        dataCfgVerPo.setDescription(dataCfgPo.getDescription());
        if (dataCfgPo.getDataSetPo() != null)
            dataCfgVerPo.setDataSetName(dataCfgPo.getDataSetPo().getName());
        dataCfgVerPo.setStructured(dataCfgPo.isStructured());
        dataCfgVerPo.setActive(dataCfgPo.isActive());
        dataCfgVerPo.setEnable(dataCfgPo.isEnable());
        dataCfgVerPo.setPublic(dataCfgPo.isPublic());
        dataCfgVerPo.setNoPublicReason(dataCfgPo.getNoPublicReason());
        dataCfgVerPo.setEditUserName(dataCfgPo.getLastEditUserName());
        dataCfgVerPo.setStartTime(dataCfgPo.getStartTime());
        dataCfgVerPo.setEndTime(dataCfgPo.getEndTime());

        List<DataFieldCfgVerPo> dataFieldCfgVerlist = new ArrayList();
        List<DataFieldCfgPo> dataFieldCfgList = dataCfgPo.getDataFieldCfgPoList();
        for (DataFieldCfgPo dataFieldCfgPo : dataFieldCfgList) {
            DataFieldCfgVerPo dataFieldCfgVerPo = new DataFieldCfgVerPo();
            dataFieldCfgVerPo.setFieldName(dataFieldCfgPo.getFieldName());
            dataFieldCfgVerPo.setDispName(dataFieldCfgPo.getDispName());
            dataFieldCfgVerPo.setFieldOrder(dataFieldCfgPo.getFieldOrder());
            dataFieldCfgVerPo.setFieldType(dataFieldCfgPo.getFieldType());
            dataFieldCfgVerPo.setCheckMethod(dataFieldCfgPo.getCheckMethod());
            dataFieldCfgVerPo.setCheckRule(dataFieldCfgPo.getCheckRule());
            dataFieldCfgVerPo.setPublic(dataFieldCfgPo.isPublic());
            dataFieldCfgVerPo.setDataCfgVerPo(dataCfgVerPo);
            dataFieldCfgVerlist.add(dataFieldCfgVerPo);
        }
        dataCfgVerPo.setDataFieldCfgVerPoList(dataFieldCfgVerlist);

        List<DataCfgMetadataPo> dataCfgMetadataPoList = dataCfgPo.getDataCfgMetadataPoList();
        List<DataCfgVerMetadataPo> dataCfgVerMetadataPoList = new ArrayList();
        for (DataCfgMetadataPo dataCfgMetadataPo : dataCfgMetadataPoList) {
            DataCfgVerMetadataPo dataCfgVerMetadataPo = new DataCfgVerMetadataPo();
            dataCfgVerMetadataPo.setMetadataKey(dataCfgMetadataPo.getMetadataKey());
            dataCfgVerMetadataPo.setMetadataValue(dataCfgMetadataPo.getMetadataValue());
            dataCfgVerMetadataPo.setCommon(dataCfgMetadataPo.isCommon());
            dataCfgVerMetadataPo.setDataCfgVerPo(dataCfgVerPo);
            dataCfgVerMetadataPoList.add(dataCfgVerMetadataPo);
        }
        dataCfgVerPo.setDataCfgVerMetadataList(dataCfgVerMetadataPoList);
    }

    private void saveDataCfgVersion(DataCfgPo dataCfgPo) {
        DataCfgVerPo dataCfgVerPo = new DataCfgVerPo();
        copyProperties(dataCfgVerPo, dataCfgPo);
        dataCfgVerPo.setDataCfgPo(dataCfgPo);
        dataCfgVerPo.setLogTime(new Date());
        this.dataCfgVerDao.save(dataCfgVerPo);
    }

    @Override
    public List<DataCfgPo> findPublicByDataSetOid(String oid) {
        return this.dataCfgDao.findPublicByDataSetOid(oid);
    }

    @Override
    public Map<String, List<DataCfgZipFilePo>> getZipFileMapKeyBySourceType(DataCfgPo dataCfgPo) {
        Map<String, List<DataCfgZipFilePo>> sourceTypeToZipFilePoMap = new HashMap();
        List<DataCfgZipFilePo> dataCfgZipFilePoList = dataCfgPo.getDataCfgZipFilePoList();

        for (String key : this.sourceTypeList) {
            sourceTypeToZipFilePoMap.put(key, new ArrayList());
        }

        if (dataCfgZipFilePoList != null) {

            for (DataCfgZipFilePo zipFilePo : dataCfgZipFilePoList) {
                List<DataCfgZipFilePo> tempList = (List) sourceTypeToZipFilePoMap.get(zipFilePo.getSourceType());

                if (tempList != null) {
                    tempList.add(zipFilePo);
                }
            }
        }

        return sourceTypeToZipFilePoMap;
    }

    @Override
    public void deleteAllCfgZipFile(DataCfgPo dataCfgPo) {
        List<DataCfgZipFilePo> dataCfgZipFilePoList = dataCfgPo.getDataCfgZipFilePoList();
        dataCfgZipFilePoList.clear();
        this.dataCfgDao.save(dataCfgPo);
    }

    @Override
    public void deleteAllCfgZipFileByType(DataCfgPo dataCfgPo, String sourceType) {
        dataCfgPo.getDataCfgZipFilePoList().removeAll((Collection) getZipFileMapKeyBySourceType(dataCfgPo).get(sourceType));

        this.dataCfgDao.save(dataCfgPo);
    }

    @Override
    public Map<String, List<DataFieldCfgPo>> getColumnFiledCfgMapByDataCfgPo(DataCfgPo po) {
        List<DataFieldCfgPo> dataFieldCfgPoList = po.getDataFieldCfgPoList();

        Map<String, List<DataFieldCfgPo>> columnFiledCfgMap = new HashMap();

        for (DataFieldCfgPo dataFieldCfgPo : dataFieldCfgPoList) {
            if (this.fieldTypeMap.get(dataFieldCfgPo.getFieldType()) != null) {
                List<DataFieldCfgPo> temp_List;
                if ((temp_List = (List) columnFiledCfgMap.get(dataFieldCfgPo.getFieldName())) == null) {
                    temp_List = new ArrayList();
                    columnFiledCfgMap.put(dataFieldCfgPo.getFieldName(), temp_List);
                }
                temp_List.add(dataFieldCfgPo);
            }
        }
        return columnFiledCfgMap;
    }

    @Override
    public DataCfgFileDto getDataCfgFileDto(String dataCfgOid) {
        return this.dataCfgDao.getDataCfgFileDto(dataCfgOid);
    }

    @Override
    public int getSumOfAllDataCfgDataCount() {
        return this.dataCfgDao.getSumOfAllDataCfgDataCount();
    }
}