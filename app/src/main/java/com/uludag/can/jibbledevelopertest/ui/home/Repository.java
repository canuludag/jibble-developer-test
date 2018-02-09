package com.uludag.can.jibbledevelopertest.ui.home;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;

public class Repository  implements HomeActivityContract.Model{
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
