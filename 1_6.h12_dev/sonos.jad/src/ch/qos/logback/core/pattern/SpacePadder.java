// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;


public class SpacePadder
{

    public SpacePadder()
    {
    }

    public static final void leftPad(StringBuilder stringbuilder, String s, int i)
    {
        int j = 0;
        if(s != null)
            j = s.length();
        if(j < i)
            spacePad(stringbuilder, i - j);
        if(s != null)
            stringbuilder.append(s);
    }

    public static final void rightPad(StringBuilder stringbuilder, String s, int i)
    {
        int j = 0;
        if(s != null)
            j = s.length();
        if(s != null)
            stringbuilder.append(s);
        if(j < i)
            spacePad(stringbuilder, i - j);
    }

    public static final void spacePad(StringBuilder stringbuilder, int i)
    {
        for(; i >= 32; i -= 32)
            stringbuilder.append(SPACES[5]);

        for(int j = 4; j >= 0; j--)
            if((i & 1 << j) != 0)
                stringbuilder.append(SPACES[j]);

    }

    static final String SPACES[];

    static 
    {
        String as[] = new String[6];
        as[0] = " ";
        as[1] = "  ";
        as[2] = "    ";
        as[3] = "        ";
        as[4] = "                ";
        as[5] = "                                ";
        SPACES = as;
    }
}
