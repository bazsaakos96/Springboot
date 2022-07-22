package hu.uni.miskolc.lev.java.CourseBoot.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "course")
    Set<CourseRegistration> courseregistration;
}