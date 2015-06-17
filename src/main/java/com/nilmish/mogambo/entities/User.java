package com.nilmish.mogambo.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by nilesh.m on 14/06/15.
 */

@Entity(noClassnameStored = true)
public class User {
    @Id
    private String username;
    private String name;
    private String emailId;
    private String userPhotoPath;
    private Double userScore;
    private Integer followersCount;
    private Integer postCount;
    private boolean isUserVerified;


}