// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.color;

import ch.qos.logback.core.pattern.CompositeConverter;

public abstract class ForegroundCompositeConverterBase extends CompositeConverter
{

    public ForegroundCompositeConverterBase()
    {
    }

    protected abstract String getForegroundColorCode(Object obj);

    protected String transform(Object obj, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("\033[");
        stringbuilder.append(getForegroundColorCode(obj));
        stringbuilder.append("m");
        stringbuilder.append(s);
        stringbuilder.append("\033[0;39m");
        return stringbuilder.toString();
    }

    private static final String SET_DEFAULT_COLOR = "\033[0;39m";
}
