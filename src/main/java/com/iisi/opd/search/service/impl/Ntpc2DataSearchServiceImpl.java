package com.iisi.opd.search.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.util.Pager;
import com.iisi.common.util.PagerUtils;
import com.iisi.opd.cfg.dao.Ntpc2DataSetDao;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.search.service.Ntpc2DataSearchService;

@Service
@Transactional
public class Ntpc2DataSearchServiceImpl implements Ntpc2DataSearchService {
    @Autowired
    private Ntpc2DataSetDao ntpc2DataSetDao;
    @Autowired
    private PagerUtils pagerUtils;

    @Override
    public List<DataSetPo> dataSetMultiCollectionSearch(List<String> unitOidList, List<String> categoryOidList, Date startTime,
            Date endTime, boolean isSearchByUpdateTime, DataSetPo.OrderByOption orderByOption) {
        return dataSetMultiCollectionSearch(unitOidList, categoryOidList, startTime, endTime, isSearchByUpdateTime, null, null,
                orderByOption);
    }

    @Override
    public List<DataSetPo> dataSetMultiCollectionSearch(List<String> unitOidList, List<String> categoryOidList, Date startTime,
            Date endTime, boolean isSearchByUpdateTime, String mataDataKey, List<String> mataDataValueList,
            DataSetPo.OrderByOption orderByOption) {
        IntervalBean interval;
        if (isSearchByUpdateTime) {
            interval = new IntervalBean("dataCfgPoList.updateTime", startTime, endTime);
        } else {
            interval = new IntervalBean("createTime", startTime, endTime);
        }
        return this.ntpc2DataSetDao.dataSetMultiCollectionSearch(unitOidList, categoryOidList, interval, mataDataKey,
                mataDataValueList, orderByOption);
    }

    @Override
    public Pager dataSetMultiCollectionSearch(Pager pager, List<String> unitOidList, List<String> categoryOidList, Date startTime,
            Date endTime, boolean isSearchByUpdateTime, DataSetPo.OrderByOption orderByOption) {
        return dataSetMultiCollectionSearch(pager, unitOidList, categoryOidList, startTime, endTime, isSearchByUpdateTime, null,
                null, orderByOption);
    }

    @Override
    public Pager dataSetMultiCollectionSearch(Pager pager, List<String> unitOidList, List<String> categoryOidList, Date startTime,
            Date endTime, boolean isSearchByUpdateTime, String mataDataKey, List<String> mataDataValueList,
            DataSetPo.OrderByOption orderByOption) {
        IntervalBean interval;
        if (isSearchByUpdateTime) {
            interval = new IntervalBean("dataCfgPoList.updateTime", startTime, endTime);
        } else {
            interval = new IntervalBean("createTime", startTime, endTime);
        }
        return this.ntpc2DataSetDao.dataSetMultiCollectionSearch(pager, unitOidList, categoryOidList, interval, mataDataKey,
                mataDataValueList, orderByOption);
    }
}
