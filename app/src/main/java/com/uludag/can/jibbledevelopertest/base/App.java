package com.uludag.can.jibbledevelopertest.base;

import android.app.Application;

import com.uludag.can.jibbledevelopertest.di.AppComponent;
import com.uludag.can.jibbledevelopertest.di.DaggerAppComponent;
import com.uludag.can.jibbledevelopertest.networking.ApiModule;
import com.uludag.can.jibbledevelopertest.ui.home.HomeModule;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger creation
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .homeModule(new HomeModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
