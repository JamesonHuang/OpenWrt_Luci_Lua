// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.pattern.DynamicConverter;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            MonoTypedConverter

public class IntegerTokenConverter extends DynamicConverter
    implements MonoTypedConverter
{

    public IntegerTokenConverter()
    {
    }

    public String convert(int i)
    {
        return Integer.toString(i);
    }

    public String convert(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("Null argument forbidden");
        if(obj instanceof Integer)
            return convert(((Integer)obj).intValue());
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot convert ").append(obj).append(" of type").append(obj.getClass().getName()).toString());
    }

    public boolean isApplicable(Object obj)
    {
        return obj instanceof Integer;
    }

    public static final String CONVERTER_KEY = "i";
}
