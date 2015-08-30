// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.os.*;

public interface INotificationSideChannel
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INotificationSideChannel
    {
        private static class Proxy
            implements INotificationSideChannel
        {

            public IBinder asBinder()
            {
                return mRemote;
            }

            public void cancel(String s, int i, String s1)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                parcel.writeString(s);
                parcel.writeInt(i);
                parcel.writeString(s1);
                mRemote.transact(2, parcel, null, 1);
                parcel.recycle();
                return;
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public void cancelAll(String s)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                parcel.writeString(s);
                mRemote.transact(3, parcel, null, 1);
                parcel.recycle();
                return;
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public String getInterfaceDescriptor()
            {
                return "android.support.v4.app.INotificationSideChannel";
            }

            public void notify(String s, int i, String s1, Notification notification)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                parcel.writeString(s);
                parcel.writeInt(i);
                parcel.writeString(s1);
                if(notification == null)
                    break MISSING_BLOCK_LABEL_70;
                parcel.writeInt(1);
                notification.writeToParcel(parcel, 0);
_L1:
                mRemote.transact(1, parcel, null, 1);
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            private IBinder mRemote;

            Proxy(IBinder ibinder)
            {
                mRemote = ibinder;
            }
        }


        public static INotificationSideChannel asInterface(IBinder ibinder)
        {
            Object obj;
            if(ibinder == null)
            {
                obj = null;
            } else
            {
                IInterface iinterface = ibinder.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
                if(iinterface != null && (iinterface instanceof INotificationSideChannel))
                    obj = (INotificationSideChannel)iinterface;
                else
                    obj = new Proxy(ibinder);
            }
            return ((INotificationSideChannel) (obj));
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            boolean flag = true;
            i;
            JVM INSTR lookupswitch 4: default 48
        //                       1: 71
        //                       2: 137
        //                       3: 162
        //                       1598968902: 62;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            flag = super.onTransact(i, parcel, parcel1, j);
_L7:
            return flag;
_L5:
            parcel1.writeString("android.support.v4.app.INotificationSideChannel");
            continue; /* Loop/switch isn't completed */
_L2:
            parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
            String s = parcel.readString();
            int k = parcel.readInt();
            String s1 = parcel.readString();
            Notification notification;
            if(parcel.readInt() != 0)
                notification = (Notification)Notification.CREATOR.createFromParcel(parcel);
            else
                notification = null;
            notify(s, k, s1, notification);
            continue; /* Loop/switch isn't completed */
_L3:
            parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
            cancel(parcel.readString(), parcel.readInt(), parcel.readString());
            continue; /* Loop/switch isn't completed */
_L4:
            parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
            cancelAll(parcel.readString());
            if(true) goto _L7; else goto _L6
_L6:
        }

        private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        public Stub()
        {
            attachInterface(this, "android.support.v4.app.INotificationSideChannel");
        }
    }


    public abstract void cancel(String s, int i, String s1)
        throws RemoteException;

    public abstract void cancelAll(String s)
        throws RemoteException;

    public abstract void notify(String s, int i, String s1, Notification notification)
        throws RemoteException;
}
