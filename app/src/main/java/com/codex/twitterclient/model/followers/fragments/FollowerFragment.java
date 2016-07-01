package com.codex.twitterclient.model.followers.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codex.twitterclient.R;
import com.codex.twitterclient.base.common.ui.recyclerview.interfaces.OnLastItemListener;
import com.codex.twitterclient.base.common.ui.recyclerview.interfaces.OnLoadMoreListener;
import com.codex.twitterclient.base.common.ui.recyclerview.view.CustomRecyclerView;
import com.codex.twitterclient.base.servicebase.ControllerBase;
import com.codex.twitterclient.base.uibase.AppFragmentMain;
import com.codex.twitterclient.model.followers.adapters.FollowersRecyclerAdapter;
import com.codex.twitterclient.model.followers.controllers.FollowersController;
import com.codex.twitterclient.model.followers.dtos.FollowerItem;

import java.util.ArrayList;

/**
 * Created by YousefEbrahim on 01/07/2016.
 */
public class FollowerFragment extends AppFragmentMain {
    FollowersController followersController;
    CustomRecyclerView cusetom_recycler_view;
    private RecyclerView.LayoutManager mLayoutManager;
    private FollowersRecyclerAdapter followersRecyclerAdapter;
    @Override
    public int onGetLayoutID() {
        return R.layout.follower_fragment;
    }

    @Override
    public void onInitialLayout(View view) {
        followersController = new FollowersController(this);
        cusetom_recycler_view= (CustomRecyclerView) view.findViewById(R.id.cusetom_recycler_view);
        onInitializeRecyclerView();
        followersController.sendRequest();
    }

    @Override
    public ControllerBase getControllerView() {
        return followersController;
    }

    private void onInitializeRecyclerView() {
        cusetom_recycler_view.mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        cusetom_recycler_view.mRecyclerView.setLayoutManager(mLayoutManager);
        followersRecyclerAdapter = new FollowersRecyclerAdapter(getActivity(),new ArrayList<FollowerItem>(),cusetom_recycler_view.mRecyclerView);
        cusetom_recycler_view.mRecyclerView.setAdapter(followersRecyclerAdapter);

        cusetom_recycler_view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        followersRecyclerAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {


            }
        });

    }

    public void onRefreshComplete() {
        cusetom_recycler_view.setRefreshing(false);
        followersRecyclerAdapter.isLoaded();
    }
}
