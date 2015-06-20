package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;

/**
 * Created by nilesh.m on 18/06/15.
 */
public class Views {
    private String username;
    private ObjectId postId;

    public Views(String username, ObjectId postId) {
        this.username = username;
        this.postId = postId;
    }

    public Views() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ObjectId getPostId() {
        return postId;
    }

    public void setPostId(ObjectId postId) {
        this.postId = postId;
    }
}
