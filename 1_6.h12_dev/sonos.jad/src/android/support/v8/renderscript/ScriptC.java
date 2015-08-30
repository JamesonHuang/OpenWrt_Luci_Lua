// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.content.Context;
import android.content.res.Resources;
import java.io.*;

// Referenced classes of package android.support.v8.renderscript:
//            Script, RenderScript, ScriptCThunker, RenderScriptThunker, 
//            RSRuntimeException

public class ScriptC extends Script
{

    protected ScriptC(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    protected ScriptC(RenderScript renderscript, Resources resources, int i)
    {
        super(0, renderscript);
        if(RenderScript.isNative)
        {
            mT = new ScriptCThunker((RenderScriptThunker)renderscript, resources, i);
        } else
        {
            int j = internalCreate(renderscript, resources, i);
            if(j == 0)
                throw new RSRuntimeException("Loading of ScriptC script failed.");
            setID(j);
        }
    }

    /**
     * @deprecated Method internalCreate is deprecated
     */

    private static int internalCreate(RenderScript renderscript, Resources resources, int i)
    {
        android/support/v8/renderscript/ScriptC;
        JVM INSTR monitorenter ;
        InputStream inputstream = resources.openRawResource(i);
        byte abyte0[];
        int j;
        abyte0 = new byte[1024];
        j = 0;
_L1:
        int l;
        int k = abyte0.length - j;
        if(k == 0)
        {
            byte abyte1[] = new byte[2 * abyte0.length];
            System.arraycopy(abyte0, 0, abyte1, 0, abyte0.length);
            abyte0 = abyte1;
            k = abyte0.length - j;
        }
        l = inputstream.read(abyte0, j, k);
        if(l > 0)
            break MISSING_BLOCK_LABEL_120;
        inputstream.close();
        int i1 = renderscript.nScriptCCreate(resources.getResourceEntryName(i), renderscript.getApplicationContext().getCacheDir().toString(), abyte0, j);
        android/support/v8/renderscript/ScriptC;
        JVM INSTR monitorexit ;
        return i1;
        j += l;
          goto _L1
        Exception exception1;
        exception1;
        try
        {
            inputstream.close();
            throw exception1;
        }
        catch(IOException ioexception) { }
        throw new android.content.res.Resources.NotFoundException();
        Exception exception;
        exception;
        android/support/v8/renderscript/ScriptC;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static final String TAG = "ScriptC";
}
