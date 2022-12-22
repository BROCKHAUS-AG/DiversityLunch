package de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "multiselect_collection")
public class MultiselectDimensionSelectableOptionsCollection {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private ProfileEntity profile;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "dimension_category_id")
    private List<MultiselectDimensionSelectableOption> selectedOptions;
    @OneToOne
    private DimensionCategory dimensionCategory;

    @Override
    public String toString() {
        return "MultiselectDimensionSelectableOptionsCollection{" +
                "id=" + id +
                ", selectedOptions=" + selectedOptions +
                '}';
    }
}


