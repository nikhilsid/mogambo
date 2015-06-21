package com.nilmish.mogambo.resources;

import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.*;
import com.nilmish.mogambo.entities.User;
import com.nilmish.mogambo.entities.UserPost;
import com.nilmish.mogambo.feed.FeedGenerator;
import io.dropwizard.auth.Auth;
import org.bson.types.ObjectId;

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
    private FollowingDAO followingDAO;
    private TagDAO tagDAO;
    private FeedGenerator feedGenerator;

    @Inject
    public UserResource(UserDAO userDAO, UserPostDAO userPostDAO, RelationshipDAO relationshipDAO, FollowingDAO followingDAO, TagDAO tagDAO, FeedGenerator feedGenerator) {
        this.userDAO = userDAO;
        this.userPostDAO = userPostDAO;
        this.relationshipDAO = relationshipDAO;
        this.followingDAO = followingDAO;
        this.tagDAO = tagDAO;
        this.feedGenerator = feedGenerator;
    }

    @Path("/save")
    @POST
    public boolean saveUser(@Auth UserSession userSession,User user){
        userDAO.save(user);
        return true;
    }

    @Path("/getAllUser")
    @GET
    public List<User> getAllUser(@Auth UserSession userSession){
        return userDAO.findAllUsers();
    }

    @Path("/getUser")
    @GET
    public User getUser(@Auth UserSession userSession){
        String username=userSession.getUsername();
        return userDAO.findUser(username);
    }

    @Path("/savePost")
    @POST
    public boolean savePost(@Auth UserSession userSession, UserPost userPost){
        userPostDAO.save(userPost);
        return true;
    }

    @Path("/getFeed")
    @GET
    public List<UserPost> getFeed(@Auth UserSession userSession){
        String username=userSession.getUsername();
        return feedGenerator.generateFeed(username);
    }

    @Path("/getFollowingUser")
    @GET
    public List<String> getFollowingUser(@Auth UserSession userSession){
        String username=userSession.getUsername();
        List<ObjectId> followingUserIdList=followingDAO.getFollowingObjectId(username).getFollowingUserIdList();
        return userDAO.getUsernameFromUserId(followingUserIdList);
    }


//    @Path("/getFollowingTag")
//    @GET
//    public List<Tag> getFollowingTags(@Auth UserSession userSession){
//        String username=userSession.getUsername();
//        List<ObjectId> tagIdList= relationshipDAO.getFollowingTagId(username);
//        return tagDAO.getTagsFromIds(tagIdList);
//    }

    @Path("/followUser")
    @GET   // username follows followUsername
    public boolean followUser(@Auth UserSession userSession,@QueryParam("followUsername") String followUsername){
        String username=userSession.getUsername();
        ObjectId followerId=userDAO.findUser(username).getId();
        ObjectId followingId=userDAO.findUser(followUsername).getId();
        relationshipDAO.followUser(followerId, followingId);
        followingDAO.addUserFollower(username, followingId);
        return true;
    }

    @Path("/unfollowUser")
    @GET  // username unfollows unfollowUsername
    public boolean unfollowUser(@Auth UserSession userSession,@QueryParam("unfollowUsername") String unfollowUsername){
        String username=userSession.getUsername();
        ObjectId followerId=userDAO.findUser(username).getId();
        ObjectId unfollowingId=userDAO.findUser(unfollowUsername).getId();
        relationshipDAO.unfollowUser(followerId, unfollowingId);
        followingDAO.removeUserFollower(username, unfollowingId);
        return true;
    }

    @Path("/followTag")
    @GET   // username follows followTag
    public boolean followTag(@Auth UserSession userSession,@QueryParam("followTag") String followTag){
        String username=userSession.getUsername();
        ObjectId followerId=userDAO.findUser(username).getId();
        ObjectId followingTagId=tagDAO.getTagObFromTagName(followTag).getTagId();
        relationshipDAO.followTag(followerId, followingTagId);
        followingDAO.addTagFollower(username, followingTagId);
        return true;
    }

    @Path("/unfollowTag")
    @GET  // username unfollows unfollowTag
    public boolean unfollowTag(@Auth UserSession userSession,@QueryParam("unfollowTag") String unfollowTag){
        String username=userSession.getUsername();
        ObjectId followerId=userDAO.findUser(username).getId();
        ObjectId unfollowingTagId=tagDAO.getTagObFromTagName(unfollowTag).getTagId();
        relationshipDAO.unfollowTag(followerId, unfollowingTagId);
        followingDAO.removeTagFollower(username, unfollowingTagId);
        return true;
    }
}
