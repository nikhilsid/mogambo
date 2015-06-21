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
    private ObjectId followerId;
    private ObjectId followingId;
    private Integer tagOrUser; // 0 for tag, 1 for user
    private Long timestamp;

    public Relationship(ObjectId followerId, ObjectId followingId, Integer tagOrUser, Long timestamp) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.tagOrUser = tagOrUser;
        this.timestamp = timestamp;
    }

    public Relationship() {
    }


    public ObjectId getFollowerId() {
        return followerId;
    }

    public ObjectId getFollowingId() {
        return followingId;
    }

    public Integer getTagOrUser() {
        return tagOrUser;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}

