package com.salonio.user.internal.controller;

import com.salonio.user.internal.security.JwtUtil;
import com.salonio.user.internal.User;
import com.salonio.user.internal.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder,  JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        // Create new user's account
        User newUser = new User(
                null,
                user.getUsername(),
                encoder.encode(user.getPassword())
        );
        userRepository.save(newUser);
        // send event to have client record created
        return "User registered successfully!";
    }

}
