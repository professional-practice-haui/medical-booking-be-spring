package com.professionalpractice.medicalbookingbespring.entities.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class DateAuditing {

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_at")
    LocalDateTime lastModifiedDate;
}
