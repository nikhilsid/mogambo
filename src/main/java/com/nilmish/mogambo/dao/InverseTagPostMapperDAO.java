package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.nilmish.mogambo.entities.InverseTagPostMapper;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by nilesh.m on 19/06/15.
 */
public class InverseTagPostMapperDAO extends BasicDAO<InverseTagPostMapper,ObjectId> {
    @Inject
    public InverseTagPostMapperDAO(Datastore ds) {
        super(ds);
    }
}
