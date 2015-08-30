// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.Context;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.media:
//            MediaRouterJellybean

abstract class RemoteControlClientCompat
{
    static class JellybeanImpl extends RemoteControlClientCompat
    {
        private static final class VolumeCallbackWrapper
            implements MediaRouterJellybean.VolumeCallback
        {

            public void onVolumeSetRequest(Object obj, int i)
            {
                JellybeanImpl jellybeanimpl = (JellybeanImpl)mImplWeak.get();
                if(jellybeanimpl != null && jellybeanimpl.mVolumeCallback != null)
                    jellybeanimpl.mVolumeCallback.onVolumeSetRequest(i);
            }

            public void onVolumeUpdateRequest(Object obj, int i)
            {
                JellybeanImpl jellybeanimpl = (JellybeanImpl)mImplWeak.get();
                if(jellybeanimpl != null && jellybeanimpl.mVolumeCallback != null)
                    jellybeanimpl.mVolumeCallback.onVolumeUpdateRequest(i);
            }

            private final WeakReference mImplWeak;

            public VolumeCallbackWrapper(JellybeanImpl jellybeanimpl)
            {
                mImplWeak = new WeakReference(jellybeanimpl);
            }
        }


        public void setPlaybackInfo(PlaybackInfo playbackinfo)
        {
            MediaRouterJellybean.UserRouteInfo.setVolume(mUserRouteObj, playbackinfo.volume);
            MediaRouterJellybean.UserRouteInfo.setVolumeMax(mUserRouteObj, playbackinfo.volumeMax);
            MediaRouterJellybean.UserRouteInfo.setVolumeHandling(mUserRouteObj, playbackinfo.volumeHandling);
            MediaRouterJellybean.UserRouteInfo.setPlaybackStream(mUserRouteObj, playbackinfo.playbackStream);
            MediaRouterJellybean.UserRouteInfo.setPlaybackType(mUserRouteObj, playbackinfo.playbackType);
            if(!mRegistered)
            {
                mRegistered = true;
                MediaRouterJellybean.UserRouteInfo.setVolumeCallback(mUserRouteObj, MediaRouterJellybean.createVolumeCallback(new VolumeCallbackWrapper(this)));
                MediaRouterJellybean.UserRouteInfo.setRemoteControlClient(mUserRouteObj, mRcc);
            }
        }

        private boolean mRegistered;
        private final Object mRouterObj;
        private final Object mUserRouteCategoryObj;
        private final Object mUserRouteObj;

        public JellybeanImpl(Context context, Object obj)
        {
            super(context, obj);
            mRouterObj = MediaRouterJellybean.getMediaRouter(context);
            mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(mRouterObj, "", false);
            mUserRouteObj = MediaRouterJellybean.createUserRoute(mRouterObj, mUserRouteCategoryObj);
        }
    }

    static class LegacyImpl extends RemoteControlClientCompat
    {

        public LegacyImpl(Context context, Object obj)
        {
            super(context, obj);
        }
    }

    public static interface VolumeCallback
    {

        public abstract void onVolumeSetRequest(int i);

        public abstract void onVolumeUpdateRequest(int i);
    }

    public static final class PlaybackInfo
    {

        public int playbackStream;
        public int playbackType;
        public int volume;
        public int volumeHandling;
        public int volumeMax;

        public PlaybackInfo()
        {
            volumeHandling = 0;
            playbackStream = 3;
            playbackType = 1;
        }
    }


    protected RemoteControlClientCompat(Context context, Object obj)
    {
        mContext = context;
        mRcc = obj;
    }

    public static RemoteControlClientCompat obtain(Context context, Object obj)
    {
        Object obj1;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            obj1 = new JellybeanImpl(context, obj);
        else
            obj1 = new LegacyImpl(context, obj);
        return ((RemoteControlClientCompat) (obj1));
    }

    public Object getRemoteControlClient()
    {
        return mRcc;
    }

    public void setPlaybackInfo(PlaybackInfo playbackinfo)
    {
    }

    public void setVolumeCallback(VolumeCallback volumecallback)
    {
        mVolumeCallback = volumecallback;
    }

    protected final Context mContext;
    protected final Object mRcc;
    protected VolumeCallback mVolumeCallback;
}
