// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIWizard, sclibJNI

public class SCIOnlineUpdateWizard extends SCIWizard
{
    public static final class OnlineUpdateWizStringID extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$302(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$308()
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


        public static OnlineUpdateWizStringID swigToEnum(int i)
        {
            OnlineUpdateWizStringID aonlineupdatewizstringid[] = (OnlineUpdateWizStringID[])com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizStringID.getEnumConstants();
            if(i >= aonlineupdatewizstringid.length || i < 0 || aonlineupdatewizstringid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            OnlineUpdateWizStringID onlineupdatewizstringid1 = aonlineupdatewizstringid[i];
_L4:
            return onlineupdatewizstringid1;
_L2:
            int j = aonlineupdatewizstringid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                OnlineUpdateWizStringID onlineupdatewizstringid = aonlineupdatewizstringid[k];
                if(onlineupdatewizstringid.swigValue == i)
                {
                    onlineupdatewizstringid1 = onlineupdatewizstringid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizStringID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static OnlineUpdateWizStringID valueOf(String s)
        {
            return (OnlineUpdateWizStringID)Enum.valueOf(com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizStringID, s);
        }

        public static OnlineUpdateWizStringID[] values()
        {
            return (OnlineUpdateWizStringID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final OnlineUpdateWizStringID $VALUES[];
        public static final OnlineUpdateWizStringID OUWIZ_STRID_BODY;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_BODY_1;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_BODY_2;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_BODY_3;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_INPUT_1;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_INPUT_2;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_INPUT_3;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_MAX;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_TITLE;
        public static final OnlineUpdateWizStringID OUWIZ_STRID_WIZARD_INSTRUCTIONS;
        private final int swigValue;

        static 
        {
            OUWIZ_STRID_TITLE = new OnlineUpdateWizStringID("OUWIZ_STRID_TITLE", 0, sclibJNI.SCIOnlineUpdateWizard_OUWIZ_STRID_TITLE_get());
            OUWIZ_STRID_BODY = new OnlineUpdateWizStringID("OUWIZ_STRID_BODY", 1, sclibJNI.SCIOnlineUpdateWizard_OUWIZ_STRID_BODY_get());
            OUWIZ_STRID_BODY_1 = new OnlineUpdateWizStringID("OUWIZ_STRID_BODY_1", 2);
            OUWIZ_STRID_BODY_2 = new OnlineUpdateWizStringID("OUWIZ_STRID_BODY_2", 3);
            OUWIZ_STRID_BODY_3 = new OnlineUpdateWizStringID("OUWIZ_STRID_BODY_3", 4);
            OUWIZ_STRID_INPUT_1 = new OnlineUpdateWizStringID("OUWIZ_STRID_INPUT_1", 5);
            OUWIZ_STRID_INPUT_2 = new OnlineUpdateWizStringID("OUWIZ_STRID_INPUT_2", 6);
            OUWIZ_STRID_INPUT_3 = new OnlineUpdateWizStringID("OUWIZ_STRID_INPUT_3", 7);
            OUWIZ_STRID_WIZARD_INSTRUCTIONS = new OnlineUpdateWizStringID("OUWIZ_STRID_WIZARD_INSTRUCTIONS", 8);
            OUWIZ_STRID_MAX = new OnlineUpdateWizStringID("OUWIZ_STRID_MAX", 9);
            OnlineUpdateWizStringID aonlineupdatewizstringid[] = new OnlineUpdateWizStringID[10];
            aonlineupdatewizstringid[0] = OUWIZ_STRID_TITLE;
            aonlineupdatewizstringid[1] = OUWIZ_STRID_BODY;
            aonlineupdatewizstringid[2] = OUWIZ_STRID_BODY_1;
            aonlineupdatewizstringid[3] = OUWIZ_STRID_BODY_2;
            aonlineupdatewizstringid[4] = OUWIZ_STRID_BODY_3;
            aonlineupdatewizstringid[5] = OUWIZ_STRID_INPUT_1;
            aonlineupdatewizstringid[6] = OUWIZ_STRID_INPUT_2;
            aonlineupdatewizstringid[7] = OUWIZ_STRID_INPUT_3;
            aonlineupdatewizstringid[8] = OUWIZ_STRID_WIZARD_INSTRUCTIONS;
            aonlineupdatewizstringid[9] = OUWIZ_STRID_MAX;
            $VALUES = aonlineupdatewizstringid;
        }

        private OnlineUpdateWizStringID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private OnlineUpdateWizStringID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private OnlineUpdateWizStringID(String s, int i, OnlineUpdateWizStringID onlineupdatewizstringid)
        {
            Enum(s, i);
            swigValue = onlineupdatewizstringid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SCIOnlineUpdateDeviceStatusCode extends Enum
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


        public static SCIOnlineUpdateDeviceStatusCode swigToEnum(int i)
        {
            SCIOnlineUpdateDeviceStatusCode ascionlineupdatedevicestatuscode[] = (SCIOnlineUpdateDeviceStatusCode[])com/sonos/sclib/SCIOnlineUpdateWizard$SCIOnlineUpdateDeviceStatusCode.getEnumConstants();
            if(i >= ascionlineupdatedevicestatuscode.length || i < 0 || ascionlineupdatedevicestatuscode[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCIOnlineUpdateDeviceStatusCode scionlineupdatedevicestatuscode1 = ascionlineupdatedevicestatuscode[i];
_L4:
            return scionlineupdatedevicestatuscode1;
_L2:
            int j = ascionlineupdatedevicestatuscode.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCIOnlineUpdateDeviceStatusCode scionlineupdatedevicestatuscode = ascionlineupdatedevicestatuscode[k];
                if(scionlineupdatedevicestatuscode.swigValue == i)
                {
                    scionlineupdatedevicestatuscode1 = scionlineupdatedevicestatuscode;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIOnlineUpdateWizard$SCIOnlineUpdateDeviceStatusCode).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCIOnlineUpdateDeviceStatusCode valueOf(String s)
        {
            return (SCIOnlineUpdateDeviceStatusCode)Enum.valueOf(com/sonos/sclib/SCIOnlineUpdateWizard$SCIOnlineUpdateDeviceStatusCode, s);
        }

        public static SCIOnlineUpdateDeviceStatusCode[] values()
        {
            return (SCIOnlineUpdateDeviceStatusCode[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCIOnlineUpdateDeviceStatusCode $VALUES[];
        public static final SCIOnlineUpdateDeviceStatusCode ONLINEUPDATE_DEVICE_STATUS_DOWNLOADING;
        public static final SCIOnlineUpdateDeviceStatusCode ONLINEUPDATE_DEVICE_STATUS_FLASHING;
        public static final SCIOnlineUpdateDeviceStatusCode ONLINEUPDATE_DEVICE_STATUS_REBOOTING;
        public static final SCIOnlineUpdateDeviceStatusCode ONLINEUPDATE_DEVICE_STATUS_STARTING;
        public static final SCIOnlineUpdateDeviceStatusCode ONLINEUPDATE_DEVICE_STATUS_UNKNOWN;
        public static final SCIOnlineUpdateDeviceStatusCode ONLINEUPDATE_DEVICE_STATUS_UPDATED;
        private final int swigValue;

        static 
        {
            ONLINEUPDATE_DEVICE_STATUS_UNKNOWN = new SCIOnlineUpdateDeviceStatusCode("ONLINEUPDATE_DEVICE_STATUS_UNKNOWN", 0, sclibJNI.SCIOnlineUpdateWizard_ONLINEUPDATE_DEVICE_STATUS_UNKNOWN_get());
            ONLINEUPDATE_DEVICE_STATUS_STARTING = new SCIOnlineUpdateDeviceStatusCode("ONLINEUPDATE_DEVICE_STATUS_STARTING", 1, sclibJNI.SCIOnlineUpdateWizard_ONLINEUPDATE_DEVICE_STATUS_STARTING_get());
            ONLINEUPDATE_DEVICE_STATUS_DOWNLOADING = new SCIOnlineUpdateDeviceStatusCode("ONLINEUPDATE_DEVICE_STATUS_DOWNLOADING", 2);
            ONLINEUPDATE_DEVICE_STATUS_FLASHING = new SCIOnlineUpdateDeviceStatusCode("ONLINEUPDATE_DEVICE_STATUS_FLASHING", 3);
            ONLINEUPDATE_DEVICE_STATUS_REBOOTING = new SCIOnlineUpdateDeviceStatusCode("ONLINEUPDATE_DEVICE_STATUS_REBOOTING", 4);
            ONLINEUPDATE_DEVICE_STATUS_UPDATED = new SCIOnlineUpdateDeviceStatusCode("ONLINEUPDATE_DEVICE_STATUS_UPDATED", 5);
            SCIOnlineUpdateDeviceStatusCode ascionlineupdatedevicestatuscode[] = new SCIOnlineUpdateDeviceStatusCode[6];
            ascionlineupdatedevicestatuscode[0] = ONLINEUPDATE_DEVICE_STATUS_UNKNOWN;
            ascionlineupdatedevicestatuscode[1] = ONLINEUPDATE_DEVICE_STATUS_STARTING;
            ascionlineupdatedevicestatuscode[2] = ONLINEUPDATE_DEVICE_STATUS_DOWNLOADING;
            ascionlineupdatedevicestatuscode[3] = ONLINEUPDATE_DEVICE_STATUS_FLASHING;
            ascionlineupdatedevicestatuscode[4] = ONLINEUPDATE_DEVICE_STATUS_REBOOTING;
            ascionlineupdatedevicestatuscode[5] = ONLINEUPDATE_DEVICE_STATUS_UPDATED;
            $VALUES = ascionlineupdatedevicestatuscode;
        }

        private SCIOnlineUpdateDeviceStatusCode(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCIOnlineUpdateDeviceStatusCode(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCIOnlineUpdateDeviceStatusCode(String s, int i, SCIOnlineUpdateDeviceStatusCode scionlineupdatedevicestatuscode)
        {
            Enum(s, i);
            swigValue = scionlineupdatedevicestatuscode.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class OnlineUpdateWizardState extends Enum
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


        public static OnlineUpdateWizardState swigToEnum(int i)
        {
            OnlineUpdateWizardState aonlineupdatewizardstate[] = (OnlineUpdateWizardState[])com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizardState.getEnumConstants();
            if(i >= aonlineupdatewizardstate.length || i < 0 || aonlineupdatewizardstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            OnlineUpdateWizardState onlineupdatewizardstate1 = aonlineupdatewizardstate[i];
_L4:
            return onlineupdatewizardstate1;
_L2:
            int j = aonlineupdatewizardstate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                OnlineUpdateWizardState onlineupdatewizardstate = aonlineupdatewizardstate[k];
                if(onlineupdatewizardstate.swigValue == i)
                {
                    onlineupdatewizardstate1 = onlineupdatewizardstate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizardState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static OnlineUpdateWizardState valueOf(String s)
        {
            return (OnlineUpdateWizardState)Enum.valueOf(com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizardState, s);
        }

        public static OnlineUpdateWizardState[] values()
        {
            return (OnlineUpdateWizardState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final OnlineUpdateWizardState $VALUES[];
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_CANCELED;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_CHECK_FOR_UPDATES;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_CHOICE;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_COMPLETE;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_CONTROLLER_NEEDS_UPDATING;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_CONTROLLER_SELFUPDATE_SUBWIZ;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_DEVICES_UPGRADED;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_DEVICES_UPGRADE_IN_PROGRESS;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_ERROR;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_ERROR_INFO;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_FINISHED;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_FROM_ZONES_MENU_START;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_INIT;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_INTRODUCTION;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_MAX;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_NOT_REQUIRED;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_NO_INLINE_SELF_UPDATE;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_PENDING;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_POST_UPDATE_REINDEXING_NEEDED;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_REGISTRATION_REQUIRED;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_REGISTRATION_WIZARD;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_UNKNOWN;
        public static final OnlineUpdateWizardState STATE_ONLINEUPDATE_WARNING;
        private final int swigValue;

        static 
        {
            STATE_ONLINEUPDATE_UNKNOWN = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_UNKNOWN", 0, sclibJNI.SCIOnlineUpdateWizard_STATE_ONLINEUPDATE_UNKNOWN_get());
            STATE_ONLINEUPDATE_INIT = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_INIT", 1, sclibJNI.SCIOnlineUpdateWizard_STATE_ONLINEUPDATE_INIT_get());
            STATE_ONLINEUPDATE_COMPLETE = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_COMPLETE", 2, sclibJNI.SCIOnlineUpdateWizard_STATE_ONLINEUPDATE_COMPLETE_get());
            STATE_ONLINEUPDATE_FINISHED = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_FINISHED", 3);
            STATE_ONLINEUPDATE_ERROR = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_ERROR", 4);
            STATE_ONLINEUPDATE_ERROR_INFO = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_ERROR_INFO", 5);
            STATE_ONLINEUPDATE_CANCELED = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_CANCELED", 6);
            STATE_ONLINEUPDATE_CHECK_FOR_UPDATES = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_CHECK_FOR_UPDATES", 7);
            STATE_ONLINEUPDATE_REGISTRATION_REQUIRED = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_REGISTRATION_REQUIRED", 8);
            STATE_ONLINEUPDATE_REGISTRATION_WIZARD = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_REGISTRATION_WIZARD", 9);
            STATE_ONLINEUPDATE_NOT_REQUIRED = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_NOT_REQUIRED", 10);
            STATE_ONLINEUPDATE_INTRODUCTION = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_INTRODUCTION", 11);
            STATE_ONLINEUPDATE_CHOICE = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_CHOICE", 12);
            STATE_ONLINEUPDATE_PENDING = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_PENDING", 13);
            STATE_ONLINEUPDATE_WARNING = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_WARNING", 14);
            STATE_ONLINEUPDATE_DEVICES_UPGRADE_IN_PROGRESS = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_DEVICES_UPGRADE_IN_PROGRESS", 15);
            STATE_ONLINEUPDATE_DEVICES_UPGRADED = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_DEVICES_UPGRADED", 16);
            STATE_ONLINEUPDATE_POST_UPDATE_REINDEXING_NEEDED = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_POST_UPDATE_REINDEXING_NEEDED", 17);
            STATE_ONLINEUPDATE_FROM_ZONES_MENU_START = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_FROM_ZONES_MENU_START", 18);
            STATE_ONLINEUPDATE_CONTROLLER_NEEDS_UPDATING = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_CONTROLLER_NEEDS_UPDATING", 19);
            STATE_ONLINEUPDATE_CONTROLLER_SELFUPDATE_SUBWIZ = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_CONTROLLER_SELFUPDATE_SUBWIZ", 20);
            STATE_ONLINEUPDATE_NO_INLINE_SELF_UPDATE = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_NO_INLINE_SELF_UPDATE", 21);
            STATE_ONLINEUPDATE_MAX = new OnlineUpdateWizardState("STATE_ONLINEUPDATE_MAX", 22);
            OnlineUpdateWizardState aonlineupdatewizardstate[] = new OnlineUpdateWizardState[23];
            aonlineupdatewizardstate[0] = STATE_ONLINEUPDATE_UNKNOWN;
            aonlineupdatewizardstate[1] = STATE_ONLINEUPDATE_INIT;
            aonlineupdatewizardstate[2] = STATE_ONLINEUPDATE_COMPLETE;
            aonlineupdatewizardstate[3] = STATE_ONLINEUPDATE_FINISHED;
            aonlineupdatewizardstate[4] = STATE_ONLINEUPDATE_ERROR;
            aonlineupdatewizardstate[5] = STATE_ONLINEUPDATE_ERROR_INFO;
            aonlineupdatewizardstate[6] = STATE_ONLINEUPDATE_CANCELED;
            aonlineupdatewizardstate[7] = STATE_ONLINEUPDATE_CHECK_FOR_UPDATES;
            aonlineupdatewizardstate[8] = STATE_ONLINEUPDATE_REGISTRATION_REQUIRED;
            aonlineupdatewizardstate[9] = STATE_ONLINEUPDATE_REGISTRATION_WIZARD;
            aonlineupdatewizardstate[10] = STATE_ONLINEUPDATE_NOT_REQUIRED;
            aonlineupdatewizardstate[11] = STATE_ONLINEUPDATE_INTRODUCTION;
            aonlineupdatewizardstate[12] = STATE_ONLINEUPDATE_CHOICE;
            aonlineupdatewizardstate[13] = STATE_ONLINEUPDATE_PENDING;
            aonlineupdatewizardstate[14] = STATE_ONLINEUPDATE_WARNING;
            aonlineupdatewizardstate[15] = STATE_ONLINEUPDATE_DEVICES_UPGRADE_IN_PROGRESS;
            aonlineupdatewizardstate[16] = STATE_ONLINEUPDATE_DEVICES_UPGRADED;
            aonlineupdatewizardstate[17] = STATE_ONLINEUPDATE_POST_UPDATE_REINDEXING_NEEDED;
            aonlineupdatewizardstate[18] = STATE_ONLINEUPDATE_FROM_ZONES_MENU_START;
            aonlineupdatewizardstate[19] = STATE_ONLINEUPDATE_CONTROLLER_NEEDS_UPDATING;
            aonlineupdatewizardstate[20] = STATE_ONLINEUPDATE_CONTROLLER_SELFUPDATE_SUBWIZ;
            aonlineupdatewizardstate[21] = STATE_ONLINEUPDATE_NO_INLINE_SELF_UPDATE;
            aonlineupdatewizardstate[22] = STATE_ONLINEUPDATE_MAX;
            $VALUES = aonlineupdatewizardstate;
        }

        private OnlineUpdateWizardState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private OnlineUpdateWizardState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private OnlineUpdateWizardState(String s, int i, OnlineUpdateWizardState onlineupdatewizardstate)
        {
            Enum(s, i);
            swigValue = onlineupdatewizardstate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class OnlineUpdateWizardMode extends Enum
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


        public static OnlineUpdateWizardMode swigToEnum(int i)
        {
            OnlineUpdateWizardMode aonlineupdatewizardmode[] = (OnlineUpdateWizardMode[])com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizardMode.getEnumConstants();
            if(i >= aonlineupdatewizardmode.length || i < 0 || aonlineupdatewizardmode[i].swigValue != i) goto _L2; else goto _L1
_L1:
            OnlineUpdateWizardMode onlineupdatewizardmode1 = aonlineupdatewizardmode[i];
_L4:
            return onlineupdatewizardmode1;
_L2:
            int j = aonlineupdatewizardmode.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                OnlineUpdateWizardMode onlineupdatewizardmode = aonlineupdatewizardmode[k];
                if(onlineupdatewizardmode.swigValue == i)
                {
                    onlineupdatewizardmode1 = onlineupdatewizardmode;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizardMode).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static OnlineUpdateWizardMode valueOf(String s)
        {
            return (OnlineUpdateWizardMode)Enum.valueOf(com/sonos/sclib/SCIOnlineUpdateWizard$OnlineUpdateWizardMode, s);
        }

        public static OnlineUpdateWizardMode[] values()
        {
            return (OnlineUpdateWizardMode[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final OnlineUpdateWizardMode $VALUES[];
        public static final OnlineUpdateWizardMode ONLINEUPDATE_ANONYMOUS_CR_MODE;
        public static final OnlineUpdateWizardMode ONLINEUPDATE_FROM_ZONES_MENU_MODE;
        public static final OnlineUpdateWizardMode ONLINEUPDATE_INTRODUCTION_ONLY_MODE;
        public static final OnlineUpdateWizardMode ONLINEUPDATE_NORMAL_MODE;
        public static final OnlineUpdateWizardMode ONLINEUPDATE_RESUME_MODE;
        private final int swigValue;

        static 
        {
            ONLINEUPDATE_NORMAL_MODE = new OnlineUpdateWizardMode("ONLINEUPDATE_NORMAL_MODE", 0);
            ONLINEUPDATE_FROM_ZONES_MENU_MODE = new OnlineUpdateWizardMode("ONLINEUPDATE_FROM_ZONES_MENU_MODE", 1);
            ONLINEUPDATE_RESUME_MODE = new OnlineUpdateWizardMode("ONLINEUPDATE_RESUME_MODE", 2);
            ONLINEUPDATE_ANONYMOUS_CR_MODE = new OnlineUpdateWizardMode("ONLINEUPDATE_ANONYMOUS_CR_MODE", 3);
            ONLINEUPDATE_INTRODUCTION_ONLY_MODE = new OnlineUpdateWizardMode("ONLINEUPDATE_INTRODUCTION_ONLY_MODE", 4);
            OnlineUpdateWizardMode aonlineupdatewizardmode[] = new OnlineUpdateWizardMode[5];
            aonlineupdatewizardmode[0] = ONLINEUPDATE_NORMAL_MODE;
            aonlineupdatewizardmode[1] = ONLINEUPDATE_FROM_ZONES_MENU_MODE;
            aonlineupdatewizardmode[2] = ONLINEUPDATE_RESUME_MODE;
            aonlineupdatewizardmode[3] = ONLINEUPDATE_ANONYMOUS_CR_MODE;
            aonlineupdatewizardmode[4] = ONLINEUPDATE_INTRODUCTION_ONLY_MODE;
            $VALUES = aonlineupdatewizardmode;
        }

        private OnlineUpdateWizardMode(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private OnlineUpdateWizardMode(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private OnlineUpdateWizardMode(String s, int i, OnlineUpdateWizardMode onlineupdatewizardmode)
        {
            Enum(s, i);
            swigValue = onlineupdatewizardmode.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIOnlineUpdateWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIWizard(sclibJNI.SWIGSCIOnlineUpdateWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOnlineUpdateWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOnlineUpdateWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOnlineUpdateWizard scionlineupdatewizard)
    {
        long l;
        if(scionlineupdatewizard == null)
            l = 0L;
        else
            l = scionlineupdatewizard.swigCPtr;
        return l;
    }

    public boolean canSelfUpdate()
    {
        return sclibJNI.SCIOnlineUpdateWizard_canSelfUpdate(swigCPtr, this);
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

    public boolean doesControllerNeedUpdating()
    {
        return sclibJNI.SCIOnlineUpdateWizard_doesControllerNeedUpdating(swigCPtr, this);
    }

    public String getChoiceCancelLabel()
    {
        return sclibJNI.SCIOnlineUpdateWizard_getChoiceCancelLabel(swigCPtr, this);
    }

    public String getChoiceContinueLabel()
    {
        return sclibJNI.SCIOnlineUpdateWizard_getChoiceContinueLabel(swigCPtr, this);
    }

    public SCIOnlineUpdateDeviceStatusCode getCurrentDeviceStatusCode()
    {
        return SCIOnlineUpdateDeviceStatusCode.swigToEnum(sclibJNI.SCIOnlineUpdateWizard_getCurrentDeviceStatusCode(swigCPtr, this));
    }

    public OnlineUpdateWizardMode getMode()
    {
        return OnlineUpdateWizardMode.swigToEnum(sclibJNI.SCIOnlineUpdateWizard_getMode(swigCPtr, this));
    }

    public String getOnlineUpdateURL()
    {
        return sclibJNI.SCIOnlineUpdateWizard_getOnlineUpdateURL(swigCPtr, this);
    }

    public int getUpdateCompletedPercentage()
    {
        return sclibJNI.SCIOnlineUpdateWizard_getUpdateCompletedPercentage(swigCPtr, this);
    }

    public void saveUpdateState()
    {
        sclibJNI.SCIOnlineUpdateWizard_saveUpdateState(swigCPtr, this);
    }

    public void setCanSelfUpdate(boolean flag)
    {
        sclibJNI.SCIOnlineUpdateWizard_setCanSelfUpdate(swigCPtr, this, flag);
    }

    public void setConnectionTimeout(int i)
    {
        sclibJNI.SCIOnlineUpdateWizard_setConnectionTimeout(swigCPtr, this, i);
    }

    public void setMode(OnlineUpdateWizardMode onlineupdatewizardmode)
    {
        sclibJNI.SCIOnlineUpdateWizard_setMode(swigCPtr, this, onlineupdatewizardmode.swigValue());
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOnlineUpdateWizard");
    private long swigCPtr;

}
