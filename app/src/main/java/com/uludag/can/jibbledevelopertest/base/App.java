package com.uludag.can.jibbledevelopertest.base;

import android.app.Application;

import com.uludag.can.jibbledevelopertest.di.AppComponent;
import com.uludag.can.jibbledevelopertest.di.DaggerAppComponent;
import com.uludag.can.jibbledevelopertest.networking.ApiModule;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger creation
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
