/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ public class ColumnCheckMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected ColumnCheckMsgSeq(String msgSeq)
/*    */   {
/*  9 */     super(msgSeq);
/*    */   }
/*    */   
/* 12 */   public static final ColumnCheckMsgSeq COLUMN_CHECK_PASSED = new ColumnCheckMsgSeq("000");
/* 13 */   public static final ColumnCheckMsgSeq COLUMN_CANT_EMPTY = new ColumnCheckMsgSeq("001");
/* 14 */   public static final ColumnCheckMsgSeq CANT_MODIFY_KEY_COLUMN = new ColumnCheckMsgSeq("002");
/* 15 */   public static final ColumnCheckMsgSeq OVER_DATA_LENGTH = new ColumnCheckMsgSeq("006");
/* 16 */   public static final ColumnCheckMsgSeq CHECK_NUMBER_ERROR = new ColumnCheckMsgSeq("007");
/* 17 */   public static final ColumnCheckMsgSeq DATA_CANT_MODITY = new ColumnCheckMsgSeq("008");
/* 18 */   public static final ColumnCheckMsgSeq DATAVALUE_OVER_RANGE = new ColumnCheckMsgSeq("009");
/* 19 */   public static final ColumnCheckMsgSeq DATA_WRONG = new ColumnCheckMsgSeq("010");
/* 20 */   public static final ColumnCheckMsgSeq FILLED_FULL_OF_DATA_OR_BLANK = new ColumnCheckMsgSeq("012");
/* 21 */   public static final ColumnCheckMsgSeq NUMBER_TYPE_DATA_WRONG = new ColumnCheckMsgSeq("013");
/* 22 */   public static final ColumnCheckMsgSeq TXT_TYPE_DATA_WRONG = new ColumnCheckMsgSeq("014");
/* 23 */   public static final ColumnCheckMsgSeq PASSWORD_WRONG = new ColumnCheckMsgSeq("015");
/* 24 */   public static final ColumnCheckMsgSeq FILLED_FULL_OF_DATA = new ColumnCheckMsgSeq("025");
/* 25 */   public static final ColumnCheckMsgSeq FILLED_FULL_OF_BLANK = new ColumnCheckMsgSeq("026");
/* 26 */   public static final ColumnCheckMsgSeq NO_EXEC_PERMISSION = new ColumnCheckMsgSeq("051");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\ColumnCheckMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */