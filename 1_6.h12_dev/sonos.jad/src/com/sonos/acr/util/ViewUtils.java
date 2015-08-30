// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.*;
import android.view.animation.*;
import android.widget.TextView;
import com.sonos.acr.application.SonosApplication;
import java.util.ArrayList;

public class ViewUtils
{

    public ViewUtils()
    {
    }

    public static void addAllChildViews(ViewGroup viewgroup, ArrayList arraylist, Class class1)
    {
        int i = viewgroup.getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view = viewgroup.getChildAt(j);
            if(!class1.isInstance(view))
                continue;
            arraylist.add(view);
            if(view instanceof ViewGroup)
                addAllChildViews((ViewGroup)view, arraylist, class1);
        }

    }

    public static void copyLayoutSettings(View view, View view1)
    {
        if(view != null && view1 != null)
        {
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
            if(marginlayoutparams != null)
            {
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams1 = (android.view.ViewGroup.MarginLayoutParams)view1.getLayoutParams();
                marginlayoutparams1.width = marginlayoutparams.width;
                marginlayoutparams1.height = marginlayoutparams.height;
            }
            view1.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void fixTypeFace(TextView textview)
    {
        Typeface typeface = textview.getTypeface();
        if(typeface != null)
        {
            if(typeface.isBold() && typeface.isItalic())
                textview.setTypeface(SONOS_MEDIUM_ITALIC, 0);
            else
            if(typeface.isItalic())
                textview.setTypeface(SONOS_ITALIC, 0);
            else
            if(typeface.isBold())
                textview.setTypeface(SONOS_MEDIUM, 0);
            else
                textview.setTypeface(SONOS_REGULAR, 0);
        } else
        {
            textview.setTypeface(SONOS_REGULAR, 0);
        }
    }

    public static ArrayList getAllChildViews(ViewGroup viewgroup)
    {
        ArrayList arraylist = new ArrayList();
        addAllChildViews(viewgroup, arraylist, android/view/View);
        return arraylist;
    }

    public static Point getDisplaySize(Activity activity)
    {
        return getDisplaySize(activity.getWindowManager());
    }

    public static Point getDisplaySize(WindowManager windowmanager)
    {
        Display display = windowmanager.getDefaultDisplay();
        Point point = new Point();
        if(android.os.Build.VERSION.SDK_INT < 13)
        {
            point.x = display.getWidth();
            point.y = display.getHeight();
        } else
        {
            display.getSize(point);
        }
        return point;
    }

    public static int[] getLocationInWindow(View view)
    {
        view.getLocationInWindow(locationInWindow1);
        return locationInWindow1;
    }

    public static void getPositionedRelativeViewDistance(View view, View view1, int ai[])
    {
        view.getLocationInWindow(locationInWindow1);
        int ai1[] = locationInWindow1;
        ai1[0] = (int)((float)ai1[0] - getTranslationX(view));
        int ai2[] = locationInWindow1;
        ai2[1] = (int)((float)ai2[1] - getTranslationY(view));
        view1.getLocationInWindow(locationInWindow2);
        int ai3[] = locationInWindow2;
        ai3[0] = (int)((float)ai3[0] - getTranslationX(view1));
        int ai4[] = locationInWindow2;
        ai4[1] = (int)((float)ai4[1] - getTranslationY(view1));
        ai[0] = locationInWindow2[0] - locationInWindow1[0];
        ai[1] = locationInWindow2[1] - locationInWindow1[1];
    }

    public static void getRelativeViewDistance(View view, View view1, int ai[])
    {
        view.getLocationInWindow(locationInWindow1);
        view1.getLocationInWindow(locationInWindow2);
        ai[0] = locationInWindow2[0] - locationInWindow1[0];
        ai[1] = locationInWindow2[1] - locationInWindow1[1];
    }

    public static float getTranslationX(View view)
    {
        float f;
        if(android.os.Build.VERSION.SDK_INT < 11)
            f = 0.0F;
        else
            f = view.getTranslationX();
        return f;
    }

    public static float getTranslationY(View view)
    {
        float f;
        if(android.os.Build.VERSION.SDK_INT < 11)
            f = 0.0F;
        else
            f = view.getTranslationY();
        return f;
    }

    public static boolean isMotionEventInView(MotionEvent motionevent, View view)
    {
        boolean flag = true;
        view.getLocationOnScreen(locationInWindow1);
        float f = motionevent.getRawX();
        float f1 = motionevent.getRawY();
        if(f <= (float)locationInWindow1[0] || f >= (float)(locationInWindow1[0] + view.getWidth()) || f1 <= (float)locationInWindow1[flag] || f1 >= (float)((locationInWindow1[flag] + view.getHeight()) - view.getPaddingBottom()))
            flag = false;
        return flag;
    }

    public static void setAlpha(View view, float f)
    {
        if(view.getVisibility() == 0)
            if(android.os.Build.VERSION.SDK_INT < 11)
            {
                AlphaAnimation alphaanimation = new AlphaAnimation(f, f);
                alphaanimation.setDuration(0L);
                alphaanimation.setFillAfter(true);
                view.startAnimation(alphaanimation);
            } else
            {
                view.setAlpha(f);
            }
    }

    public static void setPaddingBottom(View view, int i)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if(layoutparams != null && layoutparams.height > 0)
            layoutparams.height = i + layoutparams.height;
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i + view.getPaddingBottom());
    }

    public static void setPaddingTop(View view, int i)
    {
        view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setRotation(View view, float f)
    {
        if(android.os.Build.VERSION.SDK_INT < 11)
        {
            view.getAnimation();
            RotateAnimation rotateanimation = new RotateAnimation(f, f);
            rotateanimation.setFillAfter(true);
            rotateanimation.setDuration(0L);
            view.startAnimation(rotateanimation);
        } else
        {
            view.setRotation(f);
        }
    }

    public static void setTranslationX(View view, float f)
    {
        if(android.os.Build.VERSION.SDK_INT < 11)
        {
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, f, f);
            translateanimation.setFillAfter(true);
            translateanimation.setDuration(0L);
            view.startAnimation(translateanimation);
        } else
        {
            view.setTranslationX(f);
        }
    }

    public static void setTranslationY(View view, float f)
    {
        if(android.os.Build.VERSION.SDK_INT < 11)
        {
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, f, f);
            translateanimation.setFillAfter(true);
            translateanimation.setDuration(0L);
            view.startAnimation(translateanimation);
        } else
        {
            view.setTranslationY(f);
        }
    }

    public static final String LOG_TAG = com/sonos/acr/util/ViewUtils.getSimpleName();
    public static Typeface SONOS_ITALIC;
    public static Typeface SONOS_LIGHT;
    public static Typeface SONOS_MEDIUM;
    public static Typeface SONOS_MEDIUM_ITALIC;
    public static Typeface SONOS_REGULAR;
    static final int locationInWindow1[];
    static final int locationInWindow2[];

    static 
    {
        SonosApplication sonosapplication = SonosApplication.getInstance();
        if(sonosapplication != null)
        {
            android.content.res.AssetManager assetmanager = sonosapplication.getAssets();
            SONOS_REGULAR = Typeface.createFromAsset(assetmanager, "fonts/sonoshandbookpro-regular.otf");
            SONOS_ITALIC = Typeface.createFromAsset(assetmanager, "fonts/sonoshandbookpro-italic.otf");
            SONOS_LIGHT = Typeface.createFromAsset(assetmanager, "fonts/sonoshandbookpro-light.otf");
            SONOS_MEDIUM = Typeface.createFromAsset(assetmanager, "fonts/sonoshandbookpro-medium.otf");
            SONOS_MEDIUM_ITALIC = Typeface.createFromAsset(assetmanager, "fonts/sonoshandbookpro-mediumitalic.otf");
        }
        int ai[] = new int[2];
        ai[0] = 0;
        ai[1] = 0;
        locationInWindow1 = ai;
        int ai1[] = new int[2];
        ai1[0] = 0;
        ai1[1] = 0;
        locationInWindow2 = ai1;
    }
}
