// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.HashMap;

// Referenced classes of package ch.qos.logback.classic.spi:
//            StackTraceElementProxy, ClassPackagingData, IThrowableProxy, STEUtil

public class PackagingDataCalculator
{

    public PackagingDataCalculator()
    {
        cache = new HashMap();
    }

    private Class bestEffortLoadClass(ClassLoader classloader, String s)
    {
        Class class1 = loadClass(classloader, s);
        if(class1 == null) goto _L2; else goto _L1
_L1:
        return class1;
_L2:
        ClassLoader classloader1 = Thread.currentThread().getContextClassLoader();
        if(classloader1 != classloader)
            class1 = loadClass(classloader1, s);
        if(class1 != null)
            continue; /* Loop/switch isn't completed */
        Class class2 = Class.forName(s);
        class1 = class2;
        continue; /* Loop/switch isn't completed */
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        class1 = null;
        continue; /* Loop/switch isn't completed */
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        class1 = null;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        class1 = null;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private ClassPackagingData computeBySTEP(StackTraceElementProxy stacktraceelementproxy, ClassLoader classloader)
    {
        String s = stacktraceelementproxy.ste.getClassName();
        ClassPackagingData classpackagingdata = (ClassPackagingData)cache.get(s);
        if(classpackagingdata == null)
        {
            Class class1 = bestEffortLoadClass(classloader, s);
            String s1 = getImplementationVersion(class1);
            classpackagingdata = new ClassPackagingData(getCodeLocation(class1), s1, false);
            cache.put(s, classpackagingdata);
        }
        return classpackagingdata;
    }

    private String getCodeLocation(String s, char c)
    {
        int i = s.lastIndexOf(c);
        String s1;
        if(isFolder(i, s))
            s1 = s.substring(1 + s.lastIndexOf(c, i - 1));
        else
        if(i > 0)
            s1 = s.substring(i + 1);
        else
            s1 = null;
        return s1;
    }

    private boolean isFolder(int i, String s)
    {
        boolean flag;
        if(i != -1 && i + 1 == s.length())
            flag = true;
        else
            flag = false;
        return flag;
    }

    private Class loadClass(ClassLoader classloader, String s)
    {
        Class class1 = null;
        if(classloader != null) goto _L2; else goto _L1
_L1:
        return class1;
_L2:
        Class class2 = classloader.loadClass(s);
        class1 = class2;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        continue; /* Loop/switch isn't completed */
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void calculate(IThrowableProxy ithrowableproxy)
    {
        for(; ithrowableproxy != null; ithrowableproxy = ithrowableproxy.getCause())
        {
            populateFrames(ithrowableproxy.getStackTraceElementProxyArray());
            IThrowableProxy aithrowableproxy[] = ithrowableproxy.getSuppressed();
            if(aithrowableproxy == null)
                continue;
            int i = aithrowableproxy.length;
            for(int j = 0; j < i; j++)
                populateFrames(aithrowableproxy[j].getStackTraceElementProxyArray());

        }

    }

    String getCodeLocation(Class class1)
    {
        if(class1 == null)
            break MISSING_BLOCK_LABEL_70;
        String s;
        String s2;
        CodeSource codesource = class1.getProtectionDomain().getCodeSource();
        if(codesource == null)
            break MISSING_BLOCK_LABEL_70;
        URL url = codesource.getLocation();
        if(url == null)
            break MISSING_BLOCK_LABEL_70;
        String s1 = url.toString();
        s = getCodeLocation(s1, '/');
        if(s != null)
            break MISSING_BLOCK_LABEL_73;
        s2 = getCodeLocation(s1, '\\');
        s = s2;
        break MISSING_BLOCK_LABEL_73;
        Exception exception;
        exception;
        s = "na";
        return s;
    }

    String getImplementationVersion(Class class1)
    {
        if(class1 != null) goto _L2; else goto _L1
_L1:
        String s = "na";
_L4:
        return s;
_L2:
        Package package1 = class1.getPackage();
        if(package1 != null)
        {
            s = package1.getImplementationVersion();
            if(s == null)
                s = "na";
        } else
        {
            s = "na";
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    void populateFrames(StackTraceElementProxy astacktraceelementproxy[])
    {
        int i = STEUtil.findNumberOfCommonFrames((new Throwable("local stack reference")).getStackTrace(), astacktraceelementproxy);
        int j = astacktraceelementproxy.length - i;
        for(int k = 0; k < i; k++)
        {
            StackTraceElementProxy stacktraceelementproxy = astacktraceelementproxy[j + k];
            stacktraceelementproxy.setClassPackagingData(computeBySTEP(stacktraceelementproxy, null));
        }

        populateUncommonFrames(i, astacktraceelementproxy, null);
    }

    void populateUncommonFrames(int i, StackTraceElementProxy astacktraceelementproxy[], ClassLoader classloader)
    {
        int j = astacktraceelementproxy.length - i;
        for(int k = 0; k < j; k++)
        {
            StackTraceElementProxy stacktraceelementproxy = astacktraceelementproxy[k];
            stacktraceelementproxy.setClassPackagingData(computeBySTEP(stacktraceelementproxy, classloader));
        }

    }

    static final StackTraceElementProxy STEP_ARRAY_TEMPLATE[] = new StackTraceElementProxy[0];
    HashMap cache;

}
