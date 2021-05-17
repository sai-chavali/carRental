package com.java.auth;

import com.java.core.User;
import com.java.db.TokenDAO;
import com.java.db.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class BasicAuthenticator implements Authenticator<UserCredentials, MyUser> {
    private UserDAO userDAO;
    private TokenDAO tokenDAO;

    public BasicAuthenticator(UserDAO userDAO, TokenDAO tokenDAO) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
    }

    @Override
    public Optional<MyUser> authenticate(UserCredentials credentials) throws AuthenticationException {
        MyUser authenticatedUser = null;
        Long id = tokenDAO.findTokenForUser(credentials.getToken());

        if (id != null) {
            User user = userDAO.findById(id);
            if (user != null)
                authenticatedUser = new MyUser(user.getEmail(), user.getId());
        }
        return Optional.ofNullable(authenticatedUser);
    }
}
