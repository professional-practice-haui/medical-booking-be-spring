package com.professionalpractice.medicalbookingbespring.dtos;

import com.professionalpractice.medicalbookingbespring.dtos.common.DateAuditingDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentDTO extends DateAuditingDto {

    Long id;

    String name;

    String nameLeader;

    String description;
}
