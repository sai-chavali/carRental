package com.java.core;

public enum UserStatus {
    ACTIVE ("ACTIVE"),
    INACTIVE ("INACTIVE");

    private final String label;

    UserStatus(String status) {
        label = status;
    }

    public String toString() {
        return this.label;
    }

    public static UserStatus valueOfLabel(String label) {
        for (UserStatus e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public boolean equalsName(String otherName) {
        return label.equals(otherName);
    }
}
