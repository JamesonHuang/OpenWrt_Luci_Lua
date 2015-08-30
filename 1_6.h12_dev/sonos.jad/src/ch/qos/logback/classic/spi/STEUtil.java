// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;


// Referenced classes of package ch.qos.logback.classic.spi:
//            StackTraceElementProxy

public class STEUtil
{

    public STEUtil()
    {
    }

    static int findNumberOfCommonFrames(StackTraceElement astacktraceelement[], StackTraceElementProxy astacktraceelementproxy[])
    {
        int i = 0;
        if(astacktraceelementproxy != null)
        {
            int j = -1 + astacktraceelement.length;
            int k = -1 + astacktraceelementproxy.length;
            while(j >= 0 && k >= 0 && astacktraceelement[j].equals(astacktraceelementproxy[k].ste)) 
            {
                i++;
                j--;
                k--;
            }
        }
        return i;
    }
}
