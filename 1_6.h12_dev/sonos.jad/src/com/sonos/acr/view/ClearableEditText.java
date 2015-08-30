// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

// Referenced classes of package com.sonos.acr.view:
//            SonosEditText

public class ClearableEditText extends SonosEditText
{

    public ClearableEditText(Context context)
    {
        super(context);
        mOriginalComponentDrawable = null;
        mHasText = false;
    }

    public ClearableEditText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mOriginalComponentDrawable = null;
        mHasText = false;
    }

    public ClearableEditText(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mOriginalComponentDrawable = null;
        mHasText = false;
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        if(!mHasText && mOriginalComponentDrawable == null)
            mOriginalComponentDrawable = getCompoundDrawables()[2];
        if(charsequence.length() != 0) goto _L2; else goto _L1
_L1:
        mHasText = false;
        setCompoundDrawablesWithIntrinsicBounds(null, null, mOriginalComponentDrawable, null);
_L4:
        return;
_L2:
        if(!mHasText)
        {
            mHasText = true;
            setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(0x7f02007a), null);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mHasText && motionevent.getAction() == 1 && motionevent.getX() > (float)(getMeasuredWidth() - getCompoundPaddingRight()))
        {
            setText(null);
            if(hasFocus())
                showKeyboard();
        }
        return super.onTouchEvent(motionevent);
    }

    protected boolean mHasText;
    private Drawable mOriginalComponentDrawable;
}
