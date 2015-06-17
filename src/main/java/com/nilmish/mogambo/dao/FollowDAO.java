package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.nilmish.mogambo.entities.Follow;
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
public class FollowDAO extends BasicDAO<Follow,ObjectId> {
    @Inject
    public FollowDAO(Datastore ds) {
        super(ds);
    }

    public List<String> getFollowingUsernameList(String username) {
        Query<Follow> query=this.getDatastore().createQuery(Follow.class).field("followerId").equal(username);
        List<Follow> followingList=this.find(query).asList();
        List<String> usernameList=new ArrayList<String>();
        for(Follow follow:followingList){
            usernameList.add(follow.getFollowingId());
        }
        return usernameList;
    }
}
