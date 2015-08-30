// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.color;


// Referenced classes of package ch.qos.logback.core.pattern.color:
//            ForegroundCompositeConverterBase

public class BoldGreenCompositeConverter extends ForegroundCompositeConverterBase
{

    public BoldGreenCompositeConverter()
    {
    }

    protected String getForegroundColorCode(Object obj)
    {
        return "1;32";
    }
}
