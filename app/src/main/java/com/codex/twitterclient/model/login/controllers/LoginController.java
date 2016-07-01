package com.codex.twitterclient.model.login.controllers;

import com.codex.twitterclient.base.common.utilities.LogUtility;
import com.codex.twitterclient.base.uibase.AppFragmentMain;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.Callback;
/**
 * Created by YousefEbrahim on 30/06/2016.
 */
public class LoginController extends  Callback<TwitterSession> {
    AppFragmentMain appFragmentMain;
    public LoginController(AppFragmentMain appFragmentMain) {
       this.appFragmentMain = appFragmentMain;
    }

    @Override
    public void success(Result<TwitterSession> result) {

    }

    @Override
    public void failure(TwitterException exception) {
        LogUtility.Log("login",exception.getMessage());
    }
}
