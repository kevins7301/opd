/*    */ package com.iisi.opd.tag.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.tag.dao.DataTagDao;
/*    */ import com.iisi.opd.tag.po.DataTagPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.LogicalExpression;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class DataTagDaoImpl
/*    */   extends GenericDaoImpl<DataTagPo>
/*    */   implements DataTagDao
/*    */ {
/*    */   public List<DataTagPo> findAllwithDataSetCondition()
/*    */   {
/* 25 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataTagPo.class);
/*    */     
/* 27 */     DetachedCriteria dataSetCriteria = criteria.createCriteria("dataSetPoList", 1);
/* 28 */     LogicalExpression andExpression = Restrictions.and(Restrictions.eq("isActive", Boolean.valueOf(true)), Restrictions.and(Restrictions.eq("isEnable", Boolean.valueOf(true)), Restrictions.eq("isPublic", Boolean.valueOf(true))));
/*    */     
/* 30 */     LogicalExpression orExpression = Restrictions.or(Restrictions.isNull("oid"), andExpression);
/*    */     
/* 32 */     dataSetCriteria.add(orExpression);
/*    */     
/* 34 */     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
/* 35 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */   
/*    */ 
/*    */   public DataTagPo findByName(String name)
/*    */   {
/* 41 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataTagPo.class);
/* 42 */     criteria.add(Restrictions.eq("name", name));
/* 43 */     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
/* 44 */     List<DataTagPo> poList = getHibernateTemplate().findByCriteria(criteria);
/* 45 */     if ((poList != null) && (poList.size() != 0)) {
/* 46 */       return (DataTagPo)poList.get(0);
/*    */     }
/* 48 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public List<DataTagPo> dataTagSearch(String keyword)
/*    */   {
/* 54 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataTagPo.class);
/* 55 */     criteria.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
/* 56 */     criteria.add(Restrictions.like("remark", keyword, MatchMode.ANYWHERE));
/* 57 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public DataTagPo findByBlurredName(String name)
/*    */   {
/* 64 */     DetachedCriteria criteria = DetachedCriteria.forClass(DataTagPo.class);
/* 65 */     criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
/* 66 */     List<DataTagPo> poList = getHibernateTemplate().findByCriteria(criteria);
/* 67 */     if ((poList != null) && (poList.size() != 0)) {
/* 68 */       return (DataTagPo)poList.get(0);
/*    */     }
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\tag\dao\impl\DataTagDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */