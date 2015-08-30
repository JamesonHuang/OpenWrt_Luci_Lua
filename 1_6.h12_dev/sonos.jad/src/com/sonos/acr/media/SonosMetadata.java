// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.os.Bundle;
import com.sonos.acr.util.StringUtils;
import java.util.Iterator;
import java.util.Set;

public class SonosMetadata
{

    SonosMetadata(Bundle bundle, String s, Bundle bundle1)
    {
        convertFromBundle(bundle);
        httpHeaders = bundle1;
        mimeType = s;
    }

    private void convertFromBundle(Bundle bundle)
    {
        reset();
        if(bundle != null)
        {
            title = bundle.getString("android.media.metadata.TITLE");
            artist = bundle.getString("android.media.metadata.ARTIST");
            album = bundle.getString("android.media.metadata.ALBUM_TITLE");
            artworkURL = bundle.getString("android.media.metadata.ARTWORK_URI");
            durationInSecs = (int)(bundle.getLong("android.media.metadata.DURATION", -1000L) / 1000L);
        }
    }

    private static String escapeXml(String s)
    {
        return s.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "&#10;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
    }

    private void reset()
    {
        trackURL = null;
        title = null;
        artist = null;
        album = null;
        station = null;
        artworkURL = null;
        durationInSecs = -1;
    }

    private String stringFromHeaderBundle(Bundle bundle)
    {
        String s;
        if(bundle == null || bundle.isEmpty())
        {
            s = null;
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            Object aobj[];
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); stringbuilder.append(String.format("%s: %s", aobj)))
            {
                String s1 = (String)iterator.next();
                if(stringbuilder.length() > 0)
                    stringbuilder.append("\n");
                aobj = new Object[2];
                aobj[0] = s1;
                aobj[1] = bundle.getString(s1);
            }

