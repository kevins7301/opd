/*    */ package com.iisi.opd.log.dao.impl;

/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;

/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.ProjectionList;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;

/*    */
/*    */ import com.iisi.common.bean.IntervalBean;
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.log.dao.DataOutLogDao;
/*    */ import com.iisi.opd.log.po.DataOutLogPo;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ @Repository
/*    */ public class DataOutLogDaoImpl/*    */ extends GenericDaoImpl<DataOutLogPo>/*    */ implements DataOutLogDao
/*    */ {
    /*    */ @Override
    public void removeByDataSetOid(String dataSetOid)
    /*    */ {
        /* 29 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataOutLogPo.class);
        /* 30 */ criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
        /*    */
        /*    */
        /* 33 */ List<DataOutLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
        /* 34 */ deleteAll(removeList);
        /*    */ }

    /*    */
    /*    */
    /*    */ @Override
    public void removeByDataCfgOid(String dataCfgOid)
    /*    */ {
        /* 40 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataOutLogPo.class);
        /* 41 */ criteria.add(Restrictions.eq("dataCfgOid", dataCfgOid));
        /*    */
        /*    */
        /* 44 */ List<DataOutLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
        /* 45 */ deleteAll(removeList);
        /*    */ }

    /*    */
    /*    */
    /*    */ @Override
    public Map<String, Map<String, Long>> getDataOutCountStatistic(IntervalBean interval)
    /*    */ {
        /* 51 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataOutLogPo.class);
        /* 52 */ criteria.add(interval.getRestriction());
        /*    */
        /* 54 */ ProjectionList projectionList = Projections.projectionList();
        /* 55 */ criteria.setProjection(projectionList);
        /* 56 */ projectionList.add(Projections.groupProperty("dataSetOid")).add(Projections.groupProperty("fileType"))
                .add(Projections.count("dataSetOid"));
        /*    */
        /*    */
        /*    */
        /* 60 */ Map<String, Map<String, Long>> back = new HashMap();
        /* 61 */ List<Object[]> result = (List<Object[]>) getProjectionsQueryList(criteria);
        /* 62 */ for (Object[] objs : result) {
            /*    */ Map<String, Long> resultMap;
            /* 64 */ if ((resultMap = (Map) back.get(objs[0].toString())) == null) {
                /* 65 */ resultMap = new HashMap();
                /* 66 */ back.put(objs[0].toString(), resultMap);
                /*    */ }
            /* 68 */ resultMap.put(objs[1].toString(), Long.valueOf(objs[2].toString()));
            /*    */ }
        /* 70 */ return back;
        /*    */ }

    /*    */
    /*    */
    /*    */ @Override
    public Map<String, Long> getDataOutCountWithNoTypeStatistic(IntervalBean interval)
    /*    */ {
        /* 76 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataOutLogPo.class);
        /* 77 */ criteria.add(interval.getRestriction());
        /*    */
        /* 79 */ ProjectionList projectionList = Projections.projectionList();
        /* 80 */ criteria.setProjection(projectionList);
        /* 81 */ projectionList.add(Projections.groupProperty("dataSetOid")).add(Projections.count("dataSetOid"));
        /*    */
        /*    */
        /* 84 */ Map<String, Long> back = new HashMap();
        /* 85 */ List<Object[]> result = (List<Object[]>) getProjectionsQueryList(criteria);
        /* 86 */ for (Object[] objs : result) {
            /* 87 */ back.put(objs[0].toString(), Long.valueOf(objs[1].toString()));
            /*    */ }
        /* 89 */ return back;
        /*    */ }
    /*    */ }

/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\impl\DataOutLogDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */