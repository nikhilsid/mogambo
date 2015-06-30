package com.nilmish.mogambo.feed;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.dao.*;
import com.nilmish.mogambo.entities.Following;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.utils.GuiceInjector;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.List;

/**
 * Created by nilesh.m on 18/06/15.
 */

@Singleton
public class FeedGenerator {
    @Inject private UserPostDAO userPostDAO;
    @Inject private RelationshipDAO relationshipDAO;
    @Inject private FollowingDAO followingDAO;
    @Inject private InverseTagPostMapperDAO inverseTagPostMapperDAO;

    @Inject
    public FeedGenerator() {
        GuiceInjector.getInjector().injectMembers(this);
    }
//
//    public  List<UserPost> generateFeed(String username){
//        Following following=followingDAO.getFollowingObject(username);
//        List<String> followingUserIdList=following.getFollowingUsernameList();
//        List<String> followingTagIdList=following.getFollowingTagnameList();
//
//        // from following people
//        HashSet<String> userFeedIdSet=userPostDAO.findUserFeed(followingUserIdList);
//
//        // from following tag
//        for(String tagId:followingTagIdList){
//            List<String> postIdList=inverseTagPostMapperDAO.get(tagId).getPostIdList();
//            add(userFeedIdSet,postIdList);
//        }
//
//        return userPostDAO.getPostFromIds(userFeedIdSet);
//    }
//
//    private void add(HashSet<String> userFeedList, List<String> postIdList) {
//        for(String id:postIdList){
//            userFeedList.add(id);
//        }
//    }
}
