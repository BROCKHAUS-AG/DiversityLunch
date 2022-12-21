package de.brockhausag.diversitylunchspringboot.dimensions.multiselectDimension;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SelectableMultiselectValue {
    @Id
    @GeneratedValue
    private Long id;
    private String descriptor;
}
