package de.brockhausag.diversitylunchspringboot.profile.model.entities;


import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.ProfileEntitySelectedMultiselectValue;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimensionSelectableOption;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    private int birthYear;
    @NotNull
    private boolean wasChangedByAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "basic_dimension", referencedColumnName = "id")
    @ToString.Exclude
    private Map<BasicDimension, BasicDimensionSelectableOption> selectedBasicValues;

    @ManyToMany(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "weighted_dimension", referencedColumnName = "id")
    @ToString.Exclude
    private Map<WeightedDimension, WeightedDimensionSelectableOption> selectedWeightedValues;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    @MapKeyJoinColumn(name = "multiselect_dimension", referencedColumnName = "id")
    @ToString.Exclude
    private Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> selectedMultiselectValues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProfileEntity that = (ProfileEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
