package com.professionalpractice.medicalbookingbespring.dtos;

import com.professionalpractice.medicalbookingbespring.dtos.common.DateAuditingDto;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


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

    String phone;

    GenderName gender;

    LocalDateTime dateOfBirth;

    String avatar;

    Boolean isLocked;
}
