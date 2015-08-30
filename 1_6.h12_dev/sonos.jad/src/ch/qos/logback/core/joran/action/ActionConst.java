// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;


public abstract class ActionConst
{

    public ActionConst()
    {
    }

    public static final String ACTION_CLASS_ATTRIBUTE = "actionClass";
    public static final String ADDITIVITY_ATTRIBUTE = "additivity";
    public static final String APPENDER_BAG = "APPENDER_BAG";
    public static final String APPENDER_TAG = "appender";
    public static final String CONVERSION_WORD_ATTRIBUTE = "conversionWord";
    public static final String CONVERTER_CLASS_ATTRIBUTE = "converterClass";
    public static final String FILTER_CHAIN_BAG = "FILTER_CHAIN_BAG";
    public static final String INHERITED = "INHERITED";
    public static final String LEVEL_ATTRIBUTE = "level";
    public static final String NULL = "NULL";
    static final Class ONE_STRING_PARAM[];
    public static final String PATTERN_ATTRIBUTE = "pattern";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTR = "value";

    static 
    {
        Class aclass[] = new Class[1];
        aclass[0] = java/lang/String;
        ONE_STRING_PARAM = aclass;
    }
}
