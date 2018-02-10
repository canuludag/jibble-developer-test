package com.uludag.can.jibbledevelopertest.base;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App mApp;

    AppModule(App app) {
        mApp = app;
    }

    @Singleton
    @Provides
    Context provideContext(){
        return mApp.getApplicationContext();
    }
}
