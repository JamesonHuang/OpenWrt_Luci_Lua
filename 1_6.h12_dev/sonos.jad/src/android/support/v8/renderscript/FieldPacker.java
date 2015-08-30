// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.Byte2;
import android.renderscript.Byte3;
import android.renderscript.Byte4;
import android.renderscript.Double2;
import android.renderscript.Double3;
import android.renderscript.Double4;
import android.renderscript.Float2;
import android.renderscript.Float3;
import android.renderscript.Float4;
import android.renderscript.Int2;
import android.renderscript.Int3;
import android.renderscript.Int4;
import android.renderscript.Long2;
import android.renderscript.Long3;
import android.renderscript.Long4;
import android.renderscript.Matrix2f;
import android.renderscript.Matrix3f;
import android.renderscript.Matrix4f;
import android.renderscript.Short2;
import android.renderscript.Short3;
import android.renderscript.Short4;
import android.util.Log;

// Referenced classes of package android.support.v8.renderscript:
//            RenderScript, Float2, Float3, Float4, 
//            Double2, Double3, Double4, Short2, 
//            Short3, Short4, Int2, Int3, 
//            Int4, Long2, Long3, Long4, 
//            Byte2, Byte3, Byte4, Matrix2f, 
//            Matrix3f, Matrix4f, BaseObj, RSIllegalArgumentException

public class FieldPacker
{

    public FieldPacker(int i)
    {
        mPos = 0;
        mLen = i;
        mData = new byte[i];
        if(RenderScript.shouldThunk())
            mN = new android.renderscript.FieldPacker(i);
    }

