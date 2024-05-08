package com.professionalpractice.medicalbookingbespring.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String format(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(FORMATTER);
    }
}
