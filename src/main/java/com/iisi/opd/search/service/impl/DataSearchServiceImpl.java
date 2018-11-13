package com.iisi.opd.search.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.util.Pager;
import com.iisi.common.util.PagerUtils;
import com.iisi.opd.cfg.dao.DataCfgDao;
import com.iisi.opd.cfg.dao.DataSetDao;
import com.iisi.opd.cfg.po.DataCfgPo;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;
import com.iisi.opd.log.dao.DataAccessLogDao;
import com.iisi.opd.log.dao.DataAccessRejectLogDao;
import com.iisi.opd.search.service.DataSearchService;
import com.iisi.opd.tag.dao.DataTagDao;
import com.iisi.opd.tag.po.DataTagPo;

@Service
@Transactional
public class DataSearchServiceImpl implements DataSearchService {
    @Autowired
    private DataCfgDao dataCfgDao;
    @Autowired
    private DataSetDao dataSetDao;
    @Autowired
    private DataTagDao dataTagDao;
    @Autowired
    private DataAccessLogDao dataAccessLogDao;
    @Autowired
    private DataAccessRejectLogDao dataAccessRejectLogDao;
    @Autowired
    private PagerUtils pagerUtils;

    @Override
    public List<DataCfgPo> dataCfgCategorySearch(String keyword) {
        if ((keyword == null) || (keyword.trim().length() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2060001_EXCEPTION);
        }
        return this.dataCfgDao.categorySearch(keyword);
    }

    @Override
    public List<DataSetPo> dataSetCategorySearch(String keyword) {
        if ((keyword == null) || (keyword.trim().length() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2060001_EXCEPTION);
        }
        return this.dataSetDao.categorySearch(keyword);
    }

    @Override
    public List<DataCfgPo> dataCfgTagSearch(String keyword) {
        if ((keyword == null) || (keyword.trim().length() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2060002_EXCEPTION);
        }
        return this.dataCfgDao.tagSearch(keyword);
    }

    @Override
    public List<DataSetPo> dataSetTagSearch(String keyword) {
        if ((keyword == null) || (keyword.trim().length() == 0)) {
            throw new OpdException(ErrorCodeEnum.ERR_2060002_EXCEPTION);
        }
        return this.dataSetDao.tagSearch(keyword);
    }

    @Override
    public List<DataCfgPo> dataCfgLastSearch() {
        return this.dataCfgDao.lastSearch();
    }

    @Override
    public Pager dataSetLastSearch(Pager pager) {
        return this.dataSetDao.lastSearch(pager);
    }

    @Override
    public List<DataSetPo> dataSetLastSearch() {
        List<DataSetPo> dataLastList = this.dataSetDao.lastSearch();
        return dataLastList;
    }

    @Override
    public List<DataCfgPo> dataCfgAdvancedSearch(String name, String category, String tag, Date startTime, Date endTime,
            String keyword) {
        return this.dataCfgDao.advancedSearch(name, category, tag, startTime, endTime, keyword);
    }

    @Override
    public List<DataSetPo> dataSetAdvancedSearch(String name, String category, String tag, Date startTime, Date endTime,
            String keyword) {
        return dataSetAdvancedSearch(name, category, tag, startTime, endTime, keyword, null);
    }

    @Override
    public List<DataSetPo> dataSetAdvancedSearch(String name, String category, String tag, Date startTime, Date endTime,
            String keyword, List<String> unitOidList) {
        IntervalBean interval = new IntervalBean("publicTime", startTime, endTime);
        return this.dataSetDao.advancedSearch(name, category, tag, interval, keyword, unitOidList);
    }

    @Override
    public Pager dataSetAdvancedSearch(Pager pager, String name, String category, String tag, Date startTime, Date endTime,
            String keyword, List<String> unitOidList) {
        IntervalBean interval = new IntervalBean("publicTime", startTime, endTime);
        return this.dataSetDao.advancedSearch(pager, name, category, tag, interval, keyword, unitOidList);
    }

