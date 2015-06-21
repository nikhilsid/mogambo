package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by nilesh.m on 19/06/15.
 */

@Entity(noClassnameStored = true)
public class InverseTagPostMapper {
    @Id
    private ObjectId tagId;
    private List<ObjectId> postIdList;

    public InverseTagPostMapper(ObjectId tagId, List<ObjectId> postIdList) {
        this.tagId = tagId;
        this.postIdList = postIdList;
    }

    public InverseTagPostMapper() {
    }

    public List<ObjectId> getPostIdList() {
        return postIdList;
    }
}
