// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;


// Referenced classes of package ch.qos.logback.core.status:
//            StatusBase

public class InfoStatus extends StatusBase
{

    public InfoStatus(String s, Object obj)
    {
        super(0, s, obj);
    }

    public InfoStatus(String s, Object obj, Throwable throwable)
    {
        super(0, s, obj, throwable);
    }
}
