package com.codex.twitterclient.model.login.activities;


import android.content.Intent;

import com.codex.twitterclient.R;
import com.codex.twitterclient.base.uibase.AppFragmentMain;
import com.codex.twitterclient.base.uibase.AppMainActivity;
import com.codex.twitterclient.model.login.fragments.LoginFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppMainActivity {

    private static final String TWITTER_KEY = "	XCF7X0n5jcKQxYeGtyzkIZGbk";
    private static final String TWITTER_SECRET = "uNQwYFOB1vth4DYXT9FBdb1CmYt7tcsXJJfdZBG83s2henJGbQ";
    LoginFragment   loginFragment;
    @Override
    public int onGetLayoutID() {
        return 0;
    }

    @Override
    public void onInitialLayout() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setTitle(getResources().getString(R.string.login));
        loginFragment = new LoginFragment();
        loadFragment();
    }

    @Override
    public AppFragmentMain getMainCurrentFragment() {
        return loginFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }
}
