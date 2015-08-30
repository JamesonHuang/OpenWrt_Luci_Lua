// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.os.Handler;
import android.os.Process;
import java.io.*;

// Referenced classes of package com.sonos.acr.util:
//            StringUtils, SLog

public class ShellCommand
{
    public class CommandResult
    {

        public boolean success()
        {
            boolean flag;
            if(exit_value != null && exit_value.intValue() == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            return (new StringBuilder()).append("StdOut: ").append(stdout.replace("\n", " [NL] ")).append(" EC: ").append(exit_value).toString();
        }

        public final Integer exit_value;
        public final String stdout;
        final ShellCommand this$0;

        CommandResult(Integer integer, String s)
        {
            this$0 = ShellCommand.this;
            super();
            exit_value = integer;
            stdout = s;
        }
    }

    public static interface CommandCallback
    {

        public abstract void onComplete(ShellCommand shellcommand, CommandResult commandresult);
    }

    class ReaderThread extends Thread
    {

        public void run()
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(is));
_L1:
            String s;
            try
            {
                do
                {
                    s = bufferedreader.readLine();
                    if(s == null)
                        break MISSING_BLOCK_LABEL_115;
                    SLog.i("ShellCommand", (new StringBuilder()).append("Read Line: ").append(s).toString());
                    if(!s.startsWith("EXIT EC:"))
                        break MISSING_BLOCK_LABEL_151;
                    receivedExitCode(currentStrings.toString(), Integer.valueOf(s.replace("EXIT EC:", "").trim()).intValue());
                    currentStrings = new StringBuffer();
                } while(true);
            }
            catch(Exception exception)
            {
                SLog.e("ShellCommand", "Error Reading Input Stream", exception);
            }
            SLog.d("ShellCommand", "Done Reading input Stream");
            process = null;
            stdOutReader = null;
            receivedExitCode("PROCESS DIED", 255);
            return;
            currentStrings.append(s).append('\n');
              goto _L1
        }

        StringBuffer currentStrings;
        InputStream is;
        final ShellCommand this$0;

        public ReaderThread(InputStream inputstream)
        {
            this$0 = ShellCommand.this;
            super("StreamReaderThread");
            currentStrings = new StringBuffer();
            is = inputstream;
        }
    }


    public ShellCommand()
    {
        process = null;
        stdOutReader = null;
        handler = new Handler();
    }

    public static void killAll()
    {
        ShellCommand shellcommand;
        CommandResult commandresult;
        String s;
        shellcommand = new ShellCommand();
        commandresult = shellcommand.runWaitFor("ps");
        s = (new StringBuilder()).append(" ").append(Process.myPid()).append(" ").toString();
        if(!commandresult.success()) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        int j;
        as = commandresult.stdout.split("\\n");
        i = as.length;
        j = 0;
_L11:
        if(j >= i) goto _L2; else goto _L3
_L3:
        String s1 = as[j];
        if((!s1.contains(s) || s1.contains("com.sonos")) && !s1.contains("netcfg")) goto _L5; else goto _L4
_L4:
        String as1[];
        int k;
        int l;
        as1 = s1.split(" ");
        k = as1.length;
        l = 0;
_L9:
        if(l >= k) goto _L5; else goto _L6
_L6:
        String s2 = as1[l];
        if(!StringUtils.isLong(s2)) goto _L8; else goto _L7
_L7:
        shellcommand.runWaitFor((new StringBuilder()).append("kill -9 ").append(s2).toString());
_L5:
        j++;
        continue; /* Loop/switch isn't completed */
_L8:
        l++;
        if(true) goto _L9; else goto _L2
_L2:
        return;
        if(true) goto _L11; else goto _L10
_L10:
    }

    /**
     * @deprecated Method receivedExitCode is deprecated
     */

    private void receivedExitCode(String s, int i)
    {
        this;
        JVM INSTR monitorenter ;
        result = new CommandResult(Integer.valueOf(i), s);
        SLog.d("ShellCommand", (new StringBuilder()).append("Received Exit Code!!").append(result).toString());
        notify();
        if(callback != null)
            handler.post(new Runnable() {

                public void run()
                {
                    ShellCommand shellcommand = ShellCommand.this;
                    shellcommand;
                    JVM INSTR monitorenter ;
                    CommandResult commandresult = result;
                    CommandCallback commandcallback = callback;
                    result = null;
                    callback = null;
                    if(commandcallback != null)
                        commandcallback.onComplete(ShellCommand.this, commandresult);
                    return;
                }

                final ShellCommand this$0;

            
            {
                this$0 = ShellCommand.this;
                super();
            }
            }
);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void run(String s)
        throws IOException
    {
        if(process == null)
        {
            ProcessBuilder processbuilder = (new ProcessBuilder(new String[0])).directory(new File("/system/bin/")).redirectErrorStream(true);
            String as[] = new String[1];
            as[0] = "su";
            process = processbuilder.command(as).start();
            stdOutReader = new ReaderThread(process.getInputStream());
            stdOutReader.start();
        }
        SLog.d("ShellCommand", (new StringBuilder()).append("Executing Command: ").append(s).toString());
        OutputStream outputstream = process.getOutputStream();
        outputstream.write((new StringBuilder()).append(s).append("\necho ").append("EXIT EC:").append("$?\n").toString().getBytes());
        outputstream.flush();
    }

    /**
     * @deprecated Method runAndNotify is deprecated
     */

    public void runAndNotify(String s, CommandCallback commandcallback)
    {
        this;
        JVM INSTR monitorenter ;
        callback = commandcallback;
        runWaitFor(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method runWaitFor is deprecated
     */

    public CommandResult runWaitFor(String s)
    {
        this;
        JVM INSTR monitorenter ;
        SLog.e("ShellCommand", (new StringBuilder()).append("My Process Id: ").append(Process.myPid()).toString());
        run(s);
        if(callback == null)
            wait();
_L1:
        CommandResult commandresult;
        commandresult = result;
        result = null;
        this;
        JVM INSTR monitorexit ;
        return commandresult;
        Exception exception1;
        exception1;
        SLog.e("ShellCommand", "exception writing the data", exception1);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public static final String EXIT_STR = "EXIT EC:";
    static final String TAG = "ShellCommand";
    CommandCallback callback;
    Handler handler;
    Process process;
    CommandResult result;
    ReaderThread stdOutReader;

}
