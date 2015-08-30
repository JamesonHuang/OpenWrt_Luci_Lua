// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;

public class MediaRouteDiscoveryFragment extends Fragment
{

    public MediaRouteDiscoveryFragment()
    {
    }

    private void ensureRouteSelector()
    {
        if(mSelector == null)
        {
            Bundle bundle = getArguments();
            if(bundle != null)
                mSelector = MediaRouteSelector.fromBundle(bundle.getBundle("selector"));
            if(mSelector == null)
                mSelector = MediaRouteSelector.EMPTY;
        }
    }

    private void ensureRouter()
    {
        if(mRouter == null)
            mRouter = MediaRouter.getInstance(getActivity());
    }

    public MediaRouter getMediaRouter()
    {
        ensureRouter();
        return mRouter;
    }

    public MediaRouteSelector getRouteSelector()
    {
        ensureRouteSelector();
        return mSelector;
    }

    public android.support.v7.media.MediaRouter.Callback onCreateCallback()
    {
        return new android.support.v7.media.MediaRouter.Callback() {

            final MediaRouteDiscoveryFragment this$0;

            
            {
                this$0 = MediaRouteDiscoveryFragment.this;
                super();
            }
        }
;
    }

    public int onPrepareCallbackFlags()
    {
        return 4;
    }

    public void onStart()
    {
        super.onStart();
        ensureRouteSelector();
        ensureRouter();
        mCallback = onCreateCallback();
        if(mCallback != null)
            mRouter.addCallback(mSelector, mCallback, onPrepareCallbackFlags());
    }

    public void onStop()
    {
        if(mCallback != null)
        {
            mRouter.removeCallback(mCallback);
            mCallback = null;
        }
        super.onStop();
    }

    public void setRouteSelector(MediaRouteSelector mediarouteselector)
    {
        if(mediarouteselector == null)
            throw new IllegalArgumentException("selector must not be null");
        ensureRouteSelector();
        if(!mSelector.equals(mediarouteselector))
        {
            mSelector = mediarouteselector;
            Bundle bundle = getArguments();
            if(bundle == null)
                bundle = new Bundle();
            bundle.putBundle("selector", mediarouteselector.asBundle());
            setArguments(bundle);
            if(mCallback != null)
            {
                mRouter.removeCallback(mCallback);
                mRouter.addCallback(mSelector, mCallback, onPrepareCallbackFlags());
            }
        }
    }

    private final String ARGUMENT_SELECTOR = "selector";
    private android.support.v7.media.MediaRouter.Callback mCallback;
    private MediaRouter mRouter;
    private MediaRouteSelector mSelector;
}
