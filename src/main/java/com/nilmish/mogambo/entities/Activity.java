package com.nilmish.mogambo.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 17/06/15.
 */

@Entity(noClassnameStored = true)
public class Activity {
    @Id
    private String id; // username+postId
    private String username;
    private String postId;
    private Long timestamp;
    private Integer votes;

    public Activity() {
    }

    public Activity(String id, String username, String postId, Long timestamp, Integer votes) {
        this.id = id;
        this.username = username;
        this.postId = postId;
        this.timestamp = timestamp;
        this.votes = votes;
    }
}
