// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

// Referenced classes of package com.splunk.mint:
//            Properties, LowPriorityThreadFactory, MintLogLevel, Mint, 
//            Logger, EnumStateStatus

class Utils
{

    Utils()
    {
    }

    protected static String MD5(String s)
        throws Exception
    {
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.update(s.getBytes(), 0, s.length());
        return (new BigInteger(1, messagedigest.digest())).toString(16);
    }

    public static boolean allowedToSendData()
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if(Properties.flushOnlyOverWiFi && !Properties.CONNECTION.equals("WIFI"))
            flag = false;
        return flag;
    }

    protected static boolean checkForRoot()
    {
        boolean flag = false;
        String as[] = new String[8];
        as[0] = "/sbin/";
        as[1] = "/system/bin/";
        as[2] = "/system/xbin/";
        as[3] = "/data/local/xbin/";
        as[4] = "/data/local/bin/";
        as[5] = "/system/sd/xbin/";
        as[6] = "/system/bin/failsafe/";
        as[7] = "/data/local/";
        int i = as.length;
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                {
                    String s = as[j];
                    if(!(new File((new StringBuilder()).append(s).append("su").toString())).exists())
                        break label0;
                    flag = true;
                }
                return flag;
            }
            j++;
        } while(true);
    }

    protected static void clearLastPingSentTime(final Context ctx)
    {
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                if(ctx != null)
                {
                    SharedPreferences sharedpreferences = ctx.getSharedPreferences("Mint", 0);
                    if(sharedpreferences != null)
                        sharedpreferences.edit().putLong("LASTPINGTIME", 0L).commit();
                }
            }

            final Context val$ctx;

            
            {
                ctx = context;
                super();
            }
        }
);
        ExecutorService executorservice = getExecutor();
        if(thread != null && executorservice != null)
            executorservice.submit(thread);
    }

    public static int convertLoggingLevelToInt(MintLogLevel mintloglevel)
    {
        byte byte0 = 10;
        if(!mintloglevel.equals(MintLogLevel.Debug)) goto _L2; else goto _L1
_L1:
        byte0 = 20;
_L4:
        return byte0;
_L2:
        if(mintloglevel.equals(MintLogLevel.Error))
            byte0 = 60;
        else
        if(mintloglevel.equals(MintLogLevel.Info))
            byte0 = 30;
        else
        if(!mintloglevel.equals(MintLogLevel.Verbose) && mintloglevel.equals(MintLogLevel.Warning))
            byte0 = 50;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final String getCarrier(Context context)
    {
        TelephonyManager telephonymanager = (TelephonyManager)context.getSystemService("phone");
        String s;
        if(telephonymanager != null)
        {
            s = null;
            if(telephonymanager.getSimState() == 5)
                s = telephonymanager.getSimOperatorName();
            if(s == null || s.length() == 0)
                s = telephonymanager.getNetworkOperatorName();
            if(s == null || s.length() == 0)
                s = "NA";
        } else
        {
            s = "NA";
        }
        return s;
    }

    protected static HashMap getConnectionInfo(Context context)
    {
        HashMap hashmap;
        hashmap = new HashMap(2);
        hashmap.put("connection", "NA");
        hashmap.put("state", "NA");
        if(context != null) goto _L2; else goto _L1
_L1:
        if(Mint.DEBUG)
            Logger.logError("Context in getConnection is null!");
_L4:
        return hashmap;
_L2:
        PackageManager packagemanager = context.getPackageManager();
        if(packagemanager == null)
        {
            if(Mint.DEBUG)
                Logger.logError("PackageManager in CheckNetworkConnection is null!");
        } else
        if(packagemanager.checkPermission("android.permission.ACCESS_NETWORK_STATE", Properties.APP_PACKAGE) == 0)
        {
            NetworkInfo networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if(networkinfo != null)
            {
                if(networkinfo.getSubtypeName() == null || networkinfo.getSubtypeName().length() == 0)
                    hashmap.put("connection", networkinfo.getTypeName());
                else
                    hashmap.put("connection", networkinfo.getSubtypeName());
                hashmap.put("state", networkinfo.getState().toString());
            } else
            {
                hashmap.put("connection", "No connection");
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static ExecutorService getExecutor()
    {
        if(executor == null)
            executor = Executors.newFixedThreadPool(1);
        return executor;
    }

    public static final HashMap getMemoryInfo()
    {
        HashMap hashmap;
        boolean flag;
        boolean flag1;
        hashmap = new HashMap(2);
        flag = false;
        flag1 = false;
        InputStream inputstream;
        StringBuilder stringbuilder;
        byte abyte0[];
        String as[] = new String[2];
        as[0] = "/system/bin/cat";
        as[1] = "/proc/meminfo";
        inputstream = (new ProcessBuilder(as)).start().getInputStream();
        stringbuilder = new StringBuilder();
        abyte0 = new byte[1024];
_L4:
        if(inputstream.read(abyte0) == -1) goto _L2; else goto _L1
_L1:
        String as1[];
        String s = new String(abyte0);
        stringbuilder.append(s);
        as1 = stringbuilder.toString().split("kB");
        if(as1.length < 2) goto _L4; else goto _L3
_L3:
        int i;
        int j;
        i = as1.length;
        j = 0;
_L8:
        if(j >= i) goto _L2; else goto _L5
_L5:
        String s1 = as1[j];
        if(!flag && s1.contains("MemTotal:"))
        {
            hashmap.put("memTotal", String.valueOf(Float.valueOf(s1.substring(s1.indexOf(" "), s1.lastIndexOf(" ")).trim()).floatValue() / 1024F));
            flag = true;
        }
        if(!flag1 && s1.contains("MemFree:"))
        {
            hashmap.put("memFree", String.valueOf(Float.valueOf(s1.substring(s1.indexOf(" "), s1.lastIndexOf(" ")).trim()).floatValue() / 1024F));
            flag1 = true;
        }
          goto _L6
_L2:
        inputstream.close();
_L7:
        return hashmap;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        if(true) goto _L7; else goto _L6
_L6:
        j++;
          goto _L8
    }

    protected static final String getMilisFromStart()
    {
        return String.valueOf(System.currentTimeMillis() - Properties.TIMESTAMP);
    }

    public static String getRandomSessionNumber()
    {
        String s = String.valueOf(System.currentTimeMillis());
        return s.substring(-8 + s.length(), s.length());
    }

    protected static String getScreenOrientation(Context context)
    {
        int i;
        String s;
        i = ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getOrientation();
        s = "NA";
        i;
        JVM INSTR tableswitch 0 3: default 52
    //                   0 54
    //                   1 75
    //                   2 61
    //                   3 68;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return s;
_L2:
        s = "Portrait";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "PortraitUpsideDown";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "LandscapeLeft";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "LandscapeRight";
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected static final String getTime()
    {
        String s = String.valueOf(System.currentTimeMillis());
        String s1 = String.valueOf(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000L);
        s = s1;
_L2:
        return s;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected static EnumStateStatus isGPSOn(Context context)
    {
        EnumStateStatus enumstatestatus = EnumStateStatus.ON;
        if(context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", Properties.APP_PACKAGE) == 0)
        {
            if(!((LocationManager)context.getSystemService("location")).isProviderEnabled("gps"))
                enumstatestatus = EnumStateStatus.OFF;
        } else
        {
            enumstatestatus = EnumStateStatus.NA;
        }
        return enumstatestatus;
    }

    public static final boolean isKitKat()
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT == 19)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String readFile(String s)
        throws Exception
    {
        StringBuilder stringbuilder;
        BufferedReader bufferedreader;
        if(s == null)
            throw new IllegalArgumentException("filePath Argument is null");
        stringbuilder = new StringBuilder();
        bufferedreader = null;
        BufferedReader bufferedreader1;
        Exception exception;
        Exception exception1;
        try
        {
            bufferedreader1 = new BufferedReader(new FileReader(s));
            break MISSING_BLOCK_LABEL_41;
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception1) { }
          goto _L1
        exception1;
        bufferedreader = bufferedreader1;
_L1:
        throw exception1;
        exception;
_L2:
        IOException ioexception1;
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception)
            {
                throw ioexception;
            }
        throw exception;
        do
        {
            String s1 = bufferedreader1.readLine();
            if(s1 == null)
                break;
            stringbuilder.append(s1);
        } while(true);
        if(bufferedreader1 != null)
            try
            {
                bufferedreader1.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception1)
            {
                throw ioexception1;
            }
        return stringbuilder.toString();
        exception;
        bufferedreader = bufferedreader1;
        if(true) goto _L2; else goto _L1
    }

    public static final String readLogs()
    {
        int i;
        String s;
        StringBuilder stringbuilder;
        i = Properties.LOG_LINES;
        if(i < 0)
            i = 100;
        s = Properties.LOG_FILTER;
        stringbuilder = new StringBuilder();
        ArrayList arraylist;
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((new StringBuilder()).append("logcat -d ").append(s).toString()).getInputStream()));
        arraylist = new ArrayList();
        do
        {
            String s2 = bufferedreader.readLine();
            if(s2 == null)
                break;
            arraylist.add(s2);
        } while(true);
          goto _L1
        Exception exception;
        exception;
        String s1;
        Logger.logError("Error reading logcat output!");
        s1 = exception.getMessage();
_L2:
        return s1;
_L1:
        int j;
        if(arraylist.size() == 0)
        {
            s1 = "You must add the android.permission.READ_LOGS permission to your manifest file!";
        } else
        {
            j = arraylist.size() - i;
            if(j < 0)
                j = 0;
            break MISSING_BLOCK_LABEL_237;
        }
          goto _L2
_L3:
        int k;
        String s3;
        for(; k < arraylist.size(); k++)
            stringbuilder.append((new StringBuilder()).append((String)arraylist.get(k)).append("\n").toString());

        s3 = stringbuilder.toString().replaceAll(Pattern.quote("}{^"), "}{ ^");
        s1 = s3;
          goto _L2
        k = j;
          goto _L3
    }

    protected static void setForceSendPingOnNextStart()
    {
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                File file;
                file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append(".setForceSendPingOnNextStart").toString());
                if(file == null || file.exists())
                    break MISSING_BLOCK_LABEL_50;
                file.createNewFile();
_L1:
                return;
                IOException ioexception;
                ioexception;
                ioexception.printStackTrace();
                  goto _L1
            }

        }
);
        ExecutorService executorservice = getExecutor();
        if(thread != null && executorservice != null)
            executorservice.submit(thread);
    }

    protected static void setLastPingSentTime(final Context ctx)
    {
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                if(ctx != null)
                {
                    SharedPreferences sharedpreferences = ctx.getSharedPreferences("Mint", 0);
                    if(sharedpreferences != null)
                        sharedpreferences.edit().putLong("LASTPINGTIME", System.currentTimeMillis()).commit();
                }
            }

            final Context val$ctx;

            
            {
                ctx = context;
                super();
            }
        }
);
        ExecutorService executorservice = getExecutor();
        if(thread != null && executorservice != null)
            executorservice.submit(thread);
    }

    /**
     * @deprecated Method shouldSendPing is deprecated
     */

    protected static boolean shouldSendPing(Context context)
    {
        com/splunk/mint/Utils;
        JVM INSTR monitorenter ;
        File file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append(".setForceSendPingOnNextStart").toString());
        if(file == null || !file.exists()) goto _L2; else goto _L1
_L1:
        file.delete();
        boolean flag = true;
_L4:
        com/splunk/mint/Utils;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = true;
        if(context == null) goto _L4; else goto _L3
_L3:
        SharedPreferences sharedpreferences = context.getSharedPreferences("Mint", 0);
        if(sharedpreferences == null) goto _L4; else goto _L5
_L5:
        long l = sharedpreferences.getLong("LASTPINGTIME", 0L);
        long l1 = System.currentTimeMillis();
        long l2 = 1000 * Properties.RemoteSettingsProps.sessionTime.intValue();
        if(l1 - l <= l2)
            break MISSING_BLOCK_LABEL_142;
        flag = true;
_L6:
        if(flag)
            setLastPingSentTime(context);
          goto _L4
        Exception exception;
        exception;
        throw exception;
        flag = false;
          goto _L6
    }

    protected static final String CONNECTION = "connection";
    private static final int Debug = 20;
    private static final int Error = 60;
    private static final int Info = 30;
    private static final String LASTPINGTIME = "LASTPINGTIME";
    protected static final String STATE = "state";
    private static final int Verbose = 10;
    private static final int Warning = 50;
    private static ExecutorService executor = null;
    private static final String forceSendPingFile = ".setForceSendPingOnNextStart";

}
