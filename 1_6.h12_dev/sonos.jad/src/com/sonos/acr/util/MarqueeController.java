// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import com.sonos.acr.view.MarqueeView;
import java.util.*;

public class MarqueeController
    implements com.sonos.acr.view.MarqueeView.MarqueeListener
{

    public MarqueeController()
    {
        index = -1;
        started = false;
        marquees = new ArrayList();
    }

    public MarqueeController(int i)
    {
        index = -1;
        started = false;
        marquees = new ArrayList(Arrays.asList(new MarqueeView[i]));
    }

    private MarqueeView getCurrentMarquee()
    {
        MarqueeView marqueeview;
        if(index != -1)
            marqueeview = (MarqueeView)marquees.get(index);
        else
            marqueeview = null;
        return marqueeview;
    }

    private int getNextAnimatableMarquee(int i)
    {
        int k;
        int l;
        if(i == -1)
            break MISSING_BLOCK_LABEL_78;
        k = marquees.size();
        l = 0;
_L3:
        int j;
        MarqueeView marqueeview;
        if(l >= marquees.size())
            break MISSING_BLOCK_LABEL_78;
        j = (i + l) % k;
        marqueeview = (MarqueeView)marquees.get(j);
        if(marqueeview == null || !marqueeview.canAnimate() || !marqueeview.isReallyVisible()) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        l++;
          goto _L3
        j = -1;
          goto _L1
    }

    private void startNextMarquee()
    {
        if(marquees.size() > 0)
        {
            index = getNextAnimatableMarquee((1 + index) % marquees.size());
            if(index != -1)
                ((MarqueeView)marquees.get(index)).startMarquee();
        }
    }

    public void addMarquee(MarqueeView marqueeview)
    {
        if(marqueeview != null)
        {
            marqueeview.setMarqueeListener(this);
            marquees.add(marqueeview);
            if(started && index == -1)
                startNextMarquee();
        }
    }

    public void cleanupForExit()
    {
        stop();
        clearMarquees();
    }

    public void clearMarquees()
    {
        index = -1;
        Iterator iterator = marquees.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            MarqueeView marqueeview = (MarqueeView)iterator.next();
            if(marqueeview != null)
                marqueeview.setMarqueeListener(null);
        } while(true);
        marquees.clear();
    }

    public void onAnimationStateChange(MarqueeView marqueeview)
    {
        reset();
    }

    public void onComplete(MarqueeView marqueeview)
    {
        if(marqueeview == getCurrentMarquee())
            startNextMarquee();
    }

    public void onStart(MarqueeView marqueeview)
    {
    }

    public void onTextChange(MarqueeView marqueeview)
    {
        reset();
    }

    public void reset()
    {
        if(started)
        {
            stop();
            start();
        }
    }

    public void setMarquee(int i, MarqueeView marqueeview)
    {
        if(marqueeview != null)
        {
            marqueeview.setMarqueeListener(this);
            if(i >= 0 && i < marquees.size())
                marquees.set(i, marqueeview);
            else
                marquees.add(marqueeview);
            if(started && index == -1)
                startNextMarquee();
        }
    }

    public void start()
    {
        if(!started)
        {
            started = true;
            startNextMarquee();
        }
    }

    public void stop()
    {
        index = -1;
        Iterator iterator = marquees.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            MarqueeView marqueeview = (MarqueeView)iterator.next();
            if(marqueeview != null)
                marqueeview.reset();
        } while(true);
        started = false;
    }

    private int index;
    private final ArrayList marquees;
    private boolean started;
}
