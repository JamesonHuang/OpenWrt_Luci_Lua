// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import com.sonos.acr.util.SLog;
import com.sonos.acr.wizards.anonymous.AnonymousWizardState;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.wizards:
//            WizardState, SonosWizardActivity, WizardView

public abstract class Wizard
    implements WizardView.ActionListener
{
    private class WizardEventSink extends SCIEventSinkSwigBase
    {

        public void dispatchEvent(SCIObj sciobj, String s)
        {
            if(!s.contains(sclibConstants.ONSTATECHANGED_EVENT)) goto _L2; else goto _L1
_L1:
            int i = sciWizard.getState();
            SLog.i(LOG_TAG, (new StringBuilder()).append("Wizard received Event: ").append(s).append(" state: ").append(i).toString());
            if(!isDebugMode && sciWizard.completed())
                onComplete();
            else
                transitionState(i, sciWizard.getStateTransitionDirection());
_L4:
            return;
_L2:
            if(s.contains(sclibConstants.ONSTATETRANSITIONSENABLED_EVENT))
            {
                SLog.i(LOG_TAG, (new StringBuilder()).append("Wizard received Event: ").append(s).toString());
                if(currentState != null)
                    wizardView.updateButtons(currentState);
            } else
            if(s.contains(sclibConstants.ONDEVICEUPDATESTATUSCHANGED_EVENT))
            {
                SLog.v(LOG_TAG, (new StringBuilder()).append("Wizard received Event: ").append(s).toString());
                if(currentState != null)
                    currentState.onDeviceStatusChanged();
            } else
            {
                SLog.w(LOG_TAG, (new StringBuilder()).append("Wizard received unexpected Event: ").append(s).toString());
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        final Wizard this$0;

        private WizardEventSink()
        {
            this$0 = Wizard.this;
            super();
        }

    }


    protected Wizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCIWizard sciwizard)
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/wizards/Wizard.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        isDebugMode = false;
        actionContext = sciactioncontext;
        action = sciaction;
        runType = SCRunWizardActionType.SCACTN_RUNWIZ_INVALID;
        if(sciwizard != null)
            sciWizard = sciwizard;
        else
            sciWizard = createSCIWizard(runType);
    }

    protected Wizard(SCIAction sciaction, SCIActionContext sciactioncontext, SCRunWizardActionType scrunwizardactiontype, SCIWizard sciwizard)
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/wizards/Wizard.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        isDebugMode = false;
        actionContext = sciactioncontext;
        action = sciaction;
        runType = scrunwizardactiontype;
        if(sciwizard != null)
            sciWizard = sciwizard;
        else
            sciWizard = createSCIWizard(scrunwizardactiontype);
    }

    private void addPresentationString(ArrayList arraylist, int i)
    {
        String s = sciWizard.getRecommendedPresentationText(i);
        if(s != null && s.length() > 0)
            arraylist.add(s);
    }

    public abstract WizardState buildState(int i);

    public boolean canRunWithoutWifi()
    {
        return false;
    }

    protected abstract SCIWizard createSCIWizard(SCRunWizardActionType scrunwizardactiontype);

    public SonosWizardActivity getActivity()
    {
        return wizardActivity;
    }

    public abstract int getIdForState(Object obj);

    public abstract String getNameForState(int i);

    public ArrayList getRecommendedBodyStrings()
    {
        ArrayList arraylist = new ArrayList();
        addPresentationString(arraylist, SCIWizard.STRID_PRESENTATION_BODY_1);
        addPresentationString(arraylist, SCIWizard.STRID_PRESENTATION_BODY_2);
        addPresentationString(arraylist, SCIWizard.STRID_PRESENTATION_BODY_3);
        addPresentationString(arraylist, SCIWizard.STRID_PRESENTATION_BODY_4);
        addPresentationString(arraylist, SCIWizard.STRID_PRESENTATION_BODY_5);
        return arraylist;
    }

    public CharSequence getRecommendedText(int i)
    {
        return sciWizard.getRecommendedPresentationText(i);
    }

    public int getState()
    {
        return sciWizard.getState();
    }

    public abstract Object[] getStates();

    public SCIWizard getWizard()
    {
        return sciWizard;
    }

    public WizardView getWizardView()
    {
        return wizardView;
    }

    public void init(WizardView wizardview, SonosWizardActivity sonoswizardactivity)
    {
        wizardView = wizardview;
        wizardActivity = sonoswizardactivity;
    }

    public boolean isDebugMode()
    {
        return isDebugMode;
    }

    public boolean onBackPressed()
    {
        if(currentState != null && currentState.isBackEnabled() && currentState.onBackPressed())
            transitionBack();
        return true;
    }

    public void onComplete()
    {
        wizardActivity.onWizardCompleted(this);
    }

    public void onDebugPressed()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Debug Wizard");
        final Object states[] = getStates();
        if(states != null)
        {
            Arrays.sort(states, new Comparator() {

                public int compare(Object obj, Object obj1)
                {
                    return obj.toString().compareTo(obj1.toString());
                }

                final Wizard this$0;

            
            {
                this$0 = Wizard.this;
                super();
            }
            }
);
            builder.setSingleChoiceItems(new ArrayAdapter(getActivity(), 0x7f030096, states), -1, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    wizardDialog.dismiss();
                    wizardDialog = null;
                    sciWizard.dbgTransitionToState(getIdForState(states[i]));
                }

                final Wizard this$0;
                final Object val$states[];

            
            {
                this$0 = Wizard.this;
                states = aobj;
                super();
            }
            }
);
        }
        wizardDialog = builder.create();
        wizardDialog.show();
    }

    public void onNextPressed()
    {
        if(!isDebugMode) goto _L2; else goto _L1
_L1:
        if(!sciWizard.dbgNextState())
            onComplete();
_L4:
        return;
_L2:
        if(currentState != null && currentState.onNextPressed())
        {
            transitionNext();
            currentState.onNextTransition();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onPrevPressed()
    {
        if(!isDebugMode) goto _L2; else goto _L1
_L1:
        onComplete();
_L4:
        return;
_L2:
        if(currentState != null && currentState.onBackPressed())
            transitionBack();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void pause()
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Pausing Wizard: ").append(sciWizard).toString());
        unsubscribe();
        if(currentState != null)
            currentState.onExit(com.sonos.sclib.SCIWizard.StateTransitionType.STATE_TRANS_TYPE_UNKNOWN);
    }

    public void resume()
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Resuming Wizard: ").append(sciWizard).toString());
        if(isDebugMode || sciWizard == null || !sciWizard.completed()) goto _L2; else goto _L1
_L1:
        onComplete();
_L4:
        return;
_L2:
        subscribe();
        wizardView.setActionListener(this);
        if(currentState != null)
            currentState.onEntry(com.sonos.sclib.SCIWizard.StateTransitionType.STATE_TRANS_TYPE_FORWARD);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setDebugMode(boolean flag)
    {
        isDebugMode = flag;
        if(isDebugMode)
        {
            sciWizard.dbgRunInUserFlowMode();
            sciWizard.dbgNextState();
        }
    }

    public boolean shouldWarp()
    {
        return true;
    }

    public void start()
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Starting Wizard: ").append(sciWizard).toString());
        if(!sciWizard.running())
            sciWizard.run();
    }

    public void stop()
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Stopping Wizard: ").append(sciWizard).toString());
        unsubscribe();
        if(actionContext != null)
        {
            SCIPropertyBag scipropertybag = actionContext.getPropertyBag();
            if(scipropertybag != null)
            {
                int i = sciWizard.getExitCode();
                scipropertybag.setIntProp(sclibConstants.SCACTN_INTPROP_WIZARDEXITCODE, i);
                SLog.d(LOG_TAG, (new StringBuilder()).append("exit code = ").append(i).toString());
            }
            actionContext.actionHasCompleted(action);
            actionContext = null;
        }
    }

    public void subscribe()
    {
        if(wizardEventSink == null)
        {
            wizardEventSink = new WizardEventSink();
            sciWizard.subscribe(wizardEventSink);
            int i = sciWizard.getState();
            if(currentState == null || i != currentState.sclibWizardState)
                transitionState(i, com.sonos.sclib.SCIWizard.StateTransitionType.STATE_TRANS_TYPE_FORWARD);
        }
    }

    protected void transitionBack()
    {
        if(sciWizard.isPreviousStateEnabled() || sciWizard.isCancelEnabled())
        {
            wizardView.disableButtons();
            sciWizard.transitionToPreviousState();
        }
    }

    public void transitionNext()
    {
        if(!sciWizard.isNextStateEnabled()) goto _L2; else goto _L1
_L1:
        if(!sciWizard.transitionToNextState()) goto _L4; else goto _L3
_L3:
        wizardView.disableButtons();
_L2:
        return;
_L4:
        if(currentState != null)
            wizardView.updateButtons(currentState);
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void transitionState(int i, com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        boolean flag = false;
        if(currentState != null)
            currentState.onExit(statetransitiontype);
        currentState = null;
        if(sciWizard.isPresentationRecommended())
        {
            com.sonos.sclib.SCIEnumerator scienumerator = sciWizard.getWizardComponentsForCurrentState();
            WizardView wizardview;
            StringBuilder stringbuilder;
            if(scienumerator != null)
                currentState = new AnonymousWizardState(this, scienumerator);
            else
                currentState = buildState(i);
        }
        if(currentState != null)
            wizardView.transitionViews(currentState, statetransitiontype);
        else
        if(isDebugMode)
            onNextPressed();
        else
            wizardView.disableButtons();
        ((InputMethodManager)getActivity().getSystemService("input_method")).hideSoftInputFromWindow(wizardView.getWindowToken(), 0);
        wizardview = wizardView;
        stringbuilder = (new StringBuilder()).append("ID: ").append(getNameForState(i)).append("\nHasView: ");
        if(currentState != null)
            flag = true;
        wizardview.setDebugText(stringbuilder.append(flag).toString());
    }

    public void unsubscribe()
    {
        if(wizardEventSink != null)
        {
            sciWizard.unsubscribe(wizardEventSink);
            wizardEventSink = null;
        }
    }

    public void updateButtons()
    {
        if(currentState != null)
            wizardView.updateButtons(currentState);
    }

    public static final String DEVICE_ID = "SOUNDBAR_DEVICE_ID";
    public final String LOG_TAG;
    protected SCIAction action;
    protected SCIActionContext actionContext;
    protected WizardState currentState;
    private boolean isDebugMode;
    protected SCRunWizardActionType runType;
    protected final SCIWizard sciWizard;
    protected SonosWizardActivity wizardActivity;
    private AlertDialog wizardDialog;
    private WizardEventSink wizardEventSink;
    protected WizardView wizardView;



/*
    static AlertDialog access$102(Wizard wizard, AlertDialog alertdialog)
    {
        wizard.wizardDialog = alertdialog;
        return alertdialog;
    }

*/

}
