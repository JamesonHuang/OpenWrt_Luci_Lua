// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;


public class FormattingTuple
{

    public FormattingTuple(String s)
    {
        this(s, null, null);
    }

    public FormattingTuple(String s, Object aobj[], Throwable throwable1)
    {
        message = s;
        throwable = throwable1;
        if(throwable1 == null)
            argArray = aobj;
        else
            argArray = trimmedCopy(aobj);
    }

    static Object[] trimmedCopy(Object aobj[])
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

    public Object[] getArgArray()
    {
        return argArray;
    }

    public String getMessage()
    {
        return message;
    }

    public Throwable getThrowable()
    {
        return throwable;
    }

    public static FormattingTuple NULL = new FormattingTuple(null);
    private Object argArray[];
    private String message;
    private Throwable throwable;

}
