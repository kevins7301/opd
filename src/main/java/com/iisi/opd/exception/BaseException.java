/*     */ package com.iisi.opd.exception;
/*     */ 
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;
/*     */ import com.iisi.opd.exception.util.MessageUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.collections.CollectionUtils;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.core.io.ClassPathResource;
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
/*     */ public abstract class BaseException
/*     */   extends RuntimeException
/*     */ {
/*     */   private static final long serialVersionUID = -6522410790915858984L;
/*  34 */   private static Logger log = LoggerFactory.getLogger(BaseException.class);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ErrorCodeEnum errorCode;
/*     */   
/*     */ 
/*     */ 
/*  43 */   private List params = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Document document;
/*     */   
/*     */ 
/*     */ 
/*     */   private String[] arguments;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(String message)
/*     */   {
/*  59 */     this(ErrorCodeEnum.DEFAULT_EXCEPTION, message, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(List messages)
/*     */   {
/*  71 */     this(ErrorCodeEnum.DEFAULT_EXCEPTION, messages, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(ErrorCodeEnum errorCode)
/*     */   {
/*  81 */     this(errorCode, "", null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(ErrorCodeEnum errorCode, String message)
/*     */   {
/*  93 */     this(errorCode, message, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(ErrorCodeEnum errorCode, String[] arguments)
/*     */   {
/* 106 */     this(errorCode, null, arguments);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(ErrorCodeEnum errorCode, List messages)
/*     */   {
/* 118 */     this(errorCode, messages, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(Throwable cause)
/*     */   {
/* 128 */     this(ErrorCodeEnum.DEFAULT_EXCEPTION, "", cause);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(String message, Throwable cause)
/*     */   {
/* 140 */     this(ErrorCodeEnum.DEFAULT_EXCEPTION, message, cause);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(List messages, Throwable cause)
/*     */   {
/* 152 */     this(ErrorCodeEnum.DEFAULT_EXCEPTION, messages, cause);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseException(ErrorCodeEnum errorCode, Throwable cause)
/*     */   {
/* 164 */     this(errorCode, "", cause);
/*     */   }
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
/*     */   public BaseException(ErrorCodeEnum errorCode, String message, Throwable cause)
/*     */   {
/* 178 */     super(cause);
/* 179 */     init();
/* 180 */     this.errorCode = errorCode;
/*     */     
/* 182 */     if (StringUtils.isNotBlank(message)) {
/* 183 */       this.params = new ArrayList();
/* 184 */       this.params.add(message);
/*     */     }
/*     */   }
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
/*     */   public BaseException(ErrorCodeEnum errorCode, Throwable cause, String[] arguments)
/*     */   {
/* 205 */     super(cause);
/* 206 */     init();
/* 207 */     this.errorCode = errorCode;
/*     */     
/* 209 */     if (!ArrayUtils.isEmpty(arguments))
/*     */     {
/* 211 */       this.arguments = arguments;
/*     */       
/* 213 */       this.params = Arrays.asList(arguments);
/*     */     }
/*     */   }
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
/*     */   public BaseException(ErrorCodeEnum errorCode, List messages, Throwable cause)
/*     */   {
/* 231 */     super(cause);
/* 232 */     init();
/* 233 */     this.errorCode = errorCode;
/*     */     
/* 235 */     if (CollectionUtils.size(messages) > 0) {
/* 236 */       this.params = messages;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setErrorCode(ErrorCodeEnum errorCode)
/*     */   {
/* 250 */     this.errorCode = errorCode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ErrorCodeEnum getErrorCode()
/*     */   {
/* 260 */     return this.errorCode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getErrorMessage()
/*     */   {
/* 269 */     return MessageUtil.getMessage(this.errorCode, this.params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMessage()
/*     */   {
/* 279 */     String superExMsg = StringUtils.isNotBlank(super.getMessage()) ? super.getMessage() : "";
/*     */     
/*     */ 
/* 282 */     String msg = StringUtils.isNotBlank(getErrorMessage()) ? getErrorMessage() : "";
/*     */     
/*     */ 
/*     */ 
/* 286 */     return msg + " " + superExMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLocalizedMessage()
/*     */   {
/* 295 */     return getLocalizedMessage(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLocalizedMessage(Locale locale)
/*     */   {
/* 305 */     String superExMsg = StringUtils.isNotBlank(super.getMessage()) ? super.getMessage() : "";
/*     */     
/*     */ 
/* 308 */     String msg = MessageUtil.getMessage(getErrorCode(), locale, this.params);
/*     */     
/* 310 */     return msg + " " + superExMsg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getParameters()
/*     */   {
/* 319 */     if (this.params == null) {
/* 320 */       return Collections.EMPTY_LIST;
/*     */     }
/* 322 */     return this.params;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static void init()
/*     */   {
/*     */     try
/*     */     {
/* 336 */       SAXReader saxReader = new SAXReader();
/*     */       
/* 338 */       if (document == null) {
/* 339 */         document = saxReader.read(new ClassPathResource("Excecption-Conf.xml").getInputStream());
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 343 */       log.error("init", e.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Document getDocument()
/*     */   {
/* 352 */     init();
/* 353 */     return document;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[] getArguments()
/*     */   {
/* 361 */     return ArrayUtils.isEmpty(this.arguments) ? ArrayUtils.EMPTY_STRING_ARRAY : this.arguments;
/*     */   }
/*     */   
/*     */   public List getArgumentsInList()
/*     */   {
/* 366 */     if (ArrayUtils.isEmpty(this.arguments)) {
/* 367 */       return Collections.EMPTY_LIST;
/*     */     }
/*     */     
/* 370 */     return Arrays.asList(this.arguments);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setArguments(String[] arguments)
/*     */   {
/* 377 */     this.arguments = arguments;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\BaseException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */