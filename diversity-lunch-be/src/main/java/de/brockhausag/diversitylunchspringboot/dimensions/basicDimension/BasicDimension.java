package de.brockhausag.diversitylunchspringboot.dimensions.basicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class BasicDimension {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @OneToOne
    private DimensionCategory dimensionCategory;
    @OneToMany()
    @JoinColumn(name = "dimension_category_id")
    @Size(min = 1)
    private List<BasicDimensionSelectableOption> selectableValues;
    @NotNull
    @OneToOne
    private BasicDimensionSelectableOption defaultValue;
}
