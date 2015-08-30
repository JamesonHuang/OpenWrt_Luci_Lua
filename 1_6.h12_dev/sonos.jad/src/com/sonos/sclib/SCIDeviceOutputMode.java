// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCIDeviceOutputMode extends Enum
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


    private SCIDeviceOutputMode(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCIDeviceOutputMode(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCIDeviceOutputMode(String s, int i, SCIDeviceOutputMode scideviceoutputmode)
    {
        Enum(s, i);
        swigValue = scideviceoutputmode.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCIDeviceOutputMode swigToEnum(int i)
    {
        SCIDeviceOutputMode ascideviceoutputmode[] = (SCIDeviceOutputMode[])com/sonos/sclib/SCIDeviceOutputMode.getEnumConstants();
        if(i >= ascideviceoutputmode.length || i < 0 || ascideviceoutputmode[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCIDeviceOutputMode scideviceoutputmode1 = ascideviceoutputmode[i];
_L4:
        return scideviceoutputmode1;
_L2:
        int j = ascideviceoutputmode.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCIDeviceOutputMode scideviceoutputmode = ascideviceoutputmode[k];
            if(scideviceoutputmode.swigValue == i)
            {
                scideviceoutputmode1 = scideviceoutputmode;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIDeviceOutputMode).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCIDeviceOutputMode valueOf(String s)
    {
        return (SCIDeviceOutputMode)Enum.valueOf(com/sonos/sclib/SCIDeviceOutputMode, s);
    }

    public static SCIDeviceOutputMode[] values()
    {
        return (SCIDeviceOutputMode[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCIDeviceOutputMode $VALUES[];
    public static final SCIDeviceOutputMode SCI_DEVICEVOLUME_OUTPUT_DEFAULT;
    public static final SCIDeviceOutputMode SCI_DEVICEVOLUME_OUTPUT_FIXED;
    public static final SCIDeviceOutputMode SCI_DEVICEVOLUME_OUTPUT_HEADPHONES;
    public static final SCIDeviceOutputMode SCI_DEVICEVOLUME_OUTPUT_UNKNOWN;
    private final int swigValue;

    static 
    {
        SCI_DEVICEVOLUME_OUTPUT_UNKNOWN = new SCIDeviceOutputMode("SCI_DEVICEVOLUME_OUTPUT_UNKNOWN", 0, sclibJNI.SCI_DEVICEVOLUME_OUTPUT_UNKNOWN_get());
        SCI_DEVICEVOLUME_OUTPUT_DEFAULT = new SCIDeviceOutputMode("SCI_DEVICEVOLUME_OUTPUT_DEFAULT", 1);
        SCI_DEVICEVOLUME_OUTPUT_HEADPHONES = new SCIDeviceOutputMode("SCI_DEVICEVOLUME_OUTPUT_HEADPHONES", 2);
        SCI_DEVICEVOLUME_OUTPUT_FIXED = new SCIDeviceOutputMode("SCI_DEVICEVOLUME_OUTPUT_FIXED", 3);
        SCIDeviceOutputMode ascideviceoutputmode[] = new SCIDeviceOutputMode[4];
        ascideviceoutputmode[0] = SCI_DEVICEVOLUME_OUTPUT_UNKNOWN;
        ascideviceoutputmode[1] = SCI_DEVICEVOLUME_OUTPUT_DEFAULT;
        ascideviceoutputmode[2] = SCI_DEVICEVOLUME_OUTPUT_HEADPHONES;
        ascideviceoutputmode[3] = SCI_DEVICEVOLUME_OUTPUT_FIXED;
        $VALUES = ascideviceoutputmode;
    }
}
