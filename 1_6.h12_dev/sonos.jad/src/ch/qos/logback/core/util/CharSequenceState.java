// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;


class CharSequenceState
{

    public CharSequenceState(char c1)
    {
        c = c1;
        occurrences = 1;
    }

    void incrementOccurrences()
    {
        occurrences = 1 + occurrences;
    }

    final char c;
    int occurrences;
}
