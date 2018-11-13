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
/*    */ import com.iisi.opd.log.dao.DataFileLogDao;
/*    */ import com.iisi.opd.log.po.DataFileLogPo;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ @Repository
/*    */ public class DataFileLogDaoImpl/*    */ extends GenericDaoImpl<DataFileLogPo>/*    */ implements DataFileLogDao
/*    */ {
    /*    */ @Override
    public void removeByDataSetOid(String dataSetOid)
    /*    */ {
        /* 29 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataFileLogPo.class);
        /* 30 */ criteria.add(Restrictions.eq("dataSetOid", dataSetOid));
        /*    */
        /*    */
        /* 33 */ List<DataFileLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
        /* 34 */ deleteAll(removeList);
        /*    */ }

    /*    */
    /*    */
    /*    */ @Override
    public void removeByDataCfgOid(String dataCfgOid)
    /*    */ {
        /* 40 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataFileLogPo.class);
        /* 41 */ criteria.add(Restrictions.eq("dataCfgOid", dataCfgOid));
        /*    */
        /*    */
        /* 44 */ List<DataFileLogPo> removeList = getHibernateTemplate().findByCriteria(criteria);
        /* 45 */ deleteAll(removeList);
        /*    */ }

    /*    */
    /*    */
    /*    */
    /*    */ @Override
    public Map<String, Long[]> getDataFileDownloadCountStatistic(IntervalBean interval)
    /*    */ {
        /* 52 */ DetachedCriteria criteria = DetachedCriteria.forClass(DataFileLogPo.class);
        /* 53 */ criteria.add(interval.getRestriction());
        /*    */
        /* 55 */ ProjectionList projectionList = Projections.projectionList();
        /* 56 */ criteria.setProjection(projectionList);
        /* 57 */ projectionList.add(Projections.groupProperty("dataSetOid")).add(Projections.rowCount())
                .add(Projections.sum("fileSize"));
        /*    */
        /*    */
        /*    */
        /* 61 */ Map<String, Long[]> back = new HashMap();
        /* 62 */ List<Object[]> result = (List<Object[]>) getProjectionsQueryList(criteria);
        /* 63 */ for (Object[] objs : result) {
            /* 64 */ back.put(objs[0].toString(),
                    new Long[] { Long.valueOf(objs[1].toString()), Long.valueOf(objs[2].toString()) });
            /*    */ }
        /* 66 */ return back;
        /*    */ }
    /*    */ }

/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\impl\DataFileLogDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */