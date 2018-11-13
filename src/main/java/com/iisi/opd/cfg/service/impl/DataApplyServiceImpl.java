package com.iisi.opd.cfg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.opd.cfg.dao.DataCfgApplyDao;
import com.iisi.opd.cfg.po.DataCfgApplyPo;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataSetApplyPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.service.DataApplyService;
import com.iisi.opd.cfg.service.DataCfgService;
import com.iisi.opd.cfg.service.DataSetService;
import com.iisi.opd.data.in.vo.DataInOptionsVo;

@Service
@Transactional
public class DataApplyServiceImpl implements DataApplyService {
    @Autowired
    private Boolean isVerify;
    @Autowired
    private DataCfgApplyDao dataCfgApplyDao;
    @Autowired
    private DataCfgService dataCfgService;
    @Autowired
    private DataSetService dataSetService;

    @Override
    public DataSetApplyPo enableApply(DataSetApplyPo dataSetApplyPo, List<DataCfgApplyPo> dataCfgApplyPos) {
        if (this.isVerify.booleanValue()) {
            dataSetApplyPo = this.dataSetService.enableApply(dataSetApplyPo);
            List<DataCfgApplyPo> newdataCfgApplyPos = new ArrayList();

            for (DataCfgApplyPo dataCfgApplyPo : dataCfgApplyPos) {
                dataCfgApplyPo.setDataSetApplyPo(dataSetApplyPo);
                dataCfgApplyPo = this.dataCfgService.enableApply(dataCfgApplyPo);
                newdataCfgApplyPos.add(dataCfgApplyPo);
            }
            dataSetApplyPo.setDataCfgApplyPoList(newdataCfgApplyPos);
            this.dataSetService.update(dataSetApplyPo);
        } else {
            dataSetApplyPo = this.dataSetService.enableApply(dataSetApplyPo);
            DataSetPo dataSetPo = dataSetApplyPo.getDataSetPo();
            List<DataCfgPo> newDataCfgPos = new ArrayList();
            List<DataCfgApplyPo> newdataCfgApplyPos = new ArrayList();
            for (DataCfgApplyPo dataCfgApplyPo : dataCfgApplyPos) {
                dataCfgApplyPo.setDataSetPo(dataSetApplyPo.getDataSetPo());
                dataCfgApplyPo = this.dataCfgService.enableApply(dataCfgApplyPo);
                newdataCfgApplyPos.add(dataCfgApplyPo);
                DataCfgPo dataCfgPo = dataCfgApplyPo.getDataCfgPo();
                newDataCfgPos.add(dataCfgPo);
            }
            dataSetPo.setDataCfgPoList(newDataCfgPos);
            dataSetApplyPo.setDataCfgApplyPoList(newdataCfgApplyPos);
        }

        return dataSetApplyPo;
    }

    @Override
    public DataSetPo agreeDataSetApply(String oid, boolean doDataCfgAlso) throws Exception {
        return agreeDataSetApply(oid, doDataCfgAlso, null);
    }

    @Override
    public DataSetPo agreeDataSetApply(String oid, boolean doDataCfgAlso, DataInOptionsVo optionsVo) throws Exception {
        DataSetApplyPo dataSetApplyPo = this.dataSetService.findDataSetApplyPoById(oid);
        List<DataCfgApplyPo> DataCfgApplyPoList = dataSetApplyPo.getDataCfgApplyPoList();

        // ! dataSet -> save dataSetVer -> save dataSetApply -> delete
        DataSetPo dataSetPo = this.dataSetService.setAgree(oid);
        List<DataCfgPo> dataCfgPoList = dataSetPo.getDataCfgPoList();

        if ((doDataCfgAlso) && (DataCfgApplyPoList != null)) {
            dataCfgPoList.clear();

            for (DataCfgApplyPo dataCfgApplyPo : DataCfgApplyPoList) {
                dataSetPo = this.dataSetService.findByOid(dataSetPo.getOid());

                dataCfgApplyPo = this.dataCfgApplyDao.findById(dataCfgApplyPo.getOid());
                dataCfgApplyPo.setDataSetPo(dataSetPo);
                this.dataCfgApplyDao.update(dataCfgApplyPo);
                // ! dataCfg -> save dataCfgVer -> save dataCfgFile -> save dataCfgApply -> delete 
                DataCfgPo dataCfgPo = this.dataCfgService.setAgree(dataCfgApplyPo.getOid(), optionsVo);

                dataCfgPo = this.dataCfgService.findByOid(dataCfgPo.getOid());
                dataSetPo = this.dataSetService.findByOid(dataSetPo.getOid());
                dataCfgPo.setDataSetPo(dataSetPo);

                dataCfgPo = this.dataCfgService.add(dataCfgPo);
                dataCfgPoList = dataSetPo.getDataCfgPoList();
                dataCfgPoList.add(dataCfgPo);
            }
        }

        return dataSetPo;
    }

    @Override
    public void refuseDataSetApply(String oid, boolean doDataCfgAlso) {
        if (doDataCfgAlso) {
            DataSetApplyPo dataSetApplyPo = this.dataSetService.findDataSetApplyPoById(oid);
            if (dataSetApplyPo.getDataCfgApplyPoList() != null) {
                List<DataCfgApplyPo> DataCfgApplyPos = dataSetApplyPo.getDataCfgApplyPoList();
                for (DataCfgApplyPo dataCfgApplyPo : DataCfgApplyPos) {
                    this.dataCfgService.setRefuse(dataCfgApplyPo.getOid());
                }
            }
        }
        this.dataSetService.setRefuse(oid);
    }

    @Override
    public Boolean isVerify() {
        return this.isVerify;
    }

    @Override
    public void setVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }
}