// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.imaging.*;
import com.sonos.acr.sclib.SCLibManager;

// Referenced classes of package com.sonos.acr.util:
//            WeakMap, SLog, AlbumArtSize

public class ImageUtils
{
    private static class SVGResKey
    {

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this != obj) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            if(obj == null || getClass() != obj.getClass())
            {
                flag = false;
            } else
            {
                SVGResKey svgreskey = (SVGResKey)obj;
                if(height != svgreskey.height || resId != svgreskey.resId || width != svgreskey.width)
                    flag = false;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public int hashCode()
        {
            return 31 * (31 * resId + width) + height;
        }

        int height;
        int resId;
        int width;

        private SVGResKey(int i, int j, int k)
        {
            resId = i;
            width = j;
            height = k;
        }

    }


    public ImageUtils()
    {
    }

    public static Bitmap copyBitmap(Bitmap bitmap)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Creating a bitmap copy: Width: ").append(bitmap.getWidth()).append(" Height: ").append(bitmap.getHeight()).toString());
        Bitmap bitmap2 = bitmap.copy(bitmap.getConfig(), false);
        Bitmap bitmap1 = bitmap2;
_L2:
        return bitmap1;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        onOutOfMemory(outofmemoryerror);
        bitmap1 = bitmap.copy(bitmap.getConfig(), false);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Bitmap createBitmap(int i, int j, android.graphics.Bitmap.Config config)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Creating a bitmap: Width: ").append(i).append(" Height: ").append(j).toString());
        Bitmap bitmap1 = Bitmap.createBitmap(i, j, config);
        Bitmap bitmap = bitmap1;
_L2:
        return bitmap;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        onOutOfMemory(outofmemoryerror);
        bitmap = Bitmap.createBitmap(i, j, config);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Bitmap createBitmap(Bitmap bitmap)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Creating a bitmap existing: Width: ").append(bitmap.getWidth()).append(" Height: ").append(bitmap.getHeight()).toString());
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap);
        Bitmap bitmap1 = bitmap2;
