// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCNPMetadataType extends Enum
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


    private SCNPMetadataType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNPMetadataType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNPMetadataType(String s, int i, SCNPMetadataType scnpmetadatatype)
    {
        Enum(s, i);
        swigValue = scnpmetadatatype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNPMetadataType swigToEnum(int i)
    {
        SCNPMetadataType ascnpmetadatatype[] = (SCNPMetadataType[])com/sonos/sclib/SCNPMetadataType.getEnumConstants();
        if(i >= ascnpmetadatatype.length || i < 0 || ascnpmetadatatype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNPMetadataType scnpmetadatatype1 = ascnpmetadatatype[i];
_L4:
        return scnpmetadatatype1;
_L2:
        int j = ascnpmetadatatype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNPMetadataType scnpmetadatatype = ascnpmetadatatype[k];
            if(scnpmetadatatype.swigValue == i)
            {
                scnpmetadatatype1 = scnpmetadatatype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNPMetadataType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNPMetadataType valueOf(String s)
    {
        return (SCNPMetadataType)Enum.valueOf(com/sonos/sclib/SCNPMetadataType, s);
    }

    public static SCNPMetadataType[] values()
    {
        return (SCNPMetadataType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNPMetadataType $VALUES[];
    public static final SCNPMetadataType SC_NP_CUSTOM_PARTNER_LOGO;
    public static final SCNPMetadataType SC_NP_CUSTOM_PARTNER_NAME;
    public static final SCNPMetadataType SC_NP_META_ALARM_RUNNING;
    public static final SCNPMetadataType SC_NP_META_ALBUM_ART_URI;
    public static final SCNPMetadataType SC_NP_META_ALBUM_NAME;
    public static final SCNPMetadataType SC_NP_META_ARTIST_NAME;
    public static final SCNPMetadataType SC_NP_META_BROWSE_STRING;
    public static final SCNPMetadataType SC_NP_META_CHANNEL_INFO1;
    public static final SCNPMetadataType SC_NP_META_CHANNEL_INFO2;
    public static final SCNPMetadataType SC_NP_META_CHANNEL_TITLE;
    public static final SCNPMetadataType SC_NP_META_COMPOUND_STRING_4;
    public static final SCNPMetadataType SC_NP_META_CREATOR;
    public static final SCNPMetadataType SC_NP_META_CURRENT_SECTION;
    public static final SCNPMetadataType SC_NP_META_CURRENT_TRACK;
    public static final SCNPMetadataType SC_NP_META_DOCK_NAME;
    public static final SCNPMetadataType SC_NP_META_GENRE;
    public static final SCNPMetadataType SC_NP_META_IPOD_NAME;
    public static final SCNPMetadataType SC_NP_META_LINEIN_ICON;
    public static final SCNPMetadataType SC_NP_META_LINEIN_SOURCE;
    public static final SCNPMetadataType SC_NP_META_LINEIN_ZONEPLAYER_CONNECTION;
    public static final SCNPMetadataType SC_NP_META_MAX;
    public static final SCNPMetadataType SC_NP_META_NARRATOR;
    public static final SCNPMetadataType SC_NP_META_NUMBER_OF_TRACKS;
    public static final SCNPMetadataType SC_NP_META_NUM_SECTIONS;
    public static final SCNPMetadataType SC_NP_META_RADIO_SHOW_NAME;
    public static final SCNPMetadataType SC_NP_META_RADIO_STATION_NAME;
    public static final SCNPMetadataType SC_NP_META_RADIO_STREAM_INFO;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_1;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_2;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_3;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_4;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_5;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_6;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_7;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_8;
    public static final SCNPMetadataType SC_NP_META_SIMPLE_STRING_9;
    public static final SCNPMetadataType SC_NP_META_SLEEP_TIMER_GENERATION;
    public static final SCNPMetadataType SC_NP_META_SNOOZE_RUNNING;
    public static final SCNPMetadataType SC_NP_META_SONOS_PROGRADIO_NAME;
    public static final SCNPMetadataType SC_NP_META_STREAMINFO;
    public static final SCNPMetadataType SC_NP_META_SUBSCRIPTION_LEVEL;
    public static final SCNPMetadataType SC_NP_META_TOOLTIP_1;
    public static final SCNPMetadataType SC_NP_META_TOOLTIP_2;
    public static final SCNPMetadataType SC_NP_META_TOOLTIP_3;
    public static final SCNPMetadataType SC_NP_META_TOOLTIP_4;
    public static final SCNPMetadataType SC_NP_META_TRACK_DURATION;
    public static final SCNPMetadataType SC_NP_META_TRACK_NAME;
    public static final SCNPMetadataType SC_NP_META_TRACK_TIME_ELAPSED;
    private final int swigValue;

    static 
    {
        SC_NP_META_SIMPLE_STRING_1 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_1", 0, sclibJNI.SC_NP_META_SIMPLE_STRING_1_get());
        SC_NP_META_SIMPLE_STRING_2 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_2", 1, sclibJNI.SC_NP_META_SIMPLE_STRING_2_get());
        SC_NP_META_SIMPLE_STRING_3 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_3", 2, sclibJNI.SC_NP_META_SIMPLE_STRING_3_get());
        SC_NP_META_SIMPLE_STRING_4 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_4", 3, sclibJNI.SC_NP_META_SIMPLE_STRING_4_get());
        SC_NP_META_SIMPLE_STRING_5 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_5", 4, sclibJNI.SC_NP_META_SIMPLE_STRING_5_get());
        SC_NP_META_SIMPLE_STRING_6 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_6", 5, sclibJNI.SC_NP_META_SIMPLE_STRING_6_get());
        SC_NP_META_SIMPLE_STRING_7 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_7", 6, sclibJNI.SC_NP_META_SIMPLE_STRING_7_get());
        SC_NP_META_SIMPLE_STRING_8 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_8", 7, sclibJNI.SC_NP_META_SIMPLE_STRING_8_get());
        SC_NP_META_SIMPLE_STRING_9 = new SCNPMetadataType("SC_NP_META_SIMPLE_STRING_9", 8, sclibJNI.SC_NP_META_SIMPLE_STRING_9_get());
        SC_NP_META_COMPOUND_STRING_4 = new SCNPMetadataType("SC_NP_META_COMPOUND_STRING_4", 9, sclibJNI.SC_NP_META_COMPOUND_STRING_4_get());
        SC_NP_META_BROWSE_STRING = new SCNPMetadataType("SC_NP_META_BROWSE_STRING", 10, sclibJNI.SC_NP_META_BROWSE_STRING_get());
        SC_NP_META_TOOLTIP_1 = new SCNPMetadataType("SC_NP_META_TOOLTIP_1", 11, sclibJNI.SC_NP_META_TOOLTIP_1_get());
        SC_NP_META_TOOLTIP_2 = new SCNPMetadataType("SC_NP_META_TOOLTIP_2", 12, sclibJNI.SC_NP_META_TOOLTIP_2_get());
        SC_NP_META_TOOLTIP_3 = new SCNPMetadataType("SC_NP_META_TOOLTIP_3", 13, sclibJNI.SC_NP_META_TOOLTIP_3_get());
        SC_NP_META_TOOLTIP_4 = new SCNPMetadataType("SC_NP_META_TOOLTIP_4", 14, sclibJNI.SC_NP_META_TOOLTIP_4_get());
        SC_NP_META_TRACK_NAME = new SCNPMetadataType("SC_NP_META_TRACK_NAME", 15);
        SC_NP_META_ARTIST_NAME = new SCNPMetadataType("SC_NP_META_ARTIST_NAME", 16);
        SC_NP_META_ALBUM_NAME = new SCNPMetadataType("SC_NP_META_ALBUM_NAME", 17);
        SC_NP_META_ALBUM_ART_URI = new SCNPMetadataType("SC_NP_META_ALBUM_ART_URI", 18);
        SC_NP_META_GENRE = new SCNPMetadataType("SC_NP_META_GENRE", 19);
        SC_NP_META_CREATOR = new SCNPMetadataType("SC_NP_META_CREATOR", 20);
        SC_NP_META_NARRATOR = new SCNPMetadataType("SC_NP_META_NARRATOR", 21);
        SC_NP_META_LINEIN_SOURCE = new SCNPMetadataType("SC_NP_META_LINEIN_SOURCE", 22);
        SC_NP_META_LINEIN_ZONEPLAYER_CONNECTION = new SCNPMetadataType("SC_NP_META_LINEIN_ZONEPLAYER_CONNECTION", 23);
        SC_NP_META_LINEIN_ICON = new SCNPMetadataType("SC_NP_META_LINEIN_ICON", 24);
        SC_NP_META_RADIO_STATION_NAME = new SCNPMetadataType("SC_NP_META_RADIO_STATION_NAME", 25);
        SC_NP_META_RADIO_SHOW_NAME = new SCNPMetadataType("SC_NP_META_RADIO_SHOW_NAME", 26);
        SC_NP_META_RADIO_STREAM_INFO = new SCNPMetadataType("SC_NP_META_RADIO_STREAM_INFO", 27);
        SC_NP_META_TRACK_TIME_ELAPSED = new SCNPMetadataType("SC_NP_META_TRACK_TIME_ELAPSED", 28);
        SC_NP_META_TRACK_DURATION = new SCNPMetadataType("SC_NP_META_TRACK_DURATION", 29);
        SC_NP_META_NUMBER_OF_TRACKS = new SCNPMetadataType("SC_NP_META_NUMBER_OF_TRACKS", 30);
        SC_NP_META_CURRENT_TRACK = new SCNPMetadataType("SC_NP_META_CURRENT_TRACK", 31);
        SC_NP_META_SONOS_PROGRADIO_NAME = new SCNPMetadataType("SC_NP_META_SONOS_PROGRADIO_NAME", 32);
        SC_NP_META_NUM_SECTIONS = new SCNPMetadataType("SC_NP_META_NUM_SECTIONS", 33);
        SC_NP_META_CURRENT_SECTION = new SCNPMetadataType("SC_NP_META_CURRENT_SECTION", 34);
        SC_NP_META_CHANNEL_TITLE = new SCNPMetadataType("SC_NP_META_CHANNEL_TITLE", 35);
        SC_NP_META_CHANNEL_INFO1 = new SCNPMetadataType("SC_NP_META_CHANNEL_INFO1", 36);
        SC_NP_META_CHANNEL_INFO2 = new SCNPMetadataType("SC_NP_META_CHANNEL_INFO2", 37);
        SC_NP_META_SUBSCRIPTION_LEVEL = new SCNPMetadataType("SC_NP_META_SUBSCRIPTION_LEVEL", 38);
        SC_NP_META_IPOD_NAME = new SCNPMetadataType("SC_NP_META_IPOD_NAME", 39);
        SC_NP_META_DOCK_NAME = new SCNPMetadataType("SC_NP_META_DOCK_NAME", 40);
        SC_NP_META_ALARM_RUNNING = new SCNPMetadataType("SC_NP_META_ALARM_RUNNING", 41);
        SC_NP_META_SNOOZE_RUNNING = new SCNPMetadataType("SC_NP_META_SNOOZE_RUNNING", 42);
        SC_NP_META_SLEEP_TIMER_GENERATION = new SCNPMetadataType("SC_NP_META_SLEEP_TIMER_GENERATION", 43);
        SC_NP_META_STREAMINFO = new SCNPMetadataType("SC_NP_META_STREAMINFO", 44);
        SC_NP_CUSTOM_PARTNER_LOGO = new SCNPMetadataType("SC_NP_CUSTOM_PARTNER_LOGO", 45);
        SC_NP_CUSTOM_PARTNER_NAME = new SCNPMetadataType("SC_NP_CUSTOM_PARTNER_NAME", 46);
        SC_NP_META_MAX = new SCNPMetadataType("SC_NP_META_MAX", 47);
        SCNPMetadataType ascnpmetadatatype[] = new SCNPMetadataType[48];
        ascnpmetadatatype[0] = SC_NP_META_SIMPLE_STRING_1;
        ascnpmetadatatype[1] = SC_NP_META_SIMPLE_STRING_2;
        ascnpmetadatatype[2] = SC_NP_META_SIMPLE_STRING_3;
        ascnpmetadatatype[3] = SC_NP_META_SIMPLE_STRING_4;
        ascnpmetadatatype[4] = SC_NP_META_SIMPLE_STRING_5;
        ascnpmetadatatype[5] = SC_NP_META_SIMPLE_STRING_6;
        ascnpmetadatatype[6] = SC_NP_META_SIMPLE_STRING_7;
        ascnpmetadatatype[7] = SC_NP_META_SIMPLE_STRING_8;
        ascnpmetadatatype[8] = SC_NP_META_SIMPLE_STRING_9;
        ascnpmetadatatype[9] = SC_NP_META_COMPOUND_STRING_4;
        ascnpmetadatatype[10] = SC_NP_META_BROWSE_STRING;
        ascnpmetadatatype[11] = SC_NP_META_TOOLTIP_1;
        ascnpmetadatatype[12] = SC_NP_META_TOOLTIP_2;
        ascnpmetadatatype[13] = SC_NP_META_TOOLTIP_3;
        ascnpmetadatatype[14] = SC_NP_META_TOOLTIP_4;
        ascnpmetadatatype[15] = SC_NP_META_TRACK_NAME;
        ascnpmetadatatype[16] = SC_NP_META_ARTIST_NAME;
        ascnpmetadatatype[17] = SC_NP_META_ALBUM_NAME;
        ascnpmetadatatype[18] = SC_NP_META_ALBUM_ART_URI;
        ascnpmetadatatype[19] = SC_NP_META_GENRE;
        ascnpmetadatatype[20] = SC_NP_META_CREATOR;
        ascnpmetadatatype[21] = SC_NP_META_NARRATOR;
        ascnpmetadatatype[22] = SC_NP_META_LINEIN_SOURCE;
        ascnpmetadatatype[23] = SC_NP_META_LINEIN_ZONEPLAYER_CONNECTION;
        ascnpmetadatatype[24] = SC_NP_META_LINEIN_ICON;
        ascnpmetadatatype[25] = SC_NP_META_RADIO_STATION_NAME;
        ascnpmetadatatype[26] = SC_NP_META_RADIO_SHOW_NAME;
        ascnpmetadatatype[27] = SC_NP_META_RADIO_STREAM_INFO;
        ascnpmetadatatype[28] = SC_NP_META_TRACK_TIME_ELAPSED;
        ascnpmetadatatype[29] = SC_NP_META_TRACK_DURATION;
        ascnpmetadatatype[30] = SC_NP_META_NUMBER_OF_TRACKS;
        ascnpmetadatatype[31] = SC_NP_META_CURRENT_TRACK;
        ascnpmetadatatype[32] = SC_NP_META_SONOS_PROGRADIO_NAME;
        ascnpmetadatatype[33] = SC_NP_META_NUM_SECTIONS;
        ascnpmetadatatype[34] = SC_NP_META_CURRENT_SECTION;
        ascnpmetadatatype[35] = SC_NP_META_CHANNEL_TITLE;
        ascnpmetadatatype[36] = SC_NP_META_CHANNEL_INFO1;
        ascnpmetadatatype[37] = SC_NP_META_CHANNEL_INFO2;
        ascnpmetadatatype[38] = SC_NP_META_SUBSCRIPTION_LEVEL;
        ascnpmetadatatype[39] = SC_NP_META_IPOD_NAME;
        ascnpmetadatatype[40] = SC_NP_META_DOCK_NAME;
        ascnpmetadatatype[41] = SC_NP_META_ALARM_RUNNING;
        ascnpmetadatatype[42] = SC_NP_META_SNOOZE_RUNNING;
        ascnpmetadatatype[43] = SC_NP_META_SLEEP_TIMER_GENERATION;
        ascnpmetadatatype[44] = SC_NP_META_STREAMINFO;
        ascnpmetadatatype[45] = SC_NP_CUSTOM_PARTNER_LOGO;
        ascnpmetadatatype[46] = SC_NP_CUSTOM_PARTNER_NAME;
        ascnpmetadatatype[47] = SC_NP_META_MAX;
        $VALUES = ascnpmetadatatype;
    }
}
