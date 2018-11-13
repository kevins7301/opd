/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SocketMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected SocketMsgSeq(String msgSeq)
/*    */   {
/* 10 */     super(msgSeq);
/*    */   }
/*    */   
/* 13 */   public static final SocketMsgSeq CONNECTFAIL = new SocketMsgSeq("046");
/* 14 */   public static final SocketMsgSeq UNKNOWNHOST = new SocketMsgSeq("047");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\SocketMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */