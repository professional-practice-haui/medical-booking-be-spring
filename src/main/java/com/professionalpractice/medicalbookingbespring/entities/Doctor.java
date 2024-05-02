package com.professionalpractice.medicalbookingbespring.entities;

import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Enumerated(EnumType.STRING)
    GenderName gender;

    @Column(name = "degree")
    String degree;

    @Column(name = "experience")
    int experience;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "description")
    String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    Department department;

}
