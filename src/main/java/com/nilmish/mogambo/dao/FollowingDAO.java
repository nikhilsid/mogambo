package com.nilmish.mogambo.dao;

import com.google.inject.Inject;
import com.nilmish.mogambo.entities.Following;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilesh.m on 19/06/15.
 */
public class FollowingDAO extends BasicDAO<Following,String> {
    public static final Logger logger = LoggerFactory.getLogger(FollowingDAO.class);
    @Inject
    public FollowingDAO(Datastore ds) {
        super(ds);
    }


    public void addUserFollower(String username, String followingUsername) {
        Following following=this.get(username);
        if(following==null){
            List<String> followingUsernameList=new ArrayList<String>();
            followingUsernameList.add(followingUsername);
            List<String> followingTagnameList=null;
            following=new Following(username,followingUsernameList,followingTagnameList);
        }
        else{
            if(following.getFollowingUsernameList()==null) {
                following.setFollowingUsernameList(new ArrayList<String>());
            }
            following.getFollowingUsernameList().add(followingUsername);
        }
        this.save(following);
    }

    public void removeUserFollower(String username, String unfollowingId) {
        Following following=this.get(username);
        if(following.getFollowingUsernameList()!=null) {
            logger.info("user with objectId: "+unfollowingId+" is removed from user: "+username+" following list");
            following.getFollowingUsernameList().remove(unfollowingId);
            this.save(following);
        }
        else{
            logger.info("user with objectId: "+unfollowingId+" not found in the user: "+username+" following list");
        }
    }

    public void addTagFollower(String username, String followingTagId) {
        Following following=this.get(username);
        if(following==null){
            List<String> followingIdList=null;
            List<String> followingTagList= new ArrayList<String>();
            followingTagList.add(followingTagId);
            following=new Following(username,followingIdList,followingTagList);
        }
        else{
            if(following.getFollowingTagnameList()==null) {
                following.setFollowingTagnameList(new ArrayList<String>());
            }
            following.getFollowingTagnameList().add(followingTagId);
        }
        this.save(following);
    }

    public void removeTagFollower(String username, String unfollowingTagId) {
        Following following=this.get(username);
        if(following.getFollowingTagnameList()!=null) {
            logger.info("user with objectId: "+unfollowingTagId+" is removed from user: "+username+" following list");
            following.getFollowingTagnameList().remove(unfollowingTagId);
            this.save(following);
        }
        else{
            logger.info("user with objectId: "+unfollowingTagId+" not found in the user: "+username+" following list");
        }
    }

}
