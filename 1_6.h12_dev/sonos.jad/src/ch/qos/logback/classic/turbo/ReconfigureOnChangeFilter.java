// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.*;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.ConfigurationWatchList;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.StatusUtil;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.slf4j.Marker;

// Referenced classes of package ch.qos.logback.classic.turbo:
//            TurboFilter

public class ReconfigureOnChangeFilter extends TurboFilter
{
    class ReconfiguringThread
        implements Runnable
    {

        private void fallbackConfiguration(LoggerContext loggercontext, List list, URL url)
        {
            JoranConfigurator joranconfigurator;
            joranconfigurator = new JoranConfigurator();
            joranconfigurator.setContext(doConfigure);
            if(list == null)
                break MISSING_BLOCK_LABEL_86;
            addWarn("Falling back to previously registered safe configuration.");
            loggercontext.reset();
            JoranConfigurator.informContextOfURLUsedForConfiguration(doConfigure, url);
            joranconfigurator.doConfigure(list);
            addInfo("Re-registering previous fallback configuration once more as a fallback configuration point");
            joranconfigurator.registerSafeConfiguration();
_L1:
            return;
            JoranException joranexception;
            joranexception;
            addError("Unexpected exception thrown by a configuration considered safe.", joranexception);
              goto _L1
            addWarn("No previous configuration to fall back on.");
              goto _L1
        }

        private void performXMLConfiguration(LoggerContext loggercontext)
        {
            JoranConfigurator joranconfigurator;
            StatusUtil statusutil;
            List list;
            URL url;
            long l;
            joranconfigurator = new JoranConfigurator();
            joranconfigurator.setContext(doConfigure);
            statusutil = new StatusUtil(doConfigure);
            list = joranconfigurator.recallSafeConfiguration();
            url = ConfigurationWatchListUtil.getMainWatchURL(doConfigure);
            loggercontext.reset();
            l = System.currentTimeMillis();
            joranconfigurator.doConfigure(mainConfigurationURL);
            if(statusutil.hasXMLParsingErrors(l))
                fallbackConfiguration(loggercontext, list, url);
_L1:
            return;
            JoranException joranexception;
            joranexception;
            fallbackConfiguration(loggercontext, list, url);
              goto _L1
        }

        public void run()
        {
            if(mainConfigurationURL != null) goto _L2; else goto _L1
_L1:
            addInfo("Due to missing top level configuration file, skipping reconfiguration");
_L4:
            return;
_L2:
            LoggerContext loggercontext = (LoggerContext)doConfigure;
            addInfo((new StringBuilder()).append("Will reset and reconfigure context named [").append(doConfigure.getName()).append("]").toString());
            if(mainConfigurationURL.toString().endsWith("xml"))
                performXMLConfiguration(loggercontext);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final ReconfigureOnChangeFilter this$0;

        ReconfiguringThread()
        {
            this$0 = ReconfigureOnChangeFilter.this;
            super();
        }
    }


    public ReconfigureOnChangeFilter()
    {
        refreshPeriod = 60000L;
        invocationCounter = 0L;
        mask = 15L;
        lastMaskCheck = System.currentTimeMillis();
    }

    private void updateMaskIfNecessary(long l)
    {
        long l1;
        l1 = l - lastMaskCheck;
        lastMaskCheck = l;
        if(l1 >= 100L || mask >= 65535L) goto _L2; else goto _L1
_L1:
        mask = 1L | mask << 1;
_L4:
        return;
_L2:
        if(l1 > 800L)
            mask = mask >>> 2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected boolean changeDetected(long l)
    {
        boolean flag;
        if(l >= nextCheck)
        {
            updateNextCheck(l);
            flag = configurationWatchList.changeDetected();
        } else
        {
            flag = false;
        }
        return flag;
    }

    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object aobj[], Throwable throwable)
    {
        FilterReply filterreply;
        if(!isStarted())
        {
            filterreply = FilterReply.NEUTRAL;
        } else
        {
            long l = invocationCounter;
            invocationCounter = 1L + l;
            if((l & mask) != mask)
            {
                filterreply = FilterReply.NEUTRAL;
            } else
            {
                long l1 = System.currentTimeMillis();
                synchronized(configurationWatchList)
                {
                    updateMaskIfNecessary(l1);
                    if(changeDetected(l1))
                    {
                        disableSubsequentReconfiguration();
                        detachReconfigurationToNewThread();
                    }
                }
                filterreply = FilterReply.NEUTRAL;
            }
        }
        return filterreply;
        exception;
        configurationwatchlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void detachReconfigurationToNewThread()
    {
        addInfo((new StringBuilder()).append("Detected change in [").append(configurationWatchList.getCopyOfFileWatchList()).append("]").toString());
        context.getExecutorService().submit(new ReconfiguringThread());
    }

    void disableSubsequentReconfiguration()
    {
        nextCheck = 0x7fffffffffffffffL;
    }

    public long getRefreshPeriod()
    {
        return refreshPeriod;
    }

    public void setRefreshPeriod(long l)
    {
        refreshPeriod = l;
    }

    public void start()
    {
        configurationWatchList = ConfigurationWatchListUtil.getConfigurationWatchList(context);
        if(configurationWatchList != null)
        {
            mainConfigurationURL = configurationWatchList.getMainURL();
            if(mainConfigurationURL == null)
            {
                addWarn("Due to missing top level configuration file, automatic reconfiguration is impossible.");
            } else
            {
                List list = configurationWatchList.getCopyOfFileWatchList();
                long l = refreshPeriod / 1000L;
                addInfo((new StringBuilder()).append("Will scan for changes in [").append(list).append("] every ").append(l).append(" seconds. ").toString());
                synchronized(configurationWatchList)
                {
                    updateNextCheck(System.currentTimeMillis());
                }
                super.start();
            }
        } else
        {
            addWarn("Empty ConfigurationWatchList in context");
        }
        return;
        exception;
        configurationwatchlist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ReconfigureOnChangeFilter{invocationCounter=").append(invocationCounter).append('}').toString();
    }

    void updateNextCheck(long l)
    {
        nextCheck = l + refreshPeriod;
    }

    public static final long DEFAULT_REFRESH_PERIOD = 60000L;
    private static final long MASK_DECREASE_THRESHOLD = 800L;
    private static final long MASK_INCREASE_THRESHOLD = 100L;
    private static final int MAX_MASK = 65535;
    ConfigurationWatchList configurationWatchList;
    private long invocationCounter;
    private volatile long lastMaskCheck;
    URL mainConfigurationURL;
    private volatile long mask;
    protected volatile long nextCheck;
    long refreshPeriod;







}
