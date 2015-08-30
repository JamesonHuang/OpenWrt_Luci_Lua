// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.util.Xml;
import java.io.IOException;
import java.io.StringWriter;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package com.sonos.acr.util:
//            SLog

public class SonosQueuePlaybackPolicy
{
    public static final class CacheOnPausePolicyMode extends Enum
    {

        public static CacheOnPausePolicyMode valueOf(String s)
        {
            return (CacheOnPausePolicyMode)Enum.valueOf(com/sonos/acr/util/SonosQueuePlaybackPolicy$CacheOnPausePolicyMode, s);
        }

        public static CacheOnPausePolicyMode[] values()
        {
            return (CacheOnPausePolicyMode[])$VALUES.clone();
        }

        private static final CacheOnPausePolicyMode $VALUES[];
        public static final CacheOnPausePolicyMode Disabled;
        public static final CacheOnPausePolicyMode Enabled;
        public static final CacheOnPausePolicyMode Enabled_FullTrackOnly;

        static 
        {
            Disabled = new CacheOnPausePolicyMode("Disabled", 0);
            Enabled = new CacheOnPausePolicyMode("Enabled", 1);
            Enabled_FullTrackOnly = new CacheOnPausePolicyMode("Enabled_FullTrackOnly", 2);
            CacheOnPausePolicyMode acacheonpausepolicymode[] = new CacheOnPausePolicyMode[3];
            acacheonpausepolicymode[0] = Disabled;
            acacheonpausepolicymode[1] = Enabled;
            acacheonpausepolicymode[2] = Enabled_FullTrackOnly;
            $VALUES = acacheonpausepolicymode;
        }

        private CacheOnPausePolicyMode(String s, int i)
        {
            super(s, i);
        }
    }


    public SonosQueuePlaybackPolicy(boolean flag, boolean flag1, CacheOnPausePolicyMode cacheonpausepolicymode, boolean flag2, boolean flag3)
    {
        allowShuffle = flag;
        allowRepeat = flag1;
        cacheOnPause = cacheonpausepolicymode;
        stopOnError = flag2;
        clearOnEnd = flag3;
    }

    private void addTagWithAttribute(XmlSerializer xmlserializer, String s, String s1, String s2)
        throws IOException
    {
        xmlserializer.startTag("", s);
        xmlserializer.attribute("", s1, s2);
        xmlserializer.endTag("", s);
    }

    public String toString()
    {
        if(xmlFragment != null) goto _L2; else goto _L1
_L1:
        XmlSerializer xmlserializer;
        StringWriter stringwriter;
        xmlserializer = Xml.newSerializer();
        stringwriter = new StringWriter();
        xmlserializer.setOutput(stringwriter);
        xmlserializer.startTag("", "PlaybackPolicy");
        if(!allowShuffle) goto _L4; else goto _L3
_L3:
        String s = "1";
_L13:
        addTagWithAttribute(xmlserializer, "allowShuffle", "value", s);
        if(!allowRepeat) goto _L6; else goto _L5
_L5:
        String s1 = "1";
_L17:
        String s2;
        addTagWithAttribute(xmlserializer, "allowRepeat", "value", s1);
        xmlserializer.startTag("", "cacheOnPause");
        if(cacheOnPause != CacheOnPausePolicyMode.Disabled)
            break MISSING_BLOCK_LABEL_322;
        s2 = "0";
_L18:
        xmlserializer.attribute("", "value", s2);
        if(cacheOnPause != CacheOnPausePolicyMode.Enabled_FullTrackOnly) goto _L8; else goto _L7
_L7:
        xmlserializer.attribute("", "fullTrackOnly", "1");
_L14:
        xmlserializer.endTag("", "cacheOnPause");
        if(!stopOnError) goto _L10; else goto _L9
_L9:
        String s3 = "1";
_L15:
        addTagWithAttribute(xmlserializer, "stopOnError", "value", s3);
        if(!clearOnEnd) goto _L12; else goto _L11
_L11:
        String s4 = "1";
_L16:
        addTagWithAttribute(xmlserializer, "clearOnEnd", "value", s4);
        xmlserializer.endTag("", "PlaybackPolicy");
        xmlserializer.flush();
        xmlFragment = stringwriter.toString();
_L2:
        return xmlFragment;
_L4:
        s = "0";
          goto _L13
_L8:
        if(cacheOnPause == CacheOnPausePolicyMode.Enabled)
            xmlserializer.attribute("", "fullTrackOnly", "0");
          goto _L14
        IOException ioexception;
        ioexception;
        SLog.e("ZoneGroup", (new StringBuilder()).append("Error rendering SonosQueuePlaybackPolicy to XML: ").append(ioexception.toString()).toString());
        ioexception.printStackTrace();
        xmlFragment = "";
          goto _L2
_L10:
        s3 = "0";
          goto _L15
_L12:
        s4 = "0";
          goto _L16
_L6:
        s1 = "0";
          goto _L17
        s2 = "1";
          goto _L18
    }

    private static final String ALLOW_REPEAT_TAG = "allowRepeat";
    private static final String ALLOW_SHUFFLE_TAG = "allowShuffle";
    private static final String CACHE_ON_PAUSE_TAG = "cacheOnPause";
    private static final String CLEAR_ON_END_TAG = "clearOnEnd";
    private static final String FULLTRACKONLY_ATTRIBUTE = "fullTrackOnly";
    private static final String LOG_TAG = "SonosQueuePlaybackPolicy";
    private static final String PLAYBACK_POLICY_TAG = "PlaybackPolicy";
    private static final String STOP_ON_ERROR_TAG = "stopOnError";
    private static final String VALUE_ATTRIBUTE = "value";
    public final boolean allowRepeat;
    public final boolean allowShuffle;
    public final CacheOnPausePolicyMode cacheOnPause;
    public final boolean clearOnEnd;
    public final boolean stopOnError;
    private String xmlFragment;
}
