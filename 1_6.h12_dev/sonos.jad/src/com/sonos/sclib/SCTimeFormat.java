// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCTimeFormat extends Enum
{
    private static class SwigNext
    {

        private static int next = 0;



/*
        static int access$002(int i)
        {
            next = i;
            return i;
        }

*/


/*
        static int access$008()
        {
            int i = next;
            next = i + 1;
            return i;
        }

*/

        private SwigNext()
        {
        }
    }


    private SCTimeFormat(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCTimeFormat(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCTimeFormat(String s, int i, SCTimeFormat sctimeformat)
    {
        Enum(s, i);
        swigValue = sctimeformat.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCTimeFormat swigToEnum(int i)
    {
        SCTimeFormat asctimeformat[] = (SCTimeFormat[])com/sonos/sclib/SCTimeFormat.getEnumConstants();
        if(i >= asctimeformat.length || i < 0 || asctimeformat[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCTimeFormat sctimeformat1 = asctimeformat[i];
_L4:
        return sctimeformat1;
_L2:
        int j = asctimeformat.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCTimeFormat sctimeformat = asctimeformat[k];
            if(sctimeformat.swigValue == i)
            {
                sctimeformat1 = sctimeformat;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCTimeFormat).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCTimeFormat valueOf(String s)
    {
        return (SCTimeFormat)Enum.valueOf(com/sonos/sclib/SCTimeFormat, s);
    }

    public static SCTimeFormat[] values()
    {
        return (SCTimeFormat[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCTimeFormat $VALUES[];
    public static final SCTimeFormat SCTIMEFORMAT_12HOUR;
    public static final SCTimeFormat SCTIMEFORMAT_24HOUR;
    public static final SCTimeFormat SCTIMEFORMAT_UNKNOWN;
    private final int swigValue;

    static 
    {
        SCTIMEFORMAT_UNKNOWN = new SCTimeFormat("SCTIMEFORMAT_UNKNOWN", 0, sclibJNI.SCTIMEFORMAT_UNKNOWN_get());
        SCTIMEFORMAT_12HOUR = new SCTimeFormat("SCTIMEFORMAT_12HOUR", 1);
        SCTIMEFORMAT_24HOUR = new SCTimeFormat("SCTIMEFORMAT_24HOUR", 2);
        SCTimeFormat asctimeformat[] = new SCTimeFormat[3];
        asctimeformat[0] = SCTIMEFORMAT_UNKNOWN;
        asctimeformat[1] = SCTIMEFORMAT_12HOUR;
        asctimeformat[2] = SCTIMEFORMAT_24HOUR;
        $VALUES = asctimeformat;
    }
}
