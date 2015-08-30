// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.localaudiolibrary.LocalMediaUtils;
import com.sonos.acr.util.*;

// Referenced classes of package com.sonos.acr.downloadmanager:
//            BitmapDownloadOperation

public class BitmapFileReadOperation extends BitmapDownloadOperation
    implements Runnable
{

    public BitmapFileReadOperation(Context context, String s, AlbumArtSize albumartsize)
    {
        super(context, s, albumartsize);
    }

    private void onComplete()
    {
        if(albumArt != null)
            onBitmapDownloadSucceeded(albumArt, 0);
        else
            onBitmapDownloadFailed();
    }

    public void cancel()
    {
        super.cancel();
        workqueue.cancel(this);
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getDownloadType()
    {
        return com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL_URL;
    }

    protected void performDownload()
    {
        workqueue.execute(this);
    }

    public void run()
    {
        String s = getInternalUri();
        try
        {
            int i = getAlbumArtSize().getScaleFactor(s);
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.inSampleSize = i;
            options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
            options.inInputShareable = true;
            albumArt = LocalMediaUtils.decodeBitmapUri(s, options);
        }
        catch(Exception exception)
        {
            SLog.e(LOG_TAG, (new StringBuilder()).append("Error Decoding bitmap: ").append(s).toString(), exception);
        }
        handler.post(new Runnable() {

            public void run()
            {
                onComplete();
            }

            final BitmapFileReadOperation this$0;

            
            {
                this$0 = BitmapFileReadOperation.this;
                super();
            }
        }
);
    }

    public boolean wasCacheHit()
    {
        return false;
    }

    private static Handler handler = SonosApplication.getInstance().getHandler();
    private static final WorkQueue workqueue = new WorkQueue(3);
    Bitmap albumArt;


}
