// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCIGroupVolumeMode extends Enum
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


    private SCIGroupVolumeMode(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCIGroupVolumeMode(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCIGroupVolumeMode(String s, int i, SCIGroupVolumeMode scigroupvolumemode)
    {
        Enum(s, i);
        swigValue = scigroupvolumemode.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCIGroupVolumeMode swigToEnum(int i)
    {
        SCIGroupVolumeMode ascigroupvolumemode[] = (SCIGroupVolumeMode[])com/sonos/sclib/SCIGroupVolumeMode.getEnumConstants();
        if(i >= ascigroupvolumemode.length || i < 0 || ascigroupvolumemode[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCIGroupVolumeMode scigroupvolumemode1 = ascigroupvolumemode[i];
_L4:
        return scigroupvolumemode1;
_L2:
        int j = ascigroupvolumemode.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCIGroupVolumeMode scigroupvolumemode = ascigroupvolumemode[k];
            if(scigroupvolumemode.swigValue == i)
            {
                scigroupvolumemode1 = scigroupvolumemode;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIGroupVolumeMode).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCIGroupVolumeMode valueOf(String s)
    {
        return (SCIGroupVolumeMode)Enum.valueOf(com/sonos/sclib/SCIGroupVolumeMode, s);
    }

    public static SCIGroupVolumeMode[] values()
    {
        return (SCIGroupVolumeMode[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCIGroupVolumeMode $VALUES[];
    public static final SCIGroupVolumeMode SCI_GROUPVOLUMEMODE_DEFAULT;
    public static final SCIGroupVolumeMode SCI_GROUPVOLUMEMODE_FIXED;
    public static final SCIGroupVolumeMode SCI_GROUPVOLUMEMODE_INDIVIDUAL_ONLY;
    private final int swigValue;

    static 
    {
        SCI_GROUPVOLUMEMODE_DEFAULT = new SCIGroupVolumeMode("SCI_GROUPVOLUMEMODE_DEFAULT", 0, sclibJNI.SCI_GROUPVOLUMEMODE_DEFAULT_get());
        SCI_GROUPVOLUMEMODE_FIXED = new SCIGroupVolumeMode("SCI_GROUPVOLUMEMODE_FIXED", 1);
        SCI_GROUPVOLUMEMODE_INDIVIDUAL_ONLY = new SCIGroupVolumeMode("SCI_GROUPVOLUMEMODE_INDIVIDUAL_ONLY", 2);
        SCIGroupVolumeMode ascigroupvolumemode[] = new SCIGroupVolumeMode[3];
        ascigroupvolumemode[0] = SCI_GROUPVOLUMEMODE_DEFAULT;
        ascigroupvolumemode[1] = SCI_GROUPVOLUMEMODE_FIXED;
        ascigroupvolumemode[2] = SCI_GROUPVOLUMEMODE_INDIVIDUAL_ONLY;
        $VALUES = ascigroupvolumemode;
    }
}
