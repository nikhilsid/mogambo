package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by nilesh.m on 19/06/15.
 */
public class Following {
    @Id
    private ObjectId id;
    private String username;
    private List<ObjectId> followingUserIdList;
    private List<ObjectId> followingTagIdList;

    public List<ObjectId> getFollowingUserIdList() {
        return followingUserIdList;
    }

    public List<ObjectId> getFollowingTagIdList() {
        return followingTagIdList;
    }

    public String getUsername() {
        return username;
    }

    public Following(String username, List<ObjectId> followingUserIdList, List<ObjectId> followingTagIdList) {
        this.username = username;
        this.followingUserIdList = followingUserIdList;
        this.followingTagIdList = followingTagIdList;
    }

    public Following() {
    }

    public void setFollowingUserIdList(List<ObjectId> followingUserIdList) {
        this.followingUserIdList = followingUserIdList;
    }

    public void setFollowingTagIdList(List<ObjectId> followingTagIdList) {
        this.followingTagIdList = followingTagIdList;
    }
}
