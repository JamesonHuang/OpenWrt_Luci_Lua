// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.helpers;

import java.util.LinkedList;
import java.util.List;

public class ThrowableToStringArray
{

    public ThrowableToStringArray()
    {
    }

    public static String[] convert(Throwable throwable)
    {
        LinkedList linkedlist = new LinkedList();
        extract(linkedlist, throwable, null);
        return (String[])linkedlist.toArray(new String[0]);
    }

    private static void extract(List list, Throwable throwable, StackTraceElement astacktraceelement[])
    {
        StackTraceElement astacktraceelement1[] = throwable.getStackTrace();
        int i = findNumberOfCommonFrames(astacktraceelement1, astacktraceelement);
        list.add(formatFirstLine(throwable, astacktraceelement));
        for(int j = 0; j < astacktraceelement1.length - i; j++)
            list.add((new StringBuilder()).append("\tat ").append(astacktraceelement1[j].toString()).toString());

        if(i != 0)
            list.add((new StringBuilder()).append("\t... ").append(i).append(" common frames omitted").toString());
        Throwable throwable1 = throwable.getCause();
        if(throwable1 != null)
            extract(list, throwable1, astacktraceelement1);
    }

    private static int findNumberOfCommonFrames(StackTraceElement astacktraceelement[], StackTraceElement astacktraceelement1[])
    {
        int i = 0;
        if(astacktraceelement1 != null)
        {
            int j = -1 + astacktraceelement.length;
            int k = -1 + astacktraceelement1.length;
            while(j >= 0 && k >= 0 && astacktraceelement[j].equals(astacktraceelement1[k])) 
            {
                i++;
                j--;
                k--;
            }
        }
        return i;
    }

    private static String formatFirstLine(Throwable throwable, StackTraceElement astacktraceelement[])
    {
        String s = "";
        if(astacktraceelement != null)
            s = "Caused by: ";
        String s1 = (new StringBuilder()).append(s).append(throwable.getClass().getName()).toString();
        if(throwable.getMessage() != null)
            s1 = (new StringBuilder()).append(s1).append(": ").append(throwable.getMessage()).toString();
        return s1;
    }
}
