// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.color;


// Referenced classes of package ch.qos.logback.core.pattern.color:
//            ForegroundCompositeConverterBase

public class CyanCompositeConverter extends ForegroundCompositeConverterBase
{

    public CyanCompositeConverter()
    {
    }

    protected String getForegroundColorCode(Object obj)
    {
        return "36";
    }
}
