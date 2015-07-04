package com.nilmish.mogambo.dao;

import com.google.common.collect.Lists;
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

    public HashSet<String> findUserFeed(List<String> userFollowingIds) {
        HashSet<String> hashSet=new HashSet<String>();
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
//
////    private void getUniquePost(List<UserPost> userPostList1, List<UserPost> userPostList, HashSet<ObjectId> hashSet) {
////        for(UserPost userPost:userPostList1){
////            if(!hashSet.contains(userPost.getPostId())){
////                hashSet.add(userPost.getPostId());
////                userPostList.add(userPost);
////            }
////        }
////    }

    public List<UserPost> getPostFromIds(List<ObjectId> userFeedList) {
        List<UserPost> userPostList= Lists.newArrayList();
        if(userFeedList==null || userFeedList.size()==0) {
            return userPostList;
        }
        Query<UserPost> query = this.getDatastore().createQuery(UserPost.class).field("postId").in(userFeedList);
        userPostList=this.find(query).asList();
        return userPostList;
    }

    public void upvotePost(String id) {
        UserPost userPost=this.get(new ObjectId(id));
        userPost.setAggregatedVoteCount(userPost.getAggregatedVoteCount()+1);
        this.save(userPost);
    }

    public void unUpvotePost(String id) {
        UserPost userPost=this.get(new ObjectId(id));
        userPost.setAggregatedVoteCount(userPost.getAggregatedVoteCount()-1);
        this.save(userPost);
    }

    public List<UserPost> getAllPostsForAUser(String username) {
        System.out.println(username);
        Query<UserPost> query=this.getDatastore().createQuery(UserPost.class).field("username").equal(username);
        return this.find(query).asList();
    }
}
