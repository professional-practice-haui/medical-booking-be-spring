package com.professionalpractice.medicalbookingbespring.entities;

import java.time.LocalDate;
import java.util.Set;

import com.professionalpractice.medicalbookingbespring.entities.common.DateAuditing;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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

    @Column(name = "roles", nullable = false)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Role> roles;
}
