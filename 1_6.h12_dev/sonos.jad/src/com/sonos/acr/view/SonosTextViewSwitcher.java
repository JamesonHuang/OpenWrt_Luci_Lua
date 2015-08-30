// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewFlipper;
import com.sonos.acr.util.StringUtils;

// Referenced classes of package com.sonos.acr.view:
//            SonosTextView

public class SonosTextViewSwitcher extends ViewFlipper
    implements TextWatcher
{

    public SonosTextViewSwitcher(Context context)
    {
        super(context);
    }

    public SonosTextViewSwitcher(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void updateFlipper()
    {
        boolean flag;
        boolean flag1;
        if(view1 != null && StringUtils.isNotEmptyOrNull(view1.getText()))
            flag = true;
        else
            flag = false;
        if(view2 != null && StringUtils.isNotEmptyOrNull(view2.getText()))
            flag1 = true;
        else
            flag1 = false;
        if(!flag || !flag1) goto _L2; else goto _L1
_L1:
        startFlipping();
_L4:
        return;
_L2:
        if(flag)
        {
            stopFlipping();
            if(getCurrentView() != view1)
                setDisplayedChild(0);
        } else
        if(flag1)
        {
            stopFlipping();
            if(getCurrentView() != view2)
                setDisplayedChild(1);
        } else
        {
            stopFlipping();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        if(view instanceof SonosTextView)
        {
            ((SonosTextView)view).addTextChangedListener(this);
            if(view1 == null)
                view1 = (SonosTextView)view;
            else
                view2 = (SonosTextView)view;
            updateFlipper();
            return;
        } else
        {
            throw new RuntimeException("Invalid View!  View must be a sonos View.");
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
        updateFlipper();
    }

    SonosTextView view1;
    SonosTextView view2;
}
