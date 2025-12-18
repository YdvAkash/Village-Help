package Clarify.demo.model;

import jakarta.persistence.*;
import Clarify.demo.model.Enum.Gender;
import org.hibernate.annotations.CreationTimestamp;
import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(length = 10,unique = true,nullable = false)
    private Long phoneNo;


    @Column(length = 500, nullable = false)
    private String password;

    @Column(length = 12, nullable = false, unique = true)
    private Long aadhaarNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'VILLAGER'")
    private Enum.UserRole role;

    @Column(nullable = false)
    private Integer villageCode;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
}
