// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Referenced classes of package com.splunk.mint:
//            BaseExecutor, InterfaceExecutor, Properties, Utils, 
//            Logger, Mint, LowPriorityThreadFactory, SplunkFileFilter, 
//            DataSaverResponse, MintCallback, DataFlusher

class DataSaver extends BaseExecutor
    implements InterfaceExecutor
{

    DataSaver()
    {
    }

    /**
     * @deprecated Method getLastSavedName is deprecated
     */

    private String getLastSavedName()
    {
        this;
        JVM INSTR monitorenter ;
        File file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/Mint-lastsavedfile").toString());
        String s1 = Utils.readFile(file.getAbsolutePath()).trim();
        String s = s1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception1;
        exception1;
        s = null;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method saveLastSavedName is deprecated
     */

    private void saveLastSavedName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        File file;
        boolean flag;
        file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/Mint-lastsavedfile").toString());
        if(file == null)
            break MISSING_BLOCK_LABEL_56;
        flag = file.exists();
        if(flag)
            break MISSING_BLOCK_LABEL_56;
        file.delete();
        file.createNewFile();
_L1:
        BufferedWriter bufferedwriter = null;
        BufferedWriter bufferedwriter1 = new BufferedWriter(new FileWriter(file));
        bufferedwriter1.write(s.trim());
        bufferedwriter1.flush();
        bufferedwriter1.close();
        if(bufferedwriter1 == null)
            break MISSING_BLOCK_LABEL_105;
        bufferedwriter1.close();
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        IOException ioexception4;
        ioexception4;
        ioexception4.printStackTrace();
          goto _L1
        Exception exception;
        exception;
        throw exception;
        IOException ioexception3;
        ioexception3;
        ioexception3.printStackTrace();
          goto _L2
        IOException ioexception;
        ioexception;
_L6:
        Logger.logWarning("There was a problem saving the last saved file name");
        if(Mint.DEBUG)
            ioexception.printStackTrace();
        if(bufferedwriter == null) goto _L2; else goto _L3
_L3:
        bufferedwriter.close();
          goto _L2
        IOException ioexception2;
        ioexception2;
        ioexception2.printStackTrace();
          goto _L2
        Exception exception1;
        exception1;
_L5:
        if(bufferedwriter == null)
            break MISSING_BLOCK_LABEL_186;
        try
        {
            bufferedwriter.close();
        }
        catch(IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        throw exception1;
        exception1;
        bufferedwriter = bufferedwriter1;
        if(true) goto _L5; else goto _L4
_L4:
        ioexception;
        bufferedwriter = bufferedwriter1;
          goto _L6
    }

    public ExecutorService getExecutor()
    {
        if(executor == null)
            executor = Executors.newFixedThreadPool(1);
        return executor;
    }

    /**
     * @deprecated Method save is deprecated
     */

    public void save(final String jsonData)
    {
        this;
        JVM INSTR monitorenter ;
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                boolean flag;
                DataSaverResponse datasaverresponse;
                BufferedWriter bufferedwriter;
                BufferedWriter bufferedwriter1;
                String s = getLastSavedName();
                File file;
                if(s == null || s.length() == 0)
                    file = new File(SplunkFileFilter.createNewFile());
                else
                    file = new File((new StringBuilder()).append(Properties.FILES_PATH).append("/").append(s).toString());
                flag = false;
                if(file.length() >= 0x222e0L)
                {
                    file = new File(SplunkFileFilter.createNewFile());
                    flag = true;
                }
                datasaverresponse = new DataSaverResponse(jsonData, file.getAbsolutePath());
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
                bufferedwriter1 = new BufferedWriter(new FileWriter(file, true));
                bufferedwriter1.append(jsonData);
                bufferedwriter1.flush();
                bufferedwriter1.close();
                saveLastSavedName(file.getName());
                if(bufferedwriter1 != null)
                    try
                    {
                        bufferedwriter1.close();
                    }
                    catch(IOException ioexception3)
                    {
                        ioexception3.printStackTrace();
                    }
                datasaverresponse.setSavedSuccessfully(Boolean.valueOf(true));
                if(Mint.mintCallback != null)
                    Mint.mintCallback.dataSaverResponse(datasaverresponse);
                if(flag)
                    (new DataFlusher()).send();
_L1:
                return;
                IOException ioexception;
                ioexception;
_L4:
                ioexception.printStackTrace();
                datasaverresponse.setException(ioexception);
                datasaverresponse.setSavedSuccessfully(Boolean.valueOf(false));
                if(Mint.mintCallback != null)
                    Mint.mintCallback.dataSaverResponse(datasaverresponse);
                if(bufferedwriter != null)
                    try
                    {
                        bufferedwriter.close();
                    }
                    catch(IOException ioexception2)
                    {
                        ioexception2.printStackTrace();
                    }
                datasaverresponse.setSavedSuccessfully(Boolean.valueOf(true));
                if(Mint.mintCallback != null)
                    Mint.mintCallback.dataSaverResponse(datasaverresponse);
                if(flag)
                    (new DataFlusher()).send();
                  goto _L1
                Exception exception1;
                exception1;
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
                datasaverresponse.setSavedSuccessfully(Boolean.valueOf(true));
                if(Mint.mintCallback != null)
                    Mint.mintCallback.dataSaverResponse(datasaverresponse);
                if(flag)
                    (new DataFlusher()).send();
                throw exception1;
                exception1;
                bufferedwriter = bufferedwriter1;
                if(true) goto _L3; else goto _L2
_L2:
                ioexception;
                bufferedwriter = bufferedwriter1;
                  goto _L4
            }

            final DataSaver this$0;
            final String val$jsonData;

            
            {
                this$0 = DataSaver.this;
                jsonData = s;
                super();
            }
        }
);
        if(getExecutor() != null)
            getExecutor().execute(thread);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String LAST_SAVED_NAME = "/Mint-lastsavedfile";
    private static final int MAX_FILE_SIZE = 0x222e0;


}
