package com.nilmish.mogambo.dao;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nilmish.mogambo.entities.Tag;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Singleton
public class TagDAO extends BasicDAO<Tag,String> {
    private List<Tag> trendyTags;

    @Inject
    protected TagDAO(Datastore ds) {
        super(ds);
    }

    public List<Tag> getTagsFromIds(List<String> tagIdList) {
        List<Tag> tagList= Lists.newArrayList();
        if(tagIdList==null || tagIdList.size()==0){
            return tagList;
        }
        Query<Tag> query=this.getDatastore().createQuery(Tag.class).field("tagId").in(tagIdList);
        return this.find(query).asList();
    }

    public Boolean incrementCount(String followingTag) {
        Tag tag=this.get(followingTag);
        tag.setFollowedCount(tag.getFollowedCount()+1);
        Key<Tag> key=this.save(tag);
        if(key==null || key.getId()==null){
            return false;
        }
        return true;
    }

    public Boolean decrementCount(String unfollowingTag) {
        Tag tag=this.get(unfollowingTag);
        tag.setFollowedCount(tag.getFollowedCount()-1);
        Key<Tag> key=this.save(tag);
        if(key==null || key.getId()==null){
            return false;
        }
        return true;
    }


    public List<Tag> getTrendyTags() {
        Query<Tag> query=this.getDs().createQuery(Tag.class).order("-followedCount").limit(20);
        return this.find(query).asList();
    }
}
