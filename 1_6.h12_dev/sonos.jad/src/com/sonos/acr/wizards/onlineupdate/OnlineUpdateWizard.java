// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.onlineupdate;

import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.wizards.Wizard;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.onlineupdate:
//            OnlineUpdateWizardState, StateOUChoice, StateOUFromZonesMenu, StateOUCheckUpdates, 
//            StateOUUpgradeProgress, StateOUNeedsUpdating

public class OnlineUpdateWizard extends Wizard
{

    public OnlineUpdateWizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCRunWizardActionType scrunwizardactiontype, SCIOnlineUpdateWizard scionlineupdatewizard)
    {
        super(sciaction, sciactioncontext, scrunwizardactiontype, scionlineupdatewizard);
    }

    public WizardState buildState(int i)
    {
        com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState onlineupdatewizardstate = com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.swigToEnum(i);
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[];
            static final int $SwitchMap$com$sonos$sclib$SCRunWizardActionType[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState = new int[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.values().length];
                NoSuchFieldError nosuchfielderror18;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_FINISHED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_CANCELED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_REGISTRATION_REQUIRED.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_NOT_REQUIRED.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_POST_UPDATE_REINDEXING_NEEDED.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_INTRODUCTION.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_WARNING.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_DEVICES_UPGRADED.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_PENDING.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_ERROR_INFO.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_ERROR.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_CHOICE.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_FROM_ZONES_MENU_START.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_CHECK_FOR_UPDATES.ordinal()] = 14;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_DEVICES_UPGRADE_IN_PROGRESS.ordinal()] = 15;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIOnlineUpdateWizard$OnlineUpdateWizardState[com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_CONTROLLER_NEEDS_UPDATING.ordinal()] = 16;
                }
                catch(NoSuchFieldError nosuchfielderror15) { }
                $SwitchMap$com$sonos$sclib$SCRunWizardActionType = new int[SCRunWizardActionType.values().length];
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ONLINEUPDATE_INTRODUCTION_ONLY.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ONLINEUPDATE_FROM_ZONEMENU.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror17) { }
                $SwitchMap$com$sonos$sclib$SCRunWizardActionType[SCRunWizardActionType.SCACTN_RUNWIZ_ONLINEUPDATE.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror18;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState[onlineupdatewizardstate.ordinal()];
        JVM INSTR tableswitch 1 16: default 92
    //                   1 106
    //                   2 106
    //                   3 106
    //                   4 106
    //                   5 106
    //                   6 106
    //                   7 106
    //                   8 106
    //                   9 106
    //                   10 106
    //                   11 121
    //                   12 138
    //                   13 150
    //                   14 162
    //                   15 174
    //                   16 186;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        Object obj = new OnlineUpdateWizardState(this, onlineupdatewizardstate, 0x7f0300b3);
_L10:
        return ((WizardState) (obj));
_L2:
        obj = new OnlineUpdateWizardState(this, onlineupdatewizardstate, 0x7f0300b3);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new OnlineUpdateWizardState(this, onlineupdatewizardstate, 0x7f03004b, true, true);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new StateOUChoice(this);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new StateOUFromZonesMenu(this);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new StateOUCheckUpdates(this);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new StateOUUpgradeProgress(this);
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new StateOUNeedsUpdating(this);
        if(true) goto _L10; else goto _L9
_L9:
    }

    protected SCIOnlineUpdateWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        _cls1..SwitchMap.com.sonos.sclib.SCRunWizardActionType[scrunwizardactiontype.ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 42
    //                   2 59;
           goto _L1 _L2 _L3
_L1:
        SCIOnlineUpdateWizard scionlineupdatewizard = LibraryUtils.getHousehold().createOnlineUpdateWizard(false);
_L5:
        return scionlineupdatewizard;
_L2:
        scionlineupdatewizard = LibraryUtils.getHousehold().createOnlineUpdateIntroOnlyWizard(actionContext.getPropertyBag());
        continue; /* Loop/switch isn't completed */
_L3:
        scionlineupdatewizard = LibraryUtils.getHousehold().createOnlineUpdateWizard(true);
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected volatile SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return createSCIWizard(scrunwizardactiontype);
    }

    public int getIdForState(Object obj)
    {
        return ((com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState)obj).swigValue();
    }

    public String getNameForState(int i)
    {
        return com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.swigToEnum(i).toString();
    }

    public CharSequence getRecommendedText(com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizStringID onlineupdatewizstringid)
    {
        return super.getRecommendedText(onlineupdatewizstringid.swigValue());
    }

    public Object[] getStates()
    {
        return com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.values();
    }

    public static final String LOG_TAG = com/sonos/acr/wizards/onlineupdate/OnlineUpdateWizard.getSimpleName();

}
