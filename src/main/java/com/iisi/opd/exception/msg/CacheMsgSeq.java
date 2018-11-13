/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ public class CacheMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected CacheMsgSeq(String msgSeq)
/*    */   {
/*  9 */     super(msgSeq);
/*    */   }
/*    */   
/* 12 */   public static final CacheMsgSeq CACHENOTFOUND = new CacheMsgSeq("050");
/* 13 */   public static final CacheMsgSeq ELEMENTNOTFOUND = new CacheMsgSeq("051");
/* 14 */   public static final CacheMsgSeq SERVICENOTFOUND = new CacheMsgSeq("052");
/* 15 */   public static final CacheMsgSeq INITIAL = new CacheMsgSeq("053");
/* 16 */   public static final CacheMsgSeq ILLEGALACCESS = new CacheMsgSeq("054");
/* 17 */   public static final CacheMsgSeq CLASSNOTFOUND = new CacheMsgSeq("055");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\CacheMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */