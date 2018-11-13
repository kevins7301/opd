/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ public class DaoMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected DaoMsgSeq(String msgSeq)
/*    */   {
/*  9 */     super(msgSeq);
/*    */   }
/*    */   
/* 12 */   public static final DaoMsgSeq INSERTERROR = new DaoMsgSeq("005");
/* 13 */   public static final DaoMsgSeq INSERTOK = new DaoMsgSeq("006");
/* 14 */   public static final DaoMsgSeq UPDATEERROR = new DaoMsgSeq("007");
/* 15 */   public static final DaoMsgSeq UPDATEOK = new DaoMsgSeq("008");
/* 16 */   public static final DaoMsgSeq DELETEERROR = new DaoMsgSeq("009");
/* 17 */   public static final DaoMsgSeq DELETEOK = new DaoMsgSeq("010");
/* 18 */   public static final DaoMsgSeq TRANSACTIONERR = new DaoMsgSeq("011");
/* 19 */   public static final DaoMsgSeq ROLLBACK = new DaoMsgSeq("012");
/* 20 */   public static final DaoMsgSeq ENTITYISNULL = new DaoMsgSeq("013");
/* 21 */   public static final DaoMsgSeq UNDEFINEDSQLEXCEPTION = new DaoMsgSeq("014");
/* 22 */   public static final DaoMsgSeq UNKNOWNEXCEPTIONTYPE = new DaoMsgSeq("015");
/* 23 */   public static final DaoMsgSeq VALUETOOLARGE = new DaoMsgSeq("016");
/* 24 */   public static final DaoMsgSeq ARGUMENTEXCEPTION = new DaoMsgSeq("017");
/* 25 */   public static final DaoMsgSeq INVALIDSTATEEXCEPTION = new DaoMsgSeq("018");
/* 26 */   public static final DaoMsgSeq DUPLICATE = new DaoMsgSeq("019");
/* 27 */   public static final DaoMsgSeq ENTITYCLOSED = new DaoMsgSeq("020");
/* 28 */   public static final DaoMsgSeq NOTAENTITY = new DaoMsgSeq("021");
/* 29 */   public static final DaoMsgSeq PERSISTENCE = new DaoMsgSeq("022");
/* 30 */   public static final DaoMsgSeq OPTIMISTICLOCKEXCEPTION = new DaoMsgSeq("056");
/* 31 */   public static final DaoMsgSeq OVER_MAXIMUM_QUERY_SIZE = new DaoMsgSeq("057");
/* 32 */   public static final DaoMsgSeq BAD_SQL_GRAMMAR = new DaoMsgSeq("058");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\DaoMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */