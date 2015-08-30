// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import com.sonos.acr.Loc;
import com.sonos.acr.application.SonosApplication;
import com.sonos.sclib.SCLibDiagnosticConsoleLogCallback;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

// Referenced classes of package com.sonos.acr.sclib:
//            SCLibManager

public class AndroidDiagnosticConsoleLogCB extends SCLibDiagnosticConsoleLogCallback
{

    public AndroidDiagnosticConsoleLogCB()
    {
    }

    private String getRotatedLogData()
    {
        StringBuilder stringbuilder;
        File file;
        String s;
        int i;
        stringbuilder = new StringBuilder();
        file = SCLibManager.getStorageRoot(SonosApplication.getInstance());
        s = (new StringBuilder()).append(file).append("/app.log").toString();
        i = 0;
        BufferedReader bufferedreader;
        FileReader filereader = new FileReader(s);
        bufferedreader = new BufferedReader(filereader);
        ArrayList arraylist;
        arraylist = new ArrayList();
        do
        {
            String s1 = bufferedreader.readLine();
            if(s1 == null)
                break;
            arraylist.add(s1);
        } while(true);
          goto _L1
        IOException ioexception;
        ioexception;
_L6:
        ioexception.printStackTrace();
_L2:
        return stringbuilder.toString();
_L1:
        int j = Math.max(0, arraylist.size() - MAXLINES);
_L3:
        int k;
        if(i < MAXLINES && j + i < arraylist.size())
            break MISSING_BLOCK_LABEL_249;
        k = 0;
        if(i < MAXLINES)
            k = MAXLINES - i;
        if(k > 0)
        {
            File file1 = new File((new StringBuilder()).append(file).append("/app.backup.1.log").toString());
            if(file1.exists())
            {
                FileReader filereader1 = new FileReader(file1);
                BufferedReader bufferedreader1 = new BufferedReader(filereader1);
                arraylist.clear();
                do
                {
                    String s2 = bufferedreader1.readLine();
                    if(s2 == null)
                        break;
                    arraylist.add(s2);
                } while(true);
                break MISSING_BLOCK_LABEL_353;
            }
        }
          goto _L2
        stringbuilder.append((String)arraylist.get(j + i));
        stringbuilder.append("\n");
        i++;
          goto _L3
_L5:
        int l;
        if(l >= k) goto _L2; else goto _L4
_L4:
        int i1 = (-1 + arraylist.size()) - l;
        if(i1 > 0 && i1 < arraylist.size())
        {
            stringbuilder.insert(0, "\n");
            stringbuilder.insert(0, (String)arraylist.get(i1));
        }
        l++;
          goto _L5
        ioexception;
          goto _L6
        l = 0;
          goto _L5
    }

    public String executeCommand(String s)
    {
        Process process;
        InputStream inputstream;
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        process = Runtime.getRuntime().exec(s);
        inputstream = process.getInputStream();
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[1024];
        do
        {
            int i = inputstream.read(abyte0);
            if(i == -1)
                break;
            bytearrayoutputstream.write(abyte0, 0, i);
        } while(true);
          goto _L1
        IOException ioexception;
        ioexception;
        String s1 = Loc.NOLOC("Error executing command");
_L3:
        return s1;
_L1:
        String s3;
        inputstream.close();
        String s2 = new String(bytearrayoutputstream.toByteArray());
        ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
        InputStream inputstream1 = process.getErrorStream();
        do
        {
            int j = inputstream1.read(abyte0);
            if(j == -1)
                break;
            bytearrayoutputstream1.write(abyte0, 0, j);
        } while(true);
        inputstream1.close();
        s3 = (new StringBuilder()).append(s2).append(new String(bytearrayoutputstream1.toByteArray())).toString();
        s1 = s3;
        if(true) goto _L3; else goto _L2
_L2:
    }

    public String getDiagnosticConsoleLog()
    {
        return (new StringBuilder()).append("<CRType>ACR</CRType>\n<Logcat><![CDATA[").append(getRotatedLogData()).append("]]></Logcat>\n").append("<date>").append(new Date()).append("</date>").toString();
    }

    private static int MAXLINES = 1000;

}
