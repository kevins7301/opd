/*     */ package com.iisi.opd.auth.dao.impl;
/*     */ 
/*     */ import com.iisi.common.dao.impl.GenericDaoImpl;
/*     */ import com.iisi.opd.auth.dao.UserDAO;
/*     */ import com.iisi.opd.auth.po.RolePo;
/*     */ import com.iisi.opd.auth.po.UserPo;
/*     */ import com.iisi.opd.exception.OpdException;
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.criterion.MatchMode;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ 
/*     */ @Repository
/*     */ public class UserDAOImpl
/*     */   extends GenericDaoImpl<UserPo>
/*     */   implements UserDAO
/*     */ {
/*     */   public void delete(Object o)
/*     */   {
/*  24 */     UserPo userPo = (UserPo)o;
/*     */     
/*  26 */     List<RolePo> roles = userPo.getRoles();
/*  27 */     for (int i = 0; i < roles.size(); i++) {
/*  28 */       ((RolePo)roles.get(i)).removeUser(userPo);
/*     */     }
/*     */     
/*  31 */     userPo.setRoles(null);
/*     */     
/*  33 */     super.delete(o);
/*     */   }
/*     */   
/*     */   public UserPo findByUserName(String userName) throws OpdException {
/*  37 */     if ((userName == null) || (userName.length() == 0)) {
/*  38 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*     */     }
/*  40 */     Criteria c = getSession().createCriteria(UserPo.class).add(Restrictions.eq("userName", userName));
/*     */     
/*  42 */     List pos = c.list();
/*  43 */     if (pos.size() == 1) {
/*  44 */       return (UserPo)pos.get(0);
/*     */     }
/*  46 */     return null;
/*     */   }
/*     */   
/*     */   public UserPo findByLoginId(String loginId)
/*     */     throws OpdException
/*     */   {
/*  52 */     if ((loginId == null) || (loginId.length() == 0)) {
/*  53 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*     */     }
/*  55 */     Criteria c = getSession().createCriteria(UserPo.class).add(Restrictions.eq("loginId", loginId));
/*     */     
/*  57 */     List pos = c.list();
/*  58 */     if (pos.size() == 1)
/*  59 */       return (UserPo)pos.get(0);
/*  60 */     if (pos.size() > 1) {
/*  61 */       throw new OpdException(ErrorCodeEnum.ERR_2010001_EXCEPTION);
/*     */     }
/*  63 */     return null;
/*     */   }
/*     */   
/*     */   public UserPo findByEmail(String email)
/*     */     throws OpdException
/*     */   {
/*  69 */     if ((email == null) || (email.length() == 0)) {
/*  70 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*     */     }
/*  72 */     Criteria c = getSession().createCriteria(UserPo.class).add(Restrictions.eq("email", email));
/*     */     
/*  74 */     List pos = c.list();
/*  75 */     if (pos.size() == 1)
/*  76 */       return (UserPo)pos.get(0);
/*  77 */     if (pos.size() > 1) {
/*  78 */       throw new OpdException(ErrorCodeEnum.ERR_2010001_EXCEPTION);
/*     */     }
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public UserPo findByPhone(String phone)
/*     */     throws OpdException
/*     */   {
/*  86 */     if ((phone == null) || (phone.length() == 0)) {
/*  87 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*     */     }
/*  89 */     Criteria c = getSession().createCriteria(UserPo.class).add(Restrictions.eq("phone", phone));
/*     */     
/*  91 */     List pos = c.list();
/*  92 */     if (pos.size() == 1)
/*  93 */       return (UserPo)pos.get(0);
/*  94 */     if (pos.size() > 1) {
/*  95 */       throw new OpdException(ErrorCodeEnum.ERR_2010001_EXCEPTION);
/*     */     }
/*  97 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<UserPo> findByLikeUserName(String userName)
/*     */     throws OpdException
/*     */   {
/* 104 */     if ((userName == null) || (userName.length() == 0)) {
/* 105 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*     */     }
/* 107 */     Criteria c = getSession().createCriteria(UserPo.class).add(Restrictions.like("userName", userName, MatchMode.ANYWHERE));
/* 108 */     return c.list();
/*     */   }
/*     */   
/*     */   public List<UserPo> findByIsActive(boolean isActive)
/*     */     throws OpdException
/*     */   {
/* 114 */     Criteria c = getSession().createCriteria(UserPo.class).add(Restrictions.eq("isActive", Boolean.valueOf(isActive)));
/* 115 */     return c.list();
/*     */   }
/*     */   
/*     */   public List<UserPo> findByLikeLoginId(String loginId)
/*     */     throws OpdException
/*     */   {
/* 121 */     if ((loginId == null) || (loginId.length() == 0)) {
/* 122 */       throw new OpdException(ErrorCodeEnum.ERR_2014001_EXCEPTION);
/*     */     }
/* 124 */     Criteria c = getSession().createCriteria(UserPo.class).add(Restrictions.like("loginId", loginId, MatchMode.ANYWHERE));
/* 125 */     return c.list();
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dao\impl\UserDAOImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */