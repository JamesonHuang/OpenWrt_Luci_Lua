// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;

// Referenced classes of package android.support.v4.accessibilityservice:
//            AccessibilityServiceInfoCompatJellyBeanMr2, AccessibilityServiceInfoCompatIcs

public class AccessibilityServiceInfoCompat
{
    static class AccessibilityServiceInfoJellyBeanMr2 extends AccessibilityServiceInfoIcsImpl
    {

        public int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return AccessibilityServiceInfoCompatJellyBeanMr2.getCapabilities(accessibilityserviceinfo);
        }

        AccessibilityServiceInfoJellyBeanMr2()
        {
        }
    }

    static class AccessibilityServiceInfoIcsImpl extends AccessibilityServiceInfoStubImpl
    {

        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(accessibilityserviceinfo);
        }

        public int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            int i;
            if(getCanRetrieveWindowContent(accessibilityserviceinfo))
                i = 1;
            else
                i = 0;
            return i;
        }

        public String getDescription(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return AccessibilityServiceInfoCompatIcs.getDescription(accessibilityserviceinfo);
        }

        public String getId(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return AccessibilityServiceInfoCompatIcs.getId(accessibilityserviceinfo);
        }

        public ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return AccessibilityServiceInfoCompatIcs.getResolveInfo(accessibilityserviceinfo);
        }

        public String getSettingsActivityName(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(accessibilityserviceinfo);
        }

        AccessibilityServiceInfoIcsImpl()
        {
        }
    }

    static class AccessibilityServiceInfoStubImpl
        implements AccessibilityServiceInfoVersionImpl
    {

        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return false;
        }

        public int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return 0;
        }

        public String getDescription(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return null;
        }

        public String getId(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return null;
        }

        public ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return null;
        }

        public String getSettingsActivityName(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return null;
        }

        AccessibilityServiceInfoStubImpl()
        {
        }
    }

    static interface AccessibilityServiceInfoVersionImpl
    {

        public abstract boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityserviceinfo);

        public abstract int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo);

        public abstract String getDescription(AccessibilityServiceInfo accessibilityserviceinfo);

        public abstract String getId(AccessibilityServiceInfo accessibilityserviceinfo);

        public abstract ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityserviceinfo);

        public abstract String getSettingsActivityName(AccessibilityServiceInfo accessibilityserviceinfo);
    }


    private AccessibilityServiceInfoCompat()
    {
    }

    public static String capabilityToString(int i)
    {
        i;
        JVM INSTR tableswitch 1 8: default 48
    //                   1 53
    //                   2 59
    //                   3 48
    //                   4 65
    //                   5 48
    //                   6 48
    //                   7 48
    //                   8 71;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L1:
        String s = "UNKNOWN";
_L7:
        return s;
_L2:
        s = "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "CAPABILITY_CAN_FILTER_KEY_EVENTS";
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static String feedbackTypeToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        do
            if(i > 0)
            {
                int j = 1 << Integer.numberOfTrailingZeros(i);
                i &= ~j;
                if(stringbuilder.length() > 1)
                    stringbuilder.append(", ");
                switch(j)
                {
                case 1: // '\001'
                    stringbuilder.append("FEEDBACK_SPOKEN");
                    break;

                case 4: // '\004'
                    stringbuilder.append("FEEDBACK_AUDIBLE");
                    break;

                case 2: // '\002'
                    stringbuilder.append("FEEDBACK_HAPTIC");
                    break;

                case 16: // '\020'
                    stringbuilder.append("FEEDBACK_GENERIC");
                    break;

                case 8: // '\b'
                    stringbuilder.append("FEEDBACK_VISUAL");
                    break;
                }
            } else
            {
                stringbuilder.append("]");
                return stringbuilder.toString();
            }
        while(true);
    }

    public static String flagToString(int i)
    {
        i;
        JVM INSTR lookupswitch 6: default 60
    //                   1: 64
    //                   2: 70
    //                   4: 76
    //                   8: 82
    //                   16: 88
    //                   32: 94;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        String s = null;
_L9:
        return s;
_L2:
        s = "DEFAULT";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "FLAG_REPORT_VIEW_IDS";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "FLAG_REQUEST_FILTER_KEY_EVENTS";
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return IMPL.getCanRetrieveWindowContent(accessibilityserviceinfo);
    }

    public static int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return IMPL.getCapabilities(accessibilityserviceinfo);
    }

    public static String getDescription(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return IMPL.getDescription(accessibilityserviceinfo);
    }

    public static String getId(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return IMPL.getId(accessibilityserviceinfo);
    }

    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return IMPL.getResolveInfo(accessibilityserviceinfo);
    }

    public static String getSettingsActivityName(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return IMPL.getSettingsActivityName(accessibilityserviceinfo);
    }

    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoVersionImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 18)
            IMPL = new AccessibilityServiceInfoJellyBeanMr2();
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new AccessibilityServiceInfoIcsImpl();
        else
            IMPL = new AccessibilityServiceInfoStubImpl();
    }
}
