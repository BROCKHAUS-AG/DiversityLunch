package de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiselectDimension {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private DimensionCategory category;
    @ManyToMany
    private List<SelectableMultiselectValue> selectableValues;
}
