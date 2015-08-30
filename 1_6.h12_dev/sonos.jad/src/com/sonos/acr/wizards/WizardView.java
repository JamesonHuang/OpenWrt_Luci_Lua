// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sonos.acr.view.DetachCatchViewFlipper;
import com.sonos.acr.view.SonosButton;

// Referenced classes of package com.sonos.acr.wizards:
//            WizardState

public class WizardView extends RelativeLayout
    implements android.view.View.OnClickListener
{
    public static interface ActionListener
    {

        public abstract void onDebugPressed();

        public abstract void onNextPressed();

        public abstract void onPrevPressed();
    }


    public WizardView(Context context)
    {
        this(context, null);
    }

    public WizardView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public WizardView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        isDebugWizard = false;
        LayoutInflater.from(context).inflate(0x7f0300be, this);
        nextButton = (SonosButton)findViewById(0x7f0a0063);
        prevButton = (SonosButton)findViewById(0x7f0a0049);
        viewFlipper = (DetachCatchViewFlipper)findViewById(0x7f0a0201);
        debugText = (TextView)findViewById(0x7f0a0203);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        debugText.setOnClickListener(this);
        viewFlipper.setAnimateFirstView(false);
    }

    public void disableButtons()
    {
        nextButton.setEnabled(false);
        prevButton.setEnabled(false);
    }

    public void enableButtons()
    {
        nextButton.setVisibility(0);
        prevButton.setVisibility(0);
        nextButton.setText(0x7f0c00e5);
        prevButton.setText(0x7f0c00e4);
        nextButton.setEnabled(true);
        prevButton.setEnabled(true);
    }

    public void onClick(View view)
    {
        if(actionListener == null) goto _L2; else goto _L1
_L1:
        if(view != prevButton) goto _L4; else goto _L3
_L3:
        actionListener.onPrevPressed();
_L2:
        return;
_L4:
        if(view == nextButton)
            actionListener.onNextPressed();
        else
        if(view == debugText)
            actionListener.onDebugPressed();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setActionListener(ActionListener actionlistener)
    {
        actionListener = actionlistener;
    }

    public void setDebugText(String s)
    {
        debugText.setText(s);
    }

    public void setDebugWizard(boolean flag)
    {
        isDebugWizard = flag;
        TextView textview = debugText;
        int i;
        if(flag)
            i = 0;
        else
            i = 8;
        textview.setVisibility(i);
    }

    public void transitionViews(WizardState wizardstate, com.sonos.sclib.SCIWizard.StateTransitionType statetransitiontype)
    {
        View view = wizardstate.createView(viewFlipper);
        if(view != null)
        {
            View view1 = viewFlipper.getCurrentView();
            viewFlipper.addView(view);
            wizardstate.onEntry(statetransitiontype);
            nextButton.setEnabled(true);
            prevButton.setEnabled(true);
            updateButtons(wizardstate);
            if(statetransitiontype == com.sonos.sclib.SCIWizard.StateTransitionType.STATE_TRANS_TYPE_FORWARD)
            {
                viewFlipper.setInAnimation(getContext(), 0x7f040013);
                viewFlipper.setOutAnimation(getContext(), 0x7f040014);
            } else
            if(statetransitiontype == com.sonos.sclib.SCIWizard.StateTransitionType.STATE_TRANS_TYPE_BACKWARD)
            {
                viewFlipper.setInAnimation(getContext(), 0x7f040012);
                viewFlipper.setOutAnimation(getContext(), 0x7f040015);
            } else
            {
                viewFlipper.setInAnimation(getContext(), 0x7f040000);
                viewFlipper.setOutAnimation(getContext(), 0x7f040000);
            }
            viewFlipper.showNext();
            viewFlipper.removeView(view1);
        }
    }

    public void updateButtons(WizardState wizardstate)
    {
        int i = 0;
        if(isDebugWizard)
        {
            enableButtons();
        } else
        {
            if(wizardstate.isNextEnabled())
            {
                nextButton.setVisibility(0);
                nextButton.setText(wizardstate.getNextButtonText());
                Drawable drawable;
                if(wizardstate.isNextArrowHidden())
                    nextButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                else
                    nextButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020094, 0);
            } else
            {
                nextButton.setVisibility(4);
            }
            if(wizardstate.isBackEnabled())
            {
                prevButton.setVisibility(0);
                prevButton.setText(wizardstate.getBackButtonText());
                drawable = prevButton.getCompoundDrawables()[0];
                if(!wizardstate.previousStateWillCancel())
                    i = 255;
                drawable.setAlpha(i);
            } else
            {
                prevButton.setVisibility(4);
            }
        }
    }

    protected ActionListener actionListener;
    protected TextView debugText;
    private boolean isDebugWizard;
    protected SonosButton nextButton;
    protected SonosButton prevButton;
    protected DetachCatchViewFlipper viewFlipper;
    protected WizardState wizardState;
}
