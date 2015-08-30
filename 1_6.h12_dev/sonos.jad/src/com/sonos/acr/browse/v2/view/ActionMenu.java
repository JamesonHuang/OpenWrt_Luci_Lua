// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import com.sonos.acr.browse.v2.actions.ActionSet;

public interface ActionMenu
{

    public abstract void clearActionItems();

    public abstract void setActions(ActionSet actionset);
}
