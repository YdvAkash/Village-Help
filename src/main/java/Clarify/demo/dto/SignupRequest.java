package Clarify.demo.dto;

import Clarify.demo.model.Enum.UserRole;
import Clarify.demo.model.Enum.Gender;

import java.util.Date;

public class SignupRequest {

    public String name;
    public String email;
    public Long phoneNo;
    public String password;
    public Long aadhaarNo;
    public Gender gender;
    public UserRole role;
    public Integer villageCode;
    public Date dob;
}
