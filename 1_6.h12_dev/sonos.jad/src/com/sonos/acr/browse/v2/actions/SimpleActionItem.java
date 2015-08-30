// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;


// Referenced classes of package com.sonos.acr.browse.v2.actions:
//            ActionItem

public abstract class SimpleActionItem extends ActionItem
{

    protected SimpleActionItem(String s)
    {
        SimpleActionItem(s, true);
    }

    public SimpleActionItem(String s, boolean flag)
    {
        label = s;
        enabled = flag;
    }

    public String getActionID()
    {
        return getClass().getName();
    }

    public String getCategory()
    {
        return com/sonos/acr/browse/v2/actions/SimpleActionItem.getName();
    }

    public String getLabel()
    {
        return label;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean flag)
    {
        enabled = flag;
    }

    boolean enabled;
    public String label;
}
