package com.nilmish.mogambo.entities;

import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 17/06/15.
 */
public class Activity {
    @Id
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
