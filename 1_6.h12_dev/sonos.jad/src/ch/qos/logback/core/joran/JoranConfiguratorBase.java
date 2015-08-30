// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran;

import ch.qos.logback.core.joran.action.AppenderAction;
import ch.qos.logback.core.joran.action.AppenderRefAction;
import ch.qos.logback.core.joran.action.ConversionRuleAction;
import ch.qos.logback.core.joran.action.DefinePropertyAction;
import ch.qos.logback.core.joran.action.NestedBasicPropertyIA;
import ch.qos.logback.core.joran.action.NestedComplexPropertyIA;
import ch.qos.logback.core.joran.action.NewRuleAction;
import ch.qos.logback.core.joran.action.ParamAction;
import ch.qos.logback.core.joran.action.PropertyAction;
import ch.qos.logback.core.joran.action.StatusListenerAction;
import ch.qos.logback.core.joran.action.TimestampAction;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.spi.Interpreter;
import ch.qos.logback.core.joran.spi.RuleStore;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.joran:
//            GenericConfigurator

public abstract class JoranConfiguratorBase extends GenericConfigurator
{

    public JoranConfiguratorBase()
    {
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
        rulestore.addRule(new ElementSelector("configuration/substitutionProperty"), new PropertyAction());
        rulestore.addRule(new ElementSelector("configuration/timestamp"), new TimestampAction());
        rulestore.addRule(new ElementSelector("configuration/define"), new DefinePropertyAction());
        rulestore.addRule(new ElementSelector("configuration/conversionRule"), new ConversionRuleAction());
        rulestore.addRule(new ElementSelector("configuration/statusListener"), new StatusListenerAction());
        rulestore.addRule(new ElementSelector("configuration/appender"), new AppenderAction());
        rulestore.addRule(new ElementSelector("configuration/appender/appender-ref"), new AppenderRefAction());
        rulestore.addRule(new ElementSelector("configuration/newRule"), new NewRuleAction());
        rulestore.addRule(new ElementSelector("*/param"), new ParamAction());
    }

    protected void buildInterpreter()
    {
        super.buildInterpreter();
        Map map = interpreter.getInterpretationContext().getObjectMap();
        map.put("APPENDER_BAG", new HashMap());
        map.put("FILTER_CHAIN_BAG", new HashMap());
    }

    public List getErrorList()
    {
        return null;
    }

    public InterpretationContext getInterpretationContext()
    {
        return interpreter.getInterpretationContext();
    }
}
