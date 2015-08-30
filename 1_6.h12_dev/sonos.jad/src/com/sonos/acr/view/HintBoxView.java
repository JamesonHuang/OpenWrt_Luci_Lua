// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextPaint;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.text.BreakIterator;

public class HintBoxView extends FrameLayout
    implements android.view.ViewTreeObserver.OnPreDrawListener
{
    public static final class HintPosition extends Enum
    {

        public static HintPosition valueOf(String s)
        {
            return (HintPosition)Enum.valueOf(com/sonos/acr/view/HintBoxView$HintPosition, s);
        }

        public static HintPosition[] values()
        {
            return (HintPosition[])$VALUES.clone();
        }

        private static final HintPosition $VALUES[];
        public static final HintPosition AboveView_CenterScreenAligned;
        public static final HintPosition AboveView_LeftScreenAligned;
        public static final HintPosition AboveView_LeftScreenAligned_BeakViewAligned;
        public static final HintPosition AboveView_LeftViewAligned;
        public static final HintPosition AboveView_RightScreenAligned;
        public static final HintPosition AboveView_RightScreenAligned_BeakViewAligned;
        public static final HintPosition AboveView_RightViewAligned;
        public static final HintPosition BelowView_CenterScreenAligned;
        public static final HintPosition BelowView_LeftScreenAligned;
        public static final HintPosition BelowView_LeftScreenAligned_BeakViewAligned;
        public static final HintPosition BelowView_LeftViewAligned;
        public static final HintPosition BelowView_RightScreenAligned;
        public static final HintPosition BelowView_RightScreenAligned_BeakViewAligned;
        public static final HintPosition BelowView_RightViewAligned;
        public static final HintPosition Unknown;

        static 
        {
            Unknown = new HintPosition("Unknown", 0);
            BelowView_LeftScreenAligned = new HintPosition("BelowView_LeftScreenAligned", 1);
            BelowView_RightScreenAligned = new HintPosition("BelowView_RightScreenAligned", 2);
            BelowView_CenterScreenAligned = new HintPosition("BelowView_CenterScreenAligned", 3);
            AboveView_LeftScreenAligned = new HintPosition("AboveView_LeftScreenAligned", 4);
            AboveView_RightScreenAligned = new HintPosition("AboveView_RightScreenAligned", 5);
            AboveView_CenterScreenAligned = new HintPosition("AboveView_CenterScreenAligned", 6);
            BelowView_LeftScreenAligned_BeakViewAligned = new HintPosition("BelowView_LeftScreenAligned_BeakViewAligned", 7);
            BelowView_RightScreenAligned_BeakViewAligned = new HintPosition("BelowView_RightScreenAligned_BeakViewAligned", 8);
            AboveView_LeftScreenAligned_BeakViewAligned = new HintPosition("AboveView_LeftScreenAligned_BeakViewAligned", 9);
            AboveView_RightScreenAligned_BeakViewAligned = new HintPosition("AboveView_RightScreenAligned_BeakViewAligned", 10);
            BelowView_LeftViewAligned = new HintPosition("BelowView_LeftViewAligned", 11);
            BelowView_RightViewAligned = new HintPosition("BelowView_RightViewAligned", 12);
            AboveView_LeftViewAligned = new HintPosition("AboveView_LeftViewAligned", 13);
            AboveView_RightViewAligned = new HintPosition("AboveView_RightViewAligned", 14);
            HintPosition ahintposition[] = new HintPosition[15];
            ahintposition[0] = Unknown;
            ahintposition[1] = BelowView_LeftScreenAligned;
            ahintposition[2] = BelowView_RightScreenAligned;
            ahintposition[3] = BelowView_CenterScreenAligned;
            ahintposition[4] = AboveView_LeftScreenAligned;
            ahintposition[5] = AboveView_RightScreenAligned;
            ahintposition[6] = AboveView_CenterScreenAligned;
            ahintposition[7] = BelowView_LeftScreenAligned_BeakViewAligned;
            ahintposition[8] = BelowView_RightScreenAligned_BeakViewAligned;
            ahintposition[9] = AboveView_LeftScreenAligned_BeakViewAligned;
            ahintposition[10] = AboveView_RightScreenAligned_BeakViewAligned;
            ahintposition[11] = BelowView_LeftViewAligned;
            ahintposition[12] = BelowView_RightViewAligned;
            ahintposition[13] = AboveView_LeftViewAligned;
            ahintposition[14] = AboveView_RightViewAligned;
            $VALUES = ahintposition;
        }

        private HintPosition(String s, int i)
        {
            super(s, i);
        }
    }


    public HintBoxView(Context context)
    {
        super(context);
        hintPosition = HintPosition.Unknown;
        width = 0;
        height = 0;
        extraXMargin = 0;
        extraYMargin = 0;
        listeningForPreDraw = false;
        init();
    }

    private void init()
    {
        setLayoutParams(new android.widget.FrameLayout.LayoutParams(-2, -2, 48));
        LayoutInflater.from(getContext()).inflate(0x7f030025, this, true);
        box = findViewById(0x7f0a009e);
        hintTextView = (TextView)box;
        topBeak = findViewById(0x7f0a009d);
        bottomBeak = findViewById(0x7f0a009f);
    }

    private void positionHint()
    {
        if(width > 0 && height > 0 && hookedView != null && hintPosition != HintPosition.Unknown)
        {
            View view = (View)getParent();
            if(view != null)
            {
                int ai[] = new int[2];
                view.getLocationInWindow(ai);
                int i = ai[1];
                int ai1[] = new int[2];
                hookedView.getLocationInWindow(ai1);
                int ai2[] = new int[2];
                hookedView.getLocationOnScreen(ai2);
                int ai3[] = new int[2];
                hookedView.getLocationOnScreen(ai3);
                ai1[1] = ai1[1] - i;
                int j = view.getWidth();
                int k = hookedView.getHeight();
                int l = hookedView.getWidth();
                Resources resources = getResources();
                float f = resources.getDimension(0x7f09005d) + resources.getDimension(0x7f090065);
                float f1 = resources.getDimension(0x7f090079);
                float f2 = resources.getDimension(0x7f09000b);
                float f3 = resources.getDimension(0x7f090000);
                if(hintPosition == HintPosition.BelowView_LeftScreenAligned || hintPosition == HintPosition.AboveView_LeftScreenAligned)
                {
                    x = 0 + extraXMargin;
                    beakX = (int)f;
                } else
                if(hintPosition == HintPosition.BelowView_RightScreenAligned || hintPosition == HintPosition.AboveView_RightScreenAligned)
                {
                    x = j - width - extraXMargin;
                    beakX = width - topBeak.getWidth() - (int)f;
                } else
                if(hintPosition == HintPosition.BelowView_CenterScreenAligned || hintPosition == HintPosition.AboveView_CenterScreenAligned)
                {
                    x = (int)((float)j / 2.0F - (float)width / 2.0F);
                    beakX = (int)((float)width / 2.0F - (float)topBeak.getWidth() / 2.0F);
                } else
                if(hintPosition == HintPosition.BelowView_LeftScreenAligned_BeakViewAligned || hintPosition == HintPosition.AboveView_LeftScreenAligned_BeakViewAligned)
                {
                    x = 0 + extraXMargin;
                    beakX = ai1[0] + (int)f1;
                } else
                if(hintPosition == HintPosition.BelowView_RightScreenAligned_BeakViewAligned || hintPosition == HintPosition.AboveView_RightScreenAligned_BeakViewAligned)
                {
                    x = j - width - extraXMargin;
                    beakX = (ai1[0] - (j - width)) + (int)f1;
                } else
                if(hintPosition == HintPosition.BelowView_LeftViewAligned || hintPosition == HintPosition.AboveView_LeftViewAligned)
                {
                    x = ai1[0] + extraXMargin;
                    beakX = (int)f;
                } else
                if(hintPosition == HintPosition.BelowView_RightViewAligned || hintPosition == HintPosition.AboveView_RightViewAligned)
                {
                    x = (l + ai1[0]) - width - extraXMargin;
                    beakX = width - topBeak.getWidth() - (int)f;
                }
                if(hintPosition == HintPosition.BelowView_LeftScreenAligned || hintPosition == HintPosition.BelowView_RightScreenAligned || hintPosition == HintPosition.BelowView_CenterScreenAligned || hintPosition == HintPosition.BelowView_LeftScreenAligned_BeakViewAligned || hintPosition == HintPosition.BelowView_RightScreenAligned_BeakViewAligned || hintPosition == HintPosition.BelowView_LeftViewAligned || hintPosition == HintPosition.BelowView_RightViewAligned)
                {
                    y = k + ai1[1] + (int)f2 + extraYMargin;
                    ((android.view.ViewGroup.MarginLayoutParams)getLayoutParams()).setMargins(x, y, 0, 0);
                    ((android.view.ViewGroup.MarginLayoutParams)topBeak.getLayoutParams()).setMargins(beakX, 0, 0, 0);
                    topBeak.setVisibility(0);
                    bottomBeak.setVisibility(8);
                } else
                {
                    y = ((ai1[1] - height) + (int)f3) - extraYMargin;
                    ((android.view.ViewGroup.MarginLayoutParams)getLayoutParams()).setMargins(x, y, 0, 0);
                    ((android.view.ViewGroup.MarginLayoutParams)bottomBeak.getLayoutParams()).setMargins(beakX, 0, 0, 0);
                    topBeak.setVisibility(8);
                    bottomBeak.setVisibility(0);
                }
            }
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnPreDrawListener(this);
        listeningForPreDraw = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(listeningForPreDraw)
        {
            getViewTreeObserver().removeOnPreDrawListener(this);
            listeningForPreDraw = false;
        }
        if(hookedView != null)
            hookedView = null;
        width = 0;
        height = 0;
        hintPosition = HintPosition.Unknown;
    }

    public boolean onPreDraw()
    {
        width = getWidth();
        height = getHeight();
        if(width > 0 && height > 0)
        {
            getViewTreeObserver().removeOnPreDrawListener(this);
            listeningForPreDraw = false;
            android.view.ViewGroup.LayoutParams layoutparams = getLayoutParams();
            layoutparams.width = width;
            layoutparams.height = height;
            setLayoutParams(layoutparams);
            if(hintTextView.getText().length() > 0)
                positionHint();
        }
        return true;
    }

    public void setExtraMargins(int i, int j)
    {
        extraXMargin = i;
        extraYMargin = j;
    }

    public void setHint(String s)
    {
        if(hintTextView != null)
        {
            hintTextView.setText(s);
            TextPaint textpaint = hintTextView.getPaint();
            float f = getResources().getDimension(0x7f09003c);
            float f1 = getResources().getDimension(0x7f090027);
            float f2 = f - f1 * 2.0F;
            float f3 = 0.0F;
            int i = 0;
            int j = s.length();
            BreakIterator breakiterator = BreakIterator.getLineInstance();
            breakiterator.setText(s);
            while(i < s.length()) 
            {
                int k = textpaint.breakText(s, i, j, true, f2, null);
                int l = i + k;
                if(l < s.length() && !breakiterator.isBoundary(l))
                    l = breakiterator.preceding(l);
                if(l > i)
                {
                    float f4 = textpaint.measureText(s, i, l);
                    if(f4 > f3)
                        f3 = f4;
                } else
                {
                    f3 = f2;
                    l = i + k;
                }
                i = l;
                j = s.length();
            }
            if(f3 > 0.0F)
                hintTextView.setMaxWidth((int)Math.ceil(f3 + f1 * 2.0F));
            positionHint();
        }
    }

    public void setHintPosition(HintPosition hintposition)
    {
        hintPosition = hintposition;
    }

    public void setView(View view)
    {
        hookedView = view;
        positionHint();
    }

    private int beakX;
    private View bottomBeak;
    private View box;
    private int extraXMargin;
    private int extraYMargin;
    private int height;
    private HintPosition hintPosition;
    private TextView hintTextView;
    private View hookedView;
    private boolean listeningForPreDraw;
    private View topBeak;
    private int width;
    private int x;
    private int y;
}
