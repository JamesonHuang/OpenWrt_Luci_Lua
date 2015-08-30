// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.configwireless;

import com.sonos.acr.network.WifiSetupConnectionManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.wizards.Wizard;
import com.sonos.acr.wizards.WizardState;
import com.sonos.acr.wizards.anonymous.AnonymousWizardState;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.configwireless:
//            ConfigureWirelessState, ConfigureWirelessNetworkNameState, ConfigureWirelessStateWithImage

public class ConfigureWirelessWizard extends Wizard
    implements com.sonos.acr.network.WifiSetupConnectionManager.WifiConnectionListener
{

    public ConfigureWirelessWizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCRunWizardActionType scrunwizardactiontype, SCIConfigureWirelessWizard sciconfigurewirelesswizard)
    {
        super(sciaction, sciactioncontext, scrunwizardactiontype, sciconfigurewirelesswizard);
    }

    public WizardState buildState(int i)
    {
        com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState configurewirelesswizardstate = com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.swigToEnum(i);
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIConfigureWirelessWizard$ConfigureWirelessWizardState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIConfigureWirelessWizard$ConfigureWirelessWizardState = new int[com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.values().length];
                NoSuchFieldError nosuchfielderror4;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIConfigureWirelessWizard$ConfigureWirelessWizardState[com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.STATE_CONFIGUREWIRELESS_CREDENTIAL_PAGE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIConfigureWirelessWizard$ConfigureWirelessWizardState[com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.STATE_CONFIGUREWIRELESS_NETWORK_NAME.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIConfigureWirelessWizard$ConfigureWirelessWizardState[com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.STATE_CONFIGUREWIRELESS_ERROR.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIConfigureWirelessWizard$ConfigureWirelessWizardState[com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.STATE_CONFIGUREWIRELESS_SUBMITTING.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$com$sonos$sclib$SCIConfigureWirelessWizard$ConfigureWirelessWizardState[com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.STATE_CONFIGUREWIRELESS_WIFISETUP_INFO.ordinal()] = 5;
_L2:
                return;
                nosuchfielderror4;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState[configurewirelesswizardstate.ordinal()];
        JVM INSTR tableswitch 1 5: default 48
    //                   1 62
    //                   2 84
    //                   3 97
    //                   4 113
    //                   5 128;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        Object obj = new ConfigureWirelessState(this, configurewirelesswizardstate, 0x7f0300b3);
_L8:
        return ((WizardState) (obj));
_L2:
        obj = new AnonymousWizardState(this, ((SCIConfigureWirelessWizard)sciWizard).getWizardComponentsForCurrentState());
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new ConfigureWirelessNetworkNameState(this, configurewirelesswizardstate);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new ConfigureWirelessState(this, configurewirelesswizardstate, 0x7f030078, true);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new ConfigureWirelessState(this, configurewirelesswizardstate, 0x7f0300b4);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new ConfigureWirelessStateWithImage(this, configurewirelesswizardstate, 0x7f030015);
        if(true) goto _L8; else goto _L7
_L7:
    }

    public boolean canRunWithoutWifi()
    {
        return true;
    }

    protected SCIConfigureWirelessWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return null;
    }

    protected volatile SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return createSCIWizard(scrunwizardactiontype);
    }

    public int getIdForState(Object obj)
    {
        return ((com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState)obj).swigValue();
    }

    public String getNameForState(int i)
    {
        return com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.swigToEnum(i).toString();
    }

    public CharSequence getRecommendedText(com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizStringID configurewirelesswizstringid)
    {
        return super.getRecommendedText(configurewirelesswizstringid.swigValue());
    }

    public Object[] getStates()
    {
        return com.sonos.sclib.SCIConfigureWirelessWizard.ConfigureWirelessWizardState.values();
    }

    public void onComplete()
    {
        super.onComplete();
        LibraryUtils.getSCLibManager().getWifiSetupDelegate().setConnectionListener(null);
    }

    public boolean onUnexpectedSonosDisconnect()
    {
        return ((SCIConfigureWirelessWizard)sciWizard).unexpectedDisconnectFromSonosAP();
    }

    public boolean shouldWarp()
    {
        return false;
    }

    public void start()
    {
        super.start();
        LibraryUtils.getSCLibManager().getWifiSetupDelegate().setConnectionListener(this);
    }

    public static final String LOG_TAG = com/sonos/acr/wizards/configwireless/ConfigureWirelessWizard.getSimpleName();

}
