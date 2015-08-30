// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.text.TextUtils;
import android.util.*;
import android.view.View;
import android.widget.Toast;

// Referenced classes of package android.support.v7.app:
//            MediaRouterThemeHelper, MediaRouteDialogFactory, MediaRouteChooserDialogFragment, MediaRouteControllerDialogFragment

public class MediaRouteButton extends View
{
    private final class MediaRouterCallback extends android.support.v7.media.MediaRouter.Callback
    {

        public void onProviderAdded(MediaRouter mediarouter, android.support.v7.media.MediaRouter.ProviderInfo providerinfo)
        {
            refreshRoute();
        }

        public void onProviderChanged(MediaRouter mediarouter, android.support.v7.media.MediaRouter.ProviderInfo providerinfo)
        {
            refreshRoute();
        }

        public void onProviderRemoved(MediaRouter mediarouter, android.support.v7.media.MediaRouter.ProviderInfo providerinfo)
        {
            refreshRoute();
        }

        public void onRouteAdded(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute();
        }

        public void onRouteChanged(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute();
        }

        public void onRouteRemoved(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute();
        }

        public void onRouteSelected(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute();
        }

        public void onRouteUnselected(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute();
        }

        final MediaRouteButton this$0;

        private MediaRouterCallback()
        {
            this$0 = MediaRouteButton.this;
            super();
        }

    }


    public MediaRouteButton(Context context)
    {
        this(context, null);
    }

    public MediaRouteButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, android.support.v7.mediarouter.R.attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(Context context, AttributeSet attributeset, int i)
    {
        super(MediaRouterThemeHelper.createThemedContext(context, false), attributeset, i);
        mSelector = MediaRouteSelector.EMPTY;
        mDialogFactory = MediaRouteDialogFactory.getDefault();
        Context context1 = getContext();
        mRouter = MediaRouter.getInstance(context1);
        mCallback = new MediaRouterCallback();
        TypedArray typedarray = context1.obtainStyledAttributes(attributeset, android.support.v7.mediarouter.R.styleable.MediaRouteButton, i, 0);
        setRemoteIndicatorDrawable(typedarray.getDrawable(android.support.v7.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawable));
        mMinWidth = typedarray.getDimensionPixelSize(android.support.v7.mediarouter.R.styleable.MediaRouteButton_android_minWidth, 0);
        mMinHeight = typedarray.getDimensionPixelSize(android.support.v7.mediarouter.R.styleable.MediaRouteButton_android_minHeight, 0);
        typedarray.recycle();
        setClickable(true);
        setLongClickable(true);
    }

    private Activity getActivity()
    {
        Context context = getContext();
_L3:
        if(!(context instanceof ContextWrapper))
            break MISSING_BLOCK_LABEL_37;
        if(!(context instanceof Activity)) goto _L2; else goto _L1
_L1:
        Activity activity = (Activity)context;
_L4:
        return activity;
_L2:
        context = ((ContextWrapper)context).getBaseContext();
          goto _L3
        activity = null;
          goto _L4
    }

    private FragmentManager getFragmentManager()
    {
        Activity activity = getActivity();
        FragmentManager fragmentmanager;
        if(activity instanceof FragmentActivity)
            fragmentmanager = ((FragmentActivity)activity).getSupportFragmentManager();
        else
            fragmentmanager = null;
        return fragmentmanager;
    }

    private void refreshRoute()
    {
        boolean flag = false;
        if(mAttachedToWindow)
        {
            android.support.v7.media.MediaRouter.RouteInfo routeinfo = mRouter.getSelectedRoute();
            boolean flag1;
            boolean flag2;
            if(!routeinfo.isDefault() && routeinfo.matchesSelector(mSelector))
                flag1 = true;
            else
                flag1 = false;
            if(flag1 && routeinfo.isConnecting())
                flag = true;
            flag2 = false;
            if(mRemoteActive != flag1)
            {
                mRemoteActive = flag1;
                flag2 = true;
            }
            if(mIsConnecting != flag)
            {
                mIsConnecting = flag;
                flag2 = true;
            }
            if(flag2)
                refreshDrawableState();
            setEnabled(mRouter.isRouteAvailable(mSelector, 1));
        }
    }

