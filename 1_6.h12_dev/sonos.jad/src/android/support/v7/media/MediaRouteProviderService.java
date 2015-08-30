// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.media:
//            MediaRouteProvider, MediaRouteProviderDescriptor, MediaRouteDiscoveryRequest, MediaRouteSelector, 
//            MediaRouteProviderProtocol

public abstract class MediaRouteProviderService extends Service
{
    private static final class ReceiveHandler extends Handler
    {

        private boolean processMessage(int i, Messenger messenger, int j, int k, Object obj, Bundle bundle)
        {
            boolean flag;
            MediaRouteProviderService mediarouteproviderservice;
            flag = false;
            mediarouteproviderservice = (MediaRouteProviderService)mServiceRef.get();
            if(mediarouteproviderservice == null) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR tableswitch 1 10: default 76
        //                       1 79
        //                       2 93
        //                       3 105
        //                       4 135
        //                       5 149
        //                       6 163
        //                       7 177
        //                       8 209
        //                       9 240
        //                       10 267;
               goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L2:
            return flag;
_L3:
            flag = mediarouteproviderservice.onRegisterClient(messenger, j, k);
            continue; /* Loop/switch isn't completed */
_L4:
            flag = mediarouteproviderservice.onUnregisterClient(messenger, j);
            continue; /* Loop/switch isn't completed */
_L5:
            String s = bundle.getString("routeId");
            if(s != null)
                flag = mediarouteproviderservice.onCreateRouteController(messenger, j, k, s);
            continue; /* Loop/switch isn't completed */
_L6:
            flag = mediarouteproviderservice.onReleaseRouteController(messenger, j, k);
            continue; /* Loop/switch isn't completed */
_L7:
            flag = mediarouteproviderservice.onSelectRoute(messenger, j, k);
            continue; /* Loop/switch isn't completed */
_L8:
            flag = mediarouteproviderservice.onUnselectRoute(messenger, j, k);
            continue; /* Loop/switch isn't completed */
_L9:
            int i1 = bundle.getInt("volume", -1);
            if(i1 >= 0)
                flag = mediarouteproviderservice.onSetRouteVolume(messenger, j, k, i1);
            continue; /* Loop/switch isn't completed */
_L10:
            int l = bundle.getInt("volume", 0);
            if(l != 0)
                flag = mediarouteproviderservice.onUpdateRouteVolume(messenger, j, k, l);
            continue; /* Loop/switch isn't completed */
_L11:
            if(obj instanceof Intent)
                flag = mediarouteproviderservice.onRouteControlRequest(messenger, j, k, (Intent)obj);
            continue; /* Loop/switch isn't completed */
_L12:
            if(obj == null || (obj instanceof Bundle))
            {
                MediaRouteDiscoveryRequest mediaroutediscoveryrequest = MediaRouteDiscoveryRequest.fromBundle((Bundle)obj);
                if(mediaroutediscoveryrequest == null || !mediaroutediscoveryrequest.isValid())
                    mediaroutediscoveryrequest = null;
                flag = mediarouteproviderservice.onSetDiscoveryRequest(messenger, j, mediaroutediscoveryrequest);
            }
            if(true) goto _L2; else goto _L13
_L13:
        }

        public void handleMessage(Message message)
        {
            Messenger messenger = message.replyTo;
            if(!MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) goto _L2; else goto _L1
_L1:
            int i = message.what;
            int j = message.arg1;
            int k = message.arg2;
            Object obj = message.obj;
            Bundle bundle = message.peekData();
            if(!processMessage(i, messenger, j, k, obj, bundle))
            {
                if(MediaRouteProviderService.DEBUG)
                    Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(MediaRouteProviderService.getClientId(messenger)).append(": Message failed, what=").append(i).append(", requestId=").append(j).append(", arg=").append(k).append(", obj=").append(obj).append(", data=").append(bundle).toString());
                MediaRouteProviderService.sendGenericFailure(messenger, j);
            }
_L4:
            return;
_L2:
            if(MediaRouteProviderService.DEBUG)
                Log.d("MediaRouteProviderSrv", "Ignoring message without valid reply messenger.");
            if(true) goto _L4; else goto _L3
_L3:
        }

        private final WeakReference mServiceRef;

