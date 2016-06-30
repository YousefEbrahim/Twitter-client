package com.codex.twitterclient.base.servicebase;

import android.text.TextUtils;

import com.codex.twitterclient.base.common.utilities.LogUtility;
import com.codex.twitterclient.base.constants.ExceptionConstance;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by YousefEbrahim on 29/05/2016.
 */
public abstract class ServicesBase {
    protected ControllerBase controllerBase;
    private RequestBase requestBase;

    public abstract int getServiceId();

   public abstract <T> T getResponse();
    public abstract String getURL();
    public abstract int TypeOfConnection();

    public ServicesBase(ControllerBase controllerBase) {
        this.controllerBase = controllerBase;
        requestBase = new RequestBase(this);
    }

    protected void execute(Map<String, Object> keysAndValues) {
        requestBase.urlData.setKeysAndValues(keysAndValues);
        onRunRequest();
    }

    private void onRunRequest() {
        OkHttpClient client = new OkHttpClient();
        ConfigureTimeoutsOkkTtp(client);
        Request.Builder request = new Request.Builder();
        if (requestBase.urlData.isPost()) {
            RequestBody requestBody = requestBase.PostRequest();
            request.url(requestBase.urlData.getUrl());
            request.post(requestBody);
        } else {
            String url = requestBase.GetRequest();
            if (!TextUtils.isEmpty(url)) {
                LogUtility.Log("Response  :",(requestBase.urlData.getUrl() + "?" + url));
                request.url((requestBase.urlData.getUrl() + "?" + url));
            } else {
                LogUtility.Log("Response  :",requestBase.urlData.getUrl() );
                request.url(requestBase.urlData.getUrl());
            }
        }
        Request req = request.build();

        client.newCall(req).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                final IOException eData = e;
                if (controllerBase != null) {
                    LogUtility.Log("onFailure :" + requestBase.urlData.getService_id(), eData.getMessage());
                    controllerBase.onError(requestBase.urlData.getService_id(), new ExceptionBase(ExceptionConstance.SomethingWentWrong), false);
                }

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    LogUtility.Log("Response fail :", requestBase.urlData.getService_id() + "---->" + response);
                   // final Response responseData = response;
                    if (controllerBase != null) {
                        controllerBase.onError(requestBase.urlData.getService_id(),new ExceptionBase(ExceptionConstance.SomethingWentWrong), false);
                    }

                } else {
                    final String resp = response.body().string();
                    LogUtility.Log("Response  :", requestBase.urlData.getService_id() + "---->" + resp);
                    if (controllerBase != null) {

                        try {
                            if (getResponse() != null) {
                                Gson gson = new Gson();
                                if (getResponse() instanceof ResponseBase) {

                                    ResponseBase responseBase = (ResponseBase) gson.fromJson(resp, getResponse().getClass());
                                    if ( responseBase.getCode() == ExceptionConstance.codeSuccessful){
                                        controllerBase.onUpdate(requestBase.urlData.getService_id(), responseBase);
                                    }else{
                                        controllerBase.onError(requestBase.urlData.getService_id(), new ExceptionBase(responseBase.getCode()), false);

                                    }
                                }else {
                                    controllerBase.onUpdate(requestBase.urlData.getService_id(), gson.fromJson(resp, getResponse().getClass()));
                                }

                            } else {
                                controllerBase.onUpdate(requestBase.urlData.getService_id(), resp);

                            }

                        } catch (Exception ex) {

                            LogUtility.Log("Response Exception:", requestBase.urlData.getUrl() + "---->" + ex);
                            controllerBase.onError(requestBase.urlData.getService_id(), new ExceptionBase(ExceptionConstance.SomethingWentWrong), false);

                        }


                    } else {

                        LogUtility.Log("Response OnServes=null:", requestBase.urlData.getUrl() + "---->" + resp);
                    }

                }
            }
        });
    }

    private void ConfigureTimeoutsOkkTtp(OkHttpClient client) {
        try {
            client.setConnectTimeout(100, TimeUnit.SECONDS);
            client.setWriteTimeout(1000, TimeUnit.SECONDS);
            client.setReadTimeout(1000, TimeUnit.SECONDS);
        } catch (Exception e) {
            // System.out.println("Resp : time our " + e);
        }
    }

}
