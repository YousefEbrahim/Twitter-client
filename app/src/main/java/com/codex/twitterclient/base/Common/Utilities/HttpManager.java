package com.codex.twitterclient.base.common.utilities;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpManager {

    /**
     * checked network state
     * @param activity
     * @return
     */
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
