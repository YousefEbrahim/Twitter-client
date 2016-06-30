package com.codex.twitterclient.base.common.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.codex.twitterclient.base.common.utilities.ApplicationManager;
/**
 * Created by bonga on 03/06/2016.
 */
public class SharedPreferenceUtilities {


    public  String MyPREFERENCES="UserData";
    private static SharedPreferenceUtilities sharedPreferenceUtilities;

  
    public static SharedPreferenceUtilities getInstance(){
        if (sharedPreferenceUtilities == null){
            sharedPreferenceUtilities = new SharedPreferenceUtilities();
        }
        return sharedPreferenceUtilities;
    }
    private SharedPreferenceUtilities(){

    }

    /**
     * put string to shared preference
     * @param name
     * @param value
     */
    public  void setString(String name,String value){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(name, value);
        editor.commit();

    }

    /**
     * get string from shared preference
     * @param name
     * @return
     */
    public  String getString(String name){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       return sharedpreferences.getString(name,"");

    }

    /**
     * remove key from shared preference
     * @param name
     */
    public  void removeKey (String name){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(name);
        editor.commit();

    }

   
}
