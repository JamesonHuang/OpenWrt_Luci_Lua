// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

// Referenced classes of package org.slf4j:
//            ILoggerFactory, Logger

public final class LoggerFactory
{

    private LoggerFactory()
    {
    }

    private static final void bind()
    {
        Set set = findPossibleStaticLoggerBinderPathSet();
        reportMultipleBindingAmbiguity(set);
        StaticLoggerBinder.getSingleton();
        INITIALIZATION_STATE = 3;
        reportActualBinding(set);
        fixSubstitutedLoggers();
_L1:
        return;
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        if(messageContainsOrgSlf4jImplStaticLoggerBinder(noclassdeffounderror.getMessage()))
        {
            INITIALIZATION_STATE = 4;
            Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
            Util.report("Defaulting to no-operation (NOP) logger implementation");
            Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
        } else
        {
            failedBinding(noclassdeffounderror);
            throw noclassdeffounderror;
        }
          goto _L1
        NoSuchMethodError nosuchmethoderror;
        nosuchmethoderror;
        String s = nosuchmethoderror.getMessage();
        if(s != null && s.indexOf("org.slf4j.impl.StaticLoggerBinder.getSingleton()") != -1)
        {
            INITIALIZATION_STATE = 2;
            Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
            Util.report("Your binding is version 1.5.5 or earlier.");
            Util.report("Upgrade your binding to version 1.6.x.");
        }
        throw nosuchmethoderror;
        Exception exception;
        exception;
        failedBinding(exception);
        throw new IllegalStateException("Unexpected initialization failure", exception);
    }

    static void failedBinding(Throwable throwable)
    {
        INITIALIZATION_STATE = 2;
        Util.report("Failed to instantiate SLF4J LoggerFactory", throwable);
    }

    private static Set findPossibleStaticLoggerBinderPathSet()
    {
        LinkedHashSet linkedhashset = new LinkedHashSet();
        ClassLoader classloader = org/slf4j/LoggerFactory.getClassLoader();
        if(classloader != null) goto _L2; else goto _L1
_L1:
        Enumeration enumeration1;
        for(enumeration1 = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH); enumeration1.hasMoreElements(); linkedhashset.add((URL)enumeration1.nextElement()));
          goto _L3
        IOException ioexception;
        ioexception;
        Util.report("Error getting resources from path", ioexception);
_L3:
        return linkedhashset;
_L2:
        Enumeration enumeration = classloader.getResources(STATIC_LOGGER_BINDER_PATH);
        enumeration1 = enumeration;
          goto _L1
    }

    private static final void fixSubstitutedLoggers()
    {
        List list = TEMP_FACTORY.getLoggers();
        if(!list.isEmpty())
        {
            Util.report("The following set of substitute loggers may have been accessed");
            Util.report("during the initialization phase. Logging calls during this");
            Util.report("phase were not honored. However, subsequent logging calls to these");
            Util.report("loggers will work as normally expected.");
            Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
            SubstituteLogger substitutelogger;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); Util.report(substitutelogger.getName()))
            {
                substitutelogger = (SubstituteLogger)iterator.next();
                substitutelogger.setDelegate(getLogger(substitutelogger.getName()));
            }

            TEMP_FACTORY.clear();
        }
    }

    public static ILoggerFactory getILoggerFactory()
    {
        if(INITIALIZATION_STATE == 0)
        {
            INITIALIZATION_STATE = 1;
            performInitialization();
        }
        INITIALIZATION_STATE;
        JVM INSTR tableswitch 1 4: default 48
    //                   1 84
    //                   2 74
    //                   3 58
    //                   4 67;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalStateException("Unreachable code");
_L4:
        Object obj = StaticLoggerBinder.getSingleton().getLoggerFactory();
_L7:
        return ((ILoggerFactory) (obj));
_L5:
        obj = NOP_FALLBACK_FACTORY;
        continue; /* Loop/switch isn't completed */
_L3:
        throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
_L2:
        obj = TEMP_FACTORY;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static Logger getLogger(Class class1)
    {
        return getLogger(class1.getName());
    }

    public static Logger getLogger(String s)
    {
        return getILoggerFactory().getLogger(s);
    }

    private static boolean isAmbiguousStaticLoggerBinderPathSet(Set set)
    {
        boolean flag = true;
        if(set.size() <= flag)
            flag = false;
        return flag;
    }

    private static boolean messageContainsOrgSlf4jImplStaticLoggerBinder(String s)
    {
        boolean flag = false;
        if(s != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(s.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1)
            flag = true;
        else
        if(s.indexOf("org.slf4j.impl.StaticLoggerBinder") != -1)
            flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static final void performInitialization()
    {
        bind();
        if(INITIALIZATION_STATE == 3)
            versionSanityCheck();
    }

    private static void reportActualBinding(Set set)
    {
        if(isAmbiguousStaticLoggerBinderPathSet(set))
            Util.report((new StringBuilder()).append("Actual binding is of type [").append(StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr()).append("]").toString());
    }

    private static void reportMultipleBindingAmbiguity(Set set)
    {
        if(isAmbiguousStaticLoggerBinderPathSet(set))
        {
            Util.report("Class path contains multiple SLF4J bindings.");
            URL url;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); Util.report((new StringBuilder()).append("Found binding in [").append(url).append("]").toString()))
                url = (URL)iterator.next();

            Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    static void reset()
    {
        INITIALIZATION_STATE = 0;
        TEMP_FACTORY = new SubstituteLoggerFactory();
    }

    private static final void versionSanityCheck()
    {
        String s;
        boolean flag;
        s = StaticLoggerBinder.REQUESTED_API_VERSION;
        flag = false;
        NoSuchFieldError nosuchfielderror;
        Throwable throwable;
        for(int i = 0; i < API_COMPATIBILITY_LIST.length; i++)
        {
            if(s.startsWith(API_COMPATIBILITY_LIST[i]))
                flag = true;
            break MISSING_BLOCK_LABEL_103;
        }

        if(!flag)
        {
            Util.report((new StringBuilder()).append("The requested version ").append(s).append(" by your slf4j binding is not compatible with ").append(Arrays.asList(API_COMPATIBILITY_LIST).toString()).toString());
            Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
        }
_L2:
        return;
        throwable;
        Util.report("Unexpected problem occured during version sanity check", throwable);
        continue; /* Loop/switch isn't completed */
        nosuchfielderror;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String API_COMPATIBILITY_LIST[];
    static final String CODES_PREFIX = "http://www.slf4j.org/codes.html";
    static final int FAILED_INITIALIZATION = 2;
    static int INITIALIZATION_STATE = 0;
    static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
    static NOPLoggerFactory NOP_FALLBACK_FACTORY = new NOPLoggerFactory();
    static final int NOP_FALLBACK_INITIALIZATION = 4;
    static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
    static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
    static final int ONGOING_INITIALIZATION = 1;
    private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
    static final int SUCCESSFUL_INITIALIZATION = 3;
    static SubstituteLoggerFactory TEMP_FACTORY = new SubstituteLoggerFactory();
    static final int UNINITIALIZED = 0;
    static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";

    static 
    {
        INITIALIZATION_STATE = 0;
        String as[] = new String[2];
        as[0] = "1.6";
        as[1] = "1.7";
        API_COMPATIBILITY_LIST = as;
    }
}