_L2:
        return bitmap1;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        onOutOfMemory(outofmemoryerror);
        bitmap1 = Bitmap.createBitmap(bitmap);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Bitmap createBitmap(Bitmap bitmap, float f)
    {
        Matrix matrix;
        SLog.i(LOG_TAG, (new StringBuilder()).append("Creating a bitmap existing: Width: ").append(f * (float)bitmap.getWidth()).append(" Height: ").append(f * (float)bitmap.getHeight()).toString());
        matrix = new Matrix();
        matrix.setScale(f, f);
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Bitmap bitmap1 = bitmap2;
_L2:
        return bitmap1;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        onOutOfMemory(outofmemoryerror);
        bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static BlurProcessor createBlurProcessor(Context context)
    {
        Object obj;
        if(isARMv7())
            obj = new IntrinsicRenderscriptBlur(context);
        else
            obj = new JavaBlur();
        return ((BlurProcessor) (obj));
    }

    public static Bitmap decodeByteArray(byte abyte0[], int i, int j, android.graphics.BitmapFactory.Options options)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Decoding a bitmap.  DataSize: ").append(abyte0.length).append(options).toString());
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(abyte0, i, j, options);
        Bitmap bitmap = bitmap1;
_L2:
        return bitmap;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        onOutOfMemory(outofmemoryerror);
        bitmap = BitmapFactory.decodeByteArray(abyte0, i, j, options);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Bitmap getBitmap(SVG svg, RectF rectf)
    {
        Picture picture = svg.renderToPicture();
        RectF rectf1 = new RectF(0.0F, 0.0F, picture.getWidth(), picture.getHeight());
        RectF rectf2;
        Matrix matrix;
        Bitmap bitmap;
        Canvas canvas;
        if(rectf == null)
        {
            float f = SonosApplication.getInstance().getResources().getDisplayMetrics().density;
            rectf2 = new RectF(0.0F, 0.0F, f * rectf1.width(), f * rectf1.height());
        } else
        {
            rectf2 = getScaledRect(rectf, rectf1);
        }
        matrix = new Matrix();
        matrix.setRectToRect(rectf1, rectf2, android.graphics.Matrix.ScaleToFit.CENTER);
        SLog.i(LOG_TAG, (new StringBuilder()).append("Creating Svg of Size: ").append(rectf2).toString());
        bitmap = createBitmap((int)rectf2.width(), (int)rectf2.height(), android.graphics.Bitmap.Config.ARGB_8888);
        bitmap.setDensity(SonosApplication.getInstance().getResources().getDisplayMetrics().densityDpi);
        canvas = new Canvas(bitmap);
        canvas.setMatrix(matrix);
        canvas.drawPicture(picture);
        return bitmap;
    }

    public static int getDominantColor(Bitmap bitmap, float af[])
    {
        long l;
        Bitmap bitmap1;
        boolean flag;
        int k;
        l = System.currentTimeMillis();
        bitmap1 = Bitmap.createScaledBitmap(bitmap, 120, 120, false);
        if(af != null && (Float.compare(0.0F, af[1]) != 0 || Float.compare(0.0F, af[2]) != 0))
            flag = true;
        else
            flag = false;
        if(bitmap1 != null) goto _L2; else goto _L1
_L1:
        k = Color.argb(255, 255, 255, 255);
_L4:
        return k;
_L2:
        int ai[] = new int[36];
        int i = -1;
        float af1[] = new float[36];
        float af2[] = new float[36];
        float af3[] = new float[36];
        float af4[] = new float[3];
        int j = 0;
        do
        {
            if(j >= bitmap1.getHeight())
                break;
            int i1 = 0;
            while(i1 < bitmap1.getWidth()) 
            {
                int j1 = bitmap1.getPixel(i1, j);
                if(Color.alpha(j1) >= 128)
                {
                    Color.colorToHSV(j1, af4);
                    if(!flag || af4[1] > af[1] && af4[2] > af[2])
                    {
                        int k1 = (int)Math.floor(af4[0] / 10F);
                        af1[k1] = af1[k1] + af4[0];
                        af2[k1] = af2[k1] + af4[1];
                        af3[k1] = af3[k1] + af4[2];
                        ai[k1] = 1 + ai[k1];
                        if(i < 0 || ai[k1] > ai[i])
                            i = k1;
                    }
                }
                i1++;
            }
            j++;
        } while(true);
        if(i < 0)
        {
            k = Color.argb(255, 255, 255, 255);
        } else
        {
            af4[0] = af1[i] / (float)ai[i];
            af4[1] = af2[i] / (float)ai[i];
            af4[2] = af3[i] / (float)ai[i];
            SLog.e(LOG_TAG, (new StringBuilder()).append("It took ").append(System.currentTimeMillis() - l).append("ms to complete.").toString());
            k = Color.HSVToColor(af4);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Matrix getDrawMatrix(android.widget.ImageView.ScaleType scaletype, int i, int j, int k, int l)
    {
        Matrix matrix = new Matrix();
        if((i < 0 || k == i) && (j < 0 || l == j))
            matrix = null;
        else
        if(android.widget.ImageView.ScaleType.CENTER == scaletype)
            matrix.setTranslate((int)(0.5F + 0.5F * (float)(k - i)), (int)(0.5F + 0.5F * (float)(l - j)));
        else
        if(android.widget.ImageView.ScaleType.CENTER_CROP == scaletype)
        {
            float f3 = 0.0F;
            float f4 = 0.0F;
            float f5;
            if(i * l > k * j)
            {
                f5 = (float)l / (float)j;
                f3 = 0.5F * ((float)k - f5 * (float)i);
            } else
            {
                f5 = (float)k / (float)i;
                f4 = 0.5F * ((float)l - f5 * (float)j);
            }
            matrix.setScale(f5, f5);
            matrix.postTranslate((int)(f3 + 0.5F), (int)(f4 + 0.5F));
        } else
        if(android.widget.ImageView.ScaleType.CENTER_INSIDE == scaletype)
        {
            float f;
            float f1;
            float f2;
            if(i <= k && j <= l)
                f = 1.0F;
            else
                f = Math.min((float)k / (float)i, (float)l / (float)j);
            f1 = (int)(0.5F + 0.5F * ((float)k - f * (float)i));
            f2 = (int)(0.5F + 0.5F * ((float)l - f * (float)j));
            matrix.setScale(f, f);
            matrix.postTranslate(f1, f2);
        } else
        {
            sourceRect.set(0.0F, 0.0F, i, j);
            destRect.set(0.0F, 0.0F, k, l);
            matrix.setRectToRect(sourceRect, destRect, scaleTypeToScaleToFit(scaletype));
        }
        return matrix;
    }

    protected static RectF getScaledRect(RectF rectf, RectF rectf1)
    {
        float f = rectf1.width() / rectf1.height();
        float f1;
        if(rectf.width() / rectf.height() < f)
            f1 = rectf.width() / rectf1.width();
        else
            f1 = rectf.height() / rectf1.height();
        return new RectF(0.0F, 0.0F, f1 * rectf1.width(), f1 * rectf1.height());
    }

    public static Bitmap getSvgFromResource(int i, int j, int k)
    {
        return getSvgFromResource(SonosApplication.getInstance().getResources(), i, j, k);
    }

    public static Bitmap getSvgFromResource(Resources resources, int i, int j, int k)
    {
        RectF rectf;
        SVGResKey svgreskey;
        Bitmap bitmap;
        rectf = null;
        svgreskey = new SVGResKey(i, j, k);
        bitmap = (Bitmap)svgImages.get(svgreskey);
        if(bitmap != null)
            break MISSING_BLOCK_LABEL_165;
        SVG svg = SVG.getFromResource(resources, i);
        if(j != 0 && k != 0) goto _L2; else goto _L1
_L1:
        Bitmap bitmap1 = getBitmap(svg, rectf);
        bitmap = bitmap1;
_L3:
        if(bitmap != null)
        {
            SLog.i(LOG_TAG, (new StringBuilder()).append("SVG Created!!! width: ").append(j).append(" Height: ").append(k).append(" total: ").append(svgImages.size()).toString());
            svgImages.put(svgreskey, bitmap);
        }
_L4:
        return bitmap;
_L2:
        rectf = new RectF(0.0F, 0.0F, j, k);
          goto _L1
        SVGParseException svgparseexception;
        svgparseexception;
        SLog.e(LOG_TAG, "Error parsing SVG resource", svgparseexception);
          goto _L3
        SLog.i(LOG_TAG, (new StringBuilder()).append("SVG Found!!!   width: ").append(j).append(" Height: ").append(k).append(" total: ").append(svgImages.size()).toString());
          goto _L4
    }

    public static native boolean isARMv7();

    public static boolean isRawImage(Resources resources, int i)
    {
        return "raw".equals(resources.getResourceTypeName(i));
    }

    private static void onOutOfMemory(OutOfMemoryError outofmemoryerror)
    {
        SLog.e(LOG_TAG, "OutOfMemoryError!  Attempting to free resources.", outofmemoryerror);
        AlbumArtSize.onLowMemory();
        SCLibManager.onLowMemory();
        System.gc();
        System.gc();
    }

    public static RectF scaleDrawable(Drawable drawable, Bitmap bitmap, android.widget.ImageView.ScaleType scaletype)
    {
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        int k = bitmap.getWidth();
        int l = bitmap.getHeight();
        Matrix matrix;
        Canvas canvas;
        RectF rectf;
        if(i <= 0 || j <= 0 || android.widget.ImageView.ScaleType.FIT_XY == scaletype)
        {
            bounds.set(0, 0, k, l);
            matrix = new Matrix();
        } else
        {
            bounds.set(0, 0, i, j);
            matrix = getDrawMatrix(scaletype, i, j, k, l);
        }
        canvas = new Canvas(bitmap);
        canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        canvas.setMatrix(matrix);
        drawable.setBounds(bounds);
        drawable.draw(canvas);
        rectf = new RectF(bounds);
        matrix.mapRect(rectf, rectf);
        return rectf;
    }

    private static android.graphics.Matrix.ScaleToFit scaleTypeToScaleToFit(android.widget.ImageView.ScaleType scaletype)
    {
        class _cls1
        {

            static final int $SwitchMap$android$widget$ImageView$ScaleType[];

            static 
            {
                $SwitchMap$android$widget$ImageView$ScaleType = new int[android.widget.ImageView.ScaleType.values().length];
                NoSuchFieldError nosuchfielderror3;
                try
                {
                    $SwitchMap$android$widget$ImageView$ScaleType[android.widget.ImageView.ScaleType.FIT_CENTER.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$android$widget$ImageView$ScaleType[android.widget.ImageView.ScaleType.FIT_END.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$widget$ImageView$ScaleType[android.widget.ImageView.ScaleType.FIT_START.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$android$widget$ImageView$ScaleType[android.widget.ImageView.ScaleType.FIT_XY.ordinal()] = 4;
_L2:
                return;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.android.widget.ImageView.ScaleType[scaletype.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 51
    //                   2 57
    //                   3 64
    //                   4 71;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new RuntimeException("Invalid Scale type!");
_L2:
        android.graphics.Matrix.ScaleToFit scaletofit = android.graphics.Matrix.ScaleToFit.CENTER;
_L7:
        return scaletofit;
_L3:
        scaletofit = android.graphics.Matrix.ScaleToFit.END;
        continue; /* Loop/switch isn't completed */
_L4:
        scaletofit = android.graphics.Matrix.ScaleToFit.START;
        continue; /* Loop/switch isn't completed */
_L5:
        scaletofit = android.graphics.Matrix.ScaleToFit.FILL;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static final String LOG_TAG = com/sonos/acr/util/ImageUtils.getSimpleName();
    public static final float MAX_BLUR_RADIUS;
    private static final Rect bounds = new Rect();
    private static final RectF destRect = new RectF();
    private static final RectF sourceRect = new RectF();
    private static final WeakMap svgImages = new WeakMap();

    static 
    {
        float f;
        if(android.os.Build.VERSION.SDK_INT >= 11)
            f = 25F;
        else
            f = 10F;
        MAX_BLUR_RADIUS = f;
    }
}
