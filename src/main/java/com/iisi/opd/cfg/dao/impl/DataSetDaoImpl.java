package com.iisi.opd.cfg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import com.iisi.common.bean.IntervalBean;
import com.iisi.common.dao.impl.GenericDaoImpl;
import com.iisi.common.util.NativeSqlOrderUtils;
import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.dao.DataSetDao;
import com.iisi.opd.cfg.po.DataSetPo;

@Repository
public class DataSetDaoImpl extends GenericDaoImpl<DataSetPo> implements DataSetDao {
    @Override
    public List<DataSetPo> categorySearch(String keyword) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        DetachedCriteria categoryCriteria = criteria.createCriteria("dataCategoryPo");
        categoryCriteria.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataSetPo> tagSearch(String keyword) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        DetachedCriteria tagCriteria = criteria.createCriteria("dataTagPoList");
        tagCriteria.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataSetPo> lastSearch() {
        DetachedCriteria criteria = createDetachedCriteriaForLastSearch();
        criteria.addOrder(Order.desc("publicTime"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager lastSearch(Pager pager) {
        DetachedCriteria criteria = createDetachedCriteriaForLastSearch();
        return getPager(pager, criteria, "publicTime");
    }

    private DetachedCriteria createDetachedCriteriaForLastSearch() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        return criteria;
    }

    @Override
    public List<DataSetPo> advancedSearch(String name, String category, String tag, IntervalBean interval, String keyword) {
        return advancedSearch(name, category, tag, interval, keyword, null);
    }

    @Override
    public List<DataSetPo> advancedSearch(String name, String category, String tag, IntervalBean interval, String keyword,
            List<String> unitOidList) {
        DetachedCriteria criteria = createDetachedCriteriaForAdvancedSearch(name, category, tag, interval, keyword, unitOidList);
        criteria.addOrder(Order.desc("publicTime"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager advancedSearch(Pager pager, String name, String category, String tag, IntervalBean interval, String keyword,
            List<String> unitOidList) {
        DetachedCriteria criteria = createDetachedCriteriaForAdvancedSearch(name, category, tag, interval, keyword, unitOidList);
        return super.getPagerWithComplexStructure(pager, criteria, Order.desc("publicTime"));
    }

    private DetachedCriteria createDetachedCriteriaForAdvancedSearch(String name, String category, String tag,
            IntervalBean interval, String keyword, List<String> unitOidList) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        if ((name != null) && (name.trim().length() != 0)) {
            criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        }

        criteria.add(interval.getRestriction());

        if ((tag != null) && (tag.trim().length() != 0)) {
            DetachedCriteria tagCriteria = criteria.createCriteria("dataTagPoList");
            tagCriteria.add(Restrictions.like("name", tag, MatchMode.ANYWHERE));
        }

        if (((keyword != null) && (keyword.trim().length() != 0)) || ((category != null) && (category.trim().length() != 0))) {
            criteria.createAlias("dataCategoryPo", "dataCategoryPo", 4);
            if ((category != null) && (category.trim().length() != 0)) {
                criteria.add(Restrictions.like("dataCategoryPo.name", category, MatchMode.ANYWHERE));
            }
        }

        if ((keyword != null) && (keyword.trim().length() != 0)) {
            keyword = keyword.trim();

            SimpleExpression nameKeyword = Restrictions.like("name", keyword, MatchMode.ANYWHERE);
            SimpleExpression descriptionKeyword = Restrictions.like("description", keyword, MatchMode.ANYWHERE);

            SimpleExpression categoryKeyword = Restrictions.like("dataCategoryPo.name", keyword, MatchMode.ANYWHERE);

            criteria.createAlias("dataTagPoList", "dataTagPoList", 4);
            SimpleExpression tagKeyword = Restrictions.like("dataTagPoList.name", keyword, MatchMode.ANYWHERE);

            criteria.createAlias("dataSetMetadataPoList", "dataSetMetadataPoList", 4);
            SimpleExpression metadataKeyword = Restrictions.like("dataSetMetadataPoList.metadataValue", keyword,
                    MatchMode.ANYWHERE);

            LogicalExpression orExpression = Restrictions.or(metadataKeyword, Restrictions.or(tagKeyword,
                    Restrictions.or(categoryKeyword, Restrictions.or(nameKeyword, descriptionKeyword))));
            criteria.add(orExpression);
        }

        if ((unitOidList != null) && (unitOidList.size() != 0)) {
            DetachedCriteria unitCriteria = criteria.createCriteria("unitPo");
            unitCriteria.add(Restrictions.in("oid", unitOidList.toArray()));
        }

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    @Override
    public List<DataSetPo> findHide() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(false)));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataSetPo> findPublicDataSet() {
        DetachedCriteria criteria = createDetachedCriteriaForFindPublicDataSet();
        criteria.addOrder(Order.desc("publicTime"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataSetPo> findPublicDataSetOrderByCfgResourceModifiedDate() {
        DetachedCriteria criteria = createDetachedCriteriaForFindPublicDataSet();
        criteria.createAlias("dataCfgPoList", "dataCfgPoList", 1);
        criteria.add(Restrictions.eq("dataCfgPoList.isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("dataCfgPoList.isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("dataCfgPoList.isPublic", Boolean.valueOf(true)));
        criteria.addOrder(Order.desc("dataCfgPoList.resourceModifiedDate"));
        criteria.addOrder(Order.desc("dataSetPo.publicTime"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager findPublicDataSet(Pager pager) {
        DetachedCriteria criteria = createDetachedCriteriaForFindPublicDataSet();

        return getPager(pager, criteria, "publicTime");
    }

    private DetachedCriteria createDetachedCriteriaForFindPublicDataSet() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class, "dataSetPo");
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    @Override
    public List<DataSetPo> findAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("publicTime"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataSetPo> keywordSearch(String keyword) {
        return keywordSearch(keyword, null);
    }

    @Override
    public List<DataSetPo> keywordSearch(String keyword, DataSetPo.OrderByOption orderByOption) {
        DetachedCriteria criteria = createDetachedCriteriaForKeywordSearch(keyword);
        criteria.addOrder(generateOrder(orderByOption));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager keywordSearch(Pager pager, String keyword) {
        return keywordSearch(pager, keyword, null);
    }

    @Override
    public Pager keywordSearch(Pager pager, String keyword, DataSetPo.OrderByOption orderByOption) {
        DetachedCriteria criteria = createDetachedCriteriaForKeywordSearch(keyword);
        return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
    }

    @Override
    public List<DataSetPo> findAllAgreed() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isApplied", Boolean.valueOf(false)));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("publicTime"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataSetPo> findAllEnable() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager findAllAgreed(Pager pager, DataSetPo.OrderByOption orderByOption) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isApplied", Boolean.valueOf(false)));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
    }

    private DetachedCriteria createDetachedCriteriaForKeywordSearch(String keyword) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        keyword = keyword.trim();

        SimpleExpression name = Restrictions.like("name", keyword, MatchMode.ANYWHERE);
        SimpleExpression description = Restrictions.like("description", keyword, MatchMode.ANYWHERE);

        criteria.createAlias("dataCategoryPo", "dataCategoryPo", 4);
        SimpleExpression category = Restrictions.like("dataCategoryPo.name", keyword, MatchMode.ANYWHERE);

        criteria.createAlias("dataTagPoList", "dataTagPoList", 4);
        SimpleExpression tag = Restrictions.like("dataTagPoList.name", keyword, MatchMode.ANYWHERE);

        criteria.createAlias("dataSetMetadataPoList", "dataSetMetadataPoList", 4);
        SimpleExpression metadata = Restrictions.like("dataSetMetadataPoList.metadataValue", keyword, MatchMode.ANYWHERE);

        LogicalExpression orExpression = Restrictions.or(metadata,
                Restrictions.or(tag, Restrictions.or(category, Restrictions.or(name, description))));
        criteria.add(orExpression);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria;
    }

    @Override
    public List<DataSetPo> dataSetMultiCollectionSearch(List<String> unitOidList, List<String> categoryOidList,
            IntervalBean interval, DataSetPo.OrderByOption orderByOption) {
        DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval, null,
                null);
        criteria.addOrder(generateOrder(orderByOption));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataSetPo> dataSetMultiCollectionSearch(List<String> unitOidList, List<String> categoryOidList,
            IntervalBean interval, String mataDataKey, List<String> mataDataValueList, DataSetPo.OrderByOption orderByOption) {
        DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval,
                mataDataKey, mataDataValueList);
        return getComplexStructure(criteria, generateOrder(orderByOption));
    }

    @Override
    public Pager dataSetMultiCollectionSearch(Pager pager, List<String> unitOidList, List<String> categoryOidList,
            IntervalBean interval, DataSetPo.OrderByOption orderByOption) {
        DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval, null,
                null);
        return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
    }

    @Override
    public Pager dataSetMultiCollectionSearch(Pager pager, List<String> unitOidList, List<String> categoryOidList,
            IntervalBean interval, String mataDataKey, List<String> mataDataValueList, DataSetPo.OrderByOption orderByOption) {
        DetachedCriteria criteria = createDetachedCriteriaForMultiCollectionSearch(unitOidList, categoryOidList, interval,
                mataDataKey, mataDataValueList);
        return getPagerWithComplexStructure(pager, criteria, generateOrder(orderByOption));
    }

    private DetachedCriteria createDetachedCriteriaForMultiCollectionSearch(List<String> unitList, List<String> categoryList,
            IntervalBean interval, String mataDataKey, List<String> mataDataValueList) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        DetachedCriteria dataCfgPoListCriteria = null;
        DetachedCriteria cfgMataDataPoListCriteria = null;
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        if ((unitList != null) && (unitList.size() != 0)) {
            criteria.createAlias("unitPo", "unitPo", 1);
            criteria.add(Restrictions.in("unitPo.oid", unitList));
        }
        if ((categoryList != null) && (categoryList.size() != 0)) {
            criteria.createAlias("dataCategoryPo", "dataCategoryPo", 1);
            criteria.add(Restrictions.in("dataCategoryPo.oid", categoryList));
        }
        if ((mataDataKey != null) && (mataDataValueList != null) && (mataDataValueList.size() != 0)) {
            dataCfgPoListCriteria = criteria.createAlias("dataCfgPoList", "dataCfgPoList", 1);
            cfgMataDataPoListCriteria = dataCfgPoListCriteria.createCriteria("dataCfgPoList.dataCfgMetadataPoList", 1);

            cfgMataDataPoListCriteria.add(Restrictions.eq("metadataKey", mataDataKey));
            cfgMataDataPoListCriteria.add(Restrictions.in("metadataValue", mataDataValueList));
        }
        if ((interval.getName().contains("dataCfgPoList")) && (dataCfgPoListCriteria == null))
            dataCfgPoListCriteria = criteria.createAlias("dataCfgPoList", "dataCfgPoList", 1);
        criteria.add(interval.getRestriction());
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria;
    }

    private Order generateOrder(DataSetPo.OrderByOption orderByOption) {
        if (orderByOption == null)
            return Order.desc("publicTime");
        if (orderByOption == DataSetPo.OrderByOption.NEWEST_DESC)
            return Order.desc("publishedDate");
        if (orderByOption == DataSetPo.OrderByOption.OLDEST_ASC)
            return Order.asc("publishedDate");
        if (orderByOption == DataSetPo.OrderByOption.HOT_DESC) {
            return NativeSqlOrderUtils.desc("(data_out_count + download_count + view_count + data_srv_count)");
        }
        if (orderByOption == DataSetPo.OrderByOption.DATA_OUT_DESC)
            return Order.desc("dataOutCount");
        if (orderByOption == DataSetPo.OrderByOption.LAST_EDIT_TIME_DESC)
            return Order.desc("modifiedDate");
        if (orderByOption == DataSetPo.OrderByOption.VIEW_DESC) {
            return Order.desc("viewCount");
        }
        return Order.desc("publicTime");
    }

    @Override
    public List<Object[]> getDataSetOidAndCfgResourceModifiedDate() {
        StringBuffer sqlStatment = new StringBuffer();

        sqlStatment.append(
                "SELECT ODS.oid, CONVERT(VARCHAR(12), ISNULL(MAX(ODC.resource_modified_date), MAX(ODC.public_time)), 111) ");
        sqlStatment.append("FROM [dbo].[od_data_set] AS ODS ");
        sqlStatment.append("LEFT JOIN [dbo].[od_data_cfg] AS ODC ON ODC.od_data_set_oid = ODS.oid ");
        sqlStatment.append("WHERE ODS.is_active = 1 AND ODS.is_enable = 1 AND ODS.is_public = 1 ");
        sqlStatment.append("AND ODC.is_active = 1 AND ODC.is_enable = 1 AND ODC.is_public = 1 ");
        sqlStatment.append("GROUP BY ODS.oid ");
        return (List<Object[]>) getNativeSqlQueryList(sqlStatment.toString());
    }

    @Override
    public Pager dataSetHotList(Pager pager) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getPager(pager, criteria, generateOrder(DataSetPo.OrderByOption.HOT_DESC));
    }

    @Override
    public List<DataSetPo> dataSetHotList() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataSetPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.addOrder(generateOrder(DataSetPo.OrderByOption.HOT_DESC));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getHibernateTemplate().findByCriteria(criteria);
    }
}
