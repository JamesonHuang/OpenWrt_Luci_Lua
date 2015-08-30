// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;


public class TokenConverter
{

    protected TokenConverter(int i)
    {
        type = i;
    }

    public TokenConverter getNext()
    {
        return next;
    }

    public int getType()
    {
        return type;
    }

    public void setNext(TokenConverter tokenconverter)
    {
        next = tokenconverter;
    }

    public void setType(int i)
    {
        type = i;
    }

    static final int DATE = 1;
    static final int IDENTITY = 0;
    static final int INTEGER = 1;
    TokenConverter next;
    int type;
}
