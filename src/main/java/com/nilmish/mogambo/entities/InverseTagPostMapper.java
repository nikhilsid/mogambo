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
    private String tagId;
    private List<String> postIdList;

    public InverseTagPostMapper(String tagId, List<String> postIdList) {
        this.tagId = tagId;
        this.postIdList = postIdList;
    }

    public InverseTagPostMapper() {
    }

    public List<String> getPostIdList() {
        return postIdList;
    }
}
