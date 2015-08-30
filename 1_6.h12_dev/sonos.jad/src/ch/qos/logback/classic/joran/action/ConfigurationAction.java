// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.util.*;
import org.xml.sax.Attributes;

public class ConfigurationAction extends Action
{

    public ConfigurationAction()
    {
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        String s1 = OptionHelper.getSystemProperty("logback.debug");
        if(s1 == null)
            s1 = interpretationcontext.subst(attributes.getValue("debug"));
        if(OptionHelper.isEmpty(s1) || s1.equalsIgnoreCase("false") || s1.equalsIgnoreCase("null"))
            addInfo("debug attribute not set");
        else
            OnConsoleStatusListener.addNewInstanceToContext(context);
        processScanAttrib(interpretationcontext, attributes);
        (new ContextUtil(context)).addHostNameAsProperty();
        interpretationcontext.pushObject(getContext());
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
        addInfo("End of configuration.");
        interpretationcontext.popObject();
    }

    String getSystemProperty(String s)
    {
        String s2 = System.getProperty(s);
        String s1 = s2;
_L2:
        return s1;
        SecurityException securityexception;
        securityexception;
        s1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void processScanAttrib(InterpretationContext interpretationcontext, Attributes attributes)
    {
        String s = interpretationcontext.subst(attributes.getValue("scan"));
        if(!OptionHelper.isEmpty(s) && !"false".equalsIgnoreCase(s))
        {
            ReconfigureOnChangeFilter reconfigureonchangefilter = new ReconfigureOnChangeFilter();
            reconfigureonchangefilter.setContext(context);
            String s1 = interpretationcontext.subst(attributes.getValue("scanPeriod"));
            LoggerContext loggercontext;
            if(!OptionHelper.isEmpty(s1))
                try
                {
                    Duration duration = Duration.valueOf(s1);
                    reconfigureonchangefilter.setRefreshPeriod(duration.getMilliseconds());
                    addInfo((new StringBuilder()).append("Setting ReconfigureOnChangeFilter scanning period to ").append(duration).toString());
                }
                catch(NumberFormatException numberformatexception)
                {
                    addError((new StringBuilder()).append("Error while converting [").append(s).append("] to long").toString(), numberformatexception);
                }
            reconfigureonchangefilter.start();
            loggercontext = (LoggerContext)context;
            addInfo("Adding ReconfigureOnChangeFilter as a turbo filter");
            loggercontext.addTurboFilter(reconfigureonchangefilter);
        }
    }

    static final String DEBUG_SYSTEM_PROPERTY_KEY = "logback.debug";
    static final String INTERNAL_DEBUG_ATTR = "debug";
    static final String SCAN_ATTR = "scan";
    static final String SCAN_PERIOD_ATTR = "scanPeriod";
}
