package com.professionalpractice.medicalbookingbespring.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "health-forms")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HealthForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "name_patient", nullable = false)
    String namePatient;

    String email;

    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shift_id")
    Shift shift;

    @Column(name = "reason", nullable = false)
    String reason;

    @Column(name = "cccd", nullable = false)
    String cccd;

    @Column(name = "bhyt")
    String bhyt;

    @Column(name = "deniedReason")
    String deniedReason;
}
