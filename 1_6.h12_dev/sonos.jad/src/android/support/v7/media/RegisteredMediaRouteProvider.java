// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.*;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v7.media:
//            MediaRouteProvider, MediaRouteProviderDescriptor, MediaRouteDescriptor, MediaRouteProviderProtocol, 
//            MediaRouteDiscoveryRequest

final class RegisteredMediaRouteProvider extends MediaRouteProvider
    implements ServiceConnection
{
    private static final class ReceiveHandler extends Handler
    {

        private boolean processMessage(Connection connection, int i, int j, int k, Object obj, Bundle bundle)
        {
            boolean flag = true;
            i;
            JVM INSTR tableswitch 0 5: default 44
        //                       0 50
        //                       1 59
        //                       2 68
        //                       3 125
        //                       4 153
        //                       5 98;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
            flag = false;
_L8:
            return flag;
_L2:
            connection.onGenericFailure(j);
              goto _L8
_L3:
            connection.onGenericSuccess(j);
              goto _L8
_L4:
            if(obj != null && !(obj instanceof Bundle)) goto _L1; else goto _L9
_L9:
            flag = connection.onRegistered(j, k, (Bundle)obj);
              goto _L8
_L7:
            if(obj != null && !(obj instanceof Bundle)) goto _L1; else goto _L10
_L10:
            flag = connection.onDescriptorChanged((Bundle)obj);
              goto _L8
_L5:
            if(obj != null && !(obj instanceof Bundle)) goto _L1; else goto _L11
_L11:
            flag = connection.onControlRequestSucceeded(j, (Bundle)obj);
              goto _L8
_L6:
            if(obj != null && !(obj instanceof Bundle)) goto _L1; else goto _L12
_L12:
            String s;
            if(bundle == null)
                s = null;
            else
                s = bundle.getString("error");
            flag = connection.onControlRequestFailed(j, s, (Bundle)obj);
              goto _L8
        }

        public void dispose()
        {
            mConnectionRef.clear();
        }

        public void handleMessage(Message message)
        {
            Connection connection = (Connection)mConnectionRef.get();
            if(connection != null && !processMessage(connection, message.what, message.arg1, message.arg2, message.obj, message.peekData()) && RegisteredMediaRouteProvider.DEBUG)
                Log.d("MediaRouteProviderProxy", (new StringBuilder()).append("Unhandled message from server: ").append(message).toString());
        }

        private final WeakReference mConnectionRef;

        public ReceiveHandler(Connection connection)
        {
            mConnectionRef = new WeakReference(connection);
        }
    }

    private final class PrivateHandler extends Handler
    {

        final RegisteredMediaRouteProvider this$0;

        private PrivateHandler()
        {
            this$0 = RegisteredMediaRouteProvider.this;
            super();
        }

    }

    private final class Connection
        implements android.os.IBinder.DeathRecipient
    {

        private void failPendingCallbacks()
        {
            for(int i = 0; i < mPendingCallbacks.size(); i++)
                ((MediaRouter.ControlRequestCallback)mPendingCallbacks.valueAt(i)).onError(null, null);

            mPendingCallbacks.clear();
        }

        private boolean sendRequest(int i, int j, int k, Object obj, Bundle bundle)
        {
            Message message;
            message = Message.obtain();
            message.what = i;
            message.arg1 = j;
            message.arg2 = k;
            message.obj = obj;
            message.setData(bundle);
            message.replyTo = mReceiveMessenger;
            mServiceMessenger.send(message);
            boolean flag = true;
_L2:
            return flag;
            RemoteException remoteexception;
            remoteexception;
            if(i != 2)
                Log.e("MediaRouteProviderProxy", "Could not send message to service.", remoteexception);
_L3:
            flag = false;
            if(true) goto _L2; else goto _L1
_L1:
            DeadObjectException deadobjectexception;
            deadobjectexception;
              goto _L3
        }

        public void binderDied()
        {
            mPrivateHandler.post(new Runnable() {

                public void run()
                {
                    onConnectionDied(Connection.this);
                }

                final Connection this$1;

                
                {
                    this$1 = Connection.this;
                    super();
                }
            }
);
        }

        public int createRouteController(String s)
        {
            int i = mNextControllerId;
            mNextControllerId = i + 1;
            Bundle bundle = new Bundle();
            bundle.putString("routeId", s);
            int j = mNextRequestId;
            mNextRequestId = j + 1;
            sendRequest(3, j, i, null, bundle);
            return i;
        }

        public void dispose()
        {
            sendRequest(2, 0, 0, null, null);
            mReceiveHandler.dispose();
            mServiceMessenger.getBinder().unlinkToDeath(this, 0);
            mPrivateHandler.post(new Runnable() {

                public void run()
                {
                    failPendingCallbacks();
                }

                final Connection this$1;

                
                {
                    this$1 = Connection.this;
                    super();
                }
            }
);
        }

        public boolean onControlRequestFailed(int i, String s, Bundle bundle)
        {
            MediaRouter.ControlRequestCallback controlrequestcallback = (MediaRouter.ControlRequestCallback)mPendingCallbacks.get(i);
            boolean flag;
            if(controlrequestcallback != null)
            {
                mPendingCallbacks.remove(i);
                controlrequestcallback.onError(s, bundle);
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean onControlRequestSucceeded(int i, Bundle bundle)
        {
            MediaRouter.ControlRequestCallback controlrequestcallback = (MediaRouter.ControlRequestCallback)mPendingCallbacks.get(i);
            boolean flag;
            if(controlrequestcallback != null)
            {
                mPendingCallbacks.remove(i);
                controlrequestcallback.onResult(bundle);
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean onDescriptorChanged(Bundle bundle)
        {
            boolean flag;
            if(mServiceVersion != 0)
            {
                onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean onGenericFailure(int i)
        {
            if(i == mPendingRegisterRequestId)
            {
                mPendingRegisterRequestId = 0;
                onConnectionError(this, "Registation failed");
            }
            MediaRouter.ControlRequestCallback controlrequestcallback = (MediaRouter.ControlRequestCallback)mPendingCallbacks.get(i);
            if(controlrequestcallback != null)
            {
                mPendingCallbacks.remove(i);
                controlrequestcallback.onError(null, null);
            }
            return true;
        }

        public boolean onGenericSuccess(int i)
        {
            return true;
        }

        public boolean onRegistered(int i, int j, Bundle bundle)
        {
            boolean flag = true;
            if(mServiceVersion == 0 && i == mPendingRegisterRequestId && j >= flag)
            {
                mPendingRegisterRequestId = 0;
                mServiceVersion = j;
                onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
                onConnectionReady(this);
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean register()
        {
            boolean flag = true;
            int i = mNextRequestId;
            mNextRequestId = i + 1;
            mPendingRegisterRequestId = i;
            if(!sendRequest(flag, mPendingRegisterRequestId, flag, null, null))
                flag = false;
            else
                try
                {
                    mServiceMessenger.getBinder().linkToDeath(this, 0);
                }
                catch(RemoteException remoteexception)
                {
                    binderDied();
                    flag = false;
                }
            return flag;
        }

        public void releaseRouteController(int i)
        {
            int j = mNextRequestId;
            mNextRequestId = j + 1;
            sendRequest(4, j, i, null, null);
        }

        public void selectRoute(int i)
        {
            int j = mNextRequestId;
            mNextRequestId = j + 1;
            sendRequest(5, j, i, null, null);
        }

        public boolean sendControlRequest(int i, Intent intent, MediaRouter.ControlRequestCallback controlrequestcallback)
        {
            int j = mNextRequestId;
            mNextRequestId = j + 1;
            boolean flag;
            if(sendRequest(9, j, i, intent, null))
            {
                if(controlrequestcallback != null)
                    mPendingCallbacks.put(j, controlrequestcallback);
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public void setDiscoveryRequest(MediaRouteDiscoveryRequest mediaroutediscoveryrequest)
        {
            int i = mNextRequestId;
            mNextRequestId = i + 1;
            Bundle bundle;
            if(mediaroutediscoveryrequest != null)
                bundle = mediaroutediscoveryrequest.asBundle();
            else
                bundle = null;
            sendRequest(10, i, 0, bundle, null);
        }

        public void setVolume(int i, int j)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", j);
            int k = mNextRequestId;
            mNextRequestId = k + 1;
            sendRequest(7, k, i, null, bundle);
        }

        public void unselectRoute(int i)
        {
            int j = mNextRequestId;
            mNextRequestId = j + 1;
            sendRequest(6, j, i, null, null);
        }

        public void updateVolume(int i, int j)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", j);
            int k = mNextRequestId;
            mNextRequestId = k + 1;
            sendRequest(8, k, i, null, bundle);
        }

        private int mNextControllerId;
        private int mNextRequestId;
        private final SparseArray mPendingCallbacks = new SparseArray();
        private int mPendingRegisterRequestId;
        private final ReceiveHandler mReceiveHandler = new ReceiveHandler(this);
        private final Messenger mReceiveMessenger;
        private final Messenger mServiceMessenger;
        private int mServiceVersion;
        final RegisteredMediaRouteProvider this$0;


        public Connection(Messenger messenger)
        {
            this$0 = RegisteredMediaRouteProvider.this;
            super();
            mNextRequestId = 1;
            mNextControllerId = 1;
            mServiceMessenger = messenger;
            mReceiveMessenger = new Messenger(mReceiveHandler);
        }
    }

    private final class Controller extends MediaRouteProvider.RouteController
    {

        public void attachConnection(Connection connection)
        {
            mConnection = connection;
            mControllerId = connection.createRouteController(mRouteId);
            if(mSelected)
            {
                connection.selectRoute(mControllerId);
                if(mPendingSetVolume >= 0)
                {
                    connection.setVolume(mControllerId, mPendingSetVolume);
                    mPendingSetVolume = -1;
                }
                if(mPendingUpdateVolumeDelta != 0)
                {
                    connection.updateVolume(mControllerId, mPendingUpdateVolumeDelta);
                    mPendingUpdateVolumeDelta = 0;
                }
            }
        }

        public void detachConnection()
        {
            if(mConnection != null)
            {
                mConnection.releaseRouteController(mControllerId);
                mConnection = null;
                mControllerId = 0;
            }
        }

        public boolean onControlRequest(Intent intent, MediaRouter.ControlRequestCallback controlrequestcallback)
        {
            boolean flag;
            if(mConnection != null)
                flag = mConnection.sendControlRequest(mControllerId, intent, controlrequestcallback);
            else
                flag = false;
            return flag;
        }

        public void onRelease()
        {
            onControllerReleased(this);
        }

        public void onSelect()
        {
            mSelected = true;
            if(mConnection != null)
                mConnection.selectRoute(mControllerId);
        }

        public void onSetVolume(int i)
        {
            if(mConnection != null)
            {
                mConnection.setVolume(mControllerId, i);
            } else
            {
                mPendingSetVolume = i;
                mPendingUpdateVolumeDelta = 0;
            }
        }

        public void onUnselect()
        {
            mSelected = false;
            if(mConnection != null)
                mConnection.unselectRoute(mControllerId);
        }

        public void onUpdateVolume(int i)
        {
            if(mConnection != null)
                mConnection.updateVolume(mControllerId, i);
            else
                mPendingUpdateVolumeDelta = i + mPendingUpdateVolumeDelta;
        }

        private Connection mConnection;
        private int mControllerId;
        private int mPendingSetVolume;
        private int mPendingUpdateVolumeDelta;
        private final String mRouteId;
        private boolean mSelected;
        final RegisteredMediaRouteProvider this$0;

        public Controller(String s)
        {
            this$0 = RegisteredMediaRouteProvider.this;
            super();
            mPendingSetVolume = -1;
            mRouteId = s;
        }
    }


    public RegisteredMediaRouteProvider(Context context, ComponentName componentname)
    {
        super(context, new MediaRouteProvider.ProviderMetadata(componentname));
        mComponentName = componentname;
    }

    private void attachControllersToConnection()
    {
        int i = mControllers.size();
        for(int j = 0; j < i; j++)
            ((Controller)mControllers.get(j)).attachConnection(mActiveConnection);

    }

    private void bind()
    {
        Intent intent;
        if(mBound)
            break MISSING_BLOCK_LABEL_109;
        if(DEBUG)
            Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Binding").toString());
        intent = new Intent("android.media.MediaRouteProviderService");
        intent.setComponent(mComponentName);
        mBound = getContext().bindService(intent, this, 1);
        if(!mBound && DEBUG)
            Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Bind failed").toString());
_L1:
        return;
        SecurityException securityexception;
        securityexception;
        if(DEBUG)
            Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Bind failed").toString(), securityexception);
          goto _L1
    }

    private void detachControllersFromConnection()
    {
        int i = mControllers.size();
        for(int j = 0; j < i; j++)
            ((Controller)mControllers.get(j)).detachConnection();

    }

    private void disconnect()
    {
        if(mActiveConnection != null)
        {
            setDescriptor(null);
            mConnectionReady = false;
            detachControllersFromConnection();
            mActiveConnection.dispose();
            mActiveConnection = null;
        }
    }

    private void onConnectionDescriptorChanged(Connection connection, MediaRouteProviderDescriptor mediarouteproviderdescriptor)
    {
        if(mActiveConnection == connection)
        {
            if(DEBUG)
                Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Descriptor changed, descriptor=").append(mediarouteproviderdescriptor).toString());
            setDescriptor(mediarouteproviderdescriptor);
        }
    }

    private void onConnectionDied(Connection connection)
    {
        if(mActiveConnection == connection)
        {
            if(DEBUG)
                Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Service connection died").toString());
            disconnect();
        }
    }

    private void onConnectionError(Connection connection, String s)
    {
        if(mActiveConnection == connection)
        {
            if(DEBUG)
                Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Service connection error - ").append(s).toString());
            unbind();
        }
    }

    private void onConnectionReady(Connection connection)
    {
        if(mActiveConnection == connection)
        {
            mConnectionReady = true;
            attachControllersToConnection();
            MediaRouteDiscoveryRequest mediaroutediscoveryrequest = getDiscoveryRequest();
            if(mediaroutediscoveryrequest != null)
                mActiveConnection.setDiscoveryRequest(mediaroutediscoveryrequest);
        }
    }

    private void onControllerReleased(Controller controller)
    {
        mControllers.remove(controller);
        controller.detachConnection();
        updateBinding();
    }

    private boolean shouldBind()
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if(!mStarted || getDiscoveryRequest() == null && mControllers.isEmpty())
            flag = false;
        return flag;
    }

    private void unbind()
    {
        if(mBound)
        {
            if(DEBUG)
                Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Unbinding").toString());
            mBound = false;
            disconnect();
            getContext().unbindService(this);
        }
    }

    private void updateBinding()
    {
        if(shouldBind())
            bind();
        else
            unbind();
    }

    public boolean hasComponentName(String s, String s1)
    {
        boolean flag;
        if(mComponentName.getPackageName().equals(s) && mComponentName.getClassName().equals(s1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public MediaRouteProvider.RouteController onCreateRouteController(String s)
    {
        List list;
        int i;
        int j;
        MediaRouteProviderDescriptor mediarouteproviderdescriptor = getDescriptor();
        if(mediarouteproviderdescriptor == null)
            break MISSING_BLOCK_LABEL_102;
        list = mediarouteproviderdescriptor.getRoutes();
        i = list.size();
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_102;
        if(!((MediaRouteDescriptor)list.get(j)).getId().equals(s)) goto _L2; else goto _L1
_L1:
        Controller controller;
        controller = new Controller(s);
        mControllers.add(controller);
        if(mConnectionReady)
            controller.attachConnection(mActiveConnection);
        updateBinding();
_L4:
        return controller;
_L2:
        j++;
          goto _L3
        controller = null;
          goto _L4
    }

    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest mediaroutediscoveryrequest)
    {
        if(mConnectionReady)
            mActiveConnection.setDiscoveryRequest(mediaroutediscoveryrequest);
        updateBinding();
    }

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        if(DEBUG)
            Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Connected").toString());
        if(!mBound) goto _L2; else goto _L1
_L1:
        disconnect();
        Messenger messenger;
        Connection connection;
        if(ibinder != null)
            messenger = new Messenger(ibinder);
        else
            messenger = null;
        if(!MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) goto _L4; else goto _L3
_L3:
        connection = new Connection(messenger);
        if(!connection.register()) goto _L6; else goto _L5
_L5:
        mActiveConnection = connection;
_L2:
        return;
_L6:
        if(DEBUG)
            Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Registration failed").toString());
        continue; /* Loop/switch isn't completed */
_L4:
        Log.e("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Service returned invalid messenger binder").toString());
        if(true) goto _L2; else goto _L7
_L7:
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        if(DEBUG)
            Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Service disconnected").toString());
        disconnect();
    }

    public void rebindIfDisconnected()
    {
        if(mActiveConnection == null && shouldBind())
        {
            unbind();
            bind();
        }
    }

    public void start()
    {
        if(!mStarted)
        {
            if(DEBUG)
                Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Starting").toString());
            mStarted = true;
            updateBinding();
        }
    }

    public void stop()
    {
        if(mStarted)
        {
            if(DEBUG)
                Log.d("MediaRouteProviderProxy", (new StringBuilder()).append(this).append(": Stopping").toString());
            mStarted = false;
            updateBinding();
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("Service connection ").append(mComponentName.flattenToShortString()).toString();
    }

    private static final boolean DEBUG = Log.isLoggable("MediaRouteProviderProxy", 3);
    private static final String TAG = "MediaRouteProviderProxy";
    private Connection mActiveConnection;
    private boolean mBound;
    private final ComponentName mComponentName;
    private boolean mConnectionReady;
    private final ArrayList mControllers = new ArrayList();
    private final PrivateHandler mPrivateHandler = new PrivateHandler();
    private boolean mStarted;








}
