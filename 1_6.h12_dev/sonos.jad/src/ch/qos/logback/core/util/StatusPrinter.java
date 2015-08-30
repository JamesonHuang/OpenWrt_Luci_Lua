// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.helpers.ThrowableToStringArray;
import ch.qos.logback.core.status.*;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.util:
//            CachingDateFormatter

public class StatusPrinter
{

    public StatusPrinter()
    {
    }

    private static void appendThrowable(StringBuilder stringbuilder, Throwable throwable)
    {
        String as[] = ThrowableToStringArray.convert(throwable);
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s = as[j];
            if(!s.startsWith("Caused by: "))
                if(Character.isDigit(s.charAt(0)))
                    stringbuilder.append("\t... ");
                else
                    stringbuilder.append("\tat ");
            stringbuilder.append(s).append(CoreConstants.LINE_SEPARATOR);
            j++;
        }
    }

    public static void buildStr(StringBuilder stringbuilder, String s, Status status)
    {
        String s1;
        if(status.hasChildren())
            s1 = (new StringBuilder()).append(s).append("+ ").toString();
        else
            s1 = (new StringBuilder()).append(s).append("|-").toString();
        if(cachingDateFormat != null)
            stringbuilder.append(cachingDateFormat.format(status.getDate().longValue())).append(" ");
        stringbuilder.append(s1).append(status).append(CoreConstants.LINE_SEPARATOR);
        if(status.getThrowable() != null)
            appendThrowable(stringbuilder, status.getThrowable());
        if(status.hasChildren())
        {
            Status status1;
            for(Iterator iterator = status.iterator(); iterator.hasNext(); buildStr(stringbuilder, (new StringBuilder()).append(s).append("  ").toString(), status1))
                status1 = (Status)iterator.next();

        }
    }

    private static void buildStrFromStatusList(StringBuilder stringbuilder, List list)
    {
        if(list != null)
        {
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
                buildStr(stringbuilder, "", (Status)iterator.next());
        }
    }

    public static void print(Context context)
    {
        print(context, 0L);
    }

    public static void print(Context context, long l)
    {
        if(context == null)
            throw new IllegalArgumentException("Context argument cannot be null");
        StatusManager statusmanager = context.getStatusManager();
        if(statusmanager == null)
            ps.println((new StringBuilder()).append("WARN: Context named \"").append(context.getName()).append("\" has no status manager").toString());
        else
            print(statusmanager, l);
    }

    public static void print(StatusManager statusmanager)
    {
        print(statusmanager, 0L);
    }

    public static void print(StatusManager statusmanager, long l)
    {
        StringBuilder stringbuilder = new StringBuilder();
        buildStrFromStatusList(stringbuilder, StatusUtil.filterStatusListByTimeThreshold(statusmanager.getCopyOfStatusList(), l));
        ps.println(stringbuilder.toString());
    }

    public static void print(List list)
    {
        StringBuilder stringbuilder = new StringBuilder();
        buildStrFromStatusList(stringbuilder, list);
        ps.println(stringbuilder.toString());
    }

    public static void printIfErrorsOccured(Context context)
    {
        StatusManager statusmanager;
        if(context == null)
            throw new IllegalArgumentException("Context argument cannot be null");
        statusmanager = context.getStatusManager();
        if(statusmanager != null) goto _L2; else goto _L1
_L1:
        ps.println((new StringBuilder()).append("WARN: Context named \"").append(context.getName()).append("\" has no status manager").toString());
_L4:
        return;
_L2:
        if((new StatusUtil(context)).getHighestLevel(0L) == 2)
            print(statusmanager);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void printInCaseOfErrorsOrWarnings(Context context)
    {
        printInCaseOfErrorsOrWarnings(context, 0L);
    }

    public static void printInCaseOfErrorsOrWarnings(Context context, long l)
    {
        StatusManager statusmanager;
        if(context == null)
            throw new IllegalArgumentException("Context argument cannot be null");
        statusmanager = context.getStatusManager();
        if(statusmanager != null) goto _L2; else goto _L1
_L1:
        ps.println((new StringBuilder()).append("WARN: Context named \"").append(context.getName()).append("\" has no status manager").toString());
_L4:
        return;
_L2:
        if((new StatusUtil(context)).getHighestLevel(l) >= 1)
            print(statusmanager, l);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void setPrintStream(PrintStream printstream)
    {
        ps = printstream;
    }

    static CachingDateFormatter cachingDateFormat = new CachingDateFormatter("HH:mm:ss,SSS");
    private static PrintStream ps;

    static 
    {
        ps = System.out;
    }
}
