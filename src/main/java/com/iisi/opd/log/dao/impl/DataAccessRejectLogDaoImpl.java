/*     */ package com.iisi.opd.log.dao.impl;

/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.ProjectionList;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.stereotype.Repository;

/*     */
/*     */ import com.iisi.common.bean.IntervalBean;
/*     */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*     */ import com.iisi.opd.log.dao.DataAccessRejectLogDao;
/*     */ import com.iisi.opd.log.po.DataAccessRejectLogPo;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ @Repository
/*     */ public class DataAccessRejectLogDaoImpl/*     */ extends GenericDaoImpl<DataAccessRejectLogPo>
        /*     */ implements DataAccessRejectLogDao
/*     */ {
    /*     */ @Override
    public void removeByDataSetOid(String dataSetOid)
    /*     */ {
        /*  29 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataAccessRejectLogPo.class);
        /*  30 */ criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
        /*     */
        /*     */
        /*  33 */ List<DataAccessRejectLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
        /*  34 */ deleteAll(removeList);
        /*     */ }

    /*     */
    /*     */
    /*     */ @Override
    public void removeByDataCfgOid(String dataCfgOid)
    /*     */ {
        /*  40 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataAccessRejectLogPo.class);
        /*  41 */ criteria.add(Restrictions.eq("dataCfgOid", dataCfgOid));
        /*     */
        /*     */
        /*  44 */ List<DataAccessRejectLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
        /*  45 */ deleteAll(removeList);
        /*     */ }

    /*     */
    /*     */ @Override
    public Map<String, Long> dataSetAccessCount()
    /*     */ {
        /*  50 */ Map<String, Long> back = new HashMap();
        /*  51 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataAccessRejectLogPo.class);
        /*  52 */ ProjectionList projectionList = Projections.projectionList();
        /*  53 */ projectionList.add(Projections.groupProperty("dataSetOid"));
        /*  54 */ projectionList.add(Projections.rowCount());
        /*  55 */ criteria.setProjection(projectionList);
        /*     */
        /*  57 */ List results = getHibernateTemplate().findByCriteria(criteria);
        /*  58 */ Object[] objs = null;
        /*  59 */ for (int i = 0; i < results.size(); i++) {
            /*  60 */ objs = (Object[]) results.get(i);
            /*  61 */ String dataSetOid = (String) objs[0];
            /*  62 */ Long count = (Long) objs[1];
            /*  63 */ back.put(dataSetOid, count);
            /*     */ }
        /*  65 */ return back;
        /*     */ }

    /*     */
    /*     */ @Override
    public Map<String, Long> dataCfgAccessCount()
    /*     */ {
        /*  70 */ Map<String, Long> back = new HashMap();
        /*  71 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataAccessRejectLogPo.class);
        /*  72 */ ProjectionList projectionList = Projections.projectionList();
        /*  73 */ projectionList.add(Projections.groupProperty("dataCfgOid"));
        /*  74 */ projectionList.add(Projections.rowCount());
        /*  75 */ criteria.setProjection(projectionList);
        /*     */
        /*  77 */ List results = getHibernateTemplate().findByCriteria(criteria);
        /*  78 */ Object[] objs = null;
        /*  79 */ for (int i = 0; i < results.size(); i++) {
            /*  80 */ objs = (Object[]) results.get(i);
            /*  81 */ String dataCfgOid = (String) objs[0];
            /*  82 */ Long count = (Long) objs[1];
            /*  83 */ back.put(dataCfgOid, count);
            /*     */ }
        /*  85 */ return back;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ @Override
    public Map<String, Long> getDataSetAccessRejectViewStatistic(IntervalBean interval)
    /*     */ {
        /*  92 */ DetachedCriteria criteriaRejection = DetachedCriteria.forClass(DataAccessRejectLogPo.class);
        /*  93 */ criteriaRejection.add(interval.getRestriction());
        /*     */
        /*  95 */ ProjectionList projectionList = Projections.projectionList();
        /*  96 */ criteriaRejection.setProjection(projectionList);
        /*  97 */ projectionList.add(Projections.groupProperty("dataSetOid")).add(Projections.count("dataSetOid"));
        /*     */
        /*     */
        /* 100 */ Map<String, Long> back = new HashMap();
        /* 101 */ List<Object[]> result = (List<Object[]>) getProjectionsQueryList(criteriaRejection);
        /* 102 */ for (Object[] objs : result) {
            /* 103 */ back.put(objs[0].toString(), Long.valueOf(objs[1].toString()));
            /*     */ }
        /* 105 */ return back;
        /*     */ }
    /*     */ }

/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\impl\DataAccessRejectLogDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */