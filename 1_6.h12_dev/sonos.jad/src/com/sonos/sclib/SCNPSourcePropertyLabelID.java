// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCNPSourcePropertyLabelID extends Enum
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


    private SCNPSourcePropertyLabelID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNPSourcePropertyLabelID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNPSourcePropertyLabelID(String s, int i, SCNPSourcePropertyLabelID scnpsourcepropertylabelid)
    {
        Enum(s, i);
        swigValue = scnpsourcepropertylabelid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNPSourcePropertyLabelID swigToEnum(int i)
    {
        SCNPSourcePropertyLabelID ascnpsourcepropertylabelid[] = (SCNPSourcePropertyLabelID[])com/sonos/sclib/SCNPSourcePropertyLabelID.getEnumConstants();
        if(i >= ascnpsourcepropertylabelid.length || i < 0 || ascnpsourcepropertylabelid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNPSourcePropertyLabelID scnpsourcepropertylabelid1 = ascnpsourcepropertylabelid[i];
_L4:
        return scnpsourcepropertylabelid1;
_L2:
        int j = ascnpsourcepropertylabelid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNPSourcePropertyLabelID scnpsourcepropertylabelid = ascnpsourcepropertylabelid[k];
            if(scnpsourcepropertylabelid.swigValue == i)
            {
                scnpsourcepropertylabelid1 = scnpsourcepropertylabelid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNPSourcePropertyLabelID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNPSourcePropertyLabelID valueOf(String s)
    {
        return (SCNPSourcePropertyLabelID)Enum.valueOf(com/sonos/sclib/SCNPSourcePropertyLabelID, s);
    }

    public static SCNPSourcePropertyLabelID[] values()
    {
        return (SCNPSourcePropertyLabelID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNPSourcePropertyLabelID $VALUES[];
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_ALARM;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_ALBUM;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_ARTIST;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_AUTHOR;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_CHANNEL;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_CONTENT_FROM;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_DOCKED_IPOD;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_EMPTY;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_HLS;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_HOST;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_INFORMATION;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_LASTFM;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_MAX;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_NARRATOR;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_NEXT;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_NEXT_TRACK;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_ON_NOW;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_PANDORA_RADIO;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_RHAPSODY_CHANNEL;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_SECTION;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_SECTION_COUNT;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_SHOW;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_SONOS_PROGRADIO_NAME;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_SOURCE;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_STATION;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_TRACK;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_TRACK_COUNT;
    public static final SCNPSourcePropertyLabelID SC_NP_LABELID_ZONEPLAYER_CONNECTION;
    private final int swigValue;

    static 
    {
        SC_NP_LABELID_EMPTY = new SCNPSourcePropertyLabelID("SC_NP_LABELID_EMPTY", 0);
        SC_NP_LABELID_TRACK_COUNT = new SCNPSourcePropertyLabelID("SC_NP_LABELID_TRACK_COUNT", 1);
        SC_NP_LABELID_SECTION_COUNT = new SCNPSourcePropertyLabelID("SC_NP_LABELID_SECTION_COUNT", 2);
        SC_NP_LABELID_TRACK = new SCNPSourcePropertyLabelID("SC_NP_LABELID_TRACK", 3);
        SC_NP_LABELID_ALBUM = new SCNPSourcePropertyLabelID("SC_NP_LABELID_ALBUM", 4);
        SC_NP_LABELID_ARTIST = new SCNPSourcePropertyLabelID("SC_NP_LABELID_ARTIST", 5);
        SC_NP_LABELID_SECTION = new SCNPSourcePropertyLabelID("SC_NP_LABELID_SECTION", 6);
        SC_NP_LABELID_AUTHOR = new SCNPSourcePropertyLabelID("SC_NP_LABELID_AUTHOR", 7);
        SC_NP_LABELID_NARRATOR = new SCNPSourcePropertyLabelID("SC_NP_LABELID_NARRATOR", 8);
        SC_NP_LABELID_HOST = new SCNPSourcePropertyLabelID("SC_NP_LABELID_HOST", 9);
        SC_NP_LABELID_SHOW = new SCNPSourcePropertyLabelID("SC_NP_LABELID_SHOW", 10);
        SC_NP_LABELID_CONTENT_FROM = new SCNPSourcePropertyLabelID("SC_NP_LABELID_CONTENT_FROM", 11);
        SC_NP_LABELID_CHANNEL = new SCNPSourcePropertyLabelID("SC_NP_LABELID_CHANNEL", 12);
        SC_NP_LABELID_INFORMATION = new SCNPSourcePropertyLabelID("SC_NP_LABELID_INFORMATION", 13);
        SC_NP_LABELID_SOURCE = new SCNPSourcePropertyLabelID("SC_NP_LABELID_SOURCE", 14);
        SC_NP_LABELID_ZONEPLAYER_CONNECTION = new SCNPSourcePropertyLabelID("SC_NP_LABELID_ZONEPLAYER_CONNECTION", 15);
        SC_NP_LABELID_ON_NOW = new SCNPSourcePropertyLabelID("SC_NP_LABELID_ON_NOW", 16);
        SC_NP_LABELID_STATION = new SCNPSourcePropertyLabelID("SC_NP_LABELID_STATION", 17);
        SC_NP_LABELID_ALARM = new SCNPSourcePropertyLabelID("SC_NP_LABELID_ALARM", 18);
        SC_NP_LABELID_NEXT = new SCNPSourcePropertyLabelID("SC_NP_LABELID_NEXT", 19);
        SC_NP_LABELID_RHAPSODY_CHANNEL = new SCNPSourcePropertyLabelID("SC_NP_LABELID_RHAPSODY_CHANNEL", 20);
        SC_NP_LABELID_PANDORA_RADIO = new SCNPSourcePropertyLabelID("SC_NP_LABELID_PANDORA_RADIO", 21);
        SC_NP_LABELID_HLS = new SCNPSourcePropertyLabelID("SC_NP_LABELID_HLS", 22);
        SC_NP_LABELID_LASTFM = new SCNPSourcePropertyLabelID("SC_NP_LABELID_LASTFM", 23);
        SC_NP_LABELID_DOCKED_IPOD = new SCNPSourcePropertyLabelID("SC_NP_LABELID_DOCKED_IPOD", 24);
        SC_NP_LABELID_SONOS_PROGRADIO_NAME = new SCNPSourcePropertyLabelID("SC_NP_LABELID_SONOS_PROGRADIO_NAME", 25);
        SC_NP_LABELID_NEXT_TRACK = new SCNPSourcePropertyLabelID("SC_NP_LABELID_NEXT_TRACK", 26);
        SC_NP_LABELID_MAX = new SCNPSourcePropertyLabelID("SC_NP_LABELID_MAX", 27);
        SCNPSourcePropertyLabelID ascnpsourcepropertylabelid[] = new SCNPSourcePropertyLabelID[28];
        ascnpsourcepropertylabelid[0] = SC_NP_LABELID_EMPTY;
        ascnpsourcepropertylabelid[1] = SC_NP_LABELID_TRACK_COUNT;
        ascnpsourcepropertylabelid[2] = SC_NP_LABELID_SECTION_COUNT;
        ascnpsourcepropertylabelid[3] = SC_NP_LABELID_TRACK;
        ascnpsourcepropertylabelid[4] = SC_NP_LABELID_ALBUM;
        ascnpsourcepropertylabelid[5] = SC_NP_LABELID_ARTIST;
        ascnpsourcepropertylabelid[6] = SC_NP_LABELID_SECTION;
        ascnpsourcepropertylabelid[7] = SC_NP_LABELID_AUTHOR;
        ascnpsourcepropertylabelid[8] = SC_NP_LABELID_NARRATOR;
        ascnpsourcepropertylabelid[9] = SC_NP_LABELID_HOST;
        ascnpsourcepropertylabelid[10] = SC_NP_LABELID_SHOW;
        ascnpsourcepropertylabelid[11] = SC_NP_LABELID_CONTENT_FROM;
        ascnpsourcepropertylabelid[12] = SC_NP_LABELID_CHANNEL;
        ascnpsourcepropertylabelid[13] = SC_NP_LABELID_INFORMATION;
        ascnpsourcepropertylabelid[14] = SC_NP_LABELID_SOURCE;
        ascnpsourcepropertylabelid[15] = SC_NP_LABELID_ZONEPLAYER_CONNECTION;
        ascnpsourcepropertylabelid[16] = SC_NP_LABELID_ON_NOW;
        ascnpsourcepropertylabelid[17] = SC_NP_LABELID_STATION;
        ascnpsourcepropertylabelid[18] = SC_NP_LABELID_ALARM;
        ascnpsourcepropertylabelid[19] = SC_NP_LABELID_NEXT;
        ascnpsourcepropertylabelid[20] = SC_NP_LABELID_RHAPSODY_CHANNEL;
        ascnpsourcepropertylabelid[21] = SC_NP_LABELID_PANDORA_RADIO;
        ascnpsourcepropertylabelid[22] = SC_NP_LABELID_HLS;
        ascnpsourcepropertylabelid[23] = SC_NP_LABELID_LASTFM;
        ascnpsourcepropertylabelid[24] = SC_NP_LABELID_DOCKED_IPOD;
        ascnpsourcepropertylabelid[25] = SC_NP_LABELID_SONOS_PROGRADIO_NAME;
        ascnpsourcepropertylabelid[26] = SC_NP_LABELID_NEXT_TRACK;
        ascnpsourcepropertylabelid[27] = SC_NP_LABELID_MAX;
        $VALUES = ascnpsourcepropertylabelid;
    }
}
