package com.salonio.modules.user.application.service;

import com.salonio.modules.user.api.UserApi;
import com.salonio.modules.user.domain.CustomUserDetails;
import com.salonio.modules.user.domain.User;
import com.salonio.modules.user.infrastructure.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService, UserApi {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        return new CustomUserDetails(user);
    }

}
