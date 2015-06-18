package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by nilesh.m on 16/06/15.
 */
@Entity(noClassnameStored = true)
public class UserPost {
    @Id
    private ObjectId postId;
    private String username;
    private String parentId;
    private String postLocation;
    private Long timestamp;
    private String priceCurrency;
    private String postPrice;
    private String postImageURL;
    private String locationGeoCode;
    private Integer aggregatedVoteCount;
    private List<TagDisagree> tagList;

    public UserPost() {
    }

    public UserPost(String username, String parentId, String postLocation, Long timestamp, String priceCurrency, String postPrice, String postImageURL, String locationGeoCode, Integer aggregatedVoteCount, List<TagDisagree> tagList) {
        this.username = username;
        this.parentId = parentId;
        this.postLocation = postLocation;
        this.timestamp = timestamp;
        this.priceCurrency = priceCurrency;
        this.postPrice = postPrice;
        this.postImageURL = postImageURL;
        this.locationGeoCode = locationGeoCode;
        this.aggregatedVoteCount = aggregatedVoteCount;
        this.tagList = tagList;
    }

    public ObjectId getPostId() {
        return postId;
    }

    public void setPostId(ObjectId postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(String postPrice) {
        this.postPrice = postPrice;
    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public void setPostImageURL(String postImageURL) {
        this.postImageURL = postImageURL;
    }

    public String getLocationGeoCode() {
        return locationGeoCode;
    }

    public void setLocationGeoCode(String locationGeoCode) {
        this.locationGeoCode = locationGeoCode;
    }

    public Integer getAggregatedVoteCount() {
        return aggregatedVoteCount;
    }

    public void setAggregatedVoteCount(Integer aggregatedVoteCount) {
        this.aggregatedVoteCount = aggregatedVoteCount;
    }

    public List<TagDisagree> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagDisagree> tagList) {
        this.tagList = tagList;
    }
}