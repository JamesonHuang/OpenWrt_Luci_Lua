// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package org.slf4j.helpers:
//            FormattingTuple

public final class MessageFormatter
{

    public MessageFormatter()
    {
    }

    public static final FormattingTuple arrayFormat(String s, Object aobj[])
    {
        Throwable throwable = getThrowableCandidate(aobj);
        if(s != null) goto _L2; else goto _L1
_L1:
        FormattingTuple formattingtuple = new FormattingTuple(null, aobj, throwable);
_L4:
        return formattingtuple;
_L2:
        if(aobj == null)
        {
            formattingtuple = new FormattingTuple(s);
            continue; /* Loop/switch isn't completed */
        }
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder(50 + s.length());
        int j = 0;
        do
        {
            if(j >= aobj.length)
                break;
            int k = s.indexOf("{}", i);
            if(k == -1)
            {
                if(i == 0)
                {
                    formattingtuple = new FormattingTuple(s, aobj, throwable);
                } else
                {
                    stringbuilder.append(s.substring(i, s.length()));
                    formattingtuple = new FormattingTuple(stringbuilder.toString(), aobj, throwable);
                }
                continue; /* Loop/switch isn't completed */
            }
            if(isEscapedDelimeter(s, k))
            {
                if(!isDoubleEscaped(s, k))
                {
                    j--;
                    stringbuilder.append(s.substring(i, k - 1));
                    stringbuilder.append('{');
                    i = k + 1;
                } else
                {
                    stringbuilder.append(s.substring(i, k - 1));
                    deeplyAppendParameter(stringbuilder, aobj[j], new HashMap());
                    i = k + 2;
                }
            } else
            {
                stringbuilder.append(s.substring(i, k));
                deeplyAppendParameter(stringbuilder, aobj[j], new HashMap());
                i = k + 2;
            }
            j++;
        } while(true);
        stringbuilder.append(s.substring(i, s.length()));
        if(j < -1 + aobj.length)
            formattingtuple = new FormattingTuple(stringbuilder.toString(), aobj, throwable);
        else
            formattingtuple = new FormattingTuple(stringbuilder.toString(), aobj, null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void booleanArrayAppend(StringBuilder stringbuilder, boolean aflag[])
    {
        stringbuilder.append('[');
        int i = aflag.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(aflag[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    private static void byteArrayAppend(StringBuilder stringbuilder, byte abyte0[])
    {
        stringbuilder.append('[');
        int i = abyte0.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(abyte0[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    private static void charArrayAppend(StringBuilder stringbuilder, char ac[])
    {
        stringbuilder.append('[');
        int i = ac.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(ac[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    private static void deeplyAppendParameter(StringBuilder stringbuilder, Object obj, Map map)
    {
        if(obj == null)
            stringbuilder.append("null");
        else
        if(!obj.getClass().isArray())
            safeObjectAppend(stringbuilder, obj);
        else
        if(obj instanceof boolean[])
            booleanArrayAppend(stringbuilder, (boolean[])(boolean[])obj);
        else
        if(obj instanceof byte[])
            byteArrayAppend(stringbuilder, (byte[])(byte[])obj);
        else
        if(obj instanceof char[])
            charArrayAppend(stringbuilder, (char[])(char[])obj);
        else
        if(obj instanceof short[])
            shortArrayAppend(stringbuilder, (short[])(short[])obj);
        else
        if(obj instanceof int[])
            intArrayAppend(stringbuilder, (int[])(int[])obj);
        else
        if(obj instanceof long[])
            longArrayAppend(stringbuilder, (long[])(long[])obj);
        else
        if(obj instanceof float[])
            floatArrayAppend(stringbuilder, (float[])(float[])obj);
        else
        if(obj instanceof double[])
            doubleArrayAppend(stringbuilder, (double[])(double[])obj);
        else
            objectArrayAppend(stringbuilder, (Object[])(Object[])obj, map);
    }

    private static void doubleArrayAppend(StringBuilder stringbuilder, double ad[])
    {
        stringbuilder.append('[');
        int i = ad.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(ad[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    private static void floatArrayAppend(StringBuilder stringbuilder, float af[])
    {
        stringbuilder.append('[');
        int i = af.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(af[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    public static final FormattingTuple format(String s, Object obj)
    {
        Object aobj[] = new Object[1];
        aobj[0] = obj;
        return arrayFormat(s, aobj);
    }

    public static final FormattingTuple format(String s, Object obj, Object obj1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = obj;
        aobj[1] = obj1;
        return arrayFormat(s, aobj);
    }

    static final Throwable getThrowableCandidate(Object aobj[])
    {
        Throwable throwable;
        if(aobj == null || aobj.length == 0)
        {
            throwable = null;
        } else
        {
            Object obj = aobj[-1 + aobj.length];
            if(obj instanceof Throwable)
                throwable = (Throwable)obj;
            else
                throwable = null;
        }
        return throwable;
    }

    private static void intArrayAppend(StringBuilder stringbuilder, int ai[])
    {
        stringbuilder.append('[');
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(ai[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    static final boolean isDoubleEscaped(String s, int i)
    {
        boolean flag;
        if(i >= 2 && s.charAt(i - 2) == '\\')
            flag = true;
        else
            flag = false;
        return flag;
    }

    static final boolean isEscapedDelimeter(String s, int i)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(i != 0 && s.charAt(i - 1) == '\\')
            flag = true;
        return flag;
    }

    private static void longArrayAppend(StringBuilder stringbuilder, long al[])
    {
        stringbuilder.append('[');
        int i = al.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(al[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    private static void objectArrayAppend(StringBuilder stringbuilder, Object aobj[], Map map)
    {
        stringbuilder.append('[');
        if(!map.containsKey(((Object) (aobj))))
        {
            map.put(((Object) (aobj)), null);
            int i = aobj.length;
            for(int j = 0; j < i; j++)
            {
                deeplyAppendParameter(stringbuilder, aobj[j], map);
                if(j != i - 1)
                    stringbuilder.append(", ");
            }

            map.remove(((Object) (aobj)));
        } else
        {
            stringbuilder.append("...");
        }
        stringbuilder.append(']');
    }

    private static void safeObjectAppend(StringBuilder stringbuilder, Object obj)
    {
        stringbuilder.append(obj.toString());
_L1:
        return;
        Throwable throwable;
        throwable;
        System.err.println((new StringBuilder()).append("SLF4J: Failed toString() invocation on an object of type [").append(obj.getClass().getName()).append("]").toString());
        throwable.printStackTrace();
        stringbuilder.append("[FAILED toString()]");
          goto _L1
    }

    private static void shortArrayAppend(StringBuilder stringbuilder, short aword0[])
    {
        stringbuilder.append('[');
        int i = aword0.length;
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(aword0[j]);
            if(j != i - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(']');
    }

    static final char DELIM_START = 123;
    static final char DELIM_STOP = 125;
    static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = 92;
}
