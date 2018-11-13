/*     */ package com.iisi.opd.exception.util;

/*     */
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;

/*     */
/*     */ public final class Constant
/*     */ {
    /*     */ private static String SYSTEMID;
    /*     */ public static final String SDPSYSTEMID = "BASE";
    /*     */ public static final String JUTSYSTEMID = "JUT";

    /*     */
    /*     */ @Deprecated
    /*     */ public static String getSYSTEMID()
    /*     */ {
        /*  17 */ return SYSTEMID;
        /*     */ }

    /*     */
    /*     */ @Deprecated
    /*     */ public static synchronized void setSYSTEMID(String sYSTEMID)
    /*     */ {
        /*  23 */ SYSTEMID = sYSTEMID;
        /*     */ }

    /*     */
    /*     */ public static final class SysParamName
    /*     */ {
        /*     */ private String sysParam;
        /*  29 */ public static final SysParamName LOGQUEUENAME = new SysParamName("LOGQUEUENAME");
        /*     */
        /*  31 */ public static final SysParamName SAVE_BATCH_LOG_SIZE = new SysParamName("SAVE_BATCH_LOG_SIZE");
        /*     */
        /*  33 */ public static final SysParamName ERRORLOGQUEUENAME = new SysParamName("ERRORLOGQUEUENAME");
        /*     */
        /*  35 */ public static final SysParamName ISLOG = new SysParamName("ISLOG");
        /*     */
        /*  37 */ public static final SysParamName SUMMARY_FILE_PATH = new SysParamName("SUMMARYFILEPATH");
        /*     */
        /*  39 */ public static final SysParamName SYSLOG_FTP_IP = new SysParamName("SYSLOGFTPIP");
        /*     */
        /*  41 */ public static final SysParamName SYSLOG_FTP_PORT = new SysParamName("SYSLOGFTPPORT");
        /*     */
        /*  43 */ public static final SysParamName SYSLOG_FTP_ID = new SysParamName("SYSLOGFTPID");
        /*     */
        /*  45 */ public static final SysParamName SYSLOG_UPLOAD_FOLDER = new SysParamName("SYSLOGUPLOADFOLDER");
        /*     */
        /*  47 */ public static final SysParamName SYSLOG_FTP_PW = new SysParamName("SYSLOGFTPPW");
        /*     */
        /*  49 */ public static final SysParamName SYSLOG_PORT = new SysParamName("SYSLOGPORT");
        /*     */
        /*  51 */ public static final SysParamName SYSLOG_IP = new SysParamName("SYSLOGIP");
        /*     */
        /*  53 */ private static final SysParamName[] lanValues = { LOGQUEUENAME, ISLOG, ERRORLOGQUEUENAME, SUMMARY_FILE_PATH,
                SYSLOG_FTP_IP, SYSLOG_FTP_PORT, SYSLOG_FTP_ID, SYSLOG_UPLOAD_FOLDER, SYSLOG_FTP_PW, SYSLOG_PORT, SYSLOG_IP,
                SAVE_BATCH_LOG_SIZE };
        /*     */
        /*  55 */ public static final List<SysParamName> VALUES = Collections.unmodifiableList(Arrays.asList(lanValues));

        /*     */
        /*     */ private SysParamName(String sysParam)
        /*     */ {
            /*  59 */ this.sysParam = sysParam;
            /*     */ }

        /*     */
        /*     */ @Override
        public String toString()
        /*     */ {
            /*  64 */ return this.sysParam;
            /*     */ }

        /*     */
        /*     */ public static SysParamName valueOf(String sysParamNameValue)
        /*     */ {
            /*  69 */ Iterator<?> iter = VALUES.iterator();
            /*  70 */ while (iter.hasNext()) {
                /*  71 */ SysParamName sysParamName = (SysParamName) iter.next();
                /*  72 */ if (sysParamNameValue.equals(String.valueOf(sysParamName))) {
                    /*  73 */ return sysParamName;
                    /*     */ }
                /*     */ }
            /*     */
            /*  77 */ throw new IllegalArgumentException(
                    "Cannot parse into an element of SysParamName:'" + sysParamNameValue + "'");
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static final class SysParamGroup
    /*     */ {
        /*     */ private String paramGroup;
        /*  84 */ public static final SysParamGroup LOG = new SysParamGroup("LOG");
        /*     */
        /*  86 */ public static final SysParamGroup SYSLOG = new SysParamGroup("SYSLOG");
        /*     */
        /*  88 */ private static final SysParamGroup[] lanValues = { LOG, SYSLOG };
        /*     */
        /*  90 */ public static final List<SysParamGroup> VALUES = Collections.unmodifiableList(Arrays.asList(lanValues));

        /*     */
        /*     */ private SysParamGroup(String paramGroup)
        /*     */ {
            /*  94 */ this.paramGroup = paramGroup;
            /*     */ }

        /*     */
        /*     */ @Override
        public String toString()
        /*     */ {
            /*  99 */ return this.paramGroup;
            /*     */ }

        /*     */
        /*     */ public static SysParamGroup valueOf(String sysParamGroupValue)
        /*     */ {
            /* 104 */ Iterator<?> iter = VALUES.iterator();
            /* 105 */ while (iter.hasNext()) {
                /* 106 */ SysParamGroup sysParamGroup = (SysParamGroup) iter.next();
                /* 107 */ if (sysParamGroupValue.equals(String.valueOf(sysParamGroup))) {
                    /* 108 */ return sysParamGroup;
                    /*     */ }
                /*     */ }
            /*     */
            /* 112 */ throw new IllegalArgumentException(
                    "Cannot parse into an element of sysParamGroupValue:'" + sysParamGroupValue + "'");
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static final class ITSMLogColumns
    /*     */ {
        /*     */ private String column;
        /* 119 */ public static final ITSMLogColumns AGENT = new ITSMLogColumns("Agent");
        /*     */
        /* 121 */ public static final ITSMLogColumns FIRSTOCCURRENCE = new ITSMLogColumns("FirstOccurrence");
        /*     */
        /* 123 */ public static final ITSMLogColumns NODE = new ITSMLogColumns("Node");
        /*     */
        /* 125 */ public static final ITSMLogColumns NODEIP = new ITSMLogColumns("NodeIP");
        /*     */
        /* 127 */ public static final ITSMLogColumns SEVERITY = new ITSMLogColumns("Severity");
        /*     */
        /* 129 */ public static final ITSMLogColumns ALERTGROUP = new ITSMLogColumns("AlertGroup");
        /*     */
        /* 131 */ public static final ITSMLogColumns RESOURCENAME = new ITSMLogColumns("ResourceName");
        /*     */
        /* 133 */ public static final ITSMLogColumns SUBRESOURCENAME = new ITSMLogColumns("SubResourceName");
        /*     */
        /* 135 */ public static final ITSMLogColumns SUMMARY = new ITSMLogColumns("Summary");
        /*     */
        /* 137 */ public static final ITSMLogColumns SMSTEXT = new ITSMLogColumns("SMSText");
        /*     */
        /* 139 */ public static final ITSMLogColumns EMAILTEXT = new ITSMLogColumns("EmailText");
        /*     */
        /* 141 */ public static final ITSMLogColumns EXT = new ITSMLogColumns("EXT");
        /*     */
        /* 143 */ private static final ITSMLogColumns[] lanValues = { AGENT, FIRSTOCCURRENCE, NODE, NODEIP, SEVERITY, ALERTGROUP,
                RESOURCENAME, SUBRESOURCENAME, SUMMARY, SMSTEXT, EMAILTEXT, EXT };
        /*     */
        /* 145 */ public static final List<ITSMLogColumns> VALUES = Collections.unmodifiableList(Arrays.asList(lanValues));

        /*     */
        /*     */ private ITSMLogColumns(String column)
        /*     */ {
            /* 149 */ this.column = column;
            /*     */ }

        /*     */
        /*     */ @Override
        public String toString()
        /*     */ {
            /* 154 */ return this.column;
            /*     */ }

        /*     */
        /*     */ public static ITSMLogColumns valueOf(String column)
        /*     */ {
            /* 159 */ Iterator<?> iter = VALUES.iterator();
            /* 160 */ while (iter.hasNext()) {
                /* 161 */ ITSMLogColumns itsmColumn = (ITSMLogColumns) iter.next();
                /* 162 */ if (column.equals(String.valueOf(itsmColumn))) {
                    /* 163 */ return itsmColumn;
                    /*     */ }
                /*     */ }
            /*     */
            /* 167 */ throw new IllegalArgumentException("Cannot parse into an element of Spring:'" + column + "'");
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static final class Spring
    /*     */ {
        /*     */ private String beanName;
        /* 174 */ public static final Spring DAO = new Spring("openJPADaoImpl");
        /*     */
        /* 176 */ public static final Spring CACHEDAO = new Spring("MsgCacheManager");
        /*     */
        /* 178 */ public static final Spring JMSCLIENTMANAGER = new Spring("jmsClientManager");
        /*     */
        /* 180 */ public static final Spring FILE_SYSTEM_PARAM = new Spring("systemParameters");
        /*     */
        /* 182 */ public static final Spring DB_SYSTEM_PARAM = new Spring("dbSystemParameters");
        /*     */
        /* 184 */ public static final Spring LOGGER2DBAPPENDERMANAGER = new Spring("Logger2DBAppenderManager");
        /*     */
        /* 186 */ private static final Spring[] lanValues = { DAO, CACHEDAO, JMSCLIENTMANAGER, LOGGER2DBAPPENDERMANAGER,
                FILE_SYSTEM_PARAM };
        /*     */
        /* 188 */ public static final List<Spring> VALUES = Collections.unmodifiableList(Arrays.asList(lanValues));

        /*     */
        /*     */ private Spring(String beanName)
        /*     */ {
            /* 192 */ this.beanName = beanName;
            /*     */ }

        /*     */
        /*     */ @Override
        public String toString()
        /*     */ {
            /* 197 */ return this.beanName;
            /*     */ }

        /*     */
        /*     */ public static Spring valueOf(String beanName)
        /*     */ {
            /* 202 */ Iterator<?> iter = VALUES.iterator();
            /* 203 */ while (iter.hasNext()) {
                /* 204 */ Spring spring = (Spring) iter.next();
                /* 205 */ if (beanName.equals(String.valueOf(spring))) {
                    /* 206 */ return spring;
                    /*     */ }
                /*     */ }
            /*     */
            /* 210 */ throw new IllegalArgumentException("Cannot parse into an element of Spring:'" + beanName + "'");
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static final class OracleErrorCode
    /*     */ {
        /*     */ private int errorCode;
        /* 217 */ public static final OracleErrorCode DUPLICATE = new OracleErrorCode(1);
        /*     */
        /* 219 */ public static final OracleErrorCode VALUETOOLARGE = new OracleErrorCode(12899);

        /*     */
        /*     */ public OracleErrorCode(int sqlState)
        /*     */ {
            /* 223 */ this.errorCode = sqlState;
            /*     */ }

        /*     */
        /*     */ public int getErrorCode()
        /*     */ {
            /* 228 */ return this.errorCode;
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static final class DataLogType
    /*     */ {
        /*     */ private String datalogType;
        /* 235 */ public static final DataLogType FILENAME = new DataLogType("filename");
        /*     */
        /* 237 */ public static final DataLogType FILESIZE = new DataLogType("filesize");

        /*     */
        /*     */ public DataLogType(String datalogType)
        /*     */ {
            /* 241 */ this.datalogType = datalogType;
            /*     */ }

        /*     */
        /*     */ @Override
        public String toString()
        /*     */ {
            /* 246 */ return this.datalogType;
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static final class DaoProperties
    /*     */ {
        /*     */ private String isLog;
        /* 253 */ public static final DaoProperties ISLOG = new DaoProperties("islog");

        /*     */
        /*     */ public DaoProperties(String isLog)
        /*     */ {
            /* 257 */ this.isLog = isLog;
            /*     */ }

        /*     */
        /*     */ @Override
        public String toString()
        /*     */ {
            /* 262 */ return this.isLog;
            /*     */ }
        /*     */ }
    /*     */ }
