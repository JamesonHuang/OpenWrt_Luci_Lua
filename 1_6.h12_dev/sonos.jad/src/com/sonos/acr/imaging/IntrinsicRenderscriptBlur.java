// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.imaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.*;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.imaging:
//            BlurProcessor

public class IntrinsicRenderscriptBlur
    implements BlurProcessor
{

    public IntrinsicRenderscriptBlur(Context context)
    {
        if(mRS == null)
            mRS = RenderScript.create(context);
        mSIBlur = ScriptIntrinsicBlur.create(mRS, Element.U8_4(mRS));
    }

    public void performBlur(Bitmap bitmap, Bitmap bitmap1, float f)
    {
        long l = System.currentTimeMillis();
        if((int)f != 0)
        {
            Allocation allocation = Allocation.createFromBitmap(mRS, bitmap);
            Allocation allocation1 = Allocation.createFromBitmap(mRS, bitmap1);
            mSIBlur.setRadius((int)f);
            mSIBlur.setInput(allocation);
            mSIBlur.forEach(allocation1);
            allocation1.copyTo(bitmap1);
            allocation.destroy();
            allocation1.destroy();
            SLog.d(LOG_TAG, (new StringBuilder()).append("Time TO blur: ").append(System.currentTimeMillis() - l).append(" Size: ").toString());
        }
    }

    private static final String LOG_TAG = com/sonos/acr/imaging/IntrinsicRenderscriptBlur.getSimpleName();
    public static final int MAX_SUPPORTED_BLUR_PIXELS = 25;
    private static IntrinsicRenderscriptBlur instance;
    private static RenderScript mRS;
    private ScriptIntrinsicBlur mSIBlur;

}
