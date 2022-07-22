package hu.uni.miskolc.lev.java.CourseBoot.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "courseregistration")
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int courseregistration_id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime registeredAt;

    @Min(0)
    @Max(5)
    private int power;
}