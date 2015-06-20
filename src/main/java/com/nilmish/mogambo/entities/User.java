package com.nilmish.mogambo.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 14/06/15.
 */

@Entity(noClassnameStored = true)
public class User {
    @Id
    private String username;
    private String name;
    private String emailId;
    private String userPhotoPath;
    private Double userScore;
    private Integer followersCount;
    private Integer followingCount;
    private Integer tagFollowCount;
    private Integer postCount;
    private boolean isUserVerified;

    public User(String username, String name, String emailId, String userPhotoPath, Double userScore, Integer followersCount, Integer followingCount, Integer tagFollowCount, Integer postCount, boolean isUserVerified) {
        this.username = username;
        this.name = name;
        this.emailId = emailId;
        this.userPhotoPath = userPhotoPath;
        this.userScore = userScore;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.tagFollowCount = tagFollowCount;
        this.postCount = postCount;
        this.isUserVerified = isUserVerified;
    }

    public User() {
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getTagFollowCount() {
        return tagFollowCount;
    }

    public void setTagFollowCount(Integer tagFollowCount) {
        this.tagFollowCount = tagFollowCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserPhotoPath() {
        return userPhotoPath;
    }

    public void setUserPhotoPath(String userPhotoPath) {
        this.userPhotoPath = userPhotoPath;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public boolean isUserVerified() {
        return isUserVerified;
    }

    public void setUserVerified(boolean isUserVerified) {
        this.isUserVerified = isUserVerified;
    }
}