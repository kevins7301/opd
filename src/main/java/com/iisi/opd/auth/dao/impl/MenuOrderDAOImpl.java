/*    */ package com.iisi.opd.auth.dao.impl;
/*    */ 
/*    */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*    */ import com.iisi.opd.auth.dao.MenuOrderDAO;
/*    */ import com.iisi.opd.auth.po.MenuOrderPo;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MenuOrderDAOImpl
/*    */   extends GenericDaoImpl<MenuOrderPo> implements MenuOrderDAO
/*    */ {
/*    */   public List<MenuOrderPo> findChildOrderInfo(String parentID)
/*    */   {
/* 19 */     Criteria c = getSession().createCriteria(MenuOrderPo.class).add(Restrictions.eq("parentID", parentID)).addOrder(Order.asc("childOrder"));
/*    */     
/*    */ 
/* 22 */     return c.list();
/*    */   }
/*    */   
/*    */ 
/*    */   public void deleteChildsByParentID(String parentID)
/*    */   {
/* 28 */     Criteria c = getSession().createCriteria(MenuOrderPo.class).add(Restrictions.eq("parentID", parentID));
/*    */     
/* 30 */     deleteAll(c.list());
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\impl\MenuOrderDAOImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */