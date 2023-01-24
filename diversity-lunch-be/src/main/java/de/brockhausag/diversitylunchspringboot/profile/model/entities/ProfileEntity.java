package de.brockhausag.diversitylunchspringboot.profile.model.entities;


import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.generics.weightedDimension.WeightedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "profile_hobby",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "hobby_id") }
    )
    private List<HobbyEntity> hobby;
    @ManyToOne
    private SexualOrientationEntity sexualOrientation;
    @ManyToOne
    private SocialBackgroundEntity socialBackground;
    @ManyToOne
    private SocialBackgroundDiscriminationEntity socialBackgroundDiscrimination;

    private boolean wasChangedByAdmin;

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

    public List<DefaultDimensionEntity> getDefaultEntities() {
        List<DefaultDimensionEntity> baseEntities = new ArrayList<>();
        baseEntities.add(originCountry);
        baseEntities.add(diet);
        baseEntities.add(education);
        baseEntities.add(gender);
        baseEntities.add(motherTongue);
        baseEntities.add(project);
        baseEntities.add(religion);
        baseEntities.add(sexualOrientation);
        baseEntities.add(socialBackground);
        baseEntities.add(socialBackgroundDiscrimination);
        return baseEntities;
    }

    public List<WeightedEntity> getWeightedEntities() {
        List<WeightedEntity> weightedEntitites = new ArrayList<>();
        weightedEntitites.add(workExperience);
        return weightedEntitites;
    }

}
