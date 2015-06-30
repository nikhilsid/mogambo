package com.nilmish.mogambo.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Created by nilesh.m on 14/06/15.
 */
public class MogamboConfiguration extends Configuration {
    @JsonProperty
    private DbConfiguration dbConfig;

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    public DbConfiguration getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DbConfiguration dbConfig) {
        this.dbConfig = dbConfig;
    }

}
