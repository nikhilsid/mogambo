package com.nilmish.mogambo.dao;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.nilmish.mogambo.entities.ReportInappropriate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.HashSet;

/**
 * Created by nilesh.m on 27/06/15.
 */
public class ReportInappropriateDAO extends BasicDAO<ReportInappropriate,ObjectId> {

    @Inject
    public ReportInappropriateDAO(Datastore ds) {
        super(ds);
    }

    public void reportInappropriate(String username, ObjectId id) {
        ReportInappropriate reportInappropriate=this.get(id);
        if(reportInappropriate==null){
            reportInappropriate=new ReportInappropriate(id);
            HashSet usernameSet= Sets.newHashSet();
            usernameSet.add(username);
            reportInappropriate.setUsernameSet(usernameSet);
        }
        else{
            HashSet usernameSet=reportInappropriate.getUsernameSet();
            if(usernameSet==null){
                usernameSet=Sets.newHashSet();
            }
            usernameSet.add(username);
        }
        this.save(reportInappropriate);
    }
}
