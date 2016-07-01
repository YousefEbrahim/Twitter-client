package com.codex.twitterclient.model.followers.services;

import com.codex.twitterclient.base.constants.ServiceIDConstance;
import com.codex.twitterclient.base.constants.ServicesUrlsConstance;
import com.codex.twitterclient.base.servicebase.ControllerBase;
import com.codex.twitterclient.base.servicebase.ServicesBase;
import com.codex.twitterclient.model.followers.responses.FollowersResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YousefEbrahim on 01/07/2016.
 */
public class FollowersService extends ServicesBase {
    public FollowersService(ControllerBase controllerBase) {
        super(controllerBase);
    }

    @Override
    public int getServiceId() {
        return ServiceIDConstance.FOLLOWERS_ID;
    }

    @Override
    public <T> T getResponse() {
        return (T) new FollowersResponse();
    }

    @Override
    public String getURL() {
        return ServicesUrlsConstance.FOLLOWERS_URL;
    }

    @Override
    public int TypeOfConnection() {
        return ServiceIDConstance.GET;
    }
    public void sendRequest(){

        Map<String, Object> keysAndValues = new HashMap<>();
        //keysAndValues.put("cursor",);
       // keysAndValues.put("screen_name",);
       // keysAndValues.put("skip_status",);
       // keysAndValues.put("include_user_entities",);

        execute(keysAndValues);
    }
}
