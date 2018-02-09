package com.uludag.can.jibbledevelopertest.ui.home;

import android.content.Context;

import com.uludag.can.jibbledevelopertest.base.AppModule;
import com.uludag.can.jibbledevelopertest.networking.ApiModule;
import com.uludag.can.jibbledevelopertest.networking.ApiService;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class, AppModule.class})
public class HomeModule {

    @Provides
    HomeActivityContract.Presenter provideHomePresenter(HomeActivityContract.Model model, Context context) {
        return new HomePresenter(model, context);
    }

    @Provides
    HomeActivityContract.Model provideRepository(ApiService apiService) {
        return new Repository(apiService);
    }
}
