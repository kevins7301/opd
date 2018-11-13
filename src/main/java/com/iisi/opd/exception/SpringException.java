/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ public class SpringException
/*    */   extends SystemException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public SpringException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] params, Throwable e)
/*    */   {
/* 14 */     super(programId, msgType, msgSeq, msgLevel, params, e);
/*    */   }
/*    */   
/*    */   public SpringException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e)
/*    */   {
/* 19 */     super(programId, msgType, msgSeq, msgLevel, e);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\SpringException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */