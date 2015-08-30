// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.util.ViewUtils;

public class SonosTextView extends CheckedTextView
{

    public SonosTextView(Context context)
    {
        this(context, null);
    }

    public SonosTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SonosTextView(Context context, AttributeSet attributeset, int i)
    {
        TypedArray typedarray;
        super(context, attributeset, i);
        hasInitialized = false;
        nonEmptyVisibility = 0;
        emptyVisibility = EMPTY_VISIBILITY_MATCH;
        hasInitialized = true;
        attrs = attributeset;
        nonEmptyVisibility = getVisibility();
        typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.SonosTextView);
        setForceUpperCase(typedarray.getBoolean(1, false));
        if(isInEditMode()) goto _L2; else goto _L1
_L1:
        typedarray.getInt(2, -1);
        JVM INSTR tableswitch 3 6: default 108
    //                   3 135
    //                   4 145
    //                   5 155
    //                   6 165;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        setTypeface(ViewUtils.SONOS_REGULAR);
_L2:
        setEmptyVisibility(typedarray.getInt(0, emptyVisibility));
        typedarray.recycle();
        return;
_L4:
        setTypeface(ViewUtils.SONOS_ITALIC);
        continue; /* Loop/switch isn't completed */
_L5:
        setTypeface(ViewUtils.SONOS_LIGHT);
        continue; /* Loop/switch isn't completed */
_L6:
        setTypeface(ViewUtils.SONOS_MEDIUM);
        continue; /* Loop/switch isn't completed */
_L7:
        setTypeface(ViewUtils.SONOS_MEDIUM_ITALIC);
        if(true) goto _L2; else goto _L8
_L8:
    }

    private void updateEmptyVisibility()
    {
        if(emptyVisibility != EMPTY_VISIBILITY_MATCH && hasInitialized)
            if(hasEmptyText)
                super.setVisibility(emptyVisibility);
            else
                super.setVisibility(nonEmptyVisibility);
    }

    protected void onMeasure(int i, int j)
    {
        if(getContext().obtainStyledAttributes(attrs, com.sonos.acr.R.styleable.SonosTextView).getInt(2, -1) != 4);
        super.onMeasure(i, j);
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        updateEmptyVisibility();
    }

    public void setEmptyVisibility(int i)
    {
        emptyVisibility = i;
        updateEmptyVisibility();
    }

    public void setForceUpperCase(boolean flag)
    {
        forceUpperCase = flag;
        setText(getText());
    }

    public void setText(CharSequence charsequence, android.widget.TextView.BufferType buffertype)
    {
        if(forceUpperCase && charsequence != null)
            charsequence = charsequence.toString().toUpperCase();
        hasEmptyText = StringUtils.isEmptyOrNull(charsequence);
        super.setText(charsequence, buffertype);
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(!hasEmptyText)
            nonEmptyVisibility = i;
    }

    public static int EMPTY_VISIBILITY_MATCH = -1;
    AttributeSet attrs;
    int emptyVisibility;
    boolean forceUpperCase;
    boolean hasEmptyText;
    boolean hasInitialized;
    int nonEmptyVisibility;

}
