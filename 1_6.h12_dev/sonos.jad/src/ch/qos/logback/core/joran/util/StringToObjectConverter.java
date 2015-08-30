// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.util;

import ch.qos.logback.core.spi.ContextAware;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class StringToObjectConverter
{

    public StringToObjectConverter()
    {
    }

    public static boolean canBeBuiltFromSimpleString(Class class1)
    {
        boolean flag;
        Package package1;
        flag = true;
        package1 = class1.getPackage();
        break MISSING_BLOCK_LABEL_7;
        if(!class1.isPrimitive() && (package1 == null || !"java.lang".equals(package1.getName())) && !followsTheValueOfConvention(class1) && !class1.isEnum() && !isOfTypeCharset(class1))
            flag = false;
        return flag;
    }

    public static Object convertArg(ContextAware contextaware, String s, Class class1)
    {
        Object obj = null;
        if(s != null) goto _L2; else goto _L1
_L1:
        return obj;
_L2:
        String s1 = s.trim();
        if(java/lang/String.isAssignableFrom(class1))
            obj = s1;
        else
        if(Integer.TYPE.isAssignableFrom(class1))
            obj = new Integer(s1);
        else
        if(Long.TYPE.isAssignableFrom(class1))
            obj = new Long(s1);
        else
        if(Float.TYPE.isAssignableFrom(class1))
            obj = new Float(s1);
        else
        if(Double.TYPE.isAssignableFrom(class1))
            obj = new Double(s1);
        else
        if(Boolean.TYPE.isAssignableFrom(class1))
        {
            if("true".equalsIgnoreCase(s1))
                obj = Boolean.TRUE;
            else
            if("false".equalsIgnoreCase(s1))
                obj = Boolean.FALSE;
        } else
        if(class1.isEnum())
            obj = convertToEnum(contextaware, s1, class1);
        else
        if(followsTheValueOfConvention(class1))
            obj = convertByValueOfMethod(contextaware, class1, s1);
        else
        if(isOfTypeCharset(class1))
            obj = convertToCharset(contextaware, s);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static Object convertByValueOfMethod(ContextAware contextaware, Class class1, String s)
    {
        Object obj = null;
        Object obj1;
        Method method = class1.getMethod("valueOf", STING_CLASS_PARAMETER);
        Object aobj[] = new Object[1];
        aobj[0] = s;
        obj1 = method.invoke(null, aobj);
        obj = obj1;
_L2:
        return obj;
        Exception exception;
        exception;
        contextaware.addError((new StringBuilder()).append("Failed to invoke valueOf{} method in class [").append(class1.getName()).append("] with value [").append(s).append("]").toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static Charset convertToCharset(ContextAware contextaware, String s)
    {
        Charset charset1 = Charset.forName(s);
        Charset charset = charset1;
_L2:
        return charset;
        UnsupportedCharsetException unsupportedcharsetexception;
        unsupportedcharsetexception;
        contextaware.addError((new StringBuilder()).append("Failed to get charset [").append(s).append("]").toString(), unsupportedcharsetexception);
        charset = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static Object convertToEnum(ContextAware contextaware, String s, Class class1)
    {
        return Enum.valueOf(class1, s);
    }

    private static boolean followsTheValueOfConvention(Class class1)
    {
        boolean flag1 = Modifier.isStatic(class1.getMethod("valueOf", STING_CLASS_PARAMETER).getModifiers());
        if(!flag1) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
_L2:
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        SecurityException securityexception;
        securityexception;
          goto _L2
    }

    private static boolean isOfTypeCharset(Class class1)
    {
        return java/nio/charset/Charset.isAssignableFrom(class1);
    }

    boolean isBuildableFromSimpleString()
    {
        return false;
    }

    private static final Class STING_CLASS_PARAMETER[];

    static 
    {
        Class aclass[] = new Class[1];
        aclass[0] = java/lang/String;
        STING_CLASS_PARAMETER = aclass;
    }
}
