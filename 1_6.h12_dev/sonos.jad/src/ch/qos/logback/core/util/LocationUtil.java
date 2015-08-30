// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package ch.qos.logback.core.util:
//            Loader

public class LocationUtil
{

    public LocationUtil()
    {
    }

    public static URL urlForResource(String s)
        throws MalformedURLException, FileNotFoundException
    {
        if(s == null)
            throw new NullPointerException("location is required");
        URL url;
        if(!s.matches("^\\p{Alpha}[\\p{Alnum}+.-]*:.*$"))
            url = Loader.getResourceBySelfClassLoader(s);
        else
        if(s.startsWith("classpath:"))
        {
            String s1 = s.substring("classpath:".length());
            if(s1.startsWith("/"))
                s1 = s1.substring(1);
            if(s1.length() == 0)
                throw new MalformedURLException("path is required");
            url = Loader.getResourceBySelfClassLoader(s1);
        } else
        {
            url = new URL(s);
        }
        if(url == null)
            throw new FileNotFoundException(s);
        else
            return url;
    }

    public static final String CLASSPATH_SCHEME = "classpath:";
    public static final String SCHEME_PATTERN = "^\\p{Alpha}[\\p{Alnum}+.-]*:.*$";
}
