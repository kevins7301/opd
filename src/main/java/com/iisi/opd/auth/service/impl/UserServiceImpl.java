/*     */ package com.iisi.opd.auth.service.impl;
/*     */ 
/*     */ import com.iisi.opd.auth.dao.UserDAO;
/*     */ import com.iisi.opd.auth.po.UserPo;
/*     */ import com.iisi.opd.auth.service.UserService;
/*     */ import com.iisi.opd.exception.OpdException;
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class UserServiceImpl
/*     */   implements UserService
/*     */ {
/*  24 */   public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
/*     */   @Autowired
/*     */   UserDAO userDAO;
/*     */   
/*     */   public UserPo add(UserPo po)
/*     */     throws OpdException
/*     */   {
/*  31 */     if (this.userDAO.findByLoginId(po.getLoginId()) != null)
/*  32 */       throw new OpdException(ErrorCodeEnum.ERR_2010001_EXCEPTION);
/*  33 */     if (this.userDAO.findByEmail(po.getEmail()) != null) {
/*  34 */       throw new OpdException(ErrorCodeEnum.ERR_2010002_EXCEPTION);
/*     */     }
/*  36 */     return (UserPo)this.userDAO.save(po);
/*     */   }
/*     */   
/*     */ 
/*     */   public void delete(UserPo po)
/*     */   {
/*  42 */     this.userDAO.delete(po);
/*     */   }
/*     */   
/*     */   public void update(UserPo po) throws OpdException
/*     */   {
/*  47 */     if (this.userDAO.findByLoginId(po.getLoginId()) == null)
/*     */     {
/*  49 */       if (this.userDAO.findByEmail(po.getEmail()) == null)
/*     */       {
/*     */ 
/*  52 */         this.userDAO.update(po);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public List<UserPo> findAll() {
/*  58 */     return this.userDAO.findAll(UserPo.class);
/*     */   }
/*     */   
/*     */   public UserPo findByOid(String oid)
/*     */   {
/*  63 */     UserPo po = (UserPo)this.userDAO.findById(oid);
/*  64 */     log.debug("UserPo：", po);
/*  65 */     if ((null == po) || (po.equals(new UserPo()))) {
/*  66 */       throw new OpdException(ErrorCodeEnum.ERR_1001000_EXCEPTION);
/*     */     }
/*  68 */     return po;
/*     */   }
/*     */   
/*     */   public UserPo findByUserName(String userName)
/*     */     throws OpdException
/*     */   {
/*  74 */     return this.userDAO.findByUserName(userName);
/*     */   }
/*     */   
/*     */   public UserPo findByLoginId(String loginId) throws OpdException
/*     */   {
/*  79 */     return this.userDAO.findByLoginId(loginId);
/*     */   }
/*     */   
/*     */   public UserPo findByEmail(String email) throws OpdException
/*     */   {
/*  84 */     return this.userDAO.findByEmail(email);
/*     */   }
/*     */   
/*     */   public UserPo findByPhone(String phone) throws OpdException
/*     */   {
/*  89 */     return this.userDAO.findByPhone(phone);
/*     */   }
/*     */   
/*     */   public List<UserPo> findByLikeUserName(String userName) throws OpdException
/*     */   {
/*  94 */     return this.userDAO.findByLikeUserName(userName);
/*     */   }
/*     */   
/*     */   public List<UserPo> findByIsActive(boolean isActive) throws OpdException
/*     */   {
/*  99 */     return this.userDAO.findByIsActive(isActive);
/*     */   }
/*     */   
/*     */   public List<UserPo> findByLikeLoginId(String loginId) throws OpdException
/*     */   {
/* 104 */     return this.userDAO.findByLikeLoginId(loginId);
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\service\impl\UserServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */