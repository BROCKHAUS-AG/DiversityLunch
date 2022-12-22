package de.brockhausag.diversitylunchspringboot.dimensions.basicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class BasicDimensionSelectableOption {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotBlank
    private String value;
    @NotNull
    private boolean ignoreInScoring;
    @NotNull
    @ManyToOne
    private DimensionCategory dimensionCategory;
}
