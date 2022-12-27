package de.brockhausag.diversitylunchspringboot.profile.model.entities;


import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.ProfileEntitySelectedMultiselectValue;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimensionSelectableOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private int birthYear;
    @NotNull
    private boolean wasChangedByAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "basic_dimension", referencedColumnName = "id")
    private Map<BasicDimension, BasicDimensionSelectableOption> selectedBasicValues;

    @ManyToMany(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "weighted_dimension", referencedColumnName = "id")
    private Map<WeightedDimension, WeightedDimensionSelectableOption> selectedWeightedValues;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    @MapKeyJoinColumn(name = "multiselect_dimension", referencedColumnName = "id")
    private Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> selectedMultiselectValues;

}
