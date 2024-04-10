package com.professionalpractice.medicalbookingbespring.dtos.common;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class DateAuditingDto {

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;

}
