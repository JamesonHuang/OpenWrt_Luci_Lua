// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media.session;

import com.sonos.acr.media.SonosMediaItem;
import com.sonos.acr.util.SLog;
import java.util.*;

class SonosSessionQueueModel
{

    public SonosSessionQueueModel(boolean flag)
    {
        items = new ArrayList();
        currentIndex = -1;
        playing = flag;
    }

    public void appendItem(SonosMediaItem sonosmediaitem)
    {
        insertItem(sonosmediaitem, items.size());
    }

    public void clear()
    {
        items.clear();
        currentIndex = -1;
    }

    public boolean containsItem(SonosMediaItem sonosmediaitem)
    {
        return items.contains(sonosmediaitem);
    }

    public int getCurrentIndex()
    {
        return currentIndex;
    }

    public SonosMediaItem getCurrentItem()
    {
        SonosMediaItem sonosmediaitem;
        if(currentIndex >= 0 && currentIndex < items.size())
            sonosmediaitem = (SonosMediaItem)items.get(currentIndex);
        else
            sonosmediaitem = null;
        return sonosmediaitem;
    }

    public int getIndexOfItem(SonosMediaItem sonosmediaitem)
    {
        return items.indexOf(sonosmediaitem);
    }

    public SonosMediaItem getItemAt(int i)
    {
        if(!$assertionsDisabled && (i < 0 || i >= items.size()))
            throw new AssertionError();
        else
            return (SonosMediaItem)items.get(i);
    }

    public SonosMediaItem getItemById(String s)
    {
        Iterator iterator = items.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SonosMediaItem sonosmediaitem = (SonosMediaItem)iterator.next();
        if(!sonosmediaitem.getId().equals(s)) goto _L4; else goto _L3
_L3:
        return sonosmediaitem;
_L2:
        sonosmediaitem = null;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public List getItems()
    {
        return items;
    }

    public int getSize()
    {
        return items.size();
    }

    public void insertItem(SonosMediaItem sonosmediaitem, int i)
    {
        if(!$assertionsDisabled && (i < 0 || i > items.size()))
            throw new AssertionError();
        items.add(i, sonosmediaitem);
        if(i <= currentIndex)
            currentIndex = 1 + currentIndex;
        if(items.size() == 1 && currentIndex != 0)
        {
            currentIndex = 0;
            SLog.d("SonosSessionQueueModel", "Current item changed to index=0 after adding first item");
        }
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public void removeItem(SonosMediaItem sonosmediaitem)
    {
        int i;
        int j;
        i = items.indexOf(sonosmediaitem);
        j = currentIndex;
        items.remove(sonosmediaitem);
        if(items.size() != 0) goto _L2; else goto _L1
_L1:
        j = -1;
        if(sonosmediaitem != null)
            SLog.d("SonosSessionQueueModel", (new StringBuilder()).append("Current item changed from index=").append(currentIndex).append(" to -1 after removing the last item").toString());
_L4:
        if(j != currentIndex)
        {
            SLog.d("SonosSessionQueueModel", (new StringBuilder()).append("Current item changed from index=").append(currentIndex).append(" to ").append(j).append(" after removing item").toString());
            currentIndex = j;
        }
        return;
_L2:
        if(i >= 0)
            if(i == currentIndex)
                j = 0;
            else
            if(currentIndex > i)
                j--;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setCurrentIndex(int i)
    {
        if(!$assertionsDisabled && (i < -1 || i >= items.size()))
        {
            throw new AssertionError();
        } else
        {
            currentIndex = i;
            return;
        }
    }

    public void setPlaying(boolean flag)
    {
        playing = flag;
    }

    static final boolean $assertionsDisabled = false;
    private static final String LOG_TAG = "SonosSessionQueueModel";
    private int currentIndex;
    private List items;
    private boolean playing;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/media/session/SonosSessionQueueModel.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }
}
