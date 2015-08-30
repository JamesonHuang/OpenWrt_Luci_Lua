// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.io.PrintStream;

public class Util
{

    public Util()
    {
    }

    public static final void report(String s)
    {
        System.err.println((new StringBuilder()).append("SLF4J: ").append(s).toString());
    }

    public static final void report(String s, Throwable throwable)
    {
        System.err.println(s);
        System.err.println("Reported exception:");
        throwable.printStackTrace();
    }
}
