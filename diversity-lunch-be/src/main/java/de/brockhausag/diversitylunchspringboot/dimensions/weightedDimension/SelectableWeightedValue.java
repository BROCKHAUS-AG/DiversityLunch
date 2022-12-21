package de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class SelectableWeightedValue {

    @Id
    @GeneratedValue
    private Long id;

    private String descriptor;

    private int weight;

    private boolean ignoreInScoring;
}
