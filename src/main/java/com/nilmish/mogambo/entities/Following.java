package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by nilesh.m on 19/06/15.
 */
public class Following {
    @Id
    private String username;
    private List<String> followingUserList;
    List<ObjectId> followingTagList;

    public Following(String username, List<String> followingUserList, List<ObjectId> followingTagList) {
        this.username = username;
        this.followingUserList = followingUserList;
        this.followingTagList = followingTagList;
    }

    public Following() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFollowingUserList() {
        return followingUserList;
    }

    public void setFollowingUserList(List<String> followingUserList) {
        this.followingUserList = followingUserList;
    }

    public List<ObjectId> getFollowingTagList() {
        return followingTagList;
    }

    public void setFollowingTagList(List<ObjectId> followingTagList) {
        this.followingTagList = followingTagList;
    }
}
