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
public class UserDAO extends BasicDAO<User,String> {
    @Inject
    public UserDAO(Datastore ds) {
        super(ds);
    }

    public List<User> findAllUsers() {
        List<User> userList= new ArrayList<User>();
        List<String> usernameList=this.findIds();
        for(String id:usernameList){
            userList.add(this.get(id));
        }
        return userList;
    }

    public User getUserObjectFromUserId(String username) {
        Query<User> query=this.getDatastore().createQuery(User.class).field("username").equal(username);
        return this.findOne(query);
    }

    public Boolean findIfEmailTakenOrNot(String emailId) {
        Query<User> query=this.getDatastore().createQuery(User.class).field("emailId").equal(emailId);
        User user=this.findOne(query);
        if(user==null){
            return true;
        }
        return false;
    }
}
