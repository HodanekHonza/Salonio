package com.salonio.user.internal;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    private String password;

    public CustomUserDetails(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override public Collection getAuthorities() { return null; }
    @Override public String getPassword() { return null; }
    @Override public String getUsername() { return username; }
}

