// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.*;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import java.io.InputStream;

// Referenced classes of package android.support.v4.graphics.drawable:
//            RoundedBitmapDrawable21, RoundedBitmapDrawable

public class RoundedBitmapDrawableFactory
{
    private static class DefaultRoundedBitmapDrawable extends RoundedBitmapDrawable
    {

        void gravityCompatApply(int i, int j, int k, Rect rect, Rect rect1)
        {
            GravityCompat.apply(i, j, k, rect, rect1, 0);
        }

        public boolean hasMipMap()
        {
            boolean flag;
            if(mBitmap != null && BitmapCompat.hasMipMap(mBitmap))
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void setMipMap(boolean flag)
        {
            if(mBitmap != null)
            {
                BitmapCompat.setHasMipMap(mBitmap, flag);
                invalidateSelf();
            }
        }

        DefaultRoundedBitmapDrawable(Resources resources, Bitmap bitmap)
        {
            super(resources, bitmap);
        }
    }


    public RoundedBitmapDrawableFactory()
    {
    }

    public static RoundedBitmapDrawable create(Resources resources, Bitmap bitmap)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            obj = new RoundedBitmapDrawable21(resources, bitmap);
        else
            obj = new DefaultRoundedBitmapDrawable(resources, bitmap);
        return ((RoundedBitmapDrawable) (obj));
    }

    public static RoundedBitmapDrawable create(Resources resources, InputStream inputstream)
    {
        RoundedBitmapDrawable roundedbitmapdrawable = create(resources, BitmapFactory.decodeStream(inputstream));
        if(roundedbitmapdrawable.getBitmap() == null)
            Log.w("RoundedBitmapDrawableFactory", (new StringBuilder()).append("BitmapDrawable cannot decode ").append(inputstream).toString());
        return roundedbitmapdrawable;
    }

    public static RoundedBitmapDrawable create(Resources resources, String s)
    {
        RoundedBitmapDrawable roundedbitmapdrawable = create(resources, BitmapFactory.decodeFile(s));
        if(roundedbitmapdrawable.getBitmap() == null)
            Log.w("RoundedBitmapDrawableFactory", (new StringBuilder()).append("BitmapDrawable cannot decode ").append(s).toString());
        return roundedbitmapdrawable;
    }

    private static final String TAG = "RoundedBitmapDrawableFactory";
}
