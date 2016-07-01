package com.codex.twitterclient.model.login.dtos;

import java.io.Serializable;

/**
 * Created by YousefEbrahim on 01/07/2016.
 */
public class UserData implements Serializable {
    private String token ;
    private String secret ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
