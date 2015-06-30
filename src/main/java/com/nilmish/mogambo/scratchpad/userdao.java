package com.nilmish.mogambo.scratchpad;

import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by nilesh.m on 27/06/15.
 */
public class userdao extends BasicDAO<user,ObjectId>{

    @Inject
    public userdao(Datastore ds) {
        super(ds);
    }
}
