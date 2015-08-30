// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.sonosnet;

import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.wizards.Wizard;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.sonosnet:
//            SonosnetWizardState, StateSonosnetActivate

public class SonosnetWizard extends Wizard
{

    public SonosnetWizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCRunWizardActionType scrunwizardactiontype, SCISonosNetSetupWizard scisonosnetsetupwizard)
    {
        super(sciaction, sciactioncontext, scrunwizardactiontype, scisonosnetsetupwizard);
    }

    public volatile WizardState buildState(int i)
    {
        return buildState(i);
    }

    public SonosnetWizardState buildState(int i)
    {
        com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState sonosnetsetupwizardstate = com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.swigToEnum(i);
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCISonosNetSetupWizard$SonosNetSetupWizardState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCISonosNetSetupWizard$SonosNetSetupWizardState = new int[com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCISonosNetSetupWizard$SonosNetSetupWizardState[com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.STATE_SONOSNETSETUP_DETAILS.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$sonos$sclib$SCISonosNetSetupWizard$SonosNetSetupWizardState[com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.STATE_SONOSNETSETUP_CHOOSEOPTION.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState[sonosnetsetupwizardstate.ordinal()];
        JVM INSTR tableswitch 1 2: default 36
    //                   1 40
    //                   2 55;
           goto _L1 _L2 _L3
_L1:
        Object obj = null;
_L5:
        return ((SonosnetWizardState) (obj));
_L2:
        obj = new SonosnetWizardState(this, sonosnetsetupwizardstate, 0x7f0300b3);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new StateSonosnetActivate(this);
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected SCISonosNetSetupWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return LibraryUtils.getHousehold().createSonosNetSetupWizard();
    }

    protected volatile SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return createSCIWizard(scrunwizardactiontype);
    }

    public int getIdForState(Object obj)
    {
        return ((com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState)obj).swigValue();
    }

    public String getNameForState(int i)
    {
        return com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.swigToEnum(i).toString();
    }

    public Object[] getStates()
    {
        return com.sonos.sclib.SCISonosNetSetupWizard.SonosNetSetupWizardState.values();
    }

    public static final String LOG_TAG = com/sonos/acr/wizards/sonosnet/SonosnetWizard.getSimpleName();

}
