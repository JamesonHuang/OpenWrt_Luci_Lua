// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.location;

import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.location:
//            LocationServicesAction

public class SonosLocationServices extends SCILocationServicesSwigBase
{

    public SonosLocationServices()
    {
    }

    public SCIAction createRequestLocationAuthorizationAction()
    {
        return null;
    }

    public SCIAction createRequestLocationInformationAction(SCIStringArray scistringarray)
    {
        return new LocationServicesAction(scistringarray);
    }

    public String getLocationUsageDescription()
    {
        return "";
    }

    public String getLocationUsageExistingHHDescription()
    {
        return "";
    }

    public com.sonos.sclib.SCILocationServices.LocationServiceStatus locationStatus()
    {
        return com.sonos.sclib.SCILocationServices.LocationServiceStatus.StatusEnabled;
    }

    public boolean shouldRequestLocationForExistingHH()
    {
        return false;
    }

    public static final String LOG_TAG = com/sonos/acr/location/SonosLocationServices.getSimpleName();

}
