package com.nilmish.mogambo.feed;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.dao.FollowDAO;
import com.nilmish.mogambo.dao.UserPostDAO;
import com.nilmish.mogambo.entities.UserPost;

import java.util.List;

/**
 * Created by nilesh.m on 18/06/15.
 */

@Singleton
public class FeedGenerator {
    private UserPostDAO userPostDAO;
    private FollowDAO followDAO;

    @Inject
    public FeedGenerator(UserPostDAO userPostDAO) {
        this.userPostDAO = userPostDAO;
    }

    public  List<UserPost> generateFeed(String username){
        List<String> followingTagId=followDAO.getFollowingTagId(username);
        List<String> followingUsernameList=followDAO.getFollowingUsernameList(username);
        return userPostDAO.findFeed(followingUsernameList,followingTagId);
    }
}
