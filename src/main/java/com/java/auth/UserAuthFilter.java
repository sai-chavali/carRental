package com.java.auth;

import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthenticationException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Optional;

@PreMatching
@Provider
@Priority(Priorities.AUTHENTICATION)
public class UserAuthFilter extends AuthFilter<UserCredentials, MyUser> {
    private BasicAuthenticator authenticator;

    public UserAuthFilter(BasicAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        Optional<MyUser> authUser;
        UserCredentials credentials = getCredentials(containerRequestContext);

        try {
            authUser = authenticator.authenticate(credentials);
            if(!authUser.isPresent())
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        } catch (AuthenticationException e) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        if(authUser.isPresent()){
            SecurityContext securityContext = new CustomSecurityContext(authUser.get(), containerRequestContext.getSecurityContext());
            containerRequestContext.setSecurityContext(securityContext);
        }
    }

    private UserCredentials getCredentials(ContainerRequestContext context) {
        UserCredentials credentials = new UserCredentials("","");
        credentials.setToken(context.getHeaderString("Authorization"));
        return credentials;
    }
}
