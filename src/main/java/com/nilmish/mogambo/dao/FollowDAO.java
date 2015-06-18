package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.entities.Follow;
import com.nilmish.mogambo.entities.Tag;
import com.nilmish.mogambo.entities.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilesh.m on 17/06/15.
 */

@Singleton
public class FollowDAO extends BasicDAO<Follow,ObjectId> {
    @Inject
    public FollowDAO(Datastore ds) {
        super(ds);
    }

    public List<String> getFollowingUsernameList(String username) {
        Query<Follow> query=this.getDatastore().createQuery(Follow.class).field("tagOrUser").equal(1).field("followerId").equal(username);
        List<Follow> followingList=this.find(query).asList();
        List<String> usernameList=new ArrayList<String>();
        for(Follow follow:followingList){
            usernameList.add(follow.getFollowingId());
        }
        return usernameList;
    }

    public List<String> getFollowingTagId(String username) {
        Query<Follow> query=this.getDatastore().createQuery(Follow.class).field("tagOrUser").equal(1).field("followerId").equal(username);
        List<Follow> followingTagList=this.find(query).asList();
        List<String> tagIdList=new ArrayList<String>();
        for(Follow follow:followingTagList){
            tagIdList.add(follow.getFollowingId());
        }
        return tagIdList;
    }
}
