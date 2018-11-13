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
/*    */ public class TaskMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected TaskMsgSeq(String msgSeq)
/*    */   {
/* 16 */     super(msgSeq);
/*    */   }
/*    */   
/* 19 */   public static final TaskMsgSeq TASK_DONE = new TaskMsgSeq("000");
/* 20 */   public static final TaskMsgSeq KEEP_RECEIVING_DATA = new TaskMsgSeq("001");
/* 21 */   public static final TaskMsgSeq TOP_DATA = new TaskMsgSeq("002");
/* 22 */   public static final TaskMsgSeq LAST_DATA = new TaskMsgSeq("003");
/* 23 */   public static final TaskMsgSeq INSERT_OK = new TaskMsgSeq("004");
/* 24 */   public static final TaskMsgSeq INSERT_FAIL = new TaskMsgSeq("005");
/* 25 */   public static final TaskMsgSeq UPDATE_OK = new TaskMsgSeq("006");
/* 26 */   public static final TaskMsgSeq UPDATE_FAIL = new TaskMsgSeq("007");
/* 27 */   public static final TaskMsgSeq DELETE_OK = new TaskMsgSeq("008");
/* 28 */   public static final TaskMsgSeq DELETE_FAIL = new TaskMsgSeq("009");
/* 29 */   public static final TaskMsgSeq PROCESSING_PLS_WAIT = new TaskMsgSeq("011");
/* 30 */   public static final TaskMsgSeq CANT_EXEC_THIS_TASK = new TaskMsgSeq("012");
/* 31 */   public static final TaskMsgSeq TASK_NOT_FOUND = new TaskMsgSeq("013");
/* 32 */   public static final TaskMsgSeq FUNCTION_KEY_ERROR = new TaskMsgSeq("014");
/* 33 */   public static final TaskMsgSeq PASSWORD_WRONG = new TaskMsgSeq("015");
/* 34 */   public static final TaskMsgSeq LOAD_MAXIMUM_DATA_SIZE = new TaskMsgSeq("016");
/* 35 */   public static final TaskMsgSeq FILE_NOT_FOUND = new TaskMsgSeq("019");
/* 36 */   public static final TaskMsgSeq IS_REALLY_EXEC = new TaskMsgSeq("021");
/* 37 */   public static final TaskMsgSeq PLS_KEEP_GOING = new TaskMsgSeq("022");
/* 38 */   public static final TaskMsgSeq IS_PRINT = new TaskMsgSeq("024");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\TaskMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */