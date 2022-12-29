package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.weightedDimension.WeightedDimensionSelectableOption;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeightedSelectableOptionTestDataFactory {

    public WeightedDimensionSelectableOption buildEntity(DimensionCategory category, int setNumber) {
        return switch (setNumber) {
            case 1 -> WeightedDimensionSelectableOption.builder()
                    .id(1L)
                    .value("Option1")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .weight(1)
                    .build();
            case 2 -> WeightedDimensionSelectableOption.builder()
                    .id(2L)
                    .value("Option2")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .weight(2)
                    .build();
            case 3 -> WeightedDimensionSelectableOption.builder()
                    .id(3L)
                    .value("Option3")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .weight(3)
                    .build();
            default -> WeightedDimensionSelectableOption.builder()
                    .id(10L)
                    .value("DefaultOption")
                    .ignoreInScoring(true)
                    .dimensionCategory(category)
                    .weight(0)
                    .build();
        };
    }

    public Set<WeightedDimensionSelectableOption> buildEntities(DimensionCategory category, int amount) {
        return Stream.of(0, 1, 2, 3).map((setNumber) -> buildEntity(category, setNumber)).limit(amount).collect(Collectors.toSet());
    }
}
