package com.salonio.modules.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserApi {
    UserDetails loadUserByUsername(String username);
}
