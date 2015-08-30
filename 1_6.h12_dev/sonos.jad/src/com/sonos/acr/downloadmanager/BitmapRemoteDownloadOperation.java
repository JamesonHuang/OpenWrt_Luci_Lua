// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Handler;
import com.caverock.androidsvg.SVG;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.io.ByteArrayInputStream;

// Referenced classes of package com.sonos.acr.downloadmanager:
//            BitmapDownloadOperation

public class BitmapRemoteDownloadOperation extends BitmapDownloadOperation
    implements Runnable
{
    private class DecodeBuffer
    {

        public void finishUsing()
        {
            inUse = false;
        }

        public byte[] getBuffer(int i)
        {
            if(buffer == null || buffer.length < i)
            {
                String s = LOG_TAG;
                StringBuilder stringbuilder = (new StringBuilder()).append("Resizing the data buffer to be ").append(i).append(" bytes from ");
                Object obj;
                if(buffer == null)
                    obj = "null";
                else
                    obj = Integer.valueOf(buffer.length);
                SLog.w(s, stringbuilder.append(obj).toString());
                buffer = new byte[i];
            }
            return buffer;
        }

        public boolean isInUse()
        {
            return inUse;
        }

        public void startUsing()
        {
            inUse = true;
        }

        private byte buffer[];
        private boolean inUse;
        final BitmapRemoteDownloadOperation this$0;

        private DecodeBuffer()
        {
            this$0 = BitmapRemoteDownloadOperation.this;
            super();
        }

    }


    public BitmapRemoteDownloadOperation(Context context, String s, boolean flag, AlbumArtSize albumartsize)
    {
        super(context, s, albumartsize);
        mSerialNumber = sclibConstants.SCOP_INVALID_SERIALNUM;
        mOperation = null;
        com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype;
        if(flag)
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL;
        else
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL;
        mType = scalbumarttype;
    }

    private Bitmap decodeSVG(ByteArrayInputStream bytearrayinputstream)
    {
        Bitmap bitmap1;
        SVG svg = SVG.getFromInputStream(bytearrayinputstream);
        int i = getAlbumArtSize().getPixelWidth();
        bitmap1 = ImageUtils.getBitmap(svg, new RectF(0.0F, 0.0F, i, i));
        Bitmap bitmap = bitmap1;
_L2:
        return bitmap;
        Exception exception;
        exception;
        bitmap = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private DecodeBuffer getDecodeBuffer()
    {
        DecodeBuffer decodebuffer = null;
        DecodeBuffer adecodebuffer[] = decodeBuffers;
        adecodebuffer;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if(i >= decodeBuffers.length)
            break; /* Loop/switch isn't completed */
        if(decodeBuffers[i] == null)
        {
            decodeBuffers[i] = new DecodeBuffer();
            decodeBuffers[i].startUsing();
            decodebuffer = decodeBuffers[i];
            break MISSING_BLOCK_LABEL_113;
        }
        if(!decodeBuffers[i].isInUse())
        {
            decodeBuffers[i].startUsing();
            decodebuffer = decodeBuffers[i];
            break MISSING_BLOCK_LABEL_113;
        }
        break MISSING_BLOCK_LABEL_96;
        Exception exception;
        exception;
        throw exception;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        adecodebuffer;
        JVM INSTR monitorexit ;
        SLog.w(LOG_TAG, "Unable to find unused decode buffer!");
        return decodebuffer;
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
        workqueue.cancel(this);
        if(mOperation != null)
        {
            mOperation._cancel();
            mOperation = null;
        }
        mSerialNumber = sclibConstants.SCOP_INVALID_SERIALNUM;
        super.cancel();
    }

    protected void cleanupDownload(boolean flag)
    {
        mOperation = null;
        super.cleanupDownload(flag);
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getDownloadType()
    {
        return mType;
    }

    protected void performDownload()
    {
        SCIOpFactory sciopfactory = SonosApplication.getInstance().getSCLibManager().getOpFactory();
        String s = getInternalUri();
        boolean flag;
        SCIOpCBSwigBase sciopcbswigbase;
        if(getDownloadType() == com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL)
            flag = true;
        else
            flag = false;
        mOperation = sciopfactory.createLoadAlbumArtOp(s, flag, 0x200000, 30000);
        sciopcbswigbase = new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                if(i == 0)
                    BitmapRemoteDownloadOperation.workqueue.execute(BitmapRemoteDownloadOperation.this);
                else
                    onBitmapDownloadFailed();
            }

            final BitmapRemoteDownloadOperation this$0;

            
            {
                this$0 = BitmapRemoteDownloadOperation.this;
                super();
            }
        }
;
        mSerialNumber = mOperation._start(sciopcbswigbase);
        if(mSerialNumber == 0L)
            onBitmapDownloadFailed();
    }

    public void run()
    {
        if(!mOperation.dataLoaded()) goto _L2; else goto _L1
_L1:
        int i = (int)mOperation.getDataSize();
        if(i != 0) goto _L4; else goto _L3
_L3:
        SLog.d(LOG_TAG, (new StringBuilder()).append("Album art return 0 bytes ").append(getInternalUri()).toString());
_L9:
        handler.post(new Runnable() {

            public void run()
            {
                onComplete();
            }

            final BitmapRemoteDownloadOperation this$0;

            
            {
                this$0 = BitmapRemoteDownloadOperation.this;
                super();
            }
        }
);
        return;
_L4:
        DecodeBuffer decodebuffer = null;
        decodebuffer = getDecodeBuffer();
        if(decodebuffer == null)
            break MISSING_BLOCK_LABEL_319;
        byte abyte0[] = decodebuffer.getBuffer(i);
        mOperation.getData(abyte0);
        albumArt = decodeSVG(new ByteArrayInputStream(abyte0, 0, i));
        if(albumArt == null)
        {
            int j = getAlbumArtSize().getScaleFactor(abyte0, i);
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.inSampleSize = j;
            options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
            options.inInputShareable = true;
            albumArt = ImageUtils.decodeByteArray(abyte0, 0, i, options);
        }
        if(albumArt == null) goto _L6; else goto _L5
_L5:
        if(albumArt.getConfig() == null)
        {
            SLog.w(LOG_TAG, "albumart has no configuration, rebuilding.");
            Bitmap bitmap = albumArt.copy(android.graphics.Bitmap.Config.RGB_565, false);
            albumArt.recycle();
            albumArt = bitmap;
        }
_L7:
        if(decodebuffer != null)
            decodebuffer.finishUsing();
        continue; /* Loop/switch isn't completed */
_L6:
        SLog.e(LOG_TAG, (new StringBuilder()).append("Album art Bitmap creation failed - Album Art corrupted: ").append(getInternalUri()).toString());
          goto _L7
        Exception exception1;
        exception1;
        SLog.e(LOG_TAG, (new StringBuilder()).append("Album art Bitmap creation failed ").append(getInternalUri()).toString(), exception1);
        if(decodebuffer != null)
            decodebuffer.finishUsing();
        continue; /* Loop/switch isn't completed */
        SLog.e(LOG_TAG, (new StringBuilder()).append("Album art buffer creation failed ").append(getInternalUri()).toString());
          goto _L7
        Exception exception;
        exception;
        if(decodebuffer != null)
            decodebuffer.finishUsing();
        throw exception;
_L2:
        SLog.e(LOG_TAG, (new StringBuilder()).append("Album art async IO operation failed ").append(getInternalUri()).toString());
        if(true) goto _L9; else goto _L8
_L8:
    }

    public boolean wasCacheHit()
    {
        return false;
    }

    private static final int MAX_DOWNLOAD_BYTES = 0x200000;
    private static final int OPERATION_TIMEOUT_MS = 30000;
    private static final DecodeBuffer decodeBuffers[] = new DecodeBuffer[3];
    private static final Handler handler = SonosApplication.getInstance().getHandler();
    private static final WorkQueue workqueue = new WorkQueue(3);
    Bitmap albumArt;
    private SCIOpLoadAlbumArt mOperation;
    private long mSerialNumber;
    private com.sonos.sclib.SCIBrowseItem.SCAlbumArtType mType;



}
