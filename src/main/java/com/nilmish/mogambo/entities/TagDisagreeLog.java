package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 18/06/15.
 */
@Entity(noClassnameStored = true)
public class TagDisagreeLog {
    @Id
    private String id; // username+tagname+postId
    private String username;
    private String tagName;
    private ObjectId postId;

}
