package com.codex.twitterclient.model.followers.activities;

import com.codex.twitterclient.R;
import com.codex.twitterclient.base.uibase.AppFragmentMain;
import com.codex.twitterclient.base.uibase.AppMainActivity;
import com.codex.twitterclient.model.followers.fragments.FollowerFragment;

/**
 * Created by YousefEbrahim on 01/07/2016.
 */
public class FollowersActivity extends AppMainActivity {
    FollowerFragment followerFragment;
    @Override
    public int onGetLayoutID() {
        return 0;
    }

    @Override
    public void onInitialLayout() {
        followerFragment = new FollowerFragment();
        setTitle(getResources().getString(R.string.followers));
        loadFragment();
    }

    @Override
    public AppFragmentMain getMainCurrentFragment() {
        return followerFragment;
    }
}
