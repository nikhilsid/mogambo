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
    private Integer typeId; // 0(adjective) 1(non-adjective)
    private String tagName;
    private String tagMeaning;
    private boolean approved;

    public Tag(Integer typeId, String tagName, String tagMeaning, boolean approved) {
        this.typeId = typeId;
        this.tagName = tagName;
        this.tagMeaning = tagMeaning;
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
