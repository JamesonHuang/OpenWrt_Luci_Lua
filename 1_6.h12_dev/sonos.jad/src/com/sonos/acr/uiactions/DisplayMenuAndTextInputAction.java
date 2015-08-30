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
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.EnumeratorAdapter;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayMenuAndTextInputAction extends UIAction
    implements TextWatcher
{

    public DisplayMenuAndTextInputAction(SonosActivity sonosactivity, String s, String s1, String s2, boolean flag, SCIEnumerator scienumerator, int i, 
            SCIResource sciresource, int j, String s3)
    {
        super(sonosactivity);
        mSelectedIconId = -1;
        mShouldEditTextBeReplaced = false;
        mPropBag = null;
        mInputStr = "";
        mTitle = s1;
        boolean flag1;
        final LinkedHashMap menuItems;
        Resources resources;
        TextView textview;
        InputFilter ainputfilter[];
        Spinner spinner;
        if(s3.length() == 0)
            flag1 = true;
        else
            flag1 = false;
        mAllowsEmptyInput = flag1;
        menuItems = new LinkedHashMap();
        resources = sonosactivity.getResources();
        mContentView = LayoutInflater.from(sonosactivity).inflate(0x7f030074, null);
        textview = (TextView)mContentView.findViewById(0x7f0a0189);
        mInputText = (EditText)mContentView.findViewById(0x7f0a018b);
        mInputText.setInputType(0x82000);
        if(s2 != null && s2.length() > 0)
        {
            Object aobj[] = new Object[1];
            aobj[0] = s2;
            textview.setText(resources.getString(0x7f0c00a8, aobj));
            textview.setVisibility(0);
            mHasInstructions = true;
        } else
        {
            textview.setVisibility(8);
        }
        ainputfilter = new InputFilter[1];
        ainputfilter[0] = new android.text.InputFilter.LengthFilter(j);
        mInputText.setFilters(ainputfilter);
        mInputText.setText(sciresource.getName());
        mInputText.setSelection(mInputText.getText().length());
        mInputText.addTextChangedListener(this);
        mInputText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag2)
            {
                if(flag2)
                    mDialog.getWindow().setSoftInputMode(5);
            }

            final DisplayMenuAndTextInputAction this$0;

            
            {
                this$0 = DisplayMenuAndTextInputAction.this;
                super();
            }
        }
);
        spinner = (Spinner)mContentView.findViewById(0x7f0a018c);
        SCIResource sciresource1;
        for(Iterator iterator = (new EnumeratorAdapter(scienumerator, sclibConstants.SCIRESOURCE_INTERFACE)).iterator(); iterator.hasNext(); menuItems.put(sciresource1.getName(), sciresource1.getIconID()))
            sciresource1 = (SCIResource)iterator.next();

        final ArrayAdapter adapter = new ArrayAdapter(sonosactivity, 0x1090008, menuItems.keySet().toArray(new String[0]));
        adapter.setDropDownViewResource(0x1090009);
        spinner.setAdapter(adapter);
        if(i != -1)
            spinner.setSelection(i);
        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int k, long l)
            {
                SCIconID sciconid = (SCIconID)menuItems.get(adapter.getItem(k));
                mInputStr = (String)adapter.getItem(k);
                if(mShouldEditTextBeReplaced)
                    mInputText.setText(mInputStr);
                mSelectedIconId = sciconid.swigValue();
                mPropBag.setIntProp(sclibConstants.SCACTN_INTPROP_MENU_SELECTED_INDEX, k);
                mPropBag.setIntProp(sclibConstants.SCACTN_INTPROP_ICON_ID_INPUT, mSelectedIconId);
                mShouldEditTextBeReplaced = true;
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final DisplayMenuAndTextInputAction this$0;
            final ArrayAdapter val$adapter;
            final Map val$menuItems;

            
            {
                this$0 = DisplayMenuAndTextInputAction.this;
                menuItems = map;
                adapter = arrayadapter;
                super();
            }
        }
);
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        boolean flag = false;
        if(!mAllowsEmptyInput)
        {
            Button button = mDialog.getButton(-1);
            if(button != null)
                if(mTrimsWhiteSpace && "".equals(mInputText.getText().toString().trim()))
                {
                    button.setEnabled(false);
                } else
                {
                    if(charsequence.length() > 0)
                        flag = true;
                    button.setEnabled(flag);
                }
        }
    }

    public SCActionCompletionStatus perform(final SCIActionContext pActionCtxt)
    {
        final SCIPropertyBag propertyBag = pActionCtxt.getPropertyBag();
        mPropBag = propertyBag;
        if(propertyBag.doesPropExist(sclibConstants.SCACTN_BOOLPROP_UI_TRIM_WHITESPACE))
            mTrimsWhiteSpace = propertyBag.getBoolProp(sclibConstants.SCACTN_BOOLPROP_UI_TRIM_WHITESPACE);
        mDialog = (new android.app.AlertDialog.Builder(currentContext)).setTitle(mTitle).setCancelable(true).setPositiveButton(0x104000a, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                ((InputMethodManager)currentContext.getSystemService("input_method")).hideSoftInputFromWindow(mInputText.getWindowToken(), 0);
                String s = mInputText.getText().toString();
                if(mTrimsWhiteSpace)
                    s = s.trim();
                if(!propertyBag.doesPropExist(sclibConstants.SCACTN_INTPROP_MENU_SELECTED_INDEX) || !mInputStr.equals(s))
                    propertyBag.setStrProp(sclibConstants.SCACTN_STRPROP_TEXT_INPUT, s);
                pActionCtxt.actionHasCompleted(DisplayMenuAndTextInputAction.this);
            }

            final DisplayMenuAndTextInputAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayMenuAndTextInputAction.this;
                propertyBag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
).setNegativeButton(0x1040000, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                ((InputMethodManager)currentContext.getSystemService("input_method")).hideSoftInputFromWindow(mInputText.getWindowToken(), 0);
                propertyBag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
                pActionCtxt.actionHasCompleted(DisplayMenuAndTextInputAction.this);
            }

            final DisplayMenuAndTextInputAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayMenuAndTextInputAction.this;
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
                pActionCtxt.actionHasCompleted(DisplayMenuAndTextInputAction.this);
            }

            final DisplayMenuAndTextInputAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayMenuAndTextInputAction.this;
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
        mDialog.setOwnerActivity(currentContext);
        mDialog.show();
        mInputText.requestFocus();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    private boolean mAllowsEmptyInput;
    private View mContentView;
    private AlertDialog mDialog;
    private boolean mHasInstructions;
    private String mInputStr;
    private EditText mInputText;
    private SCIPropertyBag mPropBag;
    private int mSelectedIconId;
    private boolean mShouldEditTextBeReplaced;
    private String mTitle;
    private boolean mTrimsWhiteSpace;




/*
    static String access$102(DisplayMenuAndTextInputAction displaymenuandtextinputaction, String s)
    {
        displaymenuandtextinputaction.mInputStr = s;
        return s;
    }

*/



/*
    static boolean access$202(DisplayMenuAndTextInputAction displaymenuandtextinputaction, boolean flag)
    {
        displaymenuandtextinputaction.mShouldEditTextBeReplaced = flag;
        return flag;
    }

*/




/*
    static int access$402(DisplayMenuAndTextInputAction displaymenuandtextinputaction, int i)
    {
        displaymenuandtextinputaction.mSelectedIconId = i;
        return i;
    }

*/


}
