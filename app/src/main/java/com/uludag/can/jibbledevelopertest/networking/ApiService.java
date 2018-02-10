package com.uludag.can.jibbledevelopertest.networking;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    // Endpoint contains 100 result items by default
    @GET("/posts")
    Observable<List<Post>> getPosts(@Query("_limit") int limit);

    // Endpoint contains 10 result items by default
    @GET("/users")
    Observable<List<User>> getUsers(@Query("_limit") int limit);

    // Endpoint contains 100 result items by default
    @GET("/albums")
    Observable<List<Album>> getAlbums(@Query("_limit") int limit);
}
