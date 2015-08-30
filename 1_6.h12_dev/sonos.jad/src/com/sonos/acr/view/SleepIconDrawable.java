// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.Date;

public class SleepIconDrawable extends Drawable
{
    public static interface SleepTimerChangedListener
    {

        public abstract void onSleepTimerChanged(boolean flag);
    }


    public SleepIconDrawable(Context context, boolean flag)
    {
        paint = new Paint();
        timeText = null;
        remainingSeconds = 0.0D;
        updater = new Runnable() {

            public void run()
            {
                SLog.d(SleepIconDrawable.LOG_TAG, "run() Called");
                updateTime();
                invalidateSelf();
            }

            final SleepIconDrawable this$0;

            
            {
                this$0 = SleepIconDrawable.this;
                super();
            }
        }
;
        Resources resources = context.getResources();
        bubble = resources.getDrawable(0x7f020051);
        xOffset = resources.getDimension(0x7f090029);
        yOffset = resources.getDimension(0x7f09002b);
        hPadding = resources.getDimension(0x7f09002a);
        vPadding = resources.getDimension(0x7f09002c);
        paint.setColor(-1);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint.setTextSize(resources.getDimensionPixelSize(0x7f090062));
        paint.setFakeBoldText(false);
        paint.setAntiAlias(true);
        if(!flag)
            paint.setTypeface(ViewUtils.SONOS_REGULAR);
        paint.setSubpixelText(true);
        updateTime();
    }

    private float getBubbleHeight()
    {
        float f;
        if(timeText != null)
            f = -paint.getFontMetrics().ascent + 2.0F * vPadding;
        else
            f = 0.0F;
        return f;
    }

    private float getBubbleWidth()
    {
        float f;
        if(timeText != null)
            f = paint.measureText(timeText) + 2.0F * hPadding;
        else
            f = 0.0F;
        return f;
    }

    private String getCountFromTime(int i, int j)
    {
        String s1;
        if(i > 0 || j > 0)
        {
            if(i >= 1)
            {
                String s2 = SonosApplication.getInstance().getResources().getString(0x7f0c00e9);
                Object aobj2[] = new Object[1];
                aobj2[0] = Integer.toString(i);
                s1 = String.format(s2, aobj2);
            } else
            {
                String s = SonosApplication.getInstance().getResources().getString(0x7f0c00ea);
                Object aobj[] = new Object[1];
                Object aobj1[] = new Object[1];
                aobj1[0] = Integer.valueOf(j);
                aobj[0] = String.format("%02d", aobj1);
                s1 = String.format(s, aobj);
            }
        } else
        {
            s1 = null;
        }
        return s1;
    }

