// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.musicservices;

import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.wizards.Wizard;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.musicservices:
//            StateMSLoginPassword, StateMSWorking, StateMSResult, StateMSIntro, 
//            StateMSSubscribeIntro, StateMSAccountedNeeded, StateMSTerms, StateMSComplete, 
//            StateMSMerge, StateMSPassword, MusicServicesWizardState, StateMSPromotedIntro, 
//            StateMSSetNickname, StateMSMultipleAccountsAdded

public class MusicServicesWizard extends Wizard
{

    public MusicServicesWizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCRunWizardActionType scrunwizardactiontype, SCIMusicServiceWizard scimusicservicewizard)
    {
        super(sciaction, sciactioncontext, scrunwizardactiontype, scimusicservicewizard);
    }

    public volatile WizardState buildState(int i)
    {
        return buildState(i);
    }

    public MusicServicesWizardState buildState(int i)
    {
        com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState musicservicewizardstate = com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.swigToEnum(i);
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState = new int[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.values().length];
                NoSuchFieldError nosuchfielderror13;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_LOGINPASSWORD.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_WORKING.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_RESULT.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_INTRO.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_SUBSCRIBEINTRO.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_ACCOUNTNEEDED.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_TERMS.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_COMPLETE.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_MERGE.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_PASSWORD.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_LINK_CODE.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_PROMOTED_INTRO.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_SET_NICKNAME.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                $SwitchMap$com$sonos$sclib$SCIMusicServiceWizard$MusicServiceWizardState[com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.STATE_MUSICSERVICE_MULTIPLE_ACCOUNTS_ADDED.ordinal()] = 14;
_L2:
                return;
                nosuchfielderror13;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState[musicservicewizardstate.ordinal()];
        JVM INSTR tableswitch 1 14: default 84
    //                   1 88
    //                   2 100
    //                   3 112
    //                   4 124
    //                   5 136
    //                   6 148
    //                   7 160
    //                   8 172
    //                   9 184
    //                   10 196
    //                   11 208
    //                   12 223
    //                   13 235
    //                   14 247;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15
_L1:
        Object obj = null;
_L17:
        return ((MusicServicesWizardState) (obj));
_L2:
        obj = new StateMSLoginPassword(this);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new StateMSWorking(this);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new StateMSResult(this);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new StateMSIntro(this);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new StateMSSubscribeIntro(this);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new StateMSAccountedNeeded(this);
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new StateMSTerms(this);
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new StateMSComplete(this);
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new StateMSMerge(this);
        continue; /* Loop/switch isn't completed */
_L11:
        obj = new StateMSPassword(this);
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new MusicServicesWizardState(this, musicservicewizardstate, 0x7f030030);
        continue; /* Loop/switch isn't completed */
_L13:
        obj = new StateMSPromotedIntro(this);
        continue; /* Loop/switch isn't completed */
_L14:
        obj = new StateMSSetNickname(this);
        continue; /* Loop/switch isn't completed */
_L15:
        obj = new StateMSMultipleAccountsAdded(this);
        if(true) goto _L17; else goto _L16
_L16:
    }

    protected SCIMusicServiceWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return LibraryUtils.getHousehold().createMusicServiceWizard(actionContext.getPropertyBag());
    }

    protected volatile SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return createSCIWizard(scrunwizardactiontype);
    }

    public int getIdForState(Object obj)
    {
        return ((com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState)obj).swigValue();
    }

    public String getNameForState(int i)
    {
        return com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.swigToEnum(i).toString();
    }

    public CharSequence getRecommendedText(com.sonos.sclib.SCIMusicServiceWizard.MSWizStringID mswizstringid)
    {
        return super.getRecommendedText(mswizstringid.swigValue());
    }

    public Object[] getStates()
    {
        return com.sonos.sclib.SCIMusicServiceWizard.MusicServiceWizardState.values();
    }

    public static final String LOG_TAG = com/sonos/acr/wizards/musicservices/MusicServicesWizard.getSimpleName();
    private int wizardStateBeforePause;

}
