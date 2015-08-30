// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.util.EnvUtil;

public class CoreConstants
{

    public CoreConstants()
    {
    }

    public static final int BYTES_PER_INT = 4;
    public static final String CAUSED_BY = "Caused by: ";
    public static final String CLF_DATE_PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";
    public static final String CODES_URL = "http://logback.qos.ch/codes.html";
    public static final char COLON_CHAR = 58;
    public static final char COMMA_CHAR = 44;
    public static final String CONFIGURATION_WATCH_LIST = "CONFIGURATION_WATCH_LIST";
    public static final String CONFIGURATION_WATCH_LIST_RESET = "CONFIGURATION_WATCH_LIST_RESET";
    public static final String CONTEXT_NAME_KEY = "CONTEXT_NAME";
    public static final String CONTEXT_SCOPE_VALUE = "context";
    public static final int CORE_POOL_SIZE = 0;
    public static final char CURLY_LEFT = 123;
    public static final char CURLY_RIGHT = 125;
    public static final String DAILY_DATE_PATTERN = "yyyy-MM-dd";
    public static final char DASH_CHAR = 45;
    public static final String DATA_DIR_KEY = "DATA_DIR";
    public static final String DEFAULT_CONTEXT_NAME = "default";
    public static final String DEFAULT_VALUE_SEPARATOR = ":-";
    public static final char DOLLAR = 36;
    public static final char DOT = 46;
    public static final char DOUBLE_QUOTE_CHAR = 34;
    public static final String EMPTY_STRING = "";
    public static final char ESCAPE_CHAR = 92;
    public static final String EVALUATOR_MAP = "EVALUATOR_MAP";
    public static final String EXT_DIR_KEY = "EXT_DIR";
    public static final String HOSTNAME_KEY = "HOSTNAME";
    public static final String ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";
    public static final String ISO8601_STR = "ISO8601";
    public static final String LEFT_ACCOLADE;
    public static final char LEFT_PARENTHESIS_CHAR = 40;
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final int LINE_SEPARATOR_LEN = LINE_SEPARATOR.length();
    public static final int MAX_ERROR_COUNT = 4;
    public static final int MAX_POOL_SIZE = 32;
    public static final int MILLIS_IN_ONE_DAY = 0x5265c00;
    public static final int MILLIS_IN_ONE_HOUR = 0x36ee80;
    public static final int MILLIS_IN_ONE_MINUTE = 60000;
    public static final int MILLIS_IN_ONE_SECOND = 1000;
    public static final int MILLIS_IN_ONE_WEEK = 0x240c8400;
    public static final int OOS_RESET_FREQUENCY = 70;
    public static final String PACKAGE_NAME_KEY = "PACKAGE_NAME";
    public static final String PATTERN_RULE_REGISTRY = "PATTERN_RULE_REGISTRY";
    public static final char PERCENT_CHAR = 37;
    public static final String RESET_MSG_PREFIX = "Will reset and reconfigure context ";
    public static final String RIGHT_ACCOLADE;
    public static final char RIGHT_PARENTHESIS_CHAR = 41;
    public static final String SAFE_JORAN_CONFIGURATION = "SAFE_JORAN_CONFIGURATION";
    public static final int SECONDS_TO_WAIT_FOR_COMPRESSION_JOBS = 30;
    public static final String SEE_FNP_NOT_SET = "See also http://logback.qos.ch/codes.html#tbr_fnp_not_set";
    public static final char SINGLE_QUOTE_CHAR = 39;
    public static final String SUPPRESSED = "Suppressed: ";
    public static final char TAB = 9;
    public static final int TABLE_ROW_LIMIT = 10000;
    public static final String UNDEFINED_PROPERTY_SUFFIX = "_IS_UNDEFINED";
    public static final String VALUE_OF = "valueOf";
    public static final String VERSION_CODE_KEY = "VERSION_CODE";
    public static final String VERSION_NAME_KEY = "VERSION_NAME";
    public static final String WRAPPED_BY = "Wrapped by: ";
    public static final String XML_PARSING = "XML_PARSING";

    static 
    {
        int i;
        char ac[];
        char ac1[];
        if(EnvUtil.isJDK5())
            i = 1;
        else
            i = 0;
        CORE_POOL_SIZE = i;
        ac = new char[1];
        ac[0] = '{';
        LEFT_ACCOLADE = new String(ac);
        ac1 = new char[1];
        ac1[0] = '}';
        RIGHT_ACCOLADE = new String(ac1);
    }
}
