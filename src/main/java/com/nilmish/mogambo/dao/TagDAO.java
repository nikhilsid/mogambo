package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.entities.Tag;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Singleton
public class TagDAO extends BasicDAO<Tag,ObjectId> {
    @Inject
    protected TagDAO(Datastore ds) {
        super(ds);
    }

    public Tag getTagObFromTagName(String tagName){
        Query<Tag> query=this.getDatastore().createQuery(Tag.class).field("tagName").equal(tagName);
        return this.findOne(query);
    }

    public List<Tag> getTagsFromIds(List<ObjectId> tagIdList) {
        Query<Tag> query=this.getDatastore().createQuery(Tag.class).field("tagId").in(tagIdList);
        return this.find(query).asList();
    }
}
