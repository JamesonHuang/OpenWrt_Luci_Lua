// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIPropertyBag

public class SCIAppReporting extends SCIObj
{
    public static final class SCViewComponentID extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$202(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$208()
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


        public static SCViewComponentID swigToEnum(int i)
        {
            SCViewComponentID ascviewcomponentid[] = (SCViewComponentID[])com/sonos/sclib/SCIAppReporting$SCViewComponentID.getEnumConstants();
            if(i >= ascviewcomponentid.length || i < 0 || ascviewcomponentid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCViewComponentID scviewcomponentid1 = ascviewcomponentid[i];
_L4:
            return scviewcomponentid1;
_L2:
            int j = ascviewcomponentid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCViewComponentID scviewcomponentid = ascviewcomponentid[k];
                if(scviewcomponentid.swigValue == i)
                {
                    scviewcomponentid1 = scviewcomponentid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIAppReporting$SCViewComponentID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCViewComponentID valueOf(String s)
        {
            return (SCViewComponentID)Enum.valueOf(com/sonos/sclib/SCIAppReporting$SCViewComponentID, s);
        }

        public static SCViewComponentID[] values()
        {
            return (SCViewComponentID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCViewComponentID $VALUES[];
        public static final SCViewComponentID VC_LOCATION_SVC;
        public static final SCViewComponentID VC_LOCKSCREEN;
        public static final SCViewComponentID VC_MUTE;
        public static final SCViewComponentID VC_NEXT;
        public static final SCViewComponentID VC_NIGHT_MODE;
        public static final SCViewComponentID VC_NONE;
        public static final SCViewComponentID VC_NOTIFICATIONS;
        public static final SCViewComponentID VC_PLAYBACK;
        public static final SCViewComponentID VC_PREV;
        public static final SCViewComponentID VC_RATINGS;
        public static final SCViewComponentID VC_ROOM;
        public static final SCViewComponentID VC_SEEK;
        public static final SCViewComponentID VC_TV_DIALOG_ENH;
        public static final SCViewComponentID VC_VOLUME;
        private final int swigValue;

        static 
        {
            VC_NONE = new SCViewComponentID("VC_NONE", 0);
            VC_PLAYBACK = new SCViewComponentID("VC_PLAYBACK", 1);
            VC_VOLUME = new SCViewComponentID("VC_VOLUME", 2);
            VC_MUTE = new SCViewComponentID("VC_MUTE", 3);
            VC_NEXT = new SCViewComponentID("VC_NEXT", 4);
            VC_PREV = new SCViewComponentID("VC_PREV", 5);
            VC_SEEK = new SCViewComponentID("VC_SEEK", 6);
            VC_RATINGS = new SCViewComponentID("VC_RATINGS", 7);
            VC_ROOM = new SCViewComponentID("VC_ROOM", 8);
            VC_NIGHT_MODE = new SCViewComponentID("VC_NIGHT_MODE", 9);
            VC_TV_DIALOG_ENH = new SCViewComponentID("VC_TV_DIALOG_ENH", 10);
            VC_LOCKSCREEN = new SCViewComponentID("VC_LOCKSCREEN", 11);
            VC_LOCATION_SVC = new SCViewComponentID("VC_LOCATION_SVC", 12);
            VC_NOTIFICATIONS = new SCViewComponentID("VC_NOTIFICATIONS", 13);
            SCViewComponentID ascviewcomponentid[] = new SCViewComponentID[14];
            ascviewcomponentid[0] = VC_NONE;
            ascviewcomponentid[1] = VC_PLAYBACK;
            ascviewcomponentid[2] = VC_VOLUME;
            ascviewcomponentid[3] = VC_MUTE;
            ascviewcomponentid[4] = VC_NEXT;
            ascviewcomponentid[5] = VC_PREV;
            ascviewcomponentid[6] = VC_SEEK;
            ascviewcomponentid[7] = VC_RATINGS;
            ascviewcomponentid[8] = VC_ROOM;
            ascviewcomponentid[9] = VC_NIGHT_MODE;
            ascviewcomponentid[10] = VC_TV_DIALOG_ENH;
            ascviewcomponentid[11] = VC_LOCKSCREEN;
            ascviewcomponentid[12] = VC_LOCATION_SVC;
            ascviewcomponentid[13] = VC_NOTIFICATIONS;
            $VALUES = ascviewcomponentid;
        }

        private SCViewComponentID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCViewComponentID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCViewComponentID(String s, int i, SCViewComponentID scviewcomponentid)
        {
            Enum(s, i);
            swigValue = scviewcomponentid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SCInteractionID extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$102(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$108()
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


        public static SCInteractionID swigToEnum(int i)
        {
            SCInteractionID ascinteractionid[] = (SCInteractionID[])com/sonos/sclib/SCIAppReporting$SCInteractionID.getEnumConstants();
            if(i >= ascinteractionid.length || i < 0 || ascinteractionid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCInteractionID scinteractionid1 = ascinteractionid[i];
_L4:
            return scinteractionid1;
_L2:
            int j = ascinteractionid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCInteractionID scinteractionid = ascinteractionid[k];
                if(scinteractionid.swigValue == i)
                {
                    scinteractionid1 = scinteractionid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIAppReporting$SCInteractionID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCInteractionID valueOf(String s)
        {
            return (SCInteractionID)Enum.valueOf(com/sonos/sclib/SCIAppReporting$SCInteractionID, s);
        }

        public static SCInteractionID[] values()
        {
            return (SCInteractionID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCInteractionID $VALUES[];
        public static final SCInteractionID ACTIVATED;
        public static final SCInteractionID DEACTIVATED;
        public static final SCInteractionID DOUBLE_TAP;
        public static final SCInteractionID EXTERNAL;
        public static final SCInteractionID IGNORED;
        public static final SCInteractionID LONG_PRESS;
        public static final SCInteractionID SWIPE;
        public static final SCInteractionID TAP;
        private final int swigValue;

        static 
        {
            TAP = new SCInteractionID("TAP", 0);
            DOUBLE_TAP = new SCInteractionID("DOUBLE_TAP", 1);
            LONG_PRESS = new SCInteractionID("LONG_PRESS", 2);
            SWIPE = new SCInteractionID("SWIPE", 3);
            EXTERNAL = new SCInteractionID("EXTERNAL", 4);
            ACTIVATED = new SCInteractionID("ACTIVATED", 5);
            DEACTIVATED = new SCInteractionID("DEACTIVATED", 6);
            IGNORED = new SCInteractionID("IGNORED", 7);
            SCInteractionID ascinteractionid[] = new SCInteractionID[8];
            ascinteractionid[0] = TAP;
            ascinteractionid[1] = DOUBLE_TAP;
            ascinteractionid[2] = LONG_PRESS;
            ascinteractionid[3] = SWIPE;
            ascinteractionid[4] = EXTERNAL;
            ascinteractionid[5] = ACTIVATED;
            ascinteractionid[6] = DEACTIVATED;
            ascinteractionid[7] = IGNORED;
            $VALUES = ascinteractionid;
        }

        private SCInteractionID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCInteractionID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCInteractionID(String s, int i, SCInteractionID scinteractionid)
        {
            Enum(s, i);
            swigValue = scinteractionid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SCViewID extends Enum
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


        public static SCViewID swigToEnum(int i)
        {
            SCViewID ascviewid[] = (SCViewID[])com/sonos/sclib/SCIAppReporting$SCViewID.getEnumConstants();
            if(i >= ascviewid.length || i < 0 || ascviewid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCViewID scviewid1 = ascviewid[i];
_L4:
            return scviewid1;
_L2:
            int j = ascviewid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCViewID scviewid = ascviewid[k];
                if(scviewid.swigValue == i)
                {
                    scviewid1 = scviewid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIAppReporting$SCViewID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCViewID valueOf(String s)
        {
            return (SCViewID)Enum.valueOf(com/sonos/sclib/SCIAppReporting$SCViewID, s);
        }

        public static SCViewID[] values()
        {
            return (SCViewID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCViewID $VALUES[];
        public static final SCViewID ABOUT;
        public static final SCViewID BROWSE;
        public static final SCViewID COLLAPSED_QUEUE;
        public static final SCViewID EDIT_ROOMS;
        public static final SCViewID EXPANDED_QUEUE;
        public static final SCViewID LOCKSCREEN;
        public static final SCViewID MAIN_MENU;
        public static final SCViewID MINI_PLAYER;
        public static final SCViewID MORE_MENU;
        public static final SCViewID NONE;
        public static final SCViewID NOTIFICATIONS;
        public static final SCViewID NOW_PLAYING;
        public static final SCViewID QUEUE;
        public static final SCViewID ROOMS;
        public static final SCViewID SEARCH_BOX;
        public static final SCViewID SEARCH_MORE;
        public static final SCViewID SETTINGS;
        public static final SCViewID SONOS_FAVORITES;
        public static final SCViewID WIDGET;
        public static final SCViewID WIZARD;
        private final int swigValue;

        static 
        {
            NONE = new SCViewID("NONE", 0);
            ABOUT = new SCViewID("ABOUT", 1);
            BROWSE = new SCViewID("BROWSE", 2);
            COLLAPSED_QUEUE = new SCViewID("COLLAPSED_QUEUE", 3);
            EDIT_ROOMS = new SCViewID("EDIT_ROOMS", 4);
            EXPANDED_QUEUE = new SCViewID("EXPANDED_QUEUE", 5);
            LOCKSCREEN = new SCViewID("LOCKSCREEN", 6);
            MAIN_MENU = new SCViewID("MAIN_MENU", 7);
            MINI_PLAYER = new SCViewID("MINI_PLAYER", 8);
            MORE_MENU = new SCViewID("MORE_MENU", 9);
            NOTIFICATIONS = new SCViewID("NOTIFICATIONS", 10);
            NOW_PLAYING = new SCViewID("NOW_PLAYING", 11);
            QUEUE = new SCViewID("QUEUE", 12);
            ROOMS = new SCViewID("ROOMS", 13);
            SEARCH_BOX = new SCViewID("SEARCH_BOX", 14);
            SEARCH_MORE = new SCViewID("SEARCH_MORE", 15);
            SETTINGS = new SCViewID("SETTINGS", 16);
            SONOS_FAVORITES = new SCViewID("SONOS_FAVORITES", 17);
            WIDGET = new SCViewID("WIDGET", 18);
            WIZARD = new SCViewID("WIZARD", 19);
            SCViewID ascviewid[] = new SCViewID[20];
            ascviewid[0] = NONE;
            ascviewid[1] = ABOUT;
            ascviewid[2] = BROWSE;
            ascviewid[3] = COLLAPSED_QUEUE;
            ascviewid[4] = EDIT_ROOMS;
            ascviewid[5] = EXPANDED_QUEUE;
            ascviewid[6] = LOCKSCREEN;
            ascviewid[7] = MAIN_MENU;
            ascviewid[8] = MINI_PLAYER;
            ascviewid[9] = MORE_MENU;
            ascviewid[10] = NOTIFICATIONS;
            ascviewid[11] = NOW_PLAYING;
            ascviewid[12] = QUEUE;
            ascviewid[13] = ROOMS;
            ascviewid[14] = SEARCH_BOX;
            ascviewid[15] = SEARCH_MORE;
            ascviewid[16] = SETTINGS;
            ascviewid[17] = SONOS_FAVORITES;
            ascviewid[18] = WIDGET;
            ascviewid[19] = WIZARD;
            $VALUES = ascviewid;
        }

        private SCViewID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCViewID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCViewID(String s, int i, SCViewID scviewid)
        {
            Enum(s, i);
            swigValue = scviewid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIAppReporting(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAppReportingUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAppReporting(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAppReporting(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAppReporting sciappreporting)
    {
        long l;
        if(sciappreporting == null)
            l = 0L;
        else
            l = sciappreporting.swigCPtr;
        return l;
    }

    public void background()
    {
        sclibJNI.SCIAppReporting_background(swigCPtr, this);
    }

    /**
     * @deprecated Method dispose is deprecated
     */

    public void dispose()
    {
        this;
        JVM INSTR monitorenter ;
        swigCPtr = 0L;
        dispose();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void focus(SCViewID scviewid)
    {
        sclibJNI.SCIAppReporting_focus(swigCPtr, this, scviewid.swigValue());
    }

    public void foreground()
    {
        sclibJNI.SCIAppReporting_foreground(swigCPtr, this);
    }

    public void interaction(SCInteractionID scinteractionid, SCViewID scviewid, SCViewComponentID scviewcomponentid)
    {
        sclibJNI.SCIAppReporting_interaction(swigCPtr, this, scinteractionid.swigValue(), scviewid.swigValue(), scviewcomponentid.swigValue());
    }

    public void reportEventWithProps(String s, String s1, SCIPropertyBag scipropertybag)
    {
        sclibJNI.SCIAppReporting_reportEventWithProps(swigCPtr, this, s, s1, SCIPropertyBag.getCPtr(scipropertybag), scipropertybag);
    }

    public void updateScreenResolution(int i, int j)
    {
        sclibJNI.SCIAppReporting_updateScreenResolution(swigCPtr, this, i, j);
    }

    public void viewClose(SCViewID scviewid)
    {
        sclibJNI.SCIAppReporting_viewClose(swigCPtr, this, scviewid.swigValue());
    }

    public void viewOpen(SCViewID scviewid)
    {
        sclibJNI.SCIAppReporting_viewOpen(swigCPtr, this, scviewid.swigValue());
    }

    public void viewPropChange(SCViewID scviewid, int i, int j, int k, int l)
    {
        sclibJNI.SCIAppReporting_viewPropChange(swigCPtr, this, scviewid.swigValue(), i, j, k, l);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAppReporting");
    private long swigCPtr;

}
