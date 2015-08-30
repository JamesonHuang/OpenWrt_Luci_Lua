// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.content.Context;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.SLog;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.downloadmanager:
//            BitmapDownloadOperation, BitmapLocalDownloadOperation, BitmapLogoDownloadOperation, BitmapRemoteDownloadOperation, 
//            BitmapFileReadOperation, DownloadBitmapCacheListener, ImageDescription

public class BitmapDownloadOperationRecord
    implements Comparable, BitmapDownloadOperation.BitmapDownloadOperationListener
{
    static interface RecordListener
    {

        public abstract void onOperationComplete(BitmapDownloadOperationRecord bitmapdownloadoperationrecord, boolean flag);

        public abstract void onOperationStart(BitmapDownloadOperationRecord bitmapdownloadoperationrecord);
    }


    public BitmapDownloadOperationRecord(Context context1, AlbumArtSize albumartsize, String s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype)
    {
        priority = 0;
        context = context1;
        albumArtSize = albumartsize;
        albumArtUri = s;
        albumArtType = scalbumarttype;
        if(canBeCachedByUI())
        {
            int i = lastSerial;
            lastSerial = i + 1;
            serialNumber = i;
        } else
        {
            serialNumber = 0L;
        }
    }

    private void complete(boolean flag)
    {
        if(recordListener != null)
        {
            if(flag && albumArtType != com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL && albumArtType != com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOGO)
                recordListener.onOperationComplete(this, true);
            else
                recordListener.onOperationComplete(this, false);
            recordListener = null;
        }
    }

    public void addListener(DownloadBitmapCacheListener downloadbitmapcachelistener, int i)
    {
        if(!listeners.contains(downloadbitmapcachelistener))
            listeners.add(downloadbitmapcachelistener);
        priority = i;
    }

    public boolean canBeCachedByUI()
    {
        boolean flag;
        if(albumArtType != com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL && albumArtType != com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOGO)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void cancel()
    {
        if(operation != null)
        {
            recordListener = null;
            operation.cancel();
        }
    }

    public int compareTo(BitmapDownloadOperationRecord bitmapdownloadoperationrecord)
    {
        int i = 1;
        if(bitmapdownloadoperationrecord.priority != priority) goto _L2; else goto _L1
_L1:
        if(bitmapdownloadoperationrecord.serialNumber >= serialNumber)
            if(bitmapdownloadoperationrecord.serialNumber > serialNumber)
                i = -1;
            else
                i = 0;
_L4:
        return i;
_L2:
        if(bitmapdownloadoperationrecord.priority > priority)
            i = -1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((BitmapDownloadOperationRecord)obj);
    }

    protected BitmapDownloadOperation createDownloadOperation()
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType = new int[com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.values().length];
                NoSuchFieldError nosuchfielderror5;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType[com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_RESTRICTED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType[com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType[com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOGO.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType[com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType[com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$com$sonos$sclib$SCIBrowseItem$SCAlbumArtType[com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL_URL.ordinal()] = 6;
_L2:
                return;
                nosuchfielderror5;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIBrowseItem.SCAlbumArtType[albumArtType.ordinal()];
        JVM INSTR tableswitch 1 6: default 48
    //                   1 52
    //                   2 52
    //                   3 75
    //                   4 98
    //                   5 122
    //                   6 146;
           goto _L1 _L2 _L2 _L3 _L4 _L5 _L6
_L1:
        Object obj = null;
_L8:
        return ((BitmapDownloadOperation) (obj));
_L2:
        obj = new BitmapLocalDownloadOperation(context, albumArtUri, albumArtSize);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new BitmapLogoDownloadOperation(context, albumArtUri, albumArtSize);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new BitmapRemoteDownloadOperation(context, albumArtUri, false, albumArtSize);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new BitmapRemoteDownloadOperation(context, albumArtUri, true, albumArtSize);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new BitmapFileReadOperation(context, albumArtUri, albumArtSize);
        if(true) goto _L8; else goto _L7
_L7:
    }

    public ImageDescription getImageDescription()
    {
        ImageDescription imagedescription;
        if(operation != null)
            imagedescription = operation.getImageDescription();
        else
            imagedescription = null;
        return imagedescription;
    }

    public long getSerialNumber()
    {
        return serialNumber;
    }

    public String getUri()
    {
        return albumArtUri;
    }

    public boolean hasListeners()
    {
        boolean flag;
        if(!listeners.isEmpty())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onBitmapDownloadCanceled(BitmapDownloadOperation bitmapdownloadoperation)
    {
    }

    public void onBitmapDownloadFailed(BitmapDownloadOperation bitmapdownloadoperation)
    {
        complete(true);
        Iterator iterator = listeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            DownloadBitmapCacheListener downloadbitmapcachelistener = (DownloadBitmapCacheListener)iterator.next();
            if(downloadbitmapcachelistener != null)
                downloadbitmapcachelistener.onBitmapDownloadFailed(serialNumber, bitmapdownloadoperation.getUri(), -1);
        } while(true);
    }

    public void onBitmapDownloadSucceeded(BitmapDownloadOperation bitmapdownloadoperation)
    {
        complete(false);
        Iterator iterator = listeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            DownloadBitmapCacheListener downloadbitmapcachelistener = (DownloadBitmapCacheListener)iterator.next();
            if(downloadbitmapcachelistener != null)
                downloadbitmapcachelistener.onBitmapDownloaded(serialNumber, bitmapdownloadoperation.getUri(), bitmapdownloadoperation.getBitmap(), bitmapdownloadoperation.getResId(), operation.wasCacheHit());
        } while(true);
    }

    public void removeListener(DownloadBitmapCacheListener downloadbitmapcachelistener)
    {
        if(listeners.contains(downloadbitmapcachelistener))
            listeners.remove(downloadbitmapcachelistener);
        else
            SLog.w(LOG_TAG, (new StringBuilder()).append("Record does not contain listener: ").append(downloadbitmapcachelistener).append(".  Did you forget to request?").toString());
    }

    public void start(RecordListener recordlistener)
    {
        recordListener = recordlistener;
        if(operation == null)
        {
            recordListener.onOperationStart(this);
            operation = createDownloadOperation();
            operation.start(this);
            return;
        } else
        {
            throw new RuntimeException("Operation has already started!");
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(albumArtSize).append(":").append(albumArtType).append(":").append(albumArtUri).append(":").append(serialNumber).toString();
    }

    private static final String LOG_TAG = com/sonos/acr/downloadmanager/BitmapDownloadOperation.getSimpleName();
    private static int lastSerial = 0;
    final AlbumArtSize albumArtSize;
    final com.sonos.sclib.SCIBrowseItem.SCAlbumArtType albumArtType;
    final String albumArtUri;
    final Context context;
    private final ArrayList listeners = new ArrayList();
    private BitmapDownloadOperation operation;
    private int priority;
    private RecordListener recordListener;
    private final long serialNumber;

}
