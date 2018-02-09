package com.uludag.can.jibbledevelopertest.di;

import com.uludag.can.jibbledevelopertest.base.AppModule;
import com.uludag.can.jibbledevelopertest.networking.ApiModule;
import com.uludag.can.jibbledevelopertest.ui.home.HomeActivity;
import com.uludag.can.jibbledevelopertest.ui.home.HomeModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules =
        {AppModule.class, ApiModule.class, HomeModule.class})
public interface AppComponent {
    void inject(HomeActivity target);
}
