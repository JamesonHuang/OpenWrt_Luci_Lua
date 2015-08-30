// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;


public abstract class Converter
{

    public Converter()
    {
    }

    public abstract String convert(Object obj);

    public final Converter getNext()
    {
        return next;
    }

    public final void setNext(Converter converter)
    {
        if(next != null)
        {
            throw new IllegalStateException("Next converter has been already set");
        } else
        {
            next = converter;
            return;
        }
    }

    public void write(StringBuilder stringbuilder, Object obj)
    {
        stringbuilder.append(convert(obj));
    }

    Converter next;
}
