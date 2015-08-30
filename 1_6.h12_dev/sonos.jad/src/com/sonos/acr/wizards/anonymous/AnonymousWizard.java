// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous;

import com.sonos.acr.wizards.Wizard;
import com.sonos.acr.wizards.WizardState;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.wizards.anonymous:
//            AnonymousWizardState

public class AnonymousWizard extends Wizard
{

    public AnonymousWizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCIWizard sciwizard)
    {
        super(sciaction, sciactioncontext, sciwizard);
    }

    public volatile WizardState buildState(int i)
    {
        return buildState(i);
    }

    public AnonymousWizardState buildState(int i)
    {
        return new AnonymousWizardState(this, sciWizard.getWizardComponentsForCurrentState());
    }

    protected SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype)
    {
        return sciWizard;
    }

    public int getIdForState(Object obj)
    {
        return 2;
    }

    public String getNameForState(int i)
    {
        return "Anonymous Wizard - NO STATE";
    }

    public Object[] getStates()
    {
        return null;
    }

    public static final String LOG_TAG = com/sonos/acr/wizards/anonymous/AnonymousWizard.getSimpleName();

}
