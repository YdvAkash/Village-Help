package Clarify.demo.service;
import Clarify.demo.service.EmailService;
import Clarify.demo.dto.SignupRequest;
import Clarify.demo.model.UserModel;
import Clarify.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SmsService smsService;
    public UserService(EmailService emailService,SmsService smsService, UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.smsService = smsService;
    }

    public UserModel signUp(SignupRequest request) {

        if (userRepository.existsByEmail(request.email)) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhoneNo(Long.valueOf(request.phoneNo))) {
            throw new RuntimeException("Phone number already exists");
        }

        if (userRepository.existsByAadhaarNo(request.aadhaarNo)) {
            throw new RuntimeException("Aadhaar already exists");
        }

        UserModel user = new UserModel();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPhoneNo(request.phoneNo);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setAadhaarNo(request.aadhaarNo);
        user.setRole(request.role);
        user.setVillageCode(request.villageCode);
        user.setGender(request.gender);
        user.setDob(request.dob);

        UserModel savedUser = userRepository.save(user);

        // 📧 SEND MAIL AFTER SUCCESSFUL SIGNUP
        emailService.sendWelcomeMail(
                savedUser.getEmail(),
                savedUser.getName()
        );

        String message = "Welcome " + savedUser.getName() +
                "! Your account has been created successfully.";
        String Phone = "+91" + String.valueOf(savedUser.getPhoneNo()) ;
        smsService.sendSms(Phone, message);

        return savedUser;
    }
}
