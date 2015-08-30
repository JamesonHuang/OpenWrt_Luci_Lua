// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.joran.GenericConfigurator;
import ch.qos.logback.core.joran.action.*;
import ch.qos.logback.core.joran.spi.*;
import java.util.List;
import java.util.Map;

public abstract class SiftingJoranConfiguratorBase extends GenericConfigurator
{

    protected SiftingJoranConfiguratorBase(String s, String s1, Map map)
    {
        errorEmmissionCount = 0;
        key = s;
        value = s1;
        parentPropertyMap = map;
    }

    protected void addImplicitRules(Interpreter interpreter)
    {
        NestedComplexPropertyIA nestedcomplexpropertyia = new NestedComplexPropertyIA();
        nestedcomplexpropertyia.setContext(context);
        interpreter.addImplicitAction(nestedcomplexpropertyia);
        NestedBasicPropertyIA nestedbasicpropertyia = new NestedBasicPropertyIA();
        nestedbasicpropertyia.setContext(context);
        interpreter.addImplicitAction(nestedbasicpropertyia);
    }

    protected void addInstanceRules(RuleStore rulestore)
    {
        rulestore.addRule(new ElementSelector("configuration/property"), new PropertyAction());
        rulestore.addRule(new ElementSelector("configuration/timestamp"), new TimestampAction());
        rulestore.addRule(new ElementSelector("configuration/define"), new DefinePropertyAction());
    }

    public void doConfigure(List list)
        throws JoranException
    {
        super.doConfigure(list);
    }

    public abstract Appender getAppender();

    protected void oneAndOnlyOneCheck(Map map)
    {
        String s = null;
        if(map.size() != 0) goto _L2; else goto _L1
_L1:
        errorEmmissionCount = 1 + errorEmmissionCount;
        s = "No nested appenders found within the <sift> element in SiftingAppender.";
_L4:
        if(s != null && errorEmmissionCount < 4)
            addError(s);
        return;
_L2:
        if(map.size() > 1)
        {
            errorEmmissionCount = 1 + errorEmmissionCount;
            s = "Only and only one appender can be nested the <sift> element in SiftingAppender. See also http://logback.qos.ch/codes.html#1andOnly1";
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("{").append(key).append("=").append(value).append('}').toString();
    }

    static final String ONE_AND_ONLY_ONE_URL = "http://logback.qos.ch/codes.html#1andOnly1";
    int errorEmmissionCount;
    protected final String key;
    protected final Map parentPropertyMap;
    protected final String value;
}
