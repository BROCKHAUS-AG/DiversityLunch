package de.brockhausag.diversitylunchspringboot.profile.modelTest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String email;

    private int birthYear;

    @Enumerated(EnumType.STRING)
    private Project currentProject;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Country originCountry;

    @Enumerated(EnumType.STRING)
    private Language motherTongue;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Enumerated(EnumType.STRING)
    private Hobby hobby;

    @Enumerated(EnumType.STRING)
    private Education education;

    @Enumerated(EnumType.STRING)
    private WorkExperience workExperience;

    @Enumerated(EnumType.STRING)
    private Diet diet;
}
