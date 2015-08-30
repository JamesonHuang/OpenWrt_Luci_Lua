// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.core.CoreConstants;

// Referenced classes of package ch.qos.logback.classic.spi:
//            ThrowableProxy, StackTraceElementProxy, IThrowableProxy, ClassPackagingData

public class ThrowableProxyUtil
{

    public ThrowableProxyUtil()
    {
    }

    public static String asString(IThrowableProxy ithrowableproxy)
    {
        StringBuilder stringbuilder = new StringBuilder(2048);
        recursiveAppend(stringbuilder, null, 1, ithrowableproxy);
        return stringbuilder.toString();
    }

    public static void build(ThrowableProxy throwableproxy, Throwable throwable, ThrowableProxy throwableproxy1)
    {
        StackTraceElement astacktraceelement[] = throwable.getStackTrace();
        int i = -1;
        if(throwableproxy1 != null)
            i = findNumberOfCommonFrames(astacktraceelement, throwableproxy1.getStackTraceElementProxyArray());
        throwableproxy.commonFrames = i;
        throwableproxy.stackTraceElementProxyArray = steArrayToStepArray(astacktraceelement);
    }

    static int findNumberOfCommonFrames(StackTraceElement astacktraceelement[], StackTraceElementProxy astacktraceelementproxy[])
    {
        int i = 0;
        if(astacktraceelementproxy != null && astacktraceelement != null)
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

    public static void indent(StringBuilder stringbuilder, int i)
    {
        for(int j = 0; j < i; j++)
            stringbuilder.append('\t');

    }

    private static void recursiveAppend(StringBuilder stringbuilder, String s, int i, IThrowableProxy ithrowableproxy)
    {
        if(ithrowableproxy != null)
        {
            subjoinFirstLine(stringbuilder, s, i, ithrowableproxy);
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
            subjoinSTEPArray(stringbuilder, i, ithrowableproxy);
            IThrowableProxy aithrowableproxy[] = ithrowableproxy.getSuppressed();
            if(aithrowableproxy != null)
            {
                int j = aithrowableproxy.length;
                for(int k = 0; k < j; k++)
                {
                    IThrowableProxy ithrowableproxy1 = aithrowableproxy[k];
                    recursiveAppend(stringbuilder, "Suppressed: ", i + 1, ithrowableproxy1);
                }

            }
            recursiveAppend(stringbuilder, "Caused by: ", i, ithrowableproxy.getCause());
        }
    }

    static StackTraceElementProxy[] steArrayToStepArray(StackTraceElement astacktraceelement[])
    {
        int i = 0;
        StackTraceElementProxy astacktraceelementproxy1[];
        if(astacktraceelement == null)
        {
            astacktraceelementproxy1 = new StackTraceElementProxy[0];
        } else
        {
            StackTraceElementProxy astacktraceelementproxy[];
            for(astacktraceelementproxy = new StackTraceElementProxy[astacktraceelement.length]; i < astacktraceelementproxy.length; i++)
                astacktraceelementproxy[i] = new StackTraceElementProxy(astacktraceelement[i]);

            astacktraceelementproxy1 = astacktraceelementproxy;
        }
        return astacktraceelementproxy1;
    }

    private static void subjoinExceptionMessage(StringBuilder stringbuilder, IThrowableProxy ithrowableproxy)
    {
        stringbuilder.append(ithrowableproxy.getClassName()).append(": ").append(ithrowableproxy.getMessage());
    }

    public static void subjoinFirstLine(StringBuilder stringbuilder, IThrowableProxy ithrowableproxy)
    {
        if(ithrowableproxy.getCommonFrames() > 0)
            stringbuilder.append("Caused by: ");
        subjoinExceptionMessage(stringbuilder, ithrowableproxy);
    }

    private static void subjoinFirstLine(StringBuilder stringbuilder, String s, int i, IThrowableProxy ithrowableproxy)
    {
        indent(stringbuilder, i - 1);
        if(s != null)
            stringbuilder.append(s);
        subjoinExceptionMessage(stringbuilder, ithrowableproxy);
    }

    public static void subjoinFirstLineRootCauseFirst(StringBuilder stringbuilder, IThrowableProxy ithrowableproxy)
    {
        if(ithrowableproxy.getCause() != null)
            stringbuilder.append("Wrapped by: ");
        subjoinExceptionMessage(stringbuilder, ithrowableproxy);
    }

    public static void subjoinPackagingData(StringBuilder stringbuilder, StackTraceElementProxy stacktraceelementproxy)
    {
        if(stacktraceelementproxy != null)
        {
            ClassPackagingData classpackagingdata = stacktraceelementproxy.getClassPackagingData();
            if(classpackagingdata != null)
            {
                if(!classpackagingdata.isExact())
                    stringbuilder.append(" ~[");
                else
                    stringbuilder.append(" [");
                stringbuilder.append(classpackagingdata.getCodeLocation()).append(':').append(classpackagingdata.getVersion()).append(']');
            }
        }
    }

    public static void subjoinSTEP(StringBuilder stringbuilder, StackTraceElementProxy stacktraceelementproxy)
    {
        stringbuilder.append(stacktraceelementproxy.toString());
        subjoinPackagingData(stringbuilder, stacktraceelementproxy);
    }

    public static void subjoinSTEPArray(StringBuilder stringbuilder, int i, IThrowableProxy ithrowableproxy)
    {
        StackTraceElementProxy astacktraceelementproxy[] = ithrowableproxy.getStackTraceElementProxyArray();
        int j = ithrowableproxy.getCommonFrames();
        for(int k = 0; k < astacktraceelementproxy.length - j; k++)
        {
            StackTraceElementProxy stacktraceelementproxy = astacktraceelementproxy[k];
            indent(stringbuilder, i);
            subjoinSTEP(stringbuilder, stacktraceelementproxy);
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        }

        if(j > 0)
        {
            indent(stringbuilder, i);
            stringbuilder.append("... ").append(j).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
        }
    }

    public static void subjoinSTEPArray(StringBuilder stringbuilder, IThrowableProxy ithrowableproxy)
    {
        subjoinSTEPArray(stringbuilder, 1, ithrowableproxy);
    }

    private static final int BUILDER_CAPACITY = 2048;
    public static final int REGULAR_EXCEPTION_INDENT = 1;
    public static final int SUPPRESSED_EXCEPTION_INDENT = 1;
}
