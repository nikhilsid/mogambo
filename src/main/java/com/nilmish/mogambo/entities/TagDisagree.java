package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;

/**
 * Created by nilesh.m on 17/06/15.
 */
public class TagDisagree {
    private ObjectId tagId;
    private Integer count;

    public TagDisagree() {
    }

    public TagDisagree(ObjectId tagId, Integer count) {
        this.tagId = tagId;
        this.count = count;
    }

    public ObjectId getTagId() {
        return tagId;
    }

    public void setTagId(ObjectId tagId) {
        this.tagId = tagId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
