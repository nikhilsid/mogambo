package com.nilmish.mogambo.resources;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.UserDAO;
import com.nilmish.mogambo.dao.UserPostDAO;
import com.nilmish.mogambo.entities.TagDisagree;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.utils.GuiceInjector;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import io.dropwizard.auth.Auth;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nilesh.m on 23/06/15.
 */
@Path("/post")
@Api("/post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserPostResource {

    @Inject private UserPostDAO userPostDAO;
    @Inject private UserDAO userDAO;

    @Inject
    public UserPostResource(UserPostDAO userPostDAO, UserDAO userDAO) {
        GuiceInjector.getInjector().injectMembers(this);
    }

    @Path("/save")
    @ApiOperation(value = "Saves the New Post", notes = "This API is used to save the user post",
            response = Boolean.class)
    @POST
    public Boolean savePost(@Auth UserSession userSession){
        List<TagDisagree> list= Lists.newArrayList();
        UserPost userPost=new UserPost("nilmish","nilmish","",new DateTime().getMillis(),"","","","",0,list);
        userPostDAO.save(userPost);
        User user=userDAO.getUserObjectFromUserId(userSession.getUsername());
        user.setPostCount(user.getPostCount()+1);
        return true;
    }

    @Path("/upvote")
    @ApiOperation(value = "Upvote a post", notes = "This API is used to upvote a post",
            response = Boolean.class)
    @POST
    public Boolean upvotePost(@Auth UserSession userSession, String id){
        userPostDAO.upvotePost(id);
        return true;
    }

    @Path("/unUpvote")
    @ApiOperation(value = "Unupvote a post", notes = "This API is used to Unupvote a post",
            response = Boolean.class)
    @POST
    public Boolean unUpvotePost(@Auth UserSession userSession, String id){
        userPostDAO.unUpvotePost(id);
        return true;
    }

    @Path("/getAllPosts")
    @ApiOperation(value = "Unupvote a post", notes = "This API is used to Unupvote a post",
            response = Boolean.class)
    @GET
    public List<UserPost> getAllUserPosts(@Auth UserSession userSession){
        return userPostDAO.getAllPostsForAUser(userSession.getUsername());
    }

    @Path("/getPost")
    @ApiOperation(value = "API is used to get specific post", notes = "This API is used to " +
            "get specific post -- given postId of the userPOST", response = UserPost.class)
    @GET
    public UserPost getSpecificPost(@Auth UserSession userSession, @QueryParam("postId") String postId){
        ObjectId id=new ObjectId(postId);
        return userPostDAO.get(id);
    }
}
