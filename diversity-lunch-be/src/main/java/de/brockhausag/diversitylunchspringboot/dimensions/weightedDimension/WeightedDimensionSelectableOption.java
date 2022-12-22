package de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class WeightedDimensionSelectableOption {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotBlank
    private String value;
    @NotNull
    @Min(0)
    private int weight;
    @NotNull
    private boolean ignoreInScoring;
    @NotNull
    @ManyToOne
    private DimensionCategory dimensionCategory;
}
