package com.java.auth;

public enum Role {
    GUEST("Guest"),
    USER("User"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
