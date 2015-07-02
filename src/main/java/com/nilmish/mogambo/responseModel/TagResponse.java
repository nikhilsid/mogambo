package com.nilmish.mogambo.responseModel;

/**
 * Created by nilesh.m on 02/07/15.
 */
public class TagResponse {
    private String tagName;
    private String tagMeaning;

    public TagResponse(String tagName, String tagMeaning) {
        this.tagName = tagName;
        this.tagMeaning = tagMeaning;
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
}
