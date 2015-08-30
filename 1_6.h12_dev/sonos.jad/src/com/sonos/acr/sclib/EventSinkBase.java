// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import com.sonos.sclib.SCIEventSinkSwigBase;
import java.util.Collections;
import java.util.HashSet;

public abstract class EventSinkBase extends SCIEventSinkSwigBase
{

    public EventSinkBase()
    {
    }

    public boolean canRaiseEvents()
    {
        boolean flag;
        if(!m_disableEvents)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean listenToEventType(String s)
    {
        boolean flag;
        if(!canRaiseEvents())
        {
            flag = false;
        } else
        {
            if(m_supportEvents == null)
            {
                String as[] = supportedEvents();
                if(as != null)
                {
                    m_supportEvents = new HashSet();
                    Collections.addAll(m_supportEvents, as);
                }
            }
            flag = m_supportEvents.contains(s);
        }
        return flag;
    }

    public void setCanRaiseEvents(boolean flag)
    {
        boolean flag1;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        m_disableEvents = flag1;
    }

    protected abstract String[] supportedEvents();

    private boolean m_disableEvents;
    private HashSet m_supportEvents;
}
