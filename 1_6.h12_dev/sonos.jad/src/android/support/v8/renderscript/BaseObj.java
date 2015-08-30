// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            RenderScript, RSIllegalArgumentException, RSInvalidStateException, RenderScriptThunker, 
//            RSRuntimeException

public class BaseObj
{

    BaseObj(int i, RenderScript renderscript)
    {
        renderscript.validate();
        mRS = renderscript;
        mID = i;
        mDestroyed = false;
    }

    void checkValid()
    {
        if(mID == 0 && getNObj() == null)
            throw new RSIllegalArgumentException("Invalid object.");
        else
            return;
    }

    /**
     * @deprecated Method destroy is deprecated
     */

    public void destroy()
    {
        this;
        JVM INSTR monitorenter ;
        if(mDestroyed)
            throw new RSInvalidStateException("Object already destroyed.");
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mDestroyed = true;
        mRS.nObjDestroy(mID);
        this;
        JVM INSTR monitorexit ;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            BaseObj baseobj = (BaseObj)obj;
            if(mID != baseobj.mID)
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void finalize()
        throws Throwable
    {
        if(!mDestroyed)
        {
            if(mID != 0 && mRS.isAlive())
                mRS.nObjDestroy(mID);
            mRS = null;
            mID = 0;
            mDestroyed = true;
        }
        super.finalize();
    }

    int getID(RenderScript renderscript)
    {
        mRS.validate();
        if(!RenderScript.isNative) goto _L2; else goto _L1
_L1:
        (RenderScriptThunker)renderscript;
        if(getNObj() == null) goto _L2; else goto _L3
_L3:
        int i = getNObj().hashCode();
_L5:
        return i;
_L2:
        if(mDestroyed)
            throw new RSInvalidStateException("using a destroyed object.");
        if(mID == 0)
            throw new RSRuntimeException("Internal error: Object id 0.");
        if(renderscript != null && renderscript != mRS)
            throw new RSInvalidStateException("using object with mismatched context.");
        i = mID;
        if(true) goto _L5; else goto _L4
_L4:
    }

    android.renderscript.BaseObj getNObj()
    {
        return null;
    }

    public int hashCode()
    {
        return mID;
    }

    void setID(int i)
    {
        if(mID != 0)
        {
            throw new RSRuntimeException("Internal Error, reset of object ID.");
        } else
        {
            mID = i;
            return;
        }
    }

    private boolean mDestroyed;
    private int mID;
    RenderScript mRS;
}
