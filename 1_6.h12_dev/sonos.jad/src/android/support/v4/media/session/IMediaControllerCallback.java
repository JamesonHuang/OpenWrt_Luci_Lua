// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.os.*;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            PlaybackStateCompat, ParcelableVolumeInfo

public interface IMediaControllerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaControllerCallback
    {
        private static class Proxy
            implements IMediaControllerCallback
        {

            public IBinder asBinder()
            {
                return mRemote;
            }

            public String getInterfaceDescriptor()
            {
                return "android.support.v4.media.session.IMediaControllerCallback";
            }

            public void onEvent(String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                parcel.writeString(s);
                if(bundle == null)
                    break MISSING_BLOCK_LABEL_49;
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
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

            public void onExtrasChanged(Bundle bundle)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                if(bundle == null)
                    break MISSING_BLOCK_LABEL_45;
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L1:
                mRemote.transact(7, parcel, null, 1);
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                if(mediametadatacompat == null)
                    break MISSING_BLOCK_LABEL_44;
                parcel.writeInt(1);
                mediametadatacompat.writeToParcel(parcel, 0);
_L1:
                mRemote.transact(4, parcel, null, 1);
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                if(playbackstatecompat == null)
                    break MISSING_BLOCK_LABEL_44;
                parcel.writeInt(1);
                playbackstatecompat.writeToParcel(parcel, 0);
_L1:
                mRemote.transact(3, parcel, null, 1);
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public void onQueueChanged(List list)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                parcel.writeTypedList(list);
                mRemote.transact(5, parcel, null, 1);
                parcel.recycle();
                return;
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public void onQueueTitleChanged(CharSequence charsequence)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                if(charsequence == null)
                    break MISSING_BLOCK_LABEL_45;
                parcel.writeInt(1);
                TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
                mRemote.transact(6, parcel, null, 1);
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public void onSessionDestroyed()
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                mRemote.transact(2, parcel, null, 1);
                parcel.recycle();
                return;
                Exception exception;
                exception;
                parcel.recycle();
                throw exception;
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
                throws RemoteException
            {
                Parcel parcel = Parcel.obtain();
                parcel.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                if(parcelablevolumeinfo == null)
                    break MISSING_BLOCK_LABEL_45;
                parcel.writeInt(1);
                parcelablevolumeinfo.writeToParcel(parcel, 0);
_L1:
                mRemote.transact(8, parcel, null, 1);
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


        public static IMediaControllerCallback asInterface(IBinder ibinder)
        {
            Object obj;
            if(ibinder == null)
            {
                obj = null;
            } else
            {
                IInterface iinterface = ibinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
                if(iinterface != null && (iinterface instanceof IMediaControllerCallback))
                    obj = (IMediaControllerCallback)iinterface;
                else
                    obj = new Proxy(ibinder);
            }
            return ((IMediaControllerCallback) (obj));
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
            JVM INSTR lookupswitch 9: default 88
        //                       1: 111
        //                       2: 161
        //                       3: 174
        //                       4: 216
        //                       5: 258
        //                       6: 278
        //                       7: 320
        //                       8: 362
        //                       1598968902: 102;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            flag = super.onTransact(i, parcel, parcel1, j);
_L12:
            return flag;
_L10:
            parcel1.writeString("android.support.v4.media.session.IMediaControllerCallback");
            continue; /* Loop/switch isn't completed */
_L2:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            String s = parcel.readString();
            Bundle bundle1;
            if(parcel.readInt() != 0)
                bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle1 = null;
            onEvent(s, bundle1);
            continue; /* Loop/switch isn't completed */
_L3:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            onSessionDestroyed();
            continue; /* Loop/switch isn't completed */
_L4:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            PlaybackStateCompat playbackstatecompat;
            if(parcel.readInt() != 0)
                playbackstatecompat = (PlaybackStateCompat)PlaybackStateCompat.CREATOR.createFromParcel(parcel);
            else
                playbackstatecompat = null;
            onPlaybackStateChanged(playbackstatecompat);
            continue; /* Loop/switch isn't completed */
_L5:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            MediaMetadataCompat mediametadatacompat;
            if(parcel.readInt() != 0)
                mediametadatacompat = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(parcel);
            else
                mediametadatacompat = null;
            onMetadataChanged(mediametadatacompat);
            continue; /* Loop/switch isn't completed */
_L6:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            onQueueChanged(parcel.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR));
            continue; /* Loop/switch isn't completed */
_L7:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            CharSequence charsequence;
            if(parcel.readInt() != 0)
                charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            else
                charsequence = null;
            onQueueTitleChanged(charsequence);
            continue; /* Loop/switch isn't completed */
_L8:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            Bundle bundle;
            if(parcel.readInt() != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle = null;
            onExtrasChanged(bundle);
            continue; /* Loop/switch isn't completed */
_L9:
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            ParcelableVolumeInfo parcelablevolumeinfo;
            if(parcel.readInt() != 0)
                parcelablevolumeinfo = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(parcel);
            else
                parcelablevolumeinfo = null;
            onVolumeInfoChanged(parcelablevolumeinfo);
            if(true) goto _L12; else goto _L11
_L11:
        }

        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onExtrasChanged = 7;
        static final int TRANSACTION_onMetadataChanged = 4;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onQueueChanged = 5;
        static final int TRANSACTION_onQueueTitleChanged = 6;
        static final int TRANSACTION_onSessionDestroyed = 2;
        static final int TRANSACTION_onVolumeInfoChanged = 8;

        public Stub()
        {
            attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        }
    }


    public abstract void onEvent(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onExtrasChanged(Bundle bundle)
        throws RemoteException;

    public abstract void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
        throws RemoteException;

    public abstract void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
        throws RemoteException;

    public abstract void onQueueChanged(List list)
        throws RemoteException;

    public abstract void onQueueTitleChanged(CharSequence charsequence)
        throws RemoteException;

    public abstract void onSessionDestroyed()
        throws RemoteException;

    public abstract void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
        throws RemoteException;
}
