package de.brockhausag.diversitylunchspringboot.profile.model.entities;


import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiselectDimensionSelectableOptionsCollection;
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
    @MapKeyJoinColumn(name = "dimension_category", referencedColumnName = "id")
    private Map<DimensionCategory, BasicDimensionSelectableOption> selectedBasicValues;

    @ManyToMany(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "dimension_category", referencedColumnName = "id")
    private Map<DimensionCategory, WeightedDimensionSelectableOption> selectedWeightedValues;
    @ManyToMany(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "dimension_category", referencedColumnName = "id")
    private Map<DimensionCategory, MultiselectDimensionSelectableOptionsCollection> selectedMultiselectValues;
}
