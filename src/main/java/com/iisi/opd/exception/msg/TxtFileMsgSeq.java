/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TxtFileMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected TxtFileMsgSeq(String msgSeq)
/*    */   {
/* 16 */     super(msgSeq);
/*    */   }
/*    */   
/* 19 */   public static final TxtFileMsgSeq TASK_DONE = new TxtFileMsgSeq("000");
/* 20 */   public static final TxtFileMsgSeq FOREIGN_KEY_DUPLICATE = new TxtFileMsgSeq("002");
/* 21 */   public static final TxtFileMsgSeq DATA_IN_USED = new TxtFileMsgSeq("015");
/* 22 */   public static final TxtFileMsgSeq FILE_IN_USED = new TxtFileMsgSeq("018");
/* 23 */   public static final TxtFileMsgSeq FILE_CANT_SAVE = new TxtFileMsgSeq("020");
/* 24 */   public static final TxtFileMsgSeq PRIMARY_KEY_DUPLICATE = new TxtFileMsgSeq("022");
/* 25 */   public static final TxtFileMsgSeq DATA_NOT_FOUND = new TxtFileMsgSeq("023");
/* 26 */   public static final TxtFileMsgSeq DO_OTHER_JOB_DATA_IN_USED = new TxtFileMsgSeq("027");
/* 27 */   public static final TxtFileMsgSeq FILE_IN_USED_PLS_END_TASK = new TxtFileMsgSeq("028");
/* 28 */   public static final TxtFileMsgSeq UNKNOWN_ERROR_CODE9 = new TxtFileMsgSeq("090");
/* 29 */   public static final TxtFileMsgSeq FILE_NOT_FOUND = new TxtFileMsgSeq("091");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\TxtFileMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */