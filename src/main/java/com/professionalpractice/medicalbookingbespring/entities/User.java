package com.professionalpractice.medicalbookingbespring.entities;

import com.professionalpractice.medicalbookingbespring.entities.common.DateAuditing;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", columnDefinition = "tinyint default 0")
    private boolean status;
}
