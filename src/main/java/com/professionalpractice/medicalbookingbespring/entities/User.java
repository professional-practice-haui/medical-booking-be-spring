package com.professionalpractice.medicalbookingbespring.entities;

import com.professionalpractice.medicalbookingbespring.entities.common.DateAuditing;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "status")
    boolean status;

    @Column(name = "roles", nullable = false)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<Role> roles;
}
