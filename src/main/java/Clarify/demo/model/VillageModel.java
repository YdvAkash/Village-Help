package Clarify.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "villages")
public class VillageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String villageName;

    @Column(nullable = false, unique = true)
    private Integer villageCode;   // census / govt code

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String tehsil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "sarpanch_user_id",
            referencedColumnName = "id",
            nullable = true
    )
    private UserModel sarpanch;
}
