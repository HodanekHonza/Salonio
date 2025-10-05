package com.salonio.modules.user.application.service;

import com.salonio.modules.user.api.UserApi;
import com.salonio.modules.user.domain.CustomUserDetails;
import com.salonio.modules.user.domain.User;
import com.salonio.modules.user.infrastructure.persistence.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, UserApi {

    //    @Autowired
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
        return new CustomUserDetails(user);
    }

}
