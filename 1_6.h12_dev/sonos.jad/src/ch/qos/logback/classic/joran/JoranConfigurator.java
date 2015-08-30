// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran;

import ch.qos.logback.classic.joran.action.ConditionalIncludeAction;
import ch.qos.logback.classic.joran.action.ConfigurationAction;
import ch.qos.logback.classic.joran.action.ContextNameAction;
import ch.qos.logback.classic.joran.action.FindIncludeAction;
import ch.qos.logback.classic.joran.action.LevelAction;
import ch.qos.logback.classic.joran.action.LoggerAction;
import ch.qos.logback.classic.joran.action.LoggerContextListenerAction;
import ch.qos.logback.classic.joran.action.ReceiverAction;
import ch.qos.logback.classic.joran.action.RootLoggerAction;
import ch.qos.logback.classic.sift.SiftAction;
import ch.qos.logback.classic.util.DefaultNestedComponentRules;
import ch.qos.logback.core.joran.JoranConfiguratorBase;
import ch.qos.logback.core.joran.action.*;
import ch.qos.logback.core.joran.spi.*;

public class JoranConfigurator extends JoranConfiguratorBase
{

    public JoranConfigurator()
    {
    }

    protected void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry defaultnestedcomponentregistry)
    {
        DefaultNestedComponentRules.addDefaultNestedComponentRegistryRules(defaultnestedcomponentregistry);
    }

    public void addInstanceRules(RuleStore rulestore)
    {
        super.addInstanceRules(rulestore);
        rulestore.addRule(new ElementSelector("configuration"), new ConfigurationAction());
        rulestore.addRule(new ElementSelector("configuration/contextName"), new ContextNameAction());
        rulestore.addRule(new ElementSelector("configuration/contextListener"), new LoggerContextListenerAction());
        rulestore.addRule(new ElementSelector("configuration/appender/sift"), new SiftAction());
        rulestore.addRule(new ElementSelector("configuration/appender/sift/*"), new NOPAction());
        rulestore.addRule(new ElementSelector("configuration/logger"), new LoggerAction());
        rulestore.addRule(new ElementSelector("configuration/logger/level"), new LevelAction());
        rulestore.addRule(new ElementSelector("configuration/root"), new RootLoggerAction());
        rulestore.addRule(new ElementSelector("configuration/root/level"), new LevelAction());
        rulestore.addRule(new ElementSelector("configuration/logger/appender-ref"), new AppenderRefAction());
        rulestore.addRule(new ElementSelector("configuration/root/appender-ref"), new AppenderRefAction());
        rulestore.addRule(new ElementSelector("configuration/include"), new IncludeAction());
        rulestore.addRule(new ElementSelector("configuration/includes"), new FindIncludeAction());
        rulestore.addRule(new ElementSelector("configuration/includes/include"), new ConditionalIncludeAction());
        rulestore.addRule(new ElementSelector("configuration/receiver"), new ReceiverAction());
    }
}
