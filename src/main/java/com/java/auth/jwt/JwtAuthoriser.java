//package com.java.auth.jwt;
//
//import com.java.auth.BasicAuthenticator;
//import com.java.auth.Role;
//import io.dropwizard.auth.Authorizer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class JwtAuthoriser implements Authorizer<BasicAuthenticator.MyUser> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthoriser.class);
//
//    @Override
//    public boolean authorize(BasicAuthenticator.MyUser exampleUser, String requiredRole) {
//        if (exampleUser == null) {
//            LOGGER.warn("msg=user object was null");
//            return false;
//        }
//
//        Role role = exampleUser.getRole();
//        if (role == null) {
//            LOGGER.warn("msg=roles were null, user={}, userId={}", exampleUser.getName(), exampleUser.getId());
//            return false;
//        }
//        return role.equals(Role.valueOf(requiredRole));
//    }
//
//}
