package com.salonio.user.internal.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User Content.";
    }

}
