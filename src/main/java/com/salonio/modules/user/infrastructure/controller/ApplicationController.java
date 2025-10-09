package com.salonio.modules.user.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Salonio Backend API. The frontend will connect here soon.";
    }
}
