package com.uludag.can.jibbledevelopertest.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uludag.can.jibbledevelopertest.R;
import com.uludag.can.jibbledevelopertest.base.App;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity
        implements HomeActivityContract.View{

    @Inject
    Context mContext;
    @Inject
    HomeActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the injection
        ((App) getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_home);
    }

    @Override
    public void setupRecyclerView() {

    }

    @Override
    public void refreshRecyclerView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void displayProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }
}
