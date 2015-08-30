// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.sonos.acr.util.SLog;

public class ReflectiveDrawable extends Drawable
{
    public static class EffectType
    {

        public static float SKEW_MATRIX_LARGE[];
        public static float SKEW_MATRIX_NORMAL[];
        public static RectF SKEW_RECT_LARGE = new RectF(0.0F, 0.0F, 246F, 246F);
        public static RectF SKEW_RECT_NORMAL = new RectF(0.0F, 0.0F, 164F, 164F);
        public RectF referenceRect;
        public int reflectionAlpha;
        public float reflectionFactor;
        public Matrix skewMatrix;

        static 
        {
            float af[] = new float[9];
            af[0] = 1.03623F;
            af[1] = 0.0F;
            af[2] = 0.0F;
            af[3] = 0.10145F;
            af[4] = 1.0F;
            af[5] = 0.0F;
            af[6] = 0.00115F;
            af[7] = 0.0F;
            af[8] = 1.0F;
            SKEW_MATRIX_NORMAL = af;
            float af1[] = new float[9];
            af1[0] = 1.05941F;
            af1[1] = 0.0F;
            af1[2] = 0.0F;
            af1[3] = 0.10396F;
            af1[4] = 1.0F;
            af1[5] = 0.0F;
            af1[6] = 0.00089F;
            af1[7] = 0.0F;
            af1[8] = 1.0F;
            SKEW_MATRIX_LARGE = af1;
        }

        EffectType(float af[], RectF rectf)
        {
            skewMatrix = null;
            referenceRect = null;
            reflectionAlpha = 255;
            reflectionFactor = 1.0F;
            if(af != null)
            {
                skewMatrix = new Matrix();
                skewMatrix.setValues(af);
            }
            referenceRect = rectf;
        }

        EffectType(float af[], RectF rectf, int i, float f)
        {
            skewMatrix = null;
            referenceRect = null;
            reflectionAlpha = 255;
            reflectionFactor = 1.0F;
            if(af != null)
            {
                skewMatrix = new Matrix();
                skewMatrix.setValues(af);
            }
            referenceRect = rectf;
            reflectionAlpha = i;
            reflectionFactor = f + 1.0F;
        }
    }


    public ReflectiveDrawable(Drawable drawable, EffectType effecttype)
    {
        myDrawable = drawable.mutate();
        effectType = effecttype;
        if(myDrawable instanceof BitmapDrawable)
        {
            BitmapDrawable bitmapdrawable = (BitmapDrawable)myDrawable;
            bitmapdrawable.setFilterBitmap(true);
            bitmapdrawable.setAntiAlias(true);
        }
        buildTransformationMatrix();
    }

    private void buildTransformationMatrix()
    {
        if(myDrawable != null)
        {
            reflectedRect = new RectF(0.0F, 0.0F, myDrawable.getIntrinsicWidth(), (float)myDrawable.getIntrinsicHeight() * effectType.reflectionFactor);
            SLog.d("ReflectiveDrawable", (new StringBuilder()).append("Reflected Rect: ").append(reflectedRect).toString());
            preTrasformationMatrix = new Matrix();
            if(effectType.skewMatrix == null)
            {
                intrinsicRect = reflectedRect;
            } else
            {
                RectF rectf = new RectF(0.0F, 0.0F, effectType.referenceRect.width(), effectType.referenceRect.height() * effectType.reflectionFactor);
                preTrasformationMatrix.setRectToRect(reflectedRect, rectf, android.graphics.Matrix.ScaleToFit.START);
                preTrasformationMatrix.postConcat(effectType.skewMatrix);
                RectF rectf1 = new RectF();
                preTrasformationMatrix.mapRect(rectf1, reflectedRect);
                intrinsicRect = new RectF(0.0F, 0.0F, rectf1.width(), rectf1.height());
            }
        }
    }

    public void draw(Canvas canvas)
    {
        Rect rect = new Rect(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        canvas.concat(preTrasformationMatrix);
        myDrawable.setBounds(rect);
        myDrawable.draw(canvas);
        RectF rectf = new RectF(reflectedRect);
        canvas.saveLayer(rectf, null, 31);
        canvas.clipRect(rectf);
        canvas.save();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0F, -1F);
        matrix.postTranslate(0.0F, 2 * rect.height());
        canvas.concat(matrix);
        myDrawable.draw(canvas);
        canvas.restore();
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0F, rect.height(), 0.0F, reflectedRect.height(), effectType.reflectionAlpha, 0xffffff, android.graphics.Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
        canvas.drawRect(-5F, rect.height(), 10 + rect.width(), 2.0F + reflectedRect.height(), paint);
        canvas.restore();
    }

    public Drawable getDrawable()
    {
        return myDrawable;
    }

    public int getIntrinsicHeight()
    {
        int i;
        if(intrinsicRect != null)
            i = (int)Math.ceil(intrinsicRect.height());
        else
            i = -1;
        return i;
    }

    public int getIntrinsicWidth()
    {
        int i;
        if(intrinsicRect != null)
            i = (int)Math.ceil(intrinsicRect.width());
        else
            i = -1;
        return i;
    }

    public int getOpacity()
    {
        return -3;
    }

    public boolean getPadding(Rect rect)
    {
        return super.getPadding(rect);
    }

    public void setAlpha(int i)
    {
        myDrawable.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        myDrawable.setColorFilter(colorfilter);
    }

    public final String TAG = "ReflectiveDrawable";
    EffectType effectType;
    private RectF intrinsicRect;
    private Drawable myDrawable;
    private Matrix preTrasformationMatrix;
    private RectF reflectedRect;
}
