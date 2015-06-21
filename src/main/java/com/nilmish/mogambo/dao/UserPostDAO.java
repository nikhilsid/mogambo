package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObjectBuilder;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.entities.UserPost;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Singleton
public class UserPostDAO extends BasicDAO<UserPost,ObjectId> {
    @Inject
    public UserPostDAO(Datastore ds) {
        super(ds);
    }

    public HashSet<ObjectId> findUserFeed(List<ObjectId> userFollowingIds) {
        HashSet<ObjectId> hashSet=new HashSet<ObjectId>();
        if(userFollowingIds.size()==0){
            return hashSet;
        }
        Query<UserPost> query = this.getDatastore().createQuery(UserPost.class).field("username").in(userFollowingIds);
        List<UserPost> userPostList=this.find(query).asList();
        for(UserPost userPost:userPostList){
            hashSet.add(userPost.getPostId());
        }
        return hashSet;

//        Query<UserPost> tagQuery = this.getDatastore().createQuery(UserPost.class);
//        tagQuery.filter("tagList elem", BasicDBObjectBuilder.start().add("$in",tagFollowingIds).get());
//
//        List<UserPost> userPostList1=this.find(userQuery).asList();
//        List<UserPost> userPostList2=this.find(tagQuery).asList();
//
//        List<UserPost> userPostList=new ArrayList<UserPost>();
//        HashSet<ObjectId> hashSet=new HashSet<ObjectId>();
//        getUniquePost(userPostList1, userPostList, hashSet);
//        getUniquePost(userPostList2, userPostList, hashSet);

    }

    private void getUniquePost(List<UserPost> userPostList1, List<UserPost> userPostList, HashSet<ObjectId> hashSet) {
        for(UserPost userPost:userPostList1){
            if(!hashSet.contains(userPost.getPostId())){
                hashSet.add(userPost.getPostId());
                userPostList.add(userPost);
            }
        }
    }

    public List<UserPost> getPostFromIds(HashSet<ObjectId> userFeedList) {
        List<UserPost> userPostList=new ArrayList<UserPost>();
        if(userFeedList.size()!=0) {
            Query<UserPost> query = this.getDatastore().createQuery(UserPost.class).field("").in(userFeedList);
        }
        return userPostList;
    }
}
