// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import com.sonos.acr.util.ViewUtils;

// Referenced classes of package com.sonos.acr.view:
//            HintBoxView

public class HintsView extends FrameLayout
    implements android.view.ViewTreeObserver.OnGlobalLayoutListener
{
    public static interface OnHintsDismissedListener
    {

        public abstract void onHintsDismissed();
    }

    public static class Hint
    {

        public int extraXMargin;
        public int extraYMargin;
        public HintBoxView.HintPosition hintPosition;
        public String hintText;
        public View targetView;

        public Hint()
        {
        }
    }


    public HintsView(Context context)
    {
        super(context);
        LOG_TAG = "HINTS";
        layoutWaitTime = 1000;
        fadeInDuration = 400;
        init();
    }

    public HintsView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        LOG_TAG = "HINTS";
        layoutWaitTime = 1000;
        fadeInDuration = 400;
        init();
    }

    public HintsView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        LOG_TAG = "HINTS";
        layoutWaitTime = 1000;
        fadeInDuration = 400;
        init();
    }

    private HintBoxView addHintForView(View view)
    {
        HintBoxView hintboxview = new HintBoxView(getContext());
        hintboxview.setView(view);
        hintsContainer.addView(hintboxview);
        return hintboxview;
    }

    private void cleanUp()
    {
        if(showHintsRunnable != null)
        {
            handler.removeCallbacks(showHintsRunnable);
            showHintsRunnable = null;
        }
        if(viewTreeObserver != null)
        {
            if(viewTreeObserver.isAlive())
                if(android.os.Build.VERSION.SDK_INT < 16)
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                else
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
            viewTreeObserver = null;
        }
    }

    private void dismissHints()
    {
        forceDismissHints();
        if(hintsDismissedListener != null)
            hintsDismissedListener.onHintsDismissed();
    }

    private void fadeIn(int i)
    {
        fadeInDuration = i;
        cleanUp();
        viewTreeObserver = ((View)getParent()).getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(this);
        showHintsRunnable = new Runnable() {

            public void run()
            {
                if(viewTreeObserver != null && viewTreeObserver.isAlive())
                    if(android.os.Build.VERSION.SDK_INT < 16)
                        viewTreeObserver.removeGlobalOnLayoutListener(HintsView.this);
                    else
                        viewTreeObserver.removeOnGlobalLayoutListener(HintsView.this);
                viewTreeObserver = null;
                showHintsRunnable = null;
                hintsContainer.setVisibility(0);
                if(android.os.Build.VERSION.SDK_INT < 11)
                {
                    AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
                    alphaanimation.setDuration(fadeInDuration);
                    hintsLayer.startAnimation(alphaanimation);
                } else
                {
                    ViewGroup viewgroup = hintsLayer;
                    float af[] = new float[2];
                    af[0] = 0.0F;
                    af[1] = 1.0F;
                    ObjectAnimator objectanimator = ObjectAnimator.ofFloat(viewgroup, "alpha", af);
                    objectanimator.setDuration(fadeInDuration);
                    objectanimator.start();
                }
_L2:
                return;
                Exception exception;
                exception;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final HintsView this$0;

            
            {
                this$0 = HintsView.this;
                super();
            }
        }
;
        handler.postDelayed(showHintsRunnable, layoutWaitTime);
        ((View)getParent()).invalidate();
    }

    private void init()
    {
        LayoutInflater.from(getContext()).inflate(0x7f030026, this, true);
        hintsLayer = (ViewGroup)findViewById(0x7f0a00a0);
        hintsContainer = (ViewGroup)findViewById(0x7f0a00a2);
        setVisibility(8);
        ViewUtils.setAlpha(hintsLayer, 0.0F);
        ViewUtils.setAlpha(findViewById(0x7f0a00a1), 0.6F);
        ViewUtils.setAlpha(hintsContainer, 0.8F);
        hintsContainer.setVisibility(8);
        findViewById(0x7f0a00a3).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                dismissHints();
            }

            final HintsView this$0;

            
            {
                this$0 = HintsView.this;
                super();
            }
        }
);
        handler = new Handler(getContext().getMainLooper());
    }

    public void forceDismissHints()
    {
        setVisibility(8);
        hintsContainer.setVisibility(8);
        hintsContainer.removeAllViews();
        ViewUtils.setAlpha(hintsLayer, 0.0F);
        cleanUp();
    }

    public void onGlobalLayout()
    {
        if(showHintsRunnable != null)
        {
            handler.removeCallbacks(showHintsRunnable);
            handler.postDelayed(showHintsRunnable, layoutWaitTime);
        }
    }

    public void showHints(Hint ahint[], OnHintsDismissedListener onhintsdismissedlistener)
    {
        forceDismissHints();
        hintsDismissedListener = onhintsdismissedlistener;
        if(ahint != null)
        {
            int i = ahint.length;
            for(int j = 0; j < i; j++)
            {
                Hint hint = ahint[j];
                if(hint != null && hint.targetView != null && hint.hintText != null && hint.hintText.length() > 0)
                {
                    HintBoxView hintboxview = addHintForView(hint.targetView);
                    hintboxview.setHint(hint.hintText);
                    hintboxview.setHintPosition(hint.hintPosition);
                    hintboxview.setExtraMargins(hint.extraXMargin, hint.extraYMargin);
                }
            }

        }
        setVisibility(0);
        fadeIn(fadeInDuration);
    }

    public static final int HINTS_VERSION = 1;
    public final String LOG_TAG;
    private int fadeInDuration;
    private Handler handler;
    private ViewGroup hintsContainer;
    private OnHintsDismissedListener hintsDismissedListener;
    private ViewGroup hintsLayer;
    private int layoutWaitTime;
    private Runnable showHintsRunnable;
    private ViewTreeObserver viewTreeObserver;



/*
    static ViewTreeObserver access$002(HintsView hintsview, ViewTreeObserver viewtreeobserver)
    {
        hintsview.viewTreeObserver = viewtreeobserver;
        return viewtreeobserver;
    }

*/


/*
    static Runnable access$102(HintsView hintsview, Runnable runnable)
    {
        hintsview.showHintsRunnable = runnable;
        return runnable;
    }

*/




}
