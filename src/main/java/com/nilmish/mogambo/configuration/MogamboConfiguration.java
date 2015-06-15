package com.nilmish.mogambo.configuration;

import io.dropwizard.Configuration;

/**
 * Created by nilesh.m on 14/06/15.
 */
public class MogamboConfiguration extends Configuration {
    private DbConfiguration dbConfiguration;

    public DbConfiguration getDbConfiguration() {
        return dbConfiguration;
    }

    public void setDbConfiguration(DbConfiguration dbConfiguration) {
        this.dbConfiguration = dbConfiguration;
    }
}
