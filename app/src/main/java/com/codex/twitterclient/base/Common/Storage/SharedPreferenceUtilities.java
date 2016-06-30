package com.codex.twitterclient.base.Common.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.codex.twitterclient.base.Common.Utilities.ApplicationManager;
/**
 * Created by bonga on 03/06/2016.
 */
public class SharedPreferenceUtilities {

//    sharedPreference Constant
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
    public  void setString(String name,String value){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(name, value);
        editor.commit();

    }

    public  String getString(String name){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

       return sharedpreferences.getString(name,"");

    }


    public  void setInt(String name,int value){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public  int getInt(String name){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        return sharedpreferences.getInt(name,0);

    }

    public  void removeKey (String name){
        SharedPreferences sharedpreferences = ApplicationManager.getInstance().getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(name);
        editor.commit();

    }

   
}
