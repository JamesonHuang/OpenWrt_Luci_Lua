// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;


class ContainerHelpers
{

    ContainerHelpers()
    {
    }

    static int binarySearch(int ai[], int i, int j)
    {
        int i1;
label0:
        {
            int k = 0;
            for(int l = i - 1; k <= l;)
            {
                i1 = k + l >>> 1;
                int j1 = ai[i1];
                if(j1 < j)
                {
                    k = i1 + 1;
                } else
                {
                    if(j1 <= j)
                        break label0;
                    l = i1 - 1;
                }
            }

            i1 = ~k;
        }
        return i1;
    }

    static int binarySearch(long al[], int i, long l)
    {
        int i1;
label0:
        {
            int j = 0;
            for(int k = i - 1; j <= k;)
            {
                i1 = j + k >>> 1;
                long l1 = al[i1];
                if(l1 < l)
                {
                    j = i1 + 1;
                } else
                {
                    if(l1 <= l)
                        break label0;
                    k = i1 - 1;
                }
            }

            i1 = ~j;
        }
        return i1;
    }

    public static boolean equal(Object obj, Object obj1)
    {
        boolean flag;
        if(obj == obj1 || obj != null && obj.equals(obj1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static int idealByteArraySize(int i)
    {
        int j = 4;
        do
        {
label0:
            {
                if(j < 32)
                {
                    if(i > -12 + (1 << j))
                        break label0;
                    i = -12 + (1 << j);
                }
                return i;
            }
            j++;
        } while(true);
    }

    public static int idealIntArraySize(int i)
    {
        return idealByteArraySize(i * 4) / 4;
    }

    public static int idealLongArraySize(int i)
    {
        return idealByteArraySize(i * 8) / 8;
    }

    static final int EMPTY_INTS[] = new int[0];
    static final long EMPTY_LONGS[] = new long[0];
    static final Object EMPTY_OBJECTS[] = new Object[0];

}
