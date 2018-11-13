package com.iisi.opd.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.common.util.PagerUtils;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.service.DataSetService;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;
import com.iisi.opd.log.po.DataAccessLogPo;
import com.iisi.opd.log.po.DataFileLogPo;
import com.iisi.opd.log.po.DataInLogPo;
import com.iisi.opd.log.po.DataOutLogPo;
import com.iisi.opd.log.po.DataServiceLogPo;
import com.iisi.opd.log.service.LogService;
import com.iisi.opd.report.dao.Ntpc2Dao;
import com.iisi.opd.report.service.ReportService;
import com.iisi.opd.report.vo.DataFileDownloadCountVo;
import com.iisi.opd.report.vo.DataOutCountVo;
import com.iisi.opd.report.vo.DataServiceCountVo;
import com.iisi.opd.report.vo.DataSetViewVo;
import com.iisi.opd.report.vo.UnitCountVo;
import com.iisi.opd.search.service.DataSearchService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private LogService logService;
    @Autowired
    private DataSearchService dataSearchService;
    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private Ntpc2Dao ntpc2Dao;
    @Autowired
    private PagerUtils pagerUtils;

    @Override
    public List<DataSetViewVo> getDataSetViewStatistic(java.sql.Date startDateTime, java.sql.Date endDateTime) {
        HashMap<String, DataSetViewVo> counter = new HashMap();

        Map<String, Long> asscessMap = this.logService.getDataSetAccessViewStatistic(startDateTime, endDateTime);

        List<DataSetPo> dataSetPos = this.dataSetService.getAllOriginalDataSetList();
        for (DataSetPo dataSetPo : dataSetPos) {
            DataSetViewVo vo = new DataSetViewVo();
            counter.put(dataSetPo.getOid(), vo);
            vo.setDataSetOid(dataSetPo.getOid());
            vo.setStartDateTime(startDateTime);
            vo.setEndDateTime(endDateTime);

            Long accessCount;
            if ((accessCount = (Long) asscessMap.get(dataSetPo.getOid())) == null) {
                accessCount = Long.valueOf(0L);
            }

            vo.addViewCount(accessCount.longValue());
        }

        List<DataSetViewVo> back = new ArrayList();
        back.addAll(counter.values());
        return back;
    }

    @Override
    public List<DataFileDownloadCountVo> getDataFileDownloadCountStatistic(java.sql.Date startDateTime,
            java.sql.Date endDateTime) {
        HashMap<String, DataFileDownloadCountVo> counter = new HashMap();

        Map<String, Long[]> fileDownloadMap = this.logService.getDataFileDownloadCountStatistic(startDateTime, endDateTime);

        List<DataSetPo> dataSetPos = this.dataSetService.getAllOriginalDataSetList();
        for (DataSetPo dataSetPo : dataSetPos) {
            DataFileDownloadCountVo vo = new DataFileDownloadCountVo();
            counter.put(dataSetPo.getOid(), vo);
            vo.setDataSetOid(dataSetPo.getOid());
            vo.setStartDateTime(startDateTime);
            vo.setEndDateTime(endDateTime);

            Long[] fileDownloads;
            if ((fileDownloads = (Long[]) fileDownloadMap.get(dataSetPo.getOid())) != null) {
                vo.setDownloadCounts(fileDownloads[0]);
                vo.setDownloadSize(fileDownloads[1]);
            }
        }

        List<DataFileDownloadCountVo> back = new ArrayList();
        back.addAll(counter.values());
        return back;
    }

    @Override
    @Deprecated
    public List<DataServiceCountVo> getDataServiceCountStatistic(java.sql.Date startDateTime, java.sql.Date endDateTime) {
        HashMap<String, DataServiceCountVo> counter = new HashMap();

        List<DataSetPo> dataSetPos = this.dataSetService.getAllOriginalDataSetList();
        for (DataSetPo dataSetPo : dataSetPos) {
            DataServiceCountVo vo = new DataServiceCountVo();
            vo.setDataSetOid(dataSetPo.getOid());
            vo.setServiceCount(Long.valueOf(0L));
            vo.setStartDateTime(startDateTime);
            vo.setEndDateTime(endDateTime);
            counter.put(dataSetPo.getOid(), vo);
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(DataServiceLogPo.class);
        criteria.add(Restrictions.between("logTime", startDateTime, endDateTime));
        List<DataServiceLogPo> dataServiceLogPos = this.logService.findDataServiceLogPoByCriteria(criteria);
        for (DataServiceLogPo dataServiceLogPo : dataServiceLogPos) {
            DataServiceCountVo vo = (DataServiceCountVo) counter.get(dataServiceLogPo.getDataSetOid());
            vo.setServiceCount(Long.valueOf(vo.getServiceCount().longValue() + 1L));
        }
        List<DataServiceCountVo> back = new ArrayList();
        back.addAll(counter.values());
        return back;
    }

    @Override
    public List<DataOutCountVo> getDataOutCountStatistic(java.sql.Date startDateTime, java.sql.Date endDateTime) {
        HashMap<String, DataOutCountVo> counter = new HashMap();

        Map<String, Map<String, Long>> dataOutMap = this.logService.getDataOutCountStatistic(startDateTime, endDateTime);

        List<DataSetPo> dataSetPos = this.dataSetService.getAllOriginalDataSetList();
        for (DataSetPo dataSetPo : dataSetPos) {
            DataOutCountVo vo = new DataOutCountVo();
            counter.put(dataSetPo.getOid(), vo);
            vo.setDataSetOid(dataSetPo.getOid());
            vo.setStartDateTime(startDateTime);
            vo.setEndDateTime(endDateTime);

            Map<String, Long> typeMap;
            if ((typeMap = (Map) dataOutMap.get(dataSetPo.getOid())) != null) {
                vo.setDownloadCounts(typeMap);
            }
        }
        List<DataOutCountVo> back = new ArrayList();
        back.addAll(counter.values());
        return back;
    }

    @Override
    public List<UnitCountVo> getUnitCountStatistic(java.sql.Date startDateTime, java.sql.Date endDateTime) {
        Map<String, Long> asscessMap = this.logService.getDataSetAccessViewStatistic(startDateTime, endDateTime);

        Map<String, Long> dataOutMap = this.logService.getDataOutCountWithNoTypeStatistic(startDateTime, endDateTime);

        Map<String, Long[]> fileDownloadMap = this.logService.getDataFileDownloadCountStatistic(startDateTime, endDateTime);

        List<DataSetPo> dataSetPos = this.dataSetService.getAllOriginalDataSetList();
        return getUnitCountStatisticWithEnable(asscessMap, dataOutMap, fileDownloadMap, dataSetPos, startDateTime, endDateTime);
    }

    @Override
    public List<UnitCountVo> getUnitCountStatisticWithEnable(java.sql.Date startDateTime, java.sql.Date endDateTime) {
        Map<String, Long> asscessMap = this.logService.getDataSetAccessViewStatistic(startDateTime, endDateTime);

        Map<String, Long> dataOutMap = this.logService.getDataOutCountWithNoTypeStatistic(startDateTime, endDateTime);

        Map<String, Long[]> fileDownloadMap = this.logService.getDataFileDownloadCountStatistic(startDateTime, endDateTime);

        List<DataSetPo> dataSetPos = this.dataSetService.findAllEnable();

        return getUnitCountStatisticWithEnable(asscessMap, dataOutMap, fileDownloadMap, dataSetPos, startDateTime, endDateTime);
    }

    private List<UnitCountVo> getUnitCountStatisticWithEnable(Map<String, Long> asscessMap, Map<String, Long> dataOutMap,
            Map<String, Long[]> fileDownloadMap, List<DataSetPo> dataSetPos, java.sql.Date startDateTime,
            java.sql.Date endDateTime) {
        HashMap<String, UnitCountVo> counter = new HashMap();
        for (DataSetPo dataSetPo : dataSetPos) {
            UnitCountVo vo = new UnitCountVo();
            counter.put(dataSetPo.getOid(), vo);

            vo.setUnitOid(dataSetPo.getUnitPo().getOid());
            vo.setDataSetOid(dataSetPo.getOid());
            vo.setServiceCount(0L);
            vo.setStartDateTime(startDateTime);
            vo.setEndDateTime(endDateTime);
            counter.put(dataSetPo.getOid(), vo);

            Long accessCount;
            if ((accessCount = (Long) asscessMap.get(dataSetPo.getOid())) == null)
                accessCount = Long.valueOf(0L);
            vo.addViewCount(accessCount.longValue());

            Long[] fileDownloads;
            if ((fileDownloads = (Long[]) fileDownloadMap.get(dataSetPo.getOid())) != null) {
                vo.setDownloadCount(fileDownloads[0].longValue());
            }
            Long dataOutCount;
            if ((dataOutCount = (Long) dataOutMap.get(dataSetPo.getOid())) == null)
                dataOutCount = Long.valueOf(0L);
            vo.setDataOutCount(dataOutCount.longValue());
        }

        List<UnitCountVo> back = new ArrayList();
        back.addAll(counter.values());
        return back;
    }

    @Override
    public Map<String, Integer> getSiteAccessCountStatistic(java.util.Date startDateTime, java.util.Date endDateTime) {
        List<Object[]> list = this.logService.findSiteAccessLogCountData(startDateTime, endDateTime);
        Map<String, Integer> map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            String key = objs[0].toString();
            Integer value = Integer.valueOf(Integer.parseInt(objs[1].toString()));
            map.put(key, value);
        }

        return map;
    }

    @Override
    public Map<DataCfgPo, List<DataInLogPo>> getDataInLog(java.util.Date startDateTime, java.util.Date endDateTime,
            String dataSetName, Boolean success) throws OpdException {
        if ((dataSetName == null) || (dataSetName.length() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_1000100_EXCEPTION);
        }
        Map<DataCfgPo, List<DataInLogPo>> result = new HashMap();
        List<DataSetPo> datasets = this.dataSearchService.dataSetAdvancedSearch(dataSetName, null, null, null, null, null);
        System.out.println("查出" + datasets.size() + "個資料集");
        for (DataSetPo dataset : datasets) {
            for (DataCfgPo cfg : dataset.getDataCfgPoList()) {
                DetachedCriteria criteria = DetachedCriteria.forClass(DataInLogPo.class);
                if ((startDateTime != null) || (endDateTime != null)) {
                    if ((startDateTime != null) && (endDateTime == null)) {
                        criteria.add(Restrictions.ge("logTime", startDateTime));
                    } else if ((startDateTime == null) && (endDateTime != null)) {
                        criteria.add(Restrictions.le("logTime", endDateTime));
                    } else if ((startDateTime != null) && (endDateTime != null))
                        criteria.add(Restrictions.between("logTime", startDateTime, endDateTime));
                }
                if (success != null) {
                    if (success.booleanValue()) {
                        criteria.add(Restrictions.eq("execResult", DataInLogPo.ExecResult.SUCCESS));
                    } else {
                        criteria.add(Restrictions.not(Restrictions.eq("execResult", DataInLogPo.ExecResult.SUCCESS)));
                    }
                }
                criteria.add(Restrictions.eq("dataCfgOid", cfg.getOid()));
                List<DataInLogPo> datainlogpos = this.logService.findDataInLogPoByCriteria(criteria);
                if ((datainlogpos != null) && (datainlogpos.size() != 0)) {
                    result.put(cfg, datainlogpos);
                }
            }
        }
        return result;
    }

    @Override
    @Deprecated
    public UnitCountVo getDataSetSpecificStatistic(String dataSetOid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataAccessLogPo.class);
        criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
        long dataAccessCount = this.logService.getRowCount(criteria);

        criteria = DetachedCriteria.forClass(DataFileLogPo.class);
        criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
        long downloadCount = this.logService.getRowCount(criteria);

        criteria = DetachedCriteria.forClass(DataOutLogPo.class);
        criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
        long dataOutCount = this.logService.getRowCount(criteria);

        UnitCountVo vo = new UnitCountVo();
        vo.setDataSetOid(dataSetOid);
        vo.setDataOutCount(dataOutCount);
        vo.setDownloadCount(downloadCount);
        vo.setViewCount(dataAccessCount);

        return vo;
    }
}
