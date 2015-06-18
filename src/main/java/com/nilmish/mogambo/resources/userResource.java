package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.dao.FollowDAO;
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
    private FollowDAO followDAO;
    private TagDAO tagDAO;
    private FeedGenerator feedGenerator;

    @Inject
    public UserResource(UserDAO userDAO, UserPostDAO userPostDAO, FollowDAO followDAO, TagDAO tagDAO, FeedGenerator feedGenerator) {
        this.userDAO = userDAO;
        this.userPostDAO = userPostDAO;
        this.followDAO = followDAO;
        this.tagDAO = tagDAO;
        this.feedGenerator = feedGenerator;
    }

    @Path("/save")
    @POST
    public boolean saveUser(User user){
        userDAO.save(user);
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
        return followDAO.getFollowingUsernameList(username);
    }


    @Path("/getFollowingTag")
    @GET
    public List<Tag> getFollowingTags(@QueryParam("username") String username){
        List<String> tagIdList=followDAO.getFollowingTagId(username);
        return tagDAO.getTagsFromIds(tagIdList);
    }

}
