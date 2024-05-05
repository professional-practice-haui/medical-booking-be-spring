package com.professionalpractice.medicalbookingbespring.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "shifts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date", nullable = false)
    LocalDate date;

    @Column(name = "time", nullable = false)
    String time;

    @Column(name = "place", nullable = false)
    String place;

    @Column(name = "maxSlot")
    int maxSlot;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    String note;
}
