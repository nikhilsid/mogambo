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
    private Integer requestCount;
    private boolean approved;

    public Tag(String tagName, Integer typeId, String tagMeaning, Integer requestCount, boolean approved) {
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

    public String getTagName() {
        return tagName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTagMeaning() {
        return tagMeaning;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public boolean isApproved() {
        return approved;
    }
}
