package com.java.auth;
import java.security.Principal;

public class MyUser implements Principal {
    private String email;
    private Long id;

    public MyUser(String email, Long id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return email;
    }
}