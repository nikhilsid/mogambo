package com.nilmish.mogambo.auth;


import com.google.common.base.Optional;
import com.google.inject.Inject;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

/**
 * Created by nilesh.m on 21/06/15.
 */
public class AccessTokenAuthenticator implements Authenticator<AccessTokenCredentials,UserSession> {
    private AccessTokenService accessTokenService;

    @Inject
    public AccessTokenAuthenticator(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    public Optional<UserSession> authenticate(AccessTokenCredentials accessTokenCredentials) throws AuthenticationException {
        if(accessTokenService.isValidToken(accessTokenCredentials.getToken())){
            UserSession agent=accessTokenService.getUserFromAccessToken(accessTokenCredentials.getToken());
            return Optional.fromNullable(agent);
        }
        else{
            throw new AuthenticationException("Invalid credentials");
        }
    }
}
