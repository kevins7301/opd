/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ public class MsgType
/*    */ {
/*    */   private final String msgType;
/*    */   
/*    */   protected MsgType(String msgType)
/*    */   {
/*  9 */     this.msgType = msgType;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 14 */     return this.msgType;
/*    */   }
/*    */   
/*    */ 
/* 18 */   public static final MsgType SPRING = new MsgType("SYS");
/* 19 */   public static final MsgType SOCKET = new MsgType("SYS");
/* 20 */   public static final MsgType DATABASE = new MsgType("SYS");
/* 21 */   public static final MsgType INPUTOUTPUT = new MsgType("SYS");
/* 22 */   public static final MsgType CACHE = new MsgType("SYS");
/* 23 */   public static final MsgType ENTITY = new MsgType("SYS");
/* 24 */   public static final MsgType SFTP = new MsgType("SYS");
/* 25 */   public static final MsgType BASE = new MsgType("BASE");
/* 26 */   public static final MsgType CH0 = new MsgType("CH0");
/* 27 */   public static final MsgType DB0 = new MsgType("DB0");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\MsgType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */