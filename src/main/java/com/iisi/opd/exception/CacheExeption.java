/*    */ package com.iisi.opd.exception;
/*    */ 
/*    */ import com.iisi.opd.exception.msg.MsgLevel;
/*    */ import com.iisi.opd.exception.msg.MsgSeq;
/*    */ import com.iisi.opd.exception.msg.MsgType;
/*    */ 
/*    */ 
/*    */ public class CacheExeption
/*    */   extends DefineException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public CacheExeption(String programId, MsgSeq msgSeq, MsgLevel msgLevel)
/*    */   {
/* 15 */     super(programId, MsgType.CACHE, msgSeq, msgLevel);
/*    */   }
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\CacheExeption.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */