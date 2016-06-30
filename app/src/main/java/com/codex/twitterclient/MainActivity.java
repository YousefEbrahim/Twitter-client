package com.codex.twitterclient;


import com.codex.twitterclient.base.UIBase.AppFragmentMain;
import com.codex.twitterclient.base.UIBase.AppMainActivity;

public class MainActivity extends AppMainActivity {

    @Override
    public int onGetLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onInitialLayout() {

    }

    @Override
    public AppFragmentMain getMainCurrentFragment() {
        return null;
    }
}
