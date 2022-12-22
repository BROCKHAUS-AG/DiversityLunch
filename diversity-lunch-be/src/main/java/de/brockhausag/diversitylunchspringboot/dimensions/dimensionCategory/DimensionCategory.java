package de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class DimensionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @NotBlank
    String description;
    @NotNull
    @NotBlank
    // This is the question, shown above the Selector in the Frontend
    String profileQuestion;
}
