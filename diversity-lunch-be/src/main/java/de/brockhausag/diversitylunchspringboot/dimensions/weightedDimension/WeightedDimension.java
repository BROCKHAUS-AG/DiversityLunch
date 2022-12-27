package de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class WeightedDimension {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private DimensionCategory dimensionCategory;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "dimension_category_id")
    private List<WeightedDimensionSelectableOption> selectableValues;
    @OneToOne
    private WeightedDimensionSelectableOption defaultValue;
}
