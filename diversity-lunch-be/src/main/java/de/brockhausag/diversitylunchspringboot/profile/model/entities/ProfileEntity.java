package de.brockhausag.diversitylunchspringboot.profile.model.entities;


import de.brockhausag.diversitylunchspringboot.profile.model.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileEntity {

    @Id
    @GeneratedValue
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
    private ProjectEntity projects;
    @ManyToOne
    private ReligionEntity religion;


    @Enumerated(EnumType.STRING)
    private Hobby hobby;

    @Enumerated(EnumType.STRING)
    private WorkExperience workExperience;



    @Override
    public int hashCode() {
        String hashString = this.id.toString() + this.name + this.email + Integer.toString(this.birthYear);
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
