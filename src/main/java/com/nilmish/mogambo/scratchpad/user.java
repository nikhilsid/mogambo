package com.nilmish.mogambo.scratchpad;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 27/06/15.
 */

@Entity(noClassnameStored = true)
public class user {
    @Id
    private ObjectId id;
    private String username;

    public user() {
    }

    public user(String username) {
        this.username = username;
    }


    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
