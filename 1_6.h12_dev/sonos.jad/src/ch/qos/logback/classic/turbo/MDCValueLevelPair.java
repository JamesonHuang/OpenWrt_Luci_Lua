// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;

public class MDCValueLevelPair
{

    public MDCValueLevelPair()
    {
    }

    public Level getLevel()
    {
        return level;
    }

    public String getValue()
    {
        return value;
    }

    public void setLevel(Level level1)
    {
        level = level1;
    }

    public void setValue(String s)
    {
        value = s;
    }

    private Level level;
    private String value;
}
