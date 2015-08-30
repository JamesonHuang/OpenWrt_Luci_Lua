// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCNPSourceType extends Enum
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


    private SCNPSourceType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNPSourceType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNPSourceType(String s, int i, SCNPSourceType scnpsourcetype)
    {
        Enum(s, i);
        swigValue = scnpsourcetype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNPSourceType swigToEnum(int i)
    {
        SCNPSourceType ascnpsourcetype[] = (SCNPSourceType[])com/sonos/sclib/SCNPSourceType.getEnumConstants();
        if(i >= ascnpsourcetype.length || i < 0 || ascnpsourcetype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNPSourceType scnpsourcetype1 = ascnpsourcetype[i];
_L4:
        return scnpsourcetype1;
_L2:
        int j = ascnpsourcetype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNPSourceType scnpsourcetype = ascnpsourcetype[k];
            if(scnpsourcetype.swigValue == i)
            {
                scnpsourcetype1 = scnpsourcetype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNPSourceType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNPSourceType valueOf(String s)
    {
        return (SCNPSourceType)Enum.valueOf(com/sonos/sclib/SCNPSourceType, s);
    }

    public static SCNPSourceType[] values()
    {
        return (SCNPSourceType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNPSourceType $VALUES[];
    public static final SCNPSourceType SC_NP_TYPE_HLS;
    public static final SCNPSourceType SC_NP_TYPE_HT_AUDIO_SOURCE;
    public static final SCNPSourceType SC_NP_TYPE_INTERNET_RADIO;
    public static final SCNPSourceType SC_NP_TYPE_LASTFM_RADIO;
    public static final SCNPSourceType SC_NP_TYPE_NONE;
    public static final SCNPSourceType SC_NP_TYPE_NO_SOURCE;
    public static final SCNPSourceType SC_NP_TYPE_OTHER;
    public static final SCNPSourceType SC_NP_TYPE_PANDORA_RADIO;
    public static final SCNPSourceType SC_NP_TYPE_QUEUE;
    public static final SCNPSourceType SC_NP_TYPE_QUEUE_AUDIBLE;
    public static final SCNPSourceType SC_NP_TYPE_QUEUE_PRIVATE;
    public static final SCNPSourceType SC_NP_TYPE_QUEUE_SHOW;
    public static final SCNPSourceType SC_NP_TYPE_RHAPSODY_RADIO;
    public static final SCNPSourceType SC_NP_TYPE_SONOS_ALARM;
    public static final SCNPSourceType SC_NP_TYPE_SONOS_DOCK;
    public static final SCNPSourceType SC_NP_TYPE_SONOS_DOCK_ZP;
    public static final SCNPSourceType SC_NP_TYPE_SONOS_IQRADIO;
    public static final SCNPSourceType SC_NP_TYPE_SONOS_LINE_IN;
    public static final SCNPSourceType SC_NP_TYPE_SONOS_PROGRADIO;
    public static final SCNPSourceType SC_NP_TYPE_UNKNOWN;
    private final int swigValue;

    static 
    {
        SC_NP_TYPE_NONE = new SCNPSourceType("SC_NP_TYPE_NONE", 0, sclibJNI.SC_NP_TYPE_NONE_get());
        SC_NP_TYPE_NO_SOURCE = new SCNPSourceType("SC_NP_TYPE_NO_SOURCE", 1, sclibJNI.SC_NP_TYPE_NO_SOURCE_get());
        SC_NP_TYPE_QUEUE = new SCNPSourceType("SC_NP_TYPE_QUEUE", 2, sclibJNI.SC_NP_TYPE_QUEUE_get());
        SC_NP_TYPE_INTERNET_RADIO = new SCNPSourceType("SC_NP_TYPE_INTERNET_RADIO", 3, sclibJNI.SC_NP_TYPE_INTERNET_RADIO_get());
        SC_NP_TYPE_RHAPSODY_RADIO = new SCNPSourceType("SC_NP_TYPE_RHAPSODY_RADIO", 4, sclibJNI.SC_NP_TYPE_RHAPSODY_RADIO_get());
        SC_NP_TYPE_SONOS_ALARM = new SCNPSourceType("SC_NP_TYPE_SONOS_ALARM", 5, sclibJNI.SC_NP_TYPE_SONOS_ALARM_get());
        SC_NP_TYPE_PANDORA_RADIO = new SCNPSourceType("SC_NP_TYPE_PANDORA_RADIO", 6, sclibJNI.SC_NP_TYPE_PANDORA_RADIO_get());
        SC_NP_TYPE_HLS = new SCNPSourceType("SC_NP_TYPE_HLS", 7, sclibJNI.SC_NP_TYPE_HLS_get());
        SC_NP_TYPE_LASTFM_RADIO = new SCNPSourceType("SC_NP_TYPE_LASTFM_RADIO", 8, sclibJNI.SC_NP_TYPE_LASTFM_RADIO_get());
        SC_NP_TYPE_SONOS_LINE_IN = new SCNPSourceType("SC_NP_TYPE_SONOS_LINE_IN", 9, sclibJNI.SC_NP_TYPE_SONOS_LINE_IN_get());
        SC_NP_TYPE_SONOS_PROGRADIO = new SCNPSourceType("SC_NP_TYPE_SONOS_PROGRADIO", 10, sclibJNI.SC_NP_TYPE_SONOS_PROGRADIO_get());
        SC_NP_TYPE_SONOS_DOCK_ZP = new SCNPSourceType("SC_NP_TYPE_SONOS_DOCK_ZP", 11, sclibJNI.SC_NP_TYPE_SONOS_DOCK_ZP_get());
        SC_NP_TYPE_SONOS_DOCK = new SCNPSourceType("SC_NP_TYPE_SONOS_DOCK", 12, sclibJNI.SC_NP_TYPE_SONOS_DOCK_get());
        SC_NP_TYPE_HT_AUDIO_SOURCE = new SCNPSourceType("SC_NP_TYPE_HT_AUDIO_SOURCE", 13, sclibJNI.SC_NP_TYPE_HT_AUDIO_SOURCE_get());
        SC_NP_TYPE_SONOS_IQRADIO = new SCNPSourceType("SC_NP_TYPE_SONOS_IQRADIO", 14, sclibJNI.SC_NP_TYPE_SONOS_IQRADIO_get());
        SC_NP_TYPE_UNKNOWN = new SCNPSourceType("SC_NP_TYPE_UNKNOWN", 15);
        SC_NP_TYPE_QUEUE_AUDIBLE = new SCNPSourceType("SC_NP_TYPE_QUEUE_AUDIBLE", 16);
        SC_NP_TYPE_QUEUE_SHOW = new SCNPSourceType("SC_NP_TYPE_QUEUE_SHOW", 17);
        SC_NP_TYPE_QUEUE_PRIVATE = new SCNPSourceType("SC_NP_TYPE_QUEUE_PRIVATE", 18);
        SC_NP_TYPE_OTHER = new SCNPSourceType("SC_NP_TYPE_OTHER", 19);
        SCNPSourceType ascnpsourcetype[] = new SCNPSourceType[20];
        ascnpsourcetype[0] = SC_NP_TYPE_NONE;
        ascnpsourcetype[1] = SC_NP_TYPE_NO_SOURCE;
        ascnpsourcetype[2] = SC_NP_TYPE_QUEUE;
        ascnpsourcetype[3] = SC_NP_TYPE_INTERNET_RADIO;
        ascnpsourcetype[4] = SC_NP_TYPE_RHAPSODY_RADIO;
        ascnpsourcetype[5] = SC_NP_TYPE_SONOS_ALARM;
        ascnpsourcetype[6] = SC_NP_TYPE_PANDORA_RADIO;
        ascnpsourcetype[7] = SC_NP_TYPE_HLS;
        ascnpsourcetype[8] = SC_NP_TYPE_LASTFM_RADIO;
        ascnpsourcetype[9] = SC_NP_TYPE_SONOS_LINE_IN;
        ascnpsourcetype[10] = SC_NP_TYPE_SONOS_PROGRADIO;
        ascnpsourcetype[11] = SC_NP_TYPE_SONOS_DOCK_ZP;
        ascnpsourcetype[12] = SC_NP_TYPE_SONOS_DOCK;
        ascnpsourcetype[13] = SC_NP_TYPE_HT_AUDIO_SOURCE;
        ascnpsourcetype[14] = SC_NP_TYPE_SONOS_IQRADIO;
        ascnpsourcetype[15] = SC_NP_TYPE_UNKNOWN;
        ascnpsourcetype[16] = SC_NP_TYPE_QUEUE_AUDIBLE;
        ascnpsourcetype[17] = SC_NP_TYPE_QUEUE_SHOW;
        ascnpsourcetype[18] = SC_NP_TYPE_QUEUE_PRIVATE;
        ascnpsourcetype[19] = SC_NP_TYPE_OTHER;
        $VALUES = ascnpsourcetype;
    }
}
