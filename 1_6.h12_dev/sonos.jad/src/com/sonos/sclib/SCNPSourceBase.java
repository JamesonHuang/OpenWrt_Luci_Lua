// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCNPSourceBase extends Enum
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


    private SCNPSourceBase(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNPSourceBase(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNPSourceBase(String s, int i, SCNPSourceBase scnpsourcebase)
    {
        Enum(s, i);
        swigValue = scnpsourcebase.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNPSourceBase swigToEnum(int i)
    {
        SCNPSourceBase ascnpsourcebase[] = (SCNPSourceBase[])com/sonos/sclib/SCNPSourceBase.getEnumConstants();
        if(i >= ascnpsourcebase.length || i < 0 || ascnpsourcebase[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNPSourceBase scnpsourcebase1 = ascnpsourcebase[i];
_L4:
        return scnpsourcebase1;
_L2:
        int j = ascnpsourcebase.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNPSourceBase scnpsourcebase = ascnpsourcebase[k];
            if(scnpsourcebase.swigValue == i)
            {
                scnpsourcebase1 = scnpsourcebase;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNPSourceBase).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNPSourceBase valueOf(String s)
    {
        return (SCNPSourceBase)Enum.valueOf(com/sonos/sclib/SCNPSourceBase, s);
    }

    public static SCNPSourceBase[] values()
    {
        return (SCNPSourceBase[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNPSourceBase $VALUES[];
    public static final SCNPSourceBase SC_NP_BASE_LINEIN;
    public static final SCNPSourceBase SC_NP_BASE_NONE;
    public static final SCNPSourceBase SC_NP_BASE_QUEUE;
    public static final SCNPSourceBase SC_NP_BASE_STREAMING;
    private final int swigValue;

    static 
    {
        SC_NP_BASE_NONE = new SCNPSourceBase("SC_NP_BASE_NONE", 0, sclibJNI.SC_NP_BASE_NONE_get());
        SC_NP_BASE_LINEIN = new SCNPSourceBase("SC_NP_BASE_LINEIN", 1);
        SC_NP_BASE_QUEUE = new SCNPSourceBase("SC_NP_BASE_QUEUE", 2);
        SC_NP_BASE_STREAMING = new SCNPSourceBase("SC_NP_BASE_STREAMING", 3);
        SCNPSourceBase ascnpsourcebase[] = new SCNPSourceBase[4];
        ascnpsourcebase[0] = SC_NP_BASE_NONE;
        ascnpsourcebase[1] = SC_NP_BASE_LINEIN;
        ascnpsourcebase[2] = SC_NP_BASE_QUEUE;
        ascnpsourcebase[3] = SC_NP_BASE_STREAMING;
        $VALUES = ascnpsourcebase;
    }
}
