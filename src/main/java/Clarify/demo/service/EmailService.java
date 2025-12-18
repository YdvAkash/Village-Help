package Clarify.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendWelcomeMail(String toEmail, String userName) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Clarify 🎉");
        message.setText(
                "Hi " + userName + " 👋,\n\n" +
                        "🎉 Welcome to *Clarify*! 🎉\n\n" +
                        "We’re excited to let you know that your account has been **successfully created**.\n" +
                        "You’re now all set to explore, learn, and grow with us 🚀\n\n" +
                        "If you ever need help, feel free to reach out — we’re always here for you 😊\n\n" +
                        "Warm regards ❤️,\n" +
                        "— Team Clarify"

        );

        mailSender.send(message);
    }

    public void sendOtpMail(String toEmail, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Otp from Clarify");
        message.setText("Hello,\n" +
                "\n" +
                "Your One-Time Password (OTP) is:" + otp +"\n" +
                "\n" +
                "This OTP is valid for 5 minutes. Please do not share it with anyone.\n" +
                "\n" +
                "Thanks,\n" +
                "Team Village Help\n");

        mailSender.send(message);
    }

}
