// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.text.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.view.ClearableEditText;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayTextInputAction extends UIAction
    implements TextWatcher
{

    public DisplayTextInputAction(SonosActivity sonosactivity, String s, String s1, String s2, SCIStringInput scistringinput)
    {
        super(sonosactivity);
        mTitle = s;
        mTrimsWhiteSpace = false;
        mInput = scistringinput;
        mContentView = LayoutInflater.from(sonosactivity).inflate(0x7f03006c, null);
        TextView textview = (TextView)mContentView.findViewById(0x7f0a016e);
        mInputText = (ClearableEditText)mContentView.findViewById(0x7f0a0170);
        InputFilter ainputfilter[];
        if(s1 != null && s1.length() > 0)
        {
            textview.setText(s1);
            textview.setVisibility(0);
            mHasInstructions = true;
        } else
        {
            textview.setVisibility(8);
        }
        ainputfilter = new InputFilter[1];
        ainputfilter[0] = new android.text.InputFilter.LengthFilter(scistringinput.getMaxNumChars());
        mInputText.setFilters(ainputfilter);
        mInputText.setText(s2);
        mInputText.setSelection(mInputText.getText().length());
        mInputText.addTextChangedListener(this);
        mInputText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                    mDialog.getWindow().setSoftInputMode(5);
            }

            final DisplayTextInputAction this$0;

            
            {
                this$0 = DisplayTextInputAction.this;
                super();
            }
        }
);
        if(scistringinput.getRecommendedInputMethodType() == com.sonos.sclib.SCIStringInputBase.InputMethodType.INPUTMETHOD_TYPE_NUMERIC)
            mInputText.setRawInputType(2);
    }

    private void updateButtons(CharSequence charsequence)
    {
        Button button = mDialog.getButton(-1);
        if(button != null)
        {
            boolean flag;
            if(mTrimsWhiteSpace)
                flag = mInput.isValid(mInputText.getText().toString().trim());
            else
                flag = mInput.isValid(charsequence.toString());
            button.setEnabled(flag);
        }
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        updateButtons(charsequence);
    }

    public SCActionCompletionStatus perform(final SCIActionContext pActionCtxt)
    {
        final SCIPropertyBag propertyBag = pActionCtxt.getPropertyBag();
        if(propertyBag.doesPropExist(sclibConstants.SCACTN_BOOLPROP_UI_TRIM_WHITESPACE))
            mTrimsWhiteSpace = propertyBag.getBoolProp(sclibConstants.SCACTN_BOOLPROP_UI_TRIM_WHITESPACE);
        String s = currentContext.getResources().getString(0x104000a);
        if(propertyBag.doesPropExist(sclibConstants.SCACTN_STRPROP_PRIMARY_BUTTON_NAME))
            s = propertyBag.getStrProp(sclibConstants.SCACTN_STRPROP_PRIMARY_BUTTON_NAME);
        mDialog = (new android.app.AlertDialog.Builder(currentContext)).setTitle(mTitle).setCancelable(true).setPositiveButton(s, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                ((InputMethodManager)currentContext.getSystemService("input_method")).hideSoftInputFromWindow(mInputText.getWindowToken(), 0);
                String s1 = mInputText.getText().toString();
                if(mTrimsWhiteSpace)
                    s1 = s1.trim();
                mInput.setString(s1);
                pActionCtxt.actionHasCompleted(DisplayTextInputAction.this);
            }

            final DisplayTextInputAction this$0;
            final SCIActionContext val$pActionCtxt;

            
            {
                this$0 = DisplayTextInputAction.this;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
).setNegativeButton(0x1040000, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                ((InputMethodManager)currentContext.getSystemService("input_method")).hideSoftInputFromWindow(mInputText.getWindowToken(), 0);
                propertyBag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
                pActionCtxt.actionHasCompleted(DisplayTextInputAction.this);
                dialoginterface.dismiss();
            }

            final DisplayTextInputAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayTextInputAction.this;
                propertyBag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                ((InputMethodManager)currentContext.getSystemService("input_method")).hideSoftInputFromWindow(mInputText.getWindowToken(), 0);
                propertyBag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
                pActionCtxt.actionHasCompleted(DisplayTextInputAction.this);
            }

            final DisplayTextInputAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayTextInputAction.this;
                propertyBag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
).create();
        Resources resources = currentContext.getResources();
        int i;
        int j;
        int k;
        int l;
        if(mHasInstructions)
        {
            i = (int)resources.getDimension(0x7f090032);
            j = (int)resources.getDimension(0x7f090038);
            k = (int)resources.getDimension(0x7f090033);
            l = (int)resources.getDimension(0x7f090031);
        } else
        {
            i = (int)resources.getDimension(0x7f090035);
            j = (int)resources.getDimension(0x7f090037);
            k = (int)resources.getDimension(0x7f090036);
            l = (int)resources.getDimension(0x7f090034);
        }
        mDialog.setView(mContentView, i, j, k, l);
        mDialog.show();
        updateButtons(mInputText.getText());
        mInputText.requestFocus();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    protected boolean validateInput(CharSequence charsequence)
    {
        return true;
    }

    private View mContentView;
    private AlertDialog mDialog;
    private boolean mHasInstructions;
    private SCIStringInput mInput;
    private ClearableEditText mInputText;
    private String mTitle;
    private boolean mTrimsWhiteSpace;




}
