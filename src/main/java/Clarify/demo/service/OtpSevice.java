package Clarify.demo.service;

import Clarify.demo.dto.OtpResponse;
import Clarify.demo.model.OtpModel;
import Clarify.demo.repository.OtpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@Slf4j
public class OtpSevice {

    private final EmailService emailService;
    private final OtpRepository otpRepository;

    private static final SecureRandom random = new SecureRandom();

    public OtpSevice(EmailService emailService, OtpRepository otpRepository) {
        this.emailService = emailService;
        this.otpRepository = otpRepository;
    }

    // ===================== SEND OTP =====================
    public OtpResponse sendOtp(String email) {

        try {
            String otp = String.valueOf(100000 + random.nextInt(900000));

            emailService.sendOtpMail(email, otp);

            OtpModel entry = otpRepository.findByEmail(email)
                    .orElseGet(OtpModel::new);

            entry.setEmail(email);
            entry.setOtp(otp);
            entry.setVerified(false);
            entry.setExpiresAt(LocalDateTime.now().plusMinutes(5));

            otpRepository.save(entry);

            log.info("OTP sent successfully to {}", email);

            return OtpResponse.builder()
                    .success(true)
                    .message("OTP sent successfully")
                    .email(email)
                    .verified(false)
                    .build();

        } catch (Exception e) {
            log.error("Failed to send OTP to {}", email, e);

            return OtpResponse.builder()
                    .success(false)
                    .message("Failed to send OTP")
                    .email(email)
                    .verified(false)
                    .build();
        }
    }

    // ===================== VERIFY OTP =====================
    public OtpResponse verifyOtp(String email, String otp) {

        OtpModel record = otpRepository.findByEmail(email)
                .orElse(null);

        if (record == null) {
            return OtpResponse.builder()
                    .success(false)
                    .message("OTP not found")
                    .email(email)
                    .verified(false)
                    .build();
        }

        if (record.isVerified()) {
            return OtpResponse.builder()
                    .success(false)
                    .message("OTP already used")
                    .email(email)
                    .verified(true)
                    .build();
        }

        if (record.getExpiresAt().isBefore(LocalDateTime.now())) {
            return OtpResponse.builder()
                    .success(false)
                    .message("OTP expired")
                    .email(email)
                    .verified(false)
                    .build();
        }

        if (!record.getOtp().equals(otp)) {
            return OtpResponse.builder()
                    .success(false)
                    .message("Invalid OTP")
                    .email(email)
                    .verified(false)
                    .build();
        }

        // ✅ OTP verified
        record.setVerified(true);
        record.setOtp(null);
        otpRepository.save(record);

        log.info("OTP verified successfully for {}", email);

        return OtpResponse.builder()
                .success(true)
                .message("OTP verified successfully")
                .email(email)
                .verified(true)
                .build();
    }
}
