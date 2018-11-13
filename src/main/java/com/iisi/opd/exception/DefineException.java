/*     */ package com.iisi.opd.exception;
/*     */ 
/*     */ import com.iisi.opd.exception.msg.DaoMsgSeq;
/*     */ import com.iisi.opd.exception.msg.MsgLevel;
/*     */ import com.iisi.opd.exception.msg.MsgSeq;
/*     */ import com.iisi.opd.exception.msg.MsgType;
/*     */ import com.iisi.opd.exception.util.AppContext;
/*     */ import com.iisi.opd.exception.util.MessageLoader;
/*     */ import java.io.Serializable;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefineException
/*     */   extends RuntimeException
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2795706677008665127L;
/*     */   private String programId;
/*     */   private MsgType msgType;
/*     */   private MsgSeq msgSeq;
/*     */   private MsgLevel msgLevel;
/*     */   private Object[] parameters;
/*     */   private String systemId;
/*     */   private String detailMsg;
/*     */   private MessageLoader messageLoader;
/*     */   protected Logger logger;
/*     */   protected Logger exceptionLogger;
/*     */   
/*     */   private void initException()
/*     */   {
/*  35 */     ApplicationContext context = AppContext.getApplicationContext();
/*  36 */     if ((context != null) && 
/*  37 */       (context.getBean("MessageLoader") != null)) {
/*  38 */       this.messageLoader = ((MessageLoader)context.getBean("MessageLoader"));
/*     */     }
/*     */     
/*  41 */     logError();
/*     */   }
/*     */   
/*     */   public String getSystemId()
/*     */   {
/*  46 */     return this.systemId;
/*     */   }
/*     */   
/*     */   public void setSystemId(String systemId)
/*     */   {
/*  51 */     this.systemId = systemId;
/*     */   }
/*     */   
/*     */   public DefineException(String programid, Throwable e)
/*     */   {
/*  56 */     super(e);
/*  57 */     this.systemId = "";
/*  58 */     this.detailMsg = "";
/*  59 */     this.logger = LoggerFactory.getLogger(DefineException.class);
/*  60 */     this.exceptionLogger = LoggerFactory.getLogger("ExceptionLogger");
/*  61 */     this.programId = programid;
/*  62 */     this.detailMsg = e.getMessage();
/*  63 */     initException();
/*     */   }
/*     */   
/*     */   public DefineException(String programid, String exceptionInfo)
/*     */   {
/*  68 */     this.systemId = "";
/*  69 */     this.detailMsg = "";
/*  70 */     this.logger = LoggerFactory.getLogger(DefineException.class);
/*  71 */     this.exceptionLogger = LoggerFactory.getLogger("ExceptionLogger");
/*  72 */     this.programId = programid;
/*  73 */     this.detailMsg = exceptionInfo;
/*  74 */     initException();
/*     */   }
/*     */   
/*     */   public DefineException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e)
/*     */   {
/*  79 */     super(e);
/*  80 */     this.systemId = "";
/*  81 */     this.detailMsg = "";
/*  82 */     this.logger = LoggerFactory.getLogger(DefineException.class);
/*  83 */     this.exceptionLogger = LoggerFactory.getLogger("ExceptionLogger");
/*  84 */     this.msgType = msgType;
/*  85 */     this.msgSeq = msgSeq;
/*  86 */     this.msgLevel = msgLevel;
/*  87 */     this.programId = programId;
/*  88 */     this.detailMsg = e.getMessage();
/*  89 */     initException();
/*     */   }
/*     */   
/*     */   public DefineException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel)
/*     */   {
/*  94 */     this.systemId = "";
/*  95 */     this.detailMsg = "";
/*  96 */     this.logger = LoggerFactory.getLogger(DefineException.class);
/*  97 */     this.exceptionLogger = LoggerFactory.getLogger("ExceptionLogger");
/*  98 */     this.msgType = msgType;
/*  99 */     this.msgSeq = msgSeq;
/* 100 */     this.msgLevel = msgLevel;
/* 101 */     this.programId = programId;
/* 102 */     initException();
/*     */   }
/*     */   
/*     */   public DefineException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] params)
/*     */   {
/* 107 */     this.systemId = "";
/* 108 */     this.detailMsg = "";
/* 109 */     this.logger = LoggerFactory.getLogger(DefineException.class);
/* 110 */     this.exceptionLogger = LoggerFactory.getLogger("ExceptionLogger");
/* 111 */     this.msgType = msgType;
/* 112 */     this.msgSeq = msgSeq;
/* 113 */     this.msgLevel = msgLevel;
/* 114 */     this.programId = programId;
/* 115 */     this.parameters = params;
/* 116 */     initException();
/*     */   }
/*     */   
/*     */   public DefineException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] parameters, Throwable e)
/*     */   {
/* 121 */     super(e);
/* 122 */     this.systemId = "";
/* 123 */     this.detailMsg = "";
/* 124 */     this.logger = LoggerFactory.getLogger(DefineException.class);
/* 125 */     this.exceptionLogger = LoggerFactory.getLogger("ExceptionLogger");
/* 126 */     this.msgType = msgType;
/* 127 */     this.msgSeq = msgSeq;
/* 128 */     this.msgLevel = msgLevel;
/* 129 */     this.programId = programId;
/* 130 */     this.parameters = parameters;
/* 131 */     this.detailMsg = e.getMessage();
/* 132 */     initException();
/*     */   }
/*     */   
/*     */   public String getMessage()
/*     */   {
/* 137 */     StringBuffer errorMsg = new StringBuffer();
/* 138 */     if (!"".equals(this.systemId))
/* 139 */       errorMsg.append("[" + this.systemId + "]");
/* 140 */     if (!"".equals(this.programId))
/* 141 */       errorMsg.append("[" + this.programId + "]");
/* 142 */     if ((this.msgType != null) && (this.msgSeq != null) && (this.msgLevel != null)) {
/* 143 */       if ((this.msgType == MsgType.DATABASE) && (this.msgSeq == DaoMsgSeq.UNDEFINEDSQLEXCEPTION) && (this.msgLevel == MsgLevel.ERROR)) {
/* 144 */         errorMsg.append("[" + String.valueOf(this.msgType) + String.valueOf(this.msgSeq) + "-Undefined SQLException]");
/*     */       } else {
/* 146 */         errorMsg.append("[" + String.valueOf(this.msgType) + String.valueOf(this.msgSeq) + "-" + getErrorMsg() + "]");
/*     */       }
/*     */     }
/* 149 */     if (!"".equals(this.detailMsg)) {
/* 150 */       errorMsg.append("[" + this.detailMsg + "]");
/*     */     }
/* 152 */     return errorMsg.toString();
/*     */   }
/*     */   
/*     */   private String getErrorMsg()
/*     */   {
/* 157 */     if ((this.msgType == null) || (this.msgSeq == null) || (this.msgLevel == null))
/* 158 */       return "msg Code is null";
/* 159 */     String msg = null;
/* 160 */     if (this.messageLoader != null) {
/* 161 */       msg = this.messageLoader.getString(String.valueOf(this.msgType) + "." + String.valueOf(this.msgSeq) + "." + String.valueOf(this.msgLevel));
/*     */     }
/*     */     
/* 164 */     if (msg != null) {
/* 165 */       return msg;
/*     */     }
/*     */     
/* 168 */     return String.valueOf(this.msgType) + String.valueOf(this.msgSeq) + String.valueOf(this.msgLevel) + " not found";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getProgramId()
/*     */   {
/* 175 */     return this.programId;
/*     */   }
/*     */   
/*     */   public void setProgramId(String programId)
/*     */   {
/* 180 */     this.programId = programId;
/*     */   }
/*     */   
/*     */   public MsgType getMsgType()
/*     */   {
/* 185 */     return this.msgType;
/*     */   }
/*     */   
/*     */   public void setMsgType(MsgType msgType)
/*     */   {
/* 190 */     this.msgType = msgType;
/*     */   }
/*     */   
/*     */   public MsgSeq getMsgSeq()
/*     */   {
/* 195 */     return this.msgSeq;
/*     */   }
/*     */   
/*     */   public void setMsgSeq(MsgSeq msgSeq)
/*     */   {
/* 200 */     this.msgSeq = msgSeq;
/*     */   }
/*     */   
/*     */   public MsgLevel getMsgLevel()
/*     */   {
/* 205 */     return this.msgLevel;
/*     */   }
/*     */   
/*     */   public void setMsgLevel(MsgLevel msgLevel)
/*     */   {
/* 210 */     this.msgLevel = msgLevel;
/*     */   }
/*     */   
/*     */   public Object[] getParameters()
/*     */   {
/* 215 */     return this.parameters;
/*     */   }
/*     */   
/*     */   public void setParameters(Object[] parameters)
/*     */   {
/* 220 */     this.parameters = parameters;
/*     */   }
/*     */   
/*     */   protected void logError()
/*     */   {
/* 225 */     if ((MsgLevel.FATAL.toString().equalsIgnoreCase(String.valueOf(getMsgLevel()))) || (MsgLevel.ERROR.toString().equalsIgnoreCase(String.valueOf(getMsgLevel()))))
/*     */     {
/*     */ 
/* 228 */       this.exceptionLogger.error(getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\DefineException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */