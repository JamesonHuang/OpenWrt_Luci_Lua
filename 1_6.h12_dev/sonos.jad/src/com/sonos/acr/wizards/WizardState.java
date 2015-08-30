// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards;

import android.view.*;
import android.widget.TextView;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.view.SonosButton;
import com.sonos.sclib.SCIWizard;

// Referenced classes of package com.sonos.acr.wizards:
//            Wizard

public class WizardState
    implements android.view.View.OnClickListener
{

    public WizardState(Wizard wizard, int i, int j)
    {
        altNextButton = false;
        altPreviousButton = false;
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/wizards/WizardState.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        sclibWizardState = i;
        sonosWizard = wizard;
        layoutResourceId = j;
        SLog.w(LOG_TAG, (new StringBuilder()).append("New Instance Created for state: ").append(getStateName()).append(" UsingResourceId: ").append(j).toString());
    }

    public WizardState(Wizard wizard, int i, int j, boolean flag, boolean flag1)
    {
        this(wizard, i, j);
        altNextButton = flag;
        altPreviousButton = flag1;
    }

    final View createView(ViewGroup viewgroup)
    {
        if(rootView == null)
            rootView = onCreateView(LayoutInflater.from(sonosWizard.getActivity()), viewgroup);
        return rootView;
    }

    public String getBackButtonText()
    {
        return sonosWizard.getWizard().getRecommendedLabelForPreviousState();
    }

    public String getNextButtonText()
    {
        return sonosWizard.getWizard().getRecommendedLabelForNextState();
    }

    public String getStateName()
    {
        return (new StringBuilder()).append("ID(").append(sclibWizardState).append(")").toString();
    }

    public boolean hasAltBackButton()
    {
        return altPreviousButton;
    }

    public boolean hasAltNextButton()
    {
        return altNextButton;
    }

    public boolean isBackEnabled()
    {
        boolean flag = true;
        SCIWizard sciwizard = sonosWizard.getWizard();
        boolean flag1;
        if(sciwizard.isPreviousStateEnabled() || sciwizard.previousStateWillCancel())
            flag1 = flag;
        else
            flag1 = false;
        if(!flag1 || hasAltBackButton())
            flag = false;
        return flag;
    }

    public boolean isNextArrowHidden()
    {
        SCIWizard sciwizard = sonosWizard.getWizard();
        boolean flag;
        if(sciwizard.isStateDone() || sciwizard.isStateOK())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isNextEnabled()
    {
        boolean flag = true;
        SCIWizard sciwizard = sonosWizard.getWizard();
        boolean flag1;
        if(sciwizard.isNextStateEnabled() || sciwizard.completed())
            flag1 = flag;
        else
            flag1 = false;
        if(!flag1 || hasAltNextButton())
            flag = false;
        return flag;
    }

    public boolean onBackPressed()
    {
        return true;
    }

    public void onClick(View view)
    {
        SLog.d(LOG_TAG, (new StringBuilder()).append("View Clicked: ").append(view.getClass().getSimpleName()).toString());
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = layoutinflater.inflate(layoutResourceId, viewgroup, false);
        busyView = view.findViewById(0x7f0a01fe);
        return view;
    }

    public void onDeviceStatusChanged()
    {
    }

    public void onEntry(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
    }

    public void onExit(com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
    }

    public boolean onNextPressed()
    {
        return true;
    }

    public void onNextTransition()
    {
        if(busyView != null && sonosWizard.getWizard().isBusy())
            busyView.setVisibility(0);
    }

    protected void populateView(View view, int i, int j)
    {
        View view1 = view.findViewById(i);
        if(!(view1 instanceof TextView)) goto _L2; else goto _L1
_L1:
        CharSequence charsequence;
        charsequence = sonosWizard.getRecommendedText(j);
        SLog.v(LOG_TAG, (new StringBuilder()).append("PopulatingView[").append(view1.getId()).append("]: ").append(charsequence).toString());
        ((TextView)view1).setText(charsequence);
        if(!StringUtils.isEmptyOrNull(charsequence)) goto _L4; else goto _L3
_L3:
        view1.setVisibility(8);
_L2:
        return;
_L4:
        view1.setVisibility(0);
        if(view1 instanceof SonosButton)
            view1.setOnClickListener(this);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean previousStateWillCancel()
    {
        return sonosWizard.getWizard().previousStateWillCancel();
    }

    public void transitionBack()
    {
        sonosWizard.transitionBack();
    }

    public void transitionNext()
    {
        sonosWizard.transitionNext();
    }

    protected final String LOG_TAG;
    protected boolean altNextButton;
    protected boolean altPreviousButton;
    protected View busyView;
    private final int layoutResourceId;
    protected View rootView;
    protected final int sclibWizardState;
    protected final Wizard sonosWizard;
}
