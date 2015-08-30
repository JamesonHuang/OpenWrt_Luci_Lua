// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.content.Context;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.*;
import java.security.InvalidParameterException;
import java.util.*;

// Referenced classes of package com.sonos.acr.downloadmanager:
//            BitmapDownloadOperationRecord, ImageDescription, DownloadBitmapCacheListener

public class BitmapDownloadManager
    implements BitmapDownloadOperationRecord.RecordListener
{

    public BitmapDownloadManager(AlbumArtSize albumartsize, int i)
    {
        if(i < 1)
        {
            throw new InvalidParameterException("Minimum cache size is 1.");
        } else
        {
            albumArtSize = albumartsize;
            inMemoryArtworkCache = new LruWeakCache(i);
            applicationContext = SonosApplication.getInstance().getApplicationContext();
            return;
        }
    }

    private boolean cancelDownload(DownloadBitmapCacheListener downloadbitmapcachelistener, BitmapDownloadOperationRecord bitmapdownloadoperationrecord)
    {
        boolean flag = false;
        if(bitmapdownloadoperationrecord != null)
        {
            bitmapdownloadoperationrecord.removeListener(downloadbitmapcachelistener);
            if(!bitmapdownloadoperationrecord.hasListeners())
            {
                bitmapdownloadoperationrecord.cancel();
                removeRecord(bitmapdownloadoperationrecord);
                flag = true;
            }
        }
        return flag;
    }

    private void removeRecord(BitmapDownloadOperationRecord bitmapdownloadoperationrecord)
    {
        if(!downloadQueue.remove(bitmapdownloadoperationrecord) && !downloadsInProgress.remove(bitmapdownloadoperationrecord))
            throw new RuntimeException("Couldn't Remove record from queue nor Downloads in progress");
        if(currentDownloadRecords.remove(bitmapdownloadoperationrecord.getUri()) == null)
        {
            throw new RuntimeException((new StringBuilder()).append("Unable to remove listener from: ").append(bitmapdownloadoperationrecord.getUri()).toString());
        } else
        {
            processNextInQueue();
            return;
        }
    }

    public boolean cancelDownload(DownloadBitmapCacheListener downloadbitmapcachelistener, long l)
    {
        Iterator iterator = currentDownloadRecords.values().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        BitmapDownloadOperationRecord bitmapdownloadoperationrecord = (BitmapDownloadOperationRecord)iterator.next();
        if(bitmapdownloadoperationrecord.getSerialNumber() != l) goto _L4; else goto _L3
_L3:
        boolean flag = cancelDownload(downloadbitmapcachelistener, bitmapdownloadoperationrecord);
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onLowMemory()
    {
        inMemoryArtworkCache.clear();
    }

    public void onOperationComplete(BitmapDownloadOperationRecord bitmapdownloadoperationrecord, boolean flag)
    {
        if(!bitmapdownloadoperationrecord.canBeCachedByUI()) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        SLog.i(LOG_TAG, (new StringBuilder()).append("Download failed for: ").append(bitmapdownloadoperationrecord).toString());
        failedDownloads.add(Integer.valueOf(bitmapdownloadoperationrecord.getUri().hashCode()));
_L6:
        removeRecord(bitmapdownloadoperationrecord);
_L2:
        return;
_L4:
        ImageDescription imagedescription = bitmapdownloadoperationrecord.getImageDescription();
        if(imagedescription != null)
            inMemoryArtworkCache.put(bitmapdownloadoperationrecord.getUri(), imagedescription);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onOperationStart(BitmapDownloadOperationRecord bitmapdownloadoperationrecord)
    {
    }

    protected void processNextInQueue()
    {
        if(downloadsInProgress.size() < 3 && !downloadQueue.isEmpty())
        {
            BitmapDownloadOperationRecord bitmapdownloadoperationrecord = (BitmapDownloadOperationRecord)downloadQueue.poll();
            if(bitmapdownloadoperationrecord != null)
            {
                downloadsInProgress.add(bitmapdownloadoperationrecord);
                bitmapdownloadoperationrecord.start(this);
            }
        }
    }

    public long queueDownload(String s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype, DownloadBitmapCacheListener downloadbitmapcachelistener, int i)
    {
        long l = 0L;
        if(!StringUtils.isEmptyOrNull(s)) goto _L2; else goto _L1
_L1:
        SLog.w(LOG_TAG, (new StringBuilder()).append("null or empty uri requested by: ").append(downloadbitmapcachelistener).toString());
        l = -1L;
_L4:
        return l;
_L2:
        ImageDescription imagedescription = (ImageDescription)inMemoryArtworkCache.get(s);
        if(imagedescription != null)
        {
            if(downloadbitmapcachelistener != null)
                downloadbitmapcachelistener.onBitmapDownloaded(l, s, imagedescription.getBitmap(), imagedescription.getResId(), true);
        } else
        if(failedDownloads.contains(Integer.valueOf(s.hashCode())))
        {
            if(downloadbitmapcachelistener != null)
                downloadbitmapcachelistener.onBitmapDownloadFailed(-1L, s, 0);
            l = -1L;
        } else
        {
            BitmapDownloadOperationRecord bitmapdownloadoperationrecord = (BitmapDownloadOperationRecord)currentDownloadRecords.get(s);
            if(bitmapdownloadoperationrecord == null)
            {
                bitmapdownloadoperationrecord = new BitmapDownloadOperationRecord(applicationContext, albumArtSize, s, scalbumarttype);
                if(bitmapdownloadoperationrecord.canBeCachedByUI())
                {
                    currentDownloadRecords.put(s, bitmapdownloadoperationrecord);
                    downloadQueue.add(bitmapdownloadoperationrecord);
                }
            }
            bitmapdownloadoperationrecord.addListener(downloadbitmapcachelistener, i);
            if(bitmapdownloadoperationrecord.canBeCachedByUI())
                SonosApplication.getInstance().getHandler().postAtFrontOfQueue(new Runnable() {

                    public void run()
                    {
                        processNextInQueue();
                    }

                    final BitmapDownloadManager this$0;

            
            {
                this$0 = BitmapDownloadManager.this;
                super();
            }
                }
);
            else
                bitmapdownloadoperationrecord.start(this);
            l = bitmapdownloadoperationrecord.getSerialNumber();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final int DOWNLOAD_CACHE_HIT_SERIAL = 0;
    public static final int DOWNLOAD_FAIL_SERIAL = -1;
    public static final int DOWNLOAD_INVALID_SERIAL = -2;
    private static final String LOG_TAG = com/sonos/acr/downloadmanager/BitmapDownloadManager.getSimpleName();
    private static final int MAX_OPERATIONS = 3;
    private final AlbumArtSize albumArtSize;
    private Context applicationContext;
    private final HashMap currentDownloadRecords = new HashMap();
    private final PriorityQueue downloadQueue = new PriorityQueue();
    private final ArrayList downloadsInProgress = new ArrayList(3);
    private final HashSet failedDownloads = new HashSet();
    private final LruWeakCache inMemoryArtworkCache;

}
