package com.iisi.opd.cfg.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import com.iisi.common.dao.impl.GenericDaoImpl;
import com.iisi.common.util.Pager;
import com.iisi.opd.cfg.dao.DataCfgDao;
import com.iisi.opd.cfg.dto.DataCfgFileDto;
import com.iisi.opd.cfg.po.DataCfgPo;

@Repository
public class DataCfgDaoImpl extends GenericDaoImpl<DataCfgPo> implements DataCfgDao {
    @Override
    public List<DataCfgPo> categorySearch(String keyword) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        Date now = new Date();
        LogicalExpression startExpression = Restrictions.or(Restrictions.isNull("startTime"), Restrictions.lt("startTime", now));
        LogicalExpression endExpression = Restrictions.or(Restrictions.isNull("endTime"), Restrictions.ge("endTime", now));
        criteria.add(startExpression);
        criteria.add(endExpression);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        DetachedCriteria categoryCriteria = dataSetCriteria.createCriteria("dataCategoryPo");
        categoryCriteria.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgPo> tagSearch(String keyword) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        Date now = new Date();
        LogicalExpression startExpression = Restrictions.or(Restrictions.isNull("startTime"), Restrictions.lt("startTime", now));
        LogicalExpression endExpression = Restrictions.or(Restrictions.isNull("endTime"), Restrictions.ge("endTime", now));
        criteria.add(startExpression);
        criteria.add(endExpression);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        DetachedCriteria tagCriteria = dataSetCriteria.createCriteria("dataTagPoList");
        tagCriteria.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgPo> lastSearch() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        Date now = new Date();
        LogicalExpression startExpression = Restrictions.or(Restrictions.isNull("startTime"), Restrictions.lt("startTime", now));
        LogicalExpression endExpression = Restrictions.or(Restrictions.isNull("endTime"), Restrictions.ge("endTime", now));
        criteria.add(startExpression);
        criteria.add(endExpression);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.addOrder(Order.desc("publicTime"));

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgPo> advancedSearch(String name, String category, String tag, Date startTime, Date endTime,
            String keyword) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        Date now = new Date();
        LogicalExpression startExpression = Restrictions.or(Restrictions.isNull("startTime"), Restrictions.lt("startTime", now));
        LogicalExpression endExpression = Restrictions.or(Restrictions.isNull("endTime"), Restrictions.ge("endTime", now));
        criteria.add(startExpression);
        criteria.add(endExpression);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        if ((name != null) && (name.trim().length() != 0)) {
            criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        }

        if (startTime != null) {
            criteria.add(Restrictions.gt("publicTime", startTime));
        }

        if (endTime != null) {
            criteria.add(Restrictions.le("publicTime", endTime));
        }

        if ((category != null) && (category.trim().length() != 0)) {
            DetachedCriteria categoryCriteria = dataSetCriteria.createCriteria("dataCategoryPo");
            categoryCriteria.add(Restrictions.like("name", category, MatchMode.ANYWHERE));
        }

        if ((tag != null) && (tag.trim().length() != 0)) {
            DetachedCriteria tagCriteria = dataSetCriteria.createCriteria("dataTagPoList");
            tagCriteria.add(Restrictions.like("name", tag, MatchMode.ANYWHERE));
        }

