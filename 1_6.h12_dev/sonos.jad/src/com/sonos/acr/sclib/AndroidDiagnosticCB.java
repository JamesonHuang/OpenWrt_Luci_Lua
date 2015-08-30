// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import android.os.Build;
import com.sonos.acr.network.WifiSetupConnectionManager;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCLibDiagnosticExtraInfoCallback;
import java.text.DateFormat;
import java.util.Date;

// Referenced classes of package com.sonos.acr.sclib:
//            SCLibManager

public class AndroidDiagnosticCB extends SCLibDiagnosticExtraInfoCallback
{

    public AndroidDiagnosticCB()
    {
    }

    public String getDiagnosticExtraInfo()
    {
        String s = LibraryUtils.getSCLibManager().getWifiSetupDelegate().getCurrentSSID();
        String s1 = LibraryUtils.getSCLibManager().getWifiSetupDelegate().getCurrentBSSID();
        StringBuilder stringbuilder = (new StringBuilder()).append("<WirelessInfo><SSID><![CDATA[");
        String s2;
        StringBuilder stringbuilder1;
        String s3;
        if(s == null || s.equals(""))
            s2 = "SSID is Unknown";
        else
            s2 = (new StringBuilder()).append("SSID = ").append(s).toString();
        stringbuilder1 = stringbuilder.append(s2).append("]]></SSID>\n").append("<BSSID><![CDATA[");
        if(s1 == null || s1.equals(""))
            s3 = "BSSID is Unknown";
        else
            s3 = (new StringBuilder()).append("BSSID = ").append(s1).toString();
        return stringbuilder1.append(s3).append("]]></BSSID>").append("</WirelessInfo>\n").append("<CRType>CRType = ACR</CRType>\n").append("<Name><![CDATA[").append("Manufacturer = ").append(Build.MANUFACTURER).append("]]></Name>\n").append("<Model><![CDATA[").append("Model = ").append(Build.MODEL).append("]]></Model>\n").append("<ProductCode><![CDATA[").append("Product Code = ").append(Build.PRODUCT).append("]]></ProductCode>\n").append("<OSVersion><![CDATA[").append("Android OS Version = ").append(android.os.Build.VERSION.RELEASE).append("]]></OSVersion>\n").append("<date>").append("Date = ").append(DateFormat.getDateTimeInstance().format(new Date())).append("</date>\n").toString();
    }
}
