package de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ProfileEntitySelectedMultiselectValue {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<MultiselectDimensionSelectableOption> selectedOptions;
}
