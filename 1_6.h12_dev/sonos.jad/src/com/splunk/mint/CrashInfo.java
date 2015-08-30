// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Referenced classes of package com.splunk.mint:
//            BaseExecutor, InterfaceExecutor, Properties, Logger, 
//            Mint, LowPriorityThreadFactory

class CrashInfo extends BaseExecutor
    implements InterfaceExecutor
{

    CrashInfo()
    {
    }

    protected static String getLastCrashID()
    {
        File file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append("lastCrashID").toString());
        if(file == null || file.exists()) goto _L2; else goto _L1
_L1:
        file.createNewFile();
        String s = null;
_L3:
        return s;
        IOException ioexception3;
        ioexception3;
        ioexception3.printStackTrace();
_L2:
        BufferedReader bufferedreader = null;
        BufferedReader bufferedreader1 = new BufferedReader(new FileReader(file));
        String s1 = bufferedreader1.readLine().trim();
        s = s1;
_L4:
        if(bufferedreader1 != null)
            try
            {
                bufferedreader1.close();
            }
            catch(IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
          goto _L3
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        s = null;
          goto _L4
        Exception exception2;
        exception2;
_L7:
        Logger.logWarning("There was a problem getting the last crash id");
        if(Mint.DEBUG)
            exception2.printStackTrace();
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        s = null;
          goto _L3
        Exception exception;
        exception;
_L6:
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        throw exception;
        exception;
        bufferedreader = bufferedreader1;
        if(true) goto _L6; else goto _L5
_L5:
        exception2;
        bufferedreader = bufferedreader1;
          goto _L7
    }

    protected static int getTotalCrashesNum()
    {
        int i;
        Integer integer;
        i = 0;
        integer = Integer.valueOf(0);
        if(Properties.FILES_PATH != null) goto _L2; else goto _L1
_L1:
        Logger.logWarning("Please use getTotalCrashesNum after initializing the plugin! Returning 0.");
_L3:
        return i;
_L2:
        File file;
        file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append("crashCounter").toString());
        if(file == null || file.exists())
            break MISSING_BLOCK_LABEL_89;
        int k;
        file.createNewFile();
        k = integer.intValue();
        i = k;
          goto _L3
        IOException ioexception3;
        ioexception3;
        ioexception3.printStackTrace();
        BufferedReader bufferedreader = null;
        BufferedReader bufferedreader1 = new BufferedReader(new FileReader(file));
        Integer integer3 = Integer.valueOf(Integer.parseInt(bufferedreader1.readLine().trim()));
        Integer integer2 = integer3;
_L4:
        int j = integer2.intValue();
        i = j;
        if(bufferedreader1 != null)
            try
            {
                bufferedreader1.close();
            }
            catch(IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
          goto _L3
        Exception exception1;
        exception1;
        Integer integer1 = Integer.valueOf(0);
        integer2 = integer1;
          goto _L4
        Exception exception2;
        exception2;
_L7:
        Logger.logWarning("There was a problem getting the crash counter");
        if(Mint.DEBUG)
            exception2.printStackTrace();
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
          goto _L3
        Exception exception;
        exception;
_L6:
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        throw exception;
        exception;
        bufferedreader = bufferedreader1;
        if(true) goto _L6; else goto _L5
_L5:
        exception2;
        bufferedreader = bufferedreader1;
          goto _L7
    }

    protected void clearCrashCounter()
    {
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                File file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append("crashCounter").toString());
                if(file != null && file.exists())
                    file.delete();
            }

            final CrashInfo this$0;

            
            {
                this$0 = CrashInfo.this;
                super();
            }
        }
);
        ExecutorService executorservice = getExecutor();
        if(thread != null && executorservice != null)
            executorservice.submit(thread);
    }

    public ExecutorService getExecutor()
    {
        if(executor == null)
            executor = Executors.newFixedThreadPool(1);
        return executor;
    }

    protected void saveCrashCounter()
    {
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                BufferedReader bufferedreader;
                BufferedWriter bufferedwriter;
                BufferedReader bufferedreader1;
                Integer integer1;
                BufferedWriter bufferedwriter1;
                File file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append("crashCounter").toString());
                Integer integer2;
                Integer integer3;
                if(file != null && !file.exists())
                    try
                    {
                        file.createNewFile();
                    }
                    catch(IOException ioexception7)
                    {
                        ioexception7.printStackTrace();
                    }
                bufferedreader = null;
                bufferedwriter = null;
                bufferedreader1 = new BufferedReader(new FileReader(file));
                Integer.valueOf(0);
                integer3 = Integer.valueOf(Integer.parseInt(bufferedreader1.readLine().trim()));
                integer1 = integer3;
