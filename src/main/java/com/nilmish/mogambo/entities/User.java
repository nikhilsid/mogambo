package com.nilmish.mogambo.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by nilesh.m on 14/06/15.
 */

@Entity(noClassnameStored = true)
public class User {
    @Id
    private String username;
    private String name;

    @Indexed(name="emailId", unique = true)
    private String emailId;

    private String hashPassword;
    private String userPhotoPath;
    private Double userScore;
    private Integer followersCount;
    private Integer followingCount;
    private Integer tagFollowCount;
    private Integer postCount;
    private boolean userVerified;
    private boolean blocked;

    public User(String username, String name, String emailId, String hashPassword) {
        this.username = username;
        this.name = name;
        this.emailId = emailId;
        this.hashPassword = hashPassword;
    }

    public User() {
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setUserPhotoPath(String userPhotoPath) {
        this.userPhotoPath = userPhotoPath;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public void setTagFollowCount(Integer tagFollowCount) {
        this.tagFollowCount = tagFollowCount;
    }

    public void setUserVerified(boolean userVerified) {
        this.userVerified = userVerified;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", userPhotoPath='" + userPhotoPath + '\'' +
                ", userScore=" + userScore +
                ", followersCount=" + followersCount +
                ", followingCount=" + followingCount +
                ", tagFollowCount=" + tagFollowCount +
                ", postCount=" + postCount +
                ", userVerified=" + userVerified +
                '}';
    }
}