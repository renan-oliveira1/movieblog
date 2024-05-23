package com.example.movieblog.models;

public enum UserAuthority {
    ADMIN("admin"),
    USER("user");

    private String authority;

    UserAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
