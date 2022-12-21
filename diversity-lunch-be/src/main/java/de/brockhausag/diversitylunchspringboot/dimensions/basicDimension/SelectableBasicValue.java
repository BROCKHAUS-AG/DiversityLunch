package de.brockhausag.diversitylunchspringboot.dimensions.basicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class SelectableBasicValue {
    @Id
    @GeneratedValue
    private Long id;
    private String descriptor;
    private boolean ignoreInScoring;
    @ManyToOne
    private DimensionCategory dimensionCategory;
}
