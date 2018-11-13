package com.iisi.opd.cfg.dao.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.iisi.common.dao.impl.GenericDaoImpl;
import com.iisi.opd.cfg.dao.DataCfgZipFileDao;
import com.iisi.opd.cfg.dto.DataCfgZipFileDto;
import com.iisi.opd.cfg.po.DataCfgZipFilePo;

@Repository
public class DataCfgZipFileDaoImpl extends GenericDaoImpl<DataCfgZipFilePo> implements DataCfgZipFileDao {
    @Override
    public List<DataCfgZipFilePo> findByDataCfgOid(String dataCfgPoOid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgZipFilePo.class);

        DetachedCriteria categoryCriteria = criteria.createCriteria("dataCfgPo");
        categoryCriteria.add(Restrictions.eq("oid", dataCfgPoOid));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgZipFilePo> findByDataCfgOid(String dataCfgPoOid, String sourceType) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgZipFilePo.class);
        criteria.add(Restrictions.eq("sourceType", sourceType));

        DetachedCriteria categoryCriteria = criteria.createCriteria("dataCfgPo");
        categoryCriteria.add(Restrictions.eq("oid", dataCfgPoOid));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgZipFileDto> getDataCfgZipFileDtoList(String dataCfgOid) {
        StringBuffer sqlQueryStatment = new StringBuffer();
        sqlQueryStatment.append("SELECT oid                      \n");
        sqlQueryStatment.append("      ,md5                      \n");
        sqlQueryStatment.append("      ,size                     \n");
        sqlQueryStatment.append("      ,source_type              \n");
        sqlQueryStatment.append("      ,file_name                \n");
        sqlQueryStatment.append("  FROM dbo.od_data_cfg_zip_file \n");
        sqlQueryStatment.append(" WHERE od_data_cfg_oid = ?      \n");

        List<Object[]> objList = (List<Object[]>) getNativeSqlQueryList(sqlQueryStatment.toString(), new Object[] { dataCfgOid });

        List<DataCfgZipFileDto> list = new ArrayList();
        for (Object[] obj : objList) {
            DataCfgZipFileDto dataCfgZipFileDto = new DataCfgZipFileDto();
            dataCfgZipFileDto.setOid(obj[0].toString());
            dataCfgZipFileDto.setMd5(obj[1].toString());
            dataCfgZipFileDto.setSize(((BigDecimal) obj[2]).longValue());
            dataCfgZipFileDto.setSourceType(obj[3].toString());
            dataCfgZipFileDto.setZipFileName(obj[4].toString());
            list.add(dataCfgZipFileDto);
        }
        return list;
    }

    @Override
    public Blob createBlob(InputStream inputStream, long fileLength) {
        return Hibernate.getLobCreator(getSession()).createBlob(inputStream, fileLength);
    }
}