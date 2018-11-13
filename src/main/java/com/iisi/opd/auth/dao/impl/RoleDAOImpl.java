/*    */ package com.iisi.opd.auth.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.auth.po.FunctionPo;
/*    */ import com.iisi.opd.auth.po.RolePo;
/*    */ import com.iisi.opd.auth.po.UserPo;
/*    */ import com.iisi.opd.exception.OpdException;
/*    */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.hibernate.criterion.SimpleExpression;
/*    */ 
/*    */ @org.springframework.stereotype.Repository
/*    */ public class RoleDAOImpl extends GenericDaoImpl<RolePo> implements com.iisi.opd.auth.dao.RoleDAO
/*    */ {
/*    */   public RolePo findByRoleName(String name)
/*    */   {
/* 23 */     Criteria c = getSession().createCriteria(RolePo.class).add(Restrictions.eq("roleName", name));
/*    */     
/* 25 */     List pos = c.list();
/* 26 */     if (pos.size() == 1) {
/* 27 */       return (RolePo)pos.get(0);
/*    */     }
/* 29 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public void delete(Object o)
/*    */   {
/* 35 */     RolePo rolePo = (RolePo)o;
/*    */     
/* 37 */     List<UserPo> users = rolePo.getUsers();
/* 38 */     if (users != null) {
/* 39 */       for (int i = 0; i < users.size(); i++) {
/* 40 */         ((UserPo)users.get(i)).removeRole(rolePo);
/*    */       }
/*    */     }
/*    */     
/* 44 */     List<FunctionPo> functions = rolePo.getFunctions();
/* 45 */     if (functions != null) {
/* 46 */       for (int i = 0; i < functions.size(); i++) {
/* 47 */         ((FunctionPo)functions.get(i)).removeRole(rolePo);
/*    */       }
/*    */     }
/*    */     
/* 51 */     rolePo.setUsers(null);
/* 52 */     rolePo.setFunctions(null);
/*    */     
/* 54 */     super.delete(o);
/*    */   }
/*    */   
/*    */ 
/*    */   public List<RolePo> findByNameAndStatus(String roleName, Boolean isActive)
/*    */   {
/* 60 */     DetachedCriteria criteria = DetachedCriteria.forClass(RolePo.class);
/* 61 */     SimpleExpression rName = null;SimpleExpression rAct = null;
/* 62 */     if ((roleName != null) && (roleName.length() > 0)) {
/* 63 */       rName = Restrictions.like("roleName", roleName, MatchMode.ANYWHERE);
/*    */     } else {
/* 65 */       System.out.println("roleName為空");
/*    */     }
/* 67 */     if (isActive != null) {
/* 68 */       rAct = Restrictions.eq("isActive", isActive);
/*    */     }
/*    */     else {
/* 71 */       System.out.println("isActive為空");
/*    */     }
/* 73 */     if ((rName == null) && (rAct == null)) {
/* 74 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*    */     }
/* 76 */     if (rName != null) {
/* 77 */       criteria.add(rName);
/*    */     } else {
/* 79 */       System.out.println("無rName");
/*    */     }
/* 81 */     if (rAct != null) {
/* 82 */       criteria.add(rAct);
/*    */     } else {
/* 84 */       System.out.println("無rAct");
/*    */     }
/* 86 */     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 95 */     return getHibernateTemplate().findByCriteria(criteria);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\impl\RoleDAOImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */