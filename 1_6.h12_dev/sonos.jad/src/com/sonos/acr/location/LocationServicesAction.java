// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.location;

import android.location.*;
import android.os.*;
import android.util.Log;
import com.sonos.acr.application.SonosApplication;
import com.sonos.sclib.*;
import java.io.IOException;
import java.util.List;

public class LocationServicesAction extends SCIActionSwigBase
    implements LocationListener
{

    public LocationServicesAction(SCIStringArray scistringarray)
    {
        timeoutRunnable = new Runnable() {

            public void run()
            {
                timerExpired();
            }

            final LocationServicesAction this$0;

            
            {
                this$0 = LocationServicesAction.this;
                super();
            }
        }
;
        requestedProperties = scistringarray;
    }

    private void retrieveAddress(Location location)
    {
        AsyncTask asynctask = new AsyncTask() {

            protected transient Address doInBackground(Location alocation1[])
            {
                Geocoder geocoder = new Geocoder(SonosApplication.getInstance());
                if(alocation1.length != 0 && alocation1[0] != null) goto _L2; else goto _L1
_L1:
                Address address;
                Log.e("LocationServicesAction", "onLocationChanged returned empty list or null location");
                address = null;
_L4:
                return address;
_L2:
                Location location1 = alocation1[0];
                List list = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 1);
                if(list != null && !list.isEmpty())
                {
                    address = (Address)list.get(0);
                } else
                {
                    Log.e("LocationServicesAction", "geocoder.getFromLocation failed");
                    address = null;
                }
                continue; /* Loop/switch isn't completed */
                IOException ioexception;
                ioexception;
                Log.e("LocationSampleActivity", "IO Exception in getFromLocation()");
                ioexception.printStackTrace();
                if(false && !null.isEmpty())
                {
                    address = (Address)null.get(0);
                } else
                {
                    Log.e("LocationServicesAction", "geocoder.getFromLocation failed");
                    address = null;
                }
                continue; /* Loop/switch isn't completed */
                IllegalArgumentException illegalargumentexception;
                illegalargumentexception;
                Log.e("LocationSampleActivity", (new StringBuilder()).append("Illegal arguments ").append(Double.toString(location1.getLatitude())).append(" , ").append(Double.toString(location1.getLongitude())).append(" passed to address service").toString());
                illegalargumentexception.printStackTrace();
                if(false && !null.isEmpty())
                {
                    address = (Address)null.get(0);
                } else
                {
                    Log.e("LocationServicesAction", "geocoder.getFromLocation failed");
                    address = null;
                }
                continue; /* Loop/switch isn't completed */
                Exception exception;
                exception;
                if(false && !null.isEmpty())
                {
                    address = (Address)null.get(0);
                } else
                {
                    Log.e("LocationServicesAction", "geocoder.getFromLocation failed");
                    address = null;
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Location[])aobj);
            }

            protected void onPostExecute(Address address)
            {
                handler.removeCallbacks(timeoutRunnable);
                retrieveAddressCompleted(address);
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((Address)obj);
            }

            final LocationServicesAction this$0;

            
            {
                this$0 = LocationServicesAction.this;
                super();
            }
        }
;
        Location alocation[] = new Location[1];
        alocation[0] = location;
        asynctask.execute(alocation);
    }

    private void retrieveAddressCompleted(Address address)
    {
        if(address != null)
        {
            SCIPropertyBag scipropertybag = actionContext.getPropertyBag();
            int i = 0;
            while((long)i < requestedProperties.size()) 
            {
                String s = requestedProperties.getAt(i);
                if(s.equals(sclib.LS_PROP_POSTAL_CODE))
                {
                    if(address.getPostalCode() != null)
                        scipropertybag.setStrProp(sclib.LS_PROP_POSTAL_CODE, address.getPostalCode());
                } else
                if(s.equals(sclib.LS_PROP_COUNTRY_CODE))
                {
                    if(address.getCountryCode() != null)
                        scipropertybag.setStrProp(sclib.LS_PROP_COUNTRY_CODE, address.getCountryCode());
                } else
                if(s.equals(sclib.LS_PROP_COUNTRY_NAME) && address.getCountryName() != null)
                    scipropertybag.setStrProp(sclib.LS_PROP_COUNTRY_NAME, address.getCountryName());
                i++;
            }
        }
        actionContext.actionHasCompleted(this);
    }

    private void startTimer()
    {
        handler = SonosApplication.getInstance().getHandler();
        handler.postDelayed(timeoutRunnable, 3000L);
    }

    private void timerExpired()
    {
        ((LocationManager)SonosApplication.getInstance().getSystemService("location")).removeUpdates(this);
        Log.e("LocationServicesAction", "retrieving location timed out");
        actionContext.actionHasCompleted(this);
    }

    public void onLocationChanged(Location location)
    {
        Log.e("LocationServicesAction", "onLocationChanged update received");
        handler.removeCallbacks(timeoutRunnable);
        handler.postDelayed(timeoutRunnable, 1000L);
        ((LocationManager)SonosApplication.getInstance().getSystemService("location")).removeUpdates(this);
        retrieveAddress(location);
    }

    public void onProviderDisabled(String s)
    {
    }

    public void onProviderEnabled(String s)
    {
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        if(!requestedProperties.isEmpty()) goto _L2; else goto _L1
_L1:
        SCActionCompletionStatus scactioncompletionstatus = SCActionCompletionStatus.DONE_WITH_ACTION;
_L4:
        return scactioncompletionstatus;
_L2:
        actionContext = sciactioncontext;
        startTimer();
        LocationManager locationmanager = (LocationManager)SonosApplication.getInstance().getSystemService("location");
        Location location = locationmanager.getLastKnownLocation("network");
        if(location != null)
        {
            if(System.currentTimeMillis() - location.getTime() < 0x493e0L)
            {
                retrieveAddress(location);
                scactioncompletionstatus = SCActionCompletionStatus.WAIT_FOR_CALLBACK;
                continue; /* Loop/switch isn't completed */
            }
            Log.e("LocationServicesAction", "lastKnownLocation is too old, attempting to retrieve new one");
        }
        String s = locationmanager.getBestProvider(new Criteria(), true);
        if(s == null || s.length() == 0)
        {
            Log.e("LocationServicesAction", "Device's Location Services are OFF, unable to get location");
            handler.removeCallbacks(timeoutRunnable);
            scactioncompletionstatus = SCActionCompletionStatus.DONE_WITH_ACTION;
        } else
        {
            locationmanager.requestLocationUpdates("network", 0L, 0.0F, this);
            scactioncompletionstatus = SCActionCompletionStatus.WAIT_FOR_CALLBACK;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final long GET_LOCATION_TIMEOUT = 3000L;
    public static final long GET_LOCATION_TIMEOUT_EXTENSION = 1000L;
    public static final long MAX_LOCATION_AGE = 0x493e0L;
    protected SCIActionContext actionContext;
    protected Handler handler;
    protected SCIStringArray requestedProperties;
    private Runnable timeoutRunnable;



}
