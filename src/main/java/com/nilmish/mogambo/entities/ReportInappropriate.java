package com.nilmish.mogambo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.HashSet;

/**
 * Created by nilesh.m on 27/06/15.
 */

@Entity(noClassnameStored = true)
public class ReportInappropriate {
    @Id
    private ObjectId postId;
    private HashSet<String> usernameSet;

    public ReportInappropriate() {
    }

    public ReportInappropriate(ObjectId postId) {
        this.postId = postId;
    }

    public ObjectId getPostId() {
        return postId;
    }

    public HashSet<String> getUsernameSet() {
        return usernameSet;
    }

    public void setUsernameSet(HashSet<String> usernameSet) {
        this.usernameSet = usernameSet;
    }
}
