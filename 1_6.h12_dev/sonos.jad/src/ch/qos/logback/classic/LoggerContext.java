// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic;

import ch.qos.logback.classic.android.AndroidManifestPropertiesUtil;
import ch.qos.logback.classic.spi.LoggerComparator;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.classic.spi.LoggerContextVO;
import ch.qos.logback.classic.spi.TurboFilterList;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.classic.util.LoggerNameUtil;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.*;

// Referenced classes of package ch.qos.logback.classic:
//            Logger, Level

public class LoggerContext extends ContextBase
    implements ILoggerFactory, LifeCycle
{

    public LoggerContext()
    {
        noAppenderWarning = 0;
        packagingDataEnabled = true;
        androidPropsInitialized = false;
        maxCallerDataDepth = 8;
        resetCount = 0;
        loggerCache = new ConcurrentHashMap();
        loggerContextRemoteView = new LoggerContextVO(this);
        root.setLevel(Level.DEBUG);
        loggerCache.put("ROOT", root);
        initEvaluatorMap();
        size = 1;
        frameworkPackages = new ArrayList();
    }

    private void fireOnReset()
    {
        for(Iterator iterator = loggerContextListenerList.iterator(); iterator.hasNext(); ((LoggerContextListener)iterator.next()).onReset(this));
    }

    private void fireOnStart()
    {
        for(Iterator iterator = loggerContextListenerList.iterator(); iterator.hasNext(); ((LoggerContextListener)iterator.next()).onStart(this));
    }

    private void fireOnStop()
    {
        for(Iterator iterator = loggerContextListenerList.iterator(); iterator.hasNext(); ((LoggerContextListener)iterator.next()).onStop(this));
    }

    private void incSize()
    {
        size = 1 + size;
    }

    private boolean isSpecialKey(String s)
    {
        boolean flag;
        if(s.equals("PACKAGE_NAME") || s.equals("VERSION_NAME") || s.equals("VERSION_CODE") || s.equals("EXT_DIR") || s.equals("DATA_DIR"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void resetAllListeners()
    {
        loggerContextListenerList.clear();
    }

    private void resetListenersExceptResetResistant()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = loggerContextListenerList.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            LoggerContextListener loggercontextlistener = (LoggerContextListener)iterator.next();
            if(loggercontextlistener.isResetResistant())
                arraylist.add(loggercontextlistener);
        } while(true);
        loggerContextListenerList.retainAll(arraylist);
    }

    private void resetStatusListeners()
    {
        StatusManager statusmanager = getStatusManager();
        for(Iterator iterator = statusmanager.getCopyOfStatusListenerList().iterator(); iterator.hasNext(); statusmanager.remove((StatusListener)iterator.next()));
    }

    private void updateLoggerContextVO()
    {
        loggerContextRemoteView = new LoggerContextVO(this);
    }

    public void addListener(LoggerContextListener loggercontextlistener)
    {
        loggerContextListenerList.add(loggercontextlistener);
    }

    public void addTurboFilter(TurboFilter turbofilter)
    {
        turboFilterList.add(turbofilter);
    }

    public ch.qos.logback.classic.Logger exists(String s)
    {
        return (ch.qos.logback.classic.Logger)loggerCache.get(s);
    }

    void fireOnLevelChange(ch.qos.logback.classic.Logger logger, Level level)
    {
        for(Iterator iterator = loggerContextListenerList.iterator(); iterator.hasNext(); ((LoggerContextListener)iterator.next()).onLevelChange(logger, level));
    }

    public List getCopyOfListenerList()
    {
        return new ArrayList(loggerContextListenerList);
    }

    public List getFrameworkPackages()
    {
        return frameworkPackages;
    }

    public final ch.qos.logback.classic.Logger getLogger(Class class1)
    {
        return getLogger(class1.getName());
    }

    public final ch.qos.logback.classic.Logger getLogger(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("name argument cannot be null");
        if(!"ROOT".equalsIgnoreCase(s)) goto _L2; else goto _L1
_L1:
        ch.qos.logback.classic.Logger logger1 = root;
_L4:
        return logger1;
_L2:
        ch.qos.logback.classic.Logger logger;
        logger = root;
        logger1 = (ch.qos.logback.classic.Logger)loggerCache.get(s);
        if(logger1 != null) goto _L4; else goto _L3
_L3:
        ch.qos.logback.classic.Logger logger2;
        int i;
        logger2 = logger;
        i = 0;
_L5:
        int k;
        ch.qos.logback.classic.Logger logger4;
        int j = LoggerNameUtil.getSeparatorIndexOf(s, i);
        String s1;
        ch.qos.logback.classic.Logger logger3;
        if(j == -1)
            s1 = s;
        else
            s1 = s.substring(0, j);
        k = j + 1;
        logger2;
        JVM INSTR monitorenter ;
        logger3 = logger2.getChildByName(s1);
        if(logger3 == null)
        {
            logger3 = logger2.createChildByName(s1);
            loggerCache.put(s1, logger3);
            incSize();
        }
        logger4 = logger3;
        logger2;
        JVM INSTR monitorexit ;
        if(j != -1)
            break MISSING_BLOCK_LABEL_167;
        logger1 = logger4;
          goto _L4
        Exception exception;
        exception;
        logger2;
        JVM INSTR monitorexit ;
        throw exception;
        logger2 = logger4;
        i = k;
          goto _L5
    }

    public volatile Logger getLogger(String s)
    {
        return getLogger(s);
    }

    public LoggerContextVO getLoggerContextRemoteView()
    {
        return loggerContextRemoteView;
    }

    public List getLoggerList()
    {
        ArrayList arraylist = new ArrayList(loggerCache.values());
        Collections.sort(arraylist, new LoggerComparator());
        return arraylist;
    }

    public int getMaxCallerDataDepth()
    {
        return maxCallerDataDepth;
    }

    public String getProperty(String s)
    {
        if(isSpecialKey(s))
            try
            {
                if(!androidPropsInitialized)
                {
                    androidPropsInitialized = true;
                    AndroidManifestPropertiesUtil.setAndroidProperties(this);
                }
            }
            catch(JoranException joranexception)
            {
                getStatusManager().add(new WarnStatus("Can't set manifest properties", joranexception));
                androidPropsInitialized = false;
            }
        return super.getProperty(s);
    }

    final FilterReply getTurboFilterChainDecision_0_3OrMore(Marker marker, ch.qos.logback.classic.Logger logger, Level level, String s, Object aobj[], Throwable throwable)
    {
        FilterReply filterreply;
        if(turboFilterList.size() == 0)
            filterreply = FilterReply.NEUTRAL;
        else
            filterreply = turboFilterList.getTurboFilterChainDecision(marker, logger, level, s, aobj, throwable);
        return filterreply;
    }

    final FilterReply getTurboFilterChainDecision_1(Marker marker, ch.qos.logback.classic.Logger logger, Level level, String s, Object obj, Throwable throwable)
    {
        FilterReply filterreply;
        if(turboFilterList.size() == 0)
        {
            filterreply = FilterReply.NEUTRAL;
        } else
        {
            TurboFilterList turbofilterlist = turboFilterList;
            Object aobj[] = new Object[1];
            aobj[0] = obj;
            filterreply = turbofilterlist.getTurboFilterChainDecision(marker, logger, level, s, aobj, throwable);
        }
        return filterreply;
    }

    final FilterReply getTurboFilterChainDecision_2(Marker marker, ch.qos.logback.classic.Logger logger, Level level, String s, Object obj, Object obj1, Throwable throwable)
    {
        FilterReply filterreply;
        if(turboFilterList.size() == 0)
        {
            filterreply = FilterReply.NEUTRAL;
        } else
        {
            TurboFilterList turbofilterlist = turboFilterList;
            Object aobj[] = new Object[2];
            aobj[0] = obj;
            aobj[1] = obj1;
            filterreply = turbofilterlist.getTurboFilterChainDecision(marker, logger, level, s, aobj, throwable);
        }
        return filterreply;
    }

    public TurboFilterList getTurboFilterList()
    {
        return turboFilterList;
    }

    void initEvaluatorMap()
    {
        putObject("EVALUATOR_MAP", new HashMap());
    }

    public boolean isPackagingDataEnabled()
    {
        return packagingDataEnabled;
    }

    final void noAppenderDefinedWarning(ch.qos.logback.classic.Logger logger)
    {
        int i = noAppenderWarning;
        noAppenderWarning = i + 1;
        if(i == 0)
            getStatusManager().add(new WarnStatus((new StringBuilder()).append("No appenders present in context [").append(getName()).append("] for logger [").append(logger.getName()).append("].").toString(), logger));
    }

    public void putProperty(String s, String s1)
    {
        super.putProperty(s, s1);
        updateLoggerContextVO();
    }

    public void removeListener(LoggerContextListener loggercontextlistener)
    {
        loggerContextListenerList.remove(loggercontextlistener);
    }

    public void reset()
    {
        resetCount = 1 + resetCount;
        super.reset();
        initEvaluatorMap();
        root.recursiveReset();
        resetTurboFilterList();
        fireOnReset();
        resetListenersExceptResetResistant();
        resetStatusListeners();
    }

    public void resetTurboFilterList()
    {
        for(Iterator iterator = turboFilterList.iterator(); iterator.hasNext(); ((TurboFilter)iterator.next()).stop());
        turboFilterList.clear();
    }

    public void setMaxCallerDataDepth(int i)
    {
        maxCallerDataDepth = i;
    }

    public void setName(String s)
    {
        super.setName(s);
        updateLoggerContextVO();
    }

    public void setPackagingDataEnabled(boolean flag)
    {
        packagingDataEnabled = flag;
    }

    int size()
    {
        return size;
    }

    public void start()
    {
        super.start();
        fireOnStart();
    }

    public void stop()
    {
        reset();
        fireOnStop();
        resetAllListeners();
        super.stop();
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("[").append(getName()).append("]").toString();
    }

    private boolean androidPropsInitialized;
    private List frameworkPackages;
    private Map loggerCache;
    private final List loggerContextListenerList = new ArrayList();
    private LoggerContextVO loggerContextRemoteView;
    private int maxCallerDataDepth;
    private int noAppenderWarning;
    private boolean packagingDataEnabled;
    int resetCount;
    final ch.qos.logback.classic.Logger root = new ch.qos.logback.classic.Logger("ROOT", null, this);
    private int size;
    private final TurboFilterList turboFilterList = new TurboFilterList();
}
