/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ public class EntityMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected EntityMsgSeq(String msgSeq)
/*    */   {
/*  9 */     super(msgSeq);
/*    */   }
/*    */   
/* 12 */   public static final EntityMsgSeq METHODILLEGALACCESS = new EntityMsgSeq("000");
/* 13 */   public static final EntityMsgSeq NOTABLEANNOTATION = new EntityMsgSeq("001");
/* 14 */   public static final EntityMsgSeq ILLEGALARGUMENT = new EntityMsgSeq("002");
/* 15 */   public static final EntityMsgSeq INVOCATIONTARGET = new EntityMsgSeq("003");
/* 16 */   public static final EntityMsgSeq NOCOLUMNANNOTATION = new EntityMsgSeq("004");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\EntityMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */