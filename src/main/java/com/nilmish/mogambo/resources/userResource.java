package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.dao.RelationshipDAO;
import com.nilmish.mogambo.dao.TagDAO;
import com.nilmish.mogambo.dao.UserDAO;
import com.nilmish.mogambo.dao.UserPostDAO;
import com.nilmish.mogambo.entities.Tag;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.feed.FeedGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nilesh.m on 15/06/15.
 */

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;
    private UserPostDAO userPostDAO;
    private RelationshipDAO relationshipDAO;
    private TagDAO tagDAO;
    private FeedGenerator feedGenerator;

    @Inject
    public UserResource(UserDAO userDAO, UserPostDAO userPostDAO, RelationshipDAO relationshipDAO, TagDAO tagDAO, FeedGenerator feedGenerator) {
        this.userDAO = userDAO;
        this.userPostDAO = userPostDAO;
        this.relationshipDAO = relationshipDAO;
        this.tagDAO = tagDAO;
        this.feedGenerator = feedGenerator;
    }

    @Path("/save")
    @POST
    public boolean saveUser(){
        //userDAO.save(new User("nilmish","nilesh mishra","nilmish.iit@gmail.com","",new Double(2),2,3,false));
        return true;
    }

    @Path("/getAllUser")
    @GET
    public List<User> getAllUser(){
        return userDAO.findAllUsers();
    }

    @Path("/getUser")
    @GET
    public User getUser(@QueryParam("username") String username){
        return userDAO.get(username);
    }

    @Path("/savePost")
    @POST
    public boolean savePost(UserPost userPost){
        userPostDAO.save(userPost);
        return true;
    }

    @Path("/getFeed")
    @GET
    public List<UserPost> getFeed(@QueryParam("username") String username){
        return feedGenerator.generateFeed(username);
    }

    @Path("/getFollowingUser")
    @GET
    public List<String> getFollowingUser(@QueryParam("username") String username){
        return relationshipDAO.getFollowingUsernameList(username);
    }


    @Path("/getFollowingTag")
    @GET
    public List<Tag> getFollowingTags(@QueryParam("username") String username){
        List<String> tagIdList= relationshipDAO.getFollowingTagId(username);
        return tagDAO.getTagsFromIds(tagIdList);
    }

    @Path("/followUser")
    @GET   // username1 follows username2
    public boolean followUser(@QueryParam("username1") String username1, @QueryParam("username2") String username2){

        return true;
    }

    @Path("/unfollowUser")
    @GET  // username1 unfollows username2
    public boolean unfollowUser(@QueryParam("username1") String username1, @QueryParam("username2") String username2){

        return true;
    }
}
