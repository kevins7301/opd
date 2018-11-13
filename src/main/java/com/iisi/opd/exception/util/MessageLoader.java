/*    */ package com.iisi.opd.exception.util;

/*    */
/*    */ import java.io.IOException;
/*    */ import java.util.Properties;
/*    */ import java.util.StringTokenizer;

/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.core.io.Resource;
/*    */ import org.springframework.util.StringUtils;

/*    */
/*    */ public class MessageLoader
/*    */ {
    /*    */ protected Properties commonProp;
    /*    */ private final Logger logger;
    /*    */ private Resource[] msglocations;

    /*    */
    /*    */ public MessageLoader()
    /*    */ {
        /* 19 */ this.commonProp = new Properties();
        /* 20 */ this.logger = LoggerFactory.getLogger(MessageLoader.class);
        /*    */ }

    /*    */
    /*    */ public void init() {
        /* 24 */ new MessageLoader();
        /* 25 */ reload();
        /*    */ }

    /*    */
    /*    */ public void reload()
    /*    */ {
        /*    */ try {
            /* 31 */ Resource[] cRes = getMsglocations();
            /* 32 */ this.commonProp.clear();
            /* 33 */ for (Resource resource : cRes) {
                /* 34 */ this.commonProp.load(resource.getInputStream());
                /*    */ }
            /*    */ }
        /*    */ catch (IOException e)
        /*    */ {
            /* 39 */ this.logger.error("MessageLoader.reload error", e);
            /*    */ }
        /*    */ }

    /*    */
    /*    */ public String getString(String compositeKey) {
        /* 44 */ if (!StringUtils.hasText(compositeKey)) {
            /* 45 */ return "";
            /*    */ }
        /* 47 */ StringTokenizer tokenizer = new StringTokenizer(compositeKey, "+");
        /* 48 */ StringBuilder result = new StringBuilder();
        /* 49 */ while (tokenizer.hasMoreTokens()) {
            /* 50 */ String key = tokenizer.nextToken().trim();
            /*    */ String value;
            if (this.commonProp.containsKey(key)) {
                /* 53 */ value = this.commonProp.getProperty(key);
                /*    */ }
            /*    */ else {
                /* 56 */ value = "(MSG KEY \"" + key + "\" UNDEFINED)";
                /*    */ }
            /*    */
            /* 59 */ result.append(value);
            /*    */ }
        /* 61 */ return result.toString();
        /*    */ }

    /*    */
    /*    */ public Resource[] getMsglocations() {
        /* 65 */ return this.msglocations;
        /*    */ }

    /*    */
    /*    */ public void setMsglocations(Resource[] msglocations) {
        /* 69 */ this.msglocations = msglocations;
        /*    */ }
    /*    */ }
