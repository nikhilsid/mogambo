package com.nilmish.mogambo.feed;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.dao.*;
import com.nilmish.mogambo.entities.Following;
import com.nilmish.mogambo.entities.UserPost;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.List;

/**
 * Created by nilesh.m on 18/06/15.
 */

@Singleton
public class FeedGenerator {
    private UserPostDAO userPostDAO;
    private RelationshipDAO relationshipDAO;
    private FollowingDAO followingDAO;
    private InverseTagPostMapperDAO inverseTagPostMapperDAO;

    @Inject
    public FeedGenerator(UserPostDAO userPostDAO, RelationshipDAO relationshipDAO, FollowingDAO followingDAO, InverseTagPostMapperDAO inverseTagPostMapperDAO) {
        this.userPostDAO = userPostDAO;
        this.relationshipDAO = relationshipDAO;
        this.followingDAO = followingDAO;
        this.inverseTagPostMapperDAO = inverseTagPostMapperDAO;
    }

    public  List<UserPost> generateFeed(String username){
        Following following=followingDAO.getFollowingObject(username);
        List<ObjectId> followingUserIdList=following.getFollowingUserIdList();
        List<ObjectId> followingTagIdList=following.getFollowingTagIdList();

        // from following people
        HashSet<ObjectId> userFeedIdSet=userPostDAO.findUserFeed(followingUserIdList);

        // from following tag
        for(ObjectId tagId:followingTagIdList){
            List<ObjectId> postIdList=inverseTagPostMapperDAO.get(tagId).getPostIdList();
            add(userFeedIdSet,postIdList);
        }

        return userPostDAO.getPostFromIds(userFeedIdSet);
    }

    private void add(HashSet<ObjectId> userFeedList, List<ObjectId> postIdList) {
        for(ObjectId id:postIdList){
            userFeedList.add(id);
        }
    }
}
