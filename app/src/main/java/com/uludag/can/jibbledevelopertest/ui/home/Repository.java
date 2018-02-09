package com.uludag.can.jibbledevelopertest.ui.home;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;
import com.uludag.can.jibbledevelopertest.networking.ApiService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository  implements HomeActivityContract.Model{

    private ApiService mApiService;

    @Inject
    public Repository(ApiService apiService) {
        mApiService = apiService;
    }

    @NotNull
    @Override
    public Observable<List<Post>> fetchPosts() {
        return null;
    }

    @NotNull
    @Override
    public Observable<List<Album>> fetchAlbums() {
        return null;
    }

    @NotNull
    @Override
    public Observable<List<User>> fetchUsers() {
        return null;
    }
}
