package com.professionalpractice.medicalbookingbespring.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.professionalpractice.medicalbookingbespring.dtos.common.DateAuditingDto;
import com.professionalpractice.medicalbookingbespring.entities.Role;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends DateAuditingDto {

    Long id;

    String fullName;

    String email;

    String address;

    String phoneNumber;

    GenderName gender;

    LocalDate dateOfBirth;

    String avatar;

    Boolean isLocked;

    List<String> roles;

    public void setRoles(List<Role> roles) {
        this.roles = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
    }
}
