// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.search;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.SCISearchable;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.search:
//            SearchController

public class SearchBarView extends RelativeLayout
    implements android.widget.TextView.OnEditorActionListener, TextWatcher, SearchController.SearchListener
{

    public SearchBarView(Context context)
    {
        super(context);
        init(context);
    }

    public SearchBarView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init(context);
    }

    public SearchBarView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init(context);
    }

    private void searchClicked()
    {
        String s = searchText.getText().toString();
        if(StringUtils.isNotEmptyOrNull(s))
        {
            clearFocus();
            controller.setSearchTerm(s);
        }
    }

    private void updateText(String s)
    {
        if(!searchText.getText().toString().equals(s))
        {
            searchText.setText(s);
            searchText.selectAll();
        }
        String s1 = getResources().getString(0x7f0c005b);
        if(controller != null && controller.isRestrictedSearchModeEnabled())
        {
            SCISearchable scisearchable = controller.getCurrentSearchable();
            if(scisearchable != null)
                s1 = scisearchable.getPresentationTextForSearch();
        }
        searchHintText.setText(s1);
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public void clearSearchFocus()
    {
        searchText.clearFocus();
    }

    public int getCurrentCategoryIndex()
    {
        return -1;
    }

    public String getSearchText()
    {
        return searchText.getText().toString();
    }

    public void init(Context context)
    {
        LayoutInflater.from(context).inflate(0x7f030069, this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        searchText = (EditText)findViewById(0x7f0a0164);
        searchHintText = (TextView)findViewById(0x7f0a0163);
        searchText.setOnEditorActionListener(this);
        searchText.addTextChangedListener(this);
        searchText.onEditorAction(3);
        searchText.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                return false;
            }

            final SearchBarView this$0;

            
            {
                this$0 = SearchBarView.this;
                super();
            }
        }
);
        setGravity(16);
        clearableButton = (ImageView)findViewById(0x7f0a0165);
        clearableButton.setImageState(STATE_EMPTY, true);
        clearableButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(StringUtils.isNotEmptyOrNull(searchText.getText()))
                {
                    searchText.setText("");
                    clearableButton.setVisibility(8);
                }
                searchText.requestFocus();
            }

            final SearchBarView this$0;

            
            {
                this$0 = SearchBarView.this;
                super();
            }
        }
);
        clearableButton.setVisibility(8);
        updateText("");
    }

    public void onCategoriesChanged(ArrayList arraylist, int i)
    {
        updateText(controller.getSearchTermForCategory(i));
    }

    public void onCurrentCategoryChanged(ArrayList arraylist, int i)
    {
        updateText(controller.getSearchTermForCategory(i));
    }

    public boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 3)
        {
            searchClicked();
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void onSearchInputFocused(View view)
    {
    }

    public void onSearchTermChanged()
    {
    }

    public void onServiceOrderChanged()
    {
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        ImageView imageview = clearableButton;
        int ai[];
        TextView textview;
        int l;
        if(StringUtils.isEmptyOrNull(charsequence))
            ai = STATE_EMPTY;
        else
            ai = null;
        imageview.setImageState(ai, true);
        if(controller != null)
        {
            String s = charsequence.toString();
            if(s != null && s.length() > getResources().getInteger(0x7f0b0009))
                s = s.substring(0, getResources().getInteger(0x7f0b0009));
            controller.setSearchTerm(s);
        }
        if(StringUtils.isEmptyOrNull(charsequence))
            clearableButton.setVisibility(4);
        else
            clearableButton.setVisibility(0);
        textview = searchHintText;
        if(StringUtils.isEmptyOrNull(charsequence))
            l = 0;
        else
            l = 8;
        textview.setVisibility(l);
    }

    public boolean requestSearchFocus()
    {
        return searchText.requestFocus();
    }

    public void setController(SearchController searchcontroller)
    {
        controller = searchcontroller;
        searchcontroller.addListener(this);
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        searchText.setEnabled(flag);
    }

    public void setSearchText(String s)
    {
        searchText.setText(s);
    }

    public static final String LOG_TAG = com/sonos/acr/search/SearchBarView.getSimpleName();
    public static final int STATE_EMPTY[];
    protected ImageView clearableButton;
    protected SearchController controller;
    protected TextView searchHintText;
    protected EditText searchText;

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x10100a9;
        STATE_EMPTY = ai;
    }
}
