// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class WizardType extends Enum
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


    private WizardType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private WizardType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private WizardType(String s, int i, WizardType wizardtype)
    {
        Enum(s, i);
        swigValue = wizardtype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static WizardType swigToEnum(int i)
    {
        WizardType awizardtype[] = (WizardType[])com/sonos/sclib/WizardType.getEnumConstants();
        if(i >= awizardtype.length || i < 0 || awizardtype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        WizardType wizardtype1 = awizardtype[i];
_L4:
        return wizardtype1;
_L2:
        int j = awizardtype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            WizardType wizardtype = awizardtype[k];
            if(wizardtype.swigValue == i)
            {
                wizardtype1 = wizardtype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/WizardType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static WizardType valueOf(String s)
    {
        return (WizardType)Enum.valueOf(com/sonos/sclib/WizardType, s);
    }

    public static WizardType[] values()
    {
        return (WizardType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final WizardType $VALUES[];
    public static final WizardType SC_WIZSTATE_SUBWIZ_INIT_ENUM;
    private final int swigValue;

    static 
    {
        SC_WIZSTATE_SUBWIZ_INIT_ENUM = new WizardType("SC_WIZSTATE_SUBWIZ_INIT_ENUM", 0, sclibJNI.SC_WIZSTATE_SUBWIZ_INIT_ENUM_get());
        WizardType awizardtype[] = new WizardType[1];
        awizardtype[0] = SC_WIZSTATE_SUBWIZ_INIT_ENUM;
        $VALUES = awizardtype;
    }
}
