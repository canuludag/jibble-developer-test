package com.uludag.can.jibbledevelopertest.ui.home;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;
import com.uludag.can.jibbledevelopertest.networking.ApiService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository  implements HomeActivityContract.Model{

    private static final int FETCH_DATA_LIMIT = 30; // Request only 30 items from each endpoint
    private ApiService mApiService;

    @Inject
    Repository(ApiService apiService) {
        mApiService = apiService;
    }

    @NotNull
    @Override
    public Observable<List<Post>> getPosts() {
        return mApiService.getPosts(FETCH_DATA_LIMIT)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NotNull
    @Override
    public Observable<List<Album>> getAlbums() {
        return mApiService.getAlbums(FETCH_DATA_LIMIT)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NotNull
    @Override
    public Observable<List<User>> getUsers() {
        return mApiService.getUsers(FETCH_DATA_LIMIT)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
