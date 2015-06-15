package com.nilmish.mogambo.dao;

import com.nilmish.mogambo.entities.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by nilesh.m on 15/06/15.
 */
public class UserDAO extends BasicDAO<User,String> {
    protected UserDAO(Datastore ds) {
        super(ds);
    }
}
