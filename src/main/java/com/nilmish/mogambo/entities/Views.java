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
}
