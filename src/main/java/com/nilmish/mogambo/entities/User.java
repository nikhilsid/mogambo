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
    private Integer userScore;

    public User() {
    }

    public User(String username, String name, String emailId, String userPhotoPath, Integer userScore) {
        this.username = username;
        this.name = name;
        this.emailId = emailId;
        this.userPhotoPath = userPhotoPath;
        this.userScore = userScore;
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

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }
}
