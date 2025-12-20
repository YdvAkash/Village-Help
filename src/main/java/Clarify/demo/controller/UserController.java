package Clarify.demo.controller;

import Clarify.demo.dto.ApiResponse;
import Clarify.demo.dto.OtpResponse;
import Clarify.demo.dto.SignupRequest;
import Clarify.demo.dto.SignupResponse;
import Clarify.demo.model.UserModel;
import Clarify.demo.service.OtpSevice;
import Clarify.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final OtpSevice otpSevice;

    public UserController(UserService userService, OtpSevice otpSevice) {
        this.userService = userService;
        this.otpSevice = otpSevice;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(
            @RequestBody SignupRequest request) {

        SignupResponse response = userService.signUp(request);

        return ResponseEntity.status(201).body(
                ApiResponse.<SignupResponse>builder()
                        .success(true)
                        .message("Signup successful")
                        .statusCode(201)
                        .timestamp(System.currentTimeMillis())
                        .data(response)
                        .build()
        );
    }


    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<OtpResponse>> sendOtp(
            @RequestParam String email) {
        OtpResponse otpResponse = otpSevice.sendOtp(email);

        return ResponseEntity.ok(
                ApiResponse.<OtpResponse>builder()
                        .success(otpResponse.isSuccess())
                        .message(otpResponse.getMessage())
                        .data(otpResponse)
                        .statusCode(200)
                        .timestamp(System.currentTimeMillis())
                        .build()
        );
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<OtpResponse>> verifyOtp(
            @RequestParam String email , @RequestParam String otp) {

        OtpResponse otpResponse = otpSevice.verifyOtp(email,otp);

        return ResponseEntity.ok(
                ApiResponse.<OtpResponse>builder()
                        .success(otpResponse.isSuccess())
                        .message(otpResponse.getMessage())
                        .data(otpResponse)
                        .statusCode(200)
                        .timestamp(System.currentTimeMillis())
                        .build()
        );
    }

    @PostMapping("/heath")
    public String heath() {
        return "heath";
    }
}
