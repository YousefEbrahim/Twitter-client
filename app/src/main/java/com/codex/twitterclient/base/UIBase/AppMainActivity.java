package com.codex.twitterclient.base.uibase;





import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codex.twitterclient.R;
import com.codex.twitterclient.base.common.utilities.ApplicationManager;
import com.rey.material.widget.ProgressView;
import com.rey.material.widget.SnackBar;



/**
 * Created by YousefEbrahim on 15/01/2016.
 */
public abstract class AppMainActivity extends AppCompatActivity {
    public Toolbar mToolbar;
    public ActionBar actionBar;
    private SnackBar mSnackBar;
    RelativeLayout errorLayout;
    FrameLayout app_container;
    private TextView error_text;
    private TextView title_data;

    private LinearLayout main_progress_view;
    private ProgressView progressBarCustom;
    private  TextView main_progress_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_activity);

        ApplicationManager.getInstance().setContext(this);
        onInitialViews();

        if (onGetLayoutID() != 0){
            View view = View.inflate(this, onGetLayoutID(), null);
            app_container.addView(view);
        }

        if (main_progress_view != null){
            main_progress_view.setBackgroundColor(Color.TRANSPARENT);
        }
        stopProgressDialog();
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        onInitialLayout();




    }
    private void onInitialViews(){
        app_container = (FrameLayout) findViewById(R.id.app_container);
        errorLayout = (RelativeLayout) findViewById(R.id.errorLayout);
        mSnackBar = (SnackBar) findViewById(R.id.main_sn);
        error_text = (TextView) findViewById(R.id.error_text);
        main_progress_view = (LinearLayout) findViewById(R.id.main_progress_view);
        progressBarCustom = (ProgressView) findViewById(R.id.progress_bar);
        main_progress_text = (TextView) findViewById(R.id.main_progress_text);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        title_data = (TextView) findViewById(R.id.title_data);
    }
    public void loadFragment(){
        if (getMainCurrentFragment() != null) {
            ApplicationManager.getInstance().initFragment(this,getMainCurrentFragment() ,R.id.app_container );
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public abstract int onGetLayoutID();

    public abstract void onInitialLayout();

    public void onToast(final String text) {

        mSnackBar = getSnackBar();
        if (mSnackBar.getState() == SnackBar.STATE_SHOWN)
            mSnackBar.dismiss();
        else {

            mSnackBar.applyStyle(R.style.Material_Widget_SnackBar_Tablet);
            mSnackBar.text(text);
            mSnackBar.actionText("Close");
            mSnackBar.duration(3000);
            mSnackBar.show();


        }

    }


    public SnackBar getSnackBar() {
        return mSnackBar;
    }

    @Override
    public void onBackPressed() {

        finish();


    }

    public void onSetError(String error) {
        if (error == null) {
            error_text.setText("");
            error_text.setVisibility(View.GONE);
        } else {
            error_text.setVisibility(View.VISIBLE);
            error_text.setText(error);
        }
    }
    public void onSetError(int error) {
        if (error == 0) {
            error_text.setText("");
            error_text.setVisibility(View.GONE);
        } else {
            error_text.setVisibility(View.VISIBLE);
            error_text.setText(getResources().getString(error));
        }
    }


    public void startProgressDialog() {

                if (progressBarCustom != null) {
                    progressBarCustom.start();
                }
                if (main_progress_view != null){
                   main_progress_view.setVisibility(View.VISIBLE);
                }

    }
    public void startProgressDialog(int title) {

        startProgressDialog();
        if (title >0 && main_progress_text != null){
            main_progress_text.setText(getResources().getString(title));
        }

    }

    public void stopProgressDialog() {

                if (progressBarCustom != null) {
                    progressBarCustom.stop();
                }
                if (main_progress_view != null){
                    main_progress_view.setVisibility(View.GONE);
                }

    }


    public abstract AppFragmentMain getMainCurrentFragment();

   public void visibilityToolbar(boolean isVisible ){
       if (isVisible){
           mToolbar.setVisibility(View.VISIBLE);
       }else{
           mToolbar.setVisibility(View.GONE);
       }

   }
    public void setBackArrow(){
        if (actionBar != null) {
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            // upArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(upArrow);

        }

    }
    public void setTitle(String title){
        if (title != null){
            title_data.setText(title);
        }else{
            title_data.setText("");
        }
    }
}
