// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneDevice;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCIOpCBSwigBase;
import com.sonos.sclib.SCIOpConnectionManagerGetProtocolInfo;
import java.util.*;

public class SonosRoutePlayerInfo
{
    public static interface SonosRoutePlayerInfoCB
    {

        public abstract void onGetRoutePlayerInfoError(SonosRoutePlayerInfo sonosrouteplayerinfo, String s);

        public abstract void onGetRoutePlayerInfoFatalError(SonosRoutePlayerInfo sonosrouteplayerinfo, String s);

        public abstract void onRoutePlayerInfoAvailable(SonosRoutePlayerInfo sonosrouteplayerinfo);
    }


    protected SonosRoutePlayerInfo(String s, SonosRoutePlayerInfoCB sonosrouteplayerinfocb)
    {
        mimeTypesForHTTP = new ArrayList();
        pendingRequests = new ArrayList();
        playerId = s;
        callback = sonosrouteplayerinfocb;
    }

    ArrayList getMimeTypesForHTTP()
    {
        return mimeTypesForHTTP;
    }

    protected ArrayList getMimeTypesFromProtocolInfo(String s)
    {
        String s1 = "";
        int i = 0;
        do
        {
            if(i < 0 || i >= s.length())
                break;
            int l = i;
            i = s.indexOf('\\', i);
            if(i >= 0)
            {
                if(i > l)
                    s1 = (new StringBuilder()).append(s1).append(s.substring(l, i)).toString();
                if(++i < s.length())
                    if(s.charAt(i) == '\\')
                    {
                        s1 = (new StringBuilder()).append(s1).append("\\").toString();
                        i++;
                    } else
                    if(s.charAt(i) == ':')
                    {
                        s1 = (new StringBuilder()).append(s1).append("&#58;").toString();
                        i++;
                    } else
                    if(s.charAt(i) == ',')
                    {
                        s1 = (new StringBuilder()).append(s1).append("&#44;").toString();
                        i++;
                    }
            } else
            {
                s1 = (new StringBuilder()).append(s1).append(s.substring(l)).toString();
            }
        } while(true);
        String s2 = "";
        ArrayList arraylist = new ArrayList();
        String as[] = s1.split(",");
        int j = as.length;
        for(int k = 0; k < j; k++)
        {
            String as1[] = as[k].split(":");
            if(as1.length >= 3 && as1[0].equals("http-get") && as1[2].length() > 0)
            {
                String s3 = as1[2].replace("&#58;", ":").replace("&#44;", ",");
                arraylist.add(s3);
                s2 = (new StringBuilder()).append(s2).append(s3).append(", ").toString();
            }
        }

        SLog.d("SonosRoutePlayerInfo", (new StringBuilder()).append(playerId).append(" supports ").append(s2).toString());
        return arraylist;
    }

    public String getPlayerId()
    {
        return playerId;
    }

    public ArrayList queryRoutePlayerInfo()
    {
        ArrayList arraylist;
        ZoneDevice zonedevice;
        arraylist = null;
        zonedevice = LibraryUtils.getHousehold().lookupDevice(playerId);
        if(zonedevice != null) goto _L2; else goto _L1
_L1:
        SLog.w("SonosRoutePlayerInfo", (new StringBuilder()).append("queryRoutePlayerInfo: lookupDevice for ").append(playerId).append(" failed!").toString());
        if(callback != null)
            callback.onGetRoutePlayerInfoFatalError(this, "lookupDevice failed, player no longer exists");
_L4:
        return arraylist;
_L2:
        final String version = zonedevice.getSoftwareVersionStr();
        if(version.equals(playerVersion))
            arraylist = mimeTypesForHTTP;
        else
        if(!pendingRequests.contains(playerVersion))
        {
            pendingRequests.add(playerVersion);
            final SCIOpConnectionManagerGetProtocolInfo op = zonedevice.createGetProtocolInfoOp();
            if(op != null)
                op._start(new SCIOpCBSwigBase() {

                    public void _operationComplete(long l, int i)
                    {
                        if(i != 0) goto _L2; else goto _L1
_L1:
                        playerVersion = version;
                        pendingRequests.remove(version);
                        String s = op.getSink();
                        mimeTypesForHTTP = getMimeTypesFromProtocolInfo(s);
                        if(callback != null)
                            callback.onRoutePlayerInfoAvailable(info);
_L4:
                        return;
_L2:
                        if(callback != null)
                            callback.onGetRoutePlayerInfoError(info, (new StringBuilder()).append("UPnP Error ").append(i).append(" calling GetProtocolInfo").toString());
                        if(true) goto _L4; else goto _L3
_L3:
                    }

                    final SonosRoutePlayerInfo this$0;
                    final SonosRoutePlayerInfo val$info;
                    final SCIOpConnectionManagerGetProtocolInfo val$op;
                    final String val$version;

            
            {
                this$0 = SonosRoutePlayerInfo.this;
                version = s;
                op = sciopconnectionmanagergetprotocolinfo;
                info = sonosrouteplayerinfo1;
                super();
            }
                }
);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void testGetMimeTypesFromProtocolInfo()
    {
        for(Iterator iterator = allTestStrings.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            ArrayList arraylist = getMimeTypesFromProtocolInfo(s);
            ArrayList arraylist1 = new ArrayList(Arrays.asList((Object[])allTestStrings.get(s)));
            if(arraylist.equals(arraylist1))
                SLog.d("SonosRoutePlayerInfo", (new StringBuilder()).append("[PASS] ").append(s).toString());
            else
                SLog.d("SonosRoutePlayerInfo", (new StringBuilder()).append("[FAIL] Expected: \"").append(arraylist1).append("\", actual: \"").append(arraylist).append("\"").toString());
        }

    }

    private static final HashMap allTestStrings;
    private final String LOG_TAG = "SonosRoutePlayerInfo";
    private SonosRoutePlayerInfoCB callback;
    private ArrayList mimeTypesForHTTP;
    private ArrayList pendingRequests;
    private String playerId;
    private String playerVersion;

    static 
    {
        allTestStrings = new HashMap();
        HashMap hashmap = allTestStrings;
        String as[] = new String[1];
        as[0] = "c,:\\";
        hashmap.put("\\11:\\22:\\33,http-get:b:c\\,\\:\\\\", as);
        HashMap hashmap1 = allTestStrings;
        String as1[] = new String[1];
        as1[0] = "audio/mp3";
        hashmap1.put("http-get:*:audio/mp3,x-file-cifs:*:audio/mp3", as1);
        HashMap hashmap2 = allTestStrings;
        String as2[] = new String[2];
        as2[0] = "audio/mp3:";
        as2[1] = "audio/mp3,";
        hashmap2.put("http-get:*:audio/mp3\\:,http-get:*:audio/mp3\\,", as2);
    }


/*
    static String access$002(SonosRoutePlayerInfo sonosrouteplayerinfo, String s)
    {
        sonosrouteplayerinfo.playerVersion = s;
        return s;
    }

*/



/*
    static ArrayList access$202(SonosRoutePlayerInfo sonosrouteplayerinfo, ArrayList arraylist)
    {
        sonosrouteplayerinfo.mimeTypesForHTTP = arraylist;
        return arraylist;
    }

*/

}
