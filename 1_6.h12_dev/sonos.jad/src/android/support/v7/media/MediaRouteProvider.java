// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.*;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package android.support.v7.media:
//            MediaRouter, MediaRouteDiscoveryRequest, MediaRouteProviderDescriptor

public abstract class MediaRouteProvider
{
    private final class ProviderHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 39;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            deliverDescriptorChanged();
            continue; /* Loop/switch isn't completed */
_L3:
            deliverDiscoveryRequestChanged();
            if(true) goto _L1; else goto _L4
_L4:
        }

        final MediaRouteProvider this$0;

        private ProviderHandler()
        {
            this$0 = MediaRouteProvider.this;
            super();
        }

    }

    public static abstract class Callback
    {

        public void onDescriptorChanged(MediaRouteProvider mediarouteprovider, MediaRouteProviderDescriptor mediarouteproviderdescriptor)
        {
        }

        public Callback()
        {
        }
    }

    public static abstract class RouteController
    {

        public boolean onControlRequest(Intent intent, MediaRouter.ControlRequestCallback controlrequestcallback)
        {
            return false;
        }

        public void onRelease()
        {
        }

        public void onSelect()
        {
        }

        public void onSetVolume(int i)
        {
        }

        public void onUnselect()
        {
        }

        public void onUpdateVolume(int i)
        {
        }

        public RouteController()
        {
        }
    }

    public static final class ProviderMetadata
    {

        public ComponentName getComponentName()
        {
            return mComponentName;
        }

        public String getPackageName()
        {
            return mComponentName.getPackageName();
        }

        public String toString()
        {
            return (new StringBuilder()).append("ProviderMetadata{ componentName=").append(mComponentName.flattenToShortString()).append(" }").toString();
        }

        private final ComponentName mComponentName;

        ProviderMetadata(ComponentName componentname)
        {
            if(componentname == null)
            {
                throw new IllegalArgumentException("componentName must not be null");
            } else
            {
                mComponentName = componentname;
                return;
            }
        }
    }


    public MediaRouteProvider(Context context)
    {
        this(context, null);
    }

    MediaRouteProvider(Context context, ProviderMetadata providermetadata)
    {
        mHandler = new ProviderHandler();
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        mContext = context;
        if(providermetadata == null)
            mMetadata = new ProviderMetadata(new ComponentName(context, getClass()));
        else
            mMetadata = providermetadata;
    }

    private void deliverDescriptorChanged()
    {
        mPendingDescriptorChange = false;
        if(mCallback != null)
            mCallback.onDescriptorChanged(this, mDescriptor);
    }

    private void deliverDiscoveryRequestChanged()
    {
        mPendingDiscoveryRequestChange = false;
        onDiscoveryRequestChanged(mDiscoveryRequest);
    }

    public final Context getContext()
    {
        return mContext;
    }

    public final MediaRouteProviderDescriptor getDescriptor()
    {
        return mDescriptor;
    }

    public final MediaRouteDiscoveryRequest getDiscoveryRequest()
    {
        return mDiscoveryRequest;
    }

    public final Handler getHandler()
    {
        return mHandler;
    }

    public final ProviderMetadata getMetadata()
    {
        return mMetadata;
    }

    public RouteController onCreateRouteController(String s)
    {
        return null;
    }

    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest mediaroutediscoveryrequest)
    {
    }

    public final void setCallback(Callback callback)
    {
        MediaRouter.checkCallingThread();
        mCallback = callback;
    }

    public final void setDescriptor(MediaRouteProviderDescriptor mediarouteproviderdescriptor)
    {
        MediaRouter.checkCallingThread();
        if(mDescriptor != mediarouteproviderdescriptor)
        {
            mDescriptor = mediarouteproviderdescriptor;
            if(!mPendingDescriptorChange)
            {
                mPendingDescriptorChange = true;
                mHandler.sendEmptyMessage(1);
            }
        }
    }

    public final void setDiscoveryRequest(MediaRouteDiscoveryRequest mediaroutediscoveryrequest)
    {
        MediaRouter.checkCallingThread();
        if(mDiscoveryRequest != mediaroutediscoveryrequest && (mDiscoveryRequest == null || !mDiscoveryRequest.equals(mediaroutediscoveryrequest))) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mDiscoveryRequest = mediaroutediscoveryrequest;
        if(!mPendingDiscoveryRequestChange)
        {
            mPendingDiscoveryRequestChange = true;
            mHandler.sendEmptyMessage(2);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static final int MSG_DELIVER_DESCRIPTOR_CHANGED = 1;
    private static final int MSG_DELIVER_DISCOVERY_REQUEST_CHANGED = 2;
    private Callback mCallback;
    private final Context mContext;
    private MediaRouteProviderDescriptor mDescriptor;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private final ProviderHandler mHandler;
    private final ProviderMetadata mMetadata;
    private boolean mPendingDescriptorChange;
    private boolean mPendingDiscoveryRequestChange;


}
