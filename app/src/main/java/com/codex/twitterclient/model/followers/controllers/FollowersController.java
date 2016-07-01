package com.codex.twitterclient.model.followers.controllers;

import com.codex.twitterclient.base.servicebase.ControllerBase;
import com.codex.twitterclient.base.uibase.AppFragmentMain;
import com.codex.twitterclient.model.followers.services.FollowersService;

/**
 * Created by YousefEbrahim on 01/07/2016.
 */
public class FollowersController extends ControllerBase {
    FollowersService  followersService;
    public FollowersController(AppFragmentMain appFragmentMain) {
        super(appFragmentMain);
        followersService = new FollowersService(this);
    }

    public void  sendRequest(){
        followersService.sendRequest();
    }


    @Override
    public <T> void update(int servesID, T response) {

    }

    @Override
    public <T> void error(int servesID, T error, boolean isHandel) {

    }
}
