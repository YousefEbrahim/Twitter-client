package com.codex.twitterclient.model.login.fragments;

import android.content.Intent;
import android.view.View;

import com.codex.twitterclient.R;
import com.codex.twitterclient.base.common.utilities.ApplicationManager;
import com.codex.twitterclient.base.servicebase.ControllerBase;
import com.codex.twitterclient.base.uibase.AppFragmentMain;
import com.codex.twitterclient.model.followers.activities.FollowersActivity;
import com.codex.twitterclient.model.login.controllers.LoginController;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by YousefEbrahim on 30/06/2016.
 */
public class LoginFragment extends AppFragmentMain {
    TwitterLoginButton loginButton;
    LoginController loginController;
    @Override
    public int onGetLayoutID() {
        return R.layout.login_fragment;
    }

    @Override
    public void onInitialLayout(View view) {
        loginController = new LoginController(this);
        loginButton = (TwitterLoginButton) view.findViewById(R.id.login_button);
        loginButton.setCallback(loginController);
    }

    @Override
    public ControllerBase getControllerView() {
        return null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(getActivity(), FollowersActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
