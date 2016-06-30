package com.codex.twitterclient.base.servicebase;

import android.app.Activity;

import com.codex.twitterclient.base.uibase.AppFragmentMain;
import com.codex.twitterclient.base.uibase.AppMainActivity;


/**
 * Created by YousefEbrahim on 01/06/2016.
 */
public  abstract class ControllerBase implements  OnServiceLisnear {
    AppFragmentMain appFragmentMain = null;
   public Activity context;

    public ControllerBase(AppFragmentMain appFragmentMain){
        this.appFragmentMain = appFragmentMain;
        context = appFragmentMain.getActivity();
    }

    public ControllerBase(Activity context){
      this.context = context;
    }

    public abstract <T> void update(int servesID, T response);
    public abstract <T> void error(int servesID, T error, boolean isHandel);
    @Override
    public <T> void onUpdate(final int servesID, final T response) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((AppMainActivity)context).stopProgressDialog();
                update(servesID,response);
            }
        });

    }

    @Override
    public <T> void onError(final int servesID,final  T error,final  boolean isHandel) {

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((AppMainActivity)context).stopProgressDialog();
                error(servesID,error,isHandel);
            }
        });
    }
}
