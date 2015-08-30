// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            ScriptGroup, ExceptionThunker, AllocationThunker, RenderScript, 
//            Allocation, RenderScriptThunker, TypeThunker, Type

class ScriptGroupThunker extends android.support.v8.renderscript.ScriptGroup
{
    public static final class Builder
    {

        public Builder addConnection(Type type, Script.KernelID kernelid, Script.FieldID fieldid)
        {
            TypeThunker typethunker = (TypeThunker)type;
            try
            {
                bN.addConnection(typethunker.getNObj(), kernelid.mN, fieldid.mN);
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
            return this;
        }

        public Builder addConnection(Type type, Script.KernelID kernelid, Script.KernelID kernelid1)
        {
            TypeThunker typethunker = (TypeThunker)type;
            try
            {
                bN.addConnection(typethunker.getNObj(), kernelid.mN, kernelid1.mN);
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
            return this;
        }

        public Builder addKernel(Script.KernelID kernelid)
        {
            try
            {
                bN.addKernel(kernelid.mN);
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
            return this;
        }

        public ScriptGroupThunker create()
        {
            ScriptGroupThunker scriptgroupthunker = new ScriptGroupThunker(0, mRS);
            try
            {
                scriptgroupthunker.mN = bN.create();
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
            return scriptgroupthunker;
        }

        android.renderscript.ScriptGroup.Builder bN;
        RenderScript mRS;

        Builder(RenderScript renderscript)
        {
            RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
            mRS = renderscript;
            try
            {
                bN = new android.renderscript.ScriptGroup.Builder(renderscriptthunker.mN);
                return;
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
        }
    }


    ScriptGroupThunker(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public void execute()
    {
        try
        {
            mN.execute();
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    volatile BaseObj getNObj()
    {
        return getNObj();
    }

    ScriptGroup getNObj()
    {
        return mN;
    }

    public void setInput(Script.KernelID kernelid, Allocation allocation)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        try
        {
            mN.setInput(kernelid.mN, allocationthunker.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setOutput(Script.KernelID kernelid, Allocation allocation)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        try
        {
            mN.setOutput(kernelid.mN, allocationthunker.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    ScriptGroup mN;
}
