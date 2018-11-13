/*     */ package com.iisi.opd.log.po;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name="od_log_site_access")
/*     */ public class SiteAccessLogPo
/*     */ {
/*     */   @Id
/*     */   @Column(name="oid")
/*     */   @GenericGenerator(name="generator", strategy="guid", parameters={})
/*     */   @GeneratedValue(generator="generator")
/*     */   private String oid;
/*     */   @Column(name="page_name", length=100, nullable=false)
/*     */   private String pageName;
/*     */   @Column(name="page_type", length=10, nullable=false)
/*     */   private PageType pageType;
/*     */   @Column(name="client_ip", length=23, nullable=false)
/*     */   private String clientIp;
/*     */   @Column(name="method", length=10, nullable=false)
/*     */   private HttpMethod method;
/*     */   @Column(name="action", length=100, nullable=false)
/*     */   private String action;
/*     */   @Column(name="user_id", length=100, nullable=false)
/*     */   private String userId;
/*     */   @Column(name="log_time", nullable=false)
/*     */   private Date logTime;
/*     */   
/*     */   public String getOid()
/*     */   {
/*  54 */     return this.oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOid(String oid)
/*     */   {
/*  62 */     this.oid = oid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPageName()
/*     */   {
/*  70 */     return this.pageName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPageName(String pageName)
/*     */   {
/*  78 */     this.pageName = pageName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public PageType getPageType()
/*     */   {
/*  86 */     return this.pageType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPageType(PageType pageType)
/*     */   {
/*  94 */     this.pageType = pageType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getClientIp()
/*     */   {
/* 102 */     return this.clientIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setClientIp(String clientIp)
/*     */   {
/* 110 */     this.clientIp = clientIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public HttpMethod getMethod()
/*     */   {
/* 118 */     return this.method;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMethod(HttpMethod method)
/*     */   {
/* 126 */     this.method = method;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAction()
/*     */   {
/* 134 */     return this.action;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAction(String action)
/*     */   {
/* 142 */     this.action = action;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUserId()
/*     */   {
/* 150 */     return this.userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUserId(String userId)
/*     */   {
/* 158 */     this.userId = userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLogTime()
/*     */   {
/* 166 */     return this.logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogTime(Date logTime)
/*     */   {
/* 174 */     this.logTime = logTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum PageType
/*     */   {
/* 181 */     ADMIN("1"),  OTHERS("0");
/*     */     
/*     */     private String pageType;
/*     */     
/* 185 */     private PageType(String pageType) { this.pageType = pageType; }
/*     */     
/*     */     public String getPageType()
/*     */     {
/* 189 */       return this.pageType;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static enum HttpMethod
/*     */   {
/* 197 */     GET("GET"),  POST("POST"),  HEAD("HEAD"),  PUT("PUT"),  DELETE("DELETE"),  TRACE("TRACE"),  CONNECT("CONNECT"),  OPTIONS("OPTIONS"),  DEBUG("DEBUG");
/*     */     
/*     */     private String httpMethod;
/*     */     
/* 201 */     private HttpMethod(String httpMethod) { this.httpMethod = httpMethod; }
/*     */     
/*     */     public String getHttpMethod()
/*     */     {
/* 205 */       return this.httpMethod;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\log\po\SiteAccessLogPo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */