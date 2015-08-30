// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.rolling.RolloverFailure;
import java.io.File;
import java.lang.reflect.Method;

public class FileStoreUtil
{

    public FileStoreUtil()
    {
    }

    public static boolean areOnSameFileStore(File file, File file1)
        throws RolloverFailure
    {
        if(!file.exists())
            throw new IllegalArgumentException((new StringBuilder()).append("File [").append(file).append("] does not exist.").toString());
        if(!file1.exists())
            throw new IllegalArgumentException((new StringBuilder()).append("File [").append(file1).append("] does not exist.").toString());
        boolean flag;
        try
        {
            Class class1 = Class.forName("java.nio.file.Path");
            Class class2 = Class.forName("java.nio.file.Files");
            Method method = java/io/File.getMethod("toPath", new Class[0]);
            Class aclass[] = new Class[1];
            aclass[0] = class1;
            Method method1 = class2.getMethod("getFileStore", aclass);
            Object obj = method.invoke(file, new Object[0]);
            Object obj1 = method.invoke(file1, new Object[0]);
            Object aobj[] = new Object[1];
            aobj[0] = obj;
            Object obj2 = method1.invoke(null, aobj);
            Object aobj1[] = new Object[1];
            aobj1[0] = obj1;
            flag = obj2.equals(method1.invoke(null, aobj1));
        }
        catch(Exception exception)
        {
            throw new RolloverFailure((new StringBuilder()).append("Failed to check file store equality for [").append(file).append("] and [").append(file1).append("]").toString(), exception);
        }
        return flag;
    }

    static final String FILES_CLASS_STR = "java.nio.file.Files";
    static final String PATH_CLASS_STR = "java.nio.file.Path";
}
