// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import com.sonos.acr.util.StringUtils;

// Referenced classes of package com.sonos.acr.view:
//            SonosTextView

public class SonosAlternateTextView extends SonosTextView
{

    public SonosAlternateTextView(Context context)
    {
        super(context);
    }

    public SonosAlternateTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public SonosAlternateTextView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    private void measureForAlternateText()
    {
        TextPaint textpaint = getPaint();
        if(StringUtils.isNotEmptyOrNull(shortText) && StringUtils.isNotEmptyOrNull(longText) && getText().toString().toUpperCase().equals(longText.toUpperCase()))
        {
            String s = longText;
            if(forceUpperCase)
                s = longText.toUpperCase();
            if((float)(getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) < textpaint.measureText(s))
                setText(shortText);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag)
            measureForAlternateText();
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        measureForAlternateText();
    }

    public void setTextStrings(String s, String s1)
    {
        longText = s;
        shortText = s1;
        setText(s);
        forceLayout();
    }

    private String longText;
    private String shortText;
}
