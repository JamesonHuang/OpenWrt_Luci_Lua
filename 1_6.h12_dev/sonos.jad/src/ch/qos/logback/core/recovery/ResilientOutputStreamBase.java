// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.recovery;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.status.*;
import java.io.*;

// Referenced classes of package ch.qos.logback.core.recovery:
//            RecoveryCoordinator

public abstract class ResilientOutputStreamBase extends OutputStream
{

    public ResilientOutputStreamBase()
    {
        noContextWarning = 0;
        statusCount = 0;
        presumedClean = true;
    }

    private boolean isPresumedInError()
    {
        boolean flag;
        if(recoveryCoordinator != null && !presumedClean)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void postSuccessfulWrite()
    {
        if(recoveryCoordinator != null)
        {
            recoveryCoordinator = null;
            statusCount = 0;
            addStatus(new InfoStatus((new StringBuilder()).append("Recovered from IO failure on ").append(getDescription()).toString(), this));
        }
    }

    public void addStatus(Status status)
    {
        if(context != null) goto _L2; else goto _L1
_L1:
        int i = noContextWarning;
        noContextWarning = i + 1;
        if(i == 0)
            System.out.println((new StringBuilder()).append("LOGBACK: No context given for ").append(this).toString());
_L4:
        return;
_L2:
        StatusManager statusmanager = context.getStatusManager();
        if(statusmanager != null)
            statusmanager.add(status);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void addStatusIfCountNotOverLimit(Status status)
    {
        statusCount = 1 + statusCount;
        if(statusCount < 8)
            addStatus(status);
        if(statusCount == 8)
        {
            addStatus(status);
            addStatus(new InfoStatus((new StringBuilder()).append("Will supress future messages regarding ").append(getDescription()).toString(), this));
        }
    }

    void attemptRecovery()
    {
        IOException ioexception1;
        try
        {
            close();
        }
        catch(IOException ioexception) { }
        addStatusIfCountNotOverLimit(new InfoStatus((new StringBuilder()).append("Attempting to recover from IO failure on ").append(getDescription()).toString(), this));
        os = openNewOutputStream();
        presumedClean = true;
_L1:
        return;
        ioexception1;
        addStatusIfCountNotOverLimit(new ErrorStatus((new StringBuilder()).append("Failed to open ").append(getDescription()).toString(), this, ioexception1));
          goto _L1
    }

    public void close()
        throws IOException
    {
        if(os != null)
            os.close();
    }

    public void flush()
    {
        if(os == null)
            break MISSING_BLOCK_LABEL_18;
        os.flush();
        postSuccessfulWrite();
_L1:
        return;
        IOException ioexception;
        ioexception;
        postIOFailure(ioexception);
          goto _L1
    }

    public Context getContext()
    {
        return context;
    }

    abstract String getDescription();

    abstract OutputStream openNewOutputStream()
        throws IOException;

    void postIOFailure(IOException ioexception)
    {
        addStatusIfCountNotOverLimit(new ErrorStatus((new StringBuilder()).append("IO failure while writing to ").append(getDescription()).toString(), this, ioexception));
        presumedClean = false;
        if(recoveryCoordinator == null)
            recoveryCoordinator = new RecoveryCoordinator();
    }

    public void setContext(Context context1)
    {
        context = context1;
    }

    public void write(int i)
    {
        if(isPresumedInError())
        {
            if(!recoveryCoordinator.isTooSoon())
                attemptRecovery();
        } else
        {
            try
            {
                os.write(i);
                postSuccessfulWrite();
            }
            catch(IOException ioexception)
            {
                postIOFailure(ioexception);
            }
        }
    }

    public void write(byte abyte0[], int i, int j)
    {
        if(isPresumedInError())
        {
            if(!recoveryCoordinator.isTooSoon())
                attemptRecovery();
        } else
        {
            try
            {
                os.write(abyte0, i, j);
                postSuccessfulWrite();
            }
            catch(IOException ioexception)
            {
                postIOFailure(ioexception);
            }
        }
    }

    static final int STATUS_COUNT_LIMIT = 8;
    private Context context;
    private int noContextWarning;
    protected OutputStream os;
    protected boolean presumedClean;
    private RecoveryCoordinator recoveryCoordinator;
    private int statusCount;
}
