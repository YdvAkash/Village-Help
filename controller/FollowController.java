package com.clarify.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @PostMapping("/follow/{userId}")
    public String followUser() { return "follow"; }

    @PostMapping("/unfollow/{userId}")
    public String unfollowUser() { return "unfollow"; }
}
