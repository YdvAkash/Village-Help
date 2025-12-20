package Clarify.demo.dto;
import Clarify.demo.model.Enum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {

    public String name;
    public String email;
    public Long phoneNo;
    public String password;   // ❗ ONLY INPUT
    public Long aadhaarNo;    // ❗ ONLY INPUT
    public Enum.Gender gender;
    public Enum.UserRole role;
    public Integer villageCode;
    public Date dob;
}