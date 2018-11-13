/*    */ package com.iisi.common.util;

/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;

/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Component;

/*    */
/*    */ import com.iisi.opd.exception.OpdException;
/*    */ import com.iisi.opd.exception.msg.ErrorCodeEnum;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ @Component
/*    */ public class SqlParamUtil
/*    */ {
    /* 19 */ public static final Logger logger = LoggerFactory.getLogger(SqlParamUtil.class);

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */ public Object getParameter(String value, String type)/*    */ throws OpdException
    /*    */ {
        /* 29 */ Object param = value;
        /*    */ try {
            /* 31 */ if (StringUtils.equals(type, "int")) {
                /* 32 */ param = Integer.valueOf(Integer.parseInt(value));
                /* 33 */ } else if (StringUtils.equals(type, "long")) {
                /* 34 */ param = Long.valueOf(Long.parseLong(value));
                /* 35 */ } else if (StringUtils.equals(type, "double")) {
                /* 36 */ param = Double.valueOf(Double.parseDouble(value));
                /* 37 */ } else if (StringUtils.equals(type, "boolean")) {
                /* 38 */ param = Boolean.valueOf(Boolean.parseBoolean(value));
                /* 39 */ } else if (StringUtils.equals(type, "timestamp")) {
                /* 40 */ SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                /* 41 */ param = format.parseObject(value);
                /*    */ }
            /*    */ }
        /*    */ catch (NumberFormatException e)
        /*    */ {
            /* 46 */ logger.error("[NumberFormatException]SqlParamUtil.getParameter{}", e.fillInStackTrace());
            /* 47 */ throw new OpdException(ErrorCodeEnum.ERR_1001300_EXCEPTION);
            /*    */ }
        /*    */ catch (ParseException e)
        /*    */ {
            /* 51 */ logger.error("[ParseException]SqlParamUtil.getParameter{}", e.fillInStackTrace());
            /* 52 */ throw new OpdException(ErrorCodeEnum.ERR_1001301_EXCEPTION);
            /*    */ }
        /* 54 */ return param;
        /*    */ }
    /*    */ }