_L1:
                integer2 = Integer.valueOf(1 + integer1.intValue());
                bufferedwriter1 = new BufferedWriter(new FileWriter(file));
                bufferedwriter1.write(String.valueOf(integer2));
                bufferedwriter1.newLine();
                bufferedwriter1.flush();
                bufferedwriter1.close();
                Exception exception1;
                Integer integer;
                if(bufferedreader1 != null)
                    try
                    {
                        bufferedreader1.close();
                    }
                    catch(IOException ioexception6)
                    {
                        ioexception6.printStackTrace();
                    }
                if(bufferedwriter1 == null)
                    break MISSING_BLOCK_LABEL_170;
                bufferedwriter1.close();
_L2:
                return;
                exception1;
                integer = Integer.valueOf(0);
                integer1 = integer;
                  goto _L1
                IOException ioexception5;
                ioexception5;
                ioexception5.printStackTrace();
                  goto _L2
                IOException ioexception;
                ioexception;
_L5:
                Logger.logWarning("There was a problem saving the crash counter");
                if(Mint.DEBUG)
                    ioexception.printStackTrace();
                if(bufferedreader != null)
                    try
                    {
                        bufferedreader.close();
                    }
                    catch(IOException ioexception4)
                    {
                        ioexception4.printStackTrace();
                    }
                if(bufferedwriter != null)
                    try
                    {
                        bufferedwriter.close();
                    }
                    catch(IOException ioexception3)
                    {
                        ioexception3.printStackTrace();
                    }
                  goto _L2
                Exception exception;
                exception;
_L4:
                if(bufferedreader != null)
                    try
                    {
                        bufferedreader.close();
                    }
                    catch(IOException ioexception2)
                    {
                        ioexception2.printStackTrace();
                    }
                if(bufferedwriter != null)
                    try
                    {
                        bufferedwriter.close();
                    }
                    catch(IOException ioexception1)
                    {
                        ioexception1.printStackTrace();
                    }
                throw exception;
                exception;
                bufferedreader = bufferedreader1;
                continue; /* Loop/switch isn't completed */
                exception;
                bufferedwriter = bufferedwriter1;
                bufferedreader = bufferedreader1;
                if(true) goto _L4; else goto _L3
_L3:
                ioexception;
                bufferedreader = bufferedreader1;
                  goto _L5
                ioexception;
                bufferedwriter = bufferedwriter1;
                bufferedreader = bufferedreader1;
                  goto _L5
            }

            final CrashInfo this$0;

            
            {
                this$0 = CrashInfo.this;
                super();
            }
        }
);
        ExecutorService executorservice = getExecutor();
        if(thread != null && executorservice != null)
            executorservice.submit(thread);
    }

    protected void saveLastCrashID(final String lastID)
    {
        if(lastID != null)
        {
            Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

                public void run()
                {
                    BufferedWriter bufferedwriter;
                    BufferedWriter bufferedwriter1;
                    File file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append("lastCrashID").toString());
                    if(file != null && !file.exists())
                        try
                        {
                            file.createNewFile();
                        }
                        catch(IOException ioexception4)
                        {
                            ioexception4.printStackTrace();
                        }
                    bufferedwriter = null;
                    bufferedwriter1 = new BufferedWriter(new FileWriter(file));
                    bufferedwriter1.write(lastID);
                    bufferedwriter1.newLine();
                    bufferedwriter1.flush();
                    bufferedwriter1.close();
                    if(bufferedwriter1 == null)
                        break MISSING_BLOCK_LABEL_96;
                    bufferedwriter1.close();
_L1:
                    return;
                    IOException ioexception3;
                    ioexception3;
                    ioexception3.printStackTrace();
                      goto _L1
                    IOException ioexception;
                    ioexception;
_L4:
                    Logger.logWarning("There was a problem saving the last crash id");
                    if(Mint.DEBUG)
                        ioexception.printStackTrace();
                    if(bufferedwriter != null)
                        try
                        {
                            bufferedwriter.close();
                        }
                        catch(IOException ioexception2)
                        {
                            ioexception2.printStackTrace();
                        }
                      goto _L1
                    Exception exception;
                    exception;
_L3:
                    if(bufferedwriter != null)
                        try
                        {
                            bufferedwriter.close();
                        }
                        catch(IOException ioexception1)
                        {
                            ioexception1.printStackTrace();
                        }
                    throw exception;
                    exception;
                    bufferedwriter = bufferedwriter1;
                    if(true) goto _L3; else goto _L2
_L2:
                    ioexception;
                    bufferedwriter = bufferedwriter1;
                      goto _L4
                }

                final CrashInfo this$0;
                final String val$lastID;

            
            {
                this$0 = CrashInfo.this;
                lastID = s;
                super();
            }
            }
);
            ExecutorService executorservice = getExecutor();
            if(thread != null && executorservice != null)
                executorservice.submit(thread);
        }
    }

    private static final String crashCounterFile = "crashCounter";
    private static final String lastCrashIDFile = "lastCrashID";
}
