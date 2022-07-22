package hu.uni.miskolc.lev.java.CourseBoot.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profile_id;

    @NotBlank
    private String name=randomName();

    @Min(18)
    private int age=18;

    @OneToOne
    @MapsId
    @JoinColumn(name = "profile_id")
    private Student student;

    public String randomName() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("Péter", "István", "János", "Ádám","Ferenc","Miklós");
        int numberOfElements = 6;
        String randomElement = null;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            randomElement = givenList.get(randomIndex);
        }
        return randomElement;
    }
}