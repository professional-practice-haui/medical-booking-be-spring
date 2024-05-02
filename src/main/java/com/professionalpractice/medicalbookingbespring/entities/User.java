package com.professionalpractice.medicalbookingbespring.entities;

import com.professionalpractice.medicalbookingbespring.entities.common.DateAuditing;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name", nullable = false)
    @NotEmpty(message = "Họ tên không để trống")
    String fullName;

    @Email(message = "Email không hợp lệ")
    @Column(name = "email", nullable = false)
    String email;

    @Size(min = 7, message = "Mật khẩu cần nhiều hơn 7 kí tự")
    @Column(name = "password", nullable = false)
    String password;

    String address;

    String phoneNumber;

    @Enumerated(EnumType.STRING)
    GenderName gender;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "is_locked")
    Boolean isLocked;

    String avatar = "https://static.vecteezy.com/system/resources/previews/026/619/142/original/default-avatar-profile-icon-of-social-media-user-photo-image-vector.jpg";

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles;
}
