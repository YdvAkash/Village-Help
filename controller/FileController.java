package com.clarify.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file) {
        return "uploaded";
    }

    @GetMapping("/{id}")
    public String getFile() { return "file"; }
}
