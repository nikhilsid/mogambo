package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Entity(noClassnameStored = true)
public class Relationship {
    @Id
    private ObjectId id;
    private String followerUsername;
    private String followingId;
    private Integer tagOrUser; // 0 for tag, 1 for user
    private Long timestamp;

    public Relationship(String followerUsername, String followingId, Integer tagOrUser, Long timestamp) {
        this.followerUsername = followerUsername;
        this.followingId = followingId;
        this.tagOrUser = tagOrUser;
        this.timestamp = timestamp;
    }

    public Relationship() {
    }



    public Integer getTagOrUser() {
        return tagOrUser;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public ObjectId getId() {
        return id;
    }

    public String getFollowerUsername() {
        return followerUsername;
    }

    public String getFollowingId() {
        return followingId;
    }
}

