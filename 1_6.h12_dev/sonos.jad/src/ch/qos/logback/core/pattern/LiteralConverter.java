// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;


// Referenced classes of package ch.qos.logback.core.pattern:
//            Converter

public final class LiteralConverter extends Converter
{

    public LiteralConverter(String s)
    {
        literal = s;
    }

    public String convert(Object obj)
    {
        return literal;
    }

    String literal;
}
