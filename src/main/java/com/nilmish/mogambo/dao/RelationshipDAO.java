package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.entities.Relationship;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
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


    // followingId could be either tag or username
    public void follow(String followerUsername, String followingId,int tagOrUser) {
        Query<Relationship> query=this.getDatastore().createQuery(Relationship.class).field("followerUsername").equal(followerUsername).field("followingId").equal(followingId);
        if(this.findOne(query)==null) {
            this.save(new Relationship(followerUsername, followingId, tagOrUser, DateTime.now().getMillis()));
        }
    }

    public void unfollow(String followerUsername, String unfollowerId) {
        Query<Relationship> query=this.getDatastore().createQuery(Relationship.class).field("followerUsername").equal(followerUsername).field("followingId").equal(unfollowerId);
        Relationship relationship = this.findOne(query);
        if(relationship!=null) {
            this.delete(relationship);
        }
    }
}
