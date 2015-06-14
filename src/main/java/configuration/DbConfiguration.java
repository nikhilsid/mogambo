package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nilesh.m on 14/06/15.
 */

public class DbConfiguration {
    @JsonProperty
    private String dbName;

    @JsonProperty
    private int port;

    @JsonProperty
    private String host;

    @JsonProperty
    private String user;

    @JsonProperty
    private String password;


    public String getPassword() {
        return password;
    }

    public String getDBName() {
        return dbName;
    }

    public String getUser() {
        return user;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "DbConfiguration{" +
                "dbName='" + dbName + '\'' +
                ", port=" + port +
                ", host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}