package com.java.auth;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class CustomSecurityContext implements SecurityContext {
    private final MyUser principal;
    private final SecurityContext securityContext;

    public CustomSecurityContext(MyUser principal, SecurityContext securityContext) {
        this.principal = principal;
        this.securityContext = securityContext;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return true;
    }

    @Override
    public boolean isSecure() {
        return securityContext.isSecure();
    }

    @Override
    public String getAuthenticationScheme() {
        return "CUSTOM_TOKEN";
    }
}
