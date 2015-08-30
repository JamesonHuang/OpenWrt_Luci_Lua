// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.core.CoreConstants;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package ch.qos.logback.classic.spi:
//            IThrowableProxy, ThrowableProxyUtil, PackagingDataCalculator, StackTraceElementProxy

public class ThrowableProxy
    implements IThrowableProxy
{

    public ThrowableProxy(Throwable throwable1)
    {
        int i;
        i = 0;
        super();
        suppressed = NO_SUPPRESSED;
        calculatedPackageData = false;
        throwable = throwable1;
        className = throwable1.getClass().getName();
        message = throwable1.getMessage();
        stackTraceElementProxyArray = ThrowableProxyUtil.steArrayToStepArray(throwable1.getStackTrace());
        Throwable throwable2 = throwable1.getCause();
        if(throwable2 != null)
        {
            cause = new ThrowableProxy(throwable2);
            cause.commonFrames = ThrowableProxyUtil.findNumberOfCommonFrames(throwable2.getStackTrace(), stackTraceElementProxyArray);
        }
        if(GET_SUPPRESSED_METHOD == null)
            break MISSING_BLOCK_LABEL_200;
        Throwable athrowable[];
        Object obj = GET_SUPPRESSED_METHOD.invoke(throwable1, new Object[0]);
        if(!(obj instanceof Throwable[]))
            break MISSING_BLOCK_LABEL_200;
        athrowable = (Throwable[])(Throwable[])obj;
        if(athrowable.length <= 0)
            break MISSING_BLOCK_LABEL_200;
        suppressed = new ThrowableProxy[athrowable.length];
_L1:
        if(i >= athrowable.length)
            break MISSING_BLOCK_LABEL_200;
        suppressed[i] = new ThrowableProxy(athrowable[i]);
        suppressed[i].commonFrames = ThrowableProxyUtil.findNumberOfCommonFrames(athrowable[i].getStackTrace(), stackTraceElementProxyArray);
        i++;
          goto _L1
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
_L3:
        return;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        if(true) goto _L3; else goto _L2
_L2:
    }

    public void calculatePackagingData()
    {
        if(!calculatedPackageData) goto _L2; else goto _L1
_L1:
        return;
_L2:
        PackagingDataCalculator packagingdatacalculator = getPackagingDataCalculator();
        if(packagingdatacalculator != null)
        {
            calculatedPackageData = true;
            packagingdatacalculator.calculate(this);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void fullDump()
    {
        StringBuilder stringbuilder = new StringBuilder();
        StackTraceElementProxy astacktraceelementproxy[] = stackTraceElementProxyArray;
        int i = astacktraceelementproxy.length;
        for(int j = 0; j < i; j++)
        {
            StackTraceElementProxy stacktraceelementproxy = astacktraceelementproxy[j];
            String s = stacktraceelementproxy.toString();
            stringbuilder.append('\t').append(s);
            ThrowableProxyUtil.subjoinPackagingData(stringbuilder, stacktraceelementproxy);
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        }

        System.out.println(stringbuilder.toString());
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
        return commonFrames;
    }

    public String getMessage()
    {
        return message;
    }

    public PackagingDataCalculator getPackagingDataCalculator()
    {
        if(throwable != null && packagingDataCalculator == null)
            packagingDataCalculator = new PackagingDataCalculator();
        return packagingDataCalculator;
    }

    public StackTraceElementProxy[] getStackTraceElementProxyArray()
    {
        return stackTraceElementProxyArray;
    }

    public IThrowableProxy[] getSuppressed()
    {
        return suppressed;
    }

    public Throwable getThrowable()
    {
        return throwable;
    }

    private static final Method GET_SUPPRESSED_METHOD;
    private static final ThrowableProxy NO_SUPPRESSED[];
    private boolean calculatedPackageData;
    private ThrowableProxy cause;
    private String className;
    int commonFrames;
    private String message;
    private transient PackagingDataCalculator packagingDataCalculator;
    StackTraceElementProxy stackTraceElementProxyArray[];
    private ThrowableProxy suppressed[];
    private Throwable throwable;

    static 
    {
        Method method = null;
        Method method1 = java/lang/Throwable.getMethod("getSuppressed", new Class[0]);
        method = method1;
_L2:
        GET_SUPPRESSED_METHOD = method;
        NO_SUPPRESSED = new ThrowableProxy[0];
        return;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
