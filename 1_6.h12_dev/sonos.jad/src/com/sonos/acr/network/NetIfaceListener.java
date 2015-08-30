// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.network;


// Referenced classes of package com.sonos.acr.network:
//            NetIfaceManager

public interface NetIfaceListener
{

    public abstract void onConnectionStatusChange(NetIfaceManager netifacemanager, NetIfaceManager.ConnectionStatus connectionstatus);
}
