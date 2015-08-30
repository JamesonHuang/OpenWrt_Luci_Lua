// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.event.SaxEventRecorder;
import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;
import ch.qos.logback.core.joran.spi.ElementPath;
import ch.qos.logback.core.joran.spi.EventPlayer;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.spi.Interpreter;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.spi.RuleStore;
import ch.qos.logback.core.joran.spi.SimpleRuleStore;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.StatusUtil;
import java.io.*;
import java.net.*;
import java.util.List;
import org.xml.sax.InputSource;

public abstract class GenericConfigurator extends ContextAwareBase
{

    public GenericConfigurator()
    {
    }

    private final void doConfigure(InputSource inputsource)
        throws JoranException
    {
        long l = System.currentTimeMillis();
        if(!ConfigurationWatchListUtil.wasConfigurationWatchListReset(context))
            informContextOfURLUsedForConfiguration(getContext(), null);
        SaxEventRecorder saxeventrecorder = new SaxEventRecorder(context);
        saxeventrecorder.recordEvents(inputsource);
        doConfigure(saxeventrecorder.getSaxEventList());
        if((new StatusUtil(context)).noXMLParsingErrorsOccurred(l))
        {
            addInfo("Registering current configuration as safe fallback point");
            registerSafeConfiguration();
        }
    }

    public static void informContextOfURLUsedForConfiguration(Context context, URL url)
    {
        ConfigurationWatchListUtil.setMainWatchURL(context, url);
    }

    protected void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry defaultnestedcomponentregistry)
    {
    }

    protected abstract void addImplicitRules(Interpreter interpreter1);

    protected abstract void addInstanceRules(RuleStore rulestore);

    protected void buildInterpreter()
    {
        SimpleRuleStore simplerulestore = new SimpleRuleStore(context);
        addInstanceRules(simplerulestore);
        interpreter = new Interpreter(context, simplerulestore, initialElementPath());
        InterpretationContext interpretationcontext = interpreter.getInterpretationContext();
        interpretationcontext.setContext(context);
        addImplicitRules(interpreter);
        addDefaultNestedComponentRegistryRules(interpretationcontext.getDefaultNestedComponentRegistry());
    }

    public final void doConfigure(File file)
        throws JoranException
    {
        try
        {
            informContextOfURLUsedForConfiguration(getContext(), file.toURI().toURL());
            doConfigure(((InputStream) (new FileInputStream(file))));
            return;
        }
        catch(IOException ioexception)
        {
            String s = (new StringBuilder()).append("Could not open [").append(file.getPath()).append("].").toString();
            addError(s, ioexception);
            throw new JoranException(s, ioexception);
        }
    }

    public final void doConfigure(InputStream inputstream)
        throws JoranException
    {
        doConfigure(new InputSource(inputstream));
        try
        {
            inputstream.close();
            return;
        }
        catch(IOException ioexception1)
        {
            addError("Could not close the stream", ioexception1);
            throw new JoranException("Could not close the stream", ioexception1);
        }
        Exception exception;
        exception;
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception)
        {
            addError("Could not close the stream", ioexception);
            throw new JoranException("Could not close the stream", ioexception);
        }
        throw exception;
    }

    public final void doConfigure(String s)
        throws JoranException
    {
        doConfigure(new File(s));
    }

    public final void doConfigure(URL url)
        throws JoranException
    {
        try
        {
            informContextOfURLUsedForConfiguration(getContext(), url);
            URLConnection urlconnection = url.openConnection();
            urlconnection.setUseCaches(false);
            doConfigure(urlconnection.getInputStream());
            return;
        }
        catch(IOException ioexception)
        {
            String s = (new StringBuilder()).append("Could not open URL [").append(url).append("].").toString();
            addError(s, ioexception);
            throw new JoranException(s, ioexception);
        }
    }

    public void doConfigure(List list)
        throws JoranException
    {
        buildInterpreter();
        Object obj = context.getConfigurationLock();
        obj;
        JVM INSTR monitorenter ;
        interpreter.getEventPlayer().play(list);
        return;
    }

    protected ElementPath initialElementPath()
    {
        return new ElementPath();
    }

    public List recallSafeConfiguration()
    {
        return (List)context.getObject("SAFE_JORAN_CONFIGURATION");
    }

    public void registerSafeConfiguration()
    {
        context.putObject("SAFE_JORAN_CONFIGURATION", interpreter.getEventPlayer().getCopyOfPlayerEventList());
    }

    protected Interpreter interpreter;
}
