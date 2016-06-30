package com.codex.twitterclient.base.ServiceBase;

/**
 * Created by YousefEbrahim on 29/05/2016.
 */
public class ResponseBase {
    private String message ;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
