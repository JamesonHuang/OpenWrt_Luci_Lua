// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;


// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public abstract class ThrowableHandlingConverter extends ClassicConverter
{

    public ThrowableHandlingConverter()
    {
    }

    boolean handlesThrowable()
    {
        return true;
    }
}
