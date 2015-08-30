// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous;

import android.view.*;
import android.widget.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.*;
import com.sonos.acr.wizards.anonymous.components.WizardButtonComponent;
import com.sonos.acr.wizards.anonymous.components.WizardComponent;
import com.sonos.acr.wizards.anonymous.components.WizardImageComponent;
import com.sonos.acr.wizards.anonymous.components.WizardListComponent;
import com.sonos.acr.wizards.anonymous.components.WizardSpinnerComponent;
import com.sonos.acr.wizards.anonymous.components.WizardTextComponent;
import com.sonos.acr.wizards.anonymous.components.WizardTitleComponent;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AnonymousWizardState extends WizardState
    implements android.widget.AdapterView.OnItemSelectedListener
{

    public AnonymousWizardState(Wizard wizard, SCIEnumerator scienumerator)
    {
        super(wizard, 2, 0x7f030007);
        componentList = new ArrayList();
        populateState(scienumerator);
    }

    private void addViewWithComponent(LinearLayout linearlayout, WizardComponent wizardcomponent)
    {
        View view = wizardcomponent.getComponentView(linearlayout);
        if(!(wizardcomponent instanceof WizardTitleComponent)) goto _L2; else goto _L1
_L1:
        SLog.v(LOG_TAG, (new StringBuilder()).append("AddingTitleView: ").append(((WizardTitleComponent)wizardcomponent).getTitle()).toString());
_L4:
        if(view != null)
        {
            if(wizardcomponent.isInvisible())
                view.setVisibility(4);
            linearlayout.addView(view);
        }
        return;
_L2:
        if(wizardcomponent instanceof WizardTextComponent)
            SLog.v(LOG_TAG, (new StringBuilder()).append("AddingTextView: ").append(((WizardTextComponent)wizardcomponent).getText()).toString());
        else
        if(wizardcomponent instanceof WizardImageComponent)
            SLog.v(LOG_TAG, (new StringBuilder()).append("AddingImageView: ").append(((WizardImageComponent)wizardcomponent).getImageURL()).toString());
        else
        if(wizardcomponent instanceof WizardListComponent)
        {
            SLog.v(LOG_TAG, (new StringBuilder()).append("AddingListView").append(((WizardListComponent)wizardcomponent).getInput().name()).toString());
            ((Spinner)view).setOnItemSelectedListener(this);
        } else
        if(wizardcomponent instanceof WizardSpinnerComponent)
            SLog.v(LOG_TAG, "AddingSpinnerView");
        else
        if(wizardcomponent instanceof WizardButtonComponent)
        {
            if(!addedSpace)
            {
                linearlayout.addView(new View(linearlayout.getContext()), new android.widget.LinearLayout.LayoutParams(-1, -1, 1.0F));
                addedSpace = true;
            }
            SLog.v(LOG_TAG, "Adding SonosButtonView");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void populateState(SCIEnumerator scienumerator)
    {
        if(scienumerator.count() > 0)
        {
            scienumerator.reset();
            do
            {
                if(!scienumerator.moveNext())
                    break;
                SCIPropertyBag scipropertybag = (SCIPropertyBag)scienumerator.getCurrent(sclibConstants.SCIPROPERTYBAG_INTERFACE);
                if(scipropertybag != null)
                {
                    SLog.v(LOG_TAG, (new StringBuilder()).append("Component Type KEY was found: ").append(WizardComponentType.swigToEnum(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_TYPE))).toString());
                    if(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_TYPE) == WizardComponentType.WIZARD_COMPONENT_TYPE_PAGE.swigValue())
                    {
                        backButtonHidden = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_HIDE_PREVIOUS);
                        nextButtonHidden = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_HIDE_NEXT);
                        backArrowHidden = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_PREVIOUS_IS_CANCEL);
                        nextArrowHidden = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_NEXT_IS_COMPLETE);
                        sleepDisabled = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_DISABLE_SLEEP);
                        altPreviousButton = backButtonHidden;
                        altNextButton = nextButtonHidden;
                    }
                    WizardComponent wizardcomponent = WizardComponent.WizardComponentWithDescriptor(scipropertybag, sonosWizard);
                    if(wizardcomponent != null)
                        componentList.add(wizardcomponent);
                    else
                        SLog.e(LOG_TAG, (new StringBuilder()).append("Unknown Component View of type: ").append(WizardComponentType.swigToEnum(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_TYPE))).toString());
                }
            } while(true);
        }
    }

    public String getStateName()
    {
        return "Anonymous Wizard State- NO NAME";
    }

    protected String getString(int i)
    {
        return sonosWizard.getActivity().getString(i);
    }

    protected SCIWizard getWizard()
    {
        return sonosWizard.getWizard();
    }

    public boolean isNextEnabled()
    {
        boolean flag;
        if(super.isNextEnabled() && sonosWizard.getWizard().areInputsValid())
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void logWizardComponents()
    {
        String s = "\nWIZARD COMPONENTS:";
        for(Iterator iterator = componentList.iterator(); iterator.hasNext();)
        {
            WizardComponent wizardcomponent = (WizardComponent)iterator.next();
            s = (new StringBuilder()).append(s).append("\n").append(wizardcomponent.logString()).toString();
        }

        SLog.v(LOG_TAG, s);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = super.onCreateView(layoutinflater, viewgroup);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a003a);
        addedSpace = false;
        for(Iterator iterator = componentList.iterator(); iterator.hasNext(); addViewWithComponent(linearlayout, (WizardComponent)iterator.next()));
        logWizardComponents();
        return view;
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        com.sonos.sclib.SCIWizard.WizInputSelection wizinputselection = (com.sonos.sclib.SCIWizard.WizInputSelection)adapterview.getTag();
        sonosWizard.getWizard().selectInput(wizinputselection, i);
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    private boolean addedSpace;
    private boolean backArrowHidden;
    private boolean backButtonHidden;
    private ArrayList componentList;
    private boolean nextArrowHidden;
    private boolean nextButtonHidden;
    private boolean sleepDisabled;
}
