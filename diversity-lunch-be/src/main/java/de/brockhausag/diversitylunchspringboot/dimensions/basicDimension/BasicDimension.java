package de.brockhausag.diversitylunchspringboot.dimensions.basicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BasicDimension {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private DimensionCategory category;
    @OneToMany()
    @JoinColumn(name = "dimension_category_id")
    private List<SelectableBasicValue> selectableValues;
    @OneToOne
    private SelectableBasicValue defaultValue;
}
