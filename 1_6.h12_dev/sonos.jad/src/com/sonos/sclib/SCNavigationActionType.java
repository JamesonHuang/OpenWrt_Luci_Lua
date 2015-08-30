// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCNavigationActionType extends Enum
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


    private SCNavigationActionType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNavigationActionType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNavigationActionType(String s, int i, SCNavigationActionType scnavigationactiontype)
    {
        Enum(s, i);
        swigValue = scnavigationactiontype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNavigationActionType swigToEnum(int i)
    {
        SCNavigationActionType ascnavigationactiontype[] = (SCNavigationActionType[])com/sonos/sclib/SCNavigationActionType.getEnumConstants();
        if(i >= ascnavigationactiontype.length || i < 0 || ascnavigationactiontype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNavigationActionType scnavigationactiontype1 = ascnavigationactiontype[i];
_L4:
        return scnavigationactiontype1;
_L2:
        int j = ascnavigationactiontype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNavigationActionType scnavigationactiontype = ascnavigationactiontype[k];
            if(scnavigationactiontype.swigValue == i)
            {
                scnavigationactiontype1 = scnavigationactiontype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNavigationActionType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNavigationActionType valueOf(String s)
    {
        return (SCNavigationActionType)Enum.valueOf(com/sonos/sclib/SCNavigationActionType, s);
    }

    public static SCNavigationActionType[] values()
    {
        return (SCNavigationActionType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNavigationActionType $VALUES[];
    public static final SCNavigationActionType SCACTN_NAVTO_ALARMS;
    public static final SCNavigationActionType SCACTN_NAVTO_MUSICMENU;
    public static final SCNavigationActionType SCACTN_NAVTO_NOWPLAYING;
    public static final SCNavigationActionType SCACTN_NAVTO_ROOMSMENU;
    public static final SCNavigationActionType SCACTN_NAVTO_SEARCH;
    public static final SCNavigationActionType SCACTN_NAVTO_SETTINGS;
    private final int swigValue;

    static 
    {
        SCACTN_NAVTO_NOWPLAYING = new SCNavigationActionType("SCACTN_NAVTO_NOWPLAYING", 0);
        SCACTN_NAVTO_MUSICMENU = new SCNavigationActionType("SCACTN_NAVTO_MUSICMENU", 1);
        SCACTN_NAVTO_ROOMSMENU = new SCNavigationActionType("SCACTN_NAVTO_ROOMSMENU", 2);
        SCACTN_NAVTO_SEARCH = new SCNavigationActionType("SCACTN_NAVTO_SEARCH", 3);
        SCACTN_NAVTO_SETTINGS = new SCNavigationActionType("SCACTN_NAVTO_SETTINGS", 4);
        SCACTN_NAVTO_ALARMS = new SCNavigationActionType("SCACTN_NAVTO_ALARMS", 5);
        SCNavigationActionType ascnavigationactiontype[] = new SCNavigationActionType[6];
        ascnavigationactiontype[0] = SCACTN_NAVTO_NOWPLAYING;
        ascnavigationactiontype[1] = SCACTN_NAVTO_MUSICMENU;
        ascnavigationactiontype[2] = SCACTN_NAVTO_ROOMSMENU;
        ascnavigationactiontype[3] = SCACTN_NAVTO_SEARCH;
        ascnavigationactiontype[4] = SCACTN_NAVTO_SETTINGS;
        ascnavigationactiontype[5] = SCACTN_NAVTO_ALARMS;
        $VALUES = ascnavigationactiontype;
    }
}
