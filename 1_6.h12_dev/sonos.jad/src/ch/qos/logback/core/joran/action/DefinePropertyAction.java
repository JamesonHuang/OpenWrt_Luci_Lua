// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.spi.PropertyDefiner;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action, ActionUtil

public class DefinePropertyAction extends Action
{

    public DefinePropertyAction()
    {
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        scopeStr = null;
        scope = null;
        propertyName = null;
        definer = null;
        inError = false;
        propertyName = attributes.getValue("name");
        scopeStr = attributes.getValue("scope");
        scope = ActionUtil.stringToScope(scopeStr);
        if(OptionHelper.isEmpty(propertyName))
        {
            addError((new StringBuilder()).append("Missing property name for property definer. Near [").append(s).append("] line ").append(getLineNumber(interpretationcontext)).toString());
            inError = true;
        } else
        {
            String s1 = attributes.getValue("class");
            if(OptionHelper.isEmpty(s1))
            {
                addError((new StringBuilder()).append("Missing class name for property definer. Near [").append(s).append("] line ").append(getLineNumber(interpretationcontext)).toString());
                inError = true;
            } else
            {
                try
                {
                    addInfo((new StringBuilder()).append("About to instantiate property definer of type [").append(s1).append("]").toString());
                    definer = (PropertyDefiner)OptionHelper.instantiateByClassName(s1, ch/qos/logback/core/spi/PropertyDefiner, context);
                    definer.setContext(context);
                    if(definer instanceof LifeCycle)
                        ((LifeCycle)definer).start();
                    interpretationcontext.pushObject(definer);
                }
                catch(Exception exception)
                {
                    inError = true;
                    addError((new StringBuilder()).append("Could not create an PropertyDefiner of type [").append(s1).append("].").toString(), exception);
                    throw new ActionException(exception);
                }
            }
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
        if(!inError) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(interpretationcontext.peekObject() != definer)
        {
            addWarn((new StringBuilder()).append("The object at the of the stack is not the property definer for property named [").append(propertyName).append("] pushed earlier.").toString());
        } else
        {
            addInfo((new StringBuilder()).append("Popping property definer for property named [").append(propertyName).append("] from the object stack").toString());
            interpretationcontext.popObject();
            String s1 = definer.getPropertyValue();
            if(s1 != null)
                ActionUtil.setProperty(interpretationcontext, propertyName, s1, scope);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    PropertyDefiner definer;
    boolean inError;
    String propertyName;
    ActionUtil.Scope scope;
    String scopeStr;
}
