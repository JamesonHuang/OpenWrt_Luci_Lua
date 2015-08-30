// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIWizard, sclibJNI, SCISubCalibrator

public class SCISoundbarWizard extends SCIWizard
{
    public static final class SoundbarDistance extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$502(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$508()
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


        public static SoundbarDistance swigToEnum(int i)
        {
            SoundbarDistance asoundbardistance[] = (SoundbarDistance[])com/sonos/sclib/SCISoundbarWizard$SoundbarDistance.getEnumConstants();
            if(i >= asoundbardistance.length || i < 0 || asoundbardistance[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SoundbarDistance soundbardistance1 = asoundbardistance[i];
_L4:
            return soundbardistance1;
_L2:
            int j = asoundbardistance.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SoundbarDistance soundbardistance = asoundbardistance[k];
                if(soundbardistance.swigValue == i)
                {
                    soundbardistance1 = soundbardistance;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISoundbarWizard$SoundbarDistance).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SoundbarDistance valueOf(String s)
        {
            return (SoundbarDistance)Enum.valueOf(com/sonos/sclib/SCISoundbarWizard$SoundbarDistance, s);
        }

        public static SoundbarDistance[] values()
        {
            return (SoundbarDistance[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SoundbarDistance $VALUES[];
        public static final SoundbarDistance SOUNDBAR_DISTANCE_ADJACENT;
        public static final SoundbarDistance SOUNDBAR_DISTANCE_CLOSE;
        public static final SoundbarDistance SOUNDBAR_DISTANCE_DEFAULT;
        private final int swigValue;

        static 
        {
            SOUNDBAR_DISTANCE_DEFAULT = new SoundbarDistance("SOUNDBAR_DISTANCE_DEFAULT", 0);
            SOUNDBAR_DISTANCE_CLOSE = new SoundbarDistance("SOUNDBAR_DISTANCE_CLOSE", 1);
            SOUNDBAR_DISTANCE_ADJACENT = new SoundbarDistance("SOUNDBAR_DISTANCE_ADJACENT", 2);
            SoundbarDistance asoundbardistance[] = new SoundbarDistance[3];
            asoundbardistance[0] = SOUNDBAR_DISTANCE_DEFAULT;
            asoundbardistance[1] = SOUNDBAR_DISTANCE_CLOSE;
            asoundbardistance[2] = SOUNDBAR_DISTANCE_ADJACENT;
            $VALUES = asoundbardistance;
        }

        private SoundbarDistance(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SoundbarDistance(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SoundbarDistance(String s, int i, SoundbarDistance soundbardistance)
        {
            Enum(s, i);
            swigValue = soundbardistance.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SoundbarComponent extends Enum
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


        public static SoundbarComponent swigToEnum(int i)
        {
            SoundbarComponent asoundbarcomponent[] = (SoundbarComponent[])com/sonos/sclib/SCISoundbarWizard$SoundbarComponent.getEnumConstants();
            if(i >= asoundbarcomponent.length || i < 0 || asoundbarcomponent[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SoundbarComponent soundbarcomponent1 = asoundbarcomponent[i];
_L4:
            return soundbarcomponent1;
_L2:
            int j = asoundbarcomponent.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SoundbarComponent soundbarcomponent = asoundbarcomponent[k];
                if(soundbarcomponent.swigValue == i)
                {
                    soundbarcomponent1 = soundbarcomponent;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISoundbarWizard$SoundbarComponent).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SoundbarComponent valueOf(String s)
        {
            return (SoundbarComponent)Enum.valueOf(com/sonos/sclib/SCISoundbarWizard$SoundbarComponent, s);
        }

        public static SoundbarComponent[] values()
        {
            return (SoundbarComponent[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SoundbarComponent $VALUES[];
        public static final SoundbarComponent SOUNDBAR_COMP_LEFT_SURROUND;
        public static final SoundbarComponent SOUNDBAR_COMP_RIGHT_SURROUND;
        public static final SoundbarComponent SOUNDBAR_COMP_SOUNDBAR;
        public static final SoundbarComponent SOUNDBAR_COMP_SUB;
        private final int swigValue;

        static 
        {
            SOUNDBAR_COMP_SOUNDBAR = new SoundbarComponent("SOUNDBAR_COMP_SOUNDBAR", 0);
            SOUNDBAR_COMP_SUB = new SoundbarComponent("SOUNDBAR_COMP_SUB", 1);
            SOUNDBAR_COMP_LEFT_SURROUND = new SoundbarComponent("SOUNDBAR_COMP_LEFT_SURROUND", 2);
            SOUNDBAR_COMP_RIGHT_SURROUND = new SoundbarComponent("SOUNDBAR_COMP_RIGHT_SURROUND", 3);
            SoundbarComponent asoundbarcomponent[] = new SoundbarComponent[4];
            asoundbarcomponent[0] = SOUNDBAR_COMP_SOUNDBAR;
            asoundbarcomponent[1] = SOUNDBAR_COMP_SUB;
            asoundbarcomponent[2] = SOUNDBAR_COMP_LEFT_SURROUND;
            asoundbarcomponent[3] = SOUNDBAR_COMP_RIGHT_SURROUND;
            $VALUES = asoundbarcomponent;
        }

        private SoundbarComponent(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SoundbarComponent(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SoundbarComponent(String s, int i, SoundbarComponent soundbarcomponent)
        {
            Enum(s, i);
            swigValue = soundbarcomponent.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SoundbarWizStringID extends Enum
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


        public static SoundbarWizStringID swigToEnum(int i)
        {
            SoundbarWizStringID asoundbarwizstringid[] = (SoundbarWizStringID[])com/sonos/sclib/SCISoundbarWizard$SoundbarWizStringID.getEnumConstants();
            if(i >= asoundbarwizstringid.length || i < 0 || asoundbarwizstringid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SoundbarWizStringID soundbarwizstringid1 = asoundbarwizstringid[i];
_L4:
            return soundbarwizstringid1;
_L2:
            int j = asoundbarwizstringid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SoundbarWizStringID soundbarwizstringid = asoundbarwizstringid[k];
                if(soundbarwizstringid.swigValue == i)
                {
                    soundbarwizstringid1 = soundbarwizstringid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISoundbarWizard$SoundbarWizStringID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SoundbarWizStringID valueOf(String s)
        {
            return (SoundbarWizStringID)Enum.valueOf(com/sonos/sclib/SCISoundbarWizard$SoundbarWizStringID, s);
        }

        public static SoundbarWizStringID[] values()
        {
            return (SoundbarWizStringID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SoundbarWizStringID $VALUES[];
        public static final SoundbarWizStringID SOUNDBAR_STRID_BODY;
        public static final SoundbarWizStringID SOUNDBAR_STRID_BODY_1;
        public static final SoundbarWizStringID SOUNDBAR_STRID_BODY_2;
        public static final SoundbarWizStringID SOUNDBAR_STRID_BODY_3;
        public static final SoundbarWizStringID SOUNDBAR_STRID_BODY_4;
        public static final SoundbarWizStringID SOUNDBAR_STRID_BODY_5;
        public static final SoundbarWizStringID SOUNDBAR_STRID_BODY_ALT_1;
        public static final SoundbarWizStringID SOUNDBAR_STRID_IMG_LABEL_1;
        public static final SoundbarWizStringID SOUNDBAR_STRID_IMG_LABEL_2;
        public static final SoundbarWizStringID SOUNDBAR_STRID_INPUT_1;
        public static final SoundbarWizStringID SOUNDBAR_STRID_INPUT_2;
        public static final SoundbarWizStringID SOUNDBAR_STRID_INPUT_3;
        public static final SoundbarWizStringID SOUNDBAR_STRID_INPUT_4;
        public static final SoundbarWizStringID SOUNDBAR_STRID_INPUT_ALT_1;
        public static final SoundbarWizStringID SOUNDBAR_STRID_INPUT_ALT_2;
        public static final SoundbarWizStringID SOUNDBAR_STRID_MAX;
        public static final SoundbarWizStringID SOUNDBAR_STRID_TITLE_1;
        public static final SoundbarWizStringID SOUNDBAR_STRID_TITLE_2;
        public static final SoundbarWizStringID SOUNDBAR_STRID_WIZARD_INSTRUCTIONS;
        private final int swigValue;

        static 
        {
            SOUNDBAR_STRID_TITLE_1 = new SoundbarWizStringID("SOUNDBAR_STRID_TITLE_1", 0, sclibJNI.SCISoundbarWizard_SOUNDBAR_STRID_TITLE_1_get());
            SOUNDBAR_STRID_BODY = new SoundbarWizStringID("SOUNDBAR_STRID_BODY", 1, sclibJNI.SCISoundbarWizard_SOUNDBAR_STRID_BODY_get());
            SOUNDBAR_STRID_BODY_1 = new SoundbarWizStringID("SOUNDBAR_STRID_BODY_1", 2);
            SOUNDBAR_STRID_BODY_2 = new SoundbarWizStringID("SOUNDBAR_STRID_BODY_2", 3);
            SOUNDBAR_STRID_BODY_3 = new SoundbarWizStringID("SOUNDBAR_STRID_BODY_3", 4);
            SOUNDBAR_STRID_BODY_4 = new SoundbarWizStringID("SOUNDBAR_STRID_BODY_4", 5);
            SOUNDBAR_STRID_BODY_5 = new SoundbarWizStringID("SOUNDBAR_STRID_BODY_5", 6);
            SOUNDBAR_STRID_INPUT_1 = new SoundbarWizStringID("SOUNDBAR_STRID_INPUT_1", 7);
            SOUNDBAR_STRID_INPUT_2 = new SoundbarWizStringID("SOUNDBAR_STRID_INPUT_2", 8);
            SOUNDBAR_STRID_INPUT_3 = new SoundbarWizStringID("SOUNDBAR_STRID_INPUT_3", 9);
            SOUNDBAR_STRID_INPUT_4 = new SoundbarWizStringID("SOUNDBAR_STRID_INPUT_4", 10);
            SOUNDBAR_STRID_TITLE_2 = new SoundbarWizStringID("SOUNDBAR_STRID_TITLE_2", 11);
            SOUNDBAR_STRID_BODY_ALT_1 = new SoundbarWizStringID("SOUNDBAR_STRID_BODY_ALT_1", 12);
            SOUNDBAR_STRID_INPUT_ALT_1 = new SoundbarWizStringID("SOUNDBAR_STRID_INPUT_ALT_1", 13);
            SOUNDBAR_STRID_INPUT_ALT_2 = new SoundbarWizStringID("SOUNDBAR_STRID_INPUT_ALT_2", 14);
            SOUNDBAR_STRID_WIZARD_INSTRUCTIONS = new SoundbarWizStringID("SOUNDBAR_STRID_WIZARD_INSTRUCTIONS", 15);
            SOUNDBAR_STRID_IMG_LABEL_1 = new SoundbarWizStringID("SOUNDBAR_STRID_IMG_LABEL_1", 16);
            SOUNDBAR_STRID_IMG_LABEL_2 = new SoundbarWizStringID("SOUNDBAR_STRID_IMG_LABEL_2", 17);
            SOUNDBAR_STRID_MAX = new SoundbarWizStringID("SOUNDBAR_STRID_MAX", 18);
            SoundbarWizStringID asoundbarwizstringid[] = new SoundbarWizStringID[19];
            asoundbarwizstringid[0] = SOUNDBAR_STRID_TITLE_1;
            asoundbarwizstringid[1] = SOUNDBAR_STRID_BODY;
            asoundbarwizstringid[2] = SOUNDBAR_STRID_BODY_1;
            asoundbarwizstringid[3] = SOUNDBAR_STRID_BODY_2;
            asoundbarwizstringid[4] = SOUNDBAR_STRID_BODY_3;
            asoundbarwizstringid[5] = SOUNDBAR_STRID_BODY_4;
            asoundbarwizstringid[6] = SOUNDBAR_STRID_BODY_5;
            asoundbarwizstringid[7] = SOUNDBAR_STRID_INPUT_1;
            asoundbarwizstringid[8] = SOUNDBAR_STRID_INPUT_2;
            asoundbarwizstringid[9] = SOUNDBAR_STRID_INPUT_3;
            asoundbarwizstringid[10] = SOUNDBAR_STRID_INPUT_4;
            asoundbarwizstringid[11] = SOUNDBAR_STRID_TITLE_2;
            asoundbarwizstringid[12] = SOUNDBAR_STRID_BODY_ALT_1;
            asoundbarwizstringid[13] = SOUNDBAR_STRID_INPUT_ALT_1;
            asoundbarwizstringid[14] = SOUNDBAR_STRID_INPUT_ALT_2;
            asoundbarwizstringid[15] = SOUNDBAR_STRID_WIZARD_INSTRUCTIONS;
            asoundbarwizstringid[16] = SOUNDBAR_STRID_IMG_LABEL_1;
            asoundbarwizstringid[17] = SOUNDBAR_STRID_IMG_LABEL_2;
            asoundbarwizstringid[18] = SOUNDBAR_STRID_MAX;
            $VALUES = asoundbarwizstringid;
        }

        private SoundbarWizStringID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SoundbarWizStringID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SoundbarWizStringID(String s, int i, SoundbarWizStringID soundbarwizstringid)
        {
            Enum(s, i);
            swigValue = soundbarwizstringid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SoundbarWizardState extends Enum
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


        public static SoundbarWizardState swigToEnum(int i)
        {
            SoundbarWizardState asoundbarwizardstate[] = (SoundbarWizardState[])com/sonos/sclib/SCISoundbarWizard$SoundbarWizardState.getEnumConstants();
            if(i >= asoundbarwizardstate.length || i < 0 || asoundbarwizardstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SoundbarWizardState soundbarwizardstate1 = asoundbarwizardstate[i];
_L4:
            return soundbarwizardstate1;
_L2:
            int j = asoundbarwizardstate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SoundbarWizardState soundbarwizardstate = asoundbarwizardstate[k];
                if(soundbarwizardstate.swigValue == i)
                {
                    soundbarwizardstate1 = soundbarwizardstate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISoundbarWizard$SoundbarWizardState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SoundbarWizardState valueOf(String s)
        {
            return (SoundbarWizardState)Enum.valueOf(com/sonos/sclib/SCISoundbarWizard$SoundbarWizardState, s);
        }

        public static SoundbarWizardState[] values()
        {
            return (SoundbarWizardState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SoundbarWizardState $VALUES[];
        public static final SoundbarWizardState STATE_SOUNDBAR_ADD_SURROUND_PREP;
        public static final SoundbarWizardState STATE_SOUNDBAR_CALIBRATION_DONE;
        public static final SoundbarWizardState STATE_SOUNDBAR_CALIBRATION_PREP;
        public static final SoundbarWizardState STATE_SOUNDBAR_CALIBRATION_PREP2;
        public static final SoundbarWizardState STATE_SOUNDBAR_COMPLETE;
        public static final SoundbarWizardState STATE_SOUNDBAR_CONFIGURE_SUB;
        public static final SoundbarWizardState STATE_SOUNDBAR_DELAYED_BONDING;
        public static final SoundbarWizardState STATE_SOUNDBAR_DELAYED_BONDING_ERROR;
        public static final SoundbarWizardState STATE_SOUNDBAR_EXTENDER_PROBLEM;
        public static final SoundbarWizardState STATE_SOUNDBAR_INIT;
        public static final SoundbarWizardState STATE_SOUNDBAR_INSECURE_PLAYER;
        public static final SoundbarWizardState STATE_SOUNDBAR_MAX;
        public static final SoundbarWizardState STATE_SOUNDBAR_NOTVSIGNAL_CONTINUE;
        public static final SoundbarWizardState STATE_SOUNDBAR_NOTVSIGNAL_ERR;
        public static final SoundbarWizardState STATE_SOUNDBAR_OTHER_COMPONENTS;
        public static final SoundbarWizardState STATE_SOUNDBAR_PARTIAL_SURROUND;
        public static final SoundbarWizardState STATE_SOUNDBAR_REMOTE_ENTRY;
        public static final SoundbarWizardState STATE_SOUNDBAR_REMOVE_SURROUND;
        public static final SoundbarWizardState STATE_SOUNDBAR_REMOVE_SURROUND_INTRO;
        public static final SoundbarWizardState STATE_SOUNDBAR_REMOVE_SURROUND_RESULT;
        public static final SoundbarWizardState STATE_SOUNDBAR_REPLACE_REMOTE;
        public static final SoundbarWizardState STATE_SOUNDBAR_SPDIF_SIGNAL;
        public static final SoundbarWizardState STATE_SOUNDBAR_SPEAKER_CHECK;
        public static final SoundbarWizardState STATE_SOUNDBAR_SPEAKER_PLAY;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_CALIB_INIT;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_CONNECTING;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_CONNECTOK;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_ERROR;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_LEVEL;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_NOSUB;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_NOTASUB_EXISTING;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_NOTASUB_NEW;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_PHASE;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_POWER;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_STILLADD;
        public static final SoundbarWizardState STATE_SOUNDBAR_SUB_WAITING;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_CALIBRATION;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_CHOOSE_SIDE;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_CONFIGURE;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_CONNECTING;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_CONNECTOK;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_ERROR;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_EXISTING;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_MATCH;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_NOSURROUND;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_NOT_COMPATIBLE;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_PLACE;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_STILLADD;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_UNUSED_PLAYER;
        public static final SoundbarWizardState STATE_SOUNDBAR_SURROUND_WAITING;
        public static final SoundbarWizardState STATE_SOUNDBAR_TURN_AUTOPLAY_ON;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_DETECT;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_INTRO;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_NAG_INTRO;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_NAG_MESSAGE;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_NAG_VOLUME;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_NEW_REMOTE_DONE;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_OFF;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_PREP;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE_ALT;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE_BUTTON_HEARD;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE_HEARD;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE_KNOWN_REMOTE;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE_LEARN_NEW_BUTTON;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE_ONE_LEARN;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_REMOTE_TIMEOUT;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_SPEAKERS;
        public static final SoundbarWizardState STATE_SOUNDBAR_TV_ZEROVOL;
        public static final SoundbarWizardState STATE_SOUNDBAR_UNKNOWN;
        public static final SoundbarWizardState STATE_SOUNDBAR_UPDATE_DEVICES;
        public static final SoundbarWizardState STATE_SOUNDBAR_UPDATE_ERROR;
        private final int swigValue;

        static 
        {
            STATE_SOUNDBAR_UNKNOWN = new SoundbarWizardState("STATE_SOUNDBAR_UNKNOWN", 0, sclibJNI.SCISoundbarWizard_STATE_SOUNDBAR_UNKNOWN_get());
            STATE_SOUNDBAR_INIT = new SoundbarWizardState("STATE_SOUNDBAR_INIT", 1, sclibJNI.SCISoundbarWizard_STATE_SOUNDBAR_INIT_get());
            STATE_SOUNDBAR_COMPLETE = new SoundbarWizardState("STATE_SOUNDBAR_COMPLETE", 2, sclibJNI.SCISoundbarWizard_STATE_SOUNDBAR_COMPLETE_get());
            STATE_SOUNDBAR_TV_OFF = new SoundbarWizardState("STATE_SOUNDBAR_TV_OFF", 3);
            STATE_SOUNDBAR_CONFIGURE_SUB = new SoundbarWizardState("STATE_SOUNDBAR_CONFIGURE_SUB", 4);
            STATE_SOUNDBAR_SURROUND_CONFIGURE = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_CONFIGURE", 5);
            STATE_SOUNDBAR_OTHER_COMPONENTS = new SoundbarWizardState("STATE_SOUNDBAR_OTHER_COMPONENTS", 6);
            STATE_SOUNDBAR_SUB_POWER = new SoundbarWizardState("STATE_SOUNDBAR_SUB_POWER", 7);
            STATE_SOUNDBAR_SUB_WAITING = new SoundbarWizardState("STATE_SOUNDBAR_SUB_WAITING", 8);
            STATE_SOUNDBAR_SUB_CONNECTING = new SoundbarWizardState("STATE_SOUNDBAR_SUB_CONNECTING", 9);
            STATE_SOUNDBAR_SUB_NOTASUB_NEW = new SoundbarWizardState("STATE_SOUNDBAR_SUB_NOTASUB_NEW", 10);
            STATE_SOUNDBAR_SUB_NOTASUB_EXISTING = new SoundbarWizardState("STATE_SOUNDBAR_SUB_NOTASUB_EXISTING", 11);
            STATE_SOUNDBAR_SUB_CONNECTOK = new SoundbarWizardState("STATE_SOUNDBAR_SUB_CONNECTOK", 12);
            STATE_SOUNDBAR_SUB_STILLADD = new SoundbarWizardState("STATE_SOUNDBAR_SUB_STILLADD", 13);
            STATE_SOUNDBAR_SUB_NOSUB = new SoundbarWizardState("STATE_SOUNDBAR_SUB_NOSUB", 14);
            STATE_SOUNDBAR_SUB_ERROR = new SoundbarWizardState("STATE_SOUNDBAR_SUB_ERROR", 15);
            STATE_SOUNDBAR_SURROUND_PLACE = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_PLACE", 16);
            STATE_SOUNDBAR_SURROUND_WAITING = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_WAITING", 17);
            STATE_SOUNDBAR_SURROUND_CONNECTING = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_CONNECTING", 18);
            STATE_SOUNDBAR_SURROUND_NOT_COMPATIBLE = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_NOT_COMPATIBLE", 19);
            STATE_SOUNDBAR_SURROUND_CONNECTOK = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_CONNECTOK", 20);
            STATE_SOUNDBAR_SURROUND_EXISTING = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_EXISTING", 21);
            STATE_SOUNDBAR_SURROUND_STILLADD = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_STILLADD", 22);
            STATE_SOUNDBAR_DELAYED_BONDING = new SoundbarWizardState("STATE_SOUNDBAR_DELAYED_BONDING", 23);
            STATE_SOUNDBAR_DELAYED_BONDING_ERROR = new SoundbarWizardState("STATE_SOUNDBAR_DELAYED_BONDING_ERROR", 24);
            STATE_SOUNDBAR_SURROUND_NOSURROUND = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_NOSURROUND", 25);
            STATE_SOUNDBAR_SURROUND_ERROR = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_ERROR", 26);
            STATE_SOUNDBAR_UPDATE_DEVICES = new SoundbarWizardState("STATE_SOUNDBAR_UPDATE_DEVICES", 27);
            STATE_SOUNDBAR_CALIBRATION_PREP = new SoundbarWizardState("STATE_SOUNDBAR_CALIBRATION_PREP", 28);
            STATE_SOUNDBAR_CALIBRATION_PREP2 = new SoundbarWizardState("STATE_SOUNDBAR_CALIBRATION_PREP2", 29);
            STATE_SOUNDBAR_SUB_CALIB_INIT = new SoundbarWizardState("STATE_SOUNDBAR_SUB_CALIB_INIT", 30);
            STATE_SOUNDBAR_SUB_PHASE = new SoundbarWizardState("STATE_SOUNDBAR_SUB_PHASE", 31);
            STATE_SOUNDBAR_SUB_LEVEL = new SoundbarWizardState("STATE_SOUNDBAR_SUB_LEVEL", 32);
            STATE_SOUNDBAR_SURROUND_CALIBRATION = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_CALIBRATION", 33);
            STATE_SOUNDBAR_CALIBRATION_DONE = new SoundbarWizardState("STATE_SOUNDBAR_CALIBRATION_DONE", 34);
            STATE_SOUNDBAR_TV_INTRO = new SoundbarWizardState("STATE_SOUNDBAR_TV_INTRO", 35);
            STATE_SOUNDBAR_TV_PREP = new SoundbarWizardState("STATE_SOUNDBAR_TV_PREP", 36);
            STATE_SOUNDBAR_TV_DETECT = new SoundbarWizardState("STATE_SOUNDBAR_TV_DETECT", 37);
            STATE_SOUNDBAR_NOTVSIGNAL_ERR = new SoundbarWizardState("STATE_SOUNDBAR_NOTVSIGNAL_ERR", 38);
            STATE_SOUNDBAR_NOTVSIGNAL_CONTINUE = new SoundbarWizardState("STATE_SOUNDBAR_NOTVSIGNAL_CONTINUE", 39);
            STATE_SOUNDBAR_SPDIF_SIGNAL = new SoundbarWizardState("STATE_SOUNDBAR_SPDIF_SIGNAL", 40);
            STATE_SOUNDBAR_TURN_AUTOPLAY_ON = new SoundbarWizardState("STATE_SOUNDBAR_TURN_AUTOPLAY_ON", 41);
            STATE_SOUNDBAR_TV_SPEAKERS = new SoundbarWizardState("STATE_SOUNDBAR_TV_SPEAKERS", 42);
            STATE_SOUNDBAR_SPEAKER_CHECK = new SoundbarWizardState("STATE_SOUNDBAR_SPEAKER_CHECK", 43);
            STATE_SOUNDBAR_SPEAKER_PLAY = new SoundbarWizardState("STATE_SOUNDBAR_SPEAKER_PLAY", 44);
            STATE_SOUNDBAR_TV_REMOTE = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE", 45);
            STATE_SOUNDBAR_TV_ZEROVOL = new SoundbarWizardState("STATE_SOUNDBAR_TV_ZEROVOL", 46);
            STATE_SOUNDBAR_TV_REMOTE_ALT = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE_ALT", 47);
            STATE_SOUNDBAR_TV_REMOTE_ONE_LEARN = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE_ONE_LEARN", 48);
            STATE_SOUNDBAR_TV_REMOTE_KNOWN_REMOTE = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE_KNOWN_REMOTE", 49);
            STATE_SOUNDBAR_TV_REMOTE_HEARD = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE_HEARD", 50);
            STATE_SOUNDBAR_TV_REMOTE_TIMEOUT = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE_TIMEOUT", 51);
            STATE_SOUNDBAR_TV_REMOTE_LEARN_NEW_BUTTON = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE_LEARN_NEW_BUTTON", 52);
            STATE_SOUNDBAR_TV_REMOTE_BUTTON_HEARD = new SoundbarWizardState("STATE_SOUNDBAR_TV_REMOTE_BUTTON_HEARD", 53);
            STATE_SOUNDBAR_TV_NEW_REMOTE_DONE = new SoundbarWizardState("STATE_SOUNDBAR_TV_NEW_REMOTE_DONE", 54);
            STATE_SOUNDBAR_TV_NAG_INTRO = new SoundbarWizardState("STATE_SOUNDBAR_TV_NAG_INTRO", 55);
            STATE_SOUNDBAR_TV_NAG_MESSAGE = new SoundbarWizardState("STATE_SOUNDBAR_TV_NAG_MESSAGE", 56);
            STATE_SOUNDBAR_TV_NAG_VOLUME = new SoundbarWizardState("STATE_SOUNDBAR_TV_NAG_VOLUME", 57);
            STATE_SOUNDBAR_REMOTE_ENTRY = new SoundbarWizardState("STATE_SOUNDBAR_REMOTE_ENTRY", 58);
            STATE_SOUNDBAR_REPLACE_REMOTE = new SoundbarWizardState("STATE_SOUNDBAR_REPLACE_REMOTE", 59);
            STATE_SOUNDBAR_PARTIAL_SURROUND = new SoundbarWizardState("STATE_SOUNDBAR_PARTIAL_SURROUND", 60);
            STATE_SOUNDBAR_REMOVE_SURROUND_INTRO = new SoundbarWizardState("STATE_SOUNDBAR_REMOVE_SURROUND_INTRO", 61);
            STATE_SOUNDBAR_REMOVE_SURROUND = new SoundbarWizardState("STATE_SOUNDBAR_REMOVE_SURROUND", 62);
            STATE_SOUNDBAR_REMOVE_SURROUND_RESULT = new SoundbarWizardState("STATE_SOUNDBAR_REMOVE_SURROUND_RESULT", 63);
            STATE_SOUNDBAR_SURROUND_MATCH = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_MATCH", 64);
            STATE_SOUNDBAR_SURROUND_CHOOSE_SIDE = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_CHOOSE_SIDE", 65);
            STATE_SOUNDBAR_ADD_SURROUND_PREP = new SoundbarWizardState("STATE_SOUNDBAR_ADD_SURROUND_PREP", 66);
            STATE_SOUNDBAR_SURROUND_UNUSED_PLAYER = new SoundbarWizardState("STATE_SOUNDBAR_SURROUND_UNUSED_PLAYER", 67);
            STATE_SOUNDBAR_UPDATE_ERROR = new SoundbarWizardState("STATE_SOUNDBAR_UPDATE_ERROR", 68);
            STATE_SOUNDBAR_INSECURE_PLAYER = new SoundbarWizardState("STATE_SOUNDBAR_INSECURE_PLAYER", 69);
            STATE_SOUNDBAR_EXTENDER_PROBLEM = new SoundbarWizardState("STATE_SOUNDBAR_EXTENDER_PROBLEM", 70);
            STATE_SOUNDBAR_MAX = new SoundbarWizardState("STATE_SOUNDBAR_MAX", 71);
            SoundbarWizardState asoundbarwizardstate[] = new SoundbarWizardState[72];
            asoundbarwizardstate[0] = STATE_SOUNDBAR_UNKNOWN;
            asoundbarwizardstate[1] = STATE_SOUNDBAR_INIT;
            asoundbarwizardstate[2] = STATE_SOUNDBAR_COMPLETE;
            asoundbarwizardstate[3] = STATE_SOUNDBAR_TV_OFF;
            asoundbarwizardstate[4] = STATE_SOUNDBAR_CONFIGURE_SUB;
            asoundbarwizardstate[5] = STATE_SOUNDBAR_SURROUND_CONFIGURE;
            asoundbarwizardstate[6] = STATE_SOUNDBAR_OTHER_COMPONENTS;
            asoundbarwizardstate[7] = STATE_SOUNDBAR_SUB_POWER;
            asoundbarwizardstate[8] = STATE_SOUNDBAR_SUB_WAITING;
            asoundbarwizardstate[9] = STATE_SOUNDBAR_SUB_CONNECTING;
            asoundbarwizardstate[10] = STATE_SOUNDBAR_SUB_NOTASUB_NEW;
            asoundbarwizardstate[11] = STATE_SOUNDBAR_SUB_NOTASUB_EXISTING;
            asoundbarwizardstate[12] = STATE_SOUNDBAR_SUB_CONNECTOK;
            asoundbarwizardstate[13] = STATE_SOUNDBAR_SUB_STILLADD;
            asoundbarwizardstate[14] = STATE_SOUNDBAR_SUB_NOSUB;
            asoundbarwizardstate[15] = STATE_SOUNDBAR_SUB_ERROR;
            asoundbarwizardstate[16] = STATE_SOUNDBAR_SURROUND_PLACE;
            asoundbarwizardstate[17] = STATE_SOUNDBAR_SURROUND_WAITING;
            asoundbarwizardstate[18] = STATE_SOUNDBAR_SURROUND_CONNECTING;
            asoundbarwizardstate[19] = STATE_SOUNDBAR_SURROUND_NOT_COMPATIBLE;
            asoundbarwizardstate[20] = STATE_SOUNDBAR_SURROUND_CONNECTOK;
            asoundbarwizardstate[21] = STATE_SOUNDBAR_SURROUND_EXISTING;
            asoundbarwizardstate[22] = STATE_SOUNDBAR_SURROUND_STILLADD;
            asoundbarwizardstate[23] = STATE_SOUNDBAR_DELAYED_BONDING;
            asoundbarwizardstate[24] = STATE_SOUNDBAR_DELAYED_BONDING_ERROR;
            asoundbarwizardstate[25] = STATE_SOUNDBAR_SURROUND_NOSURROUND;
            asoundbarwizardstate[26] = STATE_SOUNDBAR_SURROUND_ERROR;
            asoundbarwizardstate[27] = STATE_SOUNDBAR_UPDATE_DEVICES;
            asoundbarwizardstate[28] = STATE_SOUNDBAR_CALIBRATION_PREP;
            asoundbarwizardstate[29] = STATE_SOUNDBAR_CALIBRATION_PREP2;
            asoundbarwizardstate[30] = STATE_SOUNDBAR_SUB_CALIB_INIT;
            asoundbarwizardstate[31] = STATE_SOUNDBAR_SUB_PHASE;
            asoundbarwizardstate[32] = STATE_SOUNDBAR_SUB_LEVEL;
            asoundbarwizardstate[33] = STATE_SOUNDBAR_SURROUND_CALIBRATION;
            asoundbarwizardstate[34] = STATE_SOUNDBAR_CALIBRATION_DONE;
            asoundbarwizardstate[35] = STATE_SOUNDBAR_TV_INTRO;
            asoundbarwizardstate[36] = STATE_SOUNDBAR_TV_PREP;
            asoundbarwizardstate[37] = STATE_SOUNDBAR_TV_DETECT;
            asoundbarwizardstate[38] = STATE_SOUNDBAR_NOTVSIGNAL_ERR;
            asoundbarwizardstate[39] = STATE_SOUNDBAR_NOTVSIGNAL_CONTINUE;
            asoundbarwizardstate[40] = STATE_SOUNDBAR_SPDIF_SIGNAL;
            asoundbarwizardstate[41] = STATE_SOUNDBAR_TURN_AUTOPLAY_ON;
            asoundbarwizardstate[42] = STATE_SOUNDBAR_TV_SPEAKERS;
            asoundbarwizardstate[43] = STATE_SOUNDBAR_SPEAKER_CHECK;
            asoundbarwizardstate[44] = STATE_SOUNDBAR_SPEAKER_PLAY;
            asoundbarwizardstate[45] = STATE_SOUNDBAR_TV_REMOTE;
            asoundbarwizardstate[46] = STATE_SOUNDBAR_TV_ZEROVOL;
            asoundbarwizardstate[47] = STATE_SOUNDBAR_TV_REMOTE_ALT;
            asoundbarwizardstate[48] = STATE_SOUNDBAR_TV_REMOTE_ONE_LEARN;
            asoundbarwizardstate[49] = STATE_SOUNDBAR_TV_REMOTE_KNOWN_REMOTE;
            asoundbarwizardstate[50] = STATE_SOUNDBAR_TV_REMOTE_HEARD;
            asoundbarwizardstate[51] = STATE_SOUNDBAR_TV_REMOTE_TIMEOUT;
            asoundbarwizardstate[52] = STATE_SOUNDBAR_TV_REMOTE_LEARN_NEW_BUTTON;
            asoundbarwizardstate[53] = STATE_SOUNDBAR_TV_REMOTE_BUTTON_HEARD;
            asoundbarwizardstate[54] = STATE_SOUNDBAR_TV_NEW_REMOTE_DONE;
            asoundbarwizardstate[55] = STATE_SOUNDBAR_TV_NAG_INTRO;
            asoundbarwizardstate[56] = STATE_SOUNDBAR_TV_NAG_MESSAGE;
            asoundbarwizardstate[57] = STATE_SOUNDBAR_TV_NAG_VOLUME;
            asoundbarwizardstate[58] = STATE_SOUNDBAR_REMOTE_ENTRY;
            asoundbarwizardstate[59] = STATE_SOUNDBAR_REPLACE_REMOTE;
            asoundbarwizardstate[60] = STATE_SOUNDBAR_PARTIAL_SURROUND;
            asoundbarwizardstate[61] = STATE_SOUNDBAR_REMOVE_SURROUND_INTRO;
            asoundbarwizardstate[62] = STATE_SOUNDBAR_REMOVE_SURROUND;
            asoundbarwizardstate[63] = STATE_SOUNDBAR_REMOVE_SURROUND_RESULT;
            asoundbarwizardstate[64] = STATE_SOUNDBAR_SURROUND_MATCH;
            asoundbarwizardstate[65] = STATE_SOUNDBAR_SURROUND_CHOOSE_SIDE;
            asoundbarwizardstate[66] = STATE_SOUNDBAR_ADD_SURROUND_PREP;
            asoundbarwizardstate[67] = STATE_SOUNDBAR_SURROUND_UNUSED_PLAYER;
            asoundbarwizardstate[68] = STATE_SOUNDBAR_UPDATE_ERROR;
            asoundbarwizardstate[69] = STATE_SOUNDBAR_INSECURE_PLAYER;
            asoundbarwizardstate[70] = STATE_SOUNDBAR_EXTENDER_PROBLEM;
            asoundbarwizardstate[71] = STATE_SOUNDBAR_MAX;
            $VALUES = asoundbarwizardstate;
        }

        private SoundbarWizardState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SoundbarWizardState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SoundbarWizardState(String s, int i, SoundbarWizardState soundbarwizardstate)
        {
            Enum(s, i);
            swigValue = soundbarwizardstate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SoundbarRemoteButton extends Enum
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


        public static SoundbarRemoteButton swigToEnum(int i)
        {
            SoundbarRemoteButton asoundbarremotebutton[] = (SoundbarRemoteButton[])com/sonos/sclib/SCISoundbarWizard$SoundbarRemoteButton.getEnumConstants();
            if(i >= asoundbarremotebutton.length || i < 0 || asoundbarremotebutton[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SoundbarRemoteButton soundbarremotebutton1 = asoundbarremotebutton[i];
_L4:
            return soundbarremotebutton1;
_L2:
            int j = asoundbarremotebutton.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SoundbarRemoteButton soundbarremotebutton = asoundbarremotebutton[k];
                if(soundbarremotebutton.swigValue == i)
                {
                    soundbarremotebutton1 = soundbarremotebutton;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISoundbarWizard$SoundbarRemoteButton).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SoundbarRemoteButton valueOf(String s)
        {
            return (SoundbarRemoteButton)Enum.valueOf(com/sonos/sclib/SCISoundbarWizard$SoundbarRemoteButton, s);
        }

        public static SoundbarRemoteButton[] values()
        {
            return (SoundbarRemoteButton[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SoundbarRemoteButton $VALUES[];
        public static final SoundbarRemoteButton LEARN_BUTTON_INVALID;
        public static final SoundbarRemoteButton LEARN_BUTTON_MAX;
        public static final SoundbarRemoteButton LEARN_BUTTON_MUTE;
        public static final SoundbarRemoteButton LEARN_BUTTON_VOLDOWN;
        public static final SoundbarRemoteButton LEARN_BUTTON_VOLUP;
        private final int swigValue;

        static 
        {
            LEARN_BUTTON_INVALID = new SoundbarRemoteButton("LEARN_BUTTON_INVALID", 0, sclibJNI.SCISoundbarWizard_LEARN_BUTTON_INVALID_get());
            LEARN_BUTTON_VOLUP = new SoundbarRemoteButton("LEARN_BUTTON_VOLUP", 1, sclibJNI.SCISoundbarWizard_LEARN_BUTTON_VOLUP_get());
            LEARN_BUTTON_VOLDOWN = new SoundbarRemoteButton("LEARN_BUTTON_VOLDOWN", 2);
            LEARN_BUTTON_MUTE = new SoundbarRemoteButton("LEARN_BUTTON_MUTE", 3);
            LEARN_BUTTON_MAX = new SoundbarRemoteButton("LEARN_BUTTON_MAX", 4);
            SoundbarRemoteButton asoundbarremotebutton[] = new SoundbarRemoteButton[5];
            asoundbarremotebutton[0] = LEARN_BUTTON_INVALID;
            asoundbarremotebutton[1] = LEARN_BUTTON_VOLUP;
            asoundbarremotebutton[2] = LEARN_BUTTON_VOLDOWN;
            asoundbarremotebutton[3] = LEARN_BUTTON_MUTE;
            asoundbarremotebutton[4] = LEARN_BUTTON_MAX;
            $VALUES = asoundbarremotebutton;
        }

        private SoundbarRemoteButton(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SoundbarRemoteButton(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SoundbarRemoteButton(String s, int i, SoundbarRemoteButton soundbarremotebutton)
        {
            Enum(s, i);
            swigValue = soundbarremotebutton.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SoundbarWizardMode extends Enum
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


        public static SoundbarWizardMode swigToEnum(int i)
        {
            SoundbarWizardMode asoundbarwizardmode[] = (SoundbarWizardMode[])com/sonos/sclib/SCISoundbarWizard$SoundbarWizardMode.getEnumConstants();
            if(i >= asoundbarwizardmode.length || i < 0 || asoundbarwizardmode[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SoundbarWizardMode soundbarwizardmode1 = asoundbarwizardmode[i];
_L4:
            return soundbarwizardmode1;
_L2:
            int j = asoundbarwizardmode.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SoundbarWizardMode soundbarwizardmode = asoundbarwizardmode[k];
                if(soundbarwizardmode.swigValue == i)
                {
                    soundbarwizardmode1 = soundbarwizardmode;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISoundbarWizard$SoundbarWizardMode).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SoundbarWizardMode valueOf(String s)
        {
            return (SoundbarWizardMode)Enum.valueOf(com/sonos/sclib/SCISoundbarWizard$SoundbarWizardMode, s);
        }

        public static SoundbarWizardMode[] values()
        {
            return (SoundbarWizardMode[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SoundbarWizardMode $VALUES[];
        public static final SoundbarWizardMode MODE_AUDIO_CALIBRATION;
        public static final SoundbarWizardMode MODE_FULL_SETUP;
        public static final SoundbarWizardMode MODE_REMOTE_SETUP;
        public static final SoundbarWizardMode MODE_SATELLITE_ATTACH;
        public static final SoundbarWizardMode MODE_SUB_SETUP;
        public static final SoundbarWizardMode MODE_SURROUND_SETUP;
        private final int swigValue;

        static 
        {
            MODE_FULL_SETUP = new SoundbarWizardMode("MODE_FULL_SETUP", 0);
            MODE_SUB_SETUP = new SoundbarWizardMode("MODE_SUB_SETUP", 1);
            MODE_SURROUND_SETUP = new SoundbarWizardMode("MODE_SURROUND_SETUP", 2);
            MODE_AUDIO_CALIBRATION = new SoundbarWizardMode("MODE_AUDIO_CALIBRATION", 3);
            MODE_REMOTE_SETUP = new SoundbarWizardMode("MODE_REMOTE_SETUP", 4);
            MODE_SATELLITE_ATTACH = new SoundbarWizardMode("MODE_SATELLITE_ATTACH", 5);
            SoundbarWizardMode asoundbarwizardmode[] = new SoundbarWizardMode[6];
            asoundbarwizardmode[0] = MODE_FULL_SETUP;
            asoundbarwizardmode[1] = MODE_SUB_SETUP;
            asoundbarwizardmode[2] = MODE_SURROUND_SETUP;
            asoundbarwizardmode[3] = MODE_AUDIO_CALIBRATION;
            asoundbarwizardmode[4] = MODE_REMOTE_SETUP;
            asoundbarwizardmode[5] = MODE_SATELLITE_ATTACH;
            $VALUES = asoundbarwizardmode;
        }

        private SoundbarWizardMode(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SoundbarWizardMode(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SoundbarWizardMode(String s, int i, SoundbarWizardMode soundbarwizardmode)
        {
            Enum(s, i);
            swigValue = soundbarwizardmode.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCISoundbarWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIWizard(sclibJNI.SWIGSCISoundbarWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISoundbarWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISoundbarWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISoundbarWizard scisoundbarwizard)
    {
        long l;
        if(scisoundbarwizard == null)
            l = 0L;
        else
            l = scisoundbarwizard.swigCPtr;
        return l;
    }

    public boolean completedSuccessfully()
    {
        return sclibJNI.SCISoundbarWizard_completedSuccessfully(swigCPtr, this);
    }

    public SoundbarComponent currentlyAttaching()
    {
        return SoundbarComponent.swigToEnum(sclibJNI.SCISoundbarWizard_currentlyAttaching(swigCPtr, this));
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

    public SoundbarRemoteButton getCurrentRemoteButton()
    {
        return SoundbarRemoteButton.swigToEnum(sclibJNI.SCISoundbarWizard_getCurrentRemoteButton(swigCPtr, this));
    }

    public SoundbarWizardMode getMode()
    {
        return SoundbarWizardMode.swigToEnum(sclibJNI.SCISoundbarWizard_getMode(swigCPtr, this));
    }

    public SCIWizard.WizInputSelection getSelection()
    {
        return SCIWizard.WizInputSelection.swigToEnum(sclibJNI.SCISoundbarWizard_getSelection(swigCPtr, this));
    }

    public SCIDevice.DeviceModel getSoundbarSurroundSetup()
    {
        return SCIDevice.DeviceModel.swigToEnum(sclibJNI.SCISoundbarWizard_getSoundbarSurroundSetup(swigCPtr, this));
    }

    public SCISubCalibrator getSubCalibrator()
    {
        long l = sclibJNI.SCISoundbarWizard_getSubCalibrator(swigCPtr, this);
        SCISubCalibrator scisubcalibrator;
        if(l == 0L)
            scisubcalibrator = null;
        else
            scisubcalibrator = new SCISubCalibrator(l, true);
        return scisubcalibrator;
    }

    public boolean isComponentAttached(SoundbarComponent soundbarcomponent)
    {
        return sclibJNI.SCISoundbarWizard_isComponentAttached(swigCPtr, this, soundbarcomponent.swigValue());
    }

    public void setComponentDistance(SoundbarComponent soundbarcomponent, SoundbarDistance soundbardistance)
    {
        sclibJNI.SCISoundbarWizard_setComponentDistance(swigCPtr, this, soundbarcomponent.swigValue(), soundbardistance.swigValue());
    }

    public void setComponentToAttach(SoundbarComponent soundbarcomponent)
    {
        sclibJNI.SCISoundbarWizard_setComponentToAttach(swigCPtr, this, soundbarcomponent.swigValue());
    }

    public void setInputSelected(boolean flag)
    {
        sclibJNI.SCISoundbarWizard_setInputSelected(swigCPtr, this, flag);
    }

    public void setSelection(SCIWizard.WizInputSelection wizinputselection)
    {
        sclibJNI.SCISoundbarWizard_setSelection(swigCPtr, this, wizinputselection.swigValue());
    }

    public void setSoundbarSubSetup(boolean flag)
    {
        sclibJNI.SCISoundbarWizard_setSoundbarSubSetup(swigCPtr, this, flag);
    }

    public void setSoundbarSurroundSetup(SCIDevice.DeviceModel devicemodel)
    {
        sclibJNI.SCISoundbarWizard_setSoundbarSurroundSetup(swigCPtr, this, devicemodel.swigValue());
    }

    public void setSubPhaseBIsLouder(boolean flag)
    {
        sclibJNI.SCISoundbarWizard_setSubPhaseBIsLouder(swigCPtr, this, flag);
    }

    public void setTVBuiltInDisabled(boolean flag)
    {
        sclibJNI.SCISoundbarWizard_setTVBuiltInDisabled(swigCPtr, this, flag);
    }

    public void setTryRemoteListeningAgain(boolean flag)
    {
        sclibJNI.SCISoundbarWizard_setTryRemoteListeningAgain(swigCPtr, this, flag);
    }

    public void setTryTVSignalDetectionAgain(boolean flag)
    {
        sclibJNI.SCISoundbarWizard_setTryTVSignalDetectionAgain(swigCPtr, this, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISoundbarWizard");
    private long swigCPtr;

}
