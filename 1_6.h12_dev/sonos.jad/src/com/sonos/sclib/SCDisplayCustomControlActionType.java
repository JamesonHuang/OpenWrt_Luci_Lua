// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCDisplayCustomControlActionType extends Enum
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


    private SCDisplayCustomControlActionType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCDisplayCustomControlActionType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCDisplayCustomControlActionType(String s, int i, SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype)
    {
        Enum(s, i);
        swigValue = scdisplaycustomcontrolactiontype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCDisplayCustomControlActionType swigToEnum(int i)
    {
        SCDisplayCustomControlActionType ascdisplaycustomcontrolactiontype[] = (SCDisplayCustomControlActionType[])com/sonos/sclib/SCDisplayCustomControlActionType.getEnumConstants();
        if(i >= ascdisplaycustomcontrolactiontype.length || i < 0 || ascdisplaycustomcontrolactiontype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype1 = ascdisplaycustomcontrolactiontype[i];
_L4:
        return scdisplaycustomcontrolactiontype1;
_L2:
        int j = ascdisplaycustomcontrolactiontype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype = ascdisplaycustomcontrolactiontype[k];
            if(scdisplaycustomcontrolactiontype.swigValue == i)
            {
                scdisplaycustomcontrolactiontype1 = scdisplaycustomcontrolactiontype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCDisplayCustomControlActionType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCDisplayCustomControlActionType valueOf(String s)
    {
        return (SCDisplayCustomControlActionType)Enum.valueOf(com/sonos/sclib/SCDisplayCustomControlActionType, s);
    }

    public static SCDisplayCustomControlActionType[] values()
    {
        return (SCDisplayCustomControlActionType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCDisplayCustomControlActionType $VALUES[];
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_ABOUTSONOS;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_ADDSHARE;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_ALARMFREQUENCYSELECT;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_DEALERMODE;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_FORGETHOUSEHOLD;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_MOREINFO;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_MOREMUSIC;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_MUSICEQ;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_RESETCONTROLLER;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_ROOMSETTINGS;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_SOCIAL_SHARING;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_SUBEQ;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_SURROUNDSETTINGS;
    public static final SCDisplayCustomControlActionType SCACTN_CUSTOMCTL_TVDIALOGSETTINGS;
    private final int swigValue;

    static 
    {
        SCACTN_CUSTOMCTL_ABOUTSONOS = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_ABOUTSONOS", 0);
        SCACTN_CUSTOMCTL_MUSICEQ = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_MUSICEQ", 1);
        SCACTN_CUSTOMCTL_SUBEQ = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_SUBEQ", 2);
        SCACTN_CUSTOMCTL_TVDIALOGSETTINGS = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_TVDIALOGSETTINGS", 3);
        SCACTN_CUSTOMCTL_SURROUNDSETTINGS = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_SURROUNDSETTINGS", 4);
        SCACTN_CUSTOMCTL_MOREINFO = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_MOREINFO", 5);
        SCACTN_CUSTOMCTL_RESETCONTROLLER = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_RESETCONTROLLER", 6);
        SCACTN_CUSTOMCTL_FORGETHOUSEHOLD = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_FORGETHOUSEHOLD", 7);
        SCACTN_CUSTOMCTL_DEALERMODE = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_DEALERMODE", 8);
        SCACTN_CUSTOMCTL_ALARMFREQUENCYSELECT = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_ALARMFREQUENCYSELECT", 9);
        SCACTN_CUSTOMCTL_ADDSHARE = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_ADDSHARE", 10);
        SCACTN_CUSTOMCTL_SOCIAL_SHARING = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_SOCIAL_SHARING", 11);
        SCACTN_CUSTOMCTL_MOREMUSIC = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_MOREMUSIC", 12);
        SCACTN_CUSTOMCTL_ROOMSETTINGS = new SCDisplayCustomControlActionType("SCACTN_CUSTOMCTL_ROOMSETTINGS", 13);
        SCDisplayCustomControlActionType ascdisplaycustomcontrolactiontype[] = new SCDisplayCustomControlActionType[14];
        ascdisplaycustomcontrolactiontype[0] = SCACTN_CUSTOMCTL_ABOUTSONOS;
        ascdisplaycustomcontrolactiontype[1] = SCACTN_CUSTOMCTL_MUSICEQ;
        ascdisplaycustomcontrolactiontype[2] = SCACTN_CUSTOMCTL_SUBEQ;
        ascdisplaycustomcontrolactiontype[3] = SCACTN_CUSTOMCTL_TVDIALOGSETTINGS;
        ascdisplaycustomcontrolactiontype[4] = SCACTN_CUSTOMCTL_SURROUNDSETTINGS;
        ascdisplaycustomcontrolactiontype[5] = SCACTN_CUSTOMCTL_MOREINFO;
        ascdisplaycustomcontrolactiontype[6] = SCACTN_CUSTOMCTL_RESETCONTROLLER;
        ascdisplaycustomcontrolactiontype[7] = SCACTN_CUSTOMCTL_FORGETHOUSEHOLD;
        ascdisplaycustomcontrolactiontype[8] = SCACTN_CUSTOMCTL_DEALERMODE;
        ascdisplaycustomcontrolactiontype[9] = SCACTN_CUSTOMCTL_ALARMFREQUENCYSELECT;
        ascdisplaycustomcontrolactiontype[10] = SCACTN_CUSTOMCTL_ADDSHARE;
        ascdisplaycustomcontrolactiontype[11] = SCACTN_CUSTOMCTL_SOCIAL_SHARING;
        ascdisplaycustomcontrolactiontype[12] = SCACTN_CUSTOMCTL_MOREMUSIC;
        ascdisplaycustomcontrolactiontype[13] = SCACTN_CUSTOMCTL_ROOMSETTINGS;
        $VALUES = ascdisplaycustomcontrolactiontype;
    }
}
