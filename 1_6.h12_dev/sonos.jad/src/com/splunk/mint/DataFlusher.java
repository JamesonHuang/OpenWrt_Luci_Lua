// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Referenced classes of package com.splunk.mint:
//            BaseExecutor, InterfaceExecutor, LowPriorityThreadFactory, Utils, 
//            Logger, Properties, SplunkFileFilter, NetSenderResponse, 
//            MintUrls, Mint, MintCallback, NetSender

class DataFlusher extends BaseExecutor
    implements InterfaceExecutor
{

    DataFlusher()
    {
    }

    public ExecutorService getExecutor()
    {
        if(executor == null)
            executor = Executors.newFixedThreadPool(1);
        return executor;
    }

    /**
     * @deprecated Method send is deprecated
     */

    public void send()
    {
        this;
        JVM INSTR monitorenter ;
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                if(Utils.allowedToSendData()) goto _L2; else goto _L1
_L1:
                Logger.logInfo("You have enabled the FlushOnlyOverWiFi option and there is no WiFi connection, data will not be sent now.");
_L4:
                return;
_L2:
                if(Properties.FILES_PATH == null) goto _L4; else goto _L3
_L3:
                File afile[];
                int i;
                int j;
                afile = (new File(Properties.FILES_PATH)).listFiles(SplunkFileFilter.getInstance());
                i = afile.length;
                j = 0;
_L6:
                if(j >= i) goto _L4; else goto _L5
_L5:
                File file;
                NetSenderResponse netsenderresponse;
                file = afile[j];
                netsenderresponse = new NetSenderResponse(MintUrls.getURL(), null);
                if(!file.exists())
                {
                    netsenderresponse.setException(new Exception("There is no data to be sent. This is not an error."));
                    netsenderresponse.setSentSuccessfully(Boolean.valueOf(false));
                } else
                {
label0:
                    {
                        if(file.length() <= 0x2dc6c0L)
                            break label0;
                        file.delete();
                        netsenderresponse.setException(new Exception("File was too big, this shouldn't have happened since we split the data."));
                        netsenderresponse.setSentSuccessfully(Boolean.valueOf(false));
                    }
                }
_L7:
                j++;
                  goto _L6
                  goto _L4
                String s = null;
                String s1 = Utils.readFile(file.getAbsolutePath());
                s = s1;
_L8:
                Exception exception1;
                if(s == null || s.length() == 0)
                {
                    if(Mint.mintCallback != null)
                        Mint.mintCallback.netSenderResponse(netsenderresponse);
                } else
                if((new NetSender()).sendBlocking(null, s, false).getSentSuccessfully().booleanValue())
                    file.delete();
                  goto _L7
                exception1;
                netsenderresponse.setException(exception1);
                netsenderresponse.setSentSuccessfully(Boolean.valueOf(false));
                exception1.printStackTrace();
                if(Mint.mintCallback != null)
                    Mint.mintCallback.netSenderResponse(netsenderresponse);
                  goto _L8
            }

            final DataFlusher this$0;

            
            {
                this$0 = DataFlusher.this;
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

    private static final int MAX_FILE_SIZE = 0x2dc6c0;
}
