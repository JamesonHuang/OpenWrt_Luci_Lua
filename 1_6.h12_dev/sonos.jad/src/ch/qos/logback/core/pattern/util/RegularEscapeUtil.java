// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.util;


// Referenced classes of package ch.qos.logback.core.pattern.util:
//            IEscapeUtil

public class RegularEscapeUtil
    implements IEscapeUtil
{

    public RegularEscapeUtil()
    {
    }

    public static String basicEscape(String s)
    {
        int i = s.length();
        StringBuffer stringbuffer = new StringBuffer(i);
        int j = 0;
        while(j < i) 
        {
            int k = j + 1;
            char c = s.charAt(j);
            if(c == '\\')
            {
                j = k + 1;
                c = s.charAt(k);
                if(c == 'n')
                    c = '\n';
                else
                if(c == 'r')
                    c = '\r';
                else
                if(c == 't')
                    c = '\t';
                else
                if(c == 'f')
                    c = '\f';
            } else
            {
                j = k;
            }
            stringbuffer.append(c);
        }
        return stringbuffer.toString();
    }

    public void escape(String s, StringBuffer stringbuffer, char c, int i)
    {
        if(s.indexOf(c) < 0) goto _L2; else goto _L1
_L1:
        stringbuffer.append(c);
_L4:
        return;
_L2:
        switch(c)
        {
        default:
            String s1 = formatEscapeCharsForListing(s);
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal char '").append(c).append(" at column ").append(i).append(". Only \\\\, \\_").append(s1).append(", \\t, \\n, \\r combinations are allowed as escape characters.").toString());

        case 92: // '\\'
            stringbuffer.append(c);
            break;

        case 116: // 't'
            stringbuffer.append('\t');
            break;

        case 114: // 'r'
            stringbuffer.append('\r');
            break;

        case 110: // 'n'
            stringbuffer.append('\n');
            break;

        case 95: // '_'
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    String formatEscapeCharsForListing(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
            stringbuilder.append(", \\").append(s.charAt(i));

        return stringbuilder.toString();
    }
}