    @Override
    public List<DataCfgPo> dataCfgKeywordSearch(String keyword) {
        return this.dataCfgDao.keywordSearch(keyword);
    }

    @Override
    public Pager dataCfgKeywordSearch(Pager pager, String keyword) {
        return this.dataCfgDao.keywordSearch(pager, keyword);
    }

    @Override
    public List<DataSetPo> dataSetKeywordSearch(String keyword) {
        return this.dataSetDao.keywordSearch(keyword);
    }

    @Override
    public List<DataSetPo> dataSetKeywordSearch(String keyword, DataSetPo.OrderByOption orderByOption) {
        return this.dataSetDao.keywordSearch(keyword, orderByOption);
    }

    @Override
    public Pager dataSetKeywordSearch(Pager pager, String keyword) {
        return this.dataSetDao.keywordSearch(pager, keyword);
    }

    @Override
    public Pager dataSetKeywordSearch(Pager pager, String keyword, DataSetPo.OrderByOption orderByOption) {
        return this.dataSetDao.keywordSearch(pager, keyword, orderByOption);
    }

    @Override
    public Pager dataSetHotList(Pager pager) {
        return this.dataSetDao.dataSetHotList(pager);
    }

    @Override
    public List<DataSetPo> dataSetHotList() {
        return this.dataSetDao.dataSetHotList();
    }

    @Override
    public Pager dataCfgHotList(Pager pager) {
        return this.pagerUtils.transationFromListToPager(dataCfgHotList(), pager);
    }

    @Override
    public List<DataCfgPo> dataCfgHotList() {
        List<DataCfgPo> back = new ArrayList();

        Map<String, Long> counts = this.dataAccessLogDao.dataCfgAccessCount();

        Map<String, Long> failCounts = this.dataAccessRejectLogDao.dataCfgAccessCount();

        Set<String> failKeys = failCounts.keySet();
        for (String key : failKeys) {
            if (counts.get(key) == null) {
                counts.put(key, failCounts.get(key));
            } else {
                counts.put(key, Long.valueOf(((Long) failCounts.get(key)).longValue() + ((Long) counts.get(key)).longValue()));
            }
        }

        counts = sortByValue(counts);

        List<DataCfgPo> allDataCfgs = this.dataCfgDao.findPublicDataCfg();

        Set<String> countKeys = counts.keySet();
        for (Iterator i$ = countKeys.iterator(); i$.hasNext();) {
            String key = (String) i$.next();
            for (DataCfgPo po : allDataCfgs) {
                if (po.getOid().equals(key)) {
                    if (po.getDataSetPo() != null) {
                        if ((po.getDataSetPo().isActive().booleanValue()) && (po.getDataSetPo().isPublic().booleanValue())
                                && (po.getDataSetPo().isEnable().booleanValue())) {
                            back.add(po);
                        }
                    } else
                        back.add(po);
                }
            }
        }
        String key;
        return back;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            //            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            //                return ((Comparable) o2.getValue()).compareTo(o1.getValue());
            //            }

            @Override
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<K, V>) o2).getValue().compareTo(((Map.Entry<K, V>) o1).getValue());
            }

        });
        Map<K, V> result = new LinkedHashMap();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @Override
    public List<DataTagPo> dataTagSearch(String keyword) {
        return this.dataTagDao.dataTagSearch(keyword);
    }

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
        } else
            interval = new IntervalBean("createTime", startTime, endTime);
        return this.dataSetDao.dataSetMultiCollectionSearch(unitOidList, categoryOidList, interval, mataDataKey,
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
        } else
            interval = new IntervalBean("createTime", startTime, endTime);
        return this.dataSetDao.dataSetMultiCollectionSearch(pager, unitOidList, categoryOidList, interval, mataDataKey,
                mataDataValueList, orderByOption);
    }
}
