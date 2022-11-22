package de.brockhausag.diversitylunchspringboot.profile.model.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int birthYear;

    @ManyToOne
    private CountryEntity originCountry;
    @ManyToOne
    private DietEntity diet;
    @ManyToOne
    private EducationEntity education;
    @ManyToOne
    private GenderEntity gender;
    @ManyToOne
    private LanguageEntity motherTongue;
    @ManyToOne
    private ProjectEntity project;
    @ManyToOne
    private ReligionEntity religion;
    @ManyToOne
    private WorkExperienceEntity workExperience;
    @ManyToOne
    private HobbyEntity hobby;
    @ManyToOne
    private SexualOrientationEntity sexualOrientation;
    @ManyToOne
    private SocialBackgroundEntity socialBackground;
    @ManyToOne
    private SocialBackgroundDiscriminationEntity discrimination;


    @Override
    public int hashCode() {
        String hashString = this.id.toString() + this.name + this.email + this.birthYear;
        return hashString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        final ProfileEntity other = (ProfileEntity) obj;
        return other.id.equals(this.id) && other.name.equals(this.name) &&
                other.email.equals(this.email) && (other.birthYear == this.birthYear);
    }

}
