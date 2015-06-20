package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.entities.Relationship;
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
public class RelationshipDAO extends BasicDAO<Relationship,ObjectId> {
    @Inject
    public RelationshipDAO(Datastore ds) {
        super(ds);
    }

    public List<String> getFollowingUsernameList(String username) {
        Query<Relationship> query=this.getDatastore().createQuery(Relationship.class).field("tagOrUser").equal(1).field("followerId").equal(username);
        List<Relationship> followingList=this.find(query).asList();
        List<String> usernameList=new ArrayList<String>();
        for(Relationship relationship :followingList){
            usernameList.add(relationship.getFollowingId());
        }
        return usernameList;
    }

    public List<String> getFollowingTagId(String username) {
        Query<Relationship> query=this.getDatastore().createQuery(Relationship.class).field("tagOrUser").equal(1).field("followerId").equal(username);
        List<Relationship> followingTagList=this.find(query).asList();
        List<String> tagIdList=new ArrayList<String>();
        for(Relationship relationship :followingTagList){
            tagIdList.add(relationship.getFollowingId());
        }
        return tagIdList;
    }
}
