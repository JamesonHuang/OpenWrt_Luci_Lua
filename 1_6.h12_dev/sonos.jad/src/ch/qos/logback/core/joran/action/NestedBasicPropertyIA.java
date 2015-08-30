// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.ElementPath;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.util.PropertySetter;
import ch.qos.logback.core.util.AggregationType;
import java.util.Stack;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            ImplicitAction, IADataForBasicProperty

public class NestedBasicPropertyIA extends ImplicitAction
{

    public NestedBasicPropertyIA()
    {
        actionDataStack = new Stack();
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
    }

    public void body(InterpretationContext interpretationcontext, String s)
    {
        String s1;
        IADataForBasicProperty iadataforbasicproperty;
        s1 = interpretationcontext.subst(s);
        iadataforbasicproperty = (IADataForBasicProperty)actionDataStack.peek();
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
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_COMPLEX_PROPERTY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_COMPLEX_PROPERTY_COLLECTION.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_BASIC_PROPERTY.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_BASIC_PROPERTY_COLLECTION.ordinal()] = 5;
_L2:
                return;
                nosuchfielderror4;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.ch.qos.logback.core.util.AggregationType[iadataforbasicproperty.aggregationType.ordinal()];
        JVM INSTR tableswitch 4 5: default 52
    //                   4 53
    //                   5 70;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        iadataforbasicproperty.parentBean.setProperty(iadataforbasicproperty.propertyName, s1);
        continue; /* Loop/switch isn't completed */
_L3:
        iadataforbasicproperty.parentBean.addBasicProperty(iadataforbasicproperty.propertyName, s1);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
        actionDataStack.pop();
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
            addError((new StringBuilder()).append("PropertySetter.canContainComponent returned ").append(aggregationtype).toString());
            break;

        case 4: // '\004'
        case 5: // '\005'
            IADataForBasicProperty iadataforbasicproperty = new IADataForBasicProperty(propertysetter, aggregationtype, s);
            actionDataStack.push(iadataforbasicproperty);
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
