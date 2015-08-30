// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            RSIllegalArgumentException, RSInvalidStateException, RSDriverException, RSRuntimeException

class ExceptionThunker
{

    ExceptionThunker()
    {
    }

    static RuntimeException convertException(RuntimeException runtimeexception)
    {
        if(!(runtimeexception instanceof RSIllegalArgumentException)) goto _L2; else goto _L1
_L1:
        runtimeexception = new android.support.v8.renderscript.RSIllegalArgumentException(runtimeexception.getMessage());
_L4:
        return runtimeexception;
_L2:
        if(runtimeexception instanceof RSInvalidStateException)
            runtimeexception = new android.support.v8.renderscript.RSInvalidStateException(runtimeexception.getMessage());
        else
        if(runtimeexception instanceof RSDriverException)
            runtimeexception = new android.support.v8.renderscript.RSDriverException(runtimeexception.getMessage());
        else
        if(runtimeexception instanceof RSRuntimeException)
            runtimeexception = new android.support.v8.renderscript.RSRuntimeException(runtimeexception.getMessage());
        if(true) goto _L4; else goto _L3
_L3:
    }
}
