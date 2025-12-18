package Clarify.demo.repository;

import Clarify.demo.model.OtpModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OtpRepository extends JpaRepository<OtpModel, Integer> {
    Optional<OtpModel> findByEmail(String email);
    Optional<OtpModel> findTopByEmailOrderByExpiresAtDesc(String email);
}
