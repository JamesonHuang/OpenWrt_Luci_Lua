// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFilterUtil
{

    public FileFilterUtil()
    {
    }

    public static String afterLastSlash(String s)
    {
        int i = s.lastIndexOf('/');
        if(i != -1)
            s = s.substring(i + 1);
        return s;
    }

    public static int extractCounter(File file, String s)
    {
        Pattern pattern = Pattern.compile(s);
        String s1 = file.getName();
        Matcher matcher = pattern.matcher(s1);
        if(!matcher.matches())
            throw new IllegalStateException((new StringBuilder()).append("The regex [").append(s).append("] should match [").append(s1).append("]").toString());
        else
            return (new Integer(matcher.group(1))).intValue();
    }

    public static File[] filesInFolderMatchingStemRegex(File file, final String stemRegex)
    {
        File afile[];
        if(file == null)
            afile = new File[0];
        else
        if(!file.exists() || !file.isDirectory())
            afile = new File[0];
        else
            afile = file.listFiles(new FilenameFilter() {

                public boolean accept(File file1, String s)
                {
                    return s.matches(stemRegex);
                }

                final String val$stemRegex;

            
            {
                stemRegex = s;
                super();
            }
            }
);
        return afile;
    }

    public static int findHighestCounter(File afile[], String s)
    {
        int i = 0x80000000;
        int j = afile.length;
        int k = 0;
        while(k < j) 
        {
            int l = extractCounter(afile[k], s);
            if(i >= l)
                l = i;
            k++;
            i = l;
        }
        return i;
    }

    public static boolean isEmptyDirectory(File file)
    {
        if(!file.isDirectory())
            throw new IllegalArgumentException((new StringBuilder()).append("[").append(file).append("] must be a directory").toString());
        String as[] = file.list();
        boolean flag;
        if(as == null || as.length == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void removeEmptyParentDirectories(File file, int i)
    {
        if(i < 3) goto _L2; else goto _L1
_L1:
        return;
_L2:
        File file1 = file.getParentFile();
        if(file1.isDirectory() && isEmptyDirectory(file1))
        {
            file1.delete();
            removeEmptyParentDirectories(file1, i + 1);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static void reverseSortFileArrayByName(File afile[])
    {
        Arrays.sort(afile, new Comparator() {

            public int compare(File file, File file1)
            {
                String s = file.getName();
                return file1.getName().compareTo(s);
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((File)obj, (File)obj1);
            }

        }
);
    }

    public static String slashify(String s)
    {
        return s.replace('\\', '/');
    }

    public static void sortFileArrayByName(File afile[])
    {
        Arrays.sort(afile, new Comparator() {

            public int compare(File file, File file1)
            {
                return file.getName().compareTo(file1.getName());
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((File)obj, (File)obj1);
            }

        }
);
    }
}
