// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import com.sonos.sclib.SCISonosPlaylist;

public class Playlist
{

    public Playlist(SCISonosPlaylist scisonosplaylist)
    {
        sonosPlaylist = scisonosplaylist;
        playlistName = scisonosplaylist.getTitle();
        playlistId = scisonosplaylist.getID();
    }

    public Playlist(String s)
    {
        playlistName = s;
        playlistId = "";
    }

    public boolean exists()
    {
        boolean flag;
        if(sonosPlaylist != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String getID()
    {
        return playlistId;
    }

    public String getTitle()
    {
        return playlistName;
    }

    public String toString()
    {
        return playlistName;
    }

    String playlistId;
    String playlistName;
    SCISonosPlaylist sonosPlaylist;
}
