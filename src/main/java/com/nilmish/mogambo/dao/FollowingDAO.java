package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.nilmish.mogambo.entities.Following;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by nilesh.m on 19/06/15.
 */
public class FollowingDAO extends BasicDAO<Following,String> {
    @Inject
    public FollowingDAO(Datastore ds) {
        super(ds);
    }
}
