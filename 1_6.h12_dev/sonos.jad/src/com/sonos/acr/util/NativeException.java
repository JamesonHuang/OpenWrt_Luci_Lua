// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;


public class NativeException extends Exception
{

    public NativeException(String s)
    {
        super(s);
    }

    public Throwable fillInStackTrace()
    {
        super.fillInStackTrace();
        StackTraceElement astacktraceelement[] = getStackTrace();
        StackTraceElement astacktraceelement1[] = new StackTraceElement[-1 + (nativeStack.length + astacktraceelement.length)];
        int i = 0;
        for(int j = 0; j < nativeStack.length;)
        {
            int i1 = i + 1;
            astacktraceelement1[i] = nativeStack[j];
            j++;
            i = i1;
        }

        for(int k = 1; k < astacktraceelement.length;)
        {
            int l = i + 1;
            astacktraceelement1[i] = astacktraceelement[k];
            k++;
            i = l;
        }

        setStackTrace(astacktraceelement1);
        return this;
    }

    static StackTraceElement nativeStack[] = new StackTraceElement[0];
    private static final long serialVersionUID = 1L;

}
