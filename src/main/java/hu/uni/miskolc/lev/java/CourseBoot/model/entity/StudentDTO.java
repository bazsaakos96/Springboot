package hu.uni.miskolc.lev.java.CourseBoot.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int student_id;

    private String email;

    private String password;

    private int profile_id;
}