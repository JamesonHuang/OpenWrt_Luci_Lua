// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.AppenderAttachable;
import ch.qos.logback.core.util.OptionHelper;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public class AppenderRefAction extends Action
{

    public AppenderRefAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        inError = false;
        Object obj = interpretationcontext.peekObject();
        if(!(obj instanceof AppenderAttachable))
        {
            String s3 = (new StringBuilder()).append("Could not find an AppenderAttachable at the top of execution stack. Near [").append(s).append("] line ").append(getLineNumber(interpretationcontext)).toString();
            inError = true;
            addError(s3);
        } else
        {
            AppenderAttachable appenderattachable = (AppenderAttachable)obj;
            String s1 = interpretationcontext.subst(attributes.getValue("ref"));
            if(OptionHelper.isEmpty(s1))
            {
                inError = true;
                addError("Missing appender ref attribute in <appender-ref> tag.");
            } else
            {
                Appender appender = (Appender)((HashMap)interpretationcontext.getObjectMap().get("APPENDER_BAG")).get(s1);
                if(appender == null)
                {
                    String s2 = (new StringBuilder()).append("Could not find an appender named [").append(s1).append("]. Did you define it below instead of above in the configuration file?").toString();
                    inError = true;
                    addError(s2);
                    addError("See http://logback.qos.ch/codes.html#appender_order for more details.");
                } else
                {
                    addInfo((new StringBuilder()).append("Attaching appender named [").append(s1).append("] to ").append(appenderattachable).toString());
                    appenderattachable.addAppender(appender);
                }
            }
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
    }

    boolean inError;
}
