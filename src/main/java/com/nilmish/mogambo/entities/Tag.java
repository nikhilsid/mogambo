package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Entity(noClassnameStored = true)
public class Tag {
    @Id
    private ObjectId tagId;
    private String tagName;
    private Integer typeId; // 0(adjective) 1(non-adjective)
    private String tagMeaning;
    private String requestCount;
    private boolean approved;

    public Tag(String tagName, Integer typeId, String tagMeaning, String requestCount, boolean approved) {
        this.tagName = tagName;
        this.typeId = typeId;
        this.tagMeaning = tagMeaning;
        this.requestCount = requestCount;
        this.approved = approved;
    }

    public Tag() {
    }

    public ObjectId getTagId() {
        return tagId;
    }

    public void setTagId(ObjectId tagId) {
        this.tagId = tagId;
    }

    public String getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(String requestCount) {
        this.requestCount = requestCount;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagMeaning() {
        return tagMeaning;
    }

    public void setTagMeaning(String tagMeaning) {
        this.tagMeaning = tagMeaning;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
