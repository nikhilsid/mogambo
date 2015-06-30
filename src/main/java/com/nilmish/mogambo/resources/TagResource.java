package com.nilmish.mogambo.resources;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.FollowingDAO;
import com.nilmish.mogambo.dao.RelationshipDAO;
import com.nilmish.mogambo.dao.TagDAO;
import com.nilmish.mogambo.entities.Following;
import com.nilmish.mogambo.entities.Tag;
import com.nilmish.mogambo.utils.GuiceInjector;
import com.wordnik.swagger.annotations.ApiOperation;
import io.dropwizard.auth.Auth;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by nilesh.m on 16/06/15.
 */

@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {
    public static final Logger logger = LoggerFactory.getLogger(TagResource.class);
    @Inject private TagDAO tagDAO;
    @Inject private FollowingDAO followingDAO;
    @Inject private RelationshipDAO relationshipDAO;

    @Inject
    public TagResource() {
        GuiceInjector.getInjector().injectMembers(this);
    }


    @Path("/saveTag")
    @ApiOperation(value = "Saves the New Tag", notes = "This API needs to be used to save the new Tag",
            response = Boolean.class)
    @POST
    public Boolean saveTag(@Auth UserSession userSession, Tag tag){
        Key<Tag> key=tagDAO.save(tag);
        if(key==null || key.getId()==null){
            logger.info("Could not save tag with details: "+tag.toString());
            return false;
        }
        else{
            logger.info("Tag saved in db with details: "+tag.toString());
            return true;
        }
    }


    @Path("/getAllTags")
    @ApiOperation(value = "Gets all the tags from DB", notes = "This API gets the list of all tags from DB",
            response = Boolean.class)
    @GET
    public List<Tag> getAllTags(@Auth UserSession userSession){
        List<String> tagNameList=tagDAO.findIds();
        List<Tag> tagList=Lists.newArrayList();
        for(String tag:tagNameList){
            tagList.add(tagDAO.get(tag));
        }
        return tagList;
    }


    @Path("/getTrendyTags")
    @ApiOperation(value = "Gets the trendy tags", notes = "This API gets all the trendy tags on the basis" +
            "number of user who follow a given TAG",
            response = Tag.class, responseContainer = "List")
    @GET
    public List<Tag> getTrendyTags(@Auth UserSession userSession){
        List<Tag> tagList=tagDAO.getTrendyTags();
        if(tagList==null){
            tagList=Lists.newArrayList();
        }
        return tagList;
    }



    @Path("/getFollowingTags")
    @GET
    public List<Tag> getFollowingTags(@Auth UserSession userSession){
        String username=userSession.getUsername();
        List<String> tagIdList= followingDAO.get(username).getFollowingTagnameList();
        return tagDAO.getTagsFromIds(tagIdList);
    }


    @Path("/followTag")
    @GET   // username follows followTag
    public Boolean followTag(@Auth UserSession userSession,@QueryParam("followingTag") String followingTag){
        String username=userSession.getUsername();
        relationshipDAO.follow(username, followingTag, 0);
        followingDAO.addTagFollower(username, followingTag);
        return tagDAO.incrementCount(followingTag);
    }

    @Path("/unfollowTag")
    @GET  // username unfollows unfollowTag
    public Boolean unfollowTag(@Auth UserSession userSession,@QueryParam("unfollowingTag") String unfollowingTag){
        String username=userSession.getUsername();
        relationshipDAO.unfollow(username, unfollowingTag);
        followingDAO.removeTagFollower(username, unfollowingTag);
        return tagDAO.decrementCount(unfollowingTag);
    }

    @Path("/getTagsFollowed")
    @GET
    public List<Tag> getTagsFollowed(@Auth UserSession userSession){
        Following following=followingDAO.get(userSession.getUsername());
        return tagDAO.getTagsFromIds(following.getFollowingTagnameList());
    }
}
