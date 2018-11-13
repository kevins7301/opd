/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ public class MsgLevel
/*    */ {
/*    */   private final String msgLevel;
/*    */   
/*    */   private MsgLevel(String msgLevel)
/*    */   {
/*  9 */     this.msgLevel = msgLevel;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 14 */     return this.msgLevel;
/*    */   }
/*    */   
/*    */ 
/* 18 */   public static final MsgLevel SUCCESS = new MsgLevel("S");
/* 19 */   public static final MsgLevel INFO = new MsgLevel("I");
/* 20 */   public static final MsgLevel WARN = new MsgLevel("W");
/* 21 */   public static final MsgLevel ERROR = new MsgLevel("E");
/* 22 */   public static final MsgLevel FATAL = new MsgLevel("F");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\MsgLevel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */