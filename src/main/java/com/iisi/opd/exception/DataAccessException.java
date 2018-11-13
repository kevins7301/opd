/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ public class DataAccessException
/*    */   extends DefineException
/*    */ {
/* 10 */   private String errorCodes = "";
/*    */   
/* 12 */   private String errorMessages = "";
/*    */   
/* 14 */   private StackTraceElement[] errorCause = new StackTraceElement[0];
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public String getErrorCodes() {
/* 18 */     return super.getSystemId() + "_" + super.getProgramId() + "_" + super.getMsgType() + "_" + super.getMsgSeq() + "_" + super.getMsgLevel();
/*    */   }
/*    */   
/*    */   public String getErrorMessages()
/*    */   {
/* 23 */     return super.getMessage();
/*    */   }
/*    */   
/*    */   public StackTraceElement[] getErrorCause()
/*    */   {
/* 28 */     return getCause().getStackTrace();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DataAccessException(String programid, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel)
/*    */   {
/* 37 */     super(programid, msgType, msgSeq, msgLevel);
/*    */   }
/*    */   
/*    */   public DataAccessException(String programid, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e)
/*    */   {
/* 42 */     super(programid, msgType, msgSeq, msgLevel, e);
/*    */   }
/*    */   
/*    */   public DataAccessException(String programid, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] param)
/*    */   {
/* 47 */     super(programid, msgType, msgSeq, msgLevel, param);
/*    */   }
/*    */   
/*    */   public DataAccessException(String programid, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] param, Throwable e)
/*    */   {
/* 52 */     super(programid, msgType, msgSeq, msgLevel, param, e);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\DataAccessException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */