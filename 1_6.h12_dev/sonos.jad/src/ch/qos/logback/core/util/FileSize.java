// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSize
{

    FileSize(long l)
    {
        size = l;
    }

    public static FileSize valueOf(String s)
    {
        Matcher matcher = FILE_SIZE_PATTERN.matcher(s);
        if(matcher.matches())
        {
            String s1 = matcher.group(1);
            String s2 = matcher.group(2);
            long l = Long.valueOf(s1).longValue();
            long l1;
            if(s2.equalsIgnoreCase(""))
                l1 = 1L;
            else
            if(s2.equalsIgnoreCase("kb"))
                l1 = 1024L;
            else
            if(s2.equalsIgnoreCase("mb"))
                l1 = 0x100000L;
            else
            if(s2.equalsIgnoreCase("gb"))
                l1 = 0x40000000L;
            else
                throw new IllegalStateException((new StringBuilder()).append("Unexpected ").append(s2).toString());
            return new FileSize(l1 * l);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("String value [").append(s).append("] is not in the expected format.").toString());
        }
    }

    public long getSize()
    {
        return size;
    }

    private static final int DOUBLE_GROUP = 1;
    private static final Pattern FILE_SIZE_PATTERN = Pattern.compile("([0-9]+)\\s*(|kb|mb|gb)s?", 2);
    static final long GB_COEFFICIENT = 0x40000000L;
    static final long KB_COEFFICIENT = 1024L;
    private static final String LENGTH_PART = "([0-9]+)";
    static final long MB_COEFFICIENT = 0x100000L;
    private static final int UNIT_GROUP = 2;
    private static final String UNIT_PART = "(|kb|mb|gb)s?";
    final long size;

}
