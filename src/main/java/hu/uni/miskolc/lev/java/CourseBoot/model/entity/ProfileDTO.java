package hu.uni.miskolc.lev.java.CourseBoot.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private int profile_id;

    private String name;

    private int age;

    private int student_id;
}
