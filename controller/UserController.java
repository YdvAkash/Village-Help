package com.clarify.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser() { return "getUser"; }

    @GetMapping("/search")
    public String searchUsers() { return "searchUsers"; }
}
