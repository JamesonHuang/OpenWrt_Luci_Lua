// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;


public class IncompatibleClassException extends Exception
{

    IncompatibleClassException(Class class1, Class class2)
    {
        requestedClass = class1;
        obtainedClass = class2;
    }

    private static final long serialVersionUID = 0xaf2f39dfda752c83L;
    Class obtainedClass;
    Class requestedClass;
}
