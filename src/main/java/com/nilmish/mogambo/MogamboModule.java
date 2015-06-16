package com.nilmish.mogambo;

import com.google.inject.AbstractModule;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.nilmish.mogambo.configuration.DbConfiguration;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by nilesh.m on 16/06/15.
 */
public class MogamboModule extends AbstractModule {
    private DbConfiguration dbConfig;

    public MogamboModule(DbConfiguration dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    protected void configure() {
        Mongo mongo= null;
        try {
            mongo = new MongoClient(new ServerAddress(dbConfig.getHost(),dbConfig.getPort()));
            System.out.println(dbConfig.getHost());
            System.out.println(dbConfig.getPort());
            System.out.println(dbConfig.getDBName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore((MongoClient) mongo, dbConfig.getDBName());
        bind(Datastore.class).toInstance(ds);
    }
}
