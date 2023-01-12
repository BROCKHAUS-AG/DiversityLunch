package de.brockhausag.diversitylunchspringboot.dataFactories.dimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WeightedSelectableOptionTestDataFactory {

    public static final int WEIGHTED_OPTIONS_SET_SIZE = 4;

    public WeightedDimensionSelectableOption buildEntity(DimensionCategory category, int setNumber) {
        return switch (setNumber) {
            case 1 -> WeightedDimensionSelectableOption.builder()
                    .id((long) setNumber)
                    .value("Option1")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .weight(1)
                    .build();
            case 2 -> WeightedDimensionSelectableOption.builder()
                    .id((long) setNumber)
                    .value("Option2")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .weight(2)
                    .build();
            case 3 -> WeightedDimensionSelectableOption.builder()
                    .id((long) setNumber)
                    .value("Option3")
                    .ignoreInScoring(false)
                    .dimensionCategory(category)
                    .weight(3)
                    .build();
            default -> WeightedDimensionSelectableOption.builder()
                    .id((long) WEIGHTED_OPTIONS_SET_SIZE)
                    .value("DefaultOption")
                    .ignoreInScoring(true)
                    .dimensionCategory(category)
                    .weight(0)
                    .build();
        };
    }

    public Set<WeightedDimensionSelectableOption> buildEntities(DimensionCategory category, int amount) {
        return IntStream.range(0, WEIGHTED_OPTIONS_SET_SIZE).boxed().limit(amount).map((setNumber) -> buildEntity(category, setNumber)).collect(Collectors.toSet());
    }
}
