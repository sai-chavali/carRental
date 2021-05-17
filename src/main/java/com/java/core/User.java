package com.java.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotNull;

public class User {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String username;

    @NotNull
    @JsonProperty
    private String email;

    @NotNull
    @JsonProperty
    private String password;

    @JsonProperty
    private UserStatus status;

    public User(){
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = UserStatus.ACTIVE;
    }

    public User(@NotNull String email, @NotNull  String password) {
        this.email = email;
        this.password = password;
    }

    public User(Long id, @NotNull String username, @NotNull String email, String password, UserStatus status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        if(status == null)
            status = UserStatus.ACTIVE;
        return status.name();
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