    private void setRemoteIndicatorDrawable(Drawable drawable)
    {
        if(mRemoteIndicator != null)
        {
            mRemoteIndicator.setCallback(null);
            unscheduleDrawable(mRemoteIndicator);
        }
        mRemoteIndicator = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            boolean flag;
            if(getVisibility() == 0)
                flag = true;
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
        refreshDrawableState();
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(mRemoteIndicator != null)
        {
            int ai[] = getDrawableState();
            mRemoteIndicator.setState(ai);
            invalidate();
        }
    }

    public MediaRouteDialogFactory getDialogFactory()
    {
        return mDialogFactory;
    }

    public MediaRouteSelector getRouteSelector()
    {
        return mSelector;
    }

    public void jumpDrawablesToCurrentState()
    {
        if(getBackground() != null)
            DrawableCompat.jumpToCurrentState(getBackground());
        if(mRemoteIndicator != null)
            DrawableCompat.jumpToCurrentState(mRemoteIndicator);
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
        if(!mSelector.isEmpty())
            mRouter.addCallback(mSelector, mCallback);
        refreshRoute();
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if(!mIsConnecting) goto _L2; else goto _L1
_L1:
        mergeDrawableStates(ai, CHECKABLE_STATE_SET);
_L4:
        return ai;
_L2:
        if(mRemoteActive)
            mergeDrawableStates(ai, CHECKED_STATE_SET);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDetachedFromWindow()
    {
        mAttachedToWindow = false;
        if(!mSelector.isEmpty())
            mRouter.removeCallback(mCallback);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mRemoteIndicator != null)
        {
            int i = getPaddingLeft();
            int j = getWidth() - getPaddingRight();
            int k = getPaddingTop();
            int l = getHeight() - getPaddingBottom();
            int i1 = mRemoteIndicator.getIntrinsicWidth();
            int j1 = mRemoteIndicator.getIntrinsicHeight();
            int k1 = i + (j - i - i1) / 2;
            int l1 = k + (l - k - j1) / 2;
            mRemoteIndicator.setBounds(k1, l1, k1 + i1, l1 + j1);
            mRemoteIndicator.draw(canvas);
        }
    }

    protected void onMeasure(int i, int j)
    {
        int l;
        int i1;
        int j2;
        int l2;
        int i3;
        int j3;
        int k = 0;
        l = android.view.View.MeasureSpec.getSize(i);
        i1 = android.view.View.MeasureSpec.getSize(j);
        int j1 = android.view.View.MeasureSpec.getMode(i);
        int k1 = android.view.View.MeasureSpec.getMode(j);
        int l1 = mMinWidth;
        int i2;
        int k2;
        if(mRemoteIndicator != null)
            i2 = mRemoteIndicator.getIntrinsicWidth();
        else
            i2 = 0;
        j2 = Math.max(l1, i2);
        k2 = mMinHeight;
        if(mRemoteIndicator != null)
            k = mRemoteIndicator.getIntrinsicHeight();
        l2 = Math.max(k2, k);
        j1;
        JVM INSTR lookupswitch 2: default 116
    //                   -2147483648: 196
    //                   1073741824: 189;
           goto _L1 _L2 _L3
_L1:
        i3 = j2 + getPaddingLeft() + getPaddingRight();
_L7:
        k1;
        JVM INSTR lookupswitch 2: default 160
    //                   -2147483648: 225
    //                   1073741824: 218;
           goto _L4 _L5 _L6
_L4:
        j3 = l2 + getPaddingTop() + getPaddingBottom();
_L8:
        setMeasuredDimension(i3, j3);
        return;
_L3:
        i3 = l;
          goto _L7
_L2:
        i3 = Math.min(l, j2 + getPaddingLeft() + getPaddingRight());
          goto _L7
_L6:
        j3 = i1;
          goto _L8
_L5:
        j3 = Math.min(i1, l2 + getPaddingTop() + getPaddingBottom());
          goto _L8
    }

    public boolean performClick()
    {
        boolean flag = false;
        boolean flag1 = super.performClick();
        if(!flag1)
            playSoundEffect(0);
        if(showDialog() || flag1)
            flag = true;
        return flag;
    }

