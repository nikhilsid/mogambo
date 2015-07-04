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
    private String parentUsername;
    private String postLocation;
    private Long timestamp;
    private String priceCurrency;
    private String postPrice;
    private String postImageURL;
    private String locationGeoCode;
    private Integer aggregatedVoteCount;
    private List<TagDisagree> tagList;
    // add retweet count

    public UserPost() {
    }

    public UserPost(String username, String parentUsername, String postLocation, Long timestamp, String priceCurrency, String postPrice, String postImageURL, String locationGeoCode, Integer aggregatedVoteCount, List<TagDisagree> tagList) {
        this.username = username;
        this.parentUsername = parentUsername;
        this.postLocation = postLocation;
        this.timestamp = timestamp;
        this.priceCurrency = priceCurrency;
        this.postPrice = postPrice;
        this.postImageURL = postImageURL;
        this.locationGeoCode = locationGeoCode;
        this.aggregatedVoteCount = aggregatedVoteCount;
        this.tagList = tagList;
    }

    public String getPostId() {
        return postId.toString();
    }

    public void setAggregatedVoteCount(Integer aggregatedVoteCount) {
        this.aggregatedVoteCount = aggregatedVoteCount;
    }


    public String getPostLocation() {
        return postLocation;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public String getPostPrice() {
        return postPrice;
    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public String getLocationGeoCode() {
        return locationGeoCode;
    }

    public Integer getAggregatedVoteCount() {
        return aggregatedVoteCount;
    }

    public List<TagDisagree> getTagList() {
        return tagList;
    }
}