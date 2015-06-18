package com.nilmish.mogambo.entities;

/**
 * Created by nilesh.m on 17/06/15.
 */
public class TagDisagree {
    private String tagName;
    private Integer count;

    public TagDisagree() {
    }

    public TagDisagree(String tagName, Integer count) {
        this.tagName = tagName;
        this.count = count;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
