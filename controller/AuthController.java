package com.clarify.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String register() { return "register"; }

    @PostMapping("/login")
    public String login() { return "login"; }
}
