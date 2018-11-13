package com.iisi.opd.cfg.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.opd.cfg.dao.DataCfgFileDao;
import com.iisi.opd.cfg.po.DataCfgFilePo;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.service.DataCfgFile2Service;
import com.iisi.opd.cfg.service.DataCfgFileService;
import com.iisi.opd.cfg.service.DataCfgService;

@Service
@Transactional
public class DataCfgFileServiceImpl implements DataCfgFileService {
    @Autowired
    private DataCfgFile2Service dataCfgFile2ServiceImpl;
    @Autowired
    private DataCfgService dataCfgServiceImpl;
    @Autowired
    private DataCfgFileDao dataCfgFileDaoImpl;

    @Override
    public void saveArgsByDataCfgOid(String dataCfgOid, String fileName, long fileSize, byte[] fileContent) {
        DataCfgFilePo dataCfgFilePo = this.dataCfgFileDaoImpl.findByDataCfgOid(dataCfgOid);

        if (dataCfgFilePo != null) {
            dataCfgFilePo.setName(fileName);
            dataCfgFilePo.setSize(fileSize);
            dataCfgFilePo.setContent(fileContent);
            this.dataCfgFileDaoImpl.save(dataCfgFilePo);
        } else {
            DataCfgPo dataCfgPo = this.dataCfgServiceImpl.findByOid(dataCfgOid);
            dataCfgFilePo = new DataCfgFilePo();
            dataCfgFilePo.setName(fileName);
            dataCfgFilePo.setSize(fileSize);
            dataCfgFilePo.setContent(fileContent);
            dataCfgFilePo.setDataCfgPo(dataCfgPo);
            this.dataCfgFileDaoImpl.save(dataCfgFilePo);
            this.dataCfgFileDaoImpl.flush();
        }
    }

    @Override
    public void saveArgsByDataCfgOid(String dataCfgOid, String fileName, long fileSize, File file) {
        try {
            saveArgsByDataCfgOid(dataCfgOid, fileName, fileSize, FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            //
        }
    }
}