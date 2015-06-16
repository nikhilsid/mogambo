package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.entities.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

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
        for(String username:usernameList){
            userList.add(this.get(username));
        }
        return userList;
    }
}
