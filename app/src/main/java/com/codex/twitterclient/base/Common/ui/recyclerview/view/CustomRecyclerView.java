package com.codex.twitterclient.base.common.ui.recyclerview.view;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import com.codex.twitterclient.R;

/**
 * Created by Yousef on 1/5/2016.
 */
public class CustomRecyclerView extends LinearLayout {
    public RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    public CustomRecyclerView(Context context) {
        super(context);
        initViews();
    }



    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
       // initAttrs(attrs);
        initViews();
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //initAttrs(attrs);
        initViews();
    }
    private void initViews() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycle_view, this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.topten_ListView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);
        mRecyclerView.getItemAnimator().setChangeDuration(500);

    }

    /**
     * set the refresh listener of the recycle view if there
     * @param listener
     */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {

        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setColorSchemeColors(
                R.color.swipeRefreshLayout_1, R.color.swipeRefreshLayout_2, R.color.swipeRefreshLayout_3, R.color.swipeRefreshLayout_4);
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }
    public void setRefreshing(boolean refreshing) {
        if (mSwipeRefreshLayout != null)
            mSwipeRefreshLayout.setRefreshing(refreshing);
    }
    public void setEnableSwipeRefresh(boolean isSwipeRefresh) {
        if (mSwipeRefreshLayout != null)
            mSwipeRefreshLayout.setEnabled(isSwipeRefresh);
    }

}
