package com.uludag.can.jibbledevelopertest.base;

import android.app.Application;

import com.uludag.can.jibbledevelopertest.di.AppComponent;
import com.uludag.can.jibbledevelopertest.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger creation
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
