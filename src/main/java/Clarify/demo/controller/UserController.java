package Clarify.demo.controller;

import Clarify.demo.dto.SignupRequest;
import Clarify.demo.model.UserModel;
import Clarify.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserModel signup(@RequestBody SignupRequest request) {
        return userService.signUp(request);
    }
    @PostMapping("/heath")
    public String heath() {
        return "heath";
    }
}
