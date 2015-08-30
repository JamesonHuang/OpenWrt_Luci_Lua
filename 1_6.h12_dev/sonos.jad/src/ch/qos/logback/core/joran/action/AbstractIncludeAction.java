// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.*;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.io.*;
import java.net.*;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public abstract class AbstractIncludeAction extends Action
{

    public AbstractIncludeAction()
    {
    }

    private URL attributeToURL(String s)
    {
        URL url;
        url = new URL(s);
        url.openStream().close();
_L2:
        return url;
        MalformedURLException malformedurlexception;
        malformedurlexception;
        if(!optional)
            handleError((new StringBuilder()).append("URL [").append(s).append("] is not well formed.").toString(), malformedurlexception);
_L3:
        url = null;
        if(true) goto _L2; else goto _L1
_L1:
        IOException ioexception;
        ioexception;
        if(!optional)
            handleError((new StringBuilder()).append("URL [").append(s).append("] cannot be opened.").toString(), ioexception);
          goto _L3
    }

    private boolean checkAttributes(Attributes attributes)
    {
        boolean flag = false;
        String s = attributes.getValue("file");
        String s1 = attributes.getValue("url");
        String s2 = attributes.getValue("resource");
        int i;
        if(!OptionHelper.isEmpty(s))
            i = 1;
        else
            i = 0;
        if(!OptionHelper.isEmpty(s1))
            i++;
        if(!OptionHelper.isEmpty(s2))
            i++;
        if(i == 0)
        {
            Object aobj1[] = new Object[3];
            aobj1[flag] = "file";
            aobj1[1] = "resource";
            aobj1[2] = "url";
            handleError(String.format("One of \"%1$s\", \"%2$s\" or \"%3$s\" attributes must be set.", aobj1), null);
        } else
        if(i > 1)
        {
            Object aobj[] = new Object[3];
            aobj[flag] = "file";
            aobj[1] = "resource";
            aobj[2] = "url";
            handleError(String.format("Only one of \"%1$s\", \"%2$s\" or \"%3$s\" attributes should be set.", aobj), null);
        } else
        if(i == 1)
            flag = true;
        else
            throw new IllegalStateException((new StringBuilder()).append("Count value [").append(i).append("] is not expected").toString());
        return flag;
    }

    private URL filePathAsURL(String s)
    {
        URL url;
        File file;
        url = null;
        file = new File(s);
        if(file.exists() && file.isFile()) goto _L2; else goto _L1
_L1:
        if(!optional)
            handleError((new StringBuilder()).append("File does not exist [").append(s).append("]").toString(), new FileNotFoundException(s));
_L4:
        return url;
_L2:
        URI uri = file.toURI();
        URL url1 = uri.toURL();
        url = url1;
        continue; /* Loop/switch isn't completed */
        MalformedURLException malformedurlexception;
        malformedurlexception;
        malformedurlexception.printStackTrace();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private URL getInputURL(InterpretationContext interpretationcontext, Attributes attributes)
    {
        String s = attributes.getValue("file");
        String s1 = attributes.getValue("url");
        String s2 = attributes.getValue("resource");
        URL url;
        if(!OptionHelper.isEmpty(s))
        {
            attributeInUse = interpretationcontext.subst(s);
            url = filePathAsURL(attributeInUse);
        } else
        if(!OptionHelper.isEmpty(s1))
        {
            attributeInUse = interpretationcontext.subst(s1);
            url = attributeToURL(attributeInUse);
        } else
        if(!OptionHelper.isEmpty(s2))
        {
            attributeInUse = interpretationcontext.subst(s2);
            url = resourceAsURL(attributeInUse);
        } else
        {
            throw new IllegalStateException("A URL stream should have been returned");
        }
        return url;
    }

    private URL resourceAsURL(String s)
    {
        URL url = null;
        URL url1 = Loader.getResourceBySelfClassLoader(s);
        if(url1 == null)
        {
            if(!optional)
                handleError((new StringBuilder()).append("Could not find resource corresponding to [").append(s).append("]").toString(), null);
        } else
        {
            url = url1;
        }
        return url;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        attributeInUse = null;
        optional = OptionHelper.toBoolean(attributes.getValue("optional"), false);
        if(checkAttributes(attributes)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        try
        {
            URL url = getInputURL(interpretationcontext, attributes);
            if(url != null)
                processInclude(interpretationcontext, url);
        }
        catch(JoranException joranexception)
        {
            handleError((new StringBuilder()).append("Error while parsing ").append(attributeInUse).toString(), joranexception);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void close(InputStream inputstream)
    {
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_8;
        inputstream.close();
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void end(InterpretationContext interpretationcontext, String s)
        throws ActionException
    {
    }

    protected String getAttributeInUse()
    {
        return attributeInUse;
    }

    public URL getUrl()
    {
        return urlInUse;
    }

    protected void handleError(String s, Exception exception)
    {
        addError(s, exception);
    }

    protected boolean isOptional()
    {
        return optional;
    }

    protected abstract void processInclude(InterpretationContext interpretationcontext, URL url)
        throws JoranException;

    private static final String FILE_ATTR = "file";
    private static final String OPTIONAL_ATTR = "optional";
    private static final String RESOURCE_ATTR = "resource";
    private static final String URL_ATTR = "url";
    private String attributeInUse;
    private boolean optional;
    private URL urlInUse;
}
