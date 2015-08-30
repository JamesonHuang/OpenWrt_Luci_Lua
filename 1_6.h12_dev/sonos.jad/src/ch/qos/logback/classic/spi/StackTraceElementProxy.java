// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import java.io.Serializable;

// Referenced classes of package ch.qos.logback.classic.spi:
//            ClassPackagingData

public class StackTraceElementProxy
    implements Serializable
{

    public StackTraceElementProxy(StackTraceElement stacktraceelement)
    {
        if(stacktraceelement == null)
        {
            throw new IllegalArgumentException("ste cannot be null");
        } else
        {
            ste = stacktraceelement;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
            flag = false;
        else
        if(getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            StackTraceElementProxy stacktraceelementproxy = (StackTraceElementProxy)obj;
            if(!ste.equals(stacktraceelementproxy.ste))
                flag = false;
            else
            if(cpd == null)
            {
                if(stacktraceelementproxy.cpd != null)
                    flag = false;
            } else
            if(!cpd.equals(stacktraceelementproxy.cpd))
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public ClassPackagingData getClassPackagingData()
    {
        return cpd;
    }

    public String getSTEAsString()
    {
        if(steAsString == null)
            steAsString = (new StringBuilder()).append("at ").append(ste.toString()).toString();
        return steAsString;
    }

    public StackTraceElement getStackTraceElement()
    {
        return ste;
    }

    public int hashCode()
    {
        return ste.hashCode();
    }

    public void setClassPackagingData(ClassPackagingData classpackagingdata)
    {
        if(cpd != null)
        {
            throw new IllegalStateException("Packaging data has been already set");
        } else
        {
            cpd = classpackagingdata;
            return;
        }
    }

    public String toString()
    {
        return getSTEAsString();
    }

    private static final long serialVersionUID = 0xdf0c871511134332L;
    private ClassPackagingData cpd;
    final StackTraceElement ste;
    private transient String steAsString;
}
