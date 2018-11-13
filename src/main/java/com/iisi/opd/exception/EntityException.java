/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ 
/*    */ public class EntityException
/*    */   extends DefineException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public EntityException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel, Object[] parameters, Throwable e)
/*    */   {
/* 15 */     super(programId, msgType, msgSeq, msgLevel, parameters, e);
/*    */   }
/*    */   
/*    */   public EntityException(String programId, MsgType msgType, MsgSeq msgSeq, MsgLevel msgLevel)
/*    */   {
/* 20 */     super(programId, msgType, msgSeq, msgLevel);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\EntityException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */