// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCIconID extends Enum
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


    private SCIconID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCIconID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCIconID(String s, int i, SCIconID sciconid)
    {
        Enum(s, i);
        swigValue = sciconid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCIconID swigToEnum(int i)
    {
        SCIconID asciconid[] = (SCIconID[])com/sonos/sclib/SCIconID.getEnumConstants();
        if(i >= asciconid.length || i < 0 || asciconid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCIconID sciconid1 = asciconid[i];
_L4:
        return sciconid1;
_L2:
        int j = asciconid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCIconID sciconid = asciconid[k];
            if(sciconid.swigValue == i)
            {
                sciconid1 = sciconid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIconID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCIconID valueOf(String s)
    {
        return (SCIconID)Enum.valueOf(com/sonos/sclib/SCIconID, s);
    }

    public static SCIconID[] values()
    {
        return (SCIconID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCIconID $VALUES[];
    public static final SCIconID SC_ICONID_BATHROOM;
    public static final SCIconID SC_ICONID_BEDROOM;
    public static final SCIconID SC_ICONID_DEN;
    public static final SCIconID SC_ICONID_DINING_ROOM;
    public static final SCIconID SC_ICONID_FAMILY_ROOM;
    public static final SCIconID SC_ICONID_GARAGE;
    public static final SCIconID SC_ICONID_GENERIC;
    public static final SCIconID SC_ICONID_HALLWAY;
    public static final SCIconID SC_ICONID_INPUT_AIRPLAY;
    public static final SCIconID SC_ICONID_INPUT_AUDIOCOMPONENT;
    public static final SCIconID SC_ICONID_INPUT_COMPUTER;
    public static final SCIconID SC_ICONID_INPUT_HOMETHEATER;
    public static final SCIconID SC_ICONID_INPUT_MP3PLAYER;
    public static final SCIconID SC_ICONID_INVALID;
    public static final SCIconID SC_ICONID_KITCHEN;
    public static final SCIconID SC_ICONID_LIBRARY;
    public static final SCIconID SC_ICONID_LIVING_ROOM;
    public static final SCIconID SC_ICONID_MAX;
    public static final SCIconID SC_ICONID_MEDIA_ROOM;
    public static final SCIconID SC_ICONID_OFFICE;
    public static final SCIconID SC_ICONID_PATIO;
    public static final SCIconID SC_ICONID_POOL;
    public static final SCIconID SC_ICONID_PORTABLE;
    public static final SCIconID SC_ICONID_ZONE_EXTENDER;
    private final int swigValue;

    static 
    {
        SC_ICONID_INVALID = new SCIconID("SC_ICONID_INVALID", 0, sclibJNI.SC_ICONID_INVALID_get());
        SC_ICONID_BEDROOM = new SCIconID("SC_ICONID_BEDROOM", 1, sclibJNI.SC_ICONID_BEDROOM_get());
        SC_ICONID_DEN = new SCIconID("SC_ICONID_DEN", 2);
        SC_ICONID_DINING_ROOM = new SCIconID("SC_ICONID_DINING_ROOM", 3);
        SC_ICONID_FAMILY_ROOM = new SCIconID("SC_ICONID_FAMILY_ROOM", 4);
        SC_ICONID_HALLWAY = new SCIconID("SC_ICONID_HALLWAY", 5);
        SC_ICONID_KITCHEN = new SCIconID("SC_ICONID_KITCHEN", 6);
        SC_ICONID_LIBRARY = new SCIconID("SC_ICONID_LIBRARY", 7);
        SC_ICONID_LIVING_ROOM = new SCIconID("SC_ICONID_LIVING_ROOM", 8);
        SC_ICONID_MEDIA_ROOM = new SCIconID("SC_ICONID_MEDIA_ROOM", 9);
        SC_ICONID_OFFICE = new SCIconID("SC_ICONID_OFFICE", 10);
        SC_ICONID_PATIO = new SCIconID("SC_ICONID_PATIO", 11);
        SC_ICONID_BATHROOM = new SCIconID("SC_ICONID_BATHROOM", 12);
        SC_ICONID_GARAGE = new SCIconID("SC_ICONID_GARAGE", 13);
        SC_ICONID_POOL = new SCIconID("SC_ICONID_POOL", 14);
        SC_ICONID_GENERIC = new SCIconID("SC_ICONID_GENERIC", 15);
        SC_ICONID_ZONE_EXTENDER = new SCIconID("SC_ICONID_ZONE_EXTENDER", 16);
        SC_ICONID_PORTABLE = new SCIconID("SC_ICONID_PORTABLE", 17);
        SC_ICONID_INPUT_AUDIOCOMPONENT = new SCIconID("SC_ICONID_INPUT_AUDIOCOMPONENT", 18);
        SC_ICONID_INPUT_COMPUTER = new SCIconID("SC_ICONID_INPUT_COMPUTER", 19);
        SC_ICONID_INPUT_HOMETHEATER = new SCIconID("SC_ICONID_INPUT_HOMETHEATER", 20);
        SC_ICONID_INPUT_MP3PLAYER = new SCIconID("SC_ICONID_INPUT_MP3PLAYER", 21);
        SC_ICONID_INPUT_AIRPLAY = new SCIconID("SC_ICONID_INPUT_AIRPLAY", 22);
        SC_ICONID_MAX = new SCIconID("SC_ICONID_MAX", 23);
        SCIconID asciconid[] = new SCIconID[24];
        asciconid[0] = SC_ICONID_INVALID;
        asciconid[1] = SC_ICONID_BEDROOM;
        asciconid[2] = SC_ICONID_DEN;
        asciconid[3] = SC_ICONID_DINING_ROOM;
        asciconid[4] = SC_ICONID_FAMILY_ROOM;
        asciconid[5] = SC_ICONID_HALLWAY;
        asciconid[6] = SC_ICONID_KITCHEN;
        asciconid[7] = SC_ICONID_LIBRARY;
        asciconid[8] = SC_ICONID_LIVING_ROOM;
        asciconid[9] = SC_ICONID_MEDIA_ROOM;
        asciconid[10] = SC_ICONID_OFFICE;
        asciconid[11] = SC_ICONID_PATIO;
        asciconid[12] = SC_ICONID_BATHROOM;
        asciconid[13] = SC_ICONID_GARAGE;
        asciconid[14] = SC_ICONID_POOL;
        asciconid[15] = SC_ICONID_GENERIC;
        asciconid[16] = SC_ICONID_ZONE_EXTENDER;
        asciconid[17] = SC_ICONID_PORTABLE;
        asciconid[18] = SC_ICONID_INPUT_AUDIOCOMPONENT;
        asciconid[19] = SC_ICONID_INPUT_COMPUTER;
        asciconid[20] = SC_ICONID_INPUT_HOMETHEATER;
        asciconid[21] = SC_ICONID_INPUT_MP3PLAYER;
        asciconid[22] = SC_ICONID_INPUT_AIRPLAY;
        asciconid[23] = SC_ICONID_MAX;
        $VALUES = asciconid;
    }
}
