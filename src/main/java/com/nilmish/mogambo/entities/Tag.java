package com.nilmish.mogambo.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Entity(noClassnameStored = true)
public class Tag {
    @Id
    private String tagName;
    private Integer typeId; // 0(adjective) 1(non-adjective)
    private String tagMeaning;
    private Integer requestCount;
    private Integer followedCount;
    private boolean approved;

    public Tag(String tagName, Integer typeId, String tagMeaning) {
        this.tagName = tagName;
        this.typeId = typeId;
        this.tagMeaning = tagMeaning;
        this.requestCount=1;
        this.followedCount=1;
        this.approved=false;
    }

    public Tag() {
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

    public Integer getFollowedCount() {
        return followedCount;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagName='" + tagName + '\'' +
                ", typeId=" + typeId +
                ", tagMeaning='" + tagMeaning + '\'' +
                ", requestCount=" + requestCount +
                ", approved=" + approved +
                '}';
    }

    public void setFollowedCount(Integer followedCount) {
        this.followedCount = followedCount;
    }
}
