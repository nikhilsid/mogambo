package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 14/06/15.
 */

@Entity(noClassnameStored = true)
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String name;
    private String emailId;
    private String userPhotoPath;
    private Double userScore;
    private Integer followersCount;
    private Integer followingCount;
    private Integer tagFollowCount;
    private Integer postCount;
    private boolean userVerified;

    public User(String username, String name, String emailId, String userPhotoPath, Double userScore, Integer followersCount, Integer followingCount, Integer tagFollowCount, Integer postCount, boolean UserVerified) {
        this.username = username;
        this.name = name;
        this.emailId = emailId;
        this.userPhotoPath = userPhotoPath;
        this.userScore = userScore;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.tagFollowCount = tagFollowCount;
        this.postCount = postCount;
        this.userVerified = UserVerified;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getUserPhotoPath() {
        return userPhotoPath;
    }

    public Double getUserScore() {
        return userScore;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public Integer getTagFollowCount() {
        return tagFollowCount;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public boolean isUserVerified() {
        return userVerified;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }
}