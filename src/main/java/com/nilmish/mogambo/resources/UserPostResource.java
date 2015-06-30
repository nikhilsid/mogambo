package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.UserDAO;
import com.nilmish.mogambo.dao.UserPostDAO;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.utils.GuiceInjector;
import io.dropwizard.auth.Auth;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by nilesh.m on 23/06/15.
 */
@Path("/post")
@Consumes(MediaType.APPLICATION_JSON)
public class UserPostResource {

    @Inject private UserPostDAO userPostDAO;
    @Inject private UserDAO userDAO;

    @Inject
    public UserPostResource(UserPostDAO userPostDAO, UserDAO userDAO) {
        GuiceInjector.getInjector().injectMembers(this);
    }

    @POST
    @Path("/save")
    public boolean savePost(@Auth UserSession userSession,UserPost userPost){
        userPostDAO.save(userPost);
        User user=userDAO.getUserObjectFromUserId(userSession.getUsername());
        user.setPostCount(user.getPostCount()+1);
        return true;
    }

    @POST
    @Path("/upvote")
    public boolean upvotePost(@Auth UserSession userSession, ObjectId objectId){
        userPostDAO.upvotePost(objectId);
        return true;
    }

    @POST
    @Path("/unUpvote")
    public boolean unUpvotePost(@Auth UserSession userSession, ObjectId objectId){
        userPostDAO.unUpvotePost(objectId);
        return true;
    }
}
