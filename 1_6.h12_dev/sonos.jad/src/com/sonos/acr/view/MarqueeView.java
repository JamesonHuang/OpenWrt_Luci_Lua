// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.*;
import android.graphics.Paint;
import android.opengl.GLES10;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.*;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.*;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.ViewUtils;

// Referenced classes of package com.sonos.acr.view:
//            SonosTextView

public class MarqueeView extends FrameLayout
{
    public static interface MarqueeListener
    {

        public abstract void onAnimationStateChange(MarqueeView marqueeview);

        public abstract void onComplete(MarqueeView marqueeview);

        public abstract void onStart(MarqueeView marqueeview);

        public abstract void onTextChange(MarqueeView marqueeview);
    }


    public MarqueeView(Context context)
    {
        super(context);
        mMoveTextOut = null;
        mMarqueeNeeded = false;
        mSpeed = 40;
        mAnimationPause = 2000;
        mInterpolator = new LinearInterpolator();
        mCancelled = false;
        defStyle = 0;
        mTextWidth = 0.0F;
        text = "";
        emptyVisibility = SonosTextView.EMPTY_VISIBILITY_MATCH;
        init(context);
    }

    public MarqueeView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMoveTextOut = null;
        mMarqueeNeeded = false;
        mSpeed = 40;
        mAnimationPause = 2000;
        mInterpolator = new LinearInterpolator();
        mCancelled = false;
        defStyle = 0;
        mTextWidth = 0.0F;
        text = "";
        emptyVisibility = SonosTextView.EMPTY_VISIBILITY_MATCH;
        attrs = attributeset;
        init(context);
        defStyle = attributeset.getStyleAttribute();
    }

    public MarqueeView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mMoveTextOut = null;
        mMarqueeNeeded = false;
        mSpeed = 40;
        mAnimationPause = 2000;
        mInterpolator = new LinearInterpolator();
        mCancelled = false;
        defStyle = 0;
        mTextWidth = 0.0F;
        text = "";
        emptyVisibility = SonosTextView.EMPTY_VISIBILITY_MATCH;
        defStyle = i;
        attrs = attributeset;
        init(context);
    }

    private void cutTextView(boolean flag)
    {
        android.view.ViewGroup.LayoutParams layoutparams = mLinearLayout.getLayoutParams();
        layoutparams.width = -1;
        mLinearLayout.setLayoutParams(layoutparams);
        if(flag)
            mTextField2.setVisibility(8);
        else
            SonosApplication.getInstance().getHandler().postDelayed(new Runnable() {

                public void run()
                {
                    mTextField2.setVisibility(8);
                }

                final MarqueeView this$0;

            
            {
                this$0 = MarqueeView.this;
                super();
            }
            }
, 40L);
    }

    private void expandTextView(boolean flag)
    {
        reevaluateMarquee();
        mLinearLayout.setLayoutParams(new android.widget.FrameLayout.LayoutParams((int)(2.0F * mTextWidth) + getResources().getDimensionPixelOffset(0x7f09003b), -2));
        if(flag)
            mTextField2.setVisibility(0);
        else
            SonosApplication.getInstance().getHandler().postDelayed(new Runnable() {

                public void run()
                {
                    mTextField2.setVisibility(0);
                }

                final MarqueeView this$0;

            
            {
                this$0 = MarqueeView.this;
                super();
            }
            }
, 40L);
    }

    private int getMaxTextureSize()
    {
        int ai[] = new int[1];
        GLES10.glGetIntegerv(3379, ai, 0);
        return ai[0];
    }

    private void init(Context context)
    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1.0F);
        mPaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        mInterpolator = new LinearInterpolator();
        initView(getContext());
    }

    private void initView(Context context)
    {
        setClickable(false);
        android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-1, -2);
        layoutparams.gravity = 1;
        mLinearLayout = new LinearLayout(context);
        mLinearLayout.setDuplicateParentStateEnabled(isDuplicateParentStateEnabled());
        mLinearLayout.setClickable(false);
        mLinearLayout.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-2, -2));
        mLinearLayout.setOrientation(0);
        setUpTextViews(context);
        android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(getMeasuredWidth(), -2);
        layoutparams1.leftMargin = getResources().getDimensionPixelOffset(0x7f09003b);
        layoutparams1.gravity = 1;
        android.widget.LinearLayout.LayoutParams layoutparams2 = new android.widget.LinearLayout.LayoutParams(-2, -2);
        layoutparams2.gravity = 1;
        mLinearLayout.addView(mTextField, layoutparams2);
        mLinearLayout.addView(mTextField2, layoutparams1);
        mTextField2.setVisibility(8);
        addView(mLinearLayout, layoutparams);
        cutTextView(true);
    }

    private void prepareAnimation()
    {
        reevaluateMarquee();
        int i = (int)(1000D * (double)((mTextWidth + (float)getResources().getDimensionPixelOffset(0x7f09003b)) / (float)(int)(0.5F + getResources().getDisplayMetrics().density * (float)mSpeed)));
        if(android.os.Build.VERSION.SDK_INT >= 16 && !android.os.Build.VERSION.RELEASE.equals("4.4.3"))
        {
            LinearLayout linearlayout = mLinearLayout;
            float af[] = new float[1];
            af[0] = -(mTextWidth + (float)getResources().getDimensionPixelOffset(0x7f09003b));
            animator = ObjectAnimator.ofFloat(linearlayout, "translationX", af);
            animator.setDuration(i);
            animator.setInterpolator(mInterpolator);
            animator.addListener(new android.animation.Animator.AnimatorListener() {

                public void onAnimationCancel(Animator animator1)
                {
                }

                public void onAnimationEnd(Animator animator1)
                {
                    animator.setCurrentPlayTime(0L);
                    mLinearLayout.setLayerType(0, null);
                    if(mMarqueeListener != null)
                        mMarqueeListener.onComplete(MarqueeView.this);
                    cutTextView(true);
                    immediateCut = false;
                }

                public void onAnimationRepeat(Animator animator1)
                {
                }

                public void onAnimationStart(Animator animator1)
                {
                    immediateCut = false;
                    if((int)(2.0F * mTextWidth) + getResources().getDimensionPixelOffset(0x7f09003b) <= getMaxTextureSize())
                        mLinearLayout.setLayerType(2, null);
                    else
                        Log.e(MarqueeView.TAG, "Cannot use hardware acceleration, marquee text is too long!");
                    expandTextView(true);
                    if(mMarqueeListener != null)
                        mMarqueeListener.onStart(MarqueeView.this);
                }

                final MarqueeView this$0;

            
            {
                this$0 = MarqueeView.this;
                super();
            }
            }
);
        } else
        {
            mMoveTextOut = new TranslateAnimation(0.0F, -(mTextWidth + (float)getResources().getDimensionPixelOffset(0x7f09003b)), 0.0F, 0.0F);
            mMoveTextOut.setDuration(i);
            mMoveTextOut.setInterpolator(mInterpolator);
            mMoveTextOut.setFillAfter(true);
            mMoveTextOut.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                public void onAnimationEnd(Animation animation)
                {
                    mLinearLayout.clearAnimation();
                    if(mMarqueeListener != null)
                        mMarqueeListener.onComplete(MarqueeView.this);
                    MarqueeView marqueeview = MarqueeView.this;
                    boolean flag;
                    if(mLinearLayout.getTag() != null)
                        flag = true;
                    else
                        flag = false;
                    marqueeview.cutTextView(flag);
                    immediateCut = false;
                }

                public void onAnimationRepeat(Animation animation)
                {
                }

                public void onAnimationStart(Animation animation)
                {
                    expandTextView(false);
                    immediateCut = false;
                    if(mMarqueeListener != null)
                        mMarqueeListener.onStart(MarqueeView.this);
                }

                final MarqueeView this$0;

            
            {
                this$0 = MarqueeView.this;
                super();
            }
            }
);
        }
    }

    private void reevaluateMarquee()
    {
        mPaint.setTextSize(mTextField.getTextSize());
        mPaint.setTypeface(mTextField.getTypeface());
        mTextWidth = mPaint.measureText(mTextField.getText().toString());
        boolean flag;
        if(mTextWidth > (float)getMeasuredWidth() && getMeasuredWidth() > 0)
            flag = true;
        else
            flag = false;
        mMarqueeNeeded = flag;
    }

    private void setUpTextViews(Context context)
    {
        TypedArray typedarray;
        mTextField = new SonosTextView(context, attrs, defStyle);
        mTextField2 = new SonosTextView(context, attrs, defStyle);
        if(!isInEditMode())
        {
            mTextField.setTextAppearance(context, defStyle);
            mTextField2.setTextAppearance(context, defStyle);
        }
        mTextField.setClickable(false);
        mTextField2.setClickable(false);
        mTextField.setSingleLine();
        mTextField2.setSingleLine();
        mTextField.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mTextField2.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mTextField.setTag(null);
        mTextField2.setTag(null);
        mTextField.setDuplicateParentStateEnabled(isDuplicateParentStateEnabled());
        mTextField2.setDuplicateParentStateEnabled(isDuplicateParentStateEnabled());
        typedarray = context.getTheme().obtainStyledAttributes(attrs, com.sonos.acr.R.styleable.MarqueeView, 0, defStyle);
        mTextField.setForceUpperCase(typedarray.getBoolean(9, false));
        mTextField2.setForceUpperCase(typedarray.getBoolean(9, false));
        typedarray.getInt(10, -1);
        JVM INSTR tableswitch 3 6: default 244
    //                   3 357
    //                   4 380
    //                   5 403
    //                   6 426;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        mTextField.setTypeface(ViewUtils.SONOS_REGULAR);
        mTextField2.setTypeface(ViewUtils.SONOS_REGULAR);
_L7:
        mTextField.setEmptyVisibility(typedarray.getInt(8, emptyVisibility));
        mTextField2.setEmptyVisibility(typedarray.getInt(8, emptyVisibility));
        mTextField.setTextSize(0, typedarray.getDimensionPixelSize(0, 16));
        mTextField2.setTextSize(0, typedarray.getDimensionPixelSize(0, 16));
        typedarray.recycle();
        mTextField.setText(text);
        mTextField2.setText(text);
        return;
_L2:
        mTextField.setTypeface(ViewUtils.SONOS_ITALIC);
        mTextField2.setTypeface(ViewUtils.SONOS_ITALIC);
        continue; /* Loop/switch isn't completed */
_L3:
        mTextField.setTypeface(ViewUtils.SONOS_LIGHT);
        mTextField2.setTypeface(ViewUtils.SONOS_LIGHT);
        continue; /* Loop/switch isn't completed */
_L4:
        mTextField.setTypeface(ViewUtils.SONOS_MEDIUM);
        mTextField2.setTypeface(ViewUtils.SONOS_MEDIUM);
        continue; /* Loop/switch isn't completed */
_L5:
        mTextField.setTypeface(ViewUtils.SONOS_MEDIUM_ITALIC);
        mTextField2.setTypeface(ViewUtils.SONOS_MEDIUM_ITALIC);
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void startTextFieldAnimation()
    {
        mAnimationStartRunnable = new Runnable() {

            public void run()
            {
                updateView();
                prepareAnimation();
                if(android.os.Build.VERSION.SDK_INT >= 16 && !android.os.Build.VERSION.RELEASE.equals("4.4.3"))
                    animator.start();
                else
                    mLinearLayout.startAnimation(mMoveTextOut);
            }

            final MarqueeView this$0;

            
            {
                this$0 = MarqueeView.this;
                super();
            }
        }
;
        postDelayed(mAnimationStartRunnable, mAnimationPause);
    }

    private void updateView()
    {
        if(mTextField != null && mTextField2 != null)
        {
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(getMeasuredWidth(), -2);
            layoutparams.leftMargin = getResources().getDimensionPixelOffset(0x7f09003b);
            layoutparams.gravity = 3;
            android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-2, -2);
            layoutparams1.gravity = 3;
            mTextField.setLayoutParams(layoutparams1);
            mTextField2.setLayoutParams(layoutparams);
            if(!mTextField.getText().toString().equals(text))
            {
                mTextField.setText(text);
                mTextField2.setText(text);
            }
            cutTextView(true);
        }
    }

    public boolean canAnimate()
    {
        reevaluateMarquee();
        return mMarqueeNeeded;
    }

    public boolean isReallyVisible()
    {
        ViewParent viewparent = getParent();
_L3:
        if(viewparent == null || !(viewparent instanceof View))
            break MISSING_BLOCK_LABEL_55;
        if(((View)viewparent).getVisibility() == 0 || viewparent == getRootView() || (viewparent instanceof DrawerLayout)) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        viewparent = viewparent.getParent();
          goto _L3
        flag = true;
          goto _L4
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(mMarqueeListener != null)
            mMarqueeListener.onAnimationStateChange(this);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag && mMarqueeListener != null && isReallyVisible())
            mMarqueeListener.onAnimationStateChange(this);
    }

    public void reset()
    {
        mCancelled = true;
        if(mAnimationStartRunnable != null)
            removeCallbacks(mAnimationStartRunnable);
        if(mLinearLayout != null)
        {
            immediateCut = true;
            mLinearLayout.clearAnimation();
        }
        mStarted = false;
        if(android.os.Build.VERSION.SDK_INT < 16 || android.os.Build.VERSION.RELEASE.equals("4.4.3") || animator == null) goto _L2; else goto _L1
_L1:
        animator.end();
_L4:
        return;
_L2:
        if(mMoveTextOut != null)
            mMoveTextOut.reset();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mInterpolator = interpolator;
    }

    public void setMarqueeListener(MarqueeListener marqueelistener)
    {
        mMarqueeListener = marqueelistener;
    }

    public void setPauseBetweenAnimations(int i)
    {
        mAnimationPause = i;
    }

    public void setSpeed(int i)
    {
        mSpeed = i;
    }

    public void setText(String s)
    {
        if(text == null || !text.equals(s))
        {
            text = s;
            reevaluateMarquee();
            if(mTextField != null && mTextField2 != null)
            {
                mTextField.setText(text);
                mTextField2.setText(text);
                if(mMarqueeListener != null && isReallyVisible())
                    mMarqueeListener.onTextChange(this);
                updateView();
            }
        }
        if(mTextField != null && mTextField2 != null)
        {
            mTextField.setText(text);
            mTextField2.setText(text);
        }
    }

    public void startMarquee()
    {
        reevaluateMarquee();
        if(mMarqueeNeeded)
            startTextFieldAnimation();
        mCancelled = false;
        mStarted = true;
    }

    private static final int DEFAULT_ANIMATION_PAUSE = 2000;
    private static final int DEFAULT_SPEED = 40;
    private static final String TAG = com/sonos/acr/view/MarqueeView.getSimpleName();
    private ObjectAnimator animator;
    AttributeSet attrs;
    int defStyle;
    int emptyVisibility;
    boolean immediateCut;
    private int mAnimationPause;
    private Runnable mAnimationStartRunnable;
    private boolean mCancelled;
    private Interpolator mInterpolator;
    private LinearLayout mLinearLayout;
    MarqueeListener mMarqueeListener;
    private boolean mMarqueeNeeded;
    private Animation mMoveTextOut;
    private Paint mPaint;
    private int mSpeed;
    private boolean mStarted;
    private float mTextDifference;
    private SonosTextView mTextField;
    private SonosTextView mTextField2;
    float mTextWidth;
    private String text;











}
