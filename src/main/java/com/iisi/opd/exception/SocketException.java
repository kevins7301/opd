/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ public class SocketException
/*    */   extends SystemException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public SocketException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e, Object[] param)
/*    */   {
/* 14 */     super(programId, msgType, msgSeq, msgLevel, param, e);
/*    */   }
/*    */   
/*    */   public SocketException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e)
/*    */   {
/* 19 */     super(programId, msgType, msgSeq, msgLevel, e);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\SocketException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */