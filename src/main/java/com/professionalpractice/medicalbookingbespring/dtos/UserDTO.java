package com.professionalpractice.medicalbookingbespring.dtos;

import com.professionalpractice.medicalbookingbespring.dtos.common.DateAuditingDto;
import com.professionalpractice.medicalbookingbespring.entities.Role;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends DateAuditingDto {

    Long id;

    String fullName;

    String email;

    String address;

    String phoneNumber;

    GenderName gender;

    LocalDate dateOfBirth;

    String avatar;

    Boolean isLocked;

    Set<Role> roles;

}
