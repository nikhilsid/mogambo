package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.nilmish.mogambo.entities.Following;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilesh.m on 19/06/15.
 */
public class FollowingDAO extends BasicDAO<Following,ObjectId> {
    public static final Logger logger = LoggerFactory.getLogger(FollowingDAO.class);
    @Inject
    public FollowingDAO(Datastore ds) {
        super(ds);
    }

    public Following getFollowingObjectId(String username) {
        Query<Following> query=this.getDatastore().createQuery(Following.class).field("username").equal(username);
        return this.findOne(query);
    }

    public void addUserFollower(String username, ObjectId followingId) {
        Query<Following> query=this.getDatastore().createQuery(Following.class).field("username").equal(username);
        Following following=this.findOne(query);
        if(following==null){
            List<ObjectId> followingIdList=new ArrayList<ObjectId>();
            followingIdList.add(followingId);
            List<ObjectId> followingTagList=null;
            following=new Following(username,followingIdList,followingTagList);
        }
        else{
            if(following.getFollowingUserIdList()==null) {
                following.setFollowingUserIdList(new ArrayList<ObjectId>());
            }
            following.getFollowingUserIdList().add(followingId);
        }
        this.save(following);
    }

    public void removeUserFollower(String username, ObjectId unfollowingId) {
        Query<Following> query=this.getDatastore().createQuery(Following.class).field("username").equal(username);
        Following following=this.findOne(query);
        if(following.getFollowingUserIdList()!=null) {
            logger.info("user with objectId: "+unfollowingId+" is removed from user: "+username+" following list");
            following.getFollowingUserIdList().remove(unfollowingId);
            this.save(following);
        }
        else{
            logger.info("user with objectId: "+unfollowingId+" not found in the user: "+username+" following list");
        }
    }

    public void addTagFollower(String username, ObjectId followingTagId) {

    }

    public void removeTagFollower(String username, ObjectId unfollowingTagId) {

    }
}
