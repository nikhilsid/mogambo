package com.nilmish.mogambo.bootstrap;

import com.google.inject.AbstractModule;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.nilmish.mogambo.auth.AccessTokenService;
import com.nilmish.mogambo.auth.RedisAccessTokenService;
import com.nilmish.mogambo.configuration.DbConfiguration;
import com.nilmish.mogambo.webApp.MogamboConfiguration;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by nilesh.m on 16/06/15.
 */
public class MogamboModule extends AbstractModule {
    private MogamboConfiguration mogamboConfiguration;

    public MogamboModule(MogamboConfiguration mogamboConfiguration) {
        this.mogamboConfiguration = mogamboConfiguration;
    }

    @Override
    protected void configure() {
        DbConfiguration dbConfig=mogamboConfiguration.getDbConfig();
        Mongo mongo= null;
        try {
            mongo = new MongoClient(new ServerAddress(dbConfig.getHost(),dbConfig.getPort()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore((MongoClient) mongo, dbConfig.getDBName());
        bind(Datastore.class).toInstance(ds);
        bind(AccessTokenService.class).to(RedisAccessTokenService.class);
    }
}
