package com.professionalpractice.medicalbookingbespring.entities;

import com.professionalpractice.medicalbookingbespring.utils.RoleName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    RoleName roleName;

    public Role(RoleName roleName) {

        this.roleName = roleName;
    }

    public String getRoleName() {
        
        return this.roleName.toString();
    }
}
