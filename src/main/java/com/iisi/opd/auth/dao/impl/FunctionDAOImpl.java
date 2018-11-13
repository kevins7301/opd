/*     */ package com.iisi.opd.auth.dao.impl;
/*     */ 
/*     */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*     */ import com.iisi.opd.auth.dao.FunctionDAO;
/*     */ import com.iisi.opd.auth.po.FunctionPo;
/*     */ import com.iisi.opd.auth.po.RolePo;
/*     */ import com.iisi.opd.exception.OpdException;
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.MatchMode;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.hibernate.criterion.SimpleExpression;
/*     */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ 
/*     */ @Repository
/*     */ public class FunctionDAOImpl
/*     */   extends GenericDaoImpl<FunctionPo>
/*     */   implements FunctionDAO
/*     */ {
/*     */   public FunctionPo findByFunctionName(String name)
/*     */   {
/*  29 */     Criteria c = getSession().createCriteria(FunctionPo.class).add(Restrictions.eq("functionName", name));
/*     */     
/*  31 */     List pos = c.list();
/*  32 */     if (pos.size() == 1) {
/*  33 */       return (FunctionPo)pos.get(0);
/*     */     }
/*  35 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void delete(Object o)
/*     */   {
/*  41 */     FunctionPo functionPo = (FunctionPo)o;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */     List<FunctionPo> parents = functionPo.getParentFunctions();
/*  53 */     if (parents != null) {
/*  54 */       for (int i = 0; i < parents.size(); i++) {
/*  55 */         ((FunctionPo)parents.get(i)).removeChildFunction(functionPo);
/*     */       }
/*     */     }
/*     */     
/*  59 */     List<FunctionPo> childs = functionPo.getChildFunctions();
/*  60 */     if (childs != null) {
/*  61 */       for (int i = 0; i < childs.size(); i++) {
/*  62 */         ((FunctionPo)childs.get(i)).removeParentFunction(functionPo);
/*     */       }
/*     */     }
/*     */     
/*  66 */     List<RolePo> roles = functionPo.getRoles();
/*  67 */     if (roles != null) {
/*  68 */       for (int i = 0; i < roles.size(); i++) {
/*  69 */         ((RolePo)roles.get(i)).removeFunction(functionPo);
/*     */       }
/*     */     }
/*     */     
/*  73 */     functionPo.setChildFunctions(null);
/*  74 */     functionPo.setParentFunctions(null);
/*  75 */     functionPo.setRoles(null);
/*     */     
/*  77 */     super.delete(o);
/*     */   }
/*     */   
/*     */ 
/*     */   public List<FunctionPo> findAllFunctionGroups()
/*     */   {
/*  83 */     Criteria c = getSession().createCriteria(FunctionPo.class).add(Restrictions.eq("isGroup", Boolean.valueOf(true)));
/*  84 */     return c.list();
/*     */   }
/*     */   
/*     */ 
/*     */   public List<FunctionPo> findAllNonFunctionGroups()
/*     */   {
/*  90 */     Criteria c = getSession().createCriteria(FunctionPo.class).add(Restrictions.eq("isGroup", Boolean.valueOf(false)));
/*  91 */     return c.list();
/*     */   }
/*     */   
/*     */   public List<FunctionPo> findByLikeFunctionName(String groupName, String functionName)
/*     */     throws OpdException
/*     */   {
/*  97 */     DetachedCriteria criteria = DetachedCriteria.forClass(FunctionPo.class);
/*  98 */     SimpleExpression fName = null;SimpleExpression gName = null;
/*  99 */     if ((functionName != null) && (functionName.length() > 0)) {
/* 100 */       fName = Restrictions.like("functionName", functionName, MatchMode.ANYWHERE);
/*     */     } else {
/* 102 */       System.out.println("functionName為空");
/*     */     }
/* 104 */     if ((groupName != null) && (groupName.length() > 0)) {
/* 105 */       criteria.createAlias("parentFunctions", "parentFunctions", 4);
/* 106 */       gName = Restrictions.like("parentFunctions.functionName", groupName, MatchMode.ANYWHERE);
/*     */     }
/*     */     else {
/* 109 */       System.out.println("groupName為空");
/*     */     }
/* 111 */     if ((fName == null) && (gName == null)) {
/* 112 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*     */     }
/* 114 */     if (fName != null) {
/* 115 */       criteria.add(fName);
/*     */     } else {
/* 117 */       System.out.println("無fName");
/*     */     }
/* 119 */     if (gName != null) {
/* 120 */       criteria.add(gName);
/*     */     } else {
/* 122 */       System.out.println("無gName");
/*     */     }
/* 124 */     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
/* 125 */     List result1 = getHibernateTemplate().findByCriteria(criteria);
/* 126 */     List<FunctionPo> result = new ArrayList();
/* 127 */     for (int i = 0; i < result1.size(); i++) {
/* 128 */       if (result1.get(i) != null) {
/* 129 */         result.add((FunctionPo)result1.get(i));
/*     */       }
/*     */     }
/* 132 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public FunctionPo findByAccessPath(String accessPath)
/*     */   {
/* 138 */     DetachedCriteria criteria = DetachedCriteria.forClass(FunctionPo.class);
/* 139 */     criteria.add(Restrictions.eq("accessPath", accessPath));
/* 140 */     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
/*     */     
/* 142 */     List pos = getHibernateTemplate().findByCriteria(criteria);
/* 143 */     if (pos.size() == 1) {
/* 144 */       return (FunctionPo)pos.get(0);
/*     */     }
/* 146 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\impl\FunctionDAOImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */