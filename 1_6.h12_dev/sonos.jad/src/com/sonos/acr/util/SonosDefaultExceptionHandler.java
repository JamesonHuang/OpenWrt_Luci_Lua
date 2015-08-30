// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.os.Debug;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package com.sonos.acr.util:
//            SLog, DbgProp

public class SonosDefaultExceptionHandler
    implements Thread.UncaughtExceptionHandler
{

    public SonosDefaultExceptionHandler()
    {
        if(defaultUEH == null)
            defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    private String dumpLogToString(String s)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        try
        {
            Process process = Runtime.getRuntime().exec(s);
            readAndDumpStream(bytearrayoutputstream, process.getInputStream());
            System.out.println((new StringBuilder()).append("Finished: '").append(s).append("' exitVal: ").append(process.exitValue()).toString());
        }
        catch(Exception exception)
        {
            SLog.e("SonosDefaultExceptionHandler", "dumpLogToFile", exception);
        }
        return bytearrayoutputstream.toString();
    }

    private void readAndDumpStream(OutputStream outputstream, InputStream inputstream)
        throws IOException
    {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        do
        {
            String s = bufferedreader.readLine();
            if(s != null)
            {
                outputstream.write(s.getBytes());
                outputstream.write(LINE_SEPARATOR.getBytes());
            } else
            {
                return;
            }
        } while(true);
    }

    private void writeDumpFiles(Throwable throwable)
    {
        timeStamp = (new SimpleDateFormat("MMddyyyyHHmmssSSS")).format(new Date());
        System.out.println((new StringBuilder()).append("The path for Stacktrace is:").append(getStackTraceFilename()).toString());
        System.out.println((new StringBuilder()).append("The path for memdumb is:").append(getMemdumpFilename()).toString());
        StackTraceElement astacktraceelement[] = throwable.getStackTrace();
        String s = (new StringBuilder()).append(throwable.toString()).append("\n\n").toString();
        String s1 = (new StringBuilder()).append(s).append("--------- Stack trace ---------\n\n").toString();
        int i = astacktraceelement.length;
        for(int j = 0; j < i; j++)
        {
            StackTraceElement stacktraceelement = astacktraceelement[j];
            s1 = (new StringBuilder()).append(s1).append("    ").append(stacktraceelement.toString()).append("\n").toString();
        }

        String s2 = (new StringBuilder()).append(s1).append("-------------------------------\n\n").toString();
        String s3 = (new StringBuilder()).append(s2).append("--------- Cause ---------\n\n").toString();
        Throwable throwable1 = throwable.getCause();
        if(throwable1 != null)
        {
            s3 = (new StringBuilder()).append(s3).append(throwable1.toString()).append("\n\n").toString();
            StackTraceElement astacktraceelement1[] = throwable1.getStackTrace();
            for(int k = 0; k < astacktraceelement1.length; k++)
                s3 = (new StringBuilder()).append(s3).append("    ").append(astacktraceelement1[k].toString()).append("\n").toString();

        }
        String s4 = (new StringBuilder()).append(s3).append("-------------------------------\n\n").toString();
        try
        {
            File file = DbgProp.getSonosDebugDir();
            if(file.canWrite())
            {
                file.mkdirs();
                System.out.println("Writing to sd card!");
                FileOutputStream fileoutputstream = new FileOutputStream((new StringBuilder()).append(file.getAbsolutePath()).append("/").append(getStackTraceFilename()).toString());
                fileoutputstream.write(s4.getBytes());
                dumpLogToFile(fileoutputstream, "logcat -d -v threadtime");
                dumpLogToFile(fileoutputstream, "dmesg");
                fileoutputstream.close();
                System.gc();
                Debug.dumpHprofData((new StringBuilder()).append(file.getAbsolutePath()).append("/").append(getMemdumpFilename()).toString());
            } else
            {
                System.out.println("Unable to write to sd card!");
            }
        }
        catch(Throwable throwable2)
        {
            SLog.e("SonosDefaultExceptionHandler", (new StringBuilder()).append("Unable to write logs: ").append(throwable2).toString());
            throwable2.printStackTrace();
        }
    }

    protected void dumpLogToFile(FileOutputStream fileoutputstream, String s)
    {
        fileoutputstream.write(dumpLogToString(s).getBytes());
_L1:
        return;
        Exception exception;
        exception;
        SLog.e("SonosDefaultExceptionHandler", "dumpLogToFile", exception);
          goto _L1
    }

    public String getMemdumpFilename()
    {
        return (new StringBuilder()).append("memdump_").append(timeStamp).toString();
    }

    public String getStackTraceFilename()
    {
        return (new StringBuilder()).append("stacktrace_").append(timeStamp).toString();
    }

    public void uncaughtException(Thread thread, Throwable throwable)
    {
        if(DbgProp.get("exceptionDump"))
            writeDumpFiles(throwable);
        SLog.e("SonosDefaultExceptionHandler", throwable.getMessage(), throwable);
        defaultUEH.uncaughtException(thread, throwable);
    }

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String MEMDUMP_FILENAME = "memdump";
    private static final String STACK_TRACE_FILENAME = "stacktrace";
    private static final String TAG = "SonosDefaultExceptionHandler";
    private Thread.UncaughtExceptionHandler defaultUEH;
    private String timeStamp;

}
