// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.network;

import com.sonos.sclib.SCINetworkManagementDelegateSwigBase;

// Referenced classes of package com.sonos.acr.network:
//            SonosNetworkManager

public class NetworkManagementDelegateImpl extends SCINetworkManagementDelegateSwigBase
{

    public NetworkManagementDelegateImpl(SonosNetworkManager sonosnetworkmanager)
    {
        m_networkManager = null;
        m_networkManager = sonosnetworkmanager;
    }

    public com.sonos.sclib.SCINetworkManagementDelegate.NetworkType getNetworkType()
    {
        com.sonos.sclib.SCINetworkManagementDelegate.NetworkType networktype = com.sonos.sclib.SCINetworkManagementDelegate.NetworkType.NetworkType_UNKNOWN;
        if(!m_networkManager.isRunning()) goto _L2; else goto _L1
_L1:
        if(!m_networkManager.hasWifiConnection()) goto _L4; else goto _L3
_L3:
        networktype = com.sonos.sclib.SCINetworkManagementDelegate.NetworkType.NetworkType_WIFI;
_L2:
        return networktype;
_L4:
        if(m_networkManager.hasMobileConnection())
            networktype = com.sonos.sclib.SCINetworkManagementDelegate.NetworkType.NetworkType_CELL;
        else
        if(m_networkManager.hasNoInternetConnection(false))
            networktype = com.sonos.sclib.SCINetworkManagementDelegate.NetworkType.NetworkType_NONE;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private SonosNetworkManager m_networkManager;
}
