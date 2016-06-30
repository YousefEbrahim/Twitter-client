package com.codex.twitterclient.base.Common.Utilities;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpManager {


    public static boolean isOnline(Context activity) {
        if (activity !=null) {
            ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

	
}
