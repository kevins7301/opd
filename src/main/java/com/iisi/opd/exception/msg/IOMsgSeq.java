/*    */ package com.iisi.opd.exception.msg;
/*    */ 
/*    */ 
/*    */ public class IOMsgSeq
/*    */   extends MsgSeq
/*    */ {
/*    */   protected IOMsgSeq(String msgSeq)
/*    */   {
/*  9 */     super(msgSeq);
/*    */   }
/*    */   
/* 12 */   public static final IOMsgSeq FILENOTFOUND = new IOMsgSeq("023");
/* 13 */   public static final IOMsgSeq PROPERTYNOTFOUND = new IOMsgSeq("024");
/* 14 */   public static final IOMsgSeq WRITING_STREAM_ERROR = new IOMsgSeq("025");
/* 15 */   public static final IOMsgSeq READING_STREAM_ERROR = new IOMsgSeq("026");
/* 16 */   public static final IOMsgSeq SERIALIZED_OBJECT_NOTFOUND = new IOMsgSeq("027");
/* 17 */   public static final IOMsgSeq VALIDATE = new IOMsgSeq("028");
/* 18 */   public static final IOMsgSeq SFTPEXCEPTION = new IOMsgSeq("029");
/* 19 */   public static final IOMsgSeq SFTPCHANGEFOLDERERROR = new IOMsgSeq("030");
/* 20 */   public static final IOMsgSeq CLOSESTREAMERROR = new IOMsgSeq("031");
/* 21 */   public static final IOMsgSeq FAIL_INIT_CRYPTOLOGY = new IOMsgSeq("032");
/* 22 */   public static final IOMsgSeq ENCODE_ERROR = new IOMsgSeq("033");
/* 23 */   public static final IOMsgSeq DECODE_ERROR = new IOMsgSeq("034");
/* 24 */   public static final IOMsgSeq FILEPATH_ERROR = new IOMsgSeq("035");
/* 25 */   public static final IOMsgSeq CREATE_FILE_FAIL = new IOMsgSeq("036");
/* 26 */   public static final IOMsgSeq SET_HOST_ERROR = new IOMsgSeq("037");
/* 27 */   public static final IOMsgSeq GET_SESSION_ERROR = new IOMsgSeq("038");
/* 28 */   public static final IOMsgSeq CONNECT_FAIL = new IOMsgSeq("039");
/* 29 */   public static final IOMsgSeq OPEN_CHANNEL_FAIL = new IOMsgSeq("040");
/* 30 */   public static final IOMsgSeq SFTP_NOT_CONNECTED = new IOMsgSeq("041");
/* 31 */   public static final IOMsgSeq SFTP_CHANGE_MODE_ERROR = new IOMsgSeq("042");
/* 32 */   public static final IOMsgSeq SFTP_CHANGE_FIILENAME_ERROR = new IOMsgSeq("043");
/* 33 */   public static final IOMsgSeq SFTP_GETPATHFILE_ERROR = new IOMsgSeq("044");
/* 34 */   public static final IOMsgSeq SFTP_DELFILE_ERROR = new IOMsgSeq("045");
/*    */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\exception\msg\IOMsgSeq.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */