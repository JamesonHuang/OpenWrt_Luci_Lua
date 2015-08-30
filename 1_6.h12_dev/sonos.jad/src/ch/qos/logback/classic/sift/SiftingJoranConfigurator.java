// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.sift;

import ch.qos.logback.classic.util.DefaultNestedComponentRules;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.joran.action.AppenderAction;
import ch.qos.logback.core.joran.spi.*;
import ch.qos.logback.core.sift.SiftingJoranConfiguratorBase;
import java.util.*;

public class SiftingJoranConfigurator extends SiftingJoranConfiguratorBase
{

    SiftingJoranConfigurator(String s, String s1, Map map)
    {
        super(s, s1, map);
    }

    protected void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry defaultnestedcomponentregistry)
    {
        DefaultNestedComponentRules.addDefaultNestedComponentRegistryRules(defaultnestedcomponentregistry);
    }

    protected void addInstanceRules(RuleStore rulestore)
    {
        super.addInstanceRules(rulestore);
        rulestore.addRule(new ElementSelector("configuration/appender"), new AppenderAction());
    }

    protected void buildInterpreter()
    {
        super.buildInterpreter();
        Map map = interpreter.getInterpretationContext().getObjectMap();
        map.put("APPENDER_BAG", new HashMap());
        map.put("FILTER_CHAIN_BAG", new HashMap());
        HashMap hashmap = new HashMap();
        hashmap.putAll(parentPropertyMap);
        hashmap.put(key, value);
        interpreter.setInterpretationContextPropertiesMap(hashmap);
    }

    public Appender getAppender()
    {
        HashMap hashmap = (HashMap)interpreter.getInterpretationContext().getObjectMap().get("APPENDER_BAG");
        oneAndOnlyOneCheck(hashmap);
        Collection collection = hashmap.values();
        Appender appender;
        if(collection.size() == 0)
            appender = null;
        else
            appender = (Appender)collection.iterator().next();
        return appender;
    }

    protected ElementPath initialElementPath()
    {
        return new ElementPath("configuration");
    }
}
