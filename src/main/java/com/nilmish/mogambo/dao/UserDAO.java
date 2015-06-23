package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.entities.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilesh.m on 15/06/15.
 */

@Singleton
public class UserDAO extends BasicDAO<User,ObjectId> {
    @Inject
    public UserDAO(Datastore ds) {
        super(ds);
    }

    public List<User> findAllUsers() {
        List<User> userList= new ArrayList<User>();
        List<ObjectId> usernameList=this.findIds();
        for(ObjectId id:usernameList){
            userList.add(this.get(id));
        }
        return userList;
    }

    public User findUser(String username) {
        Query<User> query=this.getDatastore().createQuery(User.class).field("username").equal(username);
        User user=this.findOne(query);
        System.out.println(user);
        return user;
    }

    public List<User> getUserObjectFromUserIdList(List<ObjectId> followingUserIdList) {
        List<User> usernameList=new ArrayList<User>();
        if(followingUserIdList==null || followingUserIdList.size()==0){
            return usernameList;
        }
        Query<User> query=this.getDatastore().createQuery(User.class).field("id").in(followingUserIdList);
        List<User> userList=this.find(query).asList();
        return userList;
    }

    public User getUserObjectFromUserId(String username) {
        Query<User> query=this.getDatastore().createQuery(User.class).field("username").equal(username);
        return this.findOne(query);
    }
}
