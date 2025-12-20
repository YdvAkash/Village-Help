package Clarify.demo.dto;

import Clarify.demo.model.Enum.UserRole;
import Clarify.demo.model.Enum.Gender;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {

    private Integer userId;
    private String name;
    private String email;
    private Long phoneNo;
    private UserRole role;
    private Integer villageCode;
    private Gender gender;
    private Date dob;
}
