package com.clarify.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @GetMapping("/image/{id}")
    public String getImage() { return "image"; }

    @GetMapping("/video/{id}")
    public String getVideo() { return "video"; }
}
