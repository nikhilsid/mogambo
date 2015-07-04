package com.nilmish.mogambo.responseModel;

/**
 * Created by nilesh.m on 02/07/15.
 */
public class TagResponse {
    private String tagName;
    private String tagMeaning;
    private Integer typeId;

    public TagResponse(String tagName, String tagMeaning, Integer typeId) {
        this.tagName = tagName;
        this.tagMeaning = tagMeaning;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