    public void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        if(timeText != null)
        {
            RectF rectf = new RectF((float)rect.left + xOffset, (float)rect.top + yOffset, (float)rect.right - xOffset, (float)rect.bottom - yOffset);
            bubble.setBounds((int)rectf.left, (int)rectf.top, (int)rectf.right, (int)rectf.bottom);
            bubble.draw(canvas);
            Rect rect1 = new Rect();
            paint.getTextBounds(timeText, 0, timeText.length(), rect1);
            canvas.drawText(timeText, rectf.centerX(), rectf.centerY() - rect1.exactCenterY(), paint);
        }
        if(timeText != null)
        {
            unscheduleSelf(updater);
            scheduleSelf(updater, 1000L + SystemClock.uptimeMillis());
        } else
        {
            updateTime();
        }
    }

    public int getIntrinsicHeight()
    {
        int i = (int)getBubbleHeight();
        if(i > 0)
            i = (int)((float)i + 2.0F * yOffset);
        return i;
    }

    public int getIntrinsicWidth()
    {
        int i = (int)getBubbleWidth();
        if(i > 0)
            i = (int)((float)i + 2.0F * xOffset);
        return i;
    }

    public int getOpacity()
    {
        return 0;
    }

    public int[] getState()
    {
        return super.getState();
    }

    public boolean isStateful()
    {
        return true;
    }

    public void pollTime()
    {
        SLog.d(LOG_TAG, "Polling sleep timer...");
        SCINowPlayingSleepTimer scinowplayingsleeptimer = LibraryUtils.getNowPlayingSleepTimer();
        if(scinowplayingsleeptimer != null)
        {
            final SCIOpAVTransportGetRemainingSleepTimerDuration op = scinowplayingsleeptimer.createGetRemainingSleepTimerDurationOp();
            if(op != null)
                op._start(new SCIOpCBSwigBase() {

                    public void _operationComplete(long l, int i)
                    {
                        if(i != 0) goto _L2; else goto _L1
_L1:
                        String s = op.getRemainingSleepTimerDuration();
                        if(s == null || s.length() != 8) goto _L4; else goto _L3
_L3:
                        String as[] = s.split("\\:");
                        int j = Integer.valueOf(as[0]).intValue();
                        int k = Integer.valueOf(as[1]).intValue();
                        int i1 = Integer.valueOf(as[2]).intValue();
                        remainingSeconds = (double)(i1 + (j * 3600 + k * 60));
                        lastUpdated = new Date();
                        int j1 = k + j * 60;
                        if(j1 > 0 && i1 > 0)
                            j1++;
                        if(j1 == 1 && i1 == 0)
                        {
                            j1 = 0;
                            i1 = 60;
                        }
                        timeText = getCountFromTime(j1, i1);
                        if(listener != null)
                            listener.onSleepTimerChanged(true);
_L2:
                        invalidateSelf();
                        return;
_L4:
                        timeText = null;
                        lastUpdated = null;
                        if(listener != null)
                            listener.onSleepTimerChanged(false);
                        if(true) goto _L2; else goto _L5
_L5:
                    }

                    final SleepIconDrawable this$0;
                    final SCIOpAVTransportGetRemainingSleepTimerDuration val$op;

            
            {
                this$0 = SleepIconDrawable.this;
                op = sciopavtransportgetremainingsleeptimerduration;
                super();
            }
                }
);
        }
    }

    public void setAlpha(int i)
    {
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

    public void setSleepStateChangedListener(SleepTimerChangedListener sleeptimerchangedlistener)
    {
        listener = sleeptimerchangedlistener;
    }

    public void timeChangeRepaint()
    {
        if(timeText != null)
        {
            unscheduleSelf(updater);
            scheduleSelf(updater, 50L + SystemClock.uptimeMillis());
        }
    }

    public void updateTime()
    {
        if(lastUpdated == null) goto _L2; else goto _L1
_L1:
        Date date = new Date();
        double d = (double)(date.getTime() - lastUpdated.getTime()) / 1000D;
        remainingSeconds = remainingSeconds - d;
        lastUpdated = date;
        int i = (int)(remainingSeconds / 60D);
        if((int)remainingSeconds == 60)
        {
            timeText = getCountFromTime(0, 60);
        } else
        {
            if(i > 0)
                i++;
            timeText = getCountFromTime(i, (int)remainingSeconds);
        }
_L4:
        return;
_L2:
        timeText = null;
        if(listener != null)
            listener.onSleepTimerChanged(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final String LOG_TAG = com/sonos/acr/view/SleepIconDrawable.getSimpleName();
    private Drawable bubble;
    private float hPadding;
    private Date lastUpdated;
    SleepTimerChangedListener listener;
    private Paint paint;
    private double remainingSeconds;
    private String timeText;
    Runnable updater;
    private float vPadding;
    private float xOffset;
    private float yOffset;



/*
    static double access$002(SleepIconDrawable sleepicondrawable, double d)
    {
        sleepicondrawable.remainingSeconds = d;
        return d;
    }

*/


/*
    static Date access$102(SleepIconDrawable sleepicondrawable, Date date)
    {
        sleepicondrawable.lastUpdated = date;
        return date;
    }

*/


/*
    static String access$202(SleepIconDrawable sleepicondrawable, String s)
    {
        sleepicondrawable.timeText = s;
        return s;
    }

*/

}
