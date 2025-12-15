package com.clarify.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/access")
public class AccessRequestController {

    @PostMapping("/request/{mediaId}")
    public String requestAccess() { return "requested"; }

    @PostMapping("/approve/{requestId}")
    public String approveAccess() { return "approved"; }
}
