package com.uludag.can.jibbledevelopertest.networking;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/posts")
    Observable<List<Post>> getPosts(@Query("_limit") int limit);

    @GET("/users")
    Observable<List<User>> getUsers(@Query("_limit") int limit);

    @GET("/albums")
    Observable<List<Album>> getAlbums(@Query("_limit") int limit);
}
