// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.volume.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;
import com.sonos.acr.util.ViewUtils;

// Referenced classes of package com.sonos.acr.volume.views:
//            VolumeSliderView

public class QueueVolumeSliderView extends VolumeSliderView
{

    public QueueVolumeSliderView(Context context)
    {
        this(context, null);
    }

    public QueueVolumeSliderView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public QueueVolumeSliderView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        queueButton = (ImageButton)findViewById(0x7f0a01e0);
    }

    protected int getDefaultLayoutId()
    {
        return 0x7f0300a5;
    }

    protected boolean willHandleMotionEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(!ViewUtils.isMotionEventInView(motionevent, queueButton))
            flag = true;
        else
            flag = false;
        return flag;
    }

    ImageButton queueButton;
}
