// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.provider;

import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.provider:
//            DocumentFile

class RawDocumentFile extends DocumentFile
{

    RawDocumentFile(DocumentFile documentfile, File file)
    {
        super(documentfile);
        mFile = file;
    }

    private static boolean deleteContents(File file)
    {
        File afile[] = file.listFiles();
        boolean flag = true;
        if(afile != null)
        {
            int i = afile.length;
            for(int j = 0; j < i; j++)
            {
                File file1 = afile[j];
                if(file1.isDirectory())
                    flag &= deleteContents(file1);
                if(!file1.delete())
                {
                    Log.w("DocumentFile", (new StringBuilder()).append("Failed to delete ").append(file1).toString());
                    flag = false;
                }
            }

        }
        return flag;
    }

    private static String getTypeForName(String s)
    {
        int i = s.lastIndexOf('.');
        if(i < 0) goto _L2; else goto _L1
_L1:
        String s1;
        String s2 = s.substring(i + 1).toLowerCase();
        s1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s2);
        if(s1 == null) goto _L2; else goto _L3
_L3:
        return s1;
_L2:
        s1 = "application/octet-stream";
        if(true) goto _L3; else goto _L4
_L4:
    }

    public boolean canRead()
    {
        return mFile.canRead();
    }

    public boolean canWrite()
    {
        return mFile.canWrite();
    }

    public DocumentFile createDirectory(String s)
    {
        File file = new File(mFile, s);
        RawDocumentFile rawdocumentfile;
        if(file.isDirectory() || file.mkdir())
            rawdocumentfile = new RawDocumentFile(this, file);
        else
            rawdocumentfile = null;
        return rawdocumentfile;
    }

    public DocumentFile createFile(String s, String s1)
    {
        String s2 = MimeTypeMap.getSingleton().getExtensionFromMimeType(s);
        if(s2 != null)
            s1 = (new StringBuilder()).append(s1).append(".").append(s2).toString();
        File file = new File(mFile, s1);
        RawDocumentFile rawdocumentfile;
        try
        {
            file.createNewFile();
            rawdocumentfile = new RawDocumentFile(this, file);
        }
        catch(IOException ioexception)
        {
            Log.w("DocumentFile", (new StringBuilder()).append("Failed to createFile: ").append(ioexception).toString());
            rawdocumentfile = null;
        }
        return rawdocumentfile;
    }

    public boolean delete()
    {
        deleteContents(mFile);
        return mFile.delete();
    }

    public boolean exists()
    {
        return mFile.exists();
    }

    public String getName()
    {
        return mFile.getName();
    }

    public String getType()
    {
        String s;
        if(mFile.isDirectory())
            s = null;
        else
            s = getTypeForName(mFile.getName());
        return s;
    }

    public Uri getUri()
    {
        return Uri.fromFile(mFile);
    }

    public boolean isDirectory()
    {
        return mFile.isDirectory();
    }

    public boolean isFile()
    {
        return mFile.isFile();
    }

    public long lastModified()
    {
        return mFile.lastModified();
    }

    public long length()
    {
        return mFile.length();
    }

    public DocumentFile[] listFiles()
    {
        ArrayList arraylist = new ArrayList();
        File afile[] = mFile.listFiles();
        if(afile != null)
        {
            int i = afile.length;
            for(int j = 0; j < i; j++)
                arraylist.add(new RawDocumentFile(this, afile[j]));

        }
        return (DocumentFile[])arraylist.toArray(new DocumentFile[arraylist.size()]);
    }

    public boolean renameTo(String s)
    {
        File file = new File(mFile.getParentFile(), s);
        boolean flag;
        if(mFile.renameTo(file))
        {
            mFile = file;
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private File mFile;
}
