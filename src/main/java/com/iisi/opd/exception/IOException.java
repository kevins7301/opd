/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ 
/*    */ public class IOException
/*    */   extends SystemException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public IOException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] params)
/*    */   {
/* 15 */     super(programId, msgType, msgSeq, msgLevel, params);
/*    */   }
/*    */   
/*    */   public IOException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel)
/*    */   {
/* 20 */     super(programId, msgType, msgSeq, msgLevel);
/*    */   }
/*    */   
/*    */   public IOException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e)
/*    */   {
/* 25 */     super(programId, msgType, msgSeq, msgLevel, e);
/*    */   }
/*    */   
/*    */   public IOException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Throwable e, Object[] params)
/*    */   {
/* 30 */     super(programId, msgType, msgSeq, msgLevel, params, e);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\IOException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */