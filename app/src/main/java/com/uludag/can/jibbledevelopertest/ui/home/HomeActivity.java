package com.uludag.can.jibbledevelopertest.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uludag.can.jibbledevelopertest.R;

public class HomeActivity extends AppCompatActivity
        implements HomeActivityContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
