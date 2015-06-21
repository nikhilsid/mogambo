package com.nilmish.mogambo.auth;

/**
 * Created by nilesh.m on 20/06/15.
 */
public interface AccessTokenService {
    public UserSession createAccessToken(UserSession userSession);

    public UserSession getUserFromAccessToken(String accessToken);

    public boolean isValidToken(String accessToken);

    public void removeAccessToken(String accessToken);

    public void removeUser(String userName);

}
