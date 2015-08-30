// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCDateFormat extends Enum
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


    private SCDateFormat(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCDateFormat(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCDateFormat(String s, int i, SCDateFormat scdateformat)
    {
        Enum(s, i);
        swigValue = scdateformat.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCDateFormat swigToEnum(int i)
    {
        SCDateFormat ascdateformat[] = (SCDateFormat[])com/sonos/sclib/SCDateFormat.getEnumConstants();
        if(i >= ascdateformat.length || i < 0 || ascdateformat[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCDateFormat scdateformat1 = ascdateformat[i];
_L4:
        return scdateformat1;
_L2:
        int j = ascdateformat.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCDateFormat scdateformat = ascdateformat[k];
            if(scdateformat.swigValue == i)
            {
                scdateformat1 = scdateformat;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCDateFormat).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCDateFormat valueOf(String s)
    {
        return (SCDateFormat)Enum.valueOf(com/sonos/sclib/SCDateFormat, s);
    }

    public static SCDateFormat[] values()
    {
        return (SCDateFormat[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCDateFormat $VALUES[];
    public static final SCDateFormat SCDATEFORMAT_DMY;
    public static final SCDateFormat SCDATEFORMAT_MDY;
    public static final SCDateFormat SCDATEFORMAT_UNKNOWN;
    public static final SCDateFormat SCDATEFORMAT_YMD;
    private final int swigValue;

    static 
    {
        SCDATEFORMAT_UNKNOWN = new SCDateFormat("SCDATEFORMAT_UNKNOWN", 0, sclibJNI.SCDATEFORMAT_UNKNOWN_get());
        SCDATEFORMAT_MDY = new SCDateFormat("SCDATEFORMAT_MDY", 1);
        SCDATEFORMAT_DMY = new SCDateFormat("SCDATEFORMAT_DMY", 2);
        SCDATEFORMAT_YMD = new SCDateFormat("SCDATEFORMAT_YMD", 3);
        SCDateFormat ascdateformat[] = new SCDateFormat[4];
        ascdateformat[0] = SCDATEFORMAT_UNKNOWN;
        ascdateformat[1] = SCDATEFORMAT_MDY;
        ascdateformat[2] = SCDATEFORMAT_DMY;
        ascdateformat[3] = SCDATEFORMAT_YMD;
        $VALUES = ascdateformat;
    }
}
