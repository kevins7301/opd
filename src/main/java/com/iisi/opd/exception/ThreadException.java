/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ public class ThreadException
/*    */   extends SystemException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public ThreadException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] parameters, Throwable e)
/*    */   {
/* 14 */     super(programId, msgType, msgSeq, msgLevel, parameters, e);
/*    */   }
/*    */   
/*    */   public ThreadException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] parameters)
/*    */   {
/* 19 */     super(programId, msgType, msgSeq, msgLevel, parameters);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\ThreadException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */