// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.Dialog;
import android.content.res.Resources;
import android.view.*;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.view.SonosButton;
import com.sonos.acr.view.SonosImageView;
import com.sonos.acr.wizards.setup.CustomZPSpinnerAdapter;
import com.sonos.sclib.SCActionCompletionStatus;
import com.sonos.sclib.SCIActionContext;
import java.lang.reflect.Field;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DebugSvgAction extends UIAction
    implements android.widget.AdapterView.OnItemSelectedListener, android.view.View.OnClickListener
{

    protected DebugSvgAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        rootView = LayoutInflater.from(sonosactivity).inflate(0x7f030016, null);
        svgSpinner = (Spinner)rootView.findViewById(0x7f0a005f);
        svgImageContainer = (FrameLayout)rootView.findViewById(0x7f0a0061);
        svgImage = (SonosImageView)rootView.findViewById(0x7f0a0062);
        prevButton = (SonosButton)rootView.findViewById(0x7f0a0049);
        nextButton = (SonosButton)rootView.findViewById(0x7f0a0063);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        ((CheckBox)rootView.findViewById(0x7f0a0060)).setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                FrameLayout framelayout = svgImageContainer;
                int k;
                if(flag)
                    k = -1;
                else
                    k = 0xff000000;
                framelayout.setBackgroundColor(k);
            }

            final DebugSvgAction this$0;

            
            {
                this$0 = DebugSvgAction.this;
                super();
            }
        }
);
        spinnerAdapter = new CustomZPSpinnerAdapter(sonosactivity);
        spinnerAdapter.setResourceIds(0x7f0300c0, 0x1090009);
        Field afield[] = com/sonos/acr/R$raw.getDeclaredFields();
        int i = afield.length;
        for(int j = 0; j < i; j++)
        {
            String s = afield[j].getName();
            spinnerAdapter.addItem(s, s, "");
        }

        svgSpinner.setAdapter(spinnerAdapter);
        svgSpinner.setOnItemSelectedListener(this);
        svgSpinner.setSelection(0);
    }

    public void onClick(View view)
    {
        if(view == prevButton && currentPosition > 0)
            svgSpinner.setSelection(-1 + currentPosition);
        if(view == nextButton && currentPosition < -1 + spinnerAdapter.getCount())
            svgSpinner.setSelection(1 + currentPosition);
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        int j = 0;
        String s = spinnerAdapter.getItemURL(i);
        int k = currentContext.getResources().getIdentifier(s, "raw", currentContext.getPackageName());
        svgImage.layout(0, 0, 0, 0);
        svgImage.setRawImageResource(k, false);
        svgImage.requestLayout();
        currentPosition = i;
        SonosButton sonosbutton = prevButton;
        int i1;
        SonosButton sonosbutton1;
        if(currentPosition > 0)
            i1 = 0;
        else
            i1 = 8;
        sonosbutton.setVisibility(i1);
        sonosbutton1 = nextButton;
        if(currentPosition >= -1 + spinnerAdapter.getCount())
            j = 8;
        sonosbutton1.setVisibility(j);
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        Dialog dialog = new Dialog(currentContext);
        dialog.setContentView(rootView);
        dialog.setTitle("Debug SVGs");
        dialog.getWindow().setLayout(-1, -1);
        dialog.show();
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    int currentPosition;
    SonosButton nextButton;
    SonosButton prevButton;
    View rootView;
    CustomZPSpinnerAdapter spinnerAdapter;
    SonosImageView svgImage;
    FrameLayout svgImageContainer;
    Spinner svgSpinner;
}
