// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.io.File;
import java.io.FileFilter;

// Referenced classes of package com.splunk.mint:
//            Properties

class SplunkFileFilter
    implements FileFilter
{

    SplunkFileFilter()
    {
    }

    public static String createNewFile()
    {
        return (new StringBuilder()).append(Properties.FILES_PATH).append("/").append("MintSavedData-1-").append(String.valueOf(System.currentTimeMillis())).append(".json").toString();
    }

    public static SplunkFileFilter getInstance()
    {
        if(fileFilterSingleton == null)
            fileFilterSingleton = new SplunkFileFilter();
        return fileFilterSingleton;
    }

    public boolean accept(File file)
    {
        boolean flag;
        if(file.getName().startsWith("MintSavedData-1-") && file.getName().endsWith(".json"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final String POSTFIX = ".json";
    private static final String PREFIX = "MintSavedData-1-";
    private static final String VERSION = "1";
    private static SplunkFileFilter fileFilterSingleton = null;

}
