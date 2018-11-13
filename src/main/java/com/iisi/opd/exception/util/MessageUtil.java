/*     */ package com.iisi.opd.exception.util;

/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;

/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/*     */
/*     */ import com.iisi.opd.exception.msg.ErrorCodeEnum;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class MessageUtil
/*     */ {
    /*  19 */ private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);
    /*     */
    /*     */
    /*     */
    /*     */ private static ReloadableResourceBundleMessageSource resource;
    /*     */
    /*     */
    /*  26 */ private static Locale defaultLocale = Locale.getDefault();

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static void setDefaultLocale(Locale locale)
    /*     */ {
        /*  33 */ defaultLocale = locale;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static Locale getDefaultLocale()
    /*     */ {
        /*  41 */ return defaultLocale;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(ErrorCodeEnum key)
    /*     */ {
        /*  51 */ return key != null ? getMessage(key.toString(), getDefaultLocale(), new Object[0]) : "";
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(ErrorCodeEnum key, List<?> params)
    /*     */ {
        /*  62 */ if (params != null) {
            /*  63 */ return getMessage(key.toString(), getDefaultLocale(), params.toArray());
            /*     */ }
        /*  65 */ return getMessage(key.toString(), getDefaultLocale());
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(ErrorCodeEnum key, Locale locale, List<?> params)
    /*     */ {
        /*  78 */ if (params != null) {
            /*  79 */ return getMessage(key.toString(), locale, params.toArray());
            /*     */ }
        /*  81 */ return getMessage(key.toString(), locale);
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(String key, Locale locale, List<?> params)
    /*     */ {
        /*  93 */ return getMessage(key, getDefaultLocale(), params.toArray());
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(String key, List<?> params)
    /*     */ {
        /* 104 */ return getMessage(key, getDefaultLocale(), params.toArray());
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getWebMessage(String key, List<?> params)
    /*     */ {
        /* 114 */ StringBuffer message = new StringBuffer();
        /* 115 */ message.append(getMessage("common.code")).append(":").append(key).append("\r\n")
                .append(getMessage("common.info")).append(":").append(getMessage(key, getDefaultLocale(), params.toArray()));
        /* 116 */ return message.toString();
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getWebMessage(String key, List<?> params, Locale locale)
    /*     */ {
        /* 126 */ StringBuffer message = new StringBuffer();
        /* 127 */ message.append(getMessage("common.code", locale)).append(":").append(key).append("\r\n")
                .append(getMessage("common.info", locale)).append(getMessage(key, locale, params.toArray()));
        /* 128 */ return message.toString();
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(String key, Locale locale)
    /*     */ {
        /* 139 */ return getMessage(key, locale, new Object[0]);
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(String key)
    /*     */ {
        /* 149 */ return getMessage(key, getDefaultLocale(), new Object[0]);
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(String key, Object[] objs)
    /*     */ {
        /* 160 */ return getMessage(key, getDefaultLocale(), objs);
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static String getMessage(String key, Locale locale, Object[] objs)
    /*     */ {
        /* 172 */ String message = "";
        /*     */
        /* 174 */ String[] emptyArray = new String[0];
        /*     */
        /* 176 */ if (key != null) {
            /*     */ try {
                /* 178 */ locale = null == locale ? getDefaultLocale() : locale;
                /* 179 */ if (null == objs) {
                    /* 180 */ message = resource.getMessage(key, emptyArray, locale);
                    /*     */ } else {
                    /* 182 */ message = resource.getMessage(key, objs, locale);
                    /*     */ }
                /*     */ }
            /*     */ catch (NullPointerException e) {
                /* 186 */ log.error("getMessage", "can't find in specified locale resource bundle,key is:" + key);
                /*     */
                /* 188 */ message = key;
                /*     */ } catch (MissingResourceException e) {
                /* 190 */ log.error("getMessage", "can't find in specified locale resource bundle,key is:" + key);
                /*     */
                /* 192 */ message = key;
                /*     */ }
            /*     */ }
        /* 195 */ return message;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public ReloadableResourceBundleMessageSource getResource()
    /*     */ {
        /* 206 */ return resource;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public void setResource(ReloadableResourceBundleMessageSource resouce)
    /*     */ {
        /* 213 */ resource = resouce;
        /*     */ }
    /*     */ }
