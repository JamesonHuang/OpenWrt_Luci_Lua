// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import java.nio.CharBuffer;
import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            TextDirectionHeuristicCompat, TextUtilsCompat

public class TextDirectionHeuristicsCompat
{
    private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl
    {

        protected boolean defaultIsRtl()
        {
            boolean flag = true;
            if(TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) != flag)
                flag = false;
            return flag;
        }

        public static final TextDirectionHeuristicLocale INSTANCE = new TextDirectionHeuristicLocale();


        public TextDirectionHeuristicLocale()
        {
            super(null);
        }
    }

    private static class AnyStrong
        implements TextDirectionAlgorithm
    {

        public int checkRtl(CharSequence charsequence, int i, int j)
        {
            int k;
            boolean flag;
            int l;
            int i1;
            k = 1;
            flag = false;
            l = i;
            i1 = i + j;
_L6:
            if(l >= i1) goto _L2; else goto _L1
_L1:
            TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(charsequence.charAt(l)));
            JVM INSTR tableswitch 0 1: default 56
        //                       0 62
        //                       1 81;
               goto _L3 _L4 _L5
_L3:
            l++;
              goto _L6
_L4:
            if(!mLookForRtl) goto _L8; else goto _L7
_L7:
            k = 0;
_L10:
            return k;
_L8:
            flag = true;
              goto _L3
_L5:
            if(!mLookForRtl) goto _L10; else goto _L9
_L9:
            flag = true;
              goto _L3
_L2:
            if(flag)
            {
                if(!mLookForRtl)
                    k = 0;
            } else
            {
                k = 2;
            }
              goto _L10
        }

        public static final AnyStrong INSTANCE_LTR = new AnyStrong(false);
        public static final AnyStrong INSTANCE_RTL = new AnyStrong(true);
        private final boolean mLookForRtl;


        private AnyStrong(boolean flag)
        {
            mLookForRtl = flag;
        }
    }

    private static class FirstStrong
        implements TextDirectionAlgorithm
    {

        public int checkRtl(CharSequence charsequence, int i, int j)
        {
            int k = 2;
            int l = i;
            for(int i1 = i + j; l < i1 && k == 2; l++)
                k = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(charsequence.charAt(l)));

            return k;
        }

        public static final FirstStrong INSTANCE = new FirstStrong();


        private FirstStrong()
        {
        }
    }

    private static interface TextDirectionAlgorithm
    {

        public abstract int checkRtl(CharSequence charsequence, int i, int j);
    }

    private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl
    {

        protected boolean defaultIsRtl()
        {
            return mDefaultIsRtl;
        }

        private final boolean mDefaultIsRtl;

        private TextDirectionHeuristicInternal(TextDirectionAlgorithm textdirectionalgorithm, boolean flag)
        {
            super(textdirectionalgorithm);
            mDefaultIsRtl = flag;
        }

    }

    private static abstract class TextDirectionHeuristicImpl
        implements TextDirectionHeuristicCompat
    {

        private boolean doCheck(CharSequence charsequence, int i, int j)
        {
            mAlgorithm.checkRtl(charsequence, i, j);
            JVM INSTR tableswitch 0 1: default 36
        //                       0 45
        //                       1 51;
               goto _L1 _L2 _L3
_L1:
            boolean flag = defaultIsRtl();
_L5:
            return flag;
_L2:
            flag = true;
            continue; /* Loop/switch isn't completed */
_L3:
            flag = false;
            if(true) goto _L5; else goto _L4
_L4:
        }

        protected abstract boolean defaultIsRtl();

        public boolean isRtl(CharSequence charsequence, int i, int j)
        {
            if(charsequence == null || i < 0 || j < 0 || charsequence.length() - j < i)
                throw new IllegalArgumentException();
            boolean flag;
            if(mAlgorithm == null)
                flag = defaultIsRtl();
            else
                flag = doCheck(charsequence, i, j);
            return flag;
        }

        public boolean isRtl(char ac[], int i, int j)
        {
            return isRtl(((CharSequence) (CharBuffer.wrap(ac))), i, j);
        }

        private final TextDirectionAlgorithm mAlgorithm;

        public TextDirectionHeuristicImpl(TextDirectionAlgorithm textdirectionalgorithm)
        {
            mAlgorithm = textdirectionalgorithm;
        }
    }


    public TextDirectionHeuristicsCompat()
    {
    }

    private static int isRtlText(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 32
    //                   1 37
    //                   2 37;
           goto _L1 _L2 _L3 _L3
_L1:
        byte byte0 = 2;
_L5:
        return byte0;
_L2:
        byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 0;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static int isRtlTextOrFormat(int i)
    {
        i;
        JVM INSTR lookupswitch 7: default 68
    //                   0: 72
    //                   1: 77
    //                   2: 77
    //                   14: 72
    //                   15: 72
    //                   16: 77
    //                   17: 77;
           goto _L1 _L2 _L3 _L3 _L2 _L2 _L3 _L3
_L1:
        byte byte0 = 2;
_L5:
        return byte0;
_L2:
        byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 0;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE;
    public static final TextDirectionHeuristicCompat LTR = new TextDirectionHeuristicInternal(null, false);
    public static final TextDirectionHeuristicCompat RTL = new TextDirectionHeuristicInternal(null, true);
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    static 
    {
        FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false);
        FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, true);
        ANYRTL_LTR = new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, false);
        LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    }


}