    public boolean performLongClick()
    {
        boolean flag = true;
        if(!super.performLongClick())
            if(!mCheatSheetEnabled)
            {
                flag = false;
            } else
            {
                CharSequence charsequence = getContentDescription();
                if(TextUtils.isEmpty(charsequence))
                {
                    flag = false;
                } else
                {
                    int ai[] = new int[2];
                    Rect rect = new Rect();
                    getLocationOnScreen(ai);
                    getWindowVisibleDisplayFrame(rect);
                    Context context = getContext();
                    int i = getWidth();
                    int j = getHeight();
                    int k = ai[flag] + j / 2;
                    int l = context.getResources().getDisplayMetrics().widthPixels;
                    Toast toast = Toast.makeText(context, charsequence, 0);
                    if(k < rect.height())
                        toast.setGravity(0x800035, l - ai[0] - i / 2, j);
                    else
                        toast.setGravity(81, 0, j);
                    toast.show();
                    performHapticFeedback(0);
                }
            }
        return flag;
    }

    void setCheatSheetEnabled(boolean flag)
    {
        mCheatSheetEnabled = flag;
    }

    public void setDialogFactory(MediaRouteDialogFactory mediaroutedialogfactory)
    {
        if(mediaroutedialogfactory == null)
        {
            throw new IllegalArgumentException("factory must not be null");
        } else
        {
            mDialogFactory = mediaroutedialogfactory;
            return;
        }
    }

    public void setRouteSelector(MediaRouteSelector mediarouteselector)
    {
        if(mediarouteselector == null)
            throw new IllegalArgumentException("selector must not be null");
        if(!mSelector.equals(mediarouteselector))
        {
            if(mAttachedToWindow)
            {
                if(!mSelector.isEmpty())
                    mRouter.removeCallback(mCallback);
                if(!mediarouteselector.isEmpty())
                    mRouter.addCallback(mediarouteselector, mCallback);
            }
            mSelector = mediarouteselector;
            refreshRoute();
        }
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(mRemoteIndicator != null)
        {
            Drawable drawable = mRemoteIndicator;
            boolean flag;
            if(getVisibility() == 0)
                flag = true;
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
    }

    public boolean showDialog()
    {
        FragmentManager fragmentmanager;
        boolean flag = false;
        if(mAttachedToWindow)
        {
            fragmentmanager = getFragmentManager();
            if(fragmentmanager == null)
                throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
            android.support.v7.media.MediaRouter.RouteInfo routeinfo = mRouter.getSelectedRoute();
            MediaRouteChooserDialogFragment mediaroutechooserdialogfragment;
            if(routeinfo.isDefault() || !routeinfo.matchesSelector(mSelector))
            {
label0:
                {
                    if(fragmentmanager.findFragmentByTag("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") == null)
                        break label0;
                    Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
                }
            } else
            {
label1:
                {
                    if(fragmentmanager.findFragmentByTag("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") == null)
                        break label1;
                    Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
                }
            }
        }
_L2:
        return flag;
        mediaroutechooserdialogfragment = mDialogFactory.onCreateChooserDialogFragment();
        mediaroutechooserdialogfragment.setRouteSelector(mSelector);
        mediaroutechooserdialogfragment.show(fragmentmanager, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
        do
        {
            flag = true;
            if(true)
                break MISSING_BLOCK_LABEL_9;
            mDialogFactory.onCreateControllerDialogFragment().show(fragmentmanager, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment");
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if(super.verifyDrawable(drawable) || drawable == mRemoteIndicator)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final int CHECKABLE_STATE_SET[];
    private static final int CHECKED_STATE_SET[];
    private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
    private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
    private static final String TAG = "MediaRouteButton";
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private boolean mCheatSheetEnabled;
    private MediaRouteDialogFactory mDialogFactory;
    private boolean mIsConnecting;
    private int mMinHeight;
    private int mMinWidth;
    private boolean mRemoteActive;
    private Drawable mRemoteIndicator;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x10100a0;
        CHECKED_STATE_SET = ai;
        int ai1[] = new int[1];
        ai1[0] = 0x101009f;
        CHECKABLE_STATE_SET = ai1;
    }

}