    public void addBoolean(boolean flag)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addBoolean(flag);
        } else
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            addI8((byte)i);
        }
    }

    public void addF32(float f)
    {
        if(RenderScript.shouldThunk())
            mN.addF32(f);
        else
            addI32(Float.floatToRawIntBits(f));
    }

    public void addF32(android.support.v8.renderscript.Float2 float2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addF32(new Float2(float2.x, float2.y));
        } else
        {
            addF32(float2.x);
            addF32(float2.y);
        }
    }

    public void addF32(android.support.v8.renderscript.Float3 float3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addF32(new Float3(float3.x, float3.y, float3.z));
        } else
        {
            addF32(float3.x);
            addF32(float3.y);
            addF32(float3.z);
        }
    }

    public void addF32(android.support.v8.renderscript.Float4 float4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addF32(new Float4(float4.x, float4.y, float4.z, float4.w));
        } else
        {
            addF32(float4.x);
            addF32(float4.y);
            addF32(float4.z);
            addF32(float4.w);
        }
    }

    public void addF64(double d)
    {
        if(RenderScript.shouldThunk())
            mN.addF64(d);
        else
            addI64(Double.doubleToRawLongBits(d));
    }

    public void addF64(android.support.v8.renderscript.Double2 double2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addF64(new Double2(double2.x, double2.y));
        } else
        {
            addF64(double2.x);
            addF64(double2.y);
        }
    }

    public void addF64(android.support.v8.renderscript.Double3 double3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addF64(new Double3(double3.x, double3.y, double3.z));
        } else
        {
            addF64(double3.x);
            addF64(double3.y);
            addF64(double3.z);
        }
    }

    public void addF64(android.support.v8.renderscript.Double4 double4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addF64(new Double4(double4.x, double4.y, double4.z, double4.w));
        } else
        {
            addF64(double4.x);
            addF64(double4.y);
            addF64(double4.z);
            addF64(double4.w);
        }
    }

    public void addI16(android.support.v8.renderscript.Short2 short2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI16(new Short2(short2.x, short2.y));
        } else
        {
            addI16(short2.x);
            addI16(short2.y);
        }
    }

    public void addI16(android.support.v8.renderscript.Short3 short3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI16(new Short3(short3.x, short3.y, short3.z));
        } else
        {
            addI16(short3.x);
            addI16(short3.y);
            addI16(short3.z);
        }
    }

    public void addI16(android.support.v8.renderscript.Short4 short4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI16(new Short4(short4.x, short4.y, short4.z, short4.w));
        } else
        {
            addI16(short4.x);
            addI16(short4.y);
            addI16(short4.z);
            addI16(short4.w);
        }
    }

    public void addI16(short word0)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI16(word0);
        } else
        {
            align(2);
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(word0 & 0xff);
            byte abyte1[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte1[j] = (byte)(word0 >> 8);
        }
    }

    public void addI32(int i)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI32(i);
        } else
        {
            align(4);
            byte abyte0[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte0[j] = (byte)(i & 0xff);
            byte abyte1[] = mData;
            int k = mPos;
            mPos = k + 1;
            abyte1[k] = (byte)(0xff & i >> 8);
            byte abyte2[] = mData;
            int l = mPos;
            mPos = l + 1;
            abyte2[l] = (byte)(0xff & i >> 16);
            byte abyte3[] = mData;
            int i1 = mPos;
            mPos = i1 + 1;
            abyte3[i1] = (byte)(0xff & i >> 24);
        }
    }

    public void addI32(android.support.v8.renderscript.Int2 int2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI32(new Int2(int2.x, int2.y));
        } else
        {
            addI32(int2.x);
            addI32(int2.y);
        }
    }

    public void addI32(android.support.v8.renderscript.Int3 int3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI32(new Int3(int3.x, int3.y, int3.z));
        } else
        {
            addI32(int3.x);
            addI32(int3.y);
            addI32(int3.z);
        }
    }

    public void addI32(android.support.v8.renderscript.Int4 int4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI32(new Int4(int4.x, int4.y, int4.z, int4.w));
        } else
        {
            addI32(int4.x);
            addI32(int4.y);
            addI32(int4.z);
            addI32(int4.w);
        }
    }

    public void addI64(long l)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI64(l);
        } else
        {
            align(8);
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l & 255L);
            byte abyte1[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte1[j] = (byte)(int)(255L & l >> 8);
            byte abyte2[] = mData;
            int k = mPos;
            mPos = k + 1;
            abyte2[k] = (byte)(int)(255L & l >> 16);
            byte abyte3[] = mData;
            int i1 = mPos;
            mPos = i1 + 1;
            abyte3[i1] = (byte)(int)(255L & l >> 24);
            byte abyte4[] = mData;
            int j1 = mPos;
            mPos = j1 + 1;
            abyte4[j1] = (byte)(int)(255L & l >> 32);
            byte abyte5[] = mData;
            int k1 = mPos;
            mPos = k1 + 1;
            abyte5[k1] = (byte)(int)(255L & l >> 40);
            byte abyte6[] = mData;
            int l1 = mPos;
            mPos = l1 + 1;
            abyte6[l1] = (byte)(int)(255L & l >> 48);
            byte abyte7[] = mData;
            int i2 = mPos;
            mPos = i2 + 1;
            abyte7[i2] = (byte)(int)(255L & l >> 56);
        }
    }

    public void addI64(android.support.v8.renderscript.Long2 long2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI64(new Long2(long2.x, long2.y));
        } else
        {
            addI64(long2.x);
            addI64(long2.y);
        }
    }

    public void addI64(android.support.v8.renderscript.Long3 long3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI64(new Long3(long3.x, long3.y, long3.z));
        } else
        {
            addI64(long3.x);
            addI64(long3.y);
            addI64(long3.z);
        }
    }

    public void addI64(android.support.v8.renderscript.Long4 long4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI64(new Long4(long4.x, long4.y, long4.z, long4.w));
        } else
        {
            addI64(long4.x);
            addI64(long4.y);
            addI64(long4.z);
            addI64(long4.w);
        }
    }

    public void addI8(byte byte0)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI8(byte0);
        } else
        {
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = byte0;
        }
    }

    public void addI8(android.support.v8.renderscript.Byte2 byte2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI8(new Byte2(byte2.x, byte2.y));
        } else
        {
            addI8(byte2.x);
            addI8(byte2.y);
        }
    }

    public void addI8(android.support.v8.renderscript.Byte3 byte3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI8(new Byte3(byte3.x, byte3.y, byte3.z));
        } else
        {
            addI8(byte3.x);
            addI8(byte3.y);
            addI8(byte3.z);
        }
    }

    public void addI8(android.support.v8.renderscript.Byte4 byte4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addI8(new Byte4(byte4.x, byte4.y, byte4.z, byte4.w));
        } else
        {
            addI8(byte4.x);
            addI8(byte4.y);
            addI8(byte4.z);
            addI8(byte4.w);
        }
    }

    public void addMatrix(android.support.v8.renderscript.Matrix2f matrix2f)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addMatrix(new Matrix2f(matrix2f.getArray()));
        } else
        {
            int i = 0;
            while(i < matrix2f.mMat.length) 
            {
                addF32(matrix2f.mMat[i]);
                i++;
            }
        }
    }

    public void addMatrix(android.support.v8.renderscript.Matrix3f matrix3f)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addMatrix(new Matrix3f(matrix3f.getArray()));
        } else
        {
            int i = 0;
            while(i < matrix3f.mMat.length) 
            {
                addF32(matrix3f.mMat[i]);
                i++;
            }
        }
    }

    public void addMatrix(android.support.v8.renderscript.Matrix4f matrix4f)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addMatrix(new Matrix4f(matrix4f.getArray()));
        } else
        {
            int i = 0;
            while(i < matrix4f.mMat.length) 
            {
                addF32(matrix4f.mMat[i]);
                i++;
            }
        }
    }

    public void addObj(BaseObj baseobj)
    {
        if(RenderScript.shouldThunk())
        {
            if(baseobj != null)
                mN.addObj(baseobj.getNObj());
            else
                mN.addObj(null);
        } else
        if(baseobj != null)
            addI32(baseobj.getID(null));
        else
            addI32(0);
    }

    public void addU16(int i)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU16(i);
        } else
        {
            if(i < 0 || i > 65535)
            {
                Log.e("rs", (new StringBuilder()).append("FieldPacker.addU16( ").append(i).append(" )").toString());
                throw new IllegalArgumentException("Saving value out of range for type");
            }
            align(2);
            byte abyte0[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte0[j] = (byte)(i & 0xff);
            byte abyte1[] = mData;
            int k = mPos;
            mPos = k + 1;
            abyte1[k] = (byte)(i >> 8);
        }
    }

    public void addU16(android.support.v8.renderscript.Int2 int2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU16(new Int2(int2.x, int2.y));
        } else
        {
            addU16(int2.x);
            addU16(int2.y);
        }
    }

    public void addU16(android.support.v8.renderscript.Int3 int3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU16(new Int3(int3.x, int3.y, int3.z));
        } else
        {
            addU16(int3.x);
            addU16(int3.y);
            addU16(int3.z);
        }
    }

    public void addU16(android.support.v8.renderscript.Int4 int4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU16(new Int4(int4.x, int4.y, int4.z, int4.w));
        } else
        {
            addU16(int4.x);
            addU16(int4.y);
            addU16(int4.z);
            addU16(int4.w);
        }
    }

    public void addU32(long l)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU32(l);
        } else
        {
            if(l < 0L || l > 0xffffffffL)
            {
                Log.e("rs", (new StringBuilder()).append("FieldPacker.addU32( ").append(l).append(" )").toString());
                throw new IllegalArgumentException("Saving value out of range for type");
            }
            align(4);
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l & 255L);
            byte abyte1[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte1[j] = (byte)(int)(255L & l >> 8);
            byte abyte2[] = mData;
            int k = mPos;
            mPos = k + 1;
            abyte2[k] = (byte)(int)(255L & l >> 16);
            byte abyte3[] = mData;
            int i1 = mPos;
            mPos = i1 + 1;
            abyte3[i1] = (byte)(int)(255L & l >> 24);
        }
    }

    public void addU32(android.support.v8.renderscript.Long2 long2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU32(new Long2(long2.x, long2.y));
        } else
        {
            addU32(long2.x);
            addU32(long2.y);
        }
    }

    public void addU32(android.support.v8.renderscript.Long3 long3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU32(new Long3(long3.x, long3.y, long3.z));
        } else
        {
            addU32(long3.x);
            addU32(long3.y);
            addU32(long3.z);
        }
    }

    public void addU32(android.support.v8.renderscript.Long4 long4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU32(new Long4(long4.x, long4.y, long4.z, long4.w));
        } else
        {
            addU32(long4.x);
            addU32(long4.y);
            addU32(long4.z);
            addU32(long4.w);
        }
    }

    public void addU64(long l)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU64(l);
        } else
        {
            if(l < 0L)
            {
                Log.e("rs", (new StringBuilder()).append("FieldPacker.addU64( ").append(l).append(" )").toString());
                throw new IllegalArgumentException("Saving value out of range for type");
            }
            align(8);
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l & 255L);
            byte abyte1[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte1[j] = (byte)(int)(255L & l >> 8);
            byte abyte2[] = mData;
            int k = mPos;
            mPos = k + 1;
            abyte2[k] = (byte)(int)(255L & l >> 16);
            byte abyte3[] = mData;
            int i1 = mPos;
            mPos = i1 + 1;
            abyte3[i1] = (byte)(int)(255L & l >> 24);
            byte abyte4[] = mData;
            int j1 = mPos;
            mPos = j1 + 1;
            abyte4[j1] = (byte)(int)(255L & l >> 32);
            byte abyte5[] = mData;
            int k1 = mPos;
            mPos = k1 + 1;
            abyte5[k1] = (byte)(int)(255L & l >> 40);
            byte abyte6[] = mData;
            int l1 = mPos;
            mPos = l1 + 1;
            abyte6[l1] = (byte)(int)(255L & l >> 48);
            byte abyte7[] = mData;
            int i2 = mPos;
            mPos = i2 + 1;
            abyte7[i2] = (byte)(int)(255L & l >> 56);
        }
    }

    public void addU64(android.support.v8.renderscript.Long2 long2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU64(new Long2(long2.x, long2.y));
        } else
        {
            addU64(long2.x);
            addU64(long2.y);
        }
    }

    public void addU64(android.support.v8.renderscript.Long3 long3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU64(new Long3(long3.x, long3.y, long3.z));
        } else
        {
            addU64(long3.x);
            addU64(long3.y);
            addU64(long3.z);
        }
    }

    public void addU64(android.support.v8.renderscript.Long4 long4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU64(new Long4(long4.x, long4.y, long4.z, long4.w));
        } else
        {
            addU64(long4.x);
            addU64(long4.y);
            addU64(long4.z);
            addU64(long4.w);
        }
    }

    public void addU8(android.support.v8.renderscript.Short2 short2)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU8(new Short2(short2.x, short2.y));
        } else
        {
            addU8(short2.x);
            addU8(short2.y);
        }
    }

    public void addU8(android.support.v8.renderscript.Short3 short3)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU8(new Short3(short3.x, short3.y, short3.z));
        } else
        {
            addU8(short3.x);
            addU8(short3.y);
            addU8(short3.z);
        }
    }

    public void addU8(android.support.v8.renderscript.Short4 short4)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU8(new Short4(short4.x, short4.y, short4.z, short4.w));
        } else
        {
            addU8(short4.x);
            addU8(short4.y);
            addU8(short4.z);
            addU8(short4.w);
        }
    }

    public void addU8(short word0)
    {
        if(RenderScript.shouldThunk())
        {
            mN.addU8(word0);
        } else
        {
            if(word0 < 0 || word0 > 255)
                throw new IllegalArgumentException("Saving value out of range for type");
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)word0;
        }
    }

    public void align(int i)
    {
        if(RenderScript.shouldThunk())
        {
            mN.align(i);
        } else
        {
            if(i <= 0 || (i & i - 1) != 0)
                throw new RSIllegalArgumentException((new StringBuilder()).append("argument must be a non-negative non-zero power of 2: ").append(i).toString());
            while((mPos & i - 1) != 0) 
            {
                byte abyte0[] = mData;
                int j = mPos;
                mPos = j + 1;
                abyte0[j] = 0;
            }
        }
    }

    public final byte[] getData()
    {
        byte abyte0[];
        if(RenderScript.shouldThunk())
            abyte0 = mN.getData();
        else
            abyte0 = mData;
        return abyte0;
    }

    public void reset()
    {
        if(RenderScript.shouldThunk())
            mN.reset();
        else
            mPos = 0;
    }

    public void reset(int i)
    {
        if(RenderScript.shouldThunk())
        {
            mN.reset(i);
        } else
        {
            if(i < 0 || i >= mLen)
                throw new RSIllegalArgumentException((new StringBuilder()).append("out of range argument: ").append(i).toString());
            mPos = i;
        }
    }

    public void skip(int i)
    {
        if(RenderScript.shouldThunk())
        {
            mN.skip(i);
        } else
        {
            int j = i + mPos;
            if(j < 0 || j > mLen)
                throw new RSIllegalArgumentException((new StringBuilder()).append("out of range argument: ").append(i).toString());
            mPos = j;
        }
    }

    private final byte mData[];
    private int mLen;
    private android.renderscript.FieldPacker mN;
    private int mPos;
}
