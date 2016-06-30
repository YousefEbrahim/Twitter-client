package com.codex.twitterclient.base.common.utilities;

import android.util.Log;

/**
 * Created by YousefEbrahim on 01/06/2016.
 */
public class LogUtility {
    private static final boolean logData = true ;

    public static void Log(String TAG , String message){
        if (logData == true) {
            Log.d(TAG,message);
        }
    }
    public static void Print(Object message){
        if (logData == true) {
           System.out.println(message);
        }
    }

}
