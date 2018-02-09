package com.uludag.can.jibbledevelopertest.models;

public class CombinedData {
    private Post mPost;
    private Album mAlbum;
    private User mUser;

    public CombinedData(Post post, Album album, User user) {
        mPost = post;
        mAlbum = album;
        mUser = user;
    }

    public Post getPost() {
        return mPost;
    }

    public void setPost(Post post) {
        mPost = post;
    }

    public Album getAlbum() {
        return mAlbum;
    }

    public void setAlbum(Album album) {
        mAlbum = album;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
