package com.nilmish.mogambo.resources;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.nilmish.mogambo.auth.UserSession;
import com.nilmish.mogambo.dao.FollowingDAO;
import com.nilmish.mogambo.dao.RelationshipDAO;
import com.nilmish.mogambo.dao.TagDAO;
import com.nilmish.mogambo.entities.Following;
import com.nilmish.mogambo.entities.Tag;
import com.nilmish.mogambo.responseModel.TagResponse;
import com.nilmish.mogambo.utils.GuiceInjector;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import io.dropwizard.auth.Auth;
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
@Api("/tag")
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
    public Boolean saveTag(@Auth UserSession userSession, @QueryParam("tagName") String tagName,
                           @QueryParam("typeId") Integer typeId, @QueryParam("tagMeaning") String tagMeaning){
        Tag tag=new Tag(tagName,typeId,tagMeaning);
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
            response = TagResponse.class, responseContainer = "List")
    @GET
    public List<TagResponse> getAllTags(@Auth UserSession userSession){
        List<String> tagNameList=tagDAO.findIds();
        List<TagResponse> tagList=Lists.newArrayList();
        for(String tagName:tagNameList){
            Tag tag=tagDAO.get(tagName);
            tagList.add(new TagResponse(tag.getTagName(),tag.getTagMeaning(),tag.getTypeId()));
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
    @ApiOperation(value = "Use to get all the tags followed by user", notes = "This API is used to get all the" +
            "tags followed by this user", response = TagResponse.class, responseContainer = "List")
    @GET
    public List<TagResponse> getTagsFollowed(@Auth UserSession userSession){
        Following following=followingDAO.get(userSession.getUsername());
        List<Tag> tags=tagDAO.getTagsFromIds(following.getFollowingTagnameList());
        List<TagResponse> tagResponses=Lists.newArrayList();
        for(Tag tag:tags){
            tagResponses.add(new TagResponse(tag.getTagName(),tag.getTagMeaning(),tag.getTypeId()));
        }
        return tagResponses;
    }


    @Path("/followTag")
    @ApiOperation(value = "Use to follow a given tag", notes = "This API is used when current user" +
            "wants to follow tag *followingTag*. Returns true iff ", response = Boolean.class)
    @GET
    public Boolean followTag(@Auth UserSession userSession,@QueryParam("followingTag") String followingTag){
        String username=userSession.getUsername();
        boolean followed=relationshipDAO.follow(username, followingTag, 0);
        if(followed) {
            followingDAO.addTagFollower(username, followingTag);
            tagDAO.incrementCount(followingTag);
            return true;
        }
        return false; // tag was already followed
    }

    @Path("/unfollowTag")
    @ApiOperation(value = "Use to unfollow a given tag", notes = "This API is used when current user" +
            "wants to follow tag *unfollowingTag*. Returns true iff ", response = Boolean.class)
    @GET
    public Boolean unfollowTag(@Auth UserSession userSession,@QueryParam("unfollowingTag") String unfollowingTag){
        String username=userSession.getUsername();
        relationshipDAO.unfollow(username, unfollowingTag);
        followingDAO.removeTagFollower(username, unfollowingTag);
        return tagDAO.decrementCount(unfollowingTag);
    }


}
