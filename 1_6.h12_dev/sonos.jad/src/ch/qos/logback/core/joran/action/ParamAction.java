// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.util.PropertySetter;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public class ParamAction extends Action
{

    public ParamAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        String s1 = attributes.getValue("name");
        String s2 = attributes.getValue("value");
        if(s1 == null)
        {
            inError = true;
            addError(NO_NAME);
        } else
        if(s2 == null)
        {
            inError = true;
            addError(NO_VALUE);
        } else
        {
            String s3 = s2.trim();
            PropertySetter propertysetter = new PropertySetter(interpretationcontext.peekObject());
            propertysetter.setContext(context);
            String s4 = interpretationcontext.subst(s3);
            propertysetter.setProperty(interpretationcontext.subst(s1), s4);
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
    }

    public void finish(InterpretationContext interpretationcontext)
    {
    }

    static String NO_NAME = "No name attribute in <param> element";
    static String NO_VALUE = "No value attribute in <param> element";
    boolean inError;

}
