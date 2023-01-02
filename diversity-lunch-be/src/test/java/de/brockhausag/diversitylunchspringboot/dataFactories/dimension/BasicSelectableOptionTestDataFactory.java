package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicSelectableOptionTestDataFactory {

    public BasicDimensionSelectableOption buildEntity(DimensionCategory category, int setNumber) {
        return switch (setNumber) {
            case 1 -> BasicDimensionSelectableOption.builder()
                    .id(1L)
                    .value("Option1")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .build();
            case 2 -> BasicDimensionSelectableOption.builder()
                    .id(2L)
                    .value("Option2")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .build();
            case 3 -> BasicDimensionSelectableOption.builder()
                    .id(3L)
                    .value("Option3")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .build();
            default -> BasicDimensionSelectableOption.builder()
                    .id(10L)
                    .value("DefaultOption")
                    .ignoreInScoring(true)
                    .dimensionCategory(category)
                    .build();
        };
    }

    public Set<BasicDimensionSelectableOption> buildEntities(DimensionCategory category, int amount) {
        return Stream.of(0, 1, 2, 3).map((setNumber) -> buildEntity(category, setNumber)).limit(amount).collect(Collectors.toSet());
    }
}
