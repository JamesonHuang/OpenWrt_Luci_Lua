// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


// Referenced classes of package com.splunk.mint:
//            Properties

class MintUrls
{

    public MintUrls(String s, String s1)
    {
        URL = new StringBuffer();
        createUrl(s, s1);
    }

    /**
     * @deprecated Method createUrl is deprecated
     */

    private void createUrl(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        if(s != null) goto _L2; else goto _L1
_L1:
        URL.append("https://");
        URL.append(s1);
        URL.append(".api.splkmobile.com/");
        URL.append("1.0");
        URL.append("/");
        URL.append(s1);
        URL.append("/");
        URL.append(Properties.UID);
        URL.append("/");
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(s.length() <= 0 || !s.startsWith("http")) goto _L4; else goto _L3
_L3:
        String s2 = removeLastSlashFromEnd(s);
        URL.append(s2);
        URL.append("/");
        URL.append("1.0");
        URL.append("/");
        URL.append(s1);
        URL.append("/");
        URL.append(Properties.UID);
        URL.append("/");
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getURL is deprecated
     */

    public static String getURL()
    {
        com/splunk/mint/MintUrls;
        JVM INSTR monitorenter ;
        if(URL == null) goto _L2; else goto _L1
_L1:
        String s1 = URL.toString();
        String s = s1;
_L4:
        com/splunk/mint/MintUrls;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = "";
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getURL is deprecated
     */

    public static String getURL(int i, int j)
    {
        com/splunk/mint/MintUrls;
        JVM INSTR monitorenter ;
        if(URL == null) goto _L2; else goto _L1
_L1:
        String s1 = (new StringBuilder()).append(URL.toString()).append(String.valueOf(i)).append("/").append(String.valueOf(j)).toString();
        String s = s1;
_L4:
        com/splunk/mint/MintUrls;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = "";
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method removeLastSlashFromEnd is deprecated
     */

    private static final String removeLastSlashFromEnd(String s)
    {
        com/splunk/mint/MintUrls;
        JVM INSTR monitorenter ;
        if(s != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        com/splunk/mint/MintUrls;
        JVM INSTR monitorexit ;
        return s;
_L2:
        String s1;
        if(!s.endsWith("/"))
            continue; /* Loop/switch isn't completed */
        s1 = s.substring(0, s.lastIndexOf("/"));
        s = s1;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    private static volatile StringBuffer URL;
}
