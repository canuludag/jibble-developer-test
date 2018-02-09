package com.uludag.can.jibbledevelopertest.networking;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/posts")
    Observable<List<Post>> getPosts();

    @GET("/users")
    Observable<List<User>> getUsers();

    @GET("/albums")
    Observable<List<Album>> getAlbums();
}
