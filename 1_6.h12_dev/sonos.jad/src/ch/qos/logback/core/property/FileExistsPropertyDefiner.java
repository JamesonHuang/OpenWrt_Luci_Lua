// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.property;

import ch.qos.logback.core.PropertyDefinerBase;
import ch.qos.logback.core.util.OptionHelper;
import java.io.File;

public class FileExistsPropertyDefiner extends PropertyDefinerBase
{

    public FileExistsPropertyDefiner()
    {
    }

    public String getPath()
    {
        return path;
    }

    public String getPropertyValue()
    {
        String s;
        if(OptionHelper.isEmpty(path))
        {
            addError("The \"path\" property must be set.");
            s = null;
        } else
        {
            s = booleanAsStr((new File(path)).exists());
        }
        return s;
    }

    public void setPath(String s)
    {
        path = s;
    }

    String path;
}
