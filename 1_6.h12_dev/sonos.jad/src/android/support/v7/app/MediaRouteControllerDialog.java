// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.view.*;
import android.widget.*;

// Referenced classes of package android.support.v7.app:
//            MediaRouterThemeHelper

public class MediaRouteControllerDialog extends Dialog
{
    private final class MediaRouterCallback extends android.support.v7.media.MediaRouter.Callback
    {

        public void onRouteChanged(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            update();
        }

        public void onRouteUnselected(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            update();
        }

        public void onRouteVolumeChanged(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            if(routeinfo == mRoute)
                updateVolume();
        }

        final MediaRouteControllerDialog this$0;

        private MediaRouterCallback()
        {
            this$0 = MediaRouteControllerDialog.this;
            super();
        }

    }


    public MediaRouteControllerDialog(Context context)
    {
        this(context, 0);
    }

    public MediaRouteControllerDialog(Context context, int i)
    {
        super(MediaRouterThemeHelper.createThemedContext(context, true), i);
        mVolumeControlEnabled = true;
        mRouter = MediaRouter.getInstance(getContext());
        mCallback = new MediaRouterCallback();
        mRoute = mRouter.getSelectedRoute();
    }

    private Drawable getIconDrawable()
    {
        Drawable drawable;
        if(mRoute.isConnecting())
        {
            if(mMediaRouteConnectingDrawable == null)
                mMediaRouteConnectingDrawable = MediaRouterThemeHelper.getThemeDrawable(getContext(), android.support.v7.mediarouter.R.attr.mediaRouteConnectingDrawable);
            drawable = mMediaRouteConnectingDrawable;
        } else
        {
            if(mMediaRouteOnDrawable == null)
                mMediaRouteOnDrawable = MediaRouterThemeHelper.getThemeDrawable(getContext(), android.support.v7.mediarouter.R.attr.mediaRouteOnDrawable);
            drawable = mMediaRouteOnDrawable;
        }
        return drawable;
    }

    private boolean isVolumeControlAvailable()
    {
        boolean flag = true;
        if(!mVolumeControlEnabled || mRoute.getVolumeHandling() != flag)
            flag = false;
        return flag;
    }

    private boolean update()
    {
        boolean flag = true;
        if(mRoute.isSelected() && !mRoute.isDefault()) goto _L2; else goto _L1
_L1:
        dismiss();
        flag = false;
_L4:
        return flag;
_L2:
        setTitle(mRoute.getName());
        updateVolume();
        Drawable drawable = getIconDrawable();
        if(drawable != mCurrentIconDrawable)
        {
            mCurrentIconDrawable = drawable;
            drawable.setVisible(false, flag);
            getWindow().setFeatureDrawable(3, drawable);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateVolume()
    {
        if(!mVolumeSliderTouched)
            if(isVolumeControlAvailable())
            {
                mVolumeLayout.setVisibility(0);
                mVolumeSlider.setMax(mRoute.getVolumeMax());
                mVolumeSlider.setProgress(mRoute.getVolume());
            } else
            {
                mVolumeLayout.setVisibility(8);
            }
    }

    public View getMediaControlView()
    {
        return mControlView;
    }

    public android.support.v7.media.MediaRouter.RouteInfo getRoute()
    {
        return mRoute;
    }

    public boolean isVolumeControlEnabled()
    {
        return mVolumeControlEnabled;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mRouter.addCallback(MediaRouteSelector.EMPTY, mCallback, 2);
        update();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().requestFeature(3);
        setContentView(android.support.v7.mediarouter.R.layout.mr_media_route_controller_dialog);
        mVolumeLayout = (LinearLayout)findViewById(android.support.v7.mediarouter.R.id.media_route_volume_layout);
        mVolumeSlider = (SeekBar)findViewById(android.support.v7.mediarouter.R.id.media_route_volume_slider);
        mVolumeSlider.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                if(flag)
                    mRoute.requestSetVolume(i);
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
                if(mVolumeSliderTouched)
                    mVolumeSlider.removeCallbacks(mStopTrackingTouch);
                else
                    mVolumeSliderTouched = true;
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
                mVolumeSlider.postDelayed(mStopTrackingTouch, 250L);
            }

            private final Runnable mStopTrackingTouch = new Runnable() {

                public void run()
                {
                    if(mVolumeSliderTouched)
                    {
                        mVolumeSliderTouched = false;
                        updateVolume();
                    }
                }

                final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
            }
;
            final MediaRouteControllerDialog this$0;

            
            {
                this$0 = MediaRouteControllerDialog.this;
                super();
            }
        }
);
        mDisconnectButton = (Button)findViewById(android.support.v7.mediarouter.R.id.media_route_disconnect_button);
        mDisconnectButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(mRoute.isSelected())
                    mRouter.getDefaultRoute().select();
                dismiss();
            }

            final MediaRouteControllerDialog this$0;

            
            {
                this$0 = MediaRouteControllerDialog.this;
                super();
            }
        }
);
        mCreated = true;
        if(update())
        {
            mControlView = onCreateMediaControlView(bundle);
            FrameLayout framelayout = (FrameLayout)findViewById(android.support.v7.mediarouter.R.id.media_route_control_frame);
            if(mControlView != null)
            {
                framelayout.addView(mControlView);
                framelayout.setVisibility(0);
            } else
            {
                framelayout.setVisibility(8);
            }
        }
    }

    public View onCreateMediaControlView(Bundle bundle)
    {
        return null;
    }

    public void onDetachedFromWindow()
    {
        mRouter.removeCallback(mCallback);
        super.onDetachedFromWindow();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = true;
        if(i == 25 || i == 24)
        {
            android.support.v7.media.MediaRouter.RouteInfo routeinfo = mRoute;
            byte byte0;
            if(i == 25)
                byte0 = -1;
            else
                byte0 = flag;
            routeinfo.requestUpdateVolume(byte0);
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 25 || i == 24)
            flag = true;
        else
            flag = super.onKeyUp(i, keyevent);
        return flag;
    }

    public void setVolumeControlEnabled(boolean flag)
    {
        if(mVolumeControlEnabled != flag)
        {
            mVolumeControlEnabled = flag;
            if(mCreated)
                updateVolume();
        }
    }

    private static final String TAG = "MediaRouteControllerDialog";
    private static final int VOLUME_UPDATE_DELAY_MILLIS = 250;
    private final MediaRouterCallback mCallback;
    private View mControlView;
    private boolean mCreated;
    private Drawable mCurrentIconDrawable;
    private Button mDisconnectButton;
    private Drawable mMediaRouteConnectingDrawable;
    private Drawable mMediaRouteOnDrawable;
    private final android.support.v7.media.MediaRouter.RouteInfo mRoute;
    private final MediaRouter mRouter;
    private boolean mVolumeControlEnabled;
    private LinearLayout mVolumeLayout;
    private SeekBar mVolumeSlider;
    private boolean mVolumeSliderTouched;



/*
    static boolean access$102(MediaRouteControllerDialog mediaroutecontrollerdialog, boolean flag)
    {
        mediaroutecontrollerdialog.mVolumeSliderTouched = flag;
        return flag;
    }

*/





}
