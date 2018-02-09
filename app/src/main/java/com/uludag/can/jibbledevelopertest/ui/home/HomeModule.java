package com.uludag.can.jibbledevelopertest.ui.home;

import com.uludag.can.jibbledevelopertest.networking.ApiModule;
import com.uludag.can.jibbledevelopertest.networking.ApiService;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class HomeModule {

    @Provides
    HomeActivityContract.Presenter provideHomePresenter(HomeActivityContract.Model model) {
        return new HomePresenter(model);
    }

    @Provides
    HomeActivityContract.Model provideRepository(ApiService apiService) {
        return new Repository(apiService);
    }
}
