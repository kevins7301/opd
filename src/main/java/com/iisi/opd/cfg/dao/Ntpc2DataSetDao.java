package com.iisi.opd.cfg.dao;

import java.util.List;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.GenericDao;
import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.po.DataSetPo;
import com.iisi.opd.cfg.po.DataSetPo.OrderByOption;

public interface Ntpc2DataSetDao extends GenericDao<DataSetPo> {
    List<DataSetPo> dataSetMultiCollectionSearch(List<String> arg0, List<String> arg1, IntervalBean arg2, OrderByOption arg3);

    List<DataSetPo> dataSetMultiCollectionSearch(List<String> arg0, List<String> arg1, IntervalBean arg2, String arg3,
            List<String> arg4, OrderByOption arg5);

    Pager dataSetMultiCollectionSearch(Pager arg0, List<String> arg1, List<String> arg2, IntervalBean arg3, OrderByOption arg4);

    Pager dataSetMultiCollectionSearch(Pager arg0, List<String> arg1, List<String> arg2, IntervalBean arg3, String arg4,
            List<String> arg5, OrderByOption arg6);

    Pager getAllDataSetApplyStatus(Pager arg0);
}