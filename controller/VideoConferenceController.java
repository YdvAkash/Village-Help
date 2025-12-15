package com.clarify.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class VideoConferenceController {

    @PostMapping("/offer")
    public String offer() { return "offer"; }

    @PostMapping("/answer")
    public String answer() { return "answer"; }

    @PostMapping("/candidate")
    public String candidate() { return "candidate"; }
}
