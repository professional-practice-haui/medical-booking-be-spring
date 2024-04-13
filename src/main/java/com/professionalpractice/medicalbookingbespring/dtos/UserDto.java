package com.professionalpractice.medicalbookingbespring.dtos;

import com.professionalpractice.medicalbookingbespring.dtos.common.DateAuditingDto;
import lombok.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends DateAuditingDto {

    private Long id;

    private String fullName;

    private String email;

    private Boolean status;

    private List<String> roles;
}
