// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            BackStackRecord, Fragment, FragmentManagerImpl

final class BackStackState
    implements Parcelable
{

    public BackStackState(Parcel parcel)
    {
        mOps = parcel.createIntArray();
        mTransition = parcel.readInt();
        mTransitionStyle = parcel.readInt();
        mName = parcel.readString();
        mIndex = parcel.readInt();
        mBreadCrumbTitleRes = parcel.readInt();
        mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mBreadCrumbShortTitleRes = parcel.readInt();
        mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mSharedElementSourceNames = parcel.createStringArrayList();
        mSharedElementTargetNames = parcel.createStringArrayList();
    }

    public BackStackState(FragmentManagerImpl fragmentmanagerimpl, BackStackRecord backstackrecord)
    {
        int i = 0;
        for(BackStackRecord.Op op = backstackrecord.mHead; op != null; op = op.next)
            if(op.removed != null)
                i += op.removed.size();

        mOps = new int[i + 7 * backstackrecord.mNumOp];
        if(!backstackrecord.mAddToBackStack)
            throw new IllegalStateException("Not on back stack");
        BackStackRecord.Op op1 = backstackrecord.mHead;
        int j = 0;
        while(op1 != null) 
        {
            int ai[] = mOps;
            int k = j + 1;
            ai[j] = op1.cmd;
            int ai1[] = mOps;
            int l = k + 1;
            int i1;
            int ai2[];
            int j1;
            int ai3[];
            int k1;
            int ai4[];
            int l1;
            int ai5[];
            int i2;
            if(op1.fragment != null)
                i1 = op1.fragment.mIndex;
            else
                i1 = -1;
            ai1[k] = i1;
            ai2 = mOps;
            j1 = l + 1;
            ai2[l] = op1.enterAnim;
            ai3 = mOps;
            k1 = j1 + 1;
            ai3[j1] = op1.exitAnim;
            ai4 = mOps;
            l1 = k1 + 1;
            ai4[k1] = op1.popEnterAnim;
            ai5 = mOps;
            i2 = l1 + 1;
            ai5[l1] = op1.popExitAnim;
            int j2;
            if(op1.removed != null)
            {
                int k2 = op1.removed.size();
                int ai7[] = mOps;
                int l2 = i2 + 1;
                ai7[i2] = k2;
                int i3 = 0;
                int j3;
                int k3;
                for(j3 = l2; i3 < k2; j3 = k3)
                {
                    int ai8[] = mOps;
                    k3 = j3 + 1;
                    ai8[j3] = ((Fragment)op1.removed.get(i3)).mIndex;
                    i3++;
                }

                j2 = j3;
            } else
            {
                int ai6[] = mOps;
                j2 = i2 + 1;
                ai6[i2] = 0;
            }
            op1 = op1.next;
            j = j2;
        }
        mTransition = backstackrecord.mTransition;
        mTransitionStyle = backstackrecord.mTransitionStyle;
        mName = backstackrecord.mName;
        mIndex = backstackrecord.mIndex;
        mBreadCrumbTitleRes = backstackrecord.mBreadCrumbTitleRes;
        mBreadCrumbTitleText = backstackrecord.mBreadCrumbTitleText;
        mBreadCrumbShortTitleRes = backstackrecord.mBreadCrumbShortTitleRes;
        mBreadCrumbShortTitleText = backstackrecord.mBreadCrumbShortTitleText;
        mSharedElementSourceNames = backstackrecord.mSharedElementSourceNames;
        mSharedElementTargetNames = backstackrecord.mSharedElementTargetNames;
    }

    public int describeContents()
    {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl fragmentmanagerimpl)
    {
        BackStackRecord backstackrecord = new BackStackRecord(fragmentmanagerimpl);
        int i = 0;
        for(int j = 0; i < mOps.length; j++)
        {
            BackStackRecord.Op op = new BackStackRecord.Op();
            int ai[] = mOps;
            int k = i + 1;
            op.cmd = ai[i];
            if(FragmentManagerImpl.DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Instantiate ").append(backstackrecord).append(" op #").append(j).append(" base fragment #").append(mOps[k]).toString());
            int ai1[] = mOps;
            int l = k + 1;
            int i1 = ai1[k];
            int ai2[];
            int j1;
            int ai3[];
            int k1;
            int ai4[];
            int l1;
            int ai5[];
            int i2;
            int ai6[];
            int j2;
            int k2;
            if(i1 >= 0)
                op.fragment = (Fragment)fragmentmanagerimpl.mActive.get(i1);
            else
                op.fragment = null;
            ai2 = mOps;
            j1 = l + 1;
            op.enterAnim = ai2[l];
            ai3 = mOps;
            k1 = j1 + 1;
            op.exitAnim = ai3[j1];
            ai4 = mOps;
            l1 = k1 + 1;
            op.popEnterAnim = ai4[k1];
            ai5 = mOps;
            i2 = l1 + 1;
            op.popExitAnim = ai5[l1];
            ai6 = mOps;
            j2 = i2 + 1;
            k2 = ai6[i2];
            if(k2 > 0)
            {
                op.removed = new ArrayList(k2);
                for(int l2 = 0; l2 < k2;)
                {
                    if(FragmentManagerImpl.DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("Instantiate ").append(backstackrecord).append(" set remove fragment #").append(mOps[j2]).toString());
                    ArrayList arraylist = fragmentmanagerimpl.mActive;
                    int ai7[] = mOps;
                    int i3 = j2 + 1;
                    Fragment fragment = (Fragment)arraylist.get(ai7[j2]);
                    op.removed.add(fragment);
                    l2++;
                    j2 = i3;
                }

            }
            i = j2;
            backstackrecord.addOp(op);
        }

        backstackrecord.mTransition = mTransition;
        backstackrecord.mTransitionStyle = mTransitionStyle;
        backstackrecord.mName = mName;
        backstackrecord.mIndex = mIndex;
        backstackrecord.mAddToBackStack = true;
        backstackrecord.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        backstackrecord.mBreadCrumbTitleText = mBreadCrumbTitleText;
        backstackrecord.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        backstackrecord.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        backstackrecord.mSharedElementSourceNames = mSharedElementSourceNames;
        backstackrecord.mSharedElementTargetNames = mSharedElementTargetNames;
        backstackrecord.bumpBackStackNesting(1);
        return backstackrecord;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeIntArray(mOps);
        parcel.writeInt(mTransition);
        parcel.writeInt(mTransitionStyle);
        parcel.writeString(mName);
        parcel.writeInt(mIndex);
        parcel.writeInt(mBreadCrumbTitleRes);
        TextUtils.writeToParcel(mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(mSharedElementSourceNames);
        parcel.writeStringList(mSharedElementTargetNames);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BackStackState createFromParcel(Parcel parcel)
        {
            return new BackStackState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BackStackState[] newArray(int i)
        {
            return new BackStackState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int mOps[];
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;

}
