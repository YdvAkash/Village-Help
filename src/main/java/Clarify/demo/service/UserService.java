package Clarify.demo.service;
import Clarify.demo.dto.SignupRequest;
import Clarify.demo.dto.SignupResponse;
import Clarify.demo.exception.ResourceAlreadyExistsException;
import Clarify.demo.service.EmailService;
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

    public UserService(EmailService emailService, SmsService smsService,
                       UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.smsService = smsService;
    }

    public SignupResponse signUp(SignupRequest request) {

        if (userRepository.existsByEmail(request.email)) {
            throw new ResourceAlreadyExistsException("Email already registered");
        }

        if (userRepository.existsByPhoneNo(request.phoneNo)) {
            throw new ResourceAlreadyExistsException("Phone number already registered");
        }

        if (userRepository.existsByAadhaarNo(request.aadhaarNo)) {
            throw new ResourceAlreadyExistsException("Aadhaar already registered");
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

        // 📧 Email
        emailService.sendWelcomeMail(savedUser.getEmail(), savedUser.getName());

        // 📱 SMS
        smsService.sendSms("+91" + savedUser.getPhoneNo(),
                "Welcome " + savedUser.getName() + "! Account created successfully.");

        return SignupResponse.builder()
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .phoneNo(savedUser.getPhoneNo())
                .role(savedUser.getRole())
                .villageCode(savedUser.getVillageCode())
                .gender(savedUser.getGender())
                .dob(savedUser.getDob())
                .build();
    }
}


