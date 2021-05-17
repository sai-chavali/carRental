package com.java.resources;


import com.java.core.Token;
import com.java.db.TokenDAO;
import com.java.db.UserDAO;
import org.skife.jdbi.v2.OutParameters;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {
    private UserDAO userDAO;
    private TokenDAO tokenDAO;

    public TokenResource(UserDAO userDAO, TokenDAO tokenDAO) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
    }

    @GET
    @Path("/token/{userId}")
    public Token getToken(@PathParam("userId") Long userId) {
        String token = tokenDAO.findOrCreateTokenforUser(userId);

        if (token != null) {
            return new Token(userId, token);
        }

        throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }
}
