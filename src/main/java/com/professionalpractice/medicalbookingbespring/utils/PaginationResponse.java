package com.professionalpractice.medicalbookingbespring.utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationResponse<T> {

    int page;

    int limit;

    long totalResults;

    List<T> items;
}
