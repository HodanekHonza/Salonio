package com.salonio.modules.user.infrastructure.controller;

import com.salonio.modules.client.domain.event.CreateNewUserFromClientEvent;
import com.salonio.modules.user.domain.enums.UserType;
import com.salonio.modules.user.api.dto.UserCreateRequest;
import com.salonio.modules.user.domain.CustomUserDetails;
import com.salonio.modules.user.application.security.JwtUtil;
import com.salonio.modules.user.domain.User;
import com.salonio.modules.user.infrastructure.persistence.UserRepository;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    @Autowired
    AuthenticationManager authenticationManager;

//    @Autowired
    UserRepository userRepository;

//    @Autowired
    PasswordEncoder encoder;

//    @Autowired
    JwtUtil jwtUtils;

    private final ApplicationEventPublisher delegate;

    AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder,  JwtUtil jwtUtils, ApplicationEventPublisher delegate) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.delegate = delegate;
    }

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return jwtUtils.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @RequestBody UserCreateRequest user) {
        if (userRepository.existsByUsername(user.username())) {
            return "Error: Username is already taken!";
        }
        // Create new user's account
        User newUser = new User(
                null,
                user.username(),
                encoder.encode(user.password()),
                user.userType()
        );
       final User savedUser =  userRepository.save(newUser);

        if (user.userType() == UserType.CLIENT) {
            delegate.publishEvent(new CreateNewUserFromClientEvent(savedUser.getId()));
        } else {
            // TODO create staff event
        }

        return "User registered successfully!";
    }

}
