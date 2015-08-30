// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            ShareCompatJB, ShareCompatICS

public class ShareCompat
{
    public static class IntentReader
    {

        public static IntentReader from(Activity activity)
        {
            return new IntentReader(activity);
        }

        public ComponentName getCallingActivity()
        {
            return mCallingActivity;
        }

        public Drawable getCallingActivityIcon()
        {
            Drawable drawable = null;
            if(mCallingActivity != null) goto _L2; else goto _L1
_L1:
            return drawable;
_L2:
            PackageManager packagemanager = mActivity.getPackageManager();
            Drawable drawable1 = packagemanager.getActivityIcon(mCallingActivity);
            drawable = drawable1;
            continue; /* Loop/switch isn't completed */
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            namenotfoundexception;
            Log.e("IntentReader", "Could not retrieve icon for calling activity", namenotfoundexception);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public Drawable getCallingApplicationIcon()
        {
            Drawable drawable = null;
            if(mCallingPackage != null) goto _L2; else goto _L1
_L1:
            return drawable;
_L2:
            PackageManager packagemanager = mActivity.getPackageManager();
            Drawable drawable1 = packagemanager.getApplicationIcon(mCallingPackage);
            drawable = drawable1;
            continue; /* Loop/switch isn't completed */
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            namenotfoundexception;
            Log.e("IntentReader", "Could not retrieve icon for calling application", namenotfoundexception);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public CharSequence getCallingApplicationLabel()
        {
            CharSequence charsequence = null;
            if(mCallingPackage != null) goto _L2; else goto _L1
_L1:
            return charsequence;
_L2:
            PackageManager packagemanager = mActivity.getPackageManager();
            CharSequence charsequence1 = packagemanager.getApplicationLabel(packagemanager.getApplicationInfo(mCallingPackage, 0));
            charsequence = charsequence1;
            continue; /* Loop/switch isn't completed */
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            namenotfoundexception;
            Log.e("IntentReader", "Could not retrieve label for calling application", namenotfoundexception);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public String getCallingPackage()
        {
            return mCallingPackage;
        }

        public String[] getEmailBcc()
        {
            return mIntent.getStringArrayExtra("android.intent.extra.BCC");
        }

        public String[] getEmailCc()
        {
            return mIntent.getStringArrayExtra("android.intent.extra.CC");
        }

        public String[] getEmailTo()
        {
            return mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
        }

        public String getHtmlText()
        {
            String s = mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
            if(s != null) goto _L2; else goto _L1
_L1:
            CharSequence charsequence = getText();
            if(!(charsequence instanceof Spanned)) goto _L4; else goto _L3
_L3:
            s = Html.toHtml((Spanned)charsequence);
_L2:
            return s;
_L4:
            if(charsequence != null)
                s = ShareCompat.IMPL.escapeHtml(charsequence);
            if(true) goto _L2; else goto _L5
_L5:
        }

        public Uri getStream()
        {
            return (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }

        public Uri getStream(int i)
        {
            if(mStreams == null && isMultipleShare())
                mStreams = mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            Uri uri;
            if(mStreams != null)
                uri = (Uri)mStreams.get(i);
            else
            if(i == 0)
                uri = (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
            else
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Stream items available: ").append(getStreamCount()).append(" index requested: ").append(i).toString());
            return uri;
        }

        public int getStreamCount()
        {
            if(mStreams == null && isMultipleShare())
                mStreams = mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            int i;
            if(mStreams != null)
                i = mStreams.size();
            else
            if(mIntent.hasExtra("android.intent.extra.STREAM"))
                i = 1;
            else
                i = 0;
            return i;
        }

        public String getSubject()
        {
            return mIntent.getStringExtra("android.intent.extra.SUBJECT");
        }

        public CharSequence getText()
        {
            return mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
        }

        public String getType()
        {
            return mIntent.getType();
        }

        public boolean isMultipleShare()
        {
            return "android.intent.action.SEND_MULTIPLE".equals(mIntent.getAction());
        }

        public boolean isShareIntent()
        {
            String s = mIntent.getAction();
            boolean flag;
            if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s))
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isSingleShare()
        {
            return "android.intent.action.SEND".equals(mIntent.getAction());
        }

        private static final String TAG = "IntentReader";
        private Activity mActivity;
        private ComponentName mCallingActivity;
        private String mCallingPackage;
        private Intent mIntent;
        private ArrayList mStreams;

        private IntentReader(Activity activity)
        {
            mActivity = activity;
            mIntent = activity.getIntent();
            mCallingPackage = ShareCompat.getCallingPackage(activity);
            mCallingActivity = ShareCompat.getCallingActivity(activity);
        }
    }

    public static class IntentBuilder
    {

        private void combineArrayExtra(String s, ArrayList arraylist)
        {
            String as[] = mIntent.getStringArrayExtra(s);
            int i;
            String as1[];
            if(as != null)
                i = as.length;
            else
                i = 0;
            as1 = new String[i + arraylist.size()];
            arraylist.toArray(as1);
            if(as != null)
                System.arraycopy(as, 0, as1, arraylist.size(), i);
            mIntent.putExtra(s, as1);
        }

        private void combineArrayExtra(String s, String as[])
        {
            Intent intent = getIntent();
            String as1[] = intent.getStringArrayExtra(s);
            int i;
            String as2[];
            if(as1 != null)
                i = as1.length;
            else
                i = 0;
            as2 = new String[i + as.length];
            if(as1 != null)
                System.arraycopy(as1, 0, as2, 0, i);
            System.arraycopy(as, 0, as2, i, as.length);
            intent.putExtra(s, as2);
        }

        public static IntentBuilder from(Activity activity)
        {
            return new IntentBuilder(activity);
        }

        public IntentBuilder addEmailBcc(String s)
        {
            if(mBccAddresses == null)
                mBccAddresses = new ArrayList();
            mBccAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailBcc(String as[])
        {
            combineArrayExtra("android.intent.extra.BCC", as);
            return this;
        }

        public IntentBuilder addEmailCc(String s)
        {
            if(mCcAddresses == null)
                mCcAddresses = new ArrayList();
            mCcAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailCc(String as[])
        {
            combineArrayExtra("android.intent.extra.CC", as);
            return this;
        }

        public IntentBuilder addEmailTo(String s)
        {
            if(mToAddresses == null)
                mToAddresses = new ArrayList();
            mToAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailTo(String as[])
        {
            combineArrayExtra("android.intent.extra.EMAIL", as);
            return this;
        }

        public IntentBuilder addStream(Uri uri)
        {
            Uri uri1 = (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
            if(uri1 == null)
            {
                this = setStream(uri);
            } else
            {
                if(mStreams == null)
                    mStreams = new ArrayList();
                if(uri1 != null)
                {
                    mIntent.removeExtra("android.intent.extra.STREAM");
                    mStreams.add(uri1);
                }
                mStreams.add(uri);
            }
            return this;
        }

        public Intent createChooserIntent()
        {
            return Intent.createChooser(getIntent(), mChooserTitle);
        }

        Activity getActivity()
        {
            return mActivity;
        }

        public Intent getIntent()
        {
            int i = 1;
            if(mToAddresses != null)
            {
                combineArrayExtra("android.intent.extra.EMAIL", mToAddresses);
                mToAddresses = null;
            }
            if(mCcAddresses != null)
            {
                combineArrayExtra("android.intent.extra.CC", mCcAddresses);
                mCcAddresses = null;
            }
            if(mBccAddresses != null)
            {
                combineArrayExtra("android.intent.extra.BCC", mBccAddresses);
                mBccAddresses = null;
            }
            boolean flag;
            if(mStreams == null || mStreams.size() <= i)
                i = 0;
            flag = mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
            if(i == 0 && flag)
            {
                mIntent.setAction("android.intent.action.SEND");
                if(mStreams != null && !mStreams.isEmpty())
                    mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)mStreams.get(0));
                else
                    mIntent.removeExtra("android.intent.extra.STREAM");
                mStreams = null;
            }
            if(i != 0 && !flag)
            {
                mIntent.setAction("android.intent.action.SEND_MULTIPLE");
                if(mStreams != null && !mStreams.isEmpty())
                    mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", mStreams);
                else
                    mIntent.removeExtra("android.intent.extra.STREAM");
            }
            return mIntent;
        }

        public IntentBuilder setChooserTitle(int i)
        {
            return setChooserTitle(mActivity.getText(i));
        }

        public IntentBuilder setChooserTitle(CharSequence charsequence)
        {
            mChooserTitle = charsequence;
            return this;
        }

        public IntentBuilder setEmailBcc(String as[])
        {
            mIntent.putExtra("android.intent.extra.BCC", as);
            return this;
        }

        public IntentBuilder setEmailCc(String as[])
        {
            mIntent.putExtra("android.intent.extra.CC", as);
            return this;
        }

        public IntentBuilder setEmailTo(String as[])
        {
            if(mToAddresses != null)
                mToAddresses = null;
            mIntent.putExtra("android.intent.extra.EMAIL", as);
            return this;
        }

        public IntentBuilder setHtmlText(String s)
        {
            mIntent.putExtra("android.intent.extra.HTML_TEXT", s);
            if(!mIntent.hasExtra("android.intent.extra.TEXT"))
                setText(Html.fromHtml(s));
            return this;
        }

        public IntentBuilder setStream(Uri uri)
        {
            if(!mIntent.getAction().equals("android.intent.action.SEND"))
                mIntent.setAction("android.intent.action.SEND");
            mStreams = null;
            mIntent.putExtra("android.intent.extra.STREAM", uri);
            return this;
        }

        public IntentBuilder setSubject(String s)
        {
            mIntent.putExtra("android.intent.extra.SUBJECT", s);
            return this;
        }

        public IntentBuilder setText(CharSequence charsequence)
        {
            mIntent.putExtra("android.intent.extra.TEXT", charsequence);
            return this;
        }

        public IntentBuilder setType(String s)
        {
            mIntent.setType(s);
            return this;
        }

        public void startChooser()
        {
            mActivity.startActivity(createChooserIntent());
        }

        private Activity mActivity;
        private ArrayList mBccAddresses;
        private ArrayList mCcAddresses;
        private CharSequence mChooserTitle;
        private Intent mIntent;
        private ArrayList mStreams;
        private ArrayList mToAddresses;

        private IntentBuilder(Activity activity)
        {
            mActivity = activity;
            mIntent = (new Intent()).setAction("android.intent.action.SEND");
            mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", activity.getPackageName());
            mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", activity.getComponentName());
            mIntent.addFlags(0x80000);
        }
    }

    static class ShareCompatImplJB extends ShareCompatImplICS
    {

        public String escapeHtml(CharSequence charsequence)
        {
            return ShareCompatJB.escapeHtml(charsequence);
        }

        boolean shouldAddChooserIntent(MenuItem menuitem)
        {
            return false;
        }

        ShareCompatImplJB()
        {
        }
    }

    static class ShareCompatImplICS extends ShareCompatImplBase
    {

        public void configureMenuItem(MenuItem menuitem, IntentBuilder intentbuilder)
        {
            ShareCompatICS.configureMenuItem(menuitem, intentbuilder.getActivity(), intentbuilder.getIntent());
            if(shouldAddChooserIntent(menuitem))
                menuitem.setIntent(intentbuilder.createChooserIntent());
        }

        boolean shouldAddChooserIntent(MenuItem menuitem)
        {
            boolean flag;
            if(!menuitem.hasSubMenu())
                flag = true;
            else
                flag = false;
            return flag;
        }

        ShareCompatImplICS()
        {
        }
    }

    static class ShareCompatImplBase
        implements ShareCompatImpl
    {

        private static void withinStyle(StringBuilder stringbuilder, CharSequence charsequence, int i, int j)
        {
            int k = i;
            while(k < j) 
            {
                char c = charsequence.charAt(k);
                if(c == '<')
                    stringbuilder.append("&lt;");
                else
                if(c == '>')
                    stringbuilder.append("&gt;");
                else
                if(c == '&')
                    stringbuilder.append("&amp;");
                else
                if(c > '~' || c < ' ')
                    stringbuilder.append((new StringBuilder()).append("&#").append(c).append(";").toString());
                else
                if(c == ' ')
                {
                    for(; k + 1 < j && charsequence.charAt(k + 1) == ' '; k++)
                        stringbuilder.append("&nbsp;");

                    stringbuilder.append(' ');
                } else
                {
                    stringbuilder.append(c);
                }
                k++;
            }
        }

        public void configureMenuItem(MenuItem menuitem, IntentBuilder intentbuilder)
        {
            menuitem.setIntent(intentbuilder.createChooserIntent());
        }

        public String escapeHtml(CharSequence charsequence)
        {
            StringBuilder stringbuilder = new StringBuilder();
            withinStyle(stringbuilder, charsequence, 0, charsequence.length());
            return stringbuilder.toString();
        }

        ShareCompatImplBase()
        {
        }
    }

    static interface ShareCompatImpl
    {

        public abstract void configureMenuItem(MenuItem menuitem, IntentBuilder intentbuilder);

        public abstract String escapeHtml(CharSequence charsequence);
    }


    public ShareCompat()
    {
    }

    public static void configureMenuItem(Menu menu, int i, IntentBuilder intentbuilder)
    {
        MenuItem menuitem = menu.findItem(i);
        if(menuitem == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Could not find menu item with id ").append(i).append(" in the supplied menu").toString());
        } else
        {
            configureMenuItem(menuitem, intentbuilder);
            return;
        }
    }

    public static void configureMenuItem(MenuItem menuitem, IntentBuilder intentbuilder)
    {
        IMPL.configureMenuItem(menuitem, intentbuilder);
    }

    public static ComponentName getCallingActivity(Activity activity)
    {
        ComponentName componentname = activity.getCallingActivity();
        if(componentname == null)
            componentname = (ComponentName)activity.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
        return componentname;
    }

    public static String getCallingPackage(Activity activity)
    {
        String s = activity.getCallingPackage();
        if(s == null)
            s = activity.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
        return s;
    }

    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static ShareCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new ShareCompatImplJB();
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new ShareCompatImplICS();
        else
            IMPL = new ShareCompatImplBase();
    }

}
