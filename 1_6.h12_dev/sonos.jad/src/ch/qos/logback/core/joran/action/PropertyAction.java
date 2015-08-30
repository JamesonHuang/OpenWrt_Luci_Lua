// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.io.*;
import java.net.URL;
import java.util.Properties;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action, ActionUtil

public class PropertyAction extends Action
{

    public PropertyAction()
    {
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        String s1;
        String s2;
        ActionUtil.Scope scope;
        String s4;
        if("substitutionProperty".equals(s))
            addWarn("[substitutionProperty] element has been deprecated. Please use the [property] element instead.");
        s1 = attributes.getValue("name");
        s2 = attributes.getValue("value");
        scope = ActionUtil.stringToScope(attributes.getValue("scope"));
        if(!checkFileAttributeSanity(attributes))
            break MISSING_BLOCK_LABEL_157;
        s4 = interpretationcontext.subst(attributes.getValue("file"));
        loadAndSetProperties(interpretationcontext, new FileInputStream(s4), scope);
_L1:
        return;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        addError((new StringBuilder()).append("Could not find properties file [").append(s4).append("].").toString());
          goto _L1
        IOException ioexception1;
        ioexception1;
        addError((new StringBuilder()).append("Could not read properties file [").append(s4).append("].").toString(), ioexception1);
          goto _L1
        if(checkResourceAttributeSanity(attributes))
        {
            String s3 = interpretationcontext.subst(attributes.getValue("resource"));
            URL url = Loader.getResourceBySelfClassLoader(s3);
            if(url == null)
                addError((new StringBuilder()).append("Could not find resource [").append(s3).append("].").toString());
            else
                try
                {
                    loadAndSetProperties(interpretationcontext, url.openStream(), scope);
                }
                catch(IOException ioexception)
                {
                    addError((new StringBuilder()).append("Could not read resource file [").append(s3).append("].").toString(), ioexception);
                }
        } else
        if(checkValueNameAttributesSanity(attributes))
            ActionUtil.setProperty(interpretationcontext, s1, interpretationcontext.subst(RegularEscapeUtil.basicEscape(s2).trim()), scope);
        else
            addError(INVALID_ATTRIBUTES);
          goto _L1
    }

    boolean checkFileAttributeSanity(Attributes attributes)
    {
        String s = attributes.getValue("file");
        String s1 = attributes.getValue("name");
        String s2 = attributes.getValue("value");
        String s3 = attributes.getValue("resource");
        boolean flag;
        if(!OptionHelper.isEmpty(s) && OptionHelper.isEmpty(s1) && OptionHelper.isEmpty(s2) && OptionHelper.isEmpty(s3))
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean checkResourceAttributeSanity(Attributes attributes)
    {
        String s = attributes.getValue("file");
        String s1 = attributes.getValue("name");
        String s2 = attributes.getValue("value");
        boolean flag;
        if(!OptionHelper.isEmpty(attributes.getValue("resource")) && OptionHelper.isEmpty(s1) && OptionHelper.isEmpty(s2) && OptionHelper.isEmpty(s))
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean checkValueNameAttributesSanity(Attributes attributes)
    {
        String s = attributes.getValue("file");
        String s1 = attributes.getValue("name");
        String s2 = attributes.getValue("value");
        String s3 = attributes.getValue("resource");
        boolean flag;
        if(!OptionHelper.isEmpty(s1) && !OptionHelper.isEmpty(s2) && OptionHelper.isEmpty(s) && OptionHelper.isEmpty(s3))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
    }

    public void finish(InterpretationContext interpretationcontext)
    {
    }

    void loadAndSetProperties(InterpretationContext interpretationcontext, InputStream inputstream, ActionUtil.Scope scope)
        throws IOException
    {
        Properties properties = new Properties();
        properties.load(inputstream);
        inputstream.close();
        ActionUtil.setProperties(interpretationcontext, properties, scope);
    }

    static String INVALID_ATTRIBUTES = "In <property> element, either the \"file\" attribute alone, or the \"resource\" element alone, or both the \"name\" and \"value\" attributes must be set.";
    static final String RESOURCE_ATTRIBUTE = "resource";

}
