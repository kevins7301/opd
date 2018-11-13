/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ public class SystemException
/*    */   extends DefineException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public SystemException(String programid, String exceptionInfo)
/*    */   {
/* 14 */     super(programid, exceptionInfo);
/*    */   }
/*    */   
/*    */   public SystemException(String programid, Throwable e)
/*    */   {
/* 19 */     super(programid, e);
/*    */   }
/*    */   
/*    */   public SystemException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e)
/*    */   {
/* 24 */     super(programId, msgType, msgSeq, msgLevel, e);
/*    */   }
/*    */   
/*    */   public SystemException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] params, Throwable e)
/*    */   {
/* 29 */     super(programId, msgType, msgSeq, msgLevel, params, e);
/*    */   }
/*    */   
/*    */   public SystemException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] params)
/*    */   {
/* 34 */     super(programId, msgType, msgSeq, msgLevel, params);
/*    */   }
/*    */   
/*    */   public SystemException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel)
/*    */   {
/* 39 */     super(programId, msgType, msgSeq, msgLevel);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\SystemException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */