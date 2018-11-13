/*    */ package com.iisi.opd.log.dao.impl;
/*    */ 
/*    */ import com.iisi.common.bean.IntervalBean;
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.common.util.Pager;
/*    */ import com.iisi.opd.log.dao.SiteAccessLogDao;
/*    */ import com.iisi.opd.log.po.SiteAccessLogPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.ProjectionList;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class SiteAccessLogDaoImpl
/*    */   extends GenericDaoImpl<SiteAccessLogPo>
/*    */   implements SiteAccessLogDao
/*    */ {
/*    */   public List<Object[]> findSiteAccessLogCountData(IntervalBean interval)
/*    */   {
/* 29 */     DetachedCriteria criteria = DetachedCriteria.forClass(SiteAccessLogPo.class);
/* 30 */     criteria.add(interval.getRestriction());
/*    */     
/* 32 */     ProjectionList projectionList = Projections.projectionList();
/* 33 */     projectionList.add(Projections.groupProperty("pageName"));
/* 34 */     projectionList.add(Projections.count("pageName"));
/* 35 */     criteria.setProjection(projectionList);
/*    */     
/* 37 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */   
/*    */   public Pager getPagerByRange(Pager pager, IntervalBean interval)
/*    */   {
/* 42 */     DetachedCriteria criteria = DetachedCriteria.forClass(SiteAccessLogPo.class);
/* 43 */     criteria.add(interval.getRestriction());
/* 44 */     return getPager(pager, criteria, "logTime");
/*    */   }
/*    */   
/*    */   public Pager getPagerByAdmRange(Pager pager, IntervalBean interval)
/*    */   {
/* 49 */     DetachedCriteria criteria = DetachedCriteria.forClass(SiteAccessLogPo.class);
/* 50 */     criteria.add(interval.getRestriction());
/*    */     
/* 52 */     criteria.add(Restrictions.like("pageName", "/adm/", MatchMode.START));
/* 53 */     return getPager(pager, criteria, "logTime");
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\dao\impl\SiteAccessLogDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */