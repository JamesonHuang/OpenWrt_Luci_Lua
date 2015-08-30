// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;


public class EventArgUtil
{

    public EventArgUtil()
    {
    }

    public static Object[] arrangeArguments(Object aobj[])
    {
        return aobj;
    }

    public static final Throwable extractThrowable(Object aobj[])
    {
        Throwable throwable;
        if(aobj == null || aobj.length == 0)
        {
            throwable = null;
        } else
        {
            Object obj = aobj[-1 + aobj.length];
            if(obj instanceof Throwable)
                throwable = (Throwable)obj;
            else
                throwable = null;
        }
        return throwable;
    }

    public static boolean successfulExtraction(Throwable throwable)
    {
        boolean flag;
        if(throwable != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static Object[] trimmedCopy(Object aobj[])
    {
        if(aobj == null || aobj.length == 0)
        {
            throw new IllegalStateException("non-sensical empty or null argument array");
        } else
        {
            int i = -1 + aobj.length;
            Object aobj1[] = new Object[i];
            System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, i);
            return aobj1;
        }
    }
}
