// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.app:
//            MediaRouteDialogFactory, MediaRouteButton

public class MediaRouteActionProvider extends ActionProvider
{
    private static final class MediaRouterCallback extends android.support.v7.media.MediaRouter.Callback
    {

        private void refreshRoute(MediaRouter mediarouter)
        {
            MediaRouteActionProvider mediarouteactionprovider = (MediaRouteActionProvider)mProviderWeak.get();
            if(mediarouteactionprovider != null)
                mediarouteactionprovider.refreshRoute();
            else
                mediarouter.removeCallback(this);
        }

        public void onProviderAdded(MediaRouter mediarouter, android.support.v7.media.MediaRouter.ProviderInfo providerinfo)
        {
            refreshRoute(mediarouter);
        }

        public void onProviderChanged(MediaRouter mediarouter, android.support.v7.media.MediaRouter.ProviderInfo providerinfo)
        {
            refreshRoute(mediarouter);
        }

        public void onProviderRemoved(MediaRouter mediarouter, android.support.v7.media.MediaRouter.ProviderInfo providerinfo)
        {
            refreshRoute(mediarouter);
        }

        public void onRouteAdded(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute(mediarouter);
        }

        public void onRouteChanged(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute(mediarouter);
        }

        public void onRouteRemoved(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute(mediarouter);
        }

        private final WeakReference mProviderWeak;

        public MediaRouterCallback(MediaRouteActionProvider mediarouteactionprovider)
        {
            mProviderWeak = new WeakReference(mediarouteactionprovider);
        }
    }


    public MediaRouteActionProvider(Context context)
    {
        super(context);
        mSelector = MediaRouteSelector.EMPTY;
        mDialogFactory = MediaRouteDialogFactory.getDefault();
        mRouter = MediaRouter.getInstance(context);
    }

    private void refreshRoute()
    {
        refreshVisibility();
    }

    public MediaRouteDialogFactory getDialogFactory()
    {
        return mDialogFactory;
    }

    public MediaRouteButton getMediaRouteButton()
    {
        return mButton;
    }

    public MediaRouteSelector getRouteSelector()
    {
        return mSelector;
    }

    public boolean isVisible()
    {
        return mRouter.isRouteAvailable(mSelector, 1);
    }

    public View onCreateActionView()
    {
        if(mButton != null)
            Log.e("MediaRouteActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
        mButton = onCreateMediaRouteButton();
        mButton.setCheatSheetEnabled(true);
        mButton.setRouteSelector(mSelector);
        mButton.setDialogFactory(mDialogFactory);
        mButton.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -1));
        return mButton;
    }

    public MediaRouteButton onCreateMediaRouteButton()
    {
        return new MediaRouteButton(getContext());
    }

    public boolean onPerformDefaultAction()
    {
        boolean flag;
        if(mButton != null)
            flag = mButton.showDialog();
        else
            flag = false;
        return flag;
    }

    public boolean overridesItemVisibility()
    {
        return true;
    }

    public void setDialogFactory(MediaRouteDialogFactory mediaroutedialogfactory)
    {
        if(mediaroutedialogfactory == null)
            throw new IllegalArgumentException("factory must not be null");
        if(mDialogFactory != mediaroutedialogfactory)
        {
            mDialogFactory = mediaroutedialogfactory;
            if(mButton != null)
                mButton.setDialogFactory(mediaroutedialogfactory);
        }
    }

    public void setRouteSelector(MediaRouteSelector mediarouteselector)
    {
        if(mediarouteselector == null)
            throw new IllegalArgumentException("selector must not be null");
        if(!mSelector.equals(mediarouteselector))
        {
            if(!mSelector.isEmpty())
                mRouter.removeCallback(mCallback);
            if(!mediarouteselector.isEmpty())
                mRouter.addCallback(mediarouteselector, mCallback);
            mSelector = mediarouteselector;
            refreshRoute();
            if(mButton != null)
                mButton.setRouteSelector(mediarouteselector);
        }
    }

    private static final String TAG = "MediaRouteActionProvider";
    private MediaRouteButton mButton;
    private final MediaRouterCallback mCallback = new MediaRouterCallback(this);
    private MediaRouteDialogFactory mDialogFactory;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;

}
