package com.iisi.opd.cfg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.iisi.common.dao.impl.GenericDaoImpl;
import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.dao.DataSetApplyDao;
import com.iisi.opd.cfg.po.DataCfgApplyPo;
import com.iisi.opd.cfg.po.DataSetApplyPo;

@Repository
public class DataSetApplyDaoImpl extends GenericDaoImpl<DataSetApplyPo> implements DataSetApplyDao {
    @Override
    public Pager findAllApplied(Pager pager, DataSetApplyPo.ApplyOrderByOption orderByOption) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetApplyPo.class);
        criteria.add(Restrictions.eq("dataStatus", DataCfgApplyPo.DataStatus.APPLY));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
    }

    @Override
    public List<DataSetApplyPo> findAllApplied() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetApplyPo.class);
        criteria.add(Restrictions.eq("dataStatus", DataCfgApplyPo.DataStatus.APPLY));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager findAllRefused(Pager pager, DataSetApplyPo.ApplyOrderByOption orderByOption) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetApplyPo.class);
        criteria.add(Restrictions.eq("dataStatus", DataCfgApplyPo.DataStatus.REFUSE));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
    }

    @Override
    public List<DataSetApplyPo> findAllRefused() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetApplyPo.class);
        criteria.add(Restrictions.eq("dataStatus", DataCfgApplyPo.DataStatus.REFUSE));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public int countByCategoryOid(String oid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetApplyPo.class);
        DetachedCriteria criteria2 = criteria.createCriteria("dataCategoryPo");
        criteria2.add(Restrictions.eq("oid", oid));
        return getHibernateTemplate().findByCriteria(criteria).size();
    }

    @Override
    public int countByTagOid(String oid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetApplyPo.class);
        DetachedCriteria criteria2 = criteria.createCriteria("dataTagPoList");
        criteria2.add(Restrictions.eq("oid", oid));
        return getHibernateTemplate().findByCriteria(criteria).size();
    }

    private Order generateOrder(DataSetApplyPo.ApplyOrderByOption orderByOption) {
        if (orderByOption == null)
            return Order.desc("applyTime");
        if (orderByOption == DataSetApplyPo.ApplyOrderByOption.APPLY_TIME_DESC) {
            return Order.desc("applyTime");
        }
        return Order.desc("applyTime");
    }
}