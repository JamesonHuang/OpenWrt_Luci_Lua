// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import java.io.Serializable;
import java.util.Arrays;

// Referenced classes of package ch.qos.logback.classic.spi:
//            IThrowableProxy, StackTraceElementProxy

public class ThrowableProxyVO
    implements IThrowableProxy, Serializable
{

    public ThrowableProxyVO()
    {
    }

    public static ThrowableProxyVO build(IThrowableProxy ithrowableproxy)
    {
        ThrowableProxyVO throwableproxyvo1;
        if(ithrowableproxy == null)
        {
            throwableproxyvo1 = null;
        } else
        {
            ThrowableProxyVO throwableproxyvo = new ThrowableProxyVO();
            throwableproxyvo.className = ithrowableproxy.getClassName();
            throwableproxyvo.message = ithrowableproxy.getMessage();
            throwableproxyvo.commonFramesCount = ithrowableproxy.getCommonFrames();
            throwableproxyvo.stackTraceElementProxyArray = ithrowableproxy.getStackTraceElementProxyArray();
            IThrowableProxy ithrowableproxy1 = ithrowableproxy.getCause();
            if(ithrowableproxy1 != null)
                throwableproxyvo.cause = build(ithrowableproxy1);
            IThrowableProxy aithrowableproxy[] = ithrowableproxy.getSuppressed();
            if(aithrowableproxy != null)
            {
                throwableproxyvo.suppressed = new IThrowableProxy[aithrowableproxy.length];
                for(int i = 0; i < aithrowableproxy.length; i++)
                    throwableproxyvo.suppressed[i] = build(aithrowableproxy[i]);

            }
            throwableproxyvo1 = throwableproxyvo;
        }
        return throwableproxyvo1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(getClass() != obj.getClass())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        ThrowableProxyVO throwableproxyvo = (ThrowableProxyVO)obj;
        if(className == null)
        {
            if(throwableproxyvo.className != null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(!className.equals(throwableproxyvo.className))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(!Arrays.equals(stackTraceElementProxyArray, throwableproxyvo.stackTraceElementProxyArray))
            flag = false;
        else
        if(!Arrays.equals(suppressed, throwableproxyvo.suppressed))
            flag = false;
        else
        if(cause == null)
        {
            if(throwableproxyvo.cause != null)
                flag = false;
        } else
        if(!cause.equals(throwableproxyvo.cause))
            flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public IThrowableProxy getCause()
    {
        return cause;
    }

    public String getClassName()
    {
        return className;
    }

    public int getCommonFrames()
    {
        return commonFramesCount;
    }

    public String getMessage()
    {
        return message;
    }

    public StackTraceElementProxy[] getStackTraceElementProxyArray()
    {
        return stackTraceElementProxyArray;
    }

    public IThrowableProxy[] getSuppressed()
    {
        return suppressed;
    }

    public int hashCode()
    {
        int i;
        if(className == null)
            i = 0;
        else
            i = className.hashCode();
        return i + 31;
    }

    private static final long serialVersionUID = 0xf54432135b2763ddL;
    private IThrowableProxy cause;
    private String className;
    private int commonFramesCount;
    private String message;
    private StackTraceElementProxy stackTraceElementProxyArray[];
    private IThrowableProxy suppressed[];
}
