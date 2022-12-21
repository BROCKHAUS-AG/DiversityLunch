package de.brockhausag.diversitylunchspringboot.profile.model.entities;


import de.brockhausag.diversitylunchspringboot.dimensions.basicDimension.SelectableBasicValue;
import de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension.MultiSelectValueMap;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.SelectableWeightedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @ManyToMany
    private List<SelectableBasicValue> selectedBasicValues;
    @ManyToMany
    private List<SelectableWeightedValue> selectedWeightedValues;
    @ManyToMany
    private List<MultiSelectValueMap> selectedMultiselectValues;
}
