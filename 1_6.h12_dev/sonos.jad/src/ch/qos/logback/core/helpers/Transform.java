// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.helpers;


public class Transform
{

    public Transform()
    {
    }

    public static void appendEscapingCDATA(StringBuilder stringbuilder, String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = s.indexOf("]]>");
        if(i < 0)
        {
            stringbuilder.append(s);
            continue; /* Loop/switch isn't completed */
        }
        int j = 0;
        for(; i > -1; i = s.indexOf("]]>", j))
        {
            stringbuilder.append(s.substring(j, i));
            stringbuilder.append("]]>]]&gt;<![CDATA[");
            j = i + CDATA_END_LEN;
            if(j >= s.length())
                continue; /* Loop/switch isn't completed */
        }

        stringbuilder.append(s.substring(j));
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static String escapeTags(String s)
    {
        if(s != null && s.length() != 0 && (s.indexOf("<") != -1 || s.indexOf(">") != -1))
            s = escapeTags(new StringBuffer(s));
        return s;
    }

    public static String escapeTags(StringBuffer stringbuffer)
    {
        int i = 0;
        while(i < stringbuffer.length()) 
        {
            char c = stringbuffer.charAt(i);
            if(c == '<')
                stringbuffer.replace(i, i + 1, "&lt;");
            else
            if(c == '>')
                stringbuffer.replace(i, i + 1, "&gt;");
            i++;
        }
        return stringbuffer.toString();
    }

    private static final String CDATA_EMBEDED_END = "]]>]]&gt;<![CDATA[";
    private static final String CDATA_END = "]]>";
    private static final int CDATA_END_LEN = "]]>".length();
    private static final String CDATA_PSEUDO_END = "]]&gt;";
    private static final String CDATA_START = "<![CDATA[";

}
