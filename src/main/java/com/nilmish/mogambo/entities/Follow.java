package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 16/06/15.
 */
public class Follow {
    @Id
    private ObjectId id;
    private String followerId;
    private String followingId;
    private Integer tagOrUser; // 0 for tag, 1 for user
    private Long timestamp;

    public Follow(String followerId, String followingId, Integer tagOrUser, Long timestamp) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.tagOrUser = tagOrUser;
        this.timestamp = timestamp;
    }

    public Follow() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTagOrUser() {
        return tagOrUser;
    }

    public void setTagOrUser(Integer tagOrUser) {
        this.tagOrUser = tagOrUser;
    }
}

