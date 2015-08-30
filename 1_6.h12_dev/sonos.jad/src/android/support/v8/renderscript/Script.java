// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.BaseObj;
import android.util.SparseArray;
import java.io.UnsupportedEncodingException;

// Referenced classes of package android.support.v8.renderscript:
//            BaseObj, ScriptCThunker, RenderScript, Allocation, 
//            RSDriverException, RSIllegalArgumentException, FieldPacker, Element, 
//            Type

public class Script extends android.support.v8.renderscript.BaseObj
{
    public static final class LaunchOptions
    {

        public int getXEnd()
        {
            return xend;
        }

        public int getXStart()
        {
            return xstart;
        }

        public int getYEnd()
        {
            return yend;
        }

        public int getYStart()
        {
            return ystart;
        }

        public int getZEnd()
        {
            return zend;
        }

        public int getZStart()
        {
            return zstart;
        }

        public LaunchOptions setX(int i, int j)
        {
            if(i < 0 || j <= i)
            {
                throw new RSIllegalArgumentException("Invalid dimensions");
            } else
            {
                xstart = i;
                xend = j;
                return this;
            }
        }

        public LaunchOptions setY(int i, int j)
        {
            if(i < 0 || j <= i)
            {
                throw new RSIllegalArgumentException("Invalid dimensions");
            } else
            {
                ystart = i;
                yend = j;
                return this;
            }
        }

        public LaunchOptions setZ(int i, int j)
        {
            if(i < 0 || j <= i)
            {
                throw new RSIllegalArgumentException("Invalid dimensions");
            } else
            {
                zstart = i;
                zend = j;
                return this;
            }
        }

        private int strategy;
        private int xend;
        private int xstart;
        private int yend;
        private int ystart;
        private int zend;
        private int zstart;







        public LaunchOptions()
        {
            xstart = 0;
            ystart = 0;
            xend = 0;
            yend = 0;
            zstart = 0;
            zend = 0;
        }
    }

    public static class FieldBase
    {

        public Allocation getAllocation()
        {
            return mAllocation;
        }

        public Element getElement()
        {
            return mElement;
        }

        public Type getType()
        {
            return mAllocation.getType();
        }

        protected void init(RenderScript renderscript, int i)
        {
            mAllocation = Allocation.createSized(renderscript, mElement, i, 1);
        }

        protected void init(RenderScript renderscript, int i, int j)
        {
            mAllocation = Allocation.createSized(renderscript, mElement, i, j | 1);
        }

        public void updateAllocation()
        {
        }

        protected Allocation mAllocation;
        protected Element mElement;

        protected FieldBase()
        {
        }
    }

    public static class Builder
    {

        RenderScript mRS;

        Builder(RenderScript renderscript)
        {
            mRS = renderscript;
        }
    }

    public static final class FieldID extends android.support.v8.renderscript.BaseObj
    {

        android.renderscript.FieldID mN;
        Script mScript;
        int mSlot;

        FieldID(int i, RenderScript renderscript, Script script, int j)
        {
            super(i, renderscript);
            mScript = script;
            mSlot = j;
        }
    }

    public static final class KernelID extends android.support.v8.renderscript.BaseObj
    {

        android.renderscript.KernelID mN;
        Script mScript;
        int mSig;
        int mSlot;

        KernelID(int i, RenderScript renderscript, Script script, int j, int k)
        {
            super(i, renderscript);
            mScript = script;
            mSlot = j;
            mSig = k;
        }
    }


