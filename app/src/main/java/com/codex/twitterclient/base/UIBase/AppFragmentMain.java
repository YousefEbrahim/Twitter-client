package com.codex.twitterclient.base.uibase;



import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.codex.twitterclient.base.servicebase.ControllerBase;




/**
 * Created by YousefEbrahim on 15/01/2016.
 */
public abstract class AppFragmentMain extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(onGetLayoutID(),container,false);
        onInitialLayout(view);
        return view;
    }
    public abstract int onGetLayoutID();
    public abstract void onInitialLayout(View view);
    public abstract ControllerBase getControllerView();

}
