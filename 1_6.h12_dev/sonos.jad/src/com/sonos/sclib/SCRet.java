// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCRet extends Enum
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


    private SCRet(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCRet(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCRet(String s, int i, SCRet scret)
    {
        Enum(s, i);
        swigValue = scret.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCRet swigToEnum(int i)
    {
        SCRet ascret[] = (SCRet[])com/sonos/sclib/SCRet.getEnumConstants();
        if(i >= ascret.length || i < 0 || ascret[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCRet scret1 = ascret[i];
_L4:
        return scret1;
_L2:
        int j = ascret.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCRet scret = ascret[k];
            if(scret.swigValue == i)
            {
                scret1 = scret;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCRet).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCRet valueOf(String s)
    {
        return (SCRet)Enum.valueOf(com/sonos/sclib/SCRet, s);
    }

    public static SCRet[] values()
    {
        return (SCRet[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCRet $VALUES[];
    public static final SCRet SC_RET_ERROR;
    public static final SCRet SC_RET_NOT_IMPLEMENTED;
    public static final SCRet SC_RET_OK;
    private final int swigValue;

    static 
    {
        SC_RET_OK = new SCRet("SC_RET_OK", 0, sclibJNI.SC_RET_OK_get());
        SC_RET_ERROR = new SCRet("SC_RET_ERROR", 1);
        SC_RET_NOT_IMPLEMENTED = new SCRet("SC_RET_NOT_IMPLEMENTED", 2);
        SCRet ascret[] = new SCRet[3];
        ascret[0] = SC_RET_OK;
        ascret[1] = SC_RET_ERROR;
        ascret[2] = SC_RET_NOT_IMPLEMENTED;
        $VALUES = ascret;
    }
}