        if ((keyword != null) && (keyword.trim().length() != 0)) {
            criteria.createAlias("dataCfgMetadataPoList", "dataCfgMetadataPoList");
            SimpleExpression description = Restrictions.like("description", keyword, MatchMode.ANYWHERE);
            SimpleExpression metadata = Restrictions.like("dataCfgMetadataPoList.metadataValue", keyword, MatchMode.ANYWHERE);
            criteria.add(Restrictions.or(description, metadata));
        }

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("publicTime"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgPo> findHide() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(false)));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgPo> findPublicDataCfg() {
        DetachedCriteria criteria = createDetachedCriteriaForFindPublicDataCfg();
        criteria.addOrder(Order.desc("publicTime"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager findPublicDataCfg(Pager pager) {
        DetachedCriteria criteria = createDetachedCriteriaForFindPublicDataCfg();

        return getPager(pager, criteria, "publicTime");
    }

    private DetachedCriteria createDetachedCriteriaForFindPublicDataCfg() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        Date now = new Date();
        LogicalExpression startExpression = Restrictions.or(Restrictions.isNull("startTime"), Restrictions.lt("startTime", now));
        LogicalExpression endExpression = Restrictions.or(Restrictions.isNull("endTime"), Restrictions.ge("endTime", now));
        criteria.add(startExpression);
        criteria.add(endExpression);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    @Override
    public List<DataCfgPo> findAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("publicTime"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgPo> keywordSearch(String keyword) {
        DetachedCriteria criteria = createDetachedCriteriaForKeywordSearch(keyword);
        criteria.addOrder(Order.desc("publicTime"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Pager keywordSearch(Pager pager, String keyword) {
        DetachedCriteria criteria = createDetachedCriteriaForKeywordSearch(keyword);
        return getPagerWithComplexStructure(pager, criteria, Order.desc("publicTime"));
    }

    @Override
    public List<DataCfgPo> findAllAgreed() {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isApplied", Boolean.valueOf(false)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isApplied", Boolean.valueOf(false)));

        criteria.addOrder(Order.desc("publicTime"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<DataCfgPo> findPublicByDataSetOid(String oid) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        Date now = new Date();
        LogicalExpression startExpression = Restrictions.or(Restrictions.isNull("startTime"), Restrictions.lt("startTime", now));
        LogicalExpression endExpression = Restrictions.or(Restrictions.isNull("endTime"), Restrictions.ge("endTime", now));
        criteria.add(startExpression);
        criteria.add(endExpression);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        criteria.addOrder(Order.desc("publicTime"));

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("oid", oid));

        criteria.addOrder(Order.desc("resourceModifiedDate"));

        return getHibernateTemplate().findByCriteria(criteria);
    }

    private DetachedCriteria createDetachedCriteriaForKeywordSearch(String keyword) {
        DetachedCriteria criteria = DetachedCriteria.forClass(DataCfgPo.class);
        Date now = new Date();
        LogicalExpression startExpression = Restrictions.or(Restrictions.isNull("startTime"), Restrictions.lt("startTime", now));
        LogicalExpression endExpression = Restrictions.or(Restrictions.isNull("endTime"), Restrictions.ge("endTime", now));
        criteria.add(startExpression);
        criteria.add(endExpression);
        criteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        criteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPo");
        dataSetCriteria.add(Restrictions.eq("isActive", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isEnable", Boolean.valueOf(true)));
        dataSetCriteria.add(Restrictions.eq("isPublic", Boolean.valueOf(true)));

        SimpleExpression name = Restrictions.like("name", keyword, MatchMode.ANYWHERE);
        SimpleExpression description = Restrictions.like("description", keyword, MatchMode.ANYWHERE);

        criteria.createAlias("dataSetPo.dataCategoryPo", "dataCategoryPo", 4);
        SimpleExpression category = Restrictions.like("dataCategoryPo.name", keyword, MatchMode.ANYWHERE);

        criteria.createAlias("dataSetPo.dataTagPoList", "dataTagPoList", 4);
        SimpleExpression tag = Restrictions.like("dataTagPoList.name", keyword, MatchMode.ANYWHERE);

        criteria.createAlias("dataCfgMetadataPoList", "dataCfgMetadataPoList", 4);
        SimpleExpression metadata = Restrictions.like("dataCfgMetadataPoList.metadataValue", keyword, MatchMode.ANYWHERE);

        LogicalExpression orExpression = Restrictions.or(name,
                Restrictions.or(description, Restrictions.or(category, Restrictions.or(tag, metadata))));
        criteria.add(orExpression);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria;
    }

    @Override
    public void updateCfginfo(String oid) {
        DataCfgPo dataCfgPo = findById(oid);
        dataCfgPo.setResourceModifiedDate(new Date(System.currentTimeMillis()));
        dataCfgPo.setUpdateTime(new Date(System.currentTimeMillis()));
    }

    @Override
    public void updateCfginfo(String oid, int count) {
        DataCfgPo dataCfgPo = findById(oid);
        dataCfgPo.setResourceModifiedDate(new Date(System.currentTimeMillis()));
        dataCfgPo.setUpdateTime(new Date(System.currentTimeMillis()));
        dataCfgPo.setDataCount(count);
    }

    @Override
    public DataCfgFileDto getDataCfgFileDto(String dataCfgOid) {
        StringBuffer sqlQueryStatment = new StringBuffer();
        sqlQueryStatment.append("SELECT oid                  \n");
        sqlQueryStatment.append("      ,name                 \n");
        sqlQueryStatment.append("      ,size                 \n");
        sqlQueryStatment.append("  FROM dbo.od_data_cfg_file \n");
        sqlQueryStatment.append(" WHERE od_data_cfg_oid = ?  \n");

        List<Object[]> objList = (List<Object[]>) getNativeSqlQueryList(sqlQueryStatment.toString(), new Object[] { dataCfgOid });

        if ((objList != null) && (objList.size() > 0)) {
            Object[] obj = objList.get(0);
            DataCfgFileDto dataCfgFileDto = new DataCfgFileDto();
            dataCfgFileDto.setOid(obj[0].toString());
            dataCfgFileDto.setName(obj[1].toString());
            dataCfgFileDto.setSize(((BigDecimal) obj[2]).longValue());
            return dataCfgFileDto;
        }

        return null;
    }

    @Override
    public int getSumOfAllDataCfgDataCount() {
        StringBuffer sqlQueryStatment = new StringBuffer();
        sqlQueryStatment.append("SELECT SUM(ODC.data_count)                                            \n");
        sqlQueryStatment.append("  FROM [dbo].[od_data_set] ODS                                        \n");
        sqlQueryStatment.append("  LEFT JOIN [dbo].[od_data_cfg] ODC ON ODC.od_data_set_oid = ODS.oid  \n");
        sqlQueryStatment.append(" WHERE (ODS.is_active = 1 AND ODS.is_enable = 1 AND ODS.is_public = 1)\n");
        sqlQueryStatment.append("   AND (ODC.is_active = 1 AND ODC.is_enable = 1 AND ODC.is_public = 1)\n");

        return ((Integer) getNativeSqlQueryList(sqlQueryStatment.toString()).get(0)).intValue();
    }
}
