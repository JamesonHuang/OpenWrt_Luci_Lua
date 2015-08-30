// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class CustomBoldTypefaceSpan extends MetricAffectingSpan
{

    public CustomBoldTypefaceSpan(Typeface typeface1)
    {
        typeface = typeface1;
    }

    private void apply(Paint paint)
    {
        paint.setFakeBoldText(true);
        paint.setTypeface(typeface);
    }

    public void updateDrawState(TextPaint textpaint)
    {
        apply(textpaint);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        apply(textpaint);
    }

    private final Typeface typeface;
}
