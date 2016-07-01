package com.codex.twitterclient.base.common.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.codex.twitterclient.base.uibase.AppMainActivity;

import java.util.Locale;

/**
 * Created by YousefEbrahim on 24/06/2016.
 */
public class ApplicationManager {
   static ApplicationManager  applicationManager;
    private Activity context;
    public static ApplicationManager getInstance(){
        if (applicationManager == null){
            applicationManager = new ApplicationManager();
        }
       return applicationManager;
    }
   private ApplicationManager(){

    }
    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    /**
     * change language of application
     * @param context
     * @param local
     */
    public  void changeLocale (Context  context , String local){
        Locale locale=new Locale(local);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        context.getResources().updateConfiguration(configuration,context.getResources().getDisplayMetrics());

    }

    /**
     * load fragment
     * @param activity
     * @param fragment
     * @param container_id
     */
    public  void initFragment(Activity activity, Fragment fragment, int container_id) {
        FragmentTransaction ft = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.add(container_id, fragment);
//        ft.addToBackStack(fragment.getClass().getName());
        ft.commit();
    }

}
