package Clarify.demo.repository;

import Clarify.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByPhoneNo(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhoneNo(Long phoneNo);
    boolean existsByAadhaarNo(Long aadhaarNo);
}
