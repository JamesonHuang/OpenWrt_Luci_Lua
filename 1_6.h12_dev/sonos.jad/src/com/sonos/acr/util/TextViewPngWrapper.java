// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.acr.util:
//            ImageUtils, DbgProp, SLog

class TextViewPngWrapper
    implements TextWatcher, android.view.ViewTreeObserver.OnPreDrawListener, android.view.ViewTreeObserver.OnGlobalLayoutListener
{

    TextViewPngWrapper(TextView textview)
    {
        dumpPng = true;
        drawId = 0;
        int i = viewNumber;
        viewNumber = i + 1;
        viewId = i;
        view = textview;
        textview.addTextChangedListener(this);
        registerForPredraw();
    }

    private void registerForPredraw()
    {
        ViewTreeObserver viewtreeobserver = view.getViewTreeObserver();
        if(viewtreeobserver.isAlive())
        {
            viewtreeobserver.removeOnPreDrawListener(this);
            viewtreeobserver.addOnPreDrawListener(this);
        }
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public boolean callDraw(Canvas canvas)
    {
        boolean flag = true;
        try
        {
            Class aclass[] = new Class[1];
            aclass[0] = android/graphics/Canvas;
            Method method = android/widget/TextView.getDeclaredMethod("onDraw", aclass);
            method.setAccessible(true);
            TextView textview = view;
            Object aobj[] = new Object[1];
            aobj[0] = canvas;
            method.invoke(textview, aobj);
        }
        catch(Exception exception)
        {
            SLog.e(LOG_TAG, "Exception calling draw", exception);
            flag = false;
        }
        return flag;
    }

    String getFileName()
    {
        CharSequence charsequence = view.getText();
        String s;
        if(charsequence != null && charsequence.length() > 0)
        {
            String s1 = (new StringBuilder()).append(viewId).append("_").append(drawId).append("__").append(charsequence.toString().substring(0, Math.min(20, charsequence.length())).trim().replaceAll("[:\\\\/*?|<>\\r\\n]", "#")).toString();
            s = (new StringBuilder()).append(s1.replace(" ", "_")).append(".png").toString();
            SLog.d(LOG_TAG, (new StringBuilder()).append("File name for image: ").append(s).toString());
        } else
        {
            s = null;
        }
        return s;
    }

    public void onGlobalLayout()
    {
        registerForPredraw();
        dumpPng = true;
    }

    public boolean onPreDraw()
    {
        if(dumpPng && view.getWidth() > 0 && view.getHeight() > 0 && view.getVisibility() == 0)
        {
            String s = getFileName();
            if(s != null)
            {
                android.graphics.drawable.Drawable drawable = view.getBackground();
                view.setBackgroundDrawable(null);
                view.setDrawingCacheEnabled(true);
                Canvas canvas = new Canvas(b);
                view.onPreDraw();
                view.draw(canvas);
                saveBitmap(view.getDrawingCache(), s);
                view.setDrawingCacheEnabled(false);
                view.setBackgroundDrawable(drawable);
                dumpPng = false;
            }
        }
        return true;
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        registerForPredraw();
        dumpPng = true;
    }

    public void saveBitmap(Bitmap bitmap, String s)
    {
        FileOutputStream fileoutputstream = null;
        File file = new File(textViewPngDir, s);
        if(!file.createNewFile()) goto _L2; else goto _L1
_L1:
        FileOutputStream fileoutputstream1 = new FileOutputStream(file);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fileoutputstream1);
        drawId = 1 + drawId;
        fileoutputstream = fileoutputstream1;
_L3:
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_67;
        fileoutputstream.close();
_L4:
        return;
_L2:
        SLog.e(LOG_TAG, "File already exists, not writing");
          goto _L3
        Exception exception2;
        exception2;
_L6:
        SLog.e(LOG_TAG, (new StringBuilder()).append("Error writing file: ").append(s).toString(), exception2);
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(Exception exception3) { }
          goto _L4
        Exception exception;
        exception;
_L5:
        Exception exception4;
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(Exception exception1) { }
        throw exception;
        exception4;
          goto _L4
        exception;
        fileoutputstream = fileoutputstream1;
          goto _L5
        exception2;
        fileoutputstream = fileoutputstream1;
          goto _L6
    }

    private static final String LOG_TAG = com/sonos/acr/util/TextViewPngWrapper.getSimpleName();
    static Bitmap b;
    private static final File textViewPngDir;
    static int viewNumber = 0;
    int drawId;
    boolean dumpPng;
    final TextView view;
    final int viewId;

    static 
    {
        b = ImageUtils.createBitmap(1, 1, android.graphics.Bitmap.Config.ARGB_8888);
        textViewPngDir = new File(DbgProp.getSonosDebugDir(), (new StringBuilder()).append("TextViewPngs_").append(System.currentTimeMillis()).toString());
        textViewPngDir.mkdir();
    }
}
