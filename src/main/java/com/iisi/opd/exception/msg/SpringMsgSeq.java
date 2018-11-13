/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpringMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected SpringMsgSeq(String msgSeq)
/*    */   {
/* 10 */     super(msgSeq);
/*    */   }
/*    */   
/* 13 */   public static final SpringMsgSeq CONFIGNOTFOUND = new SpringMsgSeq("048");
/* 14 */   public static final SpringMsgSeq BEANNOTFOUND = new SpringMsgSeq("049");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\SpringMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */