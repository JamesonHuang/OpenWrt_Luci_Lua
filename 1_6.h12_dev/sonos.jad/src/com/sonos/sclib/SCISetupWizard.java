// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIWizard, sclibJNI, SCIOpDevicePropertiesGetAutoplayLinkedZones, SCIOpDevicePropertiesGetAutoplayVolume, 
//            SCIEnumerator, SCIStringInput, SCISubCalibrator

public class SCISetupWizard extends SCIWizard
{
    public static final class SetupDeviceType extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$402(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$408()
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


        public static SetupDeviceType swigToEnum(int i)
        {
            SetupDeviceType asetupdevicetype[] = (SetupDeviceType[])com/sonos/sclib/SCISetupWizard$SetupDeviceType.getEnumConstants();
            if(i >= asetupdevicetype.length || i < 0 || asetupdevicetype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SetupDeviceType setupdevicetype1 = asetupdevicetype[i];
_L4:
            return setupdevicetype1;
_L2:
            int j = asetupdevicetype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SetupDeviceType setupdevicetype = asetupdevicetype[k];
                if(setupdevicetype.swigValue == i)
                {
                    setupdevicetype1 = setupdevicetype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISetupWizard$SetupDeviceType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SetupDeviceType valueOf(String s)
        {
            return (SetupDeviceType)Enum.valueOf(com/sonos/sclib/SCISetupWizard$SetupDeviceType, s);
        }

        public static SetupDeviceType[] values()
        {
            return (SetupDeviceType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SetupDeviceType $VALUES[];
        public static final SetupDeviceType SETUP_DEVICE_SECONDARY_PLAYER;
        public static final SetupDeviceType SETUP_DEVICE_SONOS_DOCK;
        public static final SetupDeviceType SETUP_DEVICE_UNKNOWN;
        public static final SetupDeviceType SETUP_DEVICE_ZONEBRIDGE;
        public static final SetupDeviceType SETUP_DEVICE_ZONEPLAYER;
        private final int swigValue;

        static 
        {
            SETUP_DEVICE_ZONEPLAYER = new SetupDeviceType("SETUP_DEVICE_ZONEPLAYER", 0);
            SETUP_DEVICE_ZONEBRIDGE = new SetupDeviceType("SETUP_DEVICE_ZONEBRIDGE", 1);
            SETUP_DEVICE_SONOS_DOCK = new SetupDeviceType("SETUP_DEVICE_SONOS_DOCK", 2);
            SETUP_DEVICE_SECONDARY_PLAYER = new SetupDeviceType("SETUP_DEVICE_SECONDARY_PLAYER", 3);
            SETUP_DEVICE_UNKNOWN = new SetupDeviceType("SETUP_DEVICE_UNKNOWN", 4);
            SetupDeviceType asetupdevicetype[] = new SetupDeviceType[5];
            asetupdevicetype[0] = SETUP_DEVICE_ZONEPLAYER;
            asetupdevicetype[1] = SETUP_DEVICE_ZONEBRIDGE;
            asetupdevicetype[2] = SETUP_DEVICE_SONOS_DOCK;
            asetupdevicetype[3] = SETUP_DEVICE_SECONDARY_PLAYER;
            asetupdevicetype[4] = SETUP_DEVICE_UNKNOWN;
            $VALUES = asetupdevicetype;
        }

        private SetupDeviceType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SetupDeviceType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SetupDeviceType(String s, int i, SetupDeviceType setupdevicetype)
        {
            Enum(s, i);
            swigValue = setupdevicetype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class JoinButtonType extends Enum
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


        public static JoinButtonType swigToEnum(int i)
        {
            JoinButtonType ajoinbuttontype[] = (JoinButtonType[])com/sonos/sclib/SCISetupWizard$JoinButtonType.getEnumConstants();
            if(i >= ajoinbuttontype.length || i < 0 || ajoinbuttontype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            JoinButtonType joinbuttontype1 = ajoinbuttontype[i];
_L4:
            return joinbuttontype1;
_L2:
            int j = ajoinbuttontype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                JoinButtonType joinbuttontype = ajoinbuttontype[k];
                if(joinbuttontype.swigValue == i)
                {
                    joinbuttontype1 = joinbuttontype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISetupWizard$JoinButtonType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static JoinButtonType valueOf(String s)
        {
            return (JoinButtonType)Enum.valueOf(com/sonos/sclib/SCISetupWizard$JoinButtonType, s);
        }

        public static JoinButtonType[] values()
        {
            return (JoinButtonType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final JoinButtonType $VALUES[];
        public static final JoinButtonType JOIN_BUTTON_BRIDGE;
        public static final JoinButtonType JOIN_BUTTON_DNA;
        public static final JoinButtonType JOIN_BUTTON_PLAYER;
        public static final JoinButtonType JOIN_BUTTON_SUB;
        public static final JoinButtonType JOIN_BUTTON_UNKNOWN;
        public static final JoinButtonType JOIN_BUTTON_UNKNOWN_BOOST;
        private final int swigValue;

        static 
        {
            JOIN_BUTTON_UNKNOWN = new JoinButtonType("JOIN_BUTTON_UNKNOWN", 0, sclibJNI.SCISetupWizard_JOIN_BUTTON_UNKNOWN_get());
            JOIN_BUTTON_UNKNOWN_BOOST = new JoinButtonType("JOIN_BUTTON_UNKNOWN_BOOST", 1, sclibJNI.SCISetupWizard_JOIN_BUTTON_UNKNOWN_BOOST_get());
            JOIN_BUTTON_PLAYER = new JoinButtonType("JOIN_BUTTON_PLAYER", 2);
            JOIN_BUTTON_BRIDGE = new JoinButtonType("JOIN_BUTTON_BRIDGE", 3);
            JOIN_BUTTON_SUB = new JoinButtonType("JOIN_BUTTON_SUB", 4);
            JOIN_BUTTON_DNA = new JoinButtonType("JOIN_BUTTON_DNA", 5);
            JoinButtonType ajoinbuttontype[] = new JoinButtonType[6];
            ajoinbuttontype[0] = JOIN_BUTTON_UNKNOWN;
            ajoinbuttontype[1] = JOIN_BUTTON_UNKNOWN_BOOST;
            ajoinbuttontype[2] = JOIN_BUTTON_PLAYER;
            ajoinbuttontype[3] = JOIN_BUTTON_BRIDGE;
            ajoinbuttontype[4] = JOIN_BUTTON_SUB;
            ajoinbuttontype[5] = JOIN_BUTTON_DNA;
            $VALUES = ajoinbuttontype;
        }

        private JoinButtonType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private JoinButtonType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private JoinButtonType(String s, int i, JoinButtonType joinbuttontype)
        {
            Enum(s, i);
            swigValue = joinbuttontype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SetupWizStringID extends Enum
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


        public static SetupWizStringID swigToEnum(int i)
        {
            SetupWizStringID asetupwizstringid[] = (SetupWizStringID[])com/sonos/sclib/SCISetupWizard$SetupWizStringID.getEnumConstants();
            if(i >= asetupwizstringid.length || i < 0 || asetupwizstringid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SetupWizStringID setupwizstringid1 = asetupwizstringid[i];
_L4:
            return setupwizstringid1;
_L2:
            int j = asetupwizstringid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SetupWizStringID setupwizstringid = asetupwizstringid[k];
                if(setupwizstringid.swigValue == i)
                {
                    setupwizstringid1 = setupwizstringid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISetupWizard$SetupWizStringID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SetupWizStringID valueOf(String s)
        {
            return (SetupWizStringID)Enum.valueOf(com/sonos/sclib/SCISetupWizard$SetupWizStringID, s);
        }

        public static SetupWizStringID[] values()
        {
            return (SetupWizStringID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SetupWizStringID $VALUES[];
        public static final SetupWizStringID SETUP_STRID_BODY;
        public static final SetupWizStringID SETUP_STRID_BODY_1;
        public static final SetupWizStringID SETUP_STRID_BODY_2;
        public static final SetupWizStringID SETUP_STRID_BODY_3;
        public static final SetupWizStringID SETUP_STRID_BODY_4;
        public static final SetupWizStringID SETUP_STRID_BODY_5;
        public static final SetupWizStringID SETUP_STRID_BODY_ALT_1;
        public static final SetupWizStringID SETUP_STRID_IMG_LABEL_1;
        public static final SetupWizStringID SETUP_STRID_IMG_LABEL_2;
        public static final SetupWizStringID SETUP_STRID_INPUT_1;
        public static final SetupWizStringID SETUP_STRID_INPUT_2;
        public static final SetupWizStringID SETUP_STRID_INPUT_3;
        public static final SetupWizStringID SETUP_STRID_INPUT_4;
        public static final SetupWizStringID SETUP_STRID_INPUT_ALT_1;
        public static final SetupWizStringID SETUP_STRID_INPUT_ALT_2;
        public static final SetupWizStringID SETUP_STRID_MAX;
        public static final SetupWizStringID SETUP_STRID_TITLE_1;
        public static final SetupWizStringID SETUP_STRID_TITLE_2;
        public static final SetupWizStringID SETUP_STRID_WIZARD_INSTRUCTIONS;
        private final int swigValue;

        static 
        {
            SETUP_STRID_TITLE_1 = new SetupWizStringID("SETUP_STRID_TITLE_1", 0, sclibJNI.SCISetupWizard_SETUP_STRID_TITLE_1_get());
            SETUP_STRID_BODY = new SetupWizStringID("SETUP_STRID_BODY", 1, sclibJNI.SCISetupWizard_SETUP_STRID_BODY_get());
            SETUP_STRID_BODY_1 = new SetupWizStringID("SETUP_STRID_BODY_1", 2);
            SETUP_STRID_BODY_2 = new SetupWizStringID("SETUP_STRID_BODY_2", 3);
            SETUP_STRID_BODY_3 = new SetupWizStringID("SETUP_STRID_BODY_3", 4);
            SETUP_STRID_BODY_4 = new SetupWizStringID("SETUP_STRID_BODY_4", 5);
            SETUP_STRID_BODY_5 = new SetupWizStringID("SETUP_STRID_BODY_5", 6);
            SETUP_STRID_INPUT_1 = new SetupWizStringID("SETUP_STRID_INPUT_1", 7);
            SETUP_STRID_INPUT_2 = new SetupWizStringID("SETUP_STRID_INPUT_2", 8);
            SETUP_STRID_INPUT_3 = new SetupWizStringID("SETUP_STRID_INPUT_3", 9);
            SETUP_STRID_INPUT_4 = new SetupWizStringID("SETUP_STRID_INPUT_4", 10);
            SETUP_STRID_TITLE_2 = new SetupWizStringID("SETUP_STRID_TITLE_2", 11);
            SETUP_STRID_BODY_ALT_1 = new SetupWizStringID("SETUP_STRID_BODY_ALT_1", 12);
            SETUP_STRID_INPUT_ALT_1 = new SetupWizStringID("SETUP_STRID_INPUT_ALT_1", 13);
            SETUP_STRID_INPUT_ALT_2 = new SetupWizStringID("SETUP_STRID_INPUT_ALT_2", 14);
            SETUP_STRID_WIZARD_INSTRUCTIONS = new SetupWizStringID("SETUP_STRID_WIZARD_INSTRUCTIONS", 15);
            SETUP_STRID_IMG_LABEL_1 = new SetupWizStringID("SETUP_STRID_IMG_LABEL_1", 16);
            SETUP_STRID_IMG_LABEL_2 = new SetupWizStringID("SETUP_STRID_IMG_LABEL_2", 17);
            SETUP_STRID_MAX = new SetupWizStringID("SETUP_STRID_MAX", 18);
            SetupWizStringID asetupwizstringid[] = new SetupWizStringID[19];
            asetupwizstringid[0] = SETUP_STRID_TITLE_1;
            asetupwizstringid[1] = SETUP_STRID_BODY;
            asetupwizstringid[2] = SETUP_STRID_BODY_1;
            asetupwizstringid[3] = SETUP_STRID_BODY_2;
            asetupwizstringid[4] = SETUP_STRID_BODY_3;
            asetupwizstringid[5] = SETUP_STRID_BODY_4;
            asetupwizstringid[6] = SETUP_STRID_BODY_5;
            asetupwizstringid[7] = SETUP_STRID_INPUT_1;
            asetupwizstringid[8] = SETUP_STRID_INPUT_2;
            asetupwizstringid[9] = SETUP_STRID_INPUT_3;
            asetupwizstringid[10] = SETUP_STRID_INPUT_4;
            asetupwizstringid[11] = SETUP_STRID_TITLE_2;
            asetupwizstringid[12] = SETUP_STRID_BODY_ALT_1;
            asetupwizstringid[13] = SETUP_STRID_INPUT_ALT_1;
            asetupwizstringid[14] = SETUP_STRID_INPUT_ALT_2;
            asetupwizstringid[15] = SETUP_STRID_WIZARD_INSTRUCTIONS;
            asetupwizstringid[16] = SETUP_STRID_IMG_LABEL_1;
            asetupwizstringid[17] = SETUP_STRID_IMG_LABEL_2;
            asetupwizstringid[18] = SETUP_STRID_MAX;
            $VALUES = asetupwizstringid;
        }

        private SetupWizStringID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SetupWizStringID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SetupWizStringID(String s, int i, SetupWizStringID setupwizstringid)
        {
            Enum(s, i);
            swigValue = setupwizstringid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SetupWizardState extends Enum
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


        public static SetupWizardState swigToEnum(int i)
        {
            SetupWizardState asetupwizardstate[] = (SetupWizardState[])com/sonos/sclib/SCISetupWizard$SetupWizardState.getEnumConstants();
            if(i >= asetupwizardstate.length || i < 0 || asetupwizardstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SetupWizardState setupwizardstate1 = asetupwizardstate[i];
_L4:
            return setupwizardstate1;
_L2:
            int j = asetupwizardstate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SetupWizardState setupwizardstate = asetupwizardstate[k];
                if(setupwizardstate.swigValue == i)
                {
                    setupwizardstate1 = setupwizardstate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISetupWizard$SetupWizardState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SetupWizardState valueOf(String s)
        {
            return (SetupWizardState)Enum.valueOf(com/sonos/sclib/SCISetupWizard$SetupWizardState, s);
        }

        public static SetupWizardState[] values()
        {
            return (SetupWizardState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SetupWizardState $VALUES[];
        public static final SetupWizardState STATE_BETA_OPT_IN;
        public static final SetupWizardState STATE_BETA_RESULT;
        public static final SetupWizardState STATE_BETA_START;
        public static final SetupWizardState STATE_CHECK_SHARES;
        public static final SetupWizardState STATE_CHOOSE_CONFIGURE_DEVICE;
        public static final SetupWizardState STATE_MULTIPLE_HOUSEHOLD_FOUND;
        public static final SetupWizardState STATE_MUSIC_LIBRARY_SETUP_SUBWIZ;
        public static final SetupWizardState STATE_NEW_WIRED_COMPONENT;
        public static final SetupWizardState STATE_NEW_WIRED_COMPONENT_CHECK;
        public static final SetupWizardState STATE_SETUP_ADD_BRIDGE;
        public static final SetupWizardState STATE_SETUP_ALLPLAYERS_IN_RANGE;
        public static final SetupWizardState STATE_SETUP_AUTOPLAY_ASSIGNED;
        public static final SetupWizardState STATE_SETUP_AUTOPLAY_CONFIGURE;
        public static final SetupWizardState STATE_SETUP_CHECK_CHECKING;
        public static final SetupWizardState STATE_SETUP_CHECK_ERROR;
        public static final SetupWizardState STATE_SETUP_CHECK_UPDATE_NEEDED;
        public static final SetupWizardState STATE_SETUP_COMPLETE;
        public static final SetupWizardState STATE_SETUP_CONNECTFAIL;
        public static final SetupWizardState STATE_SETUP_CONNECTING;
        public static final SetupWizardState STATE_SETUP_CONNECTOK;
        public static final SetupWizardState STATE_SETUP_CONNECT_POWER;
        public static final SetupWizardState STATE_SETUP_DEVICE_ADDED;
        public static final SetupWizardState STATE_SETUP_DEVICE_BEING_CONFIGURED;
        public static final SetupWizardState STATE_SETUP_DISCONNECT_FIRSTPLAYER;
        public static final SetupWizardState STATE_SETUP_EXISTING_REGISTERED_HH;
        public static final SetupWizardState STATE_SETUP_EXISTING_SYSTEM_FOUND;
        public static final SetupWizardState STATE_SETUP_EXTENDER_WARNING;
        public static final SetupWizardState STATE_SETUP_EXTRA_DEVICE_SETUP;
        public static final SetupWizardState STATE_SETUP_FINISH_ADD_PLAYERS;
        public static final SetupWizardState STATE_SETUP_FINISH_COMPLETE;
        public static final SetupWizardState STATE_SETUP_FINISH_INCOMPLETE;
        public static final SetupWizardState STATE_SETUP_FINISH_MOREINFO_PLAYBAR;
        public static final SetupWizardState STATE_SETUP_FIREWALL_CONFIGURATION;
        public static final SetupWizardState STATE_SETUP_FULL_SETUP_DONE;
        public static final SetupWizardState STATE_SETUP_IDENTIFY_COMPONENT_ERROR;
        public static final SetupWizardState STATE_SETUP_INIT;
        public static final SetupWizardState STATE_SETUP_INSECURE_DOCK;
        public static final SetupWizardState STATE_SETUP_INSECURE_PLAYER;
        public static final SetupWizardState STATE_SETUP_LANGUAGE_CHOICE_SUBWIZ;
        public static final SetupWizardState STATE_SETUP_MAX;
        public static final SetupWizardState STATE_SETUP_NAME_DEVICE;
        public static final SetupWizardState STATE_SETUP_NEXT_DEVICE;
        public static final SetupWizardState STATE_SETUP_NO_EXISTING_HH_FOUND;
        public static final SetupWizardState STATE_SETUP_NO_NETWORK;
        public static final SetupWizardState STATE_SETUP_NO_ZONEPLAYERS;
        public static final SetupWizardState STATE_SETUP_OUT_OF_RANGE;
        public static final SetupWizardState STATE_SETUP_PLEASEWAIT_RANGECHECK;
        public static final SetupWizardState STATE_SETUP_POWER_UP_TEMPWIRE;
        public static final SetupWizardState STATE_SETUP_POWER_UP_TEMPWIRE_ERROR;
        public static final SetupWizardState STATE_SETUP_PREP;
        public static final SetupWizardState STATE_SETUP_RESTART_FIRSTPLAYER;
        public static final SetupWizardState STATE_SETUP_SATELLITE_ATTACH;
        public static final SetupWizardState STATE_SETUP_SOUNDBAR_DESKTOP_SAME_ROOM;
        public static final SetupWizardState STATE_SETUP_START_TEMPWIRE;
        public static final SetupWizardState STATE_SETUP_START_TEMPWIRE_ON_TIMEOUT;
        public static final SetupWizardState STATE_SETUP_SUB_CALIBRATE_INTRO;
        public static final SetupWizardState STATE_SETUP_SUB_CALIBRATE_LEVEL;
        public static final SetupWizardState STATE_SETUP_SUB_CALIBRATE_MOVE;
        public static final SetupWizardState STATE_SETUP_SUB_CALIBRATE_PHASE;
        public static final SetupWizardState STATE_SETUP_SUB_CHOOSE_ROOM;
        public static final SetupWizardState STATE_SETUP_SUB_CONFIGURE;
        public static final SetupWizardState STATE_SETUP_SUB_FAILURE;
        public static final SetupWizardState STATE_SETUP_SUB_INTRO;
        public static final SetupWizardState STATE_SETUP_SUB_NO_ROOMS;
        public static final SetupWizardState STATE_SETUP_SUB_OUTRO;
        public static final SetupWizardState STATE_SETUP_SUB_REMOVE;
        public static final SetupWizardState STATE_SETUP_SUB_SPEAKER;
        public static final SetupWizardState STATE_SETUP_SURROUND_CHOOSE_ROOM;
        public static final SetupWizardState STATE_SETUP_SURROUND_INTRO;
        public static final SetupWizardState STATE_SETUP_TEMPWIRE_CONNECT_ETH;
        public static final SetupWizardState STATE_SETUP_TEMPWIRE_INCOMPLETE;
        public static final SetupWizardState STATE_SETUP_UNKNOWN;
        public static final SetupWizardState STATE_SETUP_UPDATE_DEVICES;
        public static final SetupWizardState STATE_SETUP_USE_WIRELESS;
        public static final SetupWizardState STATE_SETUP_WAITING;
        public static final SetupWizardState STATE_SETUP_WELCOME;
        public static final SetupWizardState STATE_SETUP_WIFI_CREDENTIALS_SUBWIZ;
        public static final SetupWizardState STATE_SETUP_WIRELESS_UNEXPECTED_DISCONNECT;
        public static final SetupWizardState STATE_SETUP_WIRE_BRIDGE;
        public static final SetupWizardState STATE_WIRELESS_REPEAT_BUTTONS;
        public static final SetupWizardState STATE_WIRELESS_SETUP_AUTHORIZATION;
        public static final SetupWizardState STATE_WIRELESS_SETUP_CHECK_LIGHTS;
        public static final SetupWizardState STATE_WIRELESS_SETUP_CONNECTING;
        public static final SetupWizardState STATE_WIRELESS_SETUP_CONNECT_TO_AP;
        public static final SetupWizardState STATE_WIRELESS_SETUP_IDENTIFY_COMPONENT;
        public static final SetupWizardState STATE_WIRELESS_SETUP_NEED_BRIDGE;
        public static final SetupWizardState STATE_WIRELESS_SETUP_PRESS_TO_ADD;
        public static final SetupWizardState STATE_WIRELESS_SETUP_PROBLEM;
        public static final SetupWizardState STATE_WIRELESS_SETUP_WAITING;
        public static final SetupWizardState STATE_WIZ_LOCATION_AUTHORIZE;
        public static final SetupWizardState STATE_WIZ_LOCATION_REQUEST;
        private final int swigValue;

        static 
        {
            STATE_SETUP_UNKNOWN = new SetupWizardState("STATE_SETUP_UNKNOWN", 0, sclibJNI.SCISetupWizard_STATE_SETUP_UNKNOWN_get());
            STATE_SETUP_INIT = new SetupWizardState("STATE_SETUP_INIT", 1, sclibJNI.SCISetupWizard_STATE_SETUP_INIT_get());
            STATE_SETUP_COMPLETE = new SetupWizardState("STATE_SETUP_COMPLETE", 2, sclibJNI.SCISetupWizard_STATE_SETUP_COMPLETE_get());
            STATE_SETUP_WELCOME = new SetupWizardState("STATE_SETUP_WELCOME", 3);
            STATE_SETUP_LANGUAGE_CHOICE_SUBWIZ = new SetupWizardState("STATE_SETUP_LANGUAGE_CHOICE_SUBWIZ", 4);
            STATE_SETUP_CHECK_CHECKING = new SetupWizardState("STATE_SETUP_CHECK_CHECKING", 5);
            STATE_SETUP_CHECK_UPDATE_NEEDED = new SetupWizardState("STATE_SETUP_CHECK_UPDATE_NEEDED", 6);
            STATE_SETUP_CHECK_ERROR = new SetupWizardState("STATE_SETUP_CHECK_ERROR", 7);
            STATE_SETUP_WAITING = new SetupWizardState("STATE_SETUP_WAITING", 8);
            STATE_SETUP_CONNECTING = new SetupWizardState("STATE_SETUP_CONNECTING", 9);
            STATE_SETUP_CONNECTFAIL = new SetupWizardState("STATE_SETUP_CONNECTFAIL", 10);
            STATE_SETUP_CONNECTOK = new SetupWizardState("STATE_SETUP_CONNECTOK", 11);
            STATE_SETUP_EXISTING_SYSTEM_FOUND = new SetupWizardState("STATE_SETUP_EXISTING_SYSTEM_FOUND", 12);
            STATE_SETUP_NAME_DEVICE = new SetupWizardState("STATE_SETUP_NAME_DEVICE", 13);
            STATE_SETUP_DEVICE_ADDED = new SetupWizardState("STATE_SETUP_DEVICE_ADDED", 14);
            STATE_SETUP_NO_ZONEPLAYERS = new SetupWizardState("STATE_SETUP_NO_ZONEPLAYERS", 15);
            STATE_SETUP_NEXT_DEVICE = new SetupWizardState("STATE_SETUP_NEXT_DEVICE", 16);
            STATE_SETUP_AUTOPLAY_CONFIGURE = new SetupWizardState("STATE_SETUP_AUTOPLAY_CONFIGURE", 17);
            STATE_SETUP_AUTOPLAY_ASSIGNED = new SetupWizardState("STATE_SETUP_AUTOPLAY_ASSIGNED", 18);
            STATE_SETUP_FULL_SETUP_DONE = new SetupWizardState("STATE_SETUP_FULL_SETUP_DONE", 19);
            STATE_SETUP_FINISH_MOREINFO_PLAYBAR = new SetupWizardState("STATE_SETUP_FINISH_MOREINFO_PLAYBAR", 20);
            STATE_SETUP_FINISH_COMPLETE = new SetupWizardState("STATE_SETUP_FINISH_COMPLETE", 21);
            STATE_SETUP_FINISH_INCOMPLETE = new SetupWizardState("STATE_SETUP_FINISH_INCOMPLETE", 22);
            STATE_WIZ_LOCATION_REQUEST = new SetupWizardState("STATE_WIZ_LOCATION_REQUEST", 23);
            STATE_WIZ_LOCATION_AUTHORIZE = new SetupWizardState("STATE_WIZ_LOCATION_AUTHORIZE", 24);
            STATE_SETUP_EXISTING_REGISTERED_HH = new SetupWizardState("STATE_SETUP_EXISTING_REGISTERED_HH", 25);
            STATE_CHECK_SHARES = new SetupWizardState("STATE_CHECK_SHARES", 26);
            STATE_MUSIC_LIBRARY_SETUP_SUBWIZ = new SetupWizardState("STATE_MUSIC_LIBRARY_SETUP_SUBWIZ", 27);
            STATE_SETUP_SUB_INTRO = new SetupWizardState("STATE_SETUP_SUB_INTRO", 28);
            STATE_SETUP_SUB_NO_ROOMS = new SetupWizardState("STATE_SETUP_SUB_NO_ROOMS", 29);
            STATE_SETUP_SUB_CHOOSE_ROOM = new SetupWizardState("STATE_SETUP_SUB_CHOOSE_ROOM", 30);
            STATE_SETUP_SUB_FAILURE = new SetupWizardState("STATE_SETUP_SUB_FAILURE", 31);
            STATE_SETUP_SUB_CALIBRATE_INTRO = new SetupWizardState("STATE_SETUP_SUB_CALIBRATE_INTRO", 32);
            STATE_SETUP_SUB_SPEAKER = new SetupWizardState("STATE_SETUP_SUB_SPEAKER", 33);
            STATE_SETUP_SUB_CALIBRATE_MOVE = new SetupWizardState("STATE_SETUP_SUB_CALIBRATE_MOVE", 34);
            STATE_SETUP_SUB_CALIBRATE_PHASE = new SetupWizardState("STATE_SETUP_SUB_CALIBRATE_PHASE", 35);
            STATE_SETUP_SUB_CALIBRATE_LEVEL = new SetupWizardState("STATE_SETUP_SUB_CALIBRATE_LEVEL", 36);
            STATE_SETUP_SUB_OUTRO = new SetupWizardState("STATE_SETUP_SUB_OUTRO", 37);
            STATE_SETUP_SUB_REMOVE = new SetupWizardState("STATE_SETUP_SUB_REMOVE", 38);
            STATE_SETUP_SUB_CONFIGURE = new SetupWizardState("STATE_SETUP_SUB_CONFIGURE", 39);
            STATE_SETUP_SURROUND_INTRO = new SetupWizardState("STATE_SETUP_SURROUND_INTRO", 40);
            STATE_SETUP_SURROUND_CHOOSE_ROOM = new SetupWizardState("STATE_SETUP_SURROUND_CHOOSE_ROOM", 41);
            STATE_SETUP_SATELLITE_ATTACH = new SetupWizardState("STATE_SETUP_SATELLITE_ATTACH", 42);
            STATE_CHOOSE_CONFIGURE_DEVICE = new SetupWizardState("STATE_CHOOSE_CONFIGURE_DEVICE", 43);
            STATE_SETUP_EXTRA_DEVICE_SETUP = new SetupWizardState("STATE_SETUP_EXTRA_DEVICE_SETUP", 44);
            STATE_SETUP_SOUNDBAR_DESKTOP_SAME_ROOM = new SetupWizardState("STATE_SETUP_SOUNDBAR_DESKTOP_SAME_ROOM", 45);
            STATE_SETUP_DEVICE_BEING_CONFIGURED = new SetupWizardState("STATE_SETUP_DEVICE_BEING_CONFIGURED", 46);
            STATE_SETUP_UPDATE_DEVICES = new SetupWizardState("STATE_SETUP_UPDATE_DEVICES", 47);
            STATE_BETA_START = new SetupWizardState("STATE_BETA_START", 48);
            STATE_BETA_OPT_IN = new SetupWizardState("STATE_BETA_OPT_IN", 49);
            STATE_BETA_RESULT = new SetupWizardState("STATE_BETA_RESULT", 50);
            STATE_SETUP_USE_WIRELESS = new SetupWizardState("STATE_SETUP_USE_WIRELESS", 51);
            STATE_SETUP_PREP = new SetupWizardState("STATE_SETUP_PREP", 52);
            STATE_SETUP_WIRELESS_UNEXPECTED_DISCONNECT = new SetupWizardState("STATE_SETUP_WIRELESS_UNEXPECTED_DISCONNECT", 53);
            STATE_SETUP_START_TEMPWIRE = new SetupWizardState("STATE_SETUP_START_TEMPWIRE", 54);
            STATE_SETUP_START_TEMPWIRE_ON_TIMEOUT = new SetupWizardState("STATE_SETUP_START_TEMPWIRE_ON_TIMEOUT", 55);
            STATE_SETUP_TEMPWIRE_CONNECT_ETH = new SetupWizardState("STATE_SETUP_TEMPWIRE_CONNECT_ETH", 56);
            STATE_SETUP_CONNECT_POWER = new SetupWizardState("STATE_SETUP_CONNECT_POWER", 57);
            STATE_WIRELESS_SETUP_IDENTIFY_COMPONENT = new SetupWizardState("STATE_WIRELESS_SETUP_IDENTIFY_COMPONENT", 58);
            STATE_SETUP_WIFI_CREDENTIALS_SUBWIZ = new SetupWizardState("STATE_SETUP_WIFI_CREDENTIALS_SUBWIZ", 59);
            STATE_SETUP_DISCONNECT_FIRSTPLAYER = new SetupWizardState("STATE_SETUP_DISCONNECT_FIRSTPLAYER", 60);
            STATE_SETUP_RESTART_FIRSTPLAYER = new SetupWizardState("STATE_SETUP_RESTART_FIRSTPLAYER", 61);
            STATE_SETUP_POWER_UP_TEMPWIRE = new SetupWizardState("STATE_SETUP_POWER_UP_TEMPWIRE", 62);
            STATE_SETUP_PLEASEWAIT_RANGECHECK = new SetupWizardState("STATE_SETUP_PLEASEWAIT_RANGECHECK", 63);
            STATE_SETUP_OUT_OF_RANGE = new SetupWizardState("STATE_SETUP_OUT_OF_RANGE", 64);
            STATE_SETUP_ALLPLAYERS_IN_RANGE = new SetupWizardState("STATE_SETUP_ALLPLAYERS_IN_RANGE", 65);
            STATE_WIRELESS_SETUP_PRESS_TO_ADD = new SetupWizardState("STATE_WIRELESS_SETUP_PRESS_TO_ADD", 66);
            STATE_WIRELESS_SETUP_AUTHORIZATION = new SetupWizardState("STATE_WIRELESS_SETUP_AUTHORIZATION", 67);
            STATE_SETUP_FIREWALL_CONFIGURATION = new SetupWizardState("STATE_SETUP_FIREWALL_CONFIGURATION", 68);
            STATE_WIRELESS_SETUP_CONNECT_TO_AP = new SetupWizardState("STATE_WIRELESS_SETUP_CONNECT_TO_AP", 69);
            STATE_WIRELESS_SETUP_CHECK_LIGHTS = new SetupWizardState("STATE_WIRELESS_SETUP_CHECK_LIGHTS", 70);
            STATE_WIRELESS_REPEAT_BUTTONS = new SetupWizardState("STATE_WIRELESS_REPEAT_BUTTONS", 71);
            STATE_WIRELESS_SETUP_WAITING = new SetupWizardState("STATE_WIRELESS_SETUP_WAITING", 72);
            STATE_WIRELESS_SETUP_CONNECTING = new SetupWizardState("STATE_WIRELESS_SETUP_CONNECTING", 73);
            STATE_WIRELESS_SETUP_PROBLEM = new SetupWizardState("STATE_WIRELESS_SETUP_PROBLEM", 74);
            STATE_WIRELESS_SETUP_NEED_BRIDGE = new SetupWizardState("STATE_WIRELESS_SETUP_NEED_BRIDGE", 75);
            STATE_NEW_WIRED_COMPONENT = new SetupWizardState("STATE_NEW_WIRED_COMPONENT", 76);
            STATE_NEW_WIRED_COMPONENT_CHECK = new SetupWizardState("STATE_NEW_WIRED_COMPONENT_CHECK", 77);
            STATE_MULTIPLE_HOUSEHOLD_FOUND = new SetupWizardState("STATE_MULTIPLE_HOUSEHOLD_FOUND", 78);
            STATE_SETUP_TEMPWIRE_INCOMPLETE = new SetupWizardState("STATE_SETUP_TEMPWIRE_INCOMPLETE", 79);
            STATE_SETUP_IDENTIFY_COMPONENT_ERROR = new SetupWizardState("STATE_SETUP_IDENTIFY_COMPONENT_ERROR", 80);
            STATE_SETUP_POWER_UP_TEMPWIRE_ERROR = new SetupWizardState("STATE_SETUP_POWER_UP_TEMPWIRE_ERROR", 81);
            STATE_SETUP_ADD_BRIDGE = new SetupWizardState("STATE_SETUP_ADD_BRIDGE", 82);
            STATE_SETUP_WIRE_BRIDGE = new SetupWizardState("STATE_SETUP_WIRE_BRIDGE", 83);
            STATE_SETUP_FINISH_ADD_PLAYERS = new SetupWizardState("STATE_SETUP_FINISH_ADD_PLAYERS", 84);
            STATE_SETUP_NO_EXISTING_HH_FOUND = new SetupWizardState("STATE_SETUP_NO_EXISTING_HH_FOUND", 85);
            STATE_SETUP_NO_NETWORK = new SetupWizardState("STATE_SETUP_NO_NETWORK", 86);
            STATE_SETUP_EXTENDER_WARNING = new SetupWizardState("STATE_SETUP_EXTENDER_WARNING", 87);
            STATE_SETUP_INSECURE_PLAYER = new SetupWizardState("STATE_SETUP_INSECURE_PLAYER", 88);
            STATE_SETUP_INSECURE_DOCK = new SetupWizardState("STATE_SETUP_INSECURE_DOCK", 89);
            STATE_SETUP_MAX = new SetupWizardState("STATE_SETUP_MAX", 90);
            SetupWizardState asetupwizardstate[] = new SetupWizardState[91];
            asetupwizardstate[0] = STATE_SETUP_UNKNOWN;
            asetupwizardstate[1] = STATE_SETUP_INIT;
            asetupwizardstate[2] = STATE_SETUP_COMPLETE;
            asetupwizardstate[3] = STATE_SETUP_WELCOME;
            asetupwizardstate[4] = STATE_SETUP_LANGUAGE_CHOICE_SUBWIZ;
            asetupwizardstate[5] = STATE_SETUP_CHECK_CHECKING;
            asetupwizardstate[6] = STATE_SETUP_CHECK_UPDATE_NEEDED;
            asetupwizardstate[7] = STATE_SETUP_CHECK_ERROR;
            asetupwizardstate[8] = STATE_SETUP_WAITING;
            asetupwizardstate[9] = STATE_SETUP_CONNECTING;
            asetupwizardstate[10] = STATE_SETUP_CONNECTFAIL;
            asetupwizardstate[11] = STATE_SETUP_CONNECTOK;
            asetupwizardstate[12] = STATE_SETUP_EXISTING_SYSTEM_FOUND;
            asetupwizardstate[13] = STATE_SETUP_NAME_DEVICE;
            asetupwizardstate[14] = STATE_SETUP_DEVICE_ADDED;
            asetupwizardstate[15] = STATE_SETUP_NO_ZONEPLAYERS;
            asetupwizardstate[16] = STATE_SETUP_NEXT_DEVICE;
            asetupwizardstate[17] = STATE_SETUP_AUTOPLAY_CONFIGURE;
            asetupwizardstate[18] = STATE_SETUP_AUTOPLAY_ASSIGNED;
            asetupwizardstate[19] = STATE_SETUP_FULL_SETUP_DONE;
            asetupwizardstate[20] = STATE_SETUP_FINISH_MOREINFO_PLAYBAR;
            asetupwizardstate[21] = STATE_SETUP_FINISH_COMPLETE;
            asetupwizardstate[22] = STATE_SETUP_FINISH_INCOMPLETE;
            asetupwizardstate[23] = STATE_WIZ_LOCATION_REQUEST;
            asetupwizardstate[24] = STATE_WIZ_LOCATION_AUTHORIZE;
            asetupwizardstate[25] = STATE_SETUP_EXISTING_REGISTERED_HH;
            asetupwizardstate[26] = STATE_CHECK_SHARES;
            asetupwizardstate[27] = STATE_MUSIC_LIBRARY_SETUP_SUBWIZ;
            asetupwizardstate[28] = STATE_SETUP_SUB_INTRO;
            asetupwizardstate[29] = STATE_SETUP_SUB_NO_ROOMS;
            asetupwizardstate[30] = STATE_SETUP_SUB_CHOOSE_ROOM;
            asetupwizardstate[31] = STATE_SETUP_SUB_FAILURE;
            asetupwizardstate[32] = STATE_SETUP_SUB_CALIBRATE_INTRO;
            asetupwizardstate[33] = STATE_SETUP_SUB_SPEAKER;
            asetupwizardstate[34] = STATE_SETUP_SUB_CALIBRATE_MOVE;
            asetupwizardstate[35] = STATE_SETUP_SUB_CALIBRATE_PHASE;
            asetupwizardstate[36] = STATE_SETUP_SUB_CALIBRATE_LEVEL;
            asetupwizardstate[37] = STATE_SETUP_SUB_OUTRO;
            asetupwizardstate[38] = STATE_SETUP_SUB_REMOVE;
            asetupwizardstate[39] = STATE_SETUP_SUB_CONFIGURE;
            asetupwizardstate[40] = STATE_SETUP_SURROUND_INTRO;
            asetupwizardstate[41] = STATE_SETUP_SURROUND_CHOOSE_ROOM;
            asetupwizardstate[42] = STATE_SETUP_SATELLITE_ATTACH;
            asetupwizardstate[43] = STATE_CHOOSE_CONFIGURE_DEVICE;
            asetupwizardstate[44] = STATE_SETUP_EXTRA_DEVICE_SETUP;
            asetupwizardstate[45] = STATE_SETUP_SOUNDBAR_DESKTOP_SAME_ROOM;
            asetupwizardstate[46] = STATE_SETUP_DEVICE_BEING_CONFIGURED;
            asetupwizardstate[47] = STATE_SETUP_UPDATE_DEVICES;
            asetupwizardstate[48] = STATE_BETA_START;
            asetupwizardstate[49] = STATE_BETA_OPT_IN;
            asetupwizardstate[50] = STATE_BETA_RESULT;
            asetupwizardstate[51] = STATE_SETUP_USE_WIRELESS;
            asetupwizardstate[52] = STATE_SETUP_PREP;
            asetupwizardstate[53] = STATE_SETUP_WIRELESS_UNEXPECTED_DISCONNECT;
            asetupwizardstate[54] = STATE_SETUP_START_TEMPWIRE;
            asetupwizardstate[55] = STATE_SETUP_START_TEMPWIRE_ON_TIMEOUT;
            asetupwizardstate[56] = STATE_SETUP_TEMPWIRE_CONNECT_ETH;
            asetupwizardstate[57] = STATE_SETUP_CONNECT_POWER;
            asetupwizardstate[58] = STATE_WIRELESS_SETUP_IDENTIFY_COMPONENT;
            asetupwizardstate[59] = STATE_SETUP_WIFI_CREDENTIALS_SUBWIZ;
            asetupwizardstate[60] = STATE_SETUP_DISCONNECT_FIRSTPLAYER;
            asetupwizardstate[61] = STATE_SETUP_RESTART_FIRSTPLAYER;
            asetupwizardstate[62] = STATE_SETUP_POWER_UP_TEMPWIRE;
            asetupwizardstate[63] = STATE_SETUP_PLEASEWAIT_RANGECHECK;
            asetupwizardstate[64] = STATE_SETUP_OUT_OF_RANGE;
            asetupwizardstate[65] = STATE_SETUP_ALLPLAYERS_IN_RANGE;
            asetupwizardstate[66] = STATE_WIRELESS_SETUP_PRESS_TO_ADD;
            asetupwizardstate[67] = STATE_WIRELESS_SETUP_AUTHORIZATION;
            asetupwizardstate[68] = STATE_SETUP_FIREWALL_CONFIGURATION;
            asetupwizardstate[69] = STATE_WIRELESS_SETUP_CONNECT_TO_AP;
            asetupwizardstate[70] = STATE_WIRELESS_SETUP_CHECK_LIGHTS;
            asetupwizardstate[71] = STATE_WIRELESS_REPEAT_BUTTONS;
            asetupwizardstate[72] = STATE_WIRELESS_SETUP_WAITING;
            asetupwizardstate[73] = STATE_WIRELESS_SETUP_CONNECTING;
            asetupwizardstate[74] = STATE_WIRELESS_SETUP_PROBLEM;
            asetupwizardstate[75] = STATE_WIRELESS_SETUP_NEED_BRIDGE;
            asetupwizardstate[76] = STATE_NEW_WIRED_COMPONENT;
            asetupwizardstate[77] = STATE_NEW_WIRED_COMPONENT_CHECK;
            asetupwizardstate[78] = STATE_MULTIPLE_HOUSEHOLD_FOUND;
            asetupwizardstate[79] = STATE_SETUP_TEMPWIRE_INCOMPLETE;
            asetupwizardstate[80] = STATE_SETUP_IDENTIFY_COMPONENT_ERROR;
            asetupwizardstate[81] = STATE_SETUP_POWER_UP_TEMPWIRE_ERROR;
            asetupwizardstate[82] = STATE_SETUP_ADD_BRIDGE;
            asetupwizardstate[83] = STATE_SETUP_WIRE_BRIDGE;
            asetupwizardstate[84] = STATE_SETUP_FINISH_ADD_PLAYERS;
            asetupwizardstate[85] = STATE_SETUP_NO_EXISTING_HH_FOUND;
            asetupwizardstate[86] = STATE_SETUP_NO_NETWORK;
            asetupwizardstate[87] = STATE_SETUP_EXTENDER_WARNING;
            asetupwizardstate[88] = STATE_SETUP_INSECURE_PLAYER;
            asetupwizardstate[89] = STATE_SETUP_INSECURE_DOCK;
            asetupwizardstate[90] = STATE_SETUP_MAX;
            $VALUES = asetupwizardstate;
        }

        private SetupWizardState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SetupWizardState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SetupWizardState(String s, int i, SetupWizardState setupwizardstate)
        {
            Enum(s, i);
            swigValue = setupwizardstate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SetupWizardMode extends Enum
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


        public static SetupWizardMode swigToEnum(int i)
        {
            SetupWizardMode asetupwizardmode[] = (SetupWizardMode[])com/sonos/sclib/SCISetupWizard$SetupWizardMode.getEnumConstants();
            if(i >= asetupwizardmode.length || i < 0 || asetupwizardmode[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SetupWizardMode setupwizardmode1 = asetupwizardmode[i];
_L4:
            return setupwizardmode1;
_L2:
            int j = asetupwizardmode.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SetupWizardMode setupwizardmode = asetupwizardmode[k];
                if(setupwizardmode.swigValue == i)
                {
                    setupwizardmode1 = setupwizardmode;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISetupWizard$SetupWizardMode).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SetupWizardMode valueOf(String s)
        {
            return (SetupWizardMode)Enum.valueOf(com/sonos/sclib/SCISetupWizard$SetupWizardMode, s);
        }

        public static SetupWizardMode[] values()
        {
            return (SetupWizardMode[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SetupWizardMode $VALUES[];
        public static final SetupWizardMode MODE_ADD_BOOST;
        public static final SetupWizardMode MODE_ADD_PLAYERORSUB;
        public static final SetupWizardMode MODE_BETA_PROGRAM;
        public static final SetupWizardMode MODE_CHOOSE_CONFIGURE_DEVICE;
        public static final SetupWizardMode MODE_CONFIGURE_SUB;
        public static final SetupWizardMode MODE_FULL_SETUP;
        public static final SetupWizardMode MODE_JOIN_ANOTHER_HOUSEHOLD;
        public static final SetupWizardMode MODE_MUSIC_LIBRARY_SETUP_SUBWIZ;
        public static final SetupWizardMode MODE_RECALIBRATE_SUB;
        public static final SetupWizardMode MODE_REGISTRATION;
        public static final SetupWizardMode MODE_SETUP_UNCONFIGURED_COMPONENT;
        public static final SetupWizardMode MODE_SHARE_USAGE_DATA;
        public static final SetupWizardMode MODE_SPEAKER_SIZE;
        private final int swigValue;

        static 
        {
            MODE_FULL_SETUP = new SetupWizardMode("MODE_FULL_SETUP", 0);
            MODE_ADD_PLAYERORSUB = new SetupWizardMode("MODE_ADD_PLAYERORSUB", 1);
            MODE_ADD_BOOST = new SetupWizardMode("MODE_ADD_BOOST", 2);
            MODE_SETUP_UNCONFIGURED_COMPONENT = new SetupWizardMode("MODE_SETUP_UNCONFIGURED_COMPONENT", 3);
            MODE_REGISTRATION = new SetupWizardMode("MODE_REGISTRATION", 4);
            MODE_SHARE_USAGE_DATA = new SetupWizardMode("MODE_SHARE_USAGE_DATA", 5);
            MODE_MUSIC_LIBRARY_SETUP_SUBWIZ = new SetupWizardMode("MODE_MUSIC_LIBRARY_SETUP_SUBWIZ", 6);
            MODE_CONFIGURE_SUB = new SetupWizardMode("MODE_CONFIGURE_SUB", 7);
            MODE_SPEAKER_SIZE = new SetupWizardMode("MODE_SPEAKER_SIZE", 8);
            MODE_RECALIBRATE_SUB = new SetupWizardMode("MODE_RECALIBRATE_SUB", 9);
            MODE_CHOOSE_CONFIGURE_DEVICE = new SetupWizardMode("MODE_CHOOSE_CONFIGURE_DEVICE", 10);
            MODE_JOIN_ANOTHER_HOUSEHOLD = new SetupWizardMode("MODE_JOIN_ANOTHER_HOUSEHOLD", 11);
            MODE_BETA_PROGRAM = new SetupWizardMode("MODE_BETA_PROGRAM", 12);
            SetupWizardMode asetupwizardmode[] = new SetupWizardMode[13];
            asetupwizardmode[0] = MODE_FULL_SETUP;
            asetupwizardmode[1] = MODE_ADD_PLAYERORSUB;
            asetupwizardmode[2] = MODE_ADD_BOOST;
            asetupwizardmode[3] = MODE_SETUP_UNCONFIGURED_COMPONENT;
            asetupwizardmode[4] = MODE_REGISTRATION;
            asetupwizardmode[5] = MODE_SHARE_USAGE_DATA;
            asetupwizardmode[6] = MODE_MUSIC_LIBRARY_SETUP_SUBWIZ;
            asetupwizardmode[7] = MODE_CONFIGURE_SUB;
            asetupwizardmode[8] = MODE_SPEAKER_SIZE;
            asetupwizardmode[9] = MODE_RECALIBRATE_SUB;
            asetupwizardmode[10] = MODE_CHOOSE_CONFIGURE_DEVICE;
            asetupwizardmode[11] = MODE_JOIN_ANOTHER_HOUSEHOLD;
            asetupwizardmode[12] = MODE_BETA_PROGRAM;
            $VALUES = asetupwizardmode;
        }

        private SetupWizardMode(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SetupWizardMode(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SetupWizardMode(String s, int i, SetupWizardMode setupwizardmode)
        {
            Enum(s, i);
            swigValue = setupwizardmode.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCISetupWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIWizard(sclibJNI.SWIGSCISetupWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISetupWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISetupWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISetupWizard scisetupwizard)
    {
        long l;
        if(scisetupwizard == null)
            l = 0L;
        else
            l = scisetupwizard.swigCPtr;
        return l;
    }

    public boolean completedAndIsAssociatedToHousehold()
    {
        return sclibJNI.SCISetupWizard_completedAndIsAssociatedToHousehold(swigCPtr, this);
    }

    public boolean configuringWiredComponent()
    {
        return sclibJNI.SCISetupWizard_configuringWiredComponent(swigCPtr, this);
    }

    public SCIOpDevicePropertiesGetAutoplayLinkedZones createGetAutoplayGroupedZonesOp()
    {
        long l = sclibJNI.SCISetupWizard_createGetAutoplayGroupedZonesOp(swigCPtr, this);
        SCIOpDevicePropertiesGetAutoplayLinkedZones sciopdevicepropertiesgetautoplaylinkedzones;
        if(l == 0L)
            sciopdevicepropertiesgetautoplaylinkedzones = null;
        else
            sciopdevicepropertiesgetautoplaylinkedzones = new SCIOpDevicePropertiesGetAutoplayLinkedZones(l, true);
        return sciopdevicepropertiesgetautoplaylinkedzones;
    }

    public SCIOpDevicePropertiesGetAutoplayVolume createGetAutoplayVolumeOp()
    {
        long l = sclibJNI.SCISetupWizard_createGetAutoplayVolumeOp(swigCPtr, this);
        SCIOpDevicePropertiesGetAutoplayVolume sciopdevicepropertiesgetautoplayvolume;
        if(l == 0L)
            sciopdevicepropertiesgetautoplayvolume = null;
        else
            sciopdevicepropertiesgetautoplayvolume = new SCIOpDevicePropertiesGetAutoplayVolume(l, true);
        return sciopdevicepropertiesgetautoplayvolume;
    }

    public SCIWizard deviceExtraSetupWizard()
    {
        long l = sclibJNI.SCISetupWizard_deviceExtraSetupWizard(swigCPtr, this);
        SCIWizard sciwizard;
        if(l == 0L)
            sciwizard = null;
        else
            sciwizard = new SCIWizard(l, true);
        return sciwizard;
    }

    public boolean deviceRequiresExtraSetup()
    {
        return sclibJNI.SCISetupWizard_deviceRequiresExtraSetup(swigCPtr, this);
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

    public String getAutoplayDevice()
    {
        return sclibJNI.SCISetupWizard_getAutoplayDevice(swigCPtr, this);
    }

    public SCIEnumerator getCandidateDevices()
    {
        long l = sclibJNI.SCISetupWizard_getCandidateDevices(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getConnectedDeviceID()
    {
        return sclibJNI.SCISetupWizard_getConnectedDeviceID(swigCPtr, this);
    }

    public SCIDevice.DeviceModel getDeviceModelFound()
    {
        return SCIDevice.DeviceModel.swigToEnum(sclibJNI.SCISetupWizard_getDeviceModelFound(swigCPtr, this));
    }

    public String getDeviceModelString()
    {
        return sclibJNI.SCISetupWizard_getDeviceModelString(swigCPtr, this);
    }

    public String getDeviceName()
    {
        return sclibJNI.SCISetupWizard_getDeviceName(swigCPtr, this);
    }

    public SCIStringInput getDeviceNameInput()
    {
        long l = sclibJNI.SCISetupWizard_getDeviceNameInput(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public boolean getDeviceSetupDidNotName()
    {
        return sclibJNI.SCISetupWizard_getDeviceSetupDidNotName(swigCPtr, this);
    }

    public boolean getDeviceSetupFailed()
    {
        return sclibJNI.SCISetupWizard_getDeviceSetupFailed(swigCPtr, this);
    }

    public SetupDeviceType getDeviceType()
    {
        return SetupDeviceType.swigToEnum(sclibJNI.SCISetupWizard_getDeviceType(swigCPtr, this));
    }

    public boolean getExistingHousehold()
    {
        return sclibJNI.SCISetupWizard_getExistingHousehold(swigCPtr, this);
    }

    public JoinButtonType getJoinButtonType()
    {
        return JoinButtonType.swigToEnum(sclibJNI.SCISetupWizard_getJoinButtonType(swigCPtr, this));
    }

    public SetupWizardMode getMode()
    {
        return SetupWizardMode.swigToEnum(sclibJNI.SCISetupWizard_getMode(swigCPtr, this));
    }

    public SCISubCalibrator getSubCalibrator()
    {
        long l = sclibJNI.SCISetupWizard_getSubCalibrator(swigCPtr, this);
        SCISubCalibrator scisubcalibrator;
        if(l == 0L)
            scisubcalibrator = null;
        else
            scisubcalibrator = new SCISubCalibrator(l, true);
        return scisubcalibrator;
    }

    public int getSubSpeakerSizeIndex()
    {
        return sclibJNI.SCISetupWizard_getSubSpeakerSizeIndex(swigCPtr, this);
    }

    public SCIEnumerator getUnconfiguredDevices()
    {
        long l = sclibJNI.SCISetupWizard_getUnconfiguredDevices(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public int getWifiConnectionTimeout()
    {
        return sclibJNI.SCISetupWizard_getWifiConnectionTimeout(swigCPtr, this);
    }

    public void setAddAnotherDevice(boolean flag)
    {
        sclibJNI.SCISetupWizard_setAddAnotherDevice(swigCPtr, this, flag);
    }

    public void setAutoplayDevice(String s)
    {
        sclibJNI.SCISetupWizard_setAutoplayDevice(swigCPtr, this, s);
    }

    public void setAutoplayGroupedZones(boolean flag)
    {
        sclibJNI.SCISetupWizard_setAutoplayGroupedZones(swigCPtr, this, flag);
    }

    public void setAutoplayVolume(long l)
    {
        sclibJNI.SCISetupWizard_setAutoplayVolume(swigCPtr, this, l);
    }

    public void setConnectionTimeout(int i)
    {
        sclibJNI.SCISetupWizard_setConnectionTimeout(swigCPtr, this, i);
    }

    public void setDeviceIcon(String s)
    {
        sclibJNI.SCISetupWizard_setDeviceIcon(swigCPtr, this, s);
    }

    public void setDeviceToConfigure(String s)
    {
        sclibJNI.SCISetupWizard_setDeviceToConfigure(swigCPtr, this, s);
    }

    public void setDoRegistration(boolean flag)
    {
        sclibJNI.SCISetupWizard_setDoRegistration(swigCPtr, this, flag);
    }

    public void setRequestedSubMaster(String s, String s1)
    {
        sclibJNI.SCISetupWizard_setRequestedSubMaster(swigCPtr, this, s, s1);
    }

    public void setRetryConnecting(boolean flag)
    {
        sclibJNI.SCISetupWizard_setRetryConnecting(swigCPtr, this, flag);
    }

    public void setSkipInitialVersionCheck(boolean flag)
    {
        sclibJNI.SCISetupWizard_setSkipInitialVersionCheck(swigCPtr, this, flag);
    }

    public void setSubChooseRoom(boolean flag)
    {
        sclibJNI.SCISetupWizard_setSubChooseRoom(swigCPtr, this, flag);
    }

    public void setSubPhaseBIsLouder(boolean flag)
    {
        sclibJNI.SCISetupWizard_setSubPhaseBIsLouder(swigCPtr, this, flag);
    }

    public void setSubRemove(boolean flag)
    {
        sclibJNI.SCISetupWizard_setSubRemove(swigCPtr, this, flag);
    }

    public void setSubSpeakerSizeIndex(int i)
    {
        sclibJNI.SCISetupWizard_setSubSpeakerSizeIndex(swigCPtr, this, i);
    }

    public void setSubTryAgain(boolean flag)
    {
        sclibJNI.SCISetupWizard_setSubTryAgain(swigCPtr, this, flag);
    }

    public boolean shouldShowExistingHouseholdConfirmation()
    {
        return sclibJNI.SCISetupWizard_shouldShowExistingHouseholdConfirmation(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISetupWizard");
    private long swigCPtr;

}