        public ReceiveHandler(MediaRouteProviderService mediarouteproviderservice)
        {
            mServiceRef = new WeakReference(mediarouteproviderservice);
        }
    }

    private final class ClientRecord
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            mPrivateHandler.obtainMessage(1, mMessenger).sendToTarget();
        }

        public boolean createRouteController(String s, int i)
        {
            if(mControllers.indexOfKey(i) >= 0) goto _L2; else goto _L1
_L1:
            MediaRouteProvider.RouteController routecontroller = mProvider.onCreateRouteController(s);
            if(routecontroller == null) goto _L2; else goto _L3
_L3:
            boolean flag;
            mControllers.put(i, routecontroller);
            flag = true;
_L5:
            return flag;
_L2:
            flag = false;
            if(true) goto _L5; else goto _L4
_L4:
        }

        public void dispose()
        {
            int i = mControllers.size();
            for(int j = 0; j < i; j++)
                ((MediaRouteProvider.RouteController)mControllers.valueAt(j)).onRelease();

            mControllers.clear();
            mMessenger.getBinder().unlinkToDeath(this, 0);
            setDiscoveryRequest(null);
        }

        public MediaRouteProvider.RouteController getRouteController(int i)
        {
            return (MediaRouteProvider.RouteController)mControllers.get(i);
        }

        public boolean hasMessenger(Messenger messenger)
        {
            boolean flag;
            if(mMessenger.getBinder() == messenger.getBinder())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean register()
        {
            boolean flag = false;
            mMessenger.getBinder().linkToDeath(this, 0);
            flag = true;
_L2:
            return flag;
            RemoteException remoteexception;
            remoteexception;
            binderDied();
            if(true) goto _L2; else goto _L1
_L1:
        }

        public boolean releaseRouteController(int i)
        {
            MediaRouteProvider.RouteController routecontroller = (MediaRouteProvider.RouteController)mControllers.get(i);
            boolean flag;
            if(routecontroller != null)
            {
                mControllers.remove(i);
                routecontroller.onRelease();
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest mediaroutediscoveryrequest)
        {
            boolean flag;
            if(mDiscoveryRequest != mediaroutediscoveryrequest && (mDiscoveryRequest == null || !mDiscoveryRequest.equals(mediaroutediscoveryrequest)))
            {
                mDiscoveryRequest = mediaroutediscoveryrequest;
                flag = updateCompositeDiscoveryRequest();
            } else
            {
                flag = false;
            }
            return flag;
        }

        public String toString()
        {
            return MediaRouteProviderService.getClientId(mMessenger);
        }

        private final SparseArray mControllers = new SparseArray();
        public MediaRouteDiscoveryRequest mDiscoveryRequest;
        public final Messenger mMessenger;
        public final int mVersion;
        final MediaRouteProviderService this$0;

        public ClientRecord(Messenger messenger, int i)
        {
            this$0 = MediaRouteProviderService.this;
            super();
            mMessenger = messenger;
            mVersion = i;
        }
    }

    private final class ProviderCallback extends MediaRouteProvider.Callback
    {

        public void onDescriptorChanged(MediaRouteProvider mediarouteprovider, MediaRouteProviderDescriptor mediarouteproviderdescriptor)
        {
            sendDescriptorChanged(mediarouteproviderdescriptor);
        }

        final MediaRouteProviderService this$0;

        private ProviderCallback()
        {
            this$0 = MediaRouteProviderService.this;
            super();
        }

    }

    private final class PrivateHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            onBinderDied((Messenger)message.obj);
            if(true) goto _L1; else goto _L3
_L3:
        }

        final MediaRouteProviderService this$0;

        private PrivateHandler()
        {
            this$0 = MediaRouteProviderService.this;
            super();
        }

    }


    public MediaRouteProviderService()
    {
        mReceiveMessenger = new Messenger(mReceiveHandler);
    }

    private int findClient(Messenger messenger)
    {
        int i;
        int j;
        i = mClients.size();
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_41;
        if(!((ClientRecord)mClients.get(j)).hasMessenger(messenger)) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        j++;
          goto _L3
        j = -1;
          goto _L1
    }

    private ClientRecord getClient(Messenger messenger)
    {
        int i = findClient(messenger);
        ClientRecord clientrecord;
        if(i >= 0)
            clientrecord = (ClientRecord)mClients.get(i);
        else
            clientrecord = null;
        return clientrecord;
    }

    private static String getClientId(Messenger messenger)
    {
        return (new StringBuilder()).append("Client connection ").append(messenger.getBinder().toString()).toString();
    }

    private void onBinderDied(Messenger messenger)
    {
        int i = findClient(messenger);
        if(i >= 0)
        {
            ClientRecord clientrecord = (ClientRecord)mClients.remove(i);
            if(DEBUG)
                Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Binder died").toString());
            clientrecord.dispose();
        }
    }

    private boolean onCreateRouteController(Messenger messenger, int i, int j, String s)
    {
        ClientRecord clientrecord = getClient(messenger);
        boolean flag;
        if(clientrecord != null && clientrecord.createRouteController(s, j))
        {
            if(DEBUG)
                Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Route controller created").append(", controllerId=").append(j).append(", routeId=").append(s).toString());
            sendGenericSuccess(messenger, i);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean onRegisterClient(Messenger messenger, int i, int j)
    {
        boolean flag = true;
        if(j < flag || findClient(messenger) >= 0) goto _L2; else goto _L1
_L1:
        ClientRecord clientrecord = new ClientRecord(messenger, j);
        if(!clientrecord.register()) goto _L2; else goto _L3
_L3:
        mClients.add(clientrecord);
        if(DEBUG)
            Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Registered, version=").append(j).toString());
        if(i != 0)
        {
            MediaRouteProviderDescriptor mediarouteproviderdescriptor = mProvider.getDescriptor();
            Bundle bundle;
            if(mediarouteproviderdescriptor != null)
                bundle = mediarouteproviderdescriptor.asBundle();
            else
                bundle = null;
            sendReply(messenger, 2, i, flag, bundle, null);
        }
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private boolean onReleaseRouteController(Messenger messenger, int i, int j)
    {
        ClientRecord clientrecord = getClient(messenger);
        boolean flag;
        if(clientrecord != null && clientrecord.releaseRouteController(j))
        {
            if(DEBUG)
                Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Route controller released").append(", controllerId=").append(j).toString());
            sendGenericSuccess(messenger, i);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean onRouteControlRequest(final Messenger messenger, final int requestId, final int controllerId, final Intent intent)
    {
        final ClientRecord client = getClient(messenger);
        if(client == null) goto _L2; else goto _L1
_L1:
        MediaRouteProvider.RouteController routecontroller = client.getRouteController(controllerId);
        if(routecontroller == null) goto _L2; else goto _L3
_L3:
        MediaRouter.ControlRequestCallback controlrequestcallback;
        controlrequestcallback = null;
        if(requestId != 0)
            controlrequestcallback = new MediaRouter.ControlRequestCallback() {

                public void onError(String s, Bundle bundle)
                {
                    if(MediaRouteProviderService.DEBUG)
                        Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(client).append(": Route control request failed").append(", controllerId=").append(controllerId).append(", intent=").append(intent).append(", error=").append(s).append(", data=").append(bundle).toString());
                    if(findClient(messenger) >= 0)
                        if(s != null)
                        {
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("error", s);
                            MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, bundle, bundle1);
                        } else
                        {
                            MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, bundle, null);
                        }
                }

                public void onResult(Bundle bundle)
                {
                    if(MediaRouteProviderService.DEBUG)
                        Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(client).append(": Route control request succeeded").append(", controllerId=").append(controllerId).append(", intent=").append(intent).append(", data=").append(bundle).toString());
                    if(findClient(messenger) >= 0)
                        MediaRouteProviderService.sendReply(messenger, 3, requestId, 0, bundle, null);
                }

                final MediaRouteProviderService this$0;
                final ClientRecord val$client;
                final int val$controllerId;
                final Intent val$intent;
                final Messenger val$messenger;
                final int val$requestId;

            
            {
                this$0 = MediaRouteProviderService.this;
                client = clientrecord;
                controllerId = i;
                intent = intent1;
                messenger = messenger1;
                requestId = j;
                super();
            }
            }
;
        if(!routecontroller.onControlRequest(intent, controlrequestcallback)) goto _L2; else goto _L4
_L4:
        boolean flag;
        if(DEBUG)
            Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(client).append(": Route control request delivered").append(", controllerId=").append(controllerId).append(", intent=").append(intent).toString());
        flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean onSelectRoute(Messenger messenger, int i, int j)
    {
        ClientRecord clientrecord = getClient(messenger);
        if(clientrecord == null) goto _L2; else goto _L1
_L1:
        MediaRouteProvider.RouteController routecontroller = clientrecord.getRouteController(j);
        if(routecontroller == null) goto _L2; else goto _L3
_L3:
        boolean flag;
        routecontroller.onSelect();
        if(DEBUG)
            Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Route selected").append(", controllerId=").append(j).toString());
        sendGenericSuccess(messenger, i);
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private boolean onSetDiscoveryRequest(Messenger messenger, int i, MediaRouteDiscoveryRequest mediaroutediscoveryrequest)
    {
        ClientRecord clientrecord = getClient(messenger);
        boolean flag;
        if(clientrecord != null)
        {
            boolean flag1 = clientrecord.setDiscoveryRequest(mediaroutediscoveryrequest);
            if(DEBUG)
                Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Set discovery request, request=").append(mediaroutediscoveryrequest).append(", actuallyChanged=").append(flag1).append(", compositeDiscoveryRequest=").append(mCompositeDiscoveryRequest).toString());
            sendGenericSuccess(messenger, i);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean onSetRouteVolume(Messenger messenger, int i, int j, int k)
    {
        ClientRecord clientrecord = getClient(messenger);
        if(clientrecord == null) goto _L2; else goto _L1
_L1:
        MediaRouteProvider.RouteController routecontroller = clientrecord.getRouteController(j);
        if(routecontroller == null) goto _L2; else goto _L3
_L3:
        boolean flag;
        routecontroller.onSetVolume(k);
        if(DEBUG)
            Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Route volume changed").append(", controllerId=").append(j).append(", volume=").append(k).toString());
        sendGenericSuccess(messenger, i);
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private boolean onUnregisterClient(Messenger messenger, int i)
    {
        int j = findClient(messenger);
        boolean flag;
        if(j >= 0)
        {
            ClientRecord clientrecord = (ClientRecord)mClients.remove(j);
            if(DEBUG)
                Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Unregistered").toString());
            clientrecord.dispose();
            sendGenericSuccess(messenger, i);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean onUnselectRoute(Messenger messenger, int i, int j)
    {
        ClientRecord clientrecord = getClient(messenger);
        if(clientrecord == null) goto _L2; else goto _L1
_L1:
        MediaRouteProvider.RouteController routecontroller = clientrecord.getRouteController(j);
        if(routecontroller == null) goto _L2; else goto _L3
_L3:
        boolean flag;
        routecontroller.onUnselect();
        if(DEBUG)
            Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Route unselected").append(", controllerId=").append(j).toString());
        sendGenericSuccess(messenger, i);
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private boolean onUpdateRouteVolume(Messenger messenger, int i, int j, int k)
    {
        ClientRecord clientrecord = getClient(messenger);
        if(clientrecord == null) goto _L2; else goto _L1
_L1:
        MediaRouteProvider.RouteController routecontroller = clientrecord.getRouteController(j);
        if(routecontroller == null) goto _L2; else goto _L3
_L3:
        boolean flag;
        routecontroller.onUpdateVolume(k);
        if(DEBUG)
            Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Route volume updated").append(", controllerId=").append(j).append(", delta=").append(k).toString());
        sendGenericSuccess(messenger, i);
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void sendDescriptorChanged(MediaRouteProviderDescriptor mediarouteproviderdescriptor)
    {
        Bundle bundle;
        int i;
        if(mediarouteproviderdescriptor != null)
            bundle = mediarouteproviderdescriptor.asBundle();
        else
            bundle = null;
        i = mClients.size();
        for(int j = 0; j < i; j++)
        {
            ClientRecord clientrecord = (ClientRecord)mClients.get(j);
            sendReply(clientrecord.mMessenger, 5, 0, 0, bundle, null);
            if(DEBUG)
                Log.d("MediaRouteProviderSrv", (new StringBuilder()).append(clientrecord).append(": Sent descriptor change event, descriptor=").append(mediarouteproviderdescriptor).toString());
        }

    }

    private static void sendGenericFailure(Messenger messenger, int i)
    {
        if(i != 0)
            sendReply(messenger, 0, i, 0, null, null);
    }

    private static void sendGenericSuccess(Messenger messenger, int i)
    {
        if(i != 0)
            sendReply(messenger, 1, i, 0, null, null);
    }

    private static void sendReply(Messenger messenger, int i, int j, int k, Object obj, Bundle bundle)
    {
        Message message;
        message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        message.obj = obj;
        message.setData(bundle);
        messenger.send(message);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MediaRouteProviderSrv", (new StringBuilder()).append("Could not send message to ").append(getClientId(messenger)).toString(), remoteexception);
        continue; /* Loop/switch isn't completed */
        DeadObjectException deadobjectexception;
        deadobjectexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private boolean updateCompositeDiscoveryRequest()
    {
        MediaRouteDiscoveryRequest mediaroutediscoveryrequest = null;
        MediaRouteSelector.Builder builder = null;
        boolean flag = false;
        int i = mClients.size();
        int j = 0;
        while(j < i) 
        {
            MediaRouteDiscoveryRequest mediaroutediscoveryrequest1 = ((ClientRecord)mClients.get(j)).mDiscoveryRequest;
            if(mediaroutediscoveryrequest1 != null && (!mediaroutediscoveryrequest1.getSelector().isEmpty() || mediaroutediscoveryrequest1.isActiveScan()))
            {
                flag |= mediaroutediscoveryrequest1.isActiveScan();
                if(mediaroutediscoveryrequest == null)
                {
                    mediaroutediscoveryrequest = mediaroutediscoveryrequest1;
                } else
                {
                    if(builder == null)
                        builder = new MediaRouteSelector.Builder(mediaroutediscoveryrequest.getSelector());
                    builder.addSelector(mediaroutediscoveryrequest1.getSelector());
                }
            }
            j++;
        }
        if(builder != null)
            mediaroutediscoveryrequest = new MediaRouteDiscoveryRequest(builder.build(), flag);
        boolean flag1;
        if(mCompositeDiscoveryRequest != mediaroutediscoveryrequest && (mCompositeDiscoveryRequest == null || !mCompositeDiscoveryRequest.equals(mediaroutediscoveryrequest)))
        {
            mCompositeDiscoveryRequest = mediaroutediscoveryrequest;
            mProvider.setDiscoveryRequest(mediaroutediscoveryrequest);
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    public MediaRouteProvider getMediaRouteProvider()
    {
        return mProvider;
    }

    public IBinder onBind(Intent intent)
    {
        if(!intent.getAction().equals("android.media.MediaRouteProviderService")) goto _L2; else goto _L1
_L1:
        if(mProvider == null)
        {
            MediaRouteProvider mediarouteprovider = onCreateMediaRouteProvider();
            if(mediarouteprovider != null)
            {
                String s = mediarouteprovider.getMetadata().getPackageName();
                if(!s.equals(getPackageName()))
                    throw new IllegalStateException((new StringBuilder()).append("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: ").append(s).append(".  Service package name: ").append(getPackageName()).append(".").toString());
                mProvider = mediarouteprovider;
                mProvider.setCallback(mProviderCallback);
            }
        }
        if(mProvider == null) goto _L2; else goto _L3
_L3:
        IBinder ibinder = mReceiveMessenger.getBinder();
_L5:
        return ibinder;
_L2:
        ibinder = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public abstract MediaRouteProvider onCreateMediaRouteProvider();

    private static final boolean DEBUG = Log.isLoggable("MediaRouteProviderSrv", 3);
    private static final int PRIVATE_MSG_CLIENT_DIED = 1;
    public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
    private static final String TAG = "MediaRouteProviderSrv";
    private final ArrayList mClients = new ArrayList();
    private MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
    private final PrivateHandler mPrivateHandler = new PrivateHandler();
    private MediaRouteProvider mProvider;
    private final ProviderCallback mProviderCallback = new ProviderCallback();
    private final ReceiveHandler mReceiveHandler = new ReceiveHandler(this);
    private final Messenger mReceiveMessenger;





















}
