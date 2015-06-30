package com.nilmish.mogambo.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import java.util.List;

/**
 * Created by nilesh.m on 19/06/15.
 */

@Entity(noClassnameStored = true)
public class Following {
    @Id
    private String username;
    private List<String> followingUsernameList;
    private List<String> followingTagnameList;


    public String getUsername() {
        return username;
    }

    public Following(String username, List<String> followingUsernameList, List<String> followingTagnameList) {
        this.username = username;
        this.followingUsernameList = followingUsernameList;
        this.followingTagnameList = followingTagnameList;
    }

    public Following() {
    }

    public List<String> getFollowingTagnameList() {
        return followingTagnameList;
    }

    public List<String> getFollowingUsernameList() {
        return followingUsernameList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFollowingUsernameList(List<String> followingUsernameList) {
        this.followingUsernameList = followingUsernameList;
    }

    public void setFollowingTagnameList(List<String> followingTagnameList) {
        this.followingTagnameList = followingTagnameList;
    }
}
