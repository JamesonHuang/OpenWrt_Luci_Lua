// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.util.NotificationCenter;

public class SonosEditText extends EditText
{

    public SonosEditText(Context context)
    {
        super(context);
        mPostedAvailableNotification = false;
    }

    public SonosEditText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mPostedAvailableNotification = false;
    }

    public SonosEditText(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPostedAvailableNotification = false;
    }

    public void hideKeyboard()
    {
        ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        postEntryAvailableNotification(true);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        postEntryUnavailableNotification();
        clearFocus();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        setSelectAllOnFocus(false);
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        if(flag)
            showKeyboard();
        else
            hideKeyboard();
    }

    protected void onWindowVisibilityChanged(int i)
    {
        boolean flag;
        if(getVisibility() == 0)
            flag = true;
        else
            flag = false;
        super.onWindowVisibilityChanged(i);
        if(getParent() == null) goto _L2; else goto _L1
_L1:
        if(i != 0) goto _L4; else goto _L3
_L3:
        postEntryAvailableNotification(true);
_L2:
        return;
_L4:
        if(flag)
            postEntryUnavailableNotification();
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void postEntryAvailableNotification(boolean flag)
    {
        if(!mPostedAvailableNotification)
        {
            mPostedAvailableNotification = true;
            NotificationCenter.defaultCenter().postNotification(SonosActivity.KEYBOARD_ENTRY_AVAILABLE, this);
            if(flag)
                postDelayed(mHideAction, 250L);
        }
    }

    protected void postEntryUnavailableNotification()
    {
        removeCallbacks(mHideAction);
        if(mPostedAvailableNotification)
        {
            mPostedAvailableNotification = false;
            NotificationCenter.defaultCenter().postNotification(SonosActivity.KEYBOARD_ENTRY_UNAVAILABLE, this);
        }
    }

    public boolean requestFocus(int i, Rect rect)
    {
        if(hasFocus())
            showKeyboard();
        return super.requestFocus(i, rect);
    }

    public void showKeyboard()
    {
        ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this, 1);
    }

    protected static final int EDIT_AVAILABLE_CHECK_MSEC = 250;
    private static final String LOG_TAG = com/sonos/acr/view/SonosEditText.getSimpleName();
    protected Runnable mHideAction = new Runnable() {

        public void run()
        {
            if(isShown())
                postEntryAvailableNotification(false);
            else
                postEntryUnavailableNotification();
            postDelayed(mHideAction, 250L);
        }

        final SonosEditText this$0;

            
            {
                this$0 = SonosEditText.this;
                super();
            }
    }
;
    protected boolean mPostedAvailableNotification;

}