    Script(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public void bindAllocation(Allocation allocation, int i)
    {
        if(mT != null)
        {
            mT.thunkBindAllocation(allocation, i);
        } else
        {
            mRS.validate();
            if(allocation != null)
                mRS.nScriptBindAllocation(getID(mRS), allocation.getID(mRS), i);
            else
                mRS.nScriptBindAllocation(getID(mRS), 0, i);
        }
    }

    protected FieldID createFieldID(int i, Element element)
    {
        RenderScript _tmp = mRS;
        FieldID fieldid1;
        if(RenderScript.isNative)
        {
            FieldID fieldid = new FieldID(0, mRS, this, i);
            if(mT != null)
                fieldid.mN = mT.thunkCreateFieldID(i, element);
            mFIDs.put(i, fieldid);
            fieldid1 = fieldid;
        } else
        {
            FieldID fieldid2 = (FieldID)mFIDs.get(i);
            if(fieldid2 != null)
            {
                fieldid1 = fieldid2;
            } else
            {
                int j = mRS.nScriptFieldIDCreate(getID(mRS), i);
                if(j == 0)
                    throw new RSDriverException("Failed to create FieldID");
                FieldID fieldid3 = new FieldID(j, mRS, this, i);
                mFIDs.put(i, fieldid3);
                fieldid1 = fieldid3;
            }
        }
        return fieldid1;
    }

    protected KernelID createKernelID(int i, int j, Element element, Element element1)
    {
        KernelID kernelid = (KernelID)mKIDs.get(i);
        KernelID kernelid2;
        if(kernelid != null)
        {
            kernelid2 = kernelid;
        } else
        {
            RenderScript _tmp = mRS;
            if(RenderScript.isNative)
            {
                KernelID kernelid1 = new KernelID(0, mRS, this, i, j);
                if(mT != null)
                    kernelid1.mN = mT.thunkCreateKernelID(i, j, element, element1);
                mKIDs.put(i, kernelid1);
                kernelid2 = kernelid1;
            } else
            {
                int k = mRS.nScriptKernelIDCreate(getID(mRS), i, j);
                if(k == 0)
                    throw new RSDriverException("Failed to create KernelID");
                KernelID kernelid3 = new KernelID(k, mRS, this, i, j);
                mKIDs.put(i, kernelid3);
                kernelid2 = kernelid3;
            }
        }
        return kernelid2;
    }

    protected void forEach(int i, Allocation allocation, Allocation allocation1, FieldPacker fieldpacker)
    {
        if(mT != null)
        {
            mT.thunkForEach(i, allocation, allocation1, fieldpacker);
        } else
        {
            if(allocation == null && allocation1 == null)
                throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
            int j = 0;
            if(allocation != null)
                j = allocation.getID(mRS);
            int k = 0;
            if(allocation1 != null)
                k = allocation1.getID(mRS);
            byte abyte0[] = null;
            if(fieldpacker != null)
                abyte0 = fieldpacker.getData();
            mRS.nScriptForEach(getID(mRS), i, j, k, abyte0);
        }
    }

    protected void forEach(int i, Allocation allocation, Allocation allocation1, FieldPacker fieldpacker, LaunchOptions launchoptions)
    {
        if(mT != null)
        {
            mT.thunkForEach(i, allocation, allocation1, fieldpacker, launchoptions);
        } else
        {
            if(allocation == null && allocation1 == null)
                throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
            if(launchoptions == null)
            {
                forEach(i, allocation, allocation1, fieldpacker);
            } else
            {
                int j = 0;
                if(allocation != null)
                    j = allocation.getID(mRS);
                int k = 0;
                if(allocation1 != null)
                    k = allocation1.getID(mRS);
                byte abyte0[] = null;
                if(fieldpacker != null)
                    abyte0 = fieldpacker.getData();
                mRS.nScriptForEachClipped(getID(mRS), i, j, k, abyte0, launchoptions.xstart, launchoptions.xend, launchoptions.ystart, launchoptions.yend, launchoptions.zstart, launchoptions.zend);
            }
        }
    }

    volatile BaseObj getNObj()
    {
        return getNObj();
    }

    android.renderscript.Script getNObj()
    {
        return mT;
    }

    protected void invoke(int i)
    {
        if(mT != null)
            mT.thunkInvoke(i);
        else
            mRS.nScriptInvoke(getID(mRS), i);
    }

    protected void invoke(int i, FieldPacker fieldpacker)
    {
        if(mT != null)
            mT.thunkInvoke(i, fieldpacker);
        else
        if(fieldpacker != null)
            mRS.nScriptInvokeV(getID(mRS), i, fieldpacker.getData());
        else
            mRS.nScriptInvoke(getID(mRS), i);
    }

    public void setTimeZone(String s)
    {
        if(mT != null)
        {
            mT.thunkSetTimeZone(s);
        } else
        {
            mRS.validate();
            try
            {
                mRS.nScriptSetTimeZone(getID(mRS), s.getBytes("UTF-8"));
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                throw new RuntimeException(unsupportedencodingexception);
            }
        }
    }

    public void setVar(int i, double d)
    {
        if(mT != null)
            mT.thunkSetVar(i, d);
        else
            mRS.nScriptSetVarD(getID(mRS), i, d);
    }

    public void setVar(int i, float f)
    {
        if(mT != null)
            mT.thunkSetVar(i, f);
        else
            mRS.nScriptSetVarF(getID(mRS), i, f);
    }

    public void setVar(int i, int j)
    {
        if(mT != null)
            mT.thunkSetVar(i, j);
        else
            mRS.nScriptSetVarI(getID(mRS), i, j);
    }

    public void setVar(int i, long l)
    {
        if(mT != null)
            mT.thunkSetVar(i, l);
        else
            mRS.nScriptSetVarJ(getID(mRS), i, l);
    }

    public void setVar(int i, android.support.v8.renderscript.BaseObj baseobj)
    {
        if(mT != null)
        {
            mT.thunkSetVar(i, baseobj);
        } else
        {
            RenderScript renderscript = mRS;
            int j = getID(mRS);
            int k;
            if(baseobj == null)
                k = 0;
            else
                k = baseobj.getID(mRS);
            renderscript.nScriptSetVarObj(j, i, k);
        }
    }

    public void setVar(int i, FieldPacker fieldpacker)
    {
        if(mT != null)
            mT.thunkSetVar(i, fieldpacker);
        else
            mRS.nScriptSetVarV(getID(mRS), i, fieldpacker.getData());
    }

    public void setVar(int i, FieldPacker fieldpacker, Element element, int ai[])
    {
        if(mT != null)
            mT.thunkSetVar(i, fieldpacker, element, ai);
        else
            mRS.nScriptSetVarVE(getID(mRS), i, fieldpacker.getData(), element.getID(mRS), ai);
    }

    public void setVar(int i, boolean flag)
    {
        if(mT != null)
        {
            mT.thunkSetVar(i, flag);
        } else
        {
            RenderScript renderscript = mRS;
            int j = getID(mRS);
            int k;
            if(flag)
                k = 1;
            else
                k = 0;
            renderscript.nScriptSetVarI(j, i, k);
        }
    }

    private final SparseArray mFIDs = new SparseArray();
    private final SparseArray mKIDs = new SparseArray();
    ScriptCThunker mT;
}
