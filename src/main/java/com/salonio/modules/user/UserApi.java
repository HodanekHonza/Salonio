package com.salonio.modules.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserApi {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
