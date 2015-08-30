// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.status;


// Referenced classes of package ch.qos.logback.core.status:
//            StatusBase

public class WarnStatus extends StatusBase
{

    public WarnStatus(String s, Object obj)
    {
        super(1, s, obj);
    }

    public WarnStatus(String s, Object obj, Throwable throwable)
    {
        super(1, s, obj, throwable);
    }
}
