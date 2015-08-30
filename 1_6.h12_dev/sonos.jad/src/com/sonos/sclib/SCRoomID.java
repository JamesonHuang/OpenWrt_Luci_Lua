// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCRoomID extends Enum
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


    private SCRoomID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCRoomID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCRoomID(String s, int i, SCRoomID scroomid)
    {
        Enum(s, i);
        swigValue = scroomid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCRoomID swigToEnum(int i)
    {
        SCRoomID ascroomid[] = (SCRoomID[])com/sonos/sclib/SCRoomID.getEnumConstants();
        if(i >= ascroomid.length || i < 0 || ascroomid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCRoomID scroomid1 = ascroomid[i];
_L4:
        return scroomid1;
_L2:
        int j = ascroomid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCRoomID scroomid = ascroomid[k];
            if(scroomid.swigValue == i)
            {
                scroomid1 = scroomid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCRoomID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCRoomID valueOf(String s)
    {
        return (SCRoomID)Enum.valueOf(com/sonos/sclib/SCRoomID, s);
    }

    public static SCRoomID[] values()
    {
        return (SCRoomID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCRoomID $VALUES[];
    public static final SCRoomID SC_ROOMID_BATHROOM;
    public static final SCRoomID SC_ROOMID_BEDROOM;
    public static final SCRoomID SC_ROOMID_DEN;
    public static final SCRoomID SC_ROOMID_DINING_ROOM;
    public static final SCRoomID SC_ROOMID_DOCK;
    public static final SCRoomID SC_ROOMID_FAMILY_ROOM;
    public static final SCRoomID SC_ROOMID_FOYER;
    public static final SCRoomID SC_ROOMID_GARAGE;
    public static final SCRoomID SC_ROOMID_GARDEN;
    public static final SCRoomID SC_ROOMID_GENERIC;
    public static final SCRoomID SC_ROOMID_GUEST_ROOM;
    public static final SCRoomID SC_ROOMID_HALLWAY;
    public static final SCRoomID SC_ROOMID_INVALID;
    public static final SCRoomID SC_ROOMID_KITCHEN;
    public static final SCRoomID SC_ROOMID_LIBRARY;
    public static final SCRoomID SC_ROOMID_LIVING_ROOM;
    public static final SCRoomID SC_ROOMID_LOUNGE;
    public static final SCRoomID SC_ROOMID_MASTER_BEDROOM;
    public static final SCRoomID SC_ROOMID_MEDIA_ROOM;
    public static final SCRoomID SC_ROOMID_OFFICE;
    public static final SCRoomID SC_ROOMID_PATIO;
    public static final SCRoomID SC_ROOMID_PLAYROOM;
    public static final SCRoomID SC_ROOMID_POOL;
    public static final SCRoomID SC_ROOMID_PORTABLE;
    public static final SCRoomID SC_ROOMID_TVROOM;
    public static final SCRoomID SC_ROOMID_VIPER;
    public static final SCRoomID SC_ROOMID_ZONE_EXTENDER;
    private final int swigValue;

    static 
    {
        SC_ROOMID_INVALID = new SCRoomID("SC_ROOMID_INVALID", 0, sclibJNI.SC_ROOMID_INVALID_get());
        SC_ROOMID_BATHROOM = new SCRoomID("SC_ROOMID_BATHROOM", 1, sclibJNI.SC_ROOMID_BATHROOM_get());
        SC_ROOMID_BEDROOM = new SCRoomID("SC_ROOMID_BEDROOM", 2);
        SC_ROOMID_DEN = new SCRoomID("SC_ROOMID_DEN", 3);
        SC_ROOMID_DINING_ROOM = new SCRoomID("SC_ROOMID_DINING_ROOM", 4);
        SC_ROOMID_FAMILY_ROOM = new SCRoomID("SC_ROOMID_FAMILY_ROOM", 5);
        SC_ROOMID_FOYER = new SCRoomID("SC_ROOMID_FOYER", 6);
        SC_ROOMID_GARAGE = new SCRoomID("SC_ROOMID_GARAGE", 7);
        SC_ROOMID_GARDEN = new SCRoomID("SC_ROOMID_GARDEN", 8);
        SC_ROOMID_GUEST_ROOM = new SCRoomID("SC_ROOMID_GUEST_ROOM", 9);
        SC_ROOMID_HALLWAY = new SCRoomID("SC_ROOMID_HALLWAY", 10);
        SC_ROOMID_KITCHEN = new SCRoomID("SC_ROOMID_KITCHEN", 11);
        SC_ROOMID_LIBRARY = new SCRoomID("SC_ROOMID_LIBRARY", 12);
        SC_ROOMID_LIVING_ROOM = new SCRoomID("SC_ROOMID_LIVING_ROOM", 13);
        SC_ROOMID_MASTER_BEDROOM = new SCRoomID("SC_ROOMID_MASTER_BEDROOM", 14);
        SC_ROOMID_MEDIA_ROOM = new SCRoomID("SC_ROOMID_MEDIA_ROOM", 15);
        SC_ROOMID_OFFICE = new SCRoomID("SC_ROOMID_OFFICE", 16);
        SC_ROOMID_PATIO = new SCRoomID("SC_ROOMID_PATIO", 17);
        SC_ROOMID_PLAYROOM = new SCRoomID("SC_ROOMID_PLAYROOM", 18);
        SC_ROOMID_POOL = new SCRoomID("SC_ROOMID_POOL", 19);
        SC_ROOMID_TVROOM = new SCRoomID("SC_ROOMID_TVROOM", 20);
        SC_ROOMID_GENERIC = new SCRoomID("SC_ROOMID_GENERIC", 21);
        SC_ROOMID_ZONE_EXTENDER = new SCRoomID("SC_ROOMID_ZONE_EXTENDER", 22);
        SC_ROOMID_VIPER = new SCRoomID("SC_ROOMID_VIPER", 23);
        SC_ROOMID_PORTABLE = new SCRoomID("SC_ROOMID_PORTABLE", 24);
        SC_ROOMID_DOCK = new SCRoomID("SC_ROOMID_DOCK", 25);
        SC_ROOMID_LOUNGE = new SCRoomID("SC_ROOMID_LOUNGE", 26);
        SCRoomID ascroomid[] = new SCRoomID[27];
        ascroomid[0] = SC_ROOMID_INVALID;
        ascroomid[1] = SC_ROOMID_BATHROOM;
        ascroomid[2] = SC_ROOMID_BEDROOM;
        ascroomid[3] = SC_ROOMID_DEN;
        ascroomid[4] = SC_ROOMID_DINING_ROOM;
        ascroomid[5] = SC_ROOMID_FAMILY_ROOM;
        ascroomid[6] = SC_ROOMID_FOYER;
        ascroomid[7] = SC_ROOMID_GARAGE;
        ascroomid[8] = SC_ROOMID_GARDEN;
        ascroomid[9] = SC_ROOMID_GUEST_ROOM;
        ascroomid[10] = SC_ROOMID_HALLWAY;
        ascroomid[11] = SC_ROOMID_KITCHEN;
        ascroomid[12] = SC_ROOMID_LIBRARY;
        ascroomid[13] = SC_ROOMID_LIVING_ROOM;
        ascroomid[14] = SC_ROOMID_MASTER_BEDROOM;
        ascroomid[15] = SC_ROOMID_MEDIA_ROOM;
        ascroomid[16] = SC_ROOMID_OFFICE;
        ascroomid[17] = SC_ROOMID_PATIO;
        ascroomid[18] = SC_ROOMID_PLAYROOM;
        ascroomid[19] = SC_ROOMID_POOL;
        ascroomid[20] = SC_ROOMID_TVROOM;
        ascroomid[21] = SC_ROOMID_GENERIC;
        ascroomid[22] = SC_ROOMID_ZONE_EXTENDER;
        ascroomid[23] = SC_ROOMID_VIPER;
        ascroomid[24] = SC_ROOMID_PORTABLE;
        ascroomid[25] = SC_ROOMID_DOCK;
        ascroomid[26] = SC_ROOMID_LOUNGE;
        $VALUES = ascroomid;
    }
}