            s = stringbuilder.toString();
        }
        return s;
    }

    public String getAlbum()
    {
        return album;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getArtworkURL()
    {
        return artworkURL;
    }

    public String getDIDLLite(boolean flag)
    {
        String s14;
        if(StringUtils.isEmptyOrNull(title) && StringUtils.isEmptyOrNull(artist) && StringUtils.isEmptyOrNull(album) && StringUtils.isEmptyOrNull(artworkURL) && StringUtils.isEmptyOrNull(station))
        {
            s14 = "";
        } else
        {
            String s;
            String s1;
            String s2;
            Object aobj[];
            String s3;
            String s4;
            String s5;
            String s6;
            StringBuilder stringbuilder;
            Object aobj1[];
            String s7;
            String s8;
            StringBuilder stringbuilder1;
            Object aobj2[];
            String s9;
            String s10;
            StringBuilder stringbuilder2;
            Object aobj3[];
            String s11;
            String s12;
            String s13;
            if(flag)
                s = "<?xml version=\"1.0\"?>\n";
            else
                s = "";
            s1 = (new StringBuilder()).append(s).append("<DIDL-Lite xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\" xmlns:r=\"urn:schemas-rinconnetworks-com:metadata-1-0/\" xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\">\n  <item id=\"-1\" parentID=\"-1\" restricted=\"true\">\n").toString();
            s2 = "";
            if(durationInSecs >= 0)
            {
                int i = durationInSecs / 3600;
                int j = durationInSecs / 60 - i * 60;
                int k = durationInSecs % 60;
                Object aobj7[] = new Object[3];
                aobj7[0] = Integer.valueOf(i);
                aobj7[1] = Integer.valueOf(j);
                aobj7[2] = Integer.valueOf(k);
                s2 = String.format("duration=\"%02d:%02d:%02d\"", aobj7);
            }
            aobj = new Object[2];
            if(mimeType == null)
                s3 = "audio/mpeg";
            else
                s3 = mimeType;
            aobj[0] = s3;
            aobj[1] = s2;
            s4 = String.format("    <res protocolInfo=\"http-get:*:%s:*\" %s />\n", aobj);
            s5 = (new StringBuilder()).append(s1).append(s4).toString();
            s6 = (new StringBuilder()).append(s5).append("    <upnp:class>object.item.audioItem.musicTrack</upnp:class>\n").toString();
            stringbuilder = (new StringBuilder()).append(s6);
            aobj1 = new Object[1];
            if(title != null)
                s7 = escapeXml(title);
            else
                s7 = "";
            aobj1[0] = s7;
            s8 = stringbuilder.append(String.format("    <dc:title>%s</dc:title>\n", aobj1)).toString();
            stringbuilder1 = (new StringBuilder()).append(s8);
            aobj2 = new Object[1];
            if(artist != null)
                s9 = escapeXml(artist);
            else
                s9 = "";
            aobj2[0] = s9;
            s10 = stringbuilder1.append(String.format("    <dc:creator>%s</dc:creator>\n", aobj2)).toString();
            stringbuilder2 = (new StringBuilder()).append(s10);
            aobj3 = new Object[1];
            if(album != null)
                s11 = escapeXml(album);
            else
                s11 = "";
            aobj3[0] = s11;
            s12 = stringbuilder2.append(String.format("    <upnp:album>%s</upnp:album>\n", aobj3)).toString();
            if(!StringUtils.isEmptyOrNull(artworkURL))
            {
                StringBuilder stringbuilder5 = (new StringBuilder()).append(s12);
                Object aobj6[] = new Object[1];
                aobj6[0] = escapeXml(artworkURL);
                s12 = stringbuilder5.append(String.format("    <upnp:albumArtURI>%1$s</upnp:albumArtURI>\n", aobj6)).toString();
            }
            if(!StringUtils.isEmptyOrNull(station))
            {
                StringBuilder stringbuilder4 = (new StringBuilder()).append(s12);
                Object aobj5[] = new Object[1];
                aobj5[0] = escapeXml(station);
                s12 = stringbuilder4.append(String.format("    <r:stationName>%s</r:stationName>\n", aobj5)).toString();
            }
            s13 = stringFromHeaderBundle(httpHeaders);
            if(!StringUtils.isEmptyOrNull(s13))
            {
                StringBuilder stringbuilder3 = (new StringBuilder()).append(s12);
                Object aobj4[] = new Object[1];
                aobj4[0] = escapeXml(s13);
                s12 = stringbuilder3.append(String.format("    <r:http>%s</r:http>\n", aobj4)).toString();
            }
            s14 = (new StringBuilder()).append(s12).append("  </item>\n</DIDL-Lite>").toString();
        }
        return s14;
    }

    public int getDurationInSecs()
    {
        return durationInSecs;
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public String getStation()
    {
        return station;
    }

    public String getTitle()
    {
        return title;
    }

    public String getTrackURL()
    {
        return trackURL;
    }

    public void setAlbum(String s)
    {
        album = s;
    }

    public void setArtist(String s)
    {
        artist = s;
    }

    public void setArtworkURL(String s)
    {
        artworkURL = s;
    }

    public void setDurationInSecs(int i)
    {
        durationInSecs = i;
    }

    public void setStation(String s)
    {
        station = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setTrackURL(String s)
    {
        trackURL = s;
    }

    private static final String DIDL_MD_TEMPLATE_BEGIN = "<DIDL-Lite xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\" xmlns:r=\"urn:schemas-rinconnetworks-com:metadata-1-0/\" xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\">\n  <item id=\"-1\" parentID=\"-1\" restricted=\"true\">\n";
    private static final String DIDL_MD_TEMPLATE_END = "  </item>\n</DIDL-Lite>";
    private static final String DIDL_MD_XML_DECL = "<?xml version=\"1.0\"?>\n";
    private static final String DIDL_MUSIC_TRACK_ITEM = "    <upnp:class>object.item.audioItem.musicTrack</upnp:class>\n";
    private static final String DIDL_RES_DURATION_FMT = "duration=\"%02d:%02d:%02d\"";
    private static final String DIDL_RES_FMT = "    <res protocolInfo=\"http-get:*:%s:*\" %s />\n";
    private String album;
    private String artist;
    private String artworkURL;
    private int durationInSecs;
    private Bundle httpHeaders;
    private String mimeType;
    private String station;
    private String title;
    private String trackURL;
}
