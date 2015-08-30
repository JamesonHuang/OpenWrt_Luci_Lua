// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.util.Xml;
import com.sonos.acr.util.SLog;
import java.io.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package com.sonos.acr.media:
//            SonosHouseholdZoneGroupState

class SonosZoneGroupStateParser
{

    public SonosZoneGroupStateParser()
    {
        zoneGroupState = new SonosHouseholdZoneGroupState();
    }

    private void readFromParser(XmlPullParser xmlpullparser, SonosHouseholdZoneGroupState sonoshouseholdzonegroupstate)
        throws XmlPullParserException, IOException
    {
        xmlpullparser.nextTag();
        xmlpullparser.require(2, null, "ZoneGroups");
        do
        {
            int i = xmlpullparser.next();
            if(i == 1)
                break;
            String s = xmlpullparser.getName();
            switch(i)
            {
            case 0: // '\0'
            case 1: // '\001'
            default:
                continue;

            case 2: // '\002'
                break;
            }
            if(!s.equals("ZoneGroup"))
                continue;
            String s1 = "";
            String s2 = "";
            int j = 0;
            while(j < xmlpullparser.getAttributeCount()) 
            {
                String s3 = xmlpullparser.getAttributeName(j);
                if(s3.equals("ID"))
                    s1 = xmlpullparser.getAttributeValue(j);
                else
                if(s3.equals("Coordinator"))
                    s2 = xmlpullparser.getAttributeValue(j);
                j++;
            }
            if(!$assertionsDisabled && s1.length() <= 0)
                throw new AssertionError();
            if(!$assertionsDisabled && s2.length() <= 0)
                throw new AssertionError();
            sonoshouseholdzonegroupstate.addGroup(s1, s2);
        } while(true);
    }

    public SonosHouseholdZoneGroupState parse(String s)
    {
        StringReader stringreader = new StringReader(s);
        try
        {
            XmlPullParser xmlpullparser = Xml.newPullParser();
            xmlpullparser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            xmlpullparser.setInput(stringreader);
            readFromParser(xmlpullparser, zoneGroupState);
            stringreader.close();
        }
        catch(XmlPullParserException xmlpullparserexception)
        {
            SLog.e("SonosZoneGroupStateParser", "error parsing topology XML!");
            xmlpullparserexception.printStackTrace();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return zoneGroupState;
    }

    static final boolean $assertionsDisabled = false;
    public static final String LOG_TAG = "SonosZoneGroupStateParser";
    private static final String XML_ZONEGROUP = "ZoneGroup";
    private static final String XML_ZONEGROUP_ATTR_COORDINATOR = "Coordinator";
    private static final String XML_ZONEGROUP_ATTR_ID = "ID";
    private SonosHouseholdZoneGroupState zoneGroupState;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/media/SonosZoneGroupStateParser.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }
}
