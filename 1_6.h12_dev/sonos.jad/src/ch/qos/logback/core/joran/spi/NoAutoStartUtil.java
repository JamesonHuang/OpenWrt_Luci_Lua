// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;


// Referenced classes of package ch.qos.logback.core.joran.spi:
//            NoAutoStart

public class NoAutoStartUtil
{

    public NoAutoStartUtil()
    {
    }

    public static boolean notMarkedWithNoAutoStart(Object obj)
    {
        boolean flag = false;
        if(obj != null)
        {
            boolean flag1;
            if((NoAutoStart)obj.getClass().getAnnotation(ch/qos/logback/core/joran/spi/NoAutoStart) == null)
                flag1 = true;
            else
                flag1 = false;
            flag = flag1;
        }
        return flag;
    }
}
