package com.salonio.modules.user.infrastructure.controller;

import com.salonio.modules.client.domain.event.CreateNewUserFromClientEvent;
import com.salonio.modules.staff.domain.event.CreateNewUserFromStaffEvent;
import com.salonio.modules.user.domain.enums.UserType;
import com.salonio.modules.user.api.dto.UserCreateRequest;
import com.salonio.modules.user.domain.CustomUserDetails;
import com.salonio.modules.user.application.security.JwtUtil;
import com.salonio.modules.user.domain.User;
import com.salonio.modules.user.infrastructure.persistence.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // TODO REWORK CONTROLLER
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtils;

    private final ApplicationEventPublisher delegate;

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

        if (user.userType().equals(UserType.CLIENT)) {
            delegate.publishEvent(new CreateNewUserFromClientEvent(savedUser.getId()));
        } else {
            delegate.publishEvent(new CreateNewUserFromStaffEvent(savedUser.getId()));
        }

        return "User registered successfully!";
    }

}
