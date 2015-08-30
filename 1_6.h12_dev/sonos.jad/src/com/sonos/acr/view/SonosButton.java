// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

// Referenced classes of package com.sonos.acr.view:
//            SonosTextView

public class SonosButton extends SonosTextView
{

    public SonosButton(Context context)
    {
        this(context, null);
    }

    public SonosButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SonosButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.SonosButton);
        if(typedarray != null)
        {
            setForceUpperCase(typedarray.getBoolean(0, false));
            typedarray.recycle();
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/widget/Button.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/Button.getName());
    }

    public void setForceUpperCase(boolean flag)
    {
        if(!isInEditMode())
        {
            forceUpperCase = flag;
            setText(getText());
        }
    }

    public void setText(CharSequence charsequence, android.widget.TextView.BufferType buffertype)
    {
        if(forceUpperCase && charsequence != null)
            charsequence = charsequence.toString().toUpperCase();
        super.setText(charsequence, buffertype);
    }

    boolean forceUpperCase;
}
