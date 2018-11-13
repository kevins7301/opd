/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ public class DatabaseMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected DatabaseMsgSeq(String msgSeq)
/*    */   {
/*  9 */     super(msgSeq);
/*    */   }
/*    */   
/* 12 */   public static final DatabaseMsgSeq TASK_DONE = new DatabaseMsgSeq("000");
/* 13 */   public static final DatabaseMsgSeq SERVICE_NAME_NOT_FOUND = new DatabaseMsgSeq("001");
/* 14 */   public static final DatabaseMsgSeq DATABASE_CANT_USE = new DatabaseMsgSeq("002");
/* 15 */   public static final DatabaseMsgSeq LOAD_DATA_OVER_RANGE = new DatabaseMsgSeq("010");
/* 16 */   public static final DatabaseMsgSeq PK_DUPLICATE = new DatabaseMsgSeq("022");
/* 17 */   public static final DatabaseMsgSeq DATA_NOT_FOUND = new DatabaseMsgSeq("023");
/* 18 */   public static final DatabaseMsgSeq DATA_HAS_BEEN_CHANGED = new DatabaseMsgSeq("025");
/* 19 */   public static final DatabaseMsgSeq OTHER_ERROR = new DatabaseMsgSeq("030");
/* 20 */   public static final DatabaseMsgSeq FILE_NUMBER_IN_USED = new DatabaseMsgSeq("031");
/* 21 */   public static final DatabaseMsgSeq TABLE_VIEW_NOT_FOUND = new DatabaseMsgSeq("097");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\DatabaseMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */