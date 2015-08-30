// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationWatchList extends ContextAwareBase
{

    public ConfigurationWatchList()
    {
        fileWatchList = new ArrayList();
        lastModifiedList = new ArrayList();
    }

    private void addAsFileToWatch(URL url)
    {
        File file = convertToFile(url);
        if(file != null)
        {
            fileWatchList.add(file);
            lastModifiedList.add(Long.valueOf(file.lastModified()));
        }
    }

    public void addToWatchList(URL url)
    {
        addAsFileToWatch(url);
    }

    public boolean changeDetected()
    {
        int i;
        int j;
        i = fileWatchList.size();
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_63;
        if(((Long)lastModifiedList.get(j)).longValue() == ((File)fileWatchList.get(j)).lastModified()) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L4
    }

    public void clear()
    {
        mainURL = null;
        lastModifiedList.clear();
        fileWatchList.clear();
    }

    File convertToFile(URL url)
    {
        File file;
        if("file".equals(url.getProtocol()))
        {
            file = new File(URLDecoder.decode(url.getFile()));
        } else
        {
            addInfo((new StringBuilder()).append("URL [").append(url).append("] is not of type file").toString());
            file = null;
        }
        return file;
    }

    public List getCopyOfFileWatchList()
    {
        return new ArrayList(fileWatchList);
    }

    public URL getMainURL()
    {
        return mainURL;
    }

    public void setMainURL(URL url)
    {
        mainURL = url;
        if(url != null)
            addAsFileToWatch(url);
    }

    List fileWatchList;
    List lastModifiedList;
    URL mainURL;
}
