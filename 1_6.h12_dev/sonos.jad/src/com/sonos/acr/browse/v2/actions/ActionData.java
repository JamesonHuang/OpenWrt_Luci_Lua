// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;


public interface ActionData
{

    public abstract com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getActionImageType();

    public abstract String getActionImageUrl();

    public abstract ActionData getExtendedActionData();

    public abstract String getPrimaryText();

    public abstract String getSecondaryText();
}
