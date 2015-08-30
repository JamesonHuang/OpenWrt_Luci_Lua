// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.*;
import ch.qos.logback.core.joran.util.PropertySetter;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.*;
import java.util.Stack;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            ImplicitAction, IADataForComplexProperty

public class NestedComplexPropertyIA extends ImplicitAction
{

    public NestedComplexPropertyIA()
    {
        actionDataStack = new Stack();
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        IADataForComplexProperty iadataforcomplexproperty = (IADataForComplexProperty)actionDataStack.peek();
        String s1 = interpretationcontext.subst(attributes.getValue("class"));
        try
        {
            Class class1;
            if(!OptionHelper.isEmpty(s1))
                class1 = Loader.loadClass(s1, context);
            else
                class1 = iadataforcomplexproperty.parentBean.getClassNameViaImplicitRules(iadataforcomplexproperty.getComplexPropertyName(), iadataforcomplexproperty.getAggregationType(), interpretationcontext.getDefaultNestedComponentRegistry());
            if(class1 == null)
            {
                iadataforcomplexproperty.inError = true;
                addError((new StringBuilder()).append("Could not find an appropriate class for property [").append(s).append("]").toString());
            } else
            {
                if(OptionHelper.isEmpty(s1))
                    addInfo((new StringBuilder()).append("Assuming default type [").append(class1.getName()).append("] for [").append(s).append("] property").toString());
                iadataforcomplexproperty.setNestedComplexProperty(class1.newInstance());
                if(iadataforcomplexproperty.getNestedComplexProperty() instanceof ContextAware)
                    ((ContextAware)iadataforcomplexproperty.getNestedComplexProperty()).setContext(context);
                interpretationcontext.pushObject(iadataforcomplexproperty.getNestedComplexProperty());
            }
        }
        catch(Exception exception)
        {
            iadataforcomplexproperty.inError = true;
            addError((new StringBuilder()).append("Could not create component [").append(s).append("] of type [").append(s1).append("]").toString(), exception);
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
        IADataForComplexProperty iadataforcomplexproperty = (IADataForComplexProperty)actionDataStack.pop();
        if(!iadataforcomplexproperty.inError) goto _L2; else goto _L1
_L1:
        return;
_L2:
        PropertySetter propertysetter = new PropertySetter(iadataforcomplexproperty.getNestedComplexProperty());
        propertysetter.setContext(context);
        if(propertysetter.computeAggregationType("parent") == AggregationType.AS_COMPLEX_PROPERTY)
            propertysetter.setComplexProperty("parent", iadataforcomplexproperty.parentBean.getObj());
        Object obj = iadataforcomplexproperty.getNestedComplexProperty();
        if((obj instanceof LifeCycle) && NoAutoStartUtil.notMarkedWithNoAutoStart(obj))
            ((LifeCycle)obj).start();
        if(interpretationcontext.peekObject() == iadataforcomplexproperty.getNestedComplexProperty())
            break; /* Loop/switch isn't completed */
        addError("The object on the top the of the stack is not the component pushed earlier.");
        if(true) goto _L1; else goto _L3
_L3:
        interpretationcontext.popObject();
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$util$AggregationType[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$util$AggregationType = new int[AggregationType.values().length];
                NoSuchFieldError nosuchfielderror4;
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.NOT_FOUND.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_BASIC_PROPERTY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_BASIC_PROPERTY_COLLECTION.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_COMPLEX_PROPERTY_COLLECTION.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_COMPLEX_PROPERTY.ordinal()] = 5;
_L2:
                return;
                nosuchfielderror4;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls1..SwitchMap.ch.qos.logback.core.util.AggregationType[iadataforcomplexproperty.aggregationType.ordinal()])
        {
        case 4: // '\004'
            iadataforcomplexproperty.parentBean.addComplexProperty(s, iadataforcomplexproperty.getNestedComplexProperty());
            break;

        case 5: // '\005'
            iadataforcomplexproperty.parentBean.setComplexProperty(s, iadataforcomplexproperty.getNestedComplexProperty());
            break;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public boolean isApplicable(ElementPath elementpath, Attributes attributes, InterpretationContext interpretationcontext)
    {
        boolean flag;
        String s;
        flag = false;
        s = elementpath.peekLast();
        if(!interpretationcontext.isEmpty()) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        PropertySetter propertysetter = new PropertySetter(interpretationcontext.peekObject());
        propertysetter.setContext(context);
        AggregationType aggregationtype = propertysetter.computeAggregationType(s);
        switch(_cls1..SwitchMap.ch.qos.logback.core.util.AggregationType[aggregationtype.ordinal()])
        {
        default:
            addError((new StringBuilder()).append("PropertySetter.computeAggregationType returned ").append(aggregationtype).toString());
            break;

        case 4: // '\004'
        case 5: // '\005'
            IADataForComplexProperty iadataforcomplexproperty = new IADataForComplexProperty(propertysetter, aggregationtype, s);
            actionDataStack.push(iadataforcomplexproperty);
            flag = true;
            break;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    Stack actionDataStack;
}
