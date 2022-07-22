package hu.uni.miskolc.lev.java.CourseBoot.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int student_id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> courseregistration;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profile profile;
}