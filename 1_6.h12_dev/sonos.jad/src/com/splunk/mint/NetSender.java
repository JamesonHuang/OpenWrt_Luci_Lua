// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

// Referenced classes of package com.splunk.mint:
//            BaseExecutor, InterfaceExecutor, EnumActionType, LowPriorityThreadFactory, 
//            NetSenderResponse, Mint, MintCallback, Logger, 
//            MintUrls, DataSaver

class NetSender extends BaseExecutor
    implements InterfaceExecutor
{

    NetSender()
    {
    }

    private int findAllActions(String s)
    {
        Matcher matcher = Pattern.compile("\\{\\^[0-9]+?\\^[a-z]+?\\^[0-9]+?\\}").matcher(s);
        int i;
        for(i = 0; matcher.find(); i++);
        return i;
    }

    private int findAllErrors(String s)
    {
        Matcher matcher = Pattern.compile((new StringBuilder()).append("\\^").append(EnumActionType.error.toString()).append("\\^").toString()).matcher(s);
        int i;
        for(i = 0; matcher.find(); i++);
        return i;
    }

    public ExecutorService getExecutor()
    {
        if(executor == null)
            executor = Executors.newFixedThreadPool(2);
        return executor;
    }

    /**
     * @deprecated Method send is deprecated
     */

    public void send(final String data, final boolean saveOnFail)
    {
        this;
        JVM INSTR monitorenter ;
        Thread thread = (new LowPriorityThreadFactory()).newThread(new Runnable() {

            public void run()
            {
                sendBlocking(data, saveOnFail);
            }

            final NetSender this$0;
            final String val$data;
            final boolean val$saveOnFail;

            
            {
                this$0 = NetSender.this;
                data = s;
                saveOnFail = flag;
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

    /**
     * @deprecated Method sendBlocking is deprecated
     */

    public NetSenderResponse sendBlocking(String s, String s1, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        NetSenderResponse netsenderresponse = new NetSenderResponse(s, s1);
        if(s1 != null) goto _L2; else goto _L1
_L1:
        netsenderresponse.setException(new IllegalArgumentException("null data!"));
        if(Mint.mintCallback != null)
            Mint.mintCallback.netSenderResponse(netsenderresponse);
        Logger.logInfo(netsenderresponse.toString());
_L5:
        this;
        JVM INSTR monitorexit ;
        return netsenderresponse;
_L2:
        int j;
        int k;
        if(s != null)
            break MISSING_BLOCK_LABEL_104;
        j = 0;
        k = 0;
        int l = findAllActions(s1);
        if(l > 0)
        {
            j = findAllErrors(s1);
            k = l - j;
        }
        s = MintUrls.getURL(j, k);
        DefaultHttpClient defaulthttpclient;
        HttpPost httppost;
        Logger.logInfo((new StringBuilder()).append("NetSender: Sending data to url: ").append(s).toString());
        defaulthttpclient = new DefaultHttpClient();
        org.apache.http.params.HttpParams httpparams = defaulthttpclient.getParams();
        HttpProtocolParams.setUseExpectContinue(httpparams, false);
        HttpConnectionParams.setConnectionTimeout(httpparams, 20000);
        HttpConnectionParams.setSoTimeout(httpparams, 20000);
        httppost = new HttpPost(s);
        httppost.setHeader("Content-Type", "application/x-gzip");
        HttpResponse httpresponse;
        ByteArrayOutputStream bytearrayoutputstream;
        GZIPOutputStream gzipoutputstream;
        httpresponse = null;
        bytearrayoutputstream = null;
        gzipoutputstream = null;
        ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
        GZIPOutputStream gzipoutputstream1 = new GZIPOutputStream(bytearrayoutputstream1) {

            final NetSender this$0;

            
            {
                this$0 = NetSender.this;
                super(outputstream);
                def.setLevel(9);
            }
        }
;
        HttpEntity httpentity;
        int i;
        gzipoutputstream1.write(s1.getBytes());
        gzipoutputstream1.close();
        httppost.setEntity(new ByteArrayEntity(bytearrayoutputstream1.toByteArray()));
        httpresponse = defaulthttpclient.execute(httppost);
        httpentity = httpresponse.getEntity();
        i = httpresponse.getStatusLine().getStatusCode();
        netsenderresponse.setResponseCode(i);
        if(httpentity != null || i < 400) goto _L4; else goto _L3
_L3:
        netsenderresponse.setException(new Exception(httpresponse.getStatusLine().getReasonPhrase()));
        if(Mint.mintCallback != null)
            Mint.mintCallback.netSenderResponse(netsenderresponse);
_L6:
        if(bytearrayoutputstream1 == null)
            break MISSING_BLOCK_LABEL_350;
        Exception exception;
        Exception exception1;
        Exception exception2;
        try
        {
            bytearrayoutputstream1.close();
        }
        catch(IOException ioexception5) { }
        if(gzipoutputstream1 == null)
            break MISSING_BLOCK_LABEL_360;
        try
        {
            gzipoutputstream1.close();
        }
        catch(IOException ioexception4) { }
        if(Mint.mintCallback != null)
            Mint.mintCallback.netSenderResponse(netsenderresponse);
          goto _L5
        exception;
        throw exception;
_L4:
        if(httpentity == null)
            break MISSING_BLOCK_LABEL_433;
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(httpentity.getContent()));
        String s2 = bufferedreader.readLine();
        bufferedreader.close();
        netsenderresponse.setServerResponse(s2);
        netsenderresponse.setSentSuccessfully(Boolean.valueOf(true));
          goto _L6
        exception2;
        gzipoutputstream = gzipoutputstream1;
        bytearrayoutputstream = bytearrayoutputstream1;
_L12:
        Logger.logError((new StringBuilder()).append("NetSender: Transmitting Exception ").append(exception2.getMessage()).toString());
        if(Mint.DEBUG)
            exception2.printStackTrace();
        if(httpresponse != null)
            netsenderresponse.setResponseCode(httpresponse.getStatusLine().getStatusCode());
        netsenderresponse.setException(exception2);
        if(Mint.mintCallback != null)
            Mint.mintCallback.netSenderResponse(netsenderresponse);
        if(flag)
        {
            Logger.logWarning("NetSender: Couldn't send data, saving...");
            (new DataSaver()).save(s1);
        }
        if(bytearrayoutputstream == null) goto _L8; else goto _L7
_L7:
        try
        {
            bytearrayoutputstream.close();
        }
        catch(IOException ioexception3) { }
_L8:
        if(gzipoutputstream == null) goto _L5; else goto _L9
_L9:
        try
        {
            gzipoutputstream.close();
        }
        catch(IOException ioexception2) { }
          goto _L5
        exception1;
_L11:
        if(bytearrayoutputstream == null)
            break MISSING_BLOCK_LABEL_599;
        try
        {
            bytearrayoutputstream.close();
        }
        catch(IOException ioexception1) { }
        if(gzipoutputstream == null)
            break MISSING_BLOCK_LABEL_609;
        try
        {
            gzipoutputstream.close();
        }
        catch(IOException ioexception) { }
        throw exception1;
        exception1;
        bytearrayoutputstream = bytearrayoutputstream1;
        continue; /* Loop/switch isn't completed */
        exception1;
        gzipoutputstream = gzipoutputstream1;
        bytearrayoutputstream = bytearrayoutputstream1;
        if(true) goto _L11; else goto _L10
_L10:
        exception2;
          goto _L12
        exception2;
        bytearrayoutputstream = bytearrayoutputstream1;
          goto _L12
    }

    /**
     * @deprecated Method sendBlocking is deprecated
     */

    public NetSenderResponse sendBlocking(String s, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        NetSenderResponse netsenderresponse = sendBlocking(null, s, flag);
        this;
        JVM INSTR monitorexit ;
        return netsenderresponse;
        Exception exception;
        exception;
        throw exception;
    }
}
