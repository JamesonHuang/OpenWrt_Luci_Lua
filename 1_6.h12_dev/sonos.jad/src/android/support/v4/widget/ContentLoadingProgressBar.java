// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ContentLoadingProgressBar extends ProgressBar
{

    public ContentLoadingProgressBar(Context context)
    {
        this(context, null);
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0);
        mStartTime = -1L;
        mPostedHide = false;
        mPostedShow = false;
        mDismissed = false;
        mDelayedHide = new Runnable() {

            public void run()
            {
                mPostedHide = false;
                mStartTime = -1L;
                setVisibility(8);
            }

            final ContentLoadingProgressBar this$0;

            
            {
                this$0 = ContentLoadingProgressBar.this;
                super();
            }
        }
;
        mDelayedShow = new Runnable() {

            public void run()
            {
                mPostedShow = false;
                if(!mDismissed)
                {
                    mStartTime = System.currentTimeMillis();
                    setVisibility(0);
                }
            }

            final ContentLoadingProgressBar this$0;

            
            {
                this$0 = ContentLoadingProgressBar.this;
                super();
            }
        }
;
    }

    private void removeCallbacks()
    {
        removeCallbacks(mDelayedHide);
        removeCallbacks(mDelayedShow);
    }

    public void hide()
    {
        long l;
        mDismissed = true;
        removeCallbacks(mDelayedShow);
        l = System.currentTimeMillis() - mStartTime;
        if(l < 500L && mStartTime != -1L) goto _L2; else goto _L1
_L1:
        setVisibility(8);
_L4:
        return;
_L2:
        if(!mPostedHide)
        {
            postDelayed(mDelayedHide, 500L - l);
            mPostedHide = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    public void show()
    {
        mStartTime = -1L;
        mDismissed = false;
        removeCallbacks(mDelayedHide);
        if(!mPostedShow)
        {
            postDelayed(mDelayedShow, 500L);
            mPostedShow = true;
        }
    }

    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;
    private boolean mDismissed;
    private boolean mPostedHide;
    private boolean mPostedShow;
    private long mStartTime;


/*
    static boolean access$002(ContentLoadingProgressBar contentloadingprogressbar, boolean flag)
    {
        contentloadingprogressbar.mPostedHide = flag;
        return flag;
    }

*/


/*
    static long access$102(ContentLoadingProgressBar contentloadingprogressbar, long l)
    {
        contentloadingprogressbar.mStartTime = l;
        return l;
    }

*/


/*
    static boolean access$202(ContentLoadingProgressBar contentloadingprogressbar, boolean flag)
    {
        contentloadingprogressbar.mPostedShow = flag;
        return flag;
    }

*/

}
