// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIWizard, sclibJNI, SCIOpNetstartGetScanList, SCIPropertyBag, 
//            SCIStringInput, SCIEnumerator

public class SCIConfigureWirelessWizard extends SCIWizard
{
    public static final class ConfigureWirelessWizStringID extends Enum
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


        public static ConfigureWirelessWizStringID swigToEnum(int i)
        {
            ConfigureWirelessWizStringID aconfigurewirelesswizstringid[] = (ConfigureWirelessWizStringID[])com/sonos/sclib/SCIConfigureWirelessWizard$ConfigureWirelessWizStringID.getEnumConstants();
            if(i >= aconfigurewirelesswizstringid.length || i < 0 || aconfigurewirelesswizstringid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ConfigureWirelessWizStringID configurewirelesswizstringid1 = aconfigurewirelesswizstringid[i];
_L4:
            return configurewirelesswizstringid1;
_L2:
            int j = aconfigurewirelesswizstringid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ConfigureWirelessWizStringID configurewirelesswizstringid = aconfigurewirelesswizstringid[k];
                if(configurewirelesswizstringid.swigValue == i)
                {
                    configurewirelesswizstringid1 = configurewirelesswizstringid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIConfigureWirelessWizard$ConfigureWirelessWizStringID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ConfigureWirelessWizStringID valueOf(String s)
        {
            return (ConfigureWirelessWizStringID)Enum.valueOf(com/sonos/sclib/SCIConfigureWirelessWizard$ConfigureWirelessWizStringID, s);
        }

        public static ConfigureWirelessWizStringID[] values()
        {
            return (ConfigureWirelessWizStringID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ConfigureWirelessWizStringID $VALUES[];
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_BODY;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_BODY_1;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_BODY_2;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_BODY_3;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_BODY_4;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_INPUT_1;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_INPUT_2;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_INPUT_3;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_INPUT_4;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_LABEL_1;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_LABEL_2;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_LABEL_3;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_MAX;
        public static final ConfigureWirelessWizStringID CONFIGUREWIRELESS_STRID_TITLE_1;
        private final int swigValue;

        static 
        {
            CONFIGUREWIRELESS_STRID_TITLE_1 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_TITLE_1", 0, sclibJNI.SCIConfigureWirelessWizard_CONFIGUREWIRELESS_STRID_TITLE_1_get());
            CONFIGUREWIRELESS_STRID_BODY = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_BODY", 1, sclibJNI.SCIConfigureWirelessWizard_CONFIGUREWIRELESS_STRID_BODY_get());
            CONFIGUREWIRELESS_STRID_BODY_1 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_BODY_1", 2);
            CONFIGUREWIRELESS_STRID_BODY_2 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_BODY_2", 3);
            CONFIGUREWIRELESS_STRID_BODY_3 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_BODY_3", 4);
            CONFIGUREWIRELESS_STRID_BODY_4 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_BODY_4", 5);
            CONFIGUREWIRELESS_STRID_INPUT_1 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_INPUT_1", 6);
            CONFIGUREWIRELESS_STRID_INPUT_2 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_INPUT_2", 7);
            CONFIGUREWIRELESS_STRID_INPUT_3 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_INPUT_3", 8);
            CONFIGUREWIRELESS_STRID_INPUT_4 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_INPUT_4", 9);
            CONFIGUREWIRELESS_STRID_LABEL_1 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_LABEL_1", 10);
            CONFIGUREWIRELESS_STRID_LABEL_2 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_LABEL_2", 11);
            CONFIGUREWIRELESS_STRID_LABEL_3 = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_LABEL_3", 12);
            CONFIGUREWIRELESS_STRID_MAX = new ConfigureWirelessWizStringID("CONFIGUREWIRELESS_STRID_MAX", 13);
            ConfigureWirelessWizStringID aconfigurewirelesswizstringid[] = new ConfigureWirelessWizStringID[14];
            aconfigurewirelesswizstringid[0] = CONFIGUREWIRELESS_STRID_TITLE_1;
            aconfigurewirelesswizstringid[1] = CONFIGUREWIRELESS_STRID_BODY;
            aconfigurewirelesswizstringid[2] = CONFIGUREWIRELESS_STRID_BODY_1;
            aconfigurewirelesswizstringid[3] = CONFIGUREWIRELESS_STRID_BODY_2;
            aconfigurewirelesswizstringid[4] = CONFIGUREWIRELESS_STRID_BODY_3;
            aconfigurewirelesswizstringid[5] = CONFIGUREWIRELESS_STRID_BODY_4;
            aconfigurewirelesswizstringid[6] = CONFIGUREWIRELESS_STRID_INPUT_1;
            aconfigurewirelesswizstringid[7] = CONFIGUREWIRELESS_STRID_INPUT_2;
            aconfigurewirelesswizstringid[8] = CONFIGUREWIRELESS_STRID_INPUT_3;
            aconfigurewirelesswizstringid[9] = CONFIGUREWIRELESS_STRID_INPUT_4;
            aconfigurewirelesswizstringid[10] = CONFIGUREWIRELESS_STRID_LABEL_1;
            aconfigurewirelesswizstringid[11] = CONFIGUREWIRELESS_STRID_LABEL_2;
            aconfigurewirelesswizstringid[12] = CONFIGUREWIRELESS_STRID_LABEL_3;
            aconfigurewirelesswizstringid[13] = CONFIGUREWIRELESS_STRID_MAX;
            $VALUES = aconfigurewirelesswizstringid;
        }

        private ConfigureWirelessWizStringID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ConfigureWirelessWizStringID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ConfigureWirelessWizStringID(String s, int i, ConfigureWirelessWizStringID configurewirelesswizstringid)
        {
            Enum(s, i);
            swigValue = configurewirelesswizstringid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class ConfigureWirelessWizardState extends Enum
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


        public static ConfigureWirelessWizardState swigToEnum(int i)
        {
            ConfigureWirelessWizardState aconfigurewirelesswizardstate[] = (ConfigureWirelessWizardState[])com/sonos/sclib/SCIConfigureWirelessWizard$ConfigureWirelessWizardState.getEnumConstants();
            if(i >= aconfigurewirelesswizardstate.length || i < 0 || aconfigurewirelesswizardstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ConfigureWirelessWizardState configurewirelesswizardstate1 = aconfigurewirelesswizardstate[i];
_L4:
            return configurewirelesswizardstate1;
_L2:
            int j = aconfigurewirelesswizardstate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ConfigureWirelessWizardState configurewirelesswizardstate = aconfigurewirelesswizardstate[k];
                if(configurewirelesswizardstate.swigValue == i)
                {
                    configurewirelesswizardstate1 = configurewirelesswizardstate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIConfigureWirelessWizard$ConfigureWirelessWizardState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ConfigureWirelessWizardState valueOf(String s)
        {
            return (ConfigureWirelessWizardState)Enum.valueOf(com/sonos/sclib/SCIConfigureWirelessWizard$ConfigureWirelessWizardState, s);
        }

        public static ConfigureWirelessWizardState[] values()
        {
            return (ConfigureWirelessWizardState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ConfigureWirelessWizardState $VALUES[];
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_CLEARED;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_COMPLETE;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_CREDENTIAL_PAGE;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_DONE;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_ERROR;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_INIT;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_MAX;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_MUST_BE_WIRED;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_NETWORK_NAME;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_PERM_WIRE;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_START_WIFI;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_SUBMITTING;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_UNKNOWN;
        public static final ConfigureWirelessWizardState STATE_CONFIGUREWIRELESS_WIFISETUP_INFO;
        private final int swigValue;

        static 
        {
            STATE_CONFIGUREWIRELESS_UNKNOWN = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_UNKNOWN", 0, sclibJNI.SCIConfigureWirelessWizard_STATE_CONFIGUREWIRELESS_UNKNOWN_get());
            STATE_CONFIGUREWIRELESS_INIT = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_INIT", 1, sclibJNI.SCIConfigureWirelessWizard_STATE_CONFIGUREWIRELESS_INIT_get());
            STATE_CONFIGUREWIRELESS_COMPLETE = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_COMPLETE", 2, sclibJNI.SCIConfigureWirelessWizard_STATE_CONFIGUREWIRELESS_COMPLETE_get());
            STATE_CONFIGUREWIRELESS_START_WIFI = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_START_WIFI", 3);
            STATE_CONFIGUREWIRELESS_CREDENTIAL_PAGE = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_CREDENTIAL_PAGE", 4);
            STATE_CONFIGUREWIRELESS_SUBMITTING = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_SUBMITTING", 5);
            STATE_CONFIGUREWIRELESS_DONE = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_DONE", 6);
            STATE_CONFIGUREWIRELESS_NETWORK_NAME = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_NETWORK_NAME", 7);
            STATE_CONFIGUREWIRELESS_ERROR = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_ERROR", 8);
            STATE_CONFIGUREWIRELESS_MUST_BE_WIRED = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_MUST_BE_WIRED", 9);
            STATE_CONFIGUREWIRELESS_CLEARED = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_CLEARED", 10);
            STATE_CONFIGUREWIRELESS_PERM_WIRE = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_PERM_WIRE", 11);
            STATE_CONFIGUREWIRELESS_WIFISETUP_INFO = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_WIFISETUP_INFO", 12);
            STATE_CONFIGUREWIRELESS_MAX = new ConfigureWirelessWizardState("STATE_CONFIGUREWIRELESS_MAX", 13);
            ConfigureWirelessWizardState aconfigurewirelesswizardstate[] = new ConfigureWirelessWizardState[14];
            aconfigurewirelesswizardstate[0] = STATE_CONFIGUREWIRELESS_UNKNOWN;
            aconfigurewirelesswizardstate[1] = STATE_CONFIGUREWIRELESS_INIT;
            aconfigurewirelesswizardstate[2] = STATE_CONFIGUREWIRELESS_COMPLETE;
            aconfigurewirelesswizardstate[3] = STATE_CONFIGUREWIRELESS_START_WIFI;
            aconfigurewirelesswizardstate[4] = STATE_CONFIGUREWIRELESS_CREDENTIAL_PAGE;
            aconfigurewirelesswizardstate[5] = STATE_CONFIGUREWIRELESS_SUBMITTING;
            aconfigurewirelesswizardstate[6] = STATE_CONFIGUREWIRELESS_DONE;
            aconfigurewirelesswizardstate[7] = STATE_CONFIGUREWIRELESS_NETWORK_NAME;
            aconfigurewirelesswizardstate[8] = STATE_CONFIGUREWIRELESS_ERROR;
            aconfigurewirelesswizardstate[9] = STATE_CONFIGUREWIRELESS_MUST_BE_WIRED;
            aconfigurewirelesswizardstate[10] = STATE_CONFIGUREWIRELESS_CLEARED;
            aconfigurewirelesswizardstate[11] = STATE_CONFIGUREWIRELESS_PERM_WIRE;
            aconfigurewirelesswizardstate[12] = STATE_CONFIGUREWIRELESS_WIFISETUP_INFO;
            aconfigurewirelesswizardstate[13] = STATE_CONFIGUREWIRELESS_MAX;
            $VALUES = aconfigurewirelesswizardstate;
        }

        private ConfigureWirelessWizardState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ConfigureWirelessWizardState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ConfigureWirelessWizardState(String s, int i, ConfigureWirelessWizardState configurewirelesswizardstate)
        {
            Enum(s, i);
            swigValue = configurewirelesswizardstate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIConfigureWirelessWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIWizard(sclibJNI.SWIGSCIConfigureWirelessWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIConfigureWirelessWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIConfigureWirelessWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIConfigureWirelessWizard sciconfigurewirelesswizard)
    {
        long l;
        if(sciconfigurewirelesswizard == null)
            l = 0L;
        else
            l = sciconfigurewirelesswizard.swigCPtr;
        return l;
    }

    public SCIOpNetstartGetScanList createSCINetstartGetScanListOp()
    {
        long l = sclibJNI.SCIConfigureWirelessWizard_createSCINetstartGetScanListOp(swigCPtr, this);
        SCIOpNetstartGetScanList sciopnetstartgetscanlist;
        if(l == 0L)
            sciopnetstartgetscanlist = null;
        else
            sciopnetstartgetscanlist = new SCIOpNetstartGetScanList(l, true);
        return sciopnetstartgetscanlist;
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

    public SCIPropertyBag getOriginalWifiInfo()
    {
        long l = sclibJNI.SCIConfigureWirelessWizard_getOriginalWifiInfo(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public SCIStringInput getPasswordInput()
    {
        long l = sclibJNI.SCIConfigureWirelessWizard_getPasswordInput(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public String getSSID()
    {
        return sclibJNI.SCIConfigureWirelessWizard_getSSID(swigCPtr, this);
    }

    public SCIStringInput getSSIDInput()
    {
        long l = sclibJNI.SCIConfigureWirelessWizard_getSSIDInput(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public SCIEnumerator getScanListEntries()
    {
        long l = sclibJNI.SCIConfigureWirelessWizard_getScanListEntries(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public int getScanListNumEntries()
    {
        return sclibJNI.SCIConfigureWirelessWizard_getScanListNumEntries(swigCPtr, this);
    }

    public boolean hasPassword()
    {
        return sclibJNI.SCIConfigureWirelessWizard_hasPassword(swigCPtr, this);
    }

    public boolean isOpenNetwork(String s)
    {
        return sclibJNI.SCIConfigureWirelessWizard_isOpenNetwork(swigCPtr, this, s);
    }

    public void setPassword(String s)
    {
        sclibJNI.SCIConfigureWirelessWizard_setPassword(swigCPtr, this, s);
    }

    public void setPropBagProp(String s, SCIPropertyBag scipropertybag)
    {
        sclibJNI.SCIConfigureWirelessWizard_setPropBagProp(swigCPtr, this, s, SCIPropertyBag.getCPtr(scipropertybag), scipropertybag);
    }

    public void setSSID(String s)
    {
        sclibJNI.SCIConfigureWirelessWizard_setSSID(swigCPtr, this, s);
    }

    public boolean unexpectedDisconnectFromSonosAP()
    {
        return sclibJNI.SCIConfigureWirelessWizard_unexpectedDisconnectFromSonosAP(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIConfigureWirelessWizard");
    private long swigCPtr;

}
