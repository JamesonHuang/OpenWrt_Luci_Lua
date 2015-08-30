// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;


// Referenced classes of package ch.qos.logback.classic.pattern:
//            Abbreviator

public class ClassNameOnlyAbbreviator
    implements Abbreviator
{

    public ClassNameOnlyAbbreviator()
    {
    }

    public String abbreviate(String s)
    {
        int i = s.lastIndexOf('.');
        if(i != -1)
            s = s.substring(i + 1, s.length());
        return s;
    }
}
