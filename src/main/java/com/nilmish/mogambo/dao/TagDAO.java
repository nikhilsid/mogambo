package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.nilmish.mogambo.entities.Tag;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by nilesh.m on 16/06/15.
 */
public class TagDAO extends BasicDAO<Tag,ObjectId> {
    @Inject
    protected TagDAO(Datastore ds) {
        super(ds);
    }
}
